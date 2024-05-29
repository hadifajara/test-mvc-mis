package com.crud.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.crud.app.models.UserModel;
import com.crud.app.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "registration_page";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserModel user) {
    	
    	log.info("print => {}", user);
    	
    	if(userService.findByUser(user.getUsername()) != null) {
    		return "redirect:/registration?exist";
    	}else {
    		
    		userService.register(user);
            return "redirect:/login?success";
    	}
    }
}
