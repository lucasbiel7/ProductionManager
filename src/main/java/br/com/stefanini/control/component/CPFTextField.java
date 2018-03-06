/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.component;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author lucas
 */
public class CPFTextField extends TextField {

    public CPFTextField() {
        textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue != null) {
                if (!newValue.matches("^\\d{3}\\.{1}\\d{3}\\.{1}\\d{3}\\-{1}\\d{2}$")) {
                    if (getText().length() > 3 && getText().charAt(3) != '.') {
                        setText(getText().substring(0, 3).concat(".").concat(getText().substring(3)));
                    }
                    if (getText().length() > 7 && getText().charAt(7) != '.') {
                        setText(getText().substring(0, 7).concat(".").concat(getText().substring(7)));
                    }
                    if (getText().length() > 11 && getText().charAt(11) != '-') {
                        setText(getText().substring(0, 11).concat("-").concat(getText().substring(11)));
                    }
                    if (getText().length() > 14) {
                        setText(getText().substring(0, 14));
                    }
                }
            }
        });
    }

    @Override
    public void replaceText(int start, int end, String text) {
        text = text.replace(".", "").replace("-", "");
        if (start == 3 || start == 7) {
            if (!text.equals(".")) {
                text = "." + text;
            }
        }
        if (start == 11) {
            if (!text.equals("-")) {
                text = "-" + text;
            }
        }
        if (getText() != null && getText().length() > 14) {
            text = "";
        }
        text = text.replaceAll("[^0-9\\.\\-]+", "");
        super.replaceText(start, end, text);
    }
}
