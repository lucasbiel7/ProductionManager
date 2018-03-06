/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.StringConverter;

/**
 *
 * @author rkkitagawa
 */
public class DoubleConverter extends StringConverter<Double> {

    private static DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    private static DoubleConverter doubleConverter;

    private DoubleConverter() {
    }

    public static DoubleConverter getInstance() {
        if (doubleConverter == null) {
            doubleConverter = new DoubleConverter();
        }
        return doubleConverter;
    }

    @Override
    public String toString(Double object) {
        return String.format(Locale.US, "%.1f%n", object);
    }

    @Override
    public Double fromString(String string) {
        return Double.parseDouble(string);
    }

    public static String doubleToString(Double object) {
        return df.format(object);
    }

    public static Double stringToDouble(String string) {
        try {
            return (Double) df.parse(string);
        } catch (ParseException ex) {
            Logger.getLogger(DoubleConverter.class.getName()).log(Level.SEVERE, null, ex);
            return 0.0;
        }
    }
}
