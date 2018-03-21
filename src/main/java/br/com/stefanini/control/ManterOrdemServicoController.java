/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.OrdemServicoDAO;
import br.com.stefanini.model.entity.OrdemServico;
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
public class ManterOrdemServicoController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfDescricao;
    @FXML
    private TableView<OrdemServico> tvOrdemServico;
    @FXML
    private TableColumn<OrdemServico, String> tcDescricao;

    private OrdemServico ordemServico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btNovoActionEvent(null);
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    }

    @FXML
    private void btNovoActionEvent(ActionEvent ae) {
        ordemServico = new OrdemServico();
        tvOrdemServico.getItems().setAll(OrdemServicoDAO.getInstance().pegarTodos());
        carregarDados();
    }

    @FXML
    private void btConfirmarActionEvent(ActionEvent ae) {
        ordemServico.setDescricao(tfDescricao.getText());
        if (StringUtil.isEmpty(ordemServico.getDescricao())) {
            MessageUtil.messageError(MessageUtil.CAMPOS_OBRIGATORIOS);
        } else if (ordemServico.getId() == null) {
            OrdemServicoDAO.getInstance().salvar(ordemServico);
            MessageUtil.messageInformation("Uma nova ordem de serviço foi adicionada com sucesso!");
            btNovoActionEvent(ae);
        } else {
            OrdemServicoDAO.getInstance().editar(ordemServico);
            MessageUtil.messageInformation("Uma ordem de serviço foi alterada com sucesso!");
            btNovoActionEvent(ae);
        }
    }

    @FXML
    private void miEditarActionEvent(ActionEvent ae) {
        if (tvOrdemServico.getSelectionModel().getSelectedItem() != null) {
            ordemServico = tvOrdemServico.getSelectionModel().getSelectedItem();
            carregarDados();
        }
    }

    @FXML
    private void miExcluirActionEvent(ActionEvent ae) {
        if (tvOrdemServico.getSelectionModel().getSelectedItem() != null) {
            if (MessageUtil.confirmMessage("Você realmente deseja excluir está ordem de serviço?")) {
                ordemServico = tvOrdemServico.getSelectionModel().getSelectedItem();
                OrdemServicoDAO.getInstance().excluir(ordemServico);
                btNovoActionEvent(ae);
            }
        }
    }

    private void carregarDados() {
        tfDescricao.setText(ordemServico.getDescricao());
    }
}
