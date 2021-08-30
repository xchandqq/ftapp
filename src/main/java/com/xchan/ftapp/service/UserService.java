/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.service;

import com.xchan.ftapp.dto.AccountCreationDTO;
import com.xchan.ftapp.dto.ErrorMessage;
import com.xchan.ftapp.dto.UserDTO;
import com.xchan.ftapp.model.UserInformation;
import com.xchan.ftapp.repo.UserRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Christian
 */
@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private AccountService accountService;
    
    public void addNewUser(UserDTO userDto){
        UserInformation userInformation = new UserInformation();
        userInformation.setFirstname(userDto.getFirstname());
        userInformation.setLastname(userDto.getLastname());
        userInformation.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        userInformation.setUsername(userDto.getUsername());
        userInformation.setUserRole("USER");
        userInformation.setActivated(false);
        Long userId  = this.userRepo.save(userInformation).getId();
        
        AccountCreationDTO a1 = new AccountCreationDTO();
        a1.setAsset(true);
        a1.setCurrent(true);
        a1.setDisplayName("CoH");
        a1.setInitial(false);
        a1.setName("Cash on Hand");
        a1.setOwnerUsername(userDto.getUsername());
        
        AccountCreationDTO a2 = new AccountCreationDTO();
        a2.setAsset(true);
        a2.setCurrent(false);
        a2.setDisplayName("Rcv");
        a2.setInitial(false);
        a2.setName("Receivable");
        a2.setOwnerUsername(userDto.getUsername());
        
        AccountCreationDTO a3 = new AccountCreationDTO();
        a3.setAsset(false);
        a3.setCurrent(true);
        a3.setDisplayName("Pyb");
        a3.setInitial(false);
        a3.setName("Payable");
        a3.setOwnerUsername(userDto.getUsername());
        
        this.accountService.addAccount(a1);
        this.accountService.addAccount(a2);
        this.accountService.addAccount(a3);
    }
    
    public UserInformation getUserById(long id){
        return this.userRepo.findById(id).orElse(null);
    }
    
    public UserInformation getUserByUsername(String username){
        return this.userRepo.findByUsername(username).orElse(null);
    }
    
    public ErrorMessage validate(UserDTO userDto){
        //blanks
        if(userDto.getFirstname().isBlank()) return ErrorMessage.blankField("UD-01", "First Name");
        if(userDto.getLastname().isBlank()) return ErrorMessage.blankField("UD-02", "Last Name");
        if(userDto.getUsername().isBlank()) return ErrorMessage.blankField("UD-03", "Username");
        if(userDto.getPassword().isBlank()) return ErrorMessage.blankField("UD-04", "Password");
        //mins
        if(userDto.getUsername().length()<6) return ErrorMessage.notEnoughCharacters("UD-05", "Username", 6);
        if(userDto.getPassword().length()<6) return ErrorMessage.notEnoughCharacters("UD-06", "Password", 6);
        //custom
        if(!userDto.getPassword().equals(userDto.getRepeatedPassword())) return new ErrorMessage("UD-07", "Passwords do not match");
        if(this.getUserByUsername(userDto.getUsername())!=null) return new ErrorMessage("UD-08", "Username already taken");
        //no error
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInformation user = getUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Incorrect username");
        }
        else{
            List<GrantedAuthority> authority = new ArrayList();
            authority.add(new SimpleGrantedAuthority(user.getUserRole()));
            return new User(username, user.getPassword(), authority);
        }
    }
    
}
