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

    public static String CAMPOS_OBRIGATORIOS = "É necessário preencher todos os campos obrigatórios!";

    /*
    ** Messages feitas para enviar informação para os usuários
    ** Ela utilizar já show and wait travando assim tudo que vier até ela ser fechada
     */
    public static void messageInformation(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    /*
    ** Messages feitas para enviar erros para os usuários
    ** Ela utilizar já show and wait travando assim tudo que vier até ela ser fechada
     */
    public static void messageError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    /*
    ** Message de confirmação para o usuário
    ** Retorna um boolean caso o usuário clicar no botão YES -> sim
     */
    public static boolean confirmMessage(String msg) {
        return new Alert(Alert.AlertType.CONFIRMATION, msg, ButtonType.YES, ButtonType.NO).showAndWait().orElse(ButtonType.NO).equals(ButtonType.YES);
    }
}
