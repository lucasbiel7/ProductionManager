/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.component.SpinnerDouble;
import br.com.stefanini.control.dao.AtividadeDAO;
import br.com.stefanini.control.dao.ModuloDAO;
import br.com.stefanini.control.dao.OrdemServicoDAO;
import br.com.stefanini.control.dao.PacoteDAO;
import br.com.stefanini.control.dao.ProgressoAtividadeDAO;
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
import br.com.stefanini.model.enuns.OrigemAtividade;
import br.com.stefanini.model.enuns.SituacaoAtividade;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class ManterAtividadeController implements Initializable {

    private String listaAuxNomes;
    private List<String> listaNomes;
    @FXML
    private ListView<String> lvNomesAlis;
    @FXML
    private ComboBox<OrigemAtividade> cbTipoAtividade;
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
    private SpinnerDouble spEstimada;
    @FXML
    private SpinnerDouble spDetalhada;
    @FXML
    private SpinnerDouble spAliEstimada;
    @FXML
    private SpinnerDouble spAliDetalhada;
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
        cbTipoAtividade.getItems().setAll(OrigemAtividade.values());
        apPrincipal.sceneProperty().addListener((ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) -> {
            if (newValue != null) {
                params = (Map) apPrincipal.getUserData();
                if (apPrincipal.getUserData() instanceof Atividade) {
                    ManterAtividadeController.this.atividade = (Atividade) apPrincipal.getUserData();
                    if (atividade.getId() != null) {
                        atividade = AtividadeDAO.getInstance().carregarArtefatos(atividade);
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
        spAliDetalhada.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9999999999.9, 0));
        spAliEstimada.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9999999999.9, 0));

        Calendar calendar = Calendar.getInstance();
        spAno.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(calendar.get(Calendar.YEAR), Integer.MAX_VALUE, calendar.get(Calendar.YEAR)));
          
        cbProjeto.getItems().setAll(ProjetoDAO.getInstance().pegarTodos());
        cbOrdemServico.getItems().setAll(OrdemServicoDAO.getInstance().pegarTodos());
        cbMes.getItems().setAll(Mes.values());
        lvArtefatosDisponiveis.getItems().setAll(Artefato.values());
        lvArtefatosDisponiveis.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvArtefatosSelecionados.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void btAdicionarAction(ActionEvent ae) {
        TextInputDialog text = new TextInputDialog();
        text.initOwner(stage);
        text.setTitle("Adicionar nome");
        text.setHeaderText("Digite o nome do ALI/AIE:");
        text.setContentText("Nome: ");
        String nomeAux = text.showAndWait().orElse(null);
        if (listaNomes.stream().filter(t -> t.equals(nomeAux)).count() > 0) {
            MessageUtil.messageError("Este nome já existe");
            text.close();
            return;
        }

        if ((null != nomeAux) && !(StringUtil.isEmpty(nomeAux))) {
            listaNomes.add(nomeAux);
        }

        listaAuxNomes = String.join(Atividade.SCAPE, listaNomes);
        System.out.println(listaAuxNomes);
        lvNomesAlis.getItems().setAll(listaNomes);

    }

    @FXML
    private void miExcluirActionEvent(ActionEvent ae) {
        if (MessageUtil.confirmMessage("Você realmente deseja excluir este nome?")) {
            String nomeSelecionado = lvNomesAlis.getSelectionModel().getSelectedItem();
            listaNomes.remove(nomeSelecionado);
            lvNomesAlis.getItems().setAll(listaNomes);
        }
    }

    @FXML
    private void cbProjetoActionEvent(ActionEvent ae) {
        if (cbProjeto.getValue() != null) {
            cbModulo.getItems().setAll(ModuloDAO.getInstance().pegarPorProjeto(cbProjeto.getValue()));
        } else {
            cbModulo.getItems().clear();
        }
    }

    @FXML
    private void cbModuloActionEvent(ActionEvent ae) {
        if (cbModulo.getValue() != null) {
            cbPacote.getItems().setAll(PacoteDAO.getInstance().pegarPorModulo(cbModulo.getValue()));
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

        if (null != cbTipoAtividade.getValue()) {
            ativ.setOrigemAtividade(cbTipoAtividade.getValue());
        }
        if (!lvNomesAlis.getItems().isEmpty()) {
            ativ.setNomeAli(listaAuxNomes);
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
        if (spAliDetalhada.getValue() != null) {
            ativ.setAliDetalhada(spAliDetalhada.getValue());
        }
        if (spAliEstimada.getValue() != null) {
            ativ.setAliEstimada(spAliEstimada.getValue());
        }
        return ativ;
    }

    private ProgressoAtividade buildLevantamento() {
        ProgressoAtividade levantamento = null;
        if (dpInicioLevantamento.getValue() != null || dpFimLevantamento != null
                || !lvArtefatosSelecionados.getItems().isEmpty()) {
            levantamento = new ProgressoAtividade();

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
                || atividade.getPacote() == null
                || atividade.getOrigemAtividade() == null
                || atividade.getNomeAli() == null) {
            MessageUtil.messageError(MessageUtil.CAMPOS_OBRIGATORIOS);
        } else if ((dpInicioLevantamento.getValue() != null && dpFimLevantamento.getValue() != null)
                && (dpInicioLevantamento.getValue().isAfter(dpFimLevantamento.getValue()))) {
            MessageUtil.messageError("A data de término deve ser maior que a data inicial do Levantamento");
        } else if ((dpInicioDesenvolvimento.getValue() != null && dpFimDesenvolvimento.getValue() != null)
                && (dpInicioDesenvolvimento.getValue().isAfter(dpFimDesenvolvimento.getValue()))) {
            MessageUtil.messageError("A data de término deve ser maior que a data inicial do Desenvolvimento");
        } else if ((dpInicioTeste.getValue() != null && dpFimTeste.getValue() != null)
                && (dpInicioTeste.getValue().isAfter(dpFimTeste.getValue()))) {
            MessageUtil.messageError("A data de término deve ser maior que a data inicial do Teste e Homologação");
        } else if (atividade.getId() == null) {
            AtividadeDAO.getInstance().salvar(atividade);
            if (OrigemAtividade.S == atividade.getOrigemAtividade()) {
                ProgressoAtividade servico = new ProgressoAtividade();
                servico.getId().setAtividade(atividade);
                servico.getId().setTipoAtividade(TipoAtividade.SE);
                servico.getId().setProgresso(100.0);
                servico.setFaturamento(Faturamento.EF);
                servico.setDataDoProgresso(new Date());
                ProgressoAtividadeDAO.getInstance().salvar(servico);
            }
            MessageUtil.messageInformation("Atividade foi cadastrada com sucesso!");
            stage.close();
        } else {
            AtividadeDAO.getInstance().editar(atividade);
            MessageUtil.messageInformation("Atividade foi editada com sucesso!");
            stage.close();
        }
    }

    public void carregarDados() {
        if (atividade != null) {
            if(null == atividade.getOrigemAtividade()){
                cbTipoAtividade.setValue(OrigemAtividade.P);
            }else{
                cbTipoAtividade.setValue(atividade.getOrigemAtividade());
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(atividade.getPrevisaoInicio());
            if(atividade.getNomeAli()!=null){
                listaNomes.clear();
                listaNomes.addAll(Arrays.asList(atividade.getNomeAli().split(Atividade.SCAPE)));
                lvNomesAlis.getItems().setAll(listaNomes);
            }else{
                listaNomes.clear();
                lvNomesAlis.getItems().setAll(listaNomes);
            }
            cbMes.getSelectionModel().select(Mes.values()[calendar.get(Calendar.MONTH)]);
            spAno.getValueFactory().setValue(calendar.get(Calendar.YEAR));
            cbMes.setEditable(false);
            tfAtividade.setText(atividade.getDescricao());
            cbOrdemServico.getSelectionModel().select(atividade.getOrdemServico());
            spDetalhada.getValueFactory().setValue(atividade.getContagemDetalhada() == null ? 0d : atividade.getContagemDetalhada());
            spEstimada.getValueFactory().setValue(atividade.getContagemEstimada() == null ? 0d : atividade.getContagemEstimada());
            spAliDetalhada.getValueFactory().setValue(atividade.getAliDetalhada() == null ? 0 : atividade.getAliDetalhada());
            spAliEstimada.getValueFactory().setValue(atividade.getAliEstimada() == null ? 0 : atividade.getAliEstimada());
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
        
        if(null != atividade.getId()){
            cbTipoAtividade.setDisable(true);
        }else{
            cbTipoAtividade.setDisable(false);
        }
        
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        stage = (Stage) params.get("modalStage");
        listaNomes = new ArrayList<>();
        carregarDados();
        params.put("Atividade", new Atividade());
    }
}
