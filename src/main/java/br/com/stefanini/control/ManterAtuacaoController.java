/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.AtuacaoDAO;
import br.com.stefanini.model.entity.Atuacao;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class ManterAtuacaoController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfDescricao;
    @FXML
    private TableView<Atuacao> tvAtuacao;
    @FXML
    private TableColumn<Atuacao, String> tcDescricao;

    private Atuacao atuacao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        btNovoActionEvent(null);
    }

    @FXML
    private void btNovoActionEvent(ActionEvent ae) {
        atuacao = new Atuacao();
        carregarDados();
        tvAtuacao.getItems().setAll(new AtuacaoDAO().pegarTodos());
    }

    @FXML
    private void btConfirmarActionEvent(ActionEvent ae) {
        atuacao.setDescricao(tfDescricao.getText());
        if (StringUtil.isEmpty(atuacao.getDescricao())) {
            MessageUtil.messageError(MessageUtil.CAMPOS_OBRIGATORIOS);
        } else if (atuacao.getId() == null) {
            new AtuacaoDAO().salvar(atuacao);
            MessageUtil.messageInformation("Nova atuação foi cadastrado com sucesso!");
            btNovoActionEvent(ae);
        } else {
            new AtuacaoDAO().editar(atuacao);
            MessageUtil.messageInformation("Atuação foi editado com sucesso!");
            btNovoActionEvent(ae);
        }
    }

    @FXML
    private void miEditarActionEvent(ActionEvent ae) {
        atuacao = tvAtuacao.getSelectionModel().getSelectedItem();
        carregarDados();
    }

    @FXML
    private void miExcluirActionEvent(ActionEvent ae) {
        if (MessageUtil.confirmMessage("Você realmente deseja excluir essa atuação?")) {
            atuacao = tvAtuacao.getSelectionModel().getSelectedItem();
            new AtuacaoDAO().excluir(atuacao);
            btNovoActionEvent(ae);
        }
    }

    private void carregarDados() {
        tfDescricao.setText(atuacao.getDescricao());
    }
}
