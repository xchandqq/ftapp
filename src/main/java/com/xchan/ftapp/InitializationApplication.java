/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp;

import com.xchan.ftapp.dto.AccountCreationDTO;
import com.xchan.ftapp.dto.UserDTO;
import com.xchan.ftapp.model.Account;
import com.xchan.ftapp.model.TransactionCategory;
import com.xchan.ftapp.model.UserInformation;
import com.xchan.ftapp.repo.CategoryRepo;
import com.xchan.ftapp.repo.UserRepo;
import com.xchan.ftapp.service.AccountService;
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
    private UserRepo userRepo;
    
    @Autowired
    private CategoryRepo categoryRepo;
    
//    @Autowired
//    private AccountService accountService;
//    
//    @Autowired
//    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.createAdminUser();
        this.createStandardCategories();
        
        //test
//        this.createUserTest();
//        this.createAccountTest();
    }
    
    private void createAdminUser(){
        UserInformation user = new UserInformation();
        user.setActivated(true);
        user.setUserRole("ADMIN");
        user.setFirstname("");
        user.setLastname("Administrator");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        
        this.userRepo.save(user);
    }
    
    private void createStandardCategories(){
        String[] categories = new String[]{
            "Investment:1:1",
            "Salary:1:0",
            "Other Income:1:0",
            "Reimbursement:1:0",
            "Gift:1:0",
            "Bills:0:1",
            "Insurance:0:1",
            "Withheld:0:1",
            "Other Expenses:0:1"
        };
        
        for(String s : categories){
            String[] splitArray = s.split(":");
            TransactionCategory tc = new TransactionCategory();
            tc.setName(splitArray[0]);
            tc.setStandard(true);
            tc.setQueueable(true);
            tc.setIncomeCategory(splitArray[1].equals("1"));
            tc.setExpenseCategory(splitArray[2].equals("1"));
            this.categoryRepo.save(tc);
        }
    }
    
//    private void createUserTest(){
//        UserDTO userDTO = new UserDTO();
//        userDTO.setFirstname("Christian");
//        userDTO.setLastname("Abellanosa");
//        userDTO.setPassword("123");
//        userDTO.setRepeatedPassword("123");
//        userDTO.setUsername("xchandqq");
//        this.userService.addNewUser(userDTO);
//    }
    
//    private void createAccountTest(){
//        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
//        accountCreationDTO.setAsset(true);
//        accountCreationDTO.setCurrent(true);
//        accountCreationDTO.setDate("TODAY");
//        accountCreationDTO.setDisplayName("display");
//        accountCreationDTO.setInitial(true);
//        accountCreationDTO.setName("Name of account");
//        accountCreationDTO.setOwnerUsername("xchandqq");
//        accountCreationDTO.setValue("10.50");
//        accountService.addAccount(accountCreationDTO);
//        
//        accountCreationDTO.setAsset(false);
//        accountCreationDTO.setDisplayName("sdf");
//        accountCreationDTO.setName("Payables");
//        accountService.addAccount(accountCreationDTO);
//    }
    
}
