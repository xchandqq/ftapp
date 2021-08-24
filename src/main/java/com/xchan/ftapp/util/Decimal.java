/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.util;

/**
 *
 * @author Christian
 */
public class Decimal {
    
    private int numericalValue; //numerical value, basically any floating number multiplied by 100 and remove any floating points
    private String formattedString; //an integer with floating points
    
    //convert numerical value to formatted string
    public Decimal(int numerical){
        this.numericalValue = numerical;
        String s = numerical+"";
        switch (s.length()) {
            case 1:
                formattedString = "0.0"+s;
                break;
            case 2:
                formattedString = "0." +s;
                break;
            default:
                formattedString = s.substring(0, s.length() - 2) + "." + s.substring(s.length()-2);
                break;
        }
    }
    
    //convert formatted string to numerical value
    public Decimal(String formatted){        
        if(formatted.isBlank()) numericalValue = 0;
        else{
            boolean hasDecimalPoint = formatted.contains(".");
            if(hasDecimalPoint){
                //nothing after point
                if(formatted.length() == formatted.indexOf(".") + 1){
                    formatted = formatted.substring(0, formatted.length() - 1) + "00";
                }
                //1 digit after point
                else if(formatted.length() == formatted.indexOf(".") + 2){
                    formatted = formatted.substring(0, formatted.length() - 2) + formatted.charAt(formatted.length()-1) + "0";
                }
                else{
                    formatted = formatted.substring(0, formatted.indexOf('.')) + formatted.substring(formatted.indexOf('.')+1, formatted.indexOf('.')+3);
                }
            }
            else formatted += "00";
            numericalValue = Integer.valueOf(formatted);
        }
        formattedString = numericalValue == 0 ? "0.00" : formatted.substring(0, formatted.length()-2) + "." + formatted.substring(formatted.length()-2);
    }

    public String getFormattedString() {
        return formattedString;
    }

    public int getNumericalValue() {
        return numericalValue;
    }

    @Override
    public String toString() {
        return getFormattedString();
    }
    
}
