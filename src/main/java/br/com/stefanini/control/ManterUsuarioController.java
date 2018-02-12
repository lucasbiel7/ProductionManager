/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.PerfilDAO;
import br.com.stefanini.control.dao.UsuarioDAO;
import br.com.stefanini.model.entity.Perfil;
import br.com.stefanini.model.entity.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jidefx.scene.control.field.MaskTextField;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class ManterUsuarioController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private ComboBox<Perfil> cbPerfil;

    @FXML
    private MaskTextField mtfCpf;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEmail;
    @FXML
    private TableView<Usuario> tvUsuario;
    @FXML
    private TableColumn<Usuario, String> tcNome;
    @FXML
    private TableColumn<Usuario, String> tcCPF;
    @FXML
    private TableColumn<Usuario, String> tcEmail;
    @FXML
    private TableColumn<Usuario, Perfil> tcPerfil;
    @FXML
    private TableColumn<Usuario, Boolean> tcSituacao;
    @FXML
    private TableColumn<Usuario, Usuario> tcAcoes;

    private GerenciadorDeJanela gerenciadorDeJanela;

    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            stage = (Stage) apPrincipal.getScene().getWindow();
        });
        gerenciadorDeJanela = new GerenciadorDeJanela();
        tvUsuario.getItems().setAll(new UsuarioDAO().pegarTodos());
        cbPerfil.getItems().setAll(new PerfilDAO().pegarTodos());

    }

    @FXML
    private void btPesquisarActionEvent(ActionEvent ae) {

    }

    @FXML
    private void btNovoActionEvent(ActionEvent ae) {
        //Como abrir modal here
        Stage stage = gerenciadorDeJanela.mostrarJanela(new Stage(), gerenciadorDeJanela.carregarComponente("AtualizarUsuario"), "Incluir Usuário");
        stage.initOwner(this.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
}
