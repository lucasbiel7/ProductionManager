/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.PerfilDAO;
import br.com.stefanini.model.entity.Perfil;
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
public class ManterPerfilController implements Initializable {

    private Perfil perfil;

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfDescricao;
    @FXML
    private TableView<Perfil> tvPerfil;
    @FXML
    private TableColumn<Perfil, String> tcDescricao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tvPerfil.getItems().setAll(PerfilDAO.getInstance().pegarTodos());
        btNovoActionEvent(null);
    }

    @FXML
    private void btSalvarActionEvent(ActionEvent ae) {
        if (!StringUtil.isEmpty(tfDescricao.getText())) {
            perfil.setDescricao(tfDescricao.getText());
            if (perfil.getId() != null) {
                PerfilDAO.getInstance().editar(perfil);
                MessageUtil.messageInformation("Perfil editado com sucesso!");
            } else {
                PerfilDAO.getInstance().salvar(perfil);
                MessageUtil.messageInformation("Perfil cadastrado com sucesso!");
            }
            btNovoActionEvent(ae);
        } else {
            MessageUtil.messageError(MessageUtil.CAMPOS_OBRIGATORIOS);
        }
        tvPerfil.getItems().setAll(PerfilDAO.getInstance().pegarTodos());
    }

    @FXML
    private void btNovoActionEvent(ActionEvent ae) {
        perfil = new Perfil();
        carregarDados();
    }

    private void carregarDados() {
        tfDescricao.setText(perfil.getDescricao());
    }

    @FXML
    private void miEditarActionEvent(ActionEvent ae) {
        perfil = tvPerfil.getSelectionModel().getSelectedItem();
        carregarDados();
    }

    @FXML
    private void miExcluirActionEvent(ActionEvent ae) {
        if (MessageUtil.confirmMessage("Você realmente deseja excluir este perfil?")) {
            perfil = tvPerfil.getSelectionModel().getSelectedItem();
            PerfilDAO.getInstance().excluir(perfil);
            tvPerfil.getItems().setAll(PerfilDAO.getInstance().pegarTodos());
            btNovoActionEvent(ae);
        }
    }
}
