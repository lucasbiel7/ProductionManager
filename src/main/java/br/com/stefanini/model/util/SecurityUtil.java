/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class SecurityUtil {

    public static String encript(String senha) {
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            StringBuilder hexString = new StringBuilder();
            for (byte b : algoritmo.digest(senha.getBytes("UTF-8"))) {
                hexString.append(String.format("%02x", 0xff & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SecurityUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
