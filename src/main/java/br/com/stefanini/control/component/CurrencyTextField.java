/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.component;

import br.com.stefanini.model.util.DoubleConverter;
import javafx.scene.control.TextField;

/**
 *
 * @author lgdutra1
 */
public class CurrencyTextField extends TextField{

    @Override
    public void replaceText(int start, int end, String text) {
        text = text.replace("[a-zA-Z]+", "");
        super.replaceText(start, end, text);
    }
    
    public Double getValue(){
        return DoubleConverter.stringToDouble(getText());
    }
    
    public void setValue(Double value){
        setText(DoubleConverter.doubleToString(value));
    }
}
