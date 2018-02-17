/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ModuloDAO;
import br.com.stefanini.control.dao.ParametroDAO;
import br.com.stefanini.control.dao.ProjetoDAO;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.Parametro;
import br.com.stefanini.model.entity.Projeto;
import br.com.stefanini.model.enuns.TipoParametro;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author higo
 */
public class ManterParametroController implements Initializable {
    
    @FXML
    private ComboBox<TipoParametro> tpParametro;
    
    @FXML
    private TextField idValor;
    
    @FXML
    private TableView<Parametro> gridParametro;
    
    private Parametro parametro; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tpParametro.getItems().setAll(TipoParametro.values());
    }
    
    @FXML
    private void salvarParametro(ActionEvent ae) {
        parametro.setTipoParametro(tpParametro.getValue());
        parametro.setValor(Long.parseLong(idValor.getText()));
        if(parametro.getTipoParametro() == null || parametro.getValor() == null){
            MessageUtil.messageError("É necessário preencher todos campos obrigatórios!");
        }else{
            new ParametroDAO().salvar(parametro);
            new Alert(Alert.AlertType.INFORMATION, "Parâmetro cadastro com sucesso.").show();
            atualizarTabelas();
        }
    }
        
    private void atualizarTabelas() {
        gridParametro.getItems().setAll(new ParametroDAO().pegarTodos());
    }
}

