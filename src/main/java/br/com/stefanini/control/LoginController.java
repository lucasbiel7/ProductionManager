package br.com.stefanini.control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.stefanini.control.dao.ModuloDAO;
import br.com.stefanini.control.dao.UsuarioDAO;
import br.com.stefanini.model.entity.Usuario;
import br.com.stefanini.model.util.DateUtil;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.SecurityUtil;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

//    private Stage stage;
    
    private GerenciadorDeJanela gerenciadorDeJanela;

    Map<String,Object> params = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
//            stage = (Stage) apPrincipal.getScene().getWindow();
//            stage.setResizable(false);
//            stage.setWidth(960);
//            stage.setHeight(720);
            params = (Map<String, Object>) apPrincipal.getUserData();
            gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        });
        lbDataVersao.setText("Data atual: " + DateUtil.toDateFormater(new Date()) + " Versão: ");
    }

    @FXML
    private void btLoginActionEvent(ActionEvent ae) {        
        Usuario usuario = new UsuarioDAO().login(tfCPF.getText(), SecurityUtil.encript(pfUSenha.getText()));
        System.out.println(usuario);
        if (usuario != null) {
            gerenciadorDeJanela.getMain().user= usuario;            
            gerenciadorDeJanela.trocarCena(gerenciadorDeJanela.carregarComponente("PainelDeControle"),"PainelDeControle");
        } else {           
            MessageUtil.messageError("Favor verificar usuário e/ou senha informada, não foi localizado na base de dados.");
        }
    }
}
