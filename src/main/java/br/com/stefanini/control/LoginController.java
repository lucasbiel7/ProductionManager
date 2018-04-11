package br.com.stefanini.control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.stefanini.control.dao.UsuarioDAO;
import br.com.stefanini.control.dao.VersaoDAO;
import br.com.stefanini.model.entity.Usuario;
import br.com.stefanini.model.entity.Versao;
import br.com.stefanini.model.util.DateUtil;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.SecurityUtil;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfCPF;
    @FXML
    private PasswordField pfUSenha;
    @FXML
    private Label lbDataVersao;
    @FXML
    private Label lbInformation;
    @FXML
    private Button btEntrar;

    private Stage stage;
    private GerenciadorDeJanela gerenciadorDeJanela;
    private final Double nrVersaoAtual = 1.1;

    Map<String, Object> params = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            params = (Map<String, Object>) apPrincipal.getUserData();
            if(params != null){
                gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
            }
        });
         apPrincipal.sceneProperty().addListener((ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) -> {
                if (newValue != null) {
                    newValue.windowProperty().addListener((ObservableValue<? extends Window> observable1, Window oldValue1, Window newValue1) -> {
                        if (newValue1 != null) {
                            stage = (Stage) newValue1;
                            stage.setResizable(false);
                            stage.setWidth(960);
                            stage.setHeight(720);
                            stage.centerOnScreen();
                            tfCPF.clear();
                            pfUSenha.clear();
                            tfCPF.requestFocus();
                        }
                    });
                }

            });
        Versao versao = VersaoDAO.getInstance().getVersaoAtual();
        if(!nrVersaoAtual.equals(versao.getNrVersao())){
            MessageUtil.messageInformation("A versão utilizada foi descontinuada! Entrar em contato com o administrador do sistema.");
            tfCPF.setDisable(true);
            pfUSenha.setDisable(true);
            btEntrar.setDisable(true);
            lbInformation.setVisible(true);
        } 
        lbDataVersao.setText("Data atual: " + DateUtil.toDateFormater(new Date()) + " Versão: " + nrVersaoAtual);  
    }

    @FXML
    private void btLoginActionEvent(ActionEvent ae) {
        Usuario usuario = UsuarioDAO.getInstance().login(tfCPF.getText(), SecurityUtil.encript(pfUSenha.getText()));
        params.put("usuario", usuario);
        if (usuario != null) {
            gerenciadorDeJanela.getMain().user = usuario;
             gerenciadorDeJanela.trocarCena(gerenciadorDeJanela.carregarComponente("PainelDeControle", params), "PainelDeControle");
        } else {
            MessageUtil.messageError("Favor verificar usuário e/ou senha informada, não foi localizado na base de dados.");
        }
    }
}
