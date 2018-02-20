/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author rkkitagawa
 */
public class VisualizarDetalheAtividadeController implements Initializable {

    @FXML
    private AnchorPane lbProjetoModulo;

    @FXML
    private Label lbDetalhamento;

    @FXML
    private Button btPlanilhaBDMG;

    @FXML
    private Button btPlanilhaSTEAFNINI;

    @FXML
    private Button btFaturar;

    @FXML
    private Button btCancelar;

    @FXML
    private Label lbTotalEstimadaoContrato;

    @FXML
    private Label lbTotalEstimadaoRepasse;

    @FXML
    private Label lbTotalDetalhadoContrato;

    @FXML
    private Label lbTotalDetalhadoRepasse;

    @FXML
    private TableView<?> tvLev;

    @FXML
    private TableColumn<?, ?> colIdLev;

    @FXML
    private TableColumn<?, ?> colOsLev;

    @FXML
    private TableColumn<?, ?> colPacoteLev;

    @FXML
    private TableColumn<?, ?> colAtividadeLev;

    @FXML
    private TableColumn<?, ?> colEstimativaPFLev;

    @FXML
    private TableColumn<?, ?> colDetalhadaPFLev;

    @FXML
    private TableColumn<?, ?> colEstimativaContratoLev;

    @FXML
    private TableColumn<?, ?> colEstimativaRepasseLev;

    @FXML
    private TableColumn<?, ?> colDetalhadaContratoLev;

    @FXML
    private TableColumn<?, ?> colDetalhadaRepasseLev;

    @FXML
    private TableColumn<?, ?> colAcoesLev;

    @FXML
    private Label lbTotalPfEstimadaLev;

    @FXML
    private Label lbTotalPfDetalhadaLev;

    @FXML
    private Label lbTotalEstimativaContratoLev;

    @FXML
    private Label lbTotalEstimativaRepasseLev;

    @FXML
    private Label lbTotalDetalhadaContratoLev;

    @FXML
    private Label lbTotalDetalhadaRepasseLev;

    @FXML
    private TableView<?> tvDev;

    @FXML
    private TableColumn<?, ?> colIdDev;

    @FXML
    private TableColumn<?, ?> colOsDev;

    @FXML
    private TableColumn<?, ?> colPacoteDev;

    @FXML
    private TableColumn<?, ?> colAtividadeDev;

    @FXML
    private TableColumn<?, ?> colEstimativaPFDev;

    @FXML
    private TableColumn<?, ?> colDetalhadaPFDev;

    @FXML
    private TableColumn<?, ?> colEstimativaContratoDev;

    @FXML
    private TableColumn<?, ?> colEstimativaRepasseDev;

    @FXML
    private TableColumn<?, ?> colDetalhadaContratoDev;

    @FXML
    private TableColumn<?, ?> colDetalhadaRepasseDev;

    @FXML
    private TableColumn<?, ?> colAcoesDev;

    @FXML
    private Label lbTotalPfEstimadaDev;

    @FXML
    private Label lbTotalPfDetalhadaDev;

    @FXML
    private Label lbTotalEstimativaContratoDev;

    @FXML
    private Label lbTotalEstimativaRepasseDev;

    @FXML
    private Label lbTotalDetalhadaContratoDev;

    @FXML
    private Label lbTotalDetalhadaRepasseDev;

    @FXML
    private TableView<?> tvTst;

    @FXML
    private TableColumn<?, ?> colIdTst;

    @FXML
    private TableColumn<?, ?> colOsTst;

    @FXML
    private TableColumn<?, ?> colPacoteTst;

    @FXML
    private TableColumn<?, ?> colAtividadeTst;

    @FXML
    private TableColumn<?, ?> colEstimativaPFTst;

    @FXML
    private TableColumn<?, ?> colDetalhadaPFTst;

    @FXML
    private TableColumn<?, ?> colEstimativaContratoTst;

    @FXML
    private TableColumn<?, ?> colEstimativaRepasseTst;

    @FXML
    private TableColumn<?, ?> colDetalhadaContratoTst;

    @FXML
    private TableColumn<?, ?> colDetalhadaRepasseTst;

    @FXML
    private TableColumn<?, ?> colAcoesTst;

    @FXML
    private Label lbTotalPfEstimadaTst;

    @FXML
    private Label lbTotalPfDetalhadaTst;

    @FXML
    private Label lbTotalEstimativaContratoTst;

    @FXML
    private Label lbTotalEstimativaRepasseTst;

    @FXML
    private Label lbTotalDetalhadaContratoTst;

    @FXML
    private Label lbTotalDetalhadaRepasseTst;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
