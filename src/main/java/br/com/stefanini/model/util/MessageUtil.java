/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author lucas
 */
public class MessageUtil {

    public static void messageInformation(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    public static void messageError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    public static boolean confirmMessage(String msg) {
        return new Alert(Alert.AlertType.CONFIRMATION, msg, ButtonType.YES, ButtonType.NO).showAndWait().orElse(ButtonType.NO).equals(ButtonType.YES);
    }
}
