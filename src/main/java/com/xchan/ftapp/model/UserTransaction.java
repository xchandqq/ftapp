/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.model;

import com.xchan.ftapp.util.Decimal;
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
public class UserTransaction implements Serializable {
    
    public enum Type{
        DEBIT, CREDIT, TRANSFER
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private Decimal value;
    
    
    @OneToOne
    @JoinColumn
    private Account origin;
    
    @OneToOne
    @JoinColumn
    private Account destination;
    
    @Column
    private String transactionDate;
    
    @Column
    private Type type;
    
    @OneToOne
    @JoinColumn
    private TransactionCategory category;
    
    @Column
    private String detail;
    
    @Column
    private String note;
}
