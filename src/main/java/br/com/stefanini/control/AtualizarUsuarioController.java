/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.AtuacaoDAO;
import br.com.stefanini.control.dao.UsuarioDAO;
import br.com.stefanini.model.entity.Atuacao;
import br.com.stefanini.model.entity.Atuando;
import br.com.stefanini.model.entity.Atuando.AtuadoID;
import br.com.stefanini.model.entity.Pessoa;
import br.com.stefanini.model.entity.Usuario;
import br.com.stefanini.model.enuns.TipoPerfil;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.SecurityUtil;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class AtualizarUsuarioController implements Initializable {

    private Usuario usuario;

    @FXML
    private ComboBox<TipoPerfil> cbPerfil;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField mtfCPF;
    @FXML
    private TextField tfEmail;
    @FXML
    private ListView<Atuacao> lvAtuacao;
    @FXML
    private ListView<Atuacao> lvAtuando;
    @FXML
    private PasswordField pfSenha;
    @FXML
    private PasswordField pfConfirmarSenha;

    @FXML
    private AnchorPane apPrincipal;

    private Stage stage;
    
    private GerenciadorDeJanela gerenciadorDeJanela;
    
    Map<String, Object> params = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        params = (Map) apPrincipal.getUserData();
        usuario = new Usuario();
        usuario.setAtuando(new ArrayList<>());
        cbPerfil.getItems().setAll(TipoPerfil.values());
        apPrincipal.sceneProperty().addListener((ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) -> {
            if (newValue != null) {
                newValue.windowProperty().addListener((ObservableValue<? extends Window> observable1, Window oldValue1, Window newValue1) -> {
                    stage = (Stage) newValue1;
                });
                if (apPrincipal.getUserData() instanceof Usuario) {
                    pfSenha.setPromptText("Para manter sua senha deixe este campo vazio");
                    usuario = (Usuario) apPrincipal.getUserData();
                    usuario = UsuarioDAO.getInstance().pegarAtuacoes(usuario);
                    carregarDados();
                }
            }
        });
        lvAtuacao.getItems().setAll(AtuacaoDAO.getInstance().pegarTodos());
        lvAtuacao.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvAtuando.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void btCancelarActionEvent(ActionEvent ae) {
        stage.close();
    }

    @FXML
    private void btConfirmarActionEvent(ActionEvent ae) {
        //Cadastrar usuario
        if (usuario.getPessoa() == null) {
            usuario.setPessoa(new Pessoa());
        }
        usuario.getPessoa().setCpf(mtfCPF.getText());
        usuario.getPessoa().setEmail(tfEmail.getText());
        usuario.getPessoa().setNome(tfNome.getText());
        usuario.setPerfil(cbPerfil.getSelectionModel().getSelectedItem());
        usuario.setSenha(pfSenha.getText());
        List<Atuando> atuandoLista = new ArrayList<>();
        for (Atuacao atuacao : lvAtuando.getItems()) {
            Atuando atuando = new Atuando();
            atuando.getId().setAtuacao(atuacao);
            atuando.getId().setUsuario(usuario);
            atuandoLista.add(atuando);
        }
        usuario.setAtuando(atuandoLista);
        if (StringUtil.isEmpty(usuario.getPessoa().getCpf().replaceAll("[.-]", "").trim())
                || StringUtil.isEmpty(usuario.getPessoa().getEmail())
                || StringUtil.isEmpty(usuario.getPessoa().getNome())
                || usuario.getPerfil() == null
                || atuandoLista.isEmpty()) {
            MessageUtil.messageError(MessageUtil.CAMPOS_OBRIGATORIOS);
        } else if (usuario.getId() == null) {
            //Para novos usuários validar se a senha foi digitada
            if (StringUtil.isEmpty(pfSenha.getText())) {
                MessageUtil.messageError("É necessário digitar a senha para um novo usuário.");
                return;
            }
            if (!pfSenha.getText().equals(pfConfirmarSenha.getText())) {
                MessageUtil.messageError("Sua confirmação de senha deve ser igual a sua senha.");
                return;
            }
            usuario.setSenha(SecurityUtil.encript(pfSenha.getText()));
            usuario.setAtivado(true);
            UsuarioDAO.getInstance().salvar(usuario);
            MessageUtil.messageInformation("Usuario foi cadastrado com sucesso!");
            stage.close();
        } else {
            if (!StringUtil.isEmpty(pfSenha.getText())) {
                if (!pfSenha.getText().equals(pfConfirmarSenha.getText())) {
                    MessageUtil.messageError("Sua confirmação de senha deve ser igual a sua senha.");
                    return;
                }
                usuario.setSenha(pfSenha.getText());
            }
            UsuarioDAO.getInstance().editar(usuario);
            MessageUtil.messageInformation("Usuário foi editado com sucesso!");
            stage.close();
        }
    }

    @FXML
    private void btAdicionarActionEvent(ActionEvent ae) {
        if (!lvAtuacao.getSelectionModel().getSelectedItems().isEmpty()) {
            lvAtuando.getItems().addAll(lvAtuacao.getSelectionModel().getSelectedItems());
            lvAtuacao.getItems().removeAll(lvAtuacao.getSelectionModel().getSelectedItems());
        }
    }

    @FXML
    private void btAdicionarTodosActionEvent(ActionEvent ae) {
        lvAtuando.getItems().addAll(lvAtuacao.getItems().stream().collect(Collectors.toList()));
        lvAtuacao.getItems().clear();
    }

    @FXML
    private void btRemoverTodosActionEvent(ActionEvent ae) {
        lvAtuacao.getItems().addAll(lvAtuando.getItems().stream().collect(Collectors.toList()));
        lvAtuando.getItems().clear();
    }

    @FXML
    private void btRemoverActionEvent(ActionEvent ae) {
        if (!lvAtuando.getSelectionModel().getSelectedItems().isEmpty()) {
            lvAtuacao.getItems().addAll(lvAtuando.getSelectionModel().getSelectedItems());
            lvAtuando.getItems().removeAll(lvAtuando.getSelectionModel().getSelectedItems());
        }
    }

    private void carregarDados() {
        if (usuario.getPessoa() != null) {
            tfNome.setText(usuario.getPessoa().getNome());
            mtfCPF.setText(usuario.getPessoa().getCpf());
            tfEmail.setText(usuario.getPessoa().getEmail());
            cbPerfil.getSelectionModel().select(usuario.getPerfil());
            
            lvAtuacao.getItems().setAll(AtuacaoDAO.getInstance().pegarTodos());
            lvAtuacao.getItems().removeAll(usuario.getAtuando().stream().map(Atuando::getId).map(AtuadoID::getAtuacao).collect(Collectors.toList()));
            lvAtuando.getItems().setAll(usuario.getAtuando().stream().map(Atuando::getId).map(AtuadoID::getAtuacao).collect(Collectors.toList()));
        } else {
            tfNome.setText("");
        }

    }
    
    public void teste() {
        params = (Map<String, Object>) apPrincipal.getUserData();
        usuario = (Usuario) params.get("Item");
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        stage = (Stage) params.get("modalStage");
        carregarDados();
        params.put("Item", new Usuario());
    }
}
