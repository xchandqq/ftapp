/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp;

import com.xchan.ftapp.model.UserInformation;
import com.xchan.ftapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Christian
 */
@Component
public class InitializationApplication implements ApplicationRunner{
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.createAdminUser();
    }
    
    private void createAdminUser(){
        UserInformation user = new UserInformation();
        user.setActivated(true);
        user.setUserRole("ADMIN");
        user.setFirstname("Christian");
        user.setLastname("Abellanosa");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        
        this.userService.registerUser(user);
    }
    
}
