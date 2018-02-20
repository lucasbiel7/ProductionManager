/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.OrdemServico;
import br.com.stefanini.model.entity.Pacote;
import br.com.stefanini.model.entity.ProgressoAtividade;
import br.com.stefanini.model.util.DoubleConverter;
import com.sun.javafx.collections.ObservableListWrapper;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author rkkitagawa
 */
public class VisualizarDetalheAtividadeController implements Initializable {

    @FXML
    private Label lbProjetoModulo;

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
    private TableView<ProgressoAtividade> tvLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colIdLev;

    @FXML
    private TableColumn<ProgressoAtividade, OrdemServico> colOsLev;

    @FXML
    private TableColumn<ProgressoAtividade, Pacote> colPacoteLev;

    @FXML
    private TableColumn<ProgressoAtividade, Atividade> colAtividadeLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaPFLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaPFLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaContratoLev;
    
    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaRepasseLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaContratoLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaRepasseLev;   

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
    private TableView<ProgressoAtividade> tvDev;

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
    private TableView<ProgressoAtividade> tvTst;

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
    
    @FXML
    private AnchorPane apPrincipal;
    private GerenciadorDeJanela gerenciadorDeJanela;
    private Stage stage;
    
    private Map<String,Object> paramsMap;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            paramsMap = (Map<String,Object>) apPrincipal.getUserData();
            lbDetalhamento.setText(buildLabelDetalhamento((Date)paramsMap.get("data")));
            List<Atividade> atividades = (List)paramsMap.get("atividades");
            lbProjetoModulo.setText(buildProjetoModulo(atividades));
        });
        
        colIdLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param1) -> {
            return new SimpleStringProperty(String.valueOf(tvLev.getItems().indexOf(param1.getValue()) + 1));
        });
        colOsLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, OrdemServico> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getOrdemServico()));
        colPacoteLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Pacote> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getPacote()));
        colAtividadeLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Atividade> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade()));
        colEstimativaPFLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getAtividade().getContagemEstimada()*.35)));
        colDetalhadaPFLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getAtividade().getContagemDetalhada()*.35)));
        colEstimativaContratoLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
        colEstimativaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
        colDetalhadaContratoLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
        colDetalhadaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
       
    }    
    
    private String buildProjetoModulo(List<Atividade> atividades){
        if(!atividades.isEmpty() && atividades.size()>0){
            StringBuilder sb = new StringBuilder();
            sb.append(atividades.get(0).getPacote().getModulo().getProjeto().getDescricao());            
            sb.append(" - ");
            sb.append(atividades.get(0).getPacote().getModulo().getDescricao());            
            return sb.toString();
        }else{
            return "<<Projeto>> - <<Módulo>>";
        }
    }
    
    private String buildLabelDetalhamento(Date date) {
        StringBuilder sb = new StringBuilder("Detalhamento de ");
        sb.append(new SimpleDateFormat("MM/YYYY").format(date));
        return sb.toString();
    }
    
}
