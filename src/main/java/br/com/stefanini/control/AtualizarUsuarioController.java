/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.AtuacaoDAO;
import br.com.stefanini.control.dao.PerfilDAO;
import br.com.stefanini.model.entity.Atuacao;
import br.com.stefanini.model.entity.Atuando;
import br.com.stefanini.model.entity.Perfil;
import br.com.stefanini.model.entity.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class AtualizarUsuarioController implements Initializable {

    private Usuario usuario;

    @FXML
    private ComboBox<Perfil> cbPerfil;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField mtfCPF;
    @FXML
    private TextField tfEmail;
    @FXML
    private ListView<Atuacao> lvAtuacao;
    @FXML
    private ListView<Atuando> lvAtuacaoSelecionado;

    @FXML
    private AnchorPane apPrincipal;

    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario = new Usuario();
        cbPerfil.getItems().setAll(new PerfilDAO().pegarTodos());
        Platform.runLater(() -> {
            stage = (Stage) apPrincipal.getScene().getWindow();
            if (apPrincipal.getUserData() instanceof Usuario) {

            }
        });
        lvAtuacao.getItems().setAll(new AtuacaoDAO().pegarTodos());
    }

    @FXML
    private void btCancelarActionEvent(ActionEvent ae) {
        stage.close();
    }

    @FXML
    private void btConfirmarActionEvent(ActionEvent ae) {

    }

    @FXML
    private void btAdicionarActionEvent(ActionEvent ae) {

    }

    @FXML
    private void btAdicionarTodosActionEvent(ActionEvent ae) {

    }

    @FXML
    private void btRemoverTodosActionEvent(ActionEvent ae) {

    }

    @FXML
    private void btRemoverActionEvent(ActionEvent ae) {

    }
}
