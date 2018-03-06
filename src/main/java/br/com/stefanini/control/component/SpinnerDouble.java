/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.component;

import br.com.stefanini.model.util.DoubleConverter;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

/**
 *
 * @author lucas
 */
public final class SpinnerDouble extends Spinner<Double> {

    SpinnerValueFactory.DoubleSpinnerValueFactory factory;

    public SpinnerDouble() {
        setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE));
        getFactory().setConverter(DoubleConverter.getInstance());
        NumberFormat format = NumberFormat.getNumberInstance();
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (c.isContentChange()) {
                ParsePosition parsePosition = new ParsePosition(0);
                // NumberFormat evaluates the beginning of the text
                format.parse(c.getControlNewText(), parsePosition);
                if (parsePosition.getIndex() == 0
                        || parsePosition.getIndex() < c.getControlNewText().length()) {
                    // reject parsing the complete text failed
                    return null;
                }
            }
            return c;
        };
        TextFormatter<Double> number = new TextFormatter<>(getValueFactory().getConverter(), 0d, filter);

        getEditor().setTextFormatter(number);
        getEditor().textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue != null && newValue.matches("[^0-9\\.,]+")) {
                newValue = newValue.replaceAll("[^0-9\\.,]+", "");
                getEditor().setText(newValue);
            }
        });

        getEditor().textProperty().addListener(action -> {
            String text = getEditor().getText();
            if (getEditor().getText() != null && getEditor().getText().matches("[^0-9\\.,]+")) {
                getEditor().setText(getEditor().getText().replaceAll("[^0-9\\.,]+", ""));
            }
            SpinnerValueFactory<Double> valueFactory = getValueFactory();
            if (valueFactory != null) {
                StringConverter<Double> converter = valueFactory.getConverter();
                if (converter != null) {
                    if (text.matches("^\\d+[,.]?\\d*$")) {
                        Double value = converter.fromString(text);
                        valueFactory.setValue(value);
                    }
                }
            }
        });

    }

    public SpinnerValueFactory.DoubleSpinnerValueFactory getFactory() {
        return (SpinnerValueFactory.DoubleSpinnerValueFactory) getValueFactory();
    }

}
