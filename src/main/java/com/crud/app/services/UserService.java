package com.crud.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.app.models.UserModel;
import com.crud.app.models.UserRole;
import com.crud.app.repositories.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	@Autowired
	private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public void register(UserModel user) {
    	user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        userRepo.save(user);
    }
    
    public UserModel findByUser(String username) {
    	return userRepo.findByUsernameList(username).stream().filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        
    }
}
