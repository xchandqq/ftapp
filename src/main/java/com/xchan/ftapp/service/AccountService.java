/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.service;

import com.xchan.ftapp.dto.AccountCreationDTO;
import com.xchan.ftapp.dto.ErrorMessage;
import com.xchan.ftapp.model.Account;
import com.xchan.ftapp.model.UserInformation;
import com.xchan.ftapp.model.UserTransaction;
import com.xchan.ftapp.repo.AccountRepo;
import com.xchan.ftapp.repo.TransactionRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Christian
 */
@Service
public class AccountService {
    
    @Autowired
    private AccountRepo accountRepo;
    
    @Autowired
    private TransactionRepo transactionRepo;
    
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private UserService userService;
    
    public Account addAccount(AccountCreationDTO accountCreationDTO){
        Account account = new Account();
        account.setName(accountCreationDTO.getName());
        account.setAsset(accountCreationDTO.isAsset());
        account.setCurrentAccount(accountCreationDTO.isCurrent());
        account.setDisplayName(accountCreationDTO.getDisplayName());
        account.setInitialized(accountCreationDTO.isInitial()); 
        
        UserInformation owner = this.userService.getUserByUsername(accountCreationDTO.getOwnerUsername());        
        account.setOwner(owner);  
        
        
        Long accountId = this.accountRepo.findMaxIdOfAccountByOwner(owner.getId());
        if(accountId == null) accountId = Long.valueOf(0);
        account.setUserAccountId(accountId);
        
        account.setFrozen(false);   
        account.setDisplayOrder(0);
        account = this.accountRepo.save(account);
        
        if(accountCreationDTO.isInitial()){        
            UserTransaction userTransaction = new UserTransaction();
            userTransaction.setOrigin(accountRepo.findByName(accountCreationDTO.getName(), owner.getId()));
            userTransaction.setTransactionDate(accountCreationDTO.getDate());
            userTransaction.setTransactionValue(accountCreationDTO.getValue());
            userTransaction.setType(UserTransaction.Type.INITIAL);
            userTransaction.setOwner(owner);
        }
        
        return account;
    }
    
    public Account getAccountById(long id){
        return this.accountRepo.findById(id).orElse(null);
    }
    
    public Account getAccountOfOwnerByUserAccountId(String username, long userAccountId){
        UserInformation userInformation = this.userService.getUserByUsername(username);
        return this.accountRepo.findByOwnerAndUserAccountId(userInformation, userAccountId).orElse(null);        
    }
    
    public List<Account> getAssetAccountsByUsername(String username){
        UserInformation userInformation = userService.getUserByUsername(username);
        return this.accountRepo.findByOwnerAndAssetOrderByDisplayOrderAsc(userInformation, true);
    }
    
    public List<Account> getLiabilityAccountsByUsername(String username){
        UserInformation userInformation = userService.getUserByUsername(username);
        return this.accountRepo.findByOwnerAndAssetOrderByDisplayOrderAsc(userInformation, false);
    }
    
    public ErrorMessage validate(AccountCreationDTO dto){
        //blank
        if(dto.getDate().isBlank()) return ErrorMessage.blankField("AC-01", "Date");
        if(dto.getDisplayName().isBlank()) return ErrorMessage.blankField("AC-02", "Date");
        if(dto.getName().isBlank()) return ErrorMessage.blankField("AC-03", "Date");
        if(dto.getValue().isBlank()) return ErrorMessage.blankField("AC-04", "Date");
        return null;
    }
    
}
