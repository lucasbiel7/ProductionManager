/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.util;

import java.util.Locale;
import javafx.util.StringConverter;

/**
 *
 * @author rkkitagawa
 */
public class DoubleConverter extends StringConverter<Double>{  
    @Override
    public String toString(Double object) {       
        return String.format(Locale.US,"%.1f", object);
    }

    @Override
    public Double fromString(String string) {        
        return Double.parseDouble(string);
    }
    
    public static String doubleToString(Double object) {       
        return String.format(Locale.US,"%.2f", object);
    }
    
    public static Double stringToDouble(String string) {        
        return Double.parseDouble(string);
    }
}
