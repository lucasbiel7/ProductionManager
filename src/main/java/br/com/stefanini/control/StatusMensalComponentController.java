/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class StatusMensalComponentController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbTitulo;

    private Date inicio;

    private Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            stage = (Stage) apPrincipal.getScene().getWindow();
            stage.setResizable(false);
            inicio = (Date) apPrincipal.getUserData();
            lbTitulo.setText(new SimpleDateFormat("MM - MMMM").format(inicio));
        });
    }
    @FXML
    private void labelAtividadeActionEvent(){
        GerenciadorDeJanela gerenciadorDeJanela = new GerenciadorDeJanela();
        gerenciadorDeJanela.mostrarJanela(stage, gerenciadorDeJanela.carregarComponente("PesquisarAtividade",inicio), "Início").show();
    }

}
