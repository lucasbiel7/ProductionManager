/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ProjetoDAO;
import br.com.stefanini.model.entity.Projeto;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class ManterProjetosController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfDescricao;
    @FXML
    private TableView<Projeto> tvProjeto;
    @FXML
    private TableColumn<Projeto, String> tcDescricao;

    private Projeto projeto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        atualizarTabela();
    }

    @FXML
    private void btNovoActionEvent(ActionEvent ae) {
        projeto = new Projeto();
        carregarDados();
    }

    @FXML
    private void btSalvarActionEvent(ActionEvent ae) {
        projeto.setDescricao(tfDescricao.getText());
        if (StringUtil.isEmpty(projeto.getDescricao())) {
            MessageUtil.messageError("É necessário preencher todos os campos obrigatórios!");
        } else if (projeto.getId() == null) {
            ProjetoDAO.getInstance().salvar(projeto);
            new Alert(Alert.AlertType.INFORMATION, "Projeto cadastrado com sucesso.").show();
            atualizarTabela();
        } else {
            ProjetoDAO.getInstance().editar(projeto);
            new Alert(Alert.AlertType.INFORMATION, "Projeto editado com sucesso.").show();
            atualizarTabela();
        }
    }

    private void carregarDados() {
        tfDescricao.setText(projeto.getDescricao());
    }

    @FXML
    private void miEditarActionEvent(ActionEvent ae) {
        if (tvProjeto.getSelectionModel().getSelectedItem() != null) {
            projeto = tvProjeto.getSelectionModel().getSelectedItem();
            carregarDados();
        } else {
            new Alert(Alert.AlertType.ERROR, "Você deve selecionar um projeto primeiro.").show();
        }
    }

    @FXML
    private void miExcluirActionEvent(ActionEvent ae) {
        if (tvProjeto.getSelectionModel().getSelectedItem() != null) {
            if (new Alert(Alert.AlertType.CONFIRMATION, "Você realmente deseja excluir o projeto?", ButtonType.YES, ButtonType.NO).showAndWait().orElse(ButtonType.NO).equals(ButtonType.YES)) {
                ProjetoDAO.getInstance().excluir(tvProjeto.getSelectionModel().getSelectedItem());
                atualizarTabela();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Você deve selecionar um projeto primeiro.").show();
        }
    }

    private void atualizarTabela() {
        projeto = new Projeto();
        carregarDados();
        tvProjeto.getItems().setAll(ProjetoDAO.getInstance().pegarTodos());
    }
}
