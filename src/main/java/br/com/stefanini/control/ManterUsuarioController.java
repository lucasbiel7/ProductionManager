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
import br.com.stefanini.model.util.MessageUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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

    private Usuario usuario;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apPrincipal.sceneProperty().addListener((ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) -> {
            if (newValue != null) {
                stage = (Stage) newValue.getWindow();
            }
        });
        gerenciadorDeJanela = new GerenciadorDeJanela();
        tvUsuario.getItems().setAll(new UsuarioDAO().pegarTodos());
        cbPerfil.getItems().setAll(new PerfilDAO().pegarTodos());
        tcNome.setCellValueFactory((TableColumn.CellDataFeatures<Usuario, String> param) -> new SimpleStringProperty(param.getValue().getPessoa().getNome()));
        tcCPF.setCellValueFactory((TableColumn.CellDataFeatures<Usuario, String> param) -> new SimpleStringProperty(param.getValue().getPessoa().getCpf()));
        tcEmail.setCellValueFactory((TableColumn.CellDataFeatures<Usuario, String> param) -> new SimpleStringProperty(param.getValue().getPessoa().getEmail()));
        tcPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));
        tcSituacao.setCellValueFactory(new PropertyValueFactory<>("ativado"));
        tcAcoes.setCellValueFactory((TableColumn.CellDataFeatures<Usuario, Usuario> param) -> new SimpleObjectProperty<>(param.getValue()));
        tcAcoes.setCellFactory((TableColumn<Usuario, Usuario> param) -> new TableCell<Usuario, Usuario>() {
            @Override
            protected void updateItem(Usuario item, boolean empty) {
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    HBox gerenciadorLayout = new HBox();
                    Button btEditar = new Button();
                    Button btAlterarStatus = new Button();
                    Button btExcluir = new Button();
                    ImageView ivEditar = new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/editar.png")));
                    ImageView ivAlterarStatus = new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/ativar.png")));
                    ImageView ivExcluir = new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/excluir.png")));
                    ivEditar.setFitHeight(15d);
                    ivAlterarStatus.setFitHeight(15d);
                    ivExcluir.setFitHeight(15d);
                    ivEditar.setFitWidth(15d);
                    ivAlterarStatus.setFitWidth(15d);
                    ivExcluir.setFitWidth(15d);
                    btEditar.setGraphic(ivEditar);
                    btAlterarStatus.setGraphic(ivAlterarStatus);
                    btExcluir.setGraphic(ivExcluir);
                    gerenciadorLayout.setSpacing(5);
                    gerenciadorLayout.setAlignment(Pos.CENTER);
                    gerenciadorLayout.getChildren().addAll(btEditar, btAlterarStatus, btExcluir);
                    setGraphic(gerenciadorLayout);
                    setAlignment(Pos.CENTER);
                    //Eventos dos botoes
                    btEditar.setOnAction((ActionEvent event) -> {
                        Stage stage1 = gerenciadorDeJanela.mostrarJanela(new Stage(), gerenciadorDeJanela.carregarComponente("AtualizarUsuario", item), "Editar Usuário");
                        stage1.initOwner(ManterUsuarioController.this.stage);
                        stage1.initModality(Modality.WINDOW_MODAL);
                        stage1.showAndWait();
                        tvUsuario.getItems().setAll(new UsuarioDAO().pegarTodos());
                    });
                    btAlterarStatus.setOnAction((ActionEvent event) -> {
                        item.setAtivado(!item.isAtivado());
                        new UsuarioDAO().editar(item);
                        tvUsuario.getItems().setAll(new UsuarioDAO().pegarTodos());
                    });
                    btExcluir.setOnAction((ActionEvent event) -> {
                        if (MessageUtil.confirmMessage("Você realmente deseja excluir este usuário?")) {
                            new UsuarioDAO().excluir(item);
                            tvUsuario.getItems().setAll(new UsuarioDAO().pegarTodos());
                        }
                    });
                }
            }
        });
        tcSituacao.setCellFactory((TableColumn<Usuario, Boolean> param) -> new TableCell<Usuario, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                if (empty) {
                    setText("");
                } else if (item) {
                    setText("Ativado");
                } else {
                    setText("Desativado");
                }
            }
        });
        usuario = new Usuario();
    }

    @FXML
    private void btPesquisarActionEvent(ActionEvent ae) {
        usuario.setPerfil(cbPerfil.getValue());
        usuario.getPessoa().setNome(tfNome.getText());
        usuario.getPessoa().setEmail(tfEmail.getText());
        usuario.getPessoa().setCpf(mtfCpf.getText());
        tvUsuario.getItems().setAll(new UsuarioDAO().pesquisarUsuario(usuario));
    }

    @FXML
    private void btNovoActionEvent(ActionEvent ae) {
        //Como abrir modal here
        Stage stage = gerenciadorDeJanela.mostrarJanela(new Stage(), gerenciadorDeJanela.carregarComponente("AtualizarUsuario"), "Incluir Usuário");
        stage.initOwner(this.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        tvUsuario.getItems().setAll(new UsuarioDAO().pegarTodos());
    }
}
