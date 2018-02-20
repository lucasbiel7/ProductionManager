/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ModuloDAO;
import br.com.stefanini.control.dao.PacoteDAO;
import br.com.stefanini.control.dao.ProjetoDAO;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.Pacote;
import br.com.stefanini.model.entity.Projeto;
import br.com.stefanini.model.util.MessageUtil;
import com.mysql.cj.x.io.MessageBuilder;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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

    @FXML
    private ScrollPane spContainer;

    private GerenciadorDeJanela gerenciadorDeJanela;

    @FXML
    private AnchorPane apMeses;
    
    @FXML
    private ComboBox<Projeto> filtroProjeto;
    
    @FXML
    private ComboBox<Modulo> filtroModulo;
    
    @FXML
    private ComboBox<Pacote> filtroPacote;

    private Stage stage;

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
        Platform.runLater(() -> {
            stage = (Stage) apPrincipal.getScene().getWindow();
            stage.setResizable(true);
            stage.setMaximized(true);
        });
        filtroProjeto.getItems().setAll(new ProjetoDAO().pegarTodos());
    }
    
    @FXML
    private void buttonPesquisar(){
        
    }
    
    @FXML
    private void carregaModulos(){
        if(filtroProjeto.getValue() != null){
            filtroModulo.getItems().setAll(new ModuloDAO().pegarPorProjeto(filtroProjeto.getValue()));
        }else{
            filtroModulo.getItems().setAll(new ArrayList<Modulo>());
        }
    }
    
    @FXML
    private void carregaPacotes(){
        if(filtroModulo.getValue() != null){
            filtroPacote.getItems().setAll(new PacoteDAO().pegarPorModulo(filtroModulo.getValue()));
        }else{
            filtroPacote.getItems().setAll(new ArrayList<Pacote>());
        }
    } 

    private void carregarMeses() {
        gpMeses.getChildren().clear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, spAno.getValue());
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
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
    private void cabecalhoMouseEvent(MouseEvent mouseEvent) {
        spContainer.setContent(apMeses);
    }

    @FXML
    private void miProjetosActionEvent(ActionEvent ae) {
        spContainer.setContent(gerenciadorDeJanela.carregarComponente("ManterProjetos"));
    }

    @FXML
    private void miModuloActionEvent(ActionEvent ae) {
        spContainer.setContent(gerenciadorDeJanela.carregarComponente("ManterModulo"));
    }

    @FXML
    private void miCadastrarUsuariosActionEvent(ActionEvent ae) {
        spContainer.setContent(gerenciadorDeJanela.carregarComponente("ManterUsuario"));
    }

    @FXML
    private void miPerfilActionEvent(ActionEvent ae) {
        spContainer.setContent(gerenciadorDeJanela.carregarComponente("ManterPerfil"));
    }

    @FXML
    private void miManterPacotesActionEvent(ActionEvent ae) {
        spContainer.setContent(gerenciadorDeJanela.carregarComponente("ManterPacote"));
    }

    @FXML
    private void miManterAtuacaoActionEvent(ActionEvent ae) {
        spContainer.setContent(gerenciadorDeJanela.carregarComponente("ManterAtuacao"));
    }

    @FXML
    private void miOrdemServicoActionEvent(ActionEvent ae) {
        spContainer.setContent(gerenciadorDeJanela.carregarComponente("ManterOrdemServico"));
    }

    @FXML
    private void miManterParametroActionEvent(ActionEvent ae) {
        spContainer.setContent(gerenciadorDeJanela.carregarComponente("ManterParametro"));
    }
}
