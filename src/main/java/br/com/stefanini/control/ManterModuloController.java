/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ModuloDAO;
import br.com.stefanini.control.dao.ProjetoDAO;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.Projeto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
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
public class ManterModuloController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;

    @FXML
    private ComboBox<Projeto> cbProjeto;

    @FXML
    private TextField tfDescricao;
    @FXML
    private TableView<Modulo> tvModulo;
    @FXML
    private TableColumn<Modulo, Projeto> tcProjeto;
    @FXML
    private TableColumn<Modulo, String> tcDescricao;

    private Modulo modulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbProjeto.getItems().setAll(new ProjetoDAO().pegarTodos());
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcProjeto.setCellValueFactory(new PropertyValueFactory<>("projeto"));
        tcProjeto.setCellFactory((TableColumn<Modulo, Projeto> param) -> new TableCell<Modulo, Projeto>() {
            @Override
            protected void updateItem(Projeto item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                    setText("");
                } else {
                    setText(item.toString());
                }
            }

        });
        btNovoActionEvent(null);
    }

    @FXML
    private void btNovoActionEvent(ActionEvent ae) {
        modulo = new Modulo();
        carregarDados();

    }

    @FXML
    private void btSalvarActionEvent(ActionEvent ae) {
        modulo.setDescricao(tfDescricao.getText());
        modulo.setProjeto(cbProjeto.getValue());
        if (modulo.getId() == null) {
            new ModuloDAO().salvar(modulo);
            new Alert(Alert.AlertType.INFORMATION, "Modulo cadastro com sucesso.").show();
            atualizarTabelas();
        } else {
            new ModuloDAO().editar(modulo);
            new Alert(Alert.AlertType.INFORMATION, "Modulo editado com sucesso.").show();
            atualizarTabelas();
        }
    }

    private void carregarDados() {
        cbProjeto.getSelectionModel().select(modulo.getProjeto());
        tfDescricao.setText(modulo.getDescricao());

    }

    private void atualizarTabelas() {
        btNovoActionEvent(null);
        tvModulo.getItems().setAll(new ModuloDAO().pegarTodos());
    }

    @FXML
    private void miEditarActionEvent(ActionEvent ae) {

    }

    @FXML
    private void miExcluirActionEvent(ActionEvent ae) {

    }
}
