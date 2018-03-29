/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ModificacaoAtividadeDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.ModificacaoAtividade;
import br.com.stefanini.model.enuns.Mes;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class AlterarEscopoAtividadeController implements Initializable {

    @FXML
    private ComboBox<Mes> cbMes;
    @FXML
    private Spinner<Integer> spAno;
    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbProjeto;
    @FXML
    private Label lbPacote;
    @FXML
    private Label lbModulo;
    @FXML
    private Label lbAtividade;

    @FXML
    private RadioButton rbLevantamento;

    @FXML
    private RadioButton rbDesenvolvimento;
    @FXML
    private RadioButton rbTesteHomologacao;
    @FXML
    private TextArea taDescricao;

    private Stage stage;

    private Atividade atividade;

    Map<String, Object> params = new HashMap<>();

    private GerenciadorDeJanela gerenciadorDeJanela;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        apPrincipal.sceneProperty().addListener((ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) -> {
            if (newValue != null) {
                newValue.windowProperty().addListener((ObservableValue<? extends Window> observable1, Window oldValue1, Window newWindow) -> {
                    stage = (Stage) newWindow;
                });
                params = (Map) apPrincipal.getUserData();
                load();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(atividade.getPrevisaoInicio());
                ((SpinnerValueFactory.IntegerSpinnerValueFactory) spAno.getValueFactory()).setMin(calendar.get(Calendar.YEAR));
            }
        });
        cbMes.getItems().setAll(Mes.values());
        Calendar calendar = Calendar.getInstance();
        spAno.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(calendar.get(Calendar.YEAR), Integer.MAX_VALUE, calendar.get(Calendar.YEAR)));
    }

    @FXML
    private void btConfirmarActionEvent(ActionEvent ae) {
        if (StringUtil.isEmpty(taDescricao.getText())) {
            MessageUtil.messageError(MessageUtil.CAMPOS_OBRIGATORIOS);
            return;
        }
        ModificacaoAtividade modificacaoAtividade = new ModificacaoAtividade(atividade);
        modificacaoAtividade.setTipoAtividade(rbLevantamento.isSelected() ? TipoAtividade.LE : rbDesenvolvimento.isSelected() ? TipoAtividade.DE : TipoAtividade.TE);
        modificacaoAtividade.setDescricaoModificacao(taDescricao.getText());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, spAno.getValue());
        calendar.set(Calendar.MONTH, cbMes.getSelectionModel().getSelectedIndex());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        modificacaoAtividade.setPrevisaoInicio(calendar.getTime());
        modificacaoAtividade.setDataModificacao(new Date());
        modificacaoAtividade.setTpAtividade(TipoAtividade.A);
        ModificacaoAtividadeDAO.getInstance().salvar(modificacaoAtividade);
        MessageUtil.messageInformation("Foi adicionado uma alteração de escopo!");
        stage.close();
    }

    @FXML
    private void btCancelarActionEvent(ActionEvent ae) {
        stage.close();
    }

    private void faseButton(RadioButton radioButton, TipoAtividade tipoAtividade) {
        radioButton.setText(tipoAtividade.toString());
    }

    private void load() {
        faseButton(rbDesenvolvimento, TipoAtividade.DE);
        faseButton(rbLevantamento, TipoAtividade.LE);
        faseButton(rbTesteHomologacao, TipoAtividade.TE);
        lbProjeto.setText(atividade.getPacote().getModulo().getProjeto().getDescricao());
        lbAtividade.setText(atividade.getDescricao());
        lbModulo.setText(atividade.getPacote().getModulo().getDescricao());
        lbPacote.setText(atividade.getPacote().getDescricao());
    }

    public void teste() {
        params = (Map<String, Object>) apPrincipal.getUserData();
        atividade = (Atividade) params.get("Atividade");
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        stage = (Stage) params.get("modalStage");
        load();
        params.put("Atividade", new Atividade());
    }
}
