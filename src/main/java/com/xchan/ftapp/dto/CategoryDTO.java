/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.dto;

/**
 *
 * @author Christian
 */
public class CategoryDTO {
    private String name;
    private boolean showAsIncome;
    private boolean showAsExpense;
    private Long ownerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShowAsIncome() {
        return showAsIncome;
    }

    public void setShowAsIncome(boolean showAsIncome) {
        this.showAsIncome = showAsIncome;
    }

    public boolean isShowAsExpense() {
        return showAsExpense;
    }

    public void setShowAsExpense(boolean showAsExpense) {
        this.showAsExpense = showAsExpense;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    
    
}
