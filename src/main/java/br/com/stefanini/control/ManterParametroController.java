/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ParametroDAO;
import br.com.stefanini.model.entity.Parametro;
import br.com.stefanini.model.enuns.TipoParametro;
import br.com.stefanini.model.util.DoubleConverter;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.SpinnerTextToValue;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author higo
 */
public class ManterParametroController implements Initializable {

    @FXML
    private ComboBox<TipoParametro> tpParametro;

    @FXML
    private Spinner<Double> idValor;

    @FXML
    private TableView<Parametro> gridParametro;

    @FXML
    private TableColumn<Parametro, TipoParametro> colunaParametro;

    @FXML
    private TableColumn<Parametro, Long> colunaValor;

    private Parametro parametro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizarTabelas();
        tpParametro.getItems().setAll(TipoParametro.values());
        colunaParametro.setCellValueFactory(new PropertyValueFactory<>("tipoParametro"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        idValor.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999.99, 0));
        idValor.getValueFactory().setConverter(DoubleConverter.getInstance());
        SpinnerTextToValue.configure(idValor);
    }

    @FXML
    private void salvarParametro(ActionEvent ae) {
        parametro = new Parametro();
        if (idValor.getValue() == null) {
            parametro.setValor(null);
        } else {
            parametro.setValor(idValor.getValue());
        }
        parametro.setTipoParametro(tpParametro.getValue());
        parametro.setDtInclusao(new Date());
        if (parametro.getTipoParametro() == null || parametro.getValor() == null) {
            MessageUtil.messageError("É necessário preencher todos campos obrigatórios!");
        } else {
            new ParametroDAO().salvar(parametro);
            new Alert(Alert.AlertType.INFORMATION, "Parâmetro cadastro com sucesso.").show();
            atualizarTabelas();
        }
    }

    @FXML
    private void novoParametro() {
        parametro = new Parametro();
        idValor.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9999999999.9, 0));
        tpParametro.setValue(parametro.getTipoParametro());
    }

    private void atualizarTabelas() {
        gridParametro.getItems().setAll(new ParametroDAO().buscaParametrosRecentes());
    }
}
