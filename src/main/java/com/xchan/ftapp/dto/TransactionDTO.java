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
public class TransactionDTO {
    
    private long ownerId;
    private long accountOriginId;
    private long accountDestinationId;
    private long categoryId;
    
    private String date;
    private String type;
    private String value;
    private String detail;
    private String note;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getAccountOriginId() {
        return accountOriginId;
    }

    public void setAccountOriginId(long accountOriginId) {
        this.accountOriginId = accountOriginId;
    }

    public long getAccountDestinationId() {
        return accountDestinationId;
    }

    public void setAccountDestinationId(long accountDestinationId) {
        this.accountDestinationId = accountDestinationId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
    
}
