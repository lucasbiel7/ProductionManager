/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class PainelDeControleController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Spinner<Integer> spAno;
    @FXML
    private GridPane gpMeses;

    private GerenciadorDeJanela gerenciadorDeJanela;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Calendar calendar = Calendar.getInstance();
        spAno.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, calendar.get(Calendar.YEAR)));
        spAno.getValueFactory().valueProperty().addListener((ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) -> {
            carregarMeses();
        });
        gerenciadorDeJanela = new GerenciadorDeJanela();
        //Carregar os meses quando inicia os componente
        carregarMeses();
    }

    private void carregarMeses() {
        gpMeses.getChildren().clear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, spAno.getValue());
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        GerenciadorDeJanela gerenciadorDeJanela = new GerenciadorDeJanela();
        int linha = 0;
        int coluna = 0;
        while (calendar.get(Calendar.YEAR) <= spAno.getValue()) {
            Parent parent = gerenciadorDeJanela.carregarComponente("StatusMensalComponent", calendar.getTime());
            calendar.add(Calendar.MONTH, 1);
            gpMeses.add(parent, coluna, linha);
            coluna++;
            if (coluna >= 4) {
                coluna = 0;
                linha++;
            }
        }
    }

    @FXML
    private void miProjetosActionEvent(ActionEvent ae) {
        Stage stage = gerenciadorDeJanela.mostrarJanela(new Stage(), gerenciadorDeJanela.carregarComponente("ManterProjetos"), "Manter projetos");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(apPrincipal.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void miModuloActionEvent(ActionEvent ae) {
        Stage stage = gerenciadorDeJanela.mostrarJanela(new Stage(), gerenciadorDeJanela.carregarComponente("ManterModulo"), "Manter modulos");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(apPrincipal.getScene().getWindow());
        stage.showAndWait();
    }

}
