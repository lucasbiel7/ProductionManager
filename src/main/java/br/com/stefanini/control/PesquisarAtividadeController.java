/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.AtividadeDAO;
import br.com.stefanini.control.dao.ModuloDAO;
import br.com.stefanini.control.dao.PacoteDAO;
import br.com.stefanini.control.dao.ProgressoAtividadeDAO;
import br.com.stefanini.control.dao.ProjetoDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.OrdemServico;
import br.com.stefanini.model.entity.Pacote;
import br.com.stefanini.model.entity.ProgressoAtividade;
import br.com.stefanini.model.entity.Projeto;
import br.com.stefanini.model.enuns.Faturamento;
import br.com.stefanini.model.enuns.SituacaoAtividade;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.util.MessageUtil;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rkkitagawa
 */
public class PesquisarAtividadeController implements Initializable {

    @FXML
    private ScrollPane spContainer;
    @FXML
    private AnchorPane apPrincipal;
    private GerenciadorDeJanela gerenciadorDeJanela;
    private Stage stage;
    @FXML
    private Label lbPesquisa;

    private Date param;

    @FXML
    private ComboBox<Projeto> cbProjeto;

    @FXML
    private ComboBox<Modulo> cbModulo;

    @FXML
    private ComboBox<Pacote> cbPacote;

    @FXML
    private TextField txAtividade;

    @FXML
    private ComboBox<SituacaoAtividade> cbSituacao;

    @FXML
    private ComboBox<Faturamento> cbFaturamento;

    @FXML
    private TableView<Atividade> tvAtividade;

    @FXML
    private TableColumn<Atividade, String> colId;

    @FXML
    private TableColumn<Atividade, OrdemServico> colOs;

    @FXML
    private TableColumn<Atividade, String> colAtividade;

    @FXML
    private TableColumn<Atividade, String> colEstimada;

    @FXML
    private TableColumn<Atividade, String> colDetalhada;

    @FXML
    private TableColumn<Atividade, Atividade> colLevantamento;

    @FXML
    private TableColumn<Atividade, Atividade> colDesenvolvimento;

    @FXML
    private TableColumn<Atividade, Atividade> colHomologacao;

    @FXML
    private TableColumn<Atividade, Atividade> colAcoes;

    @FXML
    private Label lbTotalEstimada;

    @FXML
    private Label lbTotalDetalhada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            stage = (Stage) apPrincipal.getScene().getWindow();
            param = (Date) apPrincipal.getUserData();
            lbPesquisa.setText(buildLabel(param));
        });
        gerenciadorDeJanela = new GerenciadorDeJanela();
        cbPacote.setValue(null);
        cbModulo.setValue(null);
        cbPacote.setValue(null);
        cbProjeto.getItems().setAll(new ProjetoDAO().pegarTodos());
        txAtividade.setText("");
        cbSituacao.getItems().setAll(SituacaoAtividade.values());
        cbFaturamento.getItems().setAll(Faturamento.values());
        tvAtividade.getItems().setAll(new AtividadeDAO().pegarTodos());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOs.setCellValueFactory(new PropertyValueFactory<>("ordemServico"));
        colAtividade.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colEstimada.setCellValueFactory(new PropertyValueFactory<>("contagemDetalhada"));
        colDetalhada.setCellValueFactory(new PropertyValueFactory<>("contagemEstimada"));
        colLevantamento.setCellValueFactory((TableColumn.CellDataFeatures<Atividade, Atividade> param1) -> new SimpleObjectProperty<>(param1.getValue()));
        colDesenvolvimento.setCellValueFactory((TableColumn.CellDataFeatures<Atividade, Atividade> param1) -> new SimpleObjectProperty<>(param1.getValue()));
        colHomologacao.setCellValueFactory((TableColumn.CellDataFeatures<Atividade, Atividade> param1) -> new SimpleObjectProperty<>(param1.getValue()));
        colAcoes.setCellValueFactory((TableColumn.CellDataFeatures<Atividade, Atividade> param1) -> new SimpleObjectProperty<>(param1.getValue()));
        colHomologacao.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.TESTE));
        colLevantamento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.LEVANTAMENTO));
        colDesenvolvimento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.DESENVOLVIMENTO));
    }

    @FXML
    private void btPesquisarAction() {
        List<Atividade> atividades = new AtividadeDAO().pegarPorAtividade(buildAtividadeFromfxml());
        tvAtividade.getItems().setAll(atividades);
        buildTotais(atividades);
    }

    private void buildTotais(List<Atividade> atividades) {
        long countEstimada = 0;
        long countDetalhada = 0;
        if (atividades != null) {
            for (Atividade atividade : atividades) {
                countEstimada += atividade.getContagemEstimada();
                countDetalhada += atividade.getContagemDetalhada();
            }
        }
        lbTotalEstimada.setText(String.valueOf(countEstimada));
        lbTotalDetalhada.setText(String.valueOf(countDetalhada));

    }

    private Atividade buildAtividadeFromfxml() {
        Atividade ativ = new Atividade();

        if (cbPacote.getValue() != null) {
            ativ.setPacote(cbPacote.getValue());
        } else {
            ativ.setPacote(new Pacote());
        }

        if (cbModulo.getValue() != null) {
            ativ.getPacote().setModulo(cbModulo.getValue());
        } else {
            ativ.getPacote().setModulo(new Modulo());
        }

        if (cbProjeto.getValue() != null) {
            ativ.getPacote().getModulo().setProjeto(cbProjeto.getValue());
        } else {
            ativ.getPacote().getModulo().setProjeto(new Projeto());
        }

        if (!"".equals(txAtividade.getText().trim())) {
            ativ.setDescricao(txAtividade.getText());
        }
        if (cbSituacao.getValue() != null) {
            ativ.setSituacaoAtividade(cbSituacao.getValue());
        }
        if (cbFaturamento.getValue() != null) {
            ativ.setFaturamento(cbFaturamento.getValue());
        }
        return ativ;
    }

    private String buildLabel(Date date) {
        StringBuilder sb = new StringBuilder("Atividade(s) de ");
        sb.append(new SimpleDateFormat("MM/YYYY").format(date));
        return sb.toString();
    }

    @FXML
    private void cbProjetoAction() {
        if (cbProjeto.getValue() != null) {
            cbModulo.getItems().setAll(new ModuloDAO().pegarPorProjeto(cbProjeto.getValue()));
        } else {
            cbModulo.getItems().clear();
        }
    }

    @FXML
    private void cbModuloAction() {
        if (cbModulo.getValue() != null) {
            cbPacote.getItems().setAll(new PacoteDAO().pegarPorModulo(cbModulo.getValue()));
        } else {
            cbPacote.getItems().clear();
        }
    }

    @FXML
    private void btAdicionarAction() {
        //TODO adicionar
    }

    private class TableCellFases extends TableCell<Atividade, Atividade> {

        private TipoAtividade tipoAtividade;

        public TableCellFases(TipoAtividade tipoAtividade) {
            this.tipoAtividade = tipoAtividade;
        }

        @Override
        protected void updateItem(Atividade item, boolean empty) {
            if (empty) {
                setGraphic(null);
            } else {
                Spinner<Double> spDados = new Spinner<>();
                List<ProgressoAtividade> progressoAtividades = new ProgressoAtividadeDAO().pegarPorAtividadeTipo(item, this.tipoAtividade);
                double initValue = progressoAtividades.stream().map(ProgressoAtividade::getProgresso).findFirst().orElse(0d);
                spDados.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(initValue, 100d, initValue, 5d));
                spDados.valueProperty().addListener((ObservableValue<? extends Double> observable, Double oldValue, Double newValue) -> {
                    ProgressoAtividade progressoAtividade = new ProgressoAtividade();
                    progressoAtividade.setAtividade(item);
                    progressoAtividade.setDataDoProgresso(new Date());
                    progressoAtividade.setProgresso(newValue);
                    progressoAtividade.setTipoAtividade(this.tipoAtividade);
                    if (newValue == 100d) {
                        if (MessageUtil.confirmMessage("Deseja realmente finalizar essa atividade?")) {
                            new ProgressoAtividadeDAO().salvar(progressoAtividade);
                            ((SpinnerValueFactory.DoubleSpinnerValueFactory) spDados.getValueFactory()).setMin(progressoAtividade.getProgresso());
                        } else {
                            newValue = oldValue;
                        }
                    } else {
                        new ProgressoAtividadeDAO().salvar(progressoAtividade);
                        ((SpinnerValueFactory.DoubleSpinnerValueFactory) spDados.getValueFactory()).setMin(progressoAtividade.getProgresso());
                    }
                });
                setGraphic(spDados);
            }
        }

    }
}
