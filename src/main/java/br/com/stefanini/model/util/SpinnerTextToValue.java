/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.util;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Spinner;

/**
 *
 * @author lucas
 */
public class SpinnerTextToValue {

    public static void configure(Spinner<Double> spinner) {
        spinner.getEditor().textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue.matches("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$")) {
                spinner.getValueFactory().setValue(Double.parseDouble(newValue));
            } else if (!StringUtil.isEmpty(newValue) && !oldValue.equals(newValue)) {
                spinner.getEditor().setText(oldValue);
            }
        });
    }

}
