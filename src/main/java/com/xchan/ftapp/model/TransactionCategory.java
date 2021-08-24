/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table
public class TransactionCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String name;
    
    @OneToOne
    @JoinColumn
    private User owner;
    
    @Column
    private boolean standard;
    
    @Column
    private boolean queueable;
    
    @Column
    private boolean debitCategory;
    
    @Column
    private boolean creditCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }

    public boolean isQueueable() {
        return queueable;
    }

    public void setQueueable(boolean queueable) {
        this.queueable = queueable;
    }

    public boolean isDebitCategory() {
        return debitCategory;
    }

    public void setDebitCategory(boolean debitCategory) {
        this.debitCategory = debitCategory;
    }

    public boolean isCreditCategory() {
        return creditCategory;
    }

    public void setCreditCategory(boolean creditCategory) {
        this.creditCategory = creditCategory;
    }
    
    
    
}
