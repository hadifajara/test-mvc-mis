package com.crud.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crud.app.models.UserModel;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel byUser = userService.findByUser(username);
        if (byUser == null) {
            return null;
        }
        return User.builder()
                .username(byUser.getUsername())
                .password(byUser.getPassword())
                .roles(byUser.getRole().name())
                .build();
    }
}
