/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.contr;

import com.xchan.ftapp.dto.AccountCreationDTO;
import com.xchan.ftapp.dto.ErrorMessage;
import com.xchan.ftapp.model.Account;
import com.xchan.ftapp.model.UserInformation;
import com.xchan.ftapp.service.AccountService;
import com.xchan.ftapp.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Christian
 */
@Controller
@RequestMapping("/u")
public class AccountController {
    
    private final static String GET_DASHBOARD = "get_dashboard";
    private final static String GET_ACCOUNT = "get_account";
    private final static String ADD_ACCOUNT = "add_account";
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("")
    public String getDashboard(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("asset_accounts", accountService.getAssetAccountsByUsername(username));
        model.addAttribute("liability_accounts", accountService.getLiabilityAccountsByUsername(username));
        return AccountController.GET_DASHBOARD;
    }     
    
    @GetMapping("/{uaid}")
    public String getAccount(Principal principal, @Param("uaid") long userAccId, Model model){
        String username = principal.getName();
        model.addAttribute("account", this.accountService.getAccountOfOwnerByUserAccountId(username, userAccId));
        model.addAttribute("asset_accounts", accountService.getAssetAccountsByUsername(username));
        model.addAttribute("liability_accounts", accountService.getLiabilityAccountsByUsername(username));
        return AccountController.GET_ACCOUNT;
    }
    
    @GetMapping("/add")
    public String getCreateAccount(Model model){
        model.addAttribute("accountdto", new AccountCreationDTO());
        return AccountController.ADD_ACCOUNT;
    }
    
    @PostMapping("/add")
    public String postCreateAccount(Principal principal, Model model, @ModelAttribute AccountCreationDTO accountCreationDTO){
        ErrorMessage errorMessage = this.accountService.validate(accountCreationDTO);
        if(errorMessage != null){
            model.addAttribute("error_code", errorMessage.getCode());
            model.addAttribute("error_msg", errorMessage.getMessage());
            model.addAttribute("userdto", accountCreationDTO);
            return AccountController.ADD_ACCOUNT;
        }
        else{
            accountCreationDTO.setOwnerUsername(principal.getName());
            Account account = this.accountService.addAccount(accountCreationDTO);
            return "redirect:/u/"+account.getUserAccountId();
        }
    }
}
