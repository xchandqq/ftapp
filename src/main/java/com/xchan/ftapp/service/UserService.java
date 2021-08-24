/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.service;

import com.xchan.ftapp.model.UserInformation;
import com.xchan.ftapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Christian
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;
    
    public void registerUser(UserInformation userInformation){
        this.userRepo.save(userInformation);
    }
    
}
