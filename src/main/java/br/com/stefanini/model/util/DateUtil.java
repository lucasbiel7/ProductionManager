/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lucas
 */
public class DateUtil {

    public static String formatterDate(Date data, String formato) {
        if (data == null) {
            return "";
        }
        return new SimpleDateFormat(formato).format(data);
    }

    public static String toDateFormater(Date date) {
        return formatterDate(date, "dd/MM/yyyy");
    }
}
