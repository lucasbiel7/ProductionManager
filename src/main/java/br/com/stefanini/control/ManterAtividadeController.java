/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.AtividadeDAO;
import br.com.stefanini.control.dao.ModuloDAO;
import br.com.stefanini.control.dao.PacoteDAO;
import br.com.stefanini.control.dao.ProjetoDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.OrdemServico;
import br.com.stefanini.model.entity.Pacote;
import br.com.stefanini.model.entity.Projeto;
import br.com.stefanini.model.enuns.Artefato;
import br.com.stefanini.model.enuns.SituacaoAtividade;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class ManterAtividadeController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private ComboBox<Projeto> cbProjeto;
    @FXML
    private ComboBox<Modulo> cbModulo;
    @FXML
    private ComboBox<Pacote> cbPacote;
    @FXML
    private TextField tfAtividade;
    @FXML
    private ComboBox<OrdemServico> cbOrdemServico;
    @FXML
    private Spinner<Integer> spEstimada;
    @FXML
    private Spinner<Integer> spDetalhada;
    @FXML
    private TextField tfMes;
    @FXML
    private ListView<Artefato> lvArtefatosDisponiveis;
    @FXML
    private ListView<Artefato> lvArtefatosSelecionados;
    @FXML
    private DatePicker dpInicioLevantamento;
    @FXML
    private DatePicker dpFimLevantamento;
    @FXML
    private DatePicker dpInicioDesenvolvimento;
    @FXML
    private DatePicker dpFimDesenvolvimento;
    @FXML
    private DatePicker dpInicioTeste;
    @FXML
    private DatePicker dpFimTeste;

    private Atividade atividade;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            if (apPrincipal.getUserData() instanceof Atividade) {
                ManterAtividadeController.this.atividade = (Atividade) apPrincipal.getUserData();
                carregarDados();
            } else {
                ManterAtividadeController.this.atividade = new Atividade();
            }
            stage = (Stage) apPrincipal.getScene().getWindow();
        });
        cbProjeto.getItems().setAll(new ProjetoDAO().pegarTodos());
        lvArtefatosDisponiveis.getItems().setAll(Artefato.values());
        lvArtefatosDisponiveis.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvArtefatosSelecionados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void cbProjetoActionEvent(ActionEvent ae) {
        if (cbProjeto.getValue() != null) {
            cbModulo.getItems().setAll(new ModuloDAO().pegarPorProjeto(cbProjeto.getValue()));
        } else {
            cbModulo.getItems().clear();
        }
    }

    @FXML
    private void cbModuloActionEvent(ActionEvent ae) {
        if (cbModulo.getValue() != null) {
            cbPacote.getItems().setAll(new PacoteDAO().pegarPorModulo(cbModulo.getValue()));
        } else {
            cbPacote.getItems().clear();
        }
    }

    @FXML
    private void btAdicionarActionEvent(ActionEvent ae) {
        if (!lvArtefatosDisponiveis.getSelectionModel().getSelectedItems().isEmpty()) {
            lvArtefatosSelecionados.getItems().addAll(lvArtefatosDisponiveis.getSelectionModel().getSelectedItems());
            lvArtefatosDisponiveis.getItems().removeAll(lvArtefatosDisponiveis.getSelectionModel().getSelectedItems());
        }
    }

    @FXML
    private void btAdicionarTodosActionEvent(ActionEvent ae) {
        lvArtefatosSelecionados.getItems().addAll(lvArtefatosDisponiveis.getItems());
        lvArtefatosDisponiveis.getItems().clear();
    }

    @FXML
    private void btRemoverTodosActionEvent(ActionEvent ae) {
        lvArtefatosDisponiveis.getItems().addAll(lvArtefatosSelecionados.getItems());
        lvArtefatosSelecionados.getItems().clear();
    }

    @FXML
    private void btRemoverActionEvent(ActionEvent ae) {
        if (!lvArtefatosSelecionados.getSelectionModel().getSelectedItems().isEmpty()) {
            lvArtefatosDisponiveis.getItems().addAll(lvArtefatosSelecionados.getSelectionModel().getSelectedItems());
            lvArtefatosSelecionados.getItems().removeAll(lvArtefatosSelecionados.getSelectionModel().getSelectedItems());
        }
    }

    @FXML
    private void btCancelarActionEvent(ActionEvent ae) {
        stage.close();
    }

    @FXML
    private void btConfirmarActionEvent(ActionEvent ae) {
        atividade.setDescricao(tfAtividade.getText());
        atividade.setContagemDetalhada(spDetalhada.getValue());
        atividade.setContagemEstimada(spEstimada.getValue());
        atividade.setOrdemServico(cbOrdemServico.getValue());
        atividade.setPacote(cbPacote.getValue());
        atividade.setSituacaoAtividade(SituacaoAtividade.L);
        if (StringUtil.isEmpty(atividade.getDescricao())
                || atividade.getOrdemServico() == null
                || atividade.getPacote() == null) {
            MessageUtil.messageError(MessageUtil.CAMPOS_OBRIGATORIOS);
        } else if (atividade.getId() == null) {
            new AtividadeDAO().salvar(atividade);
            MessageUtil.messageInformation("Atividade foi cadastrada com sucesso!");
            stage.close();
        } else {
            new AtividadeDAO().editar(atividade);
            MessageUtil.messageInformation("Atividade foi editada com sucesso!");
            stage.close();
        }
    }

    public void carregarDados() {

    }
}
