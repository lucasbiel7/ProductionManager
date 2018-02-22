/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ModificacaoAtividadeDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.ModificacaoAtividade;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class AlterarEscopoAtividadeController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
            stage = (Stage) apPrincipal.getScene().getWindow();
            atividade = (Atividade) apPrincipal.getUserData();
            faseButton(rbDesenvolvimento, TipoAtividade.DE);
            faseButton(rbLevantamento, TipoAtividade.LE);
            faseButton(rbTesteHomologacao, TipoAtividade.TE);
            lbProjeto.setText(atividade.getPacote().getModulo().getProjeto().getDescricao());
            lbAtividade.setText(atividade.getDescricao());
            lbModulo.setText(atividade.getPacote().getModulo().getDescricao());
            lbPacote.setText(atividade.getPacote().getDescricao());
        });
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
        modificacaoAtividade.setDataModificacao(new Date());
        new ModificacaoAtividadeDAO().salvar(modificacaoAtividade);
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
}