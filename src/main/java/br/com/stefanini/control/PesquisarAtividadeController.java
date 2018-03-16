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
import br.com.stefanini.model.util.DateUtil;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;

/**
 * FXML Controller class
 *
 * @author rkkitagawa
 */
public class PesquisarAtividadeController extends ControllerBase implements Initializable {

    @FXML
    private ScrollPane spContainer;
    @FXML
    private AnchorPane apPrincipal;
    private GerenciadorDeJanela gerenciadorDeJanela;
    private Stage stage;
    @FXML
    private Label lbPesquisa;

    private Date param;
    private Projeto projeto;

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

    @FXML
    private Button btAdd;
    
    @FXML
    private Button btVisualizar;
    
    Map<String, Object> params = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apPrincipal.sceneProperty().addListener((ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) -> {
            if (newValue != null) {
                stage = (Stage) newValue.getWindow();
                params = (Map) apPrincipal.getUserData();
                gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
                param = (Date) params.get("dataInicio");
//                projeto = (Projeto) params.get("projetoObject");
//                cbProjeto.setValue(projeto);
            }
        });
        colId.setCellValueFactory((TableColumn.CellDataFeatures<Atividade, String> param1) -> {
            return new SimpleStringProperty(String.valueOf(tvAtividade.getItems().indexOf(param1.getValue()) + 1));
        });
        colOs.setCellValueFactory(new PropertyValueFactory<>("ordemServico"));
        colAtividade.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        colEstimada.setCellValueFactory(new PropertyValueFactory<>("contagemEstimada"));
        colDetalhada.setCellValueFactory(new PropertyValueFactory<>("contagemDetalhada"));
        colLevantamento.setCellValueFactory((TableColumn.CellDataFeatures<Atividade, Atividade> param1) -> new SimpleObjectProperty<>(param1.getValue()));
        colDesenvolvimento.setCellValueFactory((TableColumn.CellDataFeatures<Atividade, Atividade> param1) -> new SimpleObjectProperty<>(param1.getValue()));
        colHomologacao.setCellValueFactory((TableColumn.CellDataFeatures<Atividade, Atividade> param1) -> new SimpleObjectProperty<>(param1.getValue()));
        colAcoes.setCellValueFactory((TableColumn.CellDataFeatures<Atividade, Atividade> param1) -> new SimpleObjectProperty<>(param1.getValue()));
        colHomologacao.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.TE,false));
        colLevantamento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.LE,false));
        colDesenvolvimento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.DE,false));
        colAcoes.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCell<Atividade, Atividade>() {
            @Override
            protected void updateItem(Atividade atividade, boolean empty) {
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER);
                    hBox.setSpacing(5d);
                    Button btEditar = new Button("", new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/editar.png"), 15, 15, true, true)));
                    Button btAlteracaoEscopo = new Button("", new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/bandeira.png"), 15, 15, true, true)));
                    Button btIncluirPendencia = new Button("", new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/sino.png"), 15, 15, true, true)));
                    Button btRemoverPendencia = new Button("", new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/block.png"), 15, 15, true, true)));
                    Button btVisualizarPendencia = new Button("", new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/visualizar.png"), 15, 15, true, true)));
                    Button btEnviarParaFaturamento = new Button("", new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/enviar.png"), 15, 15, true, true)));
                    Button btExcluir = new Button("", new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/excluir.png"), 15, 15, true, true)));
                    btEditar.setTooltip(new Tooltip("Editar Atividade"));
                    btAlteracaoEscopo.setTooltip(new Tooltip("Alteração de Escopo Após aprovação"));
                    btIncluirPendencia.setTooltip(new Tooltip("Incluir Pendência na atividade"));
                    btRemoverPendencia.setTooltip(new Tooltip("Remover Pendência da atividade"));
                    btVisualizarPendencia.setTooltip(new Tooltip("Visualizar Pendências da Atividade"));
                    btEnviarParaFaturamento.setTooltip(new Tooltip("Enviar para faturamento"));
                    btExcluir.setTooltip(new Tooltip("Excluir atividade"));

                    btAlteracaoEscopo.setOnAction((ActionEvent event) -> {
                        params.put("Atividade", atividade);
                        gerenciadorDeJanela.abrirModal("AlterarEscopoAtividade", params, "Alteração de Escopo da Atividade");
                        carregarTabela();

//                        Stage stage1 = gerenciadorDeJanela.mostrarJanela(new Stage(), gerenciadorDeJanela.carregarComponente("AlterarEscopoAtividade", atividade), "Alteração de Escopo da Atividade");
//                        stage1.initOwner(PesquisarAtividadeController.this.stage);
//                        stage1.initModality(Modality.WINDOW_MODAL);
//                        stage1.showAndWait();
//                        carregarTabela();
                    });
                    btEnviarParaFaturamento.setOnAction((ActionEvent event) -> {
                        params.put("Atividade", atividade);
                        gerenciadorDeJanela.abrirModal("FaturarAtividade", params, "Faturar atividade");
                        carregarTabela();
//                        Stage stage = gerenciadorDeJanela.mostrarJanela(new Stage(), gerenciadorDeJanela.carregarComponente("FaturarAtividade", atividade), "Faturar atividade");
//                        stage.initOwner(PesquisarAtividadeController.this.stage);
//                        stage.initModality(Modality.WINDOW_MODAL);
//                        stage.showAndWait();
//                        carregarTabela();
                    });
                    btEditar.setOnAction((ActionEvent event) -> {
                        if (atividade.getPrevisaoInicio() == null) {
                            atividade.setPrevisaoInicio(param);
                            new AtividadeDAO().editar(atividade);
                        }
                        params.put("Atividade", atividade);
                        gerenciadorDeJanela.abrirModal("ManterAtividade", params, "Início");
                        carregarTabela();
//                        Stage stage = gerenciadorDeJanela.mostrarJanela(new Stage(), gerenciadorDeJanela.carregarComponente("ManterAtividade", atividade), "Início");
//                        stage.initOwner(PesquisarAtividadeController.this.stage);
//                        stage.initModality(Modality.WINDOW_MODAL);
//                        stage.showAndWait();
//                        carregarTabela();
                    });
                    btExcluir.setOnAction((ActionEvent event) -> {
                        if (MessageUtil.confirmMessage("Deseja realmente excluir a atividade?")) {
                            try {
                                new AtividadeDAO().excluir(atividade);
                            } catch (Exception e) {
                                if (e instanceof PersistenceException
                                        && e.getCause() instanceof ConstraintViolationException) {
                                    MessageUtil.messageError("Não é possível excluir uma atividade que já foi inicializada.");
                                }
                            }
                            carregarTabela();
                        }
                    });
                    hBox.getChildren().addAll(btEditar, btAlteracaoEscopo, btIncluirPendencia, btRemoverPendencia, btVisualizarPendencia, btEnviarParaFaturamento, btExcluir);
                    setGraphic(hBox);
                }

            }
        });
    }

    @FXML
    private void btPesquisarAction() {
        List<Atividade> atividades = new AtividadeDAO().pegarPorAtividade(buildAtividadeFromfxml(), param);
        tvAtividade.getItems().setAll(atividades);
        buildTotais(atividades);
    }

    private void buildTotais(List<Atividade> atividades) {
        Double countEstimada = 0.0;
        Double countDetalhada = 0.0;
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

        if (StringUtil.isEmpty(txAtividade.getText())) {
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
        Atividade atividade = new Atividade();
        atividade.setPrevisaoInicio(param);
        params.put("Atividade", atividade);
//        Stage stage = gerenciadorDeJanela.mostrarJanela(new Stage(), gerenciadorDeJanela.carregarComponente("ManterAtividade", params), "Início");
//        stage.initOwner(this.stage);
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.showAndWait();
        gerenciadorDeJanela.abrirModal("ManterAtividade", params, "Início");
        carregarTabela();
    }

    private void carregarTabela() {
        tvAtividade.getItems().clear();
        tvAtividade.getItems().setAll(new AtividadeDAO().pegarPorMes(DateUtil.truncateDate(param)));
        buildTotais(tvAtividade.getItems());
    }

    @FXML
    private void visualizarAction() {
        ScrollPane scrollPane = (ScrollPane) gerenciadorDeJanela.procurarComponente("spContainer", apPrincipal);
        params.put("data", param);
//        paramsMap.put("atividades", tvAtividade.getItems().stream().collect(Collectors.toList()));
        scrollPane.setContent(gerenciadorDeJanela.carregarComponente("VisualizarDetalheAtividade", params));

    }

    @Override
    public void buildAnalista() {
        btAdd.setVisible(true);
        colAcoes.setVisible(true);
        colHomologacao.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.TE,true));
        colLevantamento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.LE,false));
        colDesenvolvimento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.DE,true));
        btVisualizar.setVisible(false);
    }

    @Override
    public void buildBancoDados() {
        btAdd.setVisible(true);
        colAcoes.setVisible(true);
        colHomologacao.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.TE,true));
        colLevantamento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.LE,true));
        colDesenvolvimento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.DE,true));
        btVisualizar.setVisible(true);
    }

    @Override
    public void buildBDMG() {
        btAdd.setVisible(false);
        colAcoes.setVisible(false);
        colHomologacao.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.TE,true));
        colLevantamento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.LE,true));
        colDesenvolvimento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.DE,true));
        btVisualizar.setVisible(false);
    }

    @Override
    public void buildDesenvolvedor() {
        btAdd.setVisible(false);
        colAcoes.setVisible(false);
        colHomologacao.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.TE,true));
        colLevantamento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.LE,true));
        colDesenvolvimento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.DE,false));
        btVisualizar.setVisible(false);
    }

    @Override
    public void buildGerente() {
        btAdd.setVisible(true);
        colAcoes.setVisible(true);
        colHomologacao.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.TE,false));
        colLevantamento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.LE,false));
        colDesenvolvimento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.DE,false));
        btVisualizar.setVisible(true);
    }

    @Override
    public void buildQualidade() {
        btAdd.setVisible(false);
        colAcoes.setVisible(false);
        colHomologacao.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.TE,false));
        colLevantamento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.LE,true));
        colDesenvolvimento.setCellFactory((TableColumn<Atividade, Atividade> param1) -> new TableCellFases(TipoAtividade.DE,true));
        btVisualizar.setVisible(false);
    }

    private class TableCellFases extends TableCell<Atividade, Atividade> {

        private final TipoAtividade tipoAtividade;
        private boolean disabled;
        
        public TableCellFases(TipoAtividade tipoAtividade, boolean disabled) {
            this.tipoAtividade = tipoAtividade;
            this.disabled = disabled;
        }

        @Override
        protected void updateItem(Atividade item, boolean empty) {
            if (empty) {
                setGraphic(null);
            } else {
                Spinner<Double> spDados = new Spinner<>();
                if(item.getProgresso(tipoAtividade)==null){
                    item.setProgresso(new ProgressoAtividadeDAO().pegarUtualProgressoPorAtividadeTipo(item, this.tipoAtividade),tipoAtividade);
                }
                double initValue = 0;
                if (item.getProgresso(tipoAtividade) != null) {
                    initValue = item.getProgresso(tipoAtividade);
                }
                spDados.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(initValue, 100d, initValue, 5d));
                spDados.valueProperty().addListener((ObservableValue<? extends Double> observable, Double oldValue, Double newValue) -> {
                    if (!Objects.equals(oldValue, newValue)) {
                        ProgressoAtividade progressoAtividade = new ProgressoAtividade();
                        progressoAtividade.getId().setAtividade(item);
                        progressoAtividade.setDataDoProgresso(new Date());
                        progressoAtividade.getId().setProgresso(newValue);
                        progressoAtividade.getId().setTipoAtividade(this.tipoAtividade);
                        if (newValue > oldValue) {
                            if (newValue == 100d) {
                                if (MessageUtil.confirmMessage("Deseja realmente finalizar essa atividade e enviar para faturamento?")) {
                                    progressoAtividade.setFaturamento(Faturamento.EF);
                                    new ProgressoAtividadeDAO().salvar(progressoAtividade);
                                    item.setProgresso(progressoAtividade.getId().getProgresso(), tipoAtividade);
                                    ((SpinnerValueFactory.DoubleSpinnerValueFactory) spDados.getValueFactory()).setMin(progressoAtividade.getId().getProgresso());
                                }
                            } else {
                                new ProgressoAtividadeDAO().salvar(progressoAtividade);
                                item.setProgresso(progressoAtividade.getId().getProgresso(), tipoAtividade);
                                ((SpinnerValueFactory.DoubleSpinnerValueFactory) spDados.getValueFactory()).setMin(progressoAtividade.getId().getProgresso());
                            }
                        }
                    }
                });
                spDados.setDisable(this.disabled);
                setGraphic(spDados);
            }
        }

    }

    public void teste() {
        params = (Map) apPrincipal.getUserData();
        param = (Date) params.get("dataInicio");
        projeto = (Projeto) params.get("projetoObject");
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        lbPesquisa.setText(buildLabel(param));
        carregarTabela();
        cbPacote.setValue(null);
        cbModulo.setValue(null);
        cbPacote.setValue(null);
        cbProjeto.getItems().setAll(new ProjetoDAO().pegarTodos());
        cbProjeto.setValue(projeto);
        txAtividade.setText("");
        cbSituacao.getItems().setAll(SituacaoAtividade.values());
        cbFaturamento.getItems().setAll(Faturamento.values());
    }
}
