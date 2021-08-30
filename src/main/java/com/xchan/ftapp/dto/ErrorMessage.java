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
public class ErrorMessage {
    
    private String message;
    private String code;
    
    public ErrorMessage(String code, String message){
        this.code = code;
        this.message = message;
    }
    
    public ErrorMessage(){
        this.code = "";
        this.message = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public static ErrorMessage blankField(String code, String fieldname){
        return new ErrorMessage(code, String.format("Do not leave the field \"%s\" blank", fieldname));
    }
    
    public static ErrorMessage notEnoughCharacters(String code, String fieldname, int minCharacters){
        return new ErrorMessage(code, String.format("\"%s\" must have at least %d characters", fieldname, minCharacters));
    }
    
}
