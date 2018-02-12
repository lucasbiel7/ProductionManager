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
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class ManterPacoteController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfDescricao;
    @FXML
    private ComboBox<Projeto> cbProjeto;
    @FXML
    private ComboBox<Modulo> cbModulo;
    @FXML
    private TableView<Pacote> tvPacote;
    @FXML
    private TableColumn<Pacote, String> tcDescricao;
    @FXML
    private TableColumn<Pacote, Modulo> tcModulo;

    private Pacote pacote;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcModulo.setCellValueFactory(new PropertyValueFactory<>("modulo"));
        cbProjeto.getItems().addAll(new ProjetoDAO().pegarTodos());
        btNovoActionEvent(null);
    }

    @FXML
    private void cbProjetoActionEvent(ActionEvent ae) {
        if (cbProjeto.getValue() != null) {
            cbModulo.getItems().setAll(new ModuloDAO().pegarPorProjeto(cbProjeto.getValue()));
        } else {
            cbModulo.getItems().clear();
        }
    }

    @FXML
    private void btNovoActionEvent(ActionEvent ae) {
        pacote = new Pacote();
        carregarDados();
        tvPacote.getItems().setAll(new PacoteDAO().pegarTodos());
    }

    @FXML
    private void btConfirmarActionEvent(ActionEvent ae) {
        pacote.setDescricao(tfDescricao.getText());
        pacote.setModulo(cbModulo.getValue());
        if (StringUtil.isEmpty(pacote.getDescricao()) || pacote.getModulo() == null) {
            MessageUtil.messageError(MessageUtil.CAMPOS_OBRIGATORIOS);
            return;
        }
        if (pacote.getId() == null) {
            new PacoteDAO().salvar(pacote);
            MessageUtil.messageInformation("Um novo pacote foi cadastrado com sucesso!");
        } else {
            new PacoteDAO().editar(pacote);
            MessageUtil.messageInformation("O pacote foi editado com sucwsso!");
        }
        btNovoActionEvent(ae);
    }

    @FXML
    private void miEditarActionEvent(ActionEvent ae) {
        pacote = tvPacote.getSelectionModel().getSelectedItem();
        carregarDados();
    }

    @FXML
    private void miExcluirActionEvent(ActionEvent ae) {
        if (MessageUtil.confirmMessage("Você realmente deseja excluir este pacote?")) {
            pacote = tvPacote.getSelectionModel().getSelectedItem();
            new PacoteDAO().excluir(pacote);
            btNovoActionEvent(ae);
        }
    }

    private void carregarDados() {
        tfDescricao.setText(pacote.getDescricao());
        if (pacote.getModulo() != null) {
            cbProjeto.getSelectionModel().select(pacote.getModulo().getProjeto());
        } else {
            cbProjeto.getSelectionModel().select(null);
        }
        cbModulo.getSelectionModel().select(pacote.getModulo());
    }
}
