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
import br.com.stefanini.control.dao.ProjetoDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.AtividadeArtefatos;
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
import br.com.stefanini.model.util.SpinnerTextToValue;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    @FXML
    private Spinner<Integer> spAno;
    @FXML
    private Accordion aPaineis;

    private Atividade atividade;
    private Stage stage;
    private GerenciadorDeJanela gerenciadorDeJanela;

    Map<String, Object> params = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apPrincipal.sceneProperty().addListener((ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) -> {
            if (newValue != null) {
                params = (Map) apPrincipal.getUserData();
                if (apPrincipal.getUserData() instanceof Atividade) {
                    ManterAtividadeController.this.atividade = (Atividade) apPrincipal.getUserData();
                    if (atividade.getId() != null) {
                        atividade = new AtividadeDAO().carregarArtefatos(atividade);
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(atividade.getPrevisaoInicio());
                    ((SpinnerValueFactory.IntegerSpinnerValueFactory) spAno.getValueFactory()).setMin(calendar.get(Calendar.YEAR));
                    carregarDados();
                } else {
                    ManterAtividadeController.this.atividade = new Atividade();
                }
                newValue.windowProperty().addListener((ObservableValue<? extends Window> observable1, Window oldValue1, Window newWindow) -> {
                    stage = (Stage) newWindow;
                });
            }
        });
        spEstimada.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9999999999.9, 0));
        spDetalhada.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9999999999.9, 0));
        spEstimada.getValueFactory().setConverter(new DoubleConverter());
        spDetalhada.getValueFactory().setConverter(new DoubleConverter());
        SpinnerTextToValue.configure(spEstimada);
        SpinnerTextToValue.configure(spDetalhada);
        Calendar calendar = Calendar.getInstance();
        spAno.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(calendar.get(Calendar.YEAR), Integer.MAX_VALUE, calendar.get(Calendar.YEAR)));
        cbProjeto.getItems().setAll(new ProjetoDAO().pegarTodos());
        cbOrdemServico.getItems().setAll(new OrdemServicoDAO().pegarTodos());
        cbMes.getItems().setAll(Mes.values());
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

    private Atividade buildAtividade(Atividade ativ) {
        if (atividade == null) {
            ativ = new Atividade();
        }
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

    @FXML
    private void btConfirmarActionEvent(ActionEvent ae) {
        atividade = buildAtividade(atividade);
        atividade.setSituacaoAtividade(SituacaoAtividade.L);
        atividade.setFaturamento(Faturamento.AF);
        atividade.setAtividadeArtefatos(lvArtefatosSelecionados.getItems().stream().map(t -> {
            AtividadeArtefatos atividadeArtefatos = new AtividadeArtefatos();
            atividadeArtefatos.setId(new AtividadeArtefatos.AtividadeArtefatosId());
            atividadeArtefatos.getId().setAtividade(atividade);
            atividadeArtefatos.getId().setArtefato(t);
            return atividadeArtefatos;
        }).collect(Collectors.toList()));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, spAno.getValue());
        calendar.set(Calendar.MONTH, cbMes.getSelectionModel().getSelectedIndex());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        atividade.setPrevisaoInicio(calendar.getTime());
        if (StringUtil.isEmpty(atividade.getDescricao())
                || atividade.getOrdemServico() == null
                || atividade.getPacote() == null) {
            MessageUtil.messageError(MessageUtil.CAMPOS_OBRIGATORIOS);
        } else if((dpInicioLevantamento.getValue() != null && dpFimLevantamento.getValue() != null) 
                && (dpInicioLevantamento.getValue().isAfter(dpFimLevantamento.getValue()))){
            MessageUtil.messageError("A data de término deve ser maior que a data inicial do Levantamento");
        } else if((dpInicioDesenvolvimento.getValue() != null && dpFimDesenvolvimento.getValue() != null) 
                && (dpInicioDesenvolvimento.getValue().isAfter(dpFimDesenvolvimento.getValue()))){
            MessageUtil.messageError("A data de término deve ser maior que a data inicial do Desenvolvimento");
        } else if((dpInicioTeste.getValue() != null && dpFimTeste.getValue() != null) 
                && (dpInicioTeste.getValue().isAfter(dpFimTeste.getValue()))){
            MessageUtil.messageError("A data de término deve ser maior que a data inicial do Teste e Homologação");
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
        if (atividade != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(atividade.getPrevisaoInicio());
            cbMes.getSelectionModel().select(Mes.values()[calendar.get(Calendar.MONTH)]);
            spAno.getValueFactory().setValue(calendar.get(Calendar.YEAR));
            cbMes.setEditable(false);
            tfAtividade.setText(atividade.getDescricao());
            cbOrdemServico.getSelectionModel().select(atividade.getOrdemServico());
            spDetalhada.getValueFactory().setValue(atividade.getContagemDetalhada() == null ? 0d : atividade.getContagemDetalhada());
            spEstimada.getValueFactory().setValue(atividade.getContagemEstimada() == null ? 0d : atividade.getContagemEstimada());
            if (atividade.getId() != null) {
                lvArtefatosSelecionados.getItems().setAll(atividade.getAtividadeArtefatos().stream().map(AtividadeArtefatos::getId).map(AtividadeArtefatos.AtividadeArtefatosId::getArtefato).collect(Collectors.toList()));
                lvArtefatosDisponiveis.getItems().removeAll(lvArtefatosSelecionados.getItems());
            }
            if (atividade.getPacote() != null) {
                cbPacote.getSelectionModel().select(atividade.getPacote());
                if (atividade.getPacote().getModulo() != null) {
                    cbModulo.getSelectionModel().select(atividade.getPacote().getModulo());
                    if (atividade.getPacote().getModulo().getProjeto() != null) {
                        cbProjeto.getSelectionModel().select(atividade.getPacote().getModulo().getProjeto());
                    } else {
                        cbProjeto.getSelectionModel().clearSelection();
                    }
                } else {
                    cbProjeto.getSelectionModel().clearSelection();
                    cbModulo.getSelectionModel().clearSelection();
                }
            } else {
                cbProjeto.getSelectionModel().clearSelection();
                cbProjeto.getSelectionModel().clearSelection();
                cbModulo.getSelectionModel().clearSelection();
            }
        }
    }

    public void teste() {
        params = (Map<String, Object>) apPrincipal.getUserData();
        atividade = (Atividade) params.get("Atividade");
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        stage = (Stage) params.get("modalStage");
        carregarDados();
        params.put("Atividade", new Atividade());
    }
}
