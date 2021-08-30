/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.contr;

import com.xchan.ftapp.dto.ErrorMessage;
import com.xchan.ftapp.dto.UserDTO;
import com.xchan.ftapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Christian
 */
@Controller
public class UserController {
    
    private final static String GET_HOME = "get_home";
    private final static String GET_SIGNUP = "get_signup";
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String getHome(){
        return UserController.GET_HOME;
    }
    
    @GetMapping("/sign-up")
    public String getSignup(Model model){
        model.addAttribute("userdto", new UserDTO());
        return UserController.GET_SIGNUP;
    }
    
    @PostMapping("/sign-up")
    public String postSignup(Model model, @ModelAttribute UserDTO userDTO){
        ErrorMessage errorMessage = this.userService.validate(userDTO);
        if(errorMessage != null){
            model.addAttribute("error_code", errorMessage.getCode());
            model.addAttribute("error_msg", errorMessage.getMessage());
            model.addAttribute("userdto", userDTO);
            return UserController.GET_SIGNUP;
        }
        else{
            this.userService.addNewUser(userDTO);
            return "redirect:/u";
        }
    }
    
}
