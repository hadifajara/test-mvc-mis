package com.crud.app.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crud.app.models.UploadModel;
import com.crud.app.models.UserModel;
import com.crud.app.repositories.UploadRepo;
import com.crud.app.repositories.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private UploadRepo uploadRepo;
	
	public void saveData(UserModel model) {
		
		repo.save(model);
		
	}
	
	public List<UserModel> getAllUser(){
		
		return repo.findAll();
	}
	
	public UserModel getUserById(long id){
		
		return repo.findById(id);
	}
	
	public void deleteById(long id) {
		
		repo.deleteById(id);
	}

	public void processUpload(MultipartFile file) {
		// TODO Auto-generated method stub
		List<UploadModel> uploadList = new ArrayList<UploadModel>();
		HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(file.getInputStream());
			HSSFSheet worksheet = workbook.getSheetAt(0);
	        
	        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	        	
	            UploadModel uploadModel = new UploadModel();
	                
	            HSSFRow row = worksheet.getRow(i);
	            
	            uploadModel.setKode(Integer.toString((int)row.getCell(0).getNumericCellValue()));
	            uploadModel.setProduk(row.getCell(1).getStringCellValue());
	            uploadModel.setJumlah(Integer.toString((int)row.getCell(2).getNumericCellValue()));
	            uploadList.add(uploadModel); 
	        }
	        
	        uploadRepo.saveAll(uploadList);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
	}
	
	public List<UploadModel> getAllUpload(){
		
		return uploadRepo.findAll();
	}
	
	public void saveDataUpload(UploadModel model) {
		
		uploadRepo.save(model);
		
	}
	
	public UploadModel getUploadById(long id){
		
		return uploadRepo.findById(id);
	}
	
	public void deleteUploadById(long id) {
		
		uploadRepo.deleteById(id);
	}
	
//	public String getDetail(Long paymentId) {
//		
//		List<Object[]> payDetail = repo.getDetail(paymentId);
//		for (Object[] obj : payDetail) {
//			log.info("res => {}", Arrays.toString(obj));
//		}
//		return "";
//	}
}
