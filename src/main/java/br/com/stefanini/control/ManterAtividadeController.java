/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.AtividadeDAO;
import br.com.stefanini.control.dao.ModuloDAO;
import br.com.stefanini.control.dao.OrdemServicoDAO;
import br.com.stefanini.control.dao.PacoteDAO;
import br.com.stefanini.control.dao.ProgressoAtividadeDAO;
import br.com.stefanini.control.dao.ProjetoDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.OrdemServico;
import br.com.stefanini.model.entity.Pacote;
import br.com.stefanini.model.entity.ProgressoAtividade;
import br.com.stefanini.model.entity.Projeto;
import br.com.stefanini.model.enuns.Artefato;
import br.com.stefanini.model.enuns.Faturamento;
import br.com.stefanini.model.enuns.Mes;
import br.com.stefanini.model.enuns.SituacaoAtividade;
import br.com.stefanini.model.util.DateUtil;
import br.com.stefanini.model.util.DoubleConverter;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.Calendar;
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
import javafx.scene.control.SpinnerValueFactory;
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
    private ComboBox<Mes> cbMes;
    @FXML
    private Spinner<Double> spEstimada;
    @FXML
    private Spinner<Double> spDetalhada;
    @FXML
    private ListView<Artefato> lvArtefatosDisponiveis;
    @FXML
    private ListView<Artefato> lvArtefatosSelecionados;
    @FXML
    private ListView<Artefato> lvArtefatosDisponiveisDev;
    @FXML
    private ListView<Artefato> lvArtefatosSelecionadosDev;
    @FXML
    private ListView<Artefato> lvArtefatosDisponiveisTeste;
    @FXML
    private ListView<Artefato> lvArtefatosSelecionadosTeste;
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

        spEstimada.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9999999999.9, 0));
        spDetalhada.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9999999999.9, 0));
        spEstimada.getValueFactory().setConverter(new DoubleConverter());
        spDetalhada.getValueFactory().setConverter(new DoubleConverter());

        cbProjeto.getItems().setAll(new ProjetoDAO().pegarTodos());
        cbOrdemServico.getItems().setAll(new OrdemServicoDAO().pegarTodos());
        cbMes.getItems().setAll(Mes.values());
        lvArtefatosDisponiveis.getItems().setAll(Artefato.values());
        lvArtefatosDisponiveis.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvArtefatosSelecionados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvArtefatosDisponiveisDev.getItems().setAll(Artefato.values());
        lvArtefatosDisponiveisDev.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvArtefatosSelecionadosDev.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvArtefatosDisponiveisTeste.getItems().setAll(Artefato.values());
        lvArtefatosDisponiveisTeste.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvArtefatosSelecionadosTeste.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
    private void btAdicionarDevActionEvent(ActionEvent ae) {
        if (!lvArtefatosDisponiveisDev.getSelectionModel().getSelectedItems().isEmpty()) {
            lvArtefatosSelecionadosDev.getItems().addAll(lvArtefatosDisponiveisDev.getSelectionModel().getSelectedItems());
            lvArtefatosDisponiveisDev.getItems().removeAll(lvArtefatosDisponiveisDev.getSelectionModel().getSelectedItems());
        }
    }

    @FXML
    private void btAdicionarTodosDevActionEvent(ActionEvent ae) {
        lvArtefatosSelecionadosDev.getItems().addAll(lvArtefatosDisponiveisDev.getItems());
        lvArtefatosDisponiveisDev.getItems().clear();
    }

    @FXML
    private void btRemoverTodosDevActionEvent(ActionEvent ae) {
        lvArtefatosDisponiveisDev.getItems().addAll(lvArtefatosSelecionadosDev.getItems());
        lvArtefatosSelecionadosDev.getItems().clear();
    }

    @FXML
    private void btRemoverDevActionEvent(ActionEvent ae) {
        if (!lvArtefatosSelecionadosDev.getSelectionModel().getSelectedItems().isEmpty()) {
            lvArtefatosDisponiveisDev.getItems().addAll(lvArtefatosSelecionadosDev.getSelectionModel().getSelectedItems());
            lvArtefatosSelecionadosDev.getItems().removeAll(lvArtefatosSelecionadosDev.getSelectionModel().getSelectedItems());
        }
    }

    @FXML
    private void btAdicionarTesteActionEvent(ActionEvent ae) {
        if (!lvArtefatosDisponiveisTeste.getSelectionModel().getSelectedItems().isEmpty()) {
            lvArtefatosSelecionadosTeste.getItems().addAll(lvArtefatosDisponiveisTeste.getSelectionModel().getSelectedItems());
            lvArtefatosDisponiveisTeste.getItems().removeAll(lvArtefatosDisponiveisTeste.getSelectionModel().getSelectedItems());
        }
    }

    @FXML
    private void btAdicionarTodosTesteActionEvent(ActionEvent ae) {
        lvArtefatosSelecionadosTeste.getItems().addAll(lvArtefatosDisponiveisTeste.getItems());
        lvArtefatosDisponiveisTeste.getItems().clear();
    }

    @FXML
    private void btRemoverTodosTesteActionEvent(ActionEvent ae) {
        lvArtefatosDisponiveisTeste.getItems().addAll(lvArtefatosSelecionadosTeste.getItems());
        lvArtefatosSelecionadosTeste.getItems().clear();
    }

    @FXML
    private void btRemoverTesteActionEvent(ActionEvent ae) {
        if (!lvArtefatosSelecionadosTeste.getSelectionModel().getSelectedItems().isEmpty()) {
            lvArtefatosDisponiveisTeste.getItems().addAll(lvArtefatosSelecionadosTeste.getSelectionModel().getSelectedItems());
            lvArtefatosSelecionadosTeste.getItems().removeAll(lvArtefatosSelecionadosTeste.getSelectionModel().getSelectedItems());
        }
    }

    @FXML
    private void btCancelarActionEvent(ActionEvent ae) {
        stage.close();
    }

    private Atividade buildAtividade() {
        Atividade ativ = new Atividade();
        if (cbPacote.getValue() != null) {
            ativ.setPacote(cbPacote.getValue());
        }
        if (!StringUtil.isEmpty(tfAtividade.getText())) {
            ativ.setDescricao(tfAtividade.getText());
        }
        if (cbOrdemServico.getValue() != null) {
            ativ.setOrdemServico(cbOrdemServico.getValue());
        }
        if (spEstimada.getValue() != null) {
            ativ.setContagemEstimada(spEstimada.getValue());
        }
        if (spDetalhada.getValue() != null) {
            ativ.setContagemDetalhada(spDetalhada.getValue());
        }

        if (cbMes.getValue() != null) {
            ativ.setMes(cbMes.getValue());
        }
        return ativ;
    }

    private ProgressoAtividade buildLevantamento() {
        ProgressoAtividade levantamento = null;
        if (dpInicioLevantamento.getValue() != null || dpFimLevantamento != null
                || !lvArtefatosSelecionados.getItems().isEmpty()) {
            levantamento = new ProgressoAtividade();

            if (dpInicioLevantamento.getValue() != null) {
                levantamento.setDataInicio(DateUtil.asDate(dpInicioLevantamento.getValue()));
            }

            if (dpFimLevantamento.getValue() != null) {
                levantamento.setDataFim(DateUtil.asDate(dpFimLevantamento.getValue()));
            }

            if (!lvArtefatosSelecionados.getItems().isEmpty()) {
//                levantamento.setAtividadeArtefatos(lvArtefatosSelecionados.getItems().stream().collect(Collectors.toList()));
            }
        }
        return levantamento;
    }

    private ProgressoAtividade buildDesenvolvimento() {
        ProgressoAtividade desenvolvimento = null;
        if (dpInicioDesenvolvimento.getValue() != null || dpFimDesenvolvimento != null
                || !lvArtefatosSelecionadosDev.getItems().isEmpty()) {
            desenvolvimento = new ProgressoAtividade();

            if (dpInicioDesenvolvimento.getValue() != null) {
                desenvolvimento.setDataInicio(DateUtil.asDate(dpInicioDesenvolvimento.getValue()));
            }

            if (dpFimDesenvolvimento.getValue() != null) {
                desenvolvimento.setDataFim(DateUtil.asDate(dpFimDesenvolvimento.getValue()));
            }

            if (!lvArtefatosSelecionadosDev.getItems().isEmpty()) {
//                desenvolvimento.setAtividadeArtefatos(lvArtefatosSelecionadosDev.getItems().stream().collect(Collectors.toList()));
            }
        }
        return desenvolvimento;
    }

    private ProgressoAtividade buildTeste() {
        ProgressoAtividade teste = null;
        if (dpInicioTeste.getValue() != null || dpFimTeste != null
                || !lvArtefatosSelecionadosTeste.getItems().isEmpty()) {
            teste = new ProgressoAtividade();

            if (dpInicioTeste.getValue() != null) {
                teste.setDataInicio(DateUtil.asDate(dpInicioTeste.getValue()));
            }

            if (dpFimTeste.getValue() != null) {
                teste.setDataFim(DateUtil.asDate(dpFimTeste.getValue()));
            }

            if (!lvArtefatosSelecionadosTeste.getItems().isEmpty()) {
//                teste.setAtividadeArtefatos(lvArtefatosSelecionadosTeste.getItems().stream().collect(Collectors.toList()));
            }
        }
        return teste;
    }

    @FXML
    private void btConfirmarActionEvent(ActionEvent ae) {
        atividade = buildAtividade();
        atividade.setSituacaoAtividade(SituacaoAtividade.L);
        atividade.setFaturamento(Faturamento.AF);
        if (StringUtil.isEmpty(atividade.getDescricao())
                || atividade.getOrdemServico() == null
                || atividade.getPacote() == null) {
            MessageUtil.messageError(MessageUtil.CAMPOS_OBRIGATORIOS);
        } else if (atividade.getId() == null) {
            new AtividadeDAO().salvar(atividade);
            if (atividade.getId() != null) {
                ProgressoAtividade levantamento = buildLevantamento();
                levantamento.setAtividade(atividade);
                new ProgressoAtividadeDAO().salvar(levantamento);

                ProgressoAtividade desenvolvimento = buildDesenvolvimento();
                desenvolvimento.setAtividade(atividade);
                new ProgressoAtividadeDAO().salvar(desenvolvimento);

                ProgressoAtividade teste = buildTeste();
                teste.setAtividade(atividade);
                new ProgressoAtividadeDAO().salvar(teste);
            }
            MessageUtil.messageInformation("Atividade foi cadastrada com sucesso!");
            stage.close();
        } else {
            new AtividadeDAO().editar(atividade);
            MessageUtil.messageInformation("Atividade foi editada com sucesso!");
            stage.close();
        }
    }

    public void carregarDados() {
        if (atividade != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(atividade.getPrevisaoInicio());
            cbMes.getSelectionModel().select(Mes.values()[calendar.get(Calendar.MONTH)]);
            cbMes.setEditable(false);

        }
    }
}
