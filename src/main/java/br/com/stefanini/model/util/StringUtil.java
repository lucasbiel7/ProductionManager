/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.util;

/**
 *
 * @author lucas
 */
public class StringUtil {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
