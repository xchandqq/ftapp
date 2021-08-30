/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.service;

import com.xchan.ftapp.dto.CategoryDTO;
import com.xchan.ftapp.dto.TransactionDTO;
import com.xchan.ftapp.model.TransactionCategory;
import com.xchan.ftapp.model.UserTransaction;
import com.xchan.ftapp.repo.CategoryRepo;
import com.xchan.ftapp.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Christian
 */
@Service
public class TransactionService {
    
    @Autowired
    private CategoryRepo categoryRepo;
    
    @Autowired
    private TransactionRepo transactionRepo;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AccountService accountService;
    
    public void addCategory(CategoryDTO categoryDTO){        
        TransactionCategory transactionCategory = new TransactionCategory();
        transactionCategory.setName(categoryDTO.getName());
        transactionCategory.setIncomeCategory(categoryDTO.isShowAsIncome());
        transactionCategory.setExpenseCategory(categoryDTO.isShowAsExpense());
        transactionCategory.setOwner(this.userService.getUserById(categoryDTO.getOwnerId()));
        transactionCategory.setStandard(false);
        transactionCategory.setQueueable(true);
        this.categoryRepo.save(transactionCategory);
    }
    
    public void addTransaction(TransactionDTO transactionDTO){
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setDetail(transactionDTO.getDetail());
        userTransaction.setTransactionDate(transactionDTO.getDate());
        userTransaction.setNote(transactionDTO.getNote());
        userTransaction.setTransactionValue(transactionDTO.getValue());
        userTransaction.setType(UserTransaction.Type.valueOf(transactionDTO.getDetail()));
        
        userTransaction.setOwner(this.userService.getUserById(transactionDTO.getOwnerId()));        
        userTransaction.setOrigin(this.accountService.getAccountById(transactionDTO.getAccountOriginId()));
        userTransaction.setDestination(this.accountService.getAccountById(transactionDTO.getAccountDestinationId()));   
        userTransaction.setCategory(this.categoryRepo.findById(transactionDTO.getCategoryId()).orElse(null));   
        
        this.transactionRepo.save(userTransaction);     
    }
    
}
