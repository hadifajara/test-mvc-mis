package com.crud.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crud.app.models.UploadModel;
import com.crud.app.models.UserModel;
import com.crud.app.services.MainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@Autowired
	MainService mainService;
	
	@GetMapping("/")
	public String main(){
		return "index";
	}
	
	@GetMapping("/view")
	public String viewData(Model model) {
		
		List<UserModel> users = mainService.getAllUser();
		model.addAttribute("users", users);
		
		return "view";
	}
	
	@GetMapping("/input")
	public String addData(Model model) {
		
		UserModel user = new UserModel();
		model.addAttribute("user", user);
		
		return "input";
	}
	
	@GetMapping("/input/upload")
	public String addDataUpload(Model model) {
		
		UploadModel upload = new UploadModel();
		model.addAttribute("upload", upload);
		
		return "input_upload";
	}
	
	@GetMapping("/edit/{id}")
	public String editData(Model model, @PathVariable String id) {
		
		UserModel user = mainService.getUserById(Long.parseLong(id));
		model.addAttribute("user", user);
		
		return "input";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteData(Model model, @PathVariable String id) {
		
		mainService.deleteById(Long.parseLong(id));
		
		return viewData(model);
	}
	
	@PostMapping("/insertOrUpdate")
	public String insertData(@ModelAttribute("user") UserModel user) {
		
//		System.out.println(user.getId());
//		System.out.println(user.getName());
//		System.out.println(user.getAddress());
		
		mainService.saveData(user);
		
		return "input";
	}
	
//	@GetMapping("/get/detail/{id}")
//	public String getDetail(@PathVariable String id) {
//		mainService.getDetail(Long.parseLong(id));
//		return "index";
//	}
	
	
	@PostMapping("/files/upload")
	  public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
	    String message = "";

	    try {
	    	
	    	mainService.processUpload(file);
	    	
	    } catch (Exception e) {
	      log.error(e.getMessage());
	    }

	    return "index";
	  }
	
	
	@GetMapping("/view/upload")
	public String viewUploadData(Model model) {
		
		List<UploadModel> uploads = mainService.getAllUpload();
		model.addAttribute("uploads", uploads);
		
		return "view_upload";
	}
	
	@PostMapping("/insertOrUpdateUpload")
	public String insertDataUpoad(@ModelAttribute("upload") UploadModel upload) {
		
//		System.out.println(user.getId());
//		System.out.println(user.getName());
//		System.out.println(user.getAddress());
		
		mainService.saveDataUpload(upload);
		
		return "input_upload";
	}
	
	@GetMapping("/upload/edit/{id}")
	public String editDataUpload(Model model, @PathVariable String id) {
		
		UploadModel upload = mainService.getUploadById(Long.parseLong(id));
		model.addAttribute("upload", upload);
		
		return "input_upload";
	}
	
	@GetMapping("/upload/delete/{id}")
	public String deleteDataUpload(Model model, @PathVariable String id) {
		
		mainService.deleteUploadById(Long.parseLong(id));
		
		return viewUploadData(model);
	}
}
