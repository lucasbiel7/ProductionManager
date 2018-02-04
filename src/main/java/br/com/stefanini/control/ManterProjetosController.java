/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ProjetoDAO;
import br.com.stefanini.model.entity.Projeto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
        projeto = new Projeto();
        carregarDados();
    }

    @FXML
    private void btNovoActionEvent(ActionEvent ae) {
        projeto = new Projeto();
        carregarDados();
    }

    @FXML
    private void btSalvarActionEvent(ActionEvent ae) {
        projeto.setDescricao(tfDescricao.getText());
        if (projeto.getId() == null) {
            new ProjetoDAO().cadastrar(projeto);
            new Alert(Alert.AlertType.INFORMATION, "Projeto cadastrado com sucesso.").show();
        } else {
            new ProjetoDAO().editar(projeto);
            new Alert(Alert.AlertType.INFORMATION, "Projeto editado com sucesso.").show();
        }
    }

    private void carregarDados() {
        tfDescricao.setText(projeto.getDescricao());
    }
}
