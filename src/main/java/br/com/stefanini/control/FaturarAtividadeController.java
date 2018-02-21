/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ProgressoAtividadeDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.ProgressoAtividade;
import br.com.stefanini.model.enuns.Faturamento;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.util.MessageUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class FaturarAtividadeController implements Initializable {

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
    private CheckBox cbAnalise;

    @FXML
    private CheckBox cbDesenvolvimento;

    @FXML
    private CheckBox cbTesteHomologacao;

    private Stage stage;

    private Atividade atividade;

    private ProgressoAtividade paAnalise;
    private ProgressoAtividade paDesenvolvimento;
    private ProgressoAtividade paTesteHomologacao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            stage = (Stage) apPrincipal.getScene().getWindow();
            atividade = (Atividade) apPrincipal.getUserData();
            paAnalise = new ProgressoAtividadeDAO().pegarPorAtividadeTipo(atividade, TipoAtividade.LE).stream().findFirst().orElse(null);
            paDesenvolvimento = new ProgressoAtividadeDAO().pegarPorAtividadeTipo(atividade, TipoAtividade.DE).stream().findFirst().orElse(null);
            paTesteHomologacao = new ProgressoAtividadeDAO().pegarPorAtividadeTipo(atividade, TipoAtividade.TE).stream().findFirst().orElse(null);
            configurarCheckBox(cbAnalise, paAnalise, TipoAtividade.LE);
            configurarCheckBox(cbDesenvolvimento, paDesenvolvimento, TipoAtividade.DE);
            configurarCheckBox(cbTesteHomologacao, paTesteHomologacao, TipoAtividade.TE);
            cbAnalise.setSelected(paAnalise.getFaturamento() == Faturamento.FO);
            lbProjeto.setText(atividade.getPacote().getModulo().getProjeto().getDescricao());
            lbAtividade.setText(atividade.getDescricao());
            lbModulo.setText(atividade.getPacote().getModulo().getDescricao());
            lbPacote.setText(atividade.getPacote().getDescricao());
        });
    }

    @FXML
    private void btCancelarActionEvent(ActionEvent ae) {
        stage.close();
    }

    @FXML
    private void btConfirmarActionEvent(ActionEvent ae) {
        //Enviando para faturamento todas as fases do sistema
        enviarParaFaturamento(cbAnalise, paAnalise);
        enviarParaFaturamento(cbDesenvolvimento, paDesenvolvimento);
        enviarParaFaturamento(cbTesteHomologacao, paTesteHomologacao);
        stage.close();
    }

    public void configurarCheckBox(CheckBox checkBox, ProgressoAtividade progressoAtividade, TipoAtividade tipoAtividade) {
        if (progressoAtividade == null) {
            checkBox.setSelected(false);
            checkBox.setDisable(true);
            checkBox.setText(tipoAtividade.toString());
        } else {
            checkBox.setText(tipoAtividade + " " + progressoAtividade.getProgresso() + "%");
            checkBox.setSelected(progressoAtividade.getFaturamento() == Faturamento.FO);
            checkBox.setDisable(progressoAtividade.getFaturamento() != Faturamento.AF || progressoAtividade.getProgresso() != 100d);
        }
    }

    private void enviarParaFaturamento(CheckBox checkBox, ProgressoAtividade progressoAtividade) {
        if (checkBox.isSelected()) {
            progressoAtividade.setFaturamento(Faturamento.EF);
            new ProgressoAtividadeDAO().editar(progressoAtividade);
            MessageUtil.messageInformation("A fase de " + progressoAtividade.getTipoAtividade() + " foi enviado para faturamento!");
        }
    }

}
