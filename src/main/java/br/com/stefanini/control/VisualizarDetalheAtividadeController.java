/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ParametroDAO;
import br.com.stefanini.control.dao.ProgressoAtividadeDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.OrdemServico;
import br.com.stefanini.model.entity.Pacote;
import br.com.stefanini.model.entity.Parametro;
import br.com.stefanini.model.entity.ProgressoAtividade;
import br.com.stefanini.model.entity.Projeto;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.enuns.TipoParametro;
import br.com.stefanini.model.util.DoubleConverter;
import br.com.stefanini.model.util.GeradorPlanilha;
import br.com.stefanini.model.util.MessageUtil;
import br.com.stefanini.model.util.PlanilhaDetalhes;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author rkkitagawa
 */
public class VisualizarDetalheAtividadeController extends ControllerBase implements Initializable {

    @FXML
    private Label lbProjetoModulo;

    @FXML
    private Label lbDetalhamento;

    @FXML
    private Button btPlanilhaBDMG;

    @FXML
    private Button btPlanilhaSTEFANINI;

    @FXML
    private Button btFaturar;

    @FXML
    private Button btCancelar;

    @FXML
    private Label lbTotalEstimadaoContrato;

    @FXML
    private Label lbTotalEstimadaoRepasse;

    @FXML
    private Label lbTotalDetalhadoContrato;

    @FXML
    private Label lbTotalDetalhadoRepasse;

    @FXML
    private TableView<ProgressoAtividade> tvLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colIdLev;

    @FXML
    private TableColumn<ProgressoAtividade, OrdemServico> colOsLev;

    @FXML
    private TableColumn<ProgressoAtividade, Modulo> colModLev;

    @FXML
    private TableColumn<ProgressoAtividade, Projeto> colProLev;

    @FXML
    private TableColumn<ProgressoAtividade, Pacote> colPacoteLev;

    @FXML
    private TableColumn<ProgressoAtividade, Atividade> colAtividadeLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaPFLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaPFLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaContratoLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaRepasseLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaContratoLev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaRepasseLev;

    @FXML
    private TableColumn<ProgressoAtividade, ProgressoAtividade> colAcoesLev;

    @FXML
    private Label lbTotalPfEstimadaLev;

    @FXML
    private Label lbTotalPfDetalhadaLev;

    @FXML
    private Label lbTotalEstimativaContratoLev;

    @FXML
    private Label lbTotalEstimativaRepasseLev;

    @FXML
    private Label lbTotalDetalhadaContratoLev;

    @FXML
    private Label lbTotalDetalhadaRepasseLev;

    @FXML
    private TableView<ProgressoAtividade> tvDev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colIdDev;

    @FXML
    private TableColumn<ProgressoAtividade, OrdemServico> colOsDev;

    @FXML
    private TableColumn<ProgressoAtividade, Modulo> colModDev;

    @FXML
    private TableColumn<ProgressoAtividade, Projeto> colProDev;

    @FXML
    private TableColumn<ProgressoAtividade, Pacote> colPacoteDev;

    @FXML
    private TableColumn<ProgressoAtividade, Atividade> colAtividadeDev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaPFDev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaPFDev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaContratoDev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaRepasseDev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaContratoDev;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaRepasseDev;

    @FXML
    private TableColumn<ProgressoAtividade, ProgressoAtividade> colAcoesDev;

    @FXML
    private Label lbTotalPfEstimadaDev;

    @FXML
    private Label lbTotalPfDetalhadaDev;

    @FXML
    private Label lbTotalEstimativaContratoDev;

    @FXML
    private Label lbTotalEstimativaRepasseDev;

    @FXML
    private Label lbTotalDetalhadaContratoDev;

    @FXML
    private Label lbTotalDetalhadaRepasseDev;

    @FXML
    private TableView<ProgressoAtividade> tvTst;

    @FXML
    private TableColumn<ProgressoAtividade, String> colIdTst;

    @FXML
    private TableColumn<ProgressoAtividade, OrdemServico> colOsTst;

    @FXML
    private TableColumn<ProgressoAtividade, Modulo> colModTst;

    @FXML
    private TableColumn<ProgressoAtividade, Projeto> colProTst;

    @FXML
    private TableColumn<ProgressoAtividade, Pacote> colPacoteTst;

    @FXML
    private TableColumn<ProgressoAtividade, Atividade> colAtividadeTst;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaPFTst;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaPFTst;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaContratoTst;

    @FXML
    private TableColumn<ProgressoAtividade, String> colEstimativaRepasseTst;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaContratoTst;

    @FXML
    private TableColumn<ProgressoAtividade, String> colDetalhadaRepasseTst;

    @FXML
    private TableColumn<ProgressoAtividade, ProgressoAtividade> colAcoesTst;

    @FXML
    private Label lbTotalPfEstimadaTst;

    @FXML
    private Label lbTotalPfDetalhadaTst;

    @FXML
    private Label lbTotalEstimativaContratoTst;

    @FXML
    private Label lbTotalEstimativaRepasseTst;

    @FXML
    private Label lbTotalDetalhadaContratoTst;

    @FXML
    private Label lbTotalDetalhadaRepasseTst;

    @FXML
    private Label lbQtdLev;

    @FXML
    private Label lbQtdDev;

    @FXML
    private Label lbQtdTst;

    @FXML
    private AnchorPane apPrincipal;
    private GerenciadorDeJanela gerenciadorDeJanela;
    private Stage stage;

    private Double valorContrato;

    private Double valorRepasse;

    @FXML
    private Label lbTxTotalDetalhadoRepasse;

    @FXML
    private Label lbTxTotalEstimadaoRepasse;

    private Map<String, Object> params;

    @FXML
    private TableView<ProgressoAtividade> tvAli;

    @FXML
    private TableColumn<ProgressoAtividade, String> tcIdAli;

    @FXML
    private TableColumn<ProgressoAtividade, String> tcAtividade;

    @FXML
    private TableColumn<ProgressoAtividade, String> tcNome;

    @FXML
    private TableColumn<ProgressoAtividade, Double> tcPfEstimada;
    @FXML
    private TableColumn<ProgressoAtividade, Double> tcPfDetalhada;

    @FXML
    private TableColumn<ProgressoAtividade, String> tcValorContratoDetalhada;

    @FXML
    private TableColumn<ProgressoAtividade, String> tcValorRepassseDetalhada;
    @FXML
    private TableColumn<ProgressoAtividade, String> tcValorContratoEstimada;

    @FXML
    private TableColumn<ProgressoAtividade, String> tcValorRepassseEstimada;

    @FXML
    private Label lbTotalRegistros;
    @FXML
    private Label lbTotalPfAlieEstimada;
    @FXML
    private Label lbTotalPfAlieDetalhada;
    @FXML
    private Label lbTotalAlieEstimadaContrato;
    @FXML
    private Label lbTotalAlieEstimadaRepasse;
    @FXML
    private Label lbTotalAlieDetalhadaContrato;
    @FXML
    private Label lbTotalAlieDetalhadaRepasse;

    private void calcularTotais() {
        Parametro paramContrato = ParametroDAO.getInstance().buscaParametroRecente(TipoParametro.CONTRATO);
        Parametro paramRepasse = ParametroDAO.getInstance().buscaParametroRecente(TipoParametro.REPASSE);

        Double totalPfEstimadaLev = 0.0;
        Double totalPfDetalhadaLev = 0.0;
        for (ProgressoAtividade progresso : tvLev.getItems()) {
            totalPfEstimadaLev += progresso.getId().getAtividade().getContagemEstimada();
            totalPfDetalhadaLev += progresso.getId().getAtividade().getContagemDetalhada();
        }
        lbTotalPfEstimadaLev.setText(DoubleConverter.doubleToString(totalPfEstimadaLev * .35));
        lbTotalPfDetalhadaLev.setText(DoubleConverter.doubleToString(totalPfDetalhadaLev * .35));
        //TODO Esperar o Higo e multiplicar o valor, Ok esperado By HIGOJO
        lbTotalEstimativaContratoLev.setText(DoubleConverter.doubleToString(totalPfEstimadaLev * .35 * paramContrato.getValor()));
        lbTotalEstimativaRepasseLev.setText(DoubleConverter.doubleToString(totalPfEstimadaLev * .35 * paramRepasse.getValor()));
        lbTotalDetalhadaContratoLev.setText(DoubleConverter.doubleToString(totalPfDetalhadaLev * .35 * paramContrato.getValor()));
        lbTotalDetalhadaRepasseLev.setText(DoubleConverter.doubleToString(totalPfDetalhadaLev * .35 * paramRepasse.getValor()));
        lbQtdLev.setText(String.valueOf(tvLev.getItems().size()));

        Double totalPfEstimadaDev = 0.0;
        Double totalPfDetalhadaDev = 0.0;
        for (ProgressoAtividade progresso : tvDev.getItems()) {
            totalPfEstimadaDev += progresso.getId().getAtividade().getContagemEstimada();
            totalPfDetalhadaDev += progresso.getId().getAtividade().getContagemDetalhada();
        }
        lbTotalPfEstimadaDev.setText(DoubleConverter.doubleToString(totalPfEstimadaDev * .4));
        lbTotalPfDetalhadaDev.setText(DoubleConverter.doubleToString(totalPfDetalhadaDev * .4));
        //TODO Esperar o Higo e multiplicar o valor,CARAI já ouvi
        lbTotalEstimativaContratoDev.setText(DoubleConverter.doubleToString(totalPfEstimadaDev * .4 * paramContrato.getValor()));
        lbTotalEstimativaRepasseDev.setText(DoubleConverter.doubleToString(totalPfEstimadaDev * .4 * paramRepasse.getValor()));
        lbTotalDetalhadaContratoDev.setText(DoubleConverter.doubleToString(totalPfDetalhadaDev * .4 * paramContrato.getValor()));
        lbTotalDetalhadaRepasseDev.setText(DoubleConverter.doubleToString(totalPfDetalhadaDev * .4 * paramRepasse.getValor()));
        lbQtdDev.setText(String.valueOf(tvDev.getItems().size()));

        Double totalPfEstimadaTst = 0.0;
        Double totalPfDetalhadaTst = 0.0;
        for (ProgressoAtividade progresso : tvTst.getItems()) {
            totalPfEstimadaTst += progresso.getId().getAtividade().getContagemEstimada();
            totalPfDetalhadaTst += progresso.getId().getAtividade().getContagemDetalhada();
        }
        lbTotalPfEstimadaTst.setText(DoubleConverter.doubleToString(totalPfEstimadaTst * .25));
        lbTotalPfDetalhadaTst.setText(DoubleConverter.doubleToString(totalPfDetalhadaTst * .25));
        //TODO Esperar o Higo e multiplicar o valor, Meu deus dnv? tu tem demencia?
        lbTotalEstimativaContratoTst.setText(DoubleConverter.doubleToString(totalPfEstimadaTst * .25 * paramContrato.getValor()));
        lbTotalEstimativaRepasseTst.setText(DoubleConverter.doubleToString(totalPfEstimadaTst * .25 * paramRepasse.getValor()));
        lbTotalDetalhadaContratoTst.setText(DoubleConverter.doubleToString(totalPfDetalhadaTst * .25 * paramContrato.getValor()));
        lbTotalDetalhadaRepasseTst.setText(DoubleConverter.doubleToString(totalPfDetalhadaTst * .25 * paramRepasse.getValor()));
        lbQtdTst.setText(String.valueOf(tvTst.getItems().size()));
        //Calcular ALI-AIE
        Double totalPFAieEstimada = tvAli.getItems().stream().mapToDouble(t -> t.getId().getAtividade().getContagemEstimada()).sum();
        Double totalPFAieDetalhada = tvAli.getItems().stream().mapToDouble(t -> t.getId().getAtividade().getContagemDetalhada()).sum();
        lbTotalRegistros.setText(String.valueOf(tvAli.getItems().size()));
        lbTotalPfAlieDetalhada.setText(String.valueOf(totalPFAieDetalhada));
        lbTotalPfAlieEstimada.setText(String.valueOf(totalPFAieEstimada));
        lbTotalAlieDetalhadaContrato.setText(DoubleConverter.doubleToString(totalPFAieDetalhada * paramContrato.getValor()));
        lbTotalAlieDetalhadaRepasse.setText(DoubleConverter.doubleToString(totalPFAieDetalhada * paramRepasse.getValor()));
        lbTotalAlieEstimadaContrato.setText(DoubleConverter.doubleToString(totalPFAieEstimada * paramContrato.getValor()));
        lbTotalAlieEstimadaRepasse.setText(DoubleConverter.doubleToString(totalPFAieEstimada * paramRepasse.getValor()));
        //Total geral
        lbTotalEstimadaoContrato.setText(DoubleConverter.doubleToString(totalPfEstimadaLev * .35 * paramContrato.getValor() + totalPfEstimadaDev * .4 * paramContrato.getValor() + totalPfEstimadaTst * .25 * paramContrato.getValor() + totalPFAieEstimada * paramContrato.getValor()));
        lbTotalEstimadaoRepasse.setText(DoubleConverter.doubleToString(totalPfEstimadaLev * .35 * paramRepasse.getValor() + totalPfEstimadaDev * .4 * paramRepasse.getValor() + totalPfEstimadaTst * .25 * paramRepasse.getValor() + totalPFAieEstimada * paramRepasse.getValor()));
        lbTotalDetalhadoContrato.setText(DoubleConverter.doubleToString(totalPfDetalhadaLev * .35 * paramContrato.getValor() + totalPfDetalhadaDev * .4 * paramContrato.getValor() + totalPfDetalhadaTst * .25 * paramContrato.getValor() + totalPFAieDetalhada * paramContrato.getValor()));
        lbTotalDetalhadoRepasse.setText(DoubleConverter.doubleToString(totalPfDetalhadaLev * .35 * paramRepasse.getValor() + totalPfDetalhadaDev * .4 * paramRepasse.getValor() + totalPfDetalhadaTst * .25 * paramRepasse.getValor() + totalPFAieDetalhada * paramContrato.getValor()));

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            params = (Map<String, Object>) apPrincipal.getUserData();
            lbDetalhamento.setText(buildLabelDetalhamento((Date) params.get("data")));
            gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
            lbProjetoModulo.setText("");
            tvLev.getItems().setAll(ProgressoAtividadeDAO.getInstance().pegarEmFaturamentoPorDataTipoAtividade((Date) params.get("data"), TipoAtividade.LE));
            tvDev.getItems().setAll(ProgressoAtividadeDAO.getInstance().pegarEmFaturamentoPorDataTipoAtividade((Date) params.get("data"), TipoAtividade.DE));
            tvTst.getItems().setAll(ProgressoAtividadeDAO.getInstance().pegarEmFaturamentoPorDataTipoAtividade((Date) params.get("data"), TipoAtividade.TE));
            calcularTotais();

        });
    }

    private String buildProjetoModulo(List<Atividade> atividades) {
        if (!atividades.isEmpty() && atividades.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(atividades.get(0).getPacote().getModulo().getProjeto().getDescricao());
            sb.append(" - ");
            sb.append(atividades.get(0).getPacote().getModulo().getDescricao());
            return sb.toString();
        } else {
            return "<<Projeto>> - <<Módulo>>";
        }
    }

    private String buildLabelDetalhamento(Date date) {
        if (date == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("Detalhamento de ");
        sb.append(new SimpleDateFormat("MM/YYYY").format(date));
        return sb.toString();
    }

    @FXML
    private void faturarAction() {
        List<ProgressoAtividade> progressoAtividades = new ArrayList<>();
        progressoAtividades.addAll(tvLev.getItems().stream().collect(Collectors.toList()));
        progressoAtividades.addAll(tvDev.getItems().stream().collect(Collectors.toList()));
        progressoAtividades.addAll(tvTst.getItems().stream().collect(Collectors.toList()));
        if (progressoAtividades.isEmpty()) {
            MessageUtil.messageError("Não existe progressos para faturar.");
        } else {
            ProgressoAtividadeDAO.getInstance().faturar(progressoAtividades);
            MessageUtil.confirmMessage("Faturamento realizado com sucesso.");
            retornarTelaPesquisa();
        }
    }

    @FXML
    private void cancelarAction() {
        retornarTelaPesquisa();
    }

    private void retornarTelaPesquisa() {
        ScrollPane scrollPane = (ScrollPane) gerenciadorDeJanela.procurarComponente("spContainer", apPrincipal);
        scrollPane.setContent(gerenciadorDeJanela.carregarComponente("PesquisarAtividade", params));
    }

    void criarPasta() {
        Path p = Paths.get("//planilhas");
        if (!Files.isDirectory(p)) {
            try {
                Files.createDirectories(p);
            } catch (IOException ex) {
                Logger.getLogger(VisualizarDetalheAtividadeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void gerarPlanilhaSTEFANINIAction() {
        criarPasta();
        String file = new GeradorPlanilha().gerarDetalhamento(new PlanilhaDetalhes((Date) params.get("data"),
                tvLev.getItems().stream().collect(Collectors.toList()),
                tvDev.getItems().stream().collect(Collectors.toList()),
                tvTst.getItems().stream().collect(Collectors.toList()),
                lbTotalEstimadaoContrato.getText(),
                lbTotalDetalhadoContrato.getText(),
                lbTotalEstimadaoRepasse.getText(),
                lbTotalDetalhadoRepasse.getText(),
                true));
        if (file == null) {
            MessageUtil.messageError("Erro ao gerar planilha STEFANINI");
        } else {
            MessageUtil.confirmMessage("Planilha gerada com sucesso: " + file);
        }
    }

    @FXML
    private void gerarPlanilhaBDMGAction() {
        criarPasta();
        String file = new GeradorPlanilha().gerarDetalhamento(new PlanilhaDetalhes((Date) params.get("data"),
                tvLev.getItems().stream().collect(Collectors.toList()),
                tvDev.getItems().stream().collect(Collectors.toList()),
                tvTst.getItems().stream().collect(Collectors.toList()),
                lbTotalEstimadaoContrato.getText(),
                lbTotalDetalhadoContrato.getText(),
                lbTotalEstimadaoRepasse.getText(),
                lbTotalDetalhadoRepasse.getText(),
                false));
        if (file == null) {
            MessageUtil.messageError("Erro ao gerar planilha BDMG");
        } else {
            MessageUtil.confirmMessage("Planilha gerada com sucesso: " + file);
        }

    }

    public void teste() {
        params = (Map<String, Object>) apPrincipal.getUserData();
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        lbDetalhamento.setText(buildLabelDetalhamento((Date) params.get("data")));
        lbProjetoModulo.setText("");
        tvLev.getItems().setAll(ProgressoAtividadeDAO.getInstance().pegarEmFaturamentoPorDataTipoAtividade((Date) params.get("data"), TipoAtividade.LE));
        tvDev.getItems().setAll(ProgressoAtividadeDAO.getInstance().pegarEmFaturamentoPorDataTipoAtividade((Date) params.get("data"), TipoAtividade.DE));
        tvAli.getItems().setAll(ProgressoAtividadeDAO.getInstance().pegarEmFaturamentoPorDataTipoAtividade((Date) params.get("data"), TipoAtividade.DE));
        tvTst.getItems().setAll(ProgressoAtividadeDAO.getInstance().pegarEmFaturamentoPorDataTipoAtividade((Date) params.get("data"), TipoAtividade.TE));
        calcularTotais();
        colAcoesLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, ProgressoAtividade> param) -> new SimpleObjectProperty<>(param.getValue()));
        colAcoesLev.setCellFactory((TableColumn<ProgressoAtividade, ProgressoAtividade> param) -> new TableCell<ProgressoAtividade, ProgressoAtividade>() {
            @Override
            protected void updateItem(ProgressoAtividade item, boolean empty) {
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    HBox gerenciadorLayout = new HBox();
                    Button btExcluir = new Button();
                    ImageView ivExcluir = new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/excluir.png")));
                    ivExcluir.setFitHeight(15d);
                    ivExcluir.setFitWidth(15d);
                    btExcluir.setGraphic(ivExcluir);
                    gerenciadorLayout.setSpacing(5);
                    gerenciadorLayout.setAlignment(Pos.CENTER);
                    gerenciadorLayout.getChildren().addAll(btExcluir);
                    setGraphic(gerenciadorLayout);
                    setAlignment(Pos.CENTER);
                    btExcluir.setTooltip(new Tooltip("Excluir"));
                    btExcluir.setOnAction((ActionEvent event) -> {
                        if (MessageUtil.confirmMessage("Você realmente deseja excluir este Progresso?")) {
                            tvLev.getItems().remove(item);
                            calcularTotais();
                        }
                    });
                }
            }
        });
        colAcoesDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, ProgressoAtividade> param) -> new SimpleObjectProperty<>(param.getValue()));
        colAcoesDev.setCellFactory((TableColumn<ProgressoAtividade, ProgressoAtividade> param) -> new TableCell<ProgressoAtividade, ProgressoAtividade>() {

            @Override
            protected void updateItem(ProgressoAtividade item, boolean empty) {
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    HBox gerenciadorLayout = new HBox();
                    Button btExcluir = new Button();
                    ImageView ivExcluir = new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/excluir.png")));
                    ivExcluir.setFitHeight(15d);
                    ivExcluir.setFitWidth(15d);
                    btExcluir.setGraphic(ivExcluir);
                    gerenciadorLayout.setSpacing(5);
                    gerenciadorLayout.setAlignment(Pos.CENTER);
                    gerenciadorLayout.getChildren().addAll(btExcluir);
                    setGraphic(gerenciadorLayout);
                    setAlignment(Pos.CENTER);
                    btExcluir.setOnAction((ActionEvent event) -> {
                        if (MessageUtil.confirmMessage("Você realmente deseja excluir este Progresso?")) {
                            tvDev.getItems().remove(item);
                            calcularTotais();
                        }
                    });
                }
            }
        });

        colAcoesTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, ProgressoAtividade> param) -> new SimpleObjectProperty<>(param.getValue()));
        colAcoesTst.setCellFactory((TableColumn<ProgressoAtividade, ProgressoAtividade> param) -> new TableCell<ProgressoAtividade, ProgressoAtividade>() {

            @Override
            protected void updateItem(ProgressoAtividade item, boolean empty) {
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    HBox gerenciadorLayout = new HBox();
                    Button btExcluir = new Button();
                    ImageView ivExcluir = new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/excluir.png")));
                    ivExcluir.setFitHeight(15d);
                    ivExcluir.setFitWidth(15d);
                    btExcluir.setGraphic(ivExcluir);
                    gerenciadorLayout.setSpacing(5);
                    gerenciadorLayout.setAlignment(Pos.CENTER);
                    gerenciadorLayout.getChildren().addAll(btExcluir);
                    setGraphic(gerenciadorLayout);
                    setAlignment(Pos.CENTER);
                    btExcluir.setOnAction((ActionEvent event) -> {
                        if (MessageUtil.confirmMessage("Você realmente deseja excluir este Progresso?")) {
                            tvTst.getItems().remove(item);
                            calcularTotais();
                        }
                    });
                }
            }

        });
        valorContrato =  ParametroDAO.getInstance().buscaParametroRecente(TipoParametro.CONTRATO).getValor();
        valorRepasse =  ParametroDAO.getInstance().buscaParametroRecente(TipoParametro.REPASSE).getValor();
        colIdLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param1) -> {
            return new SimpleStringProperty(String.valueOf(tvLev.getItems().indexOf(param1.getValue()) + 1));
        });
        colOsLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, OrdemServico> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getOrdemServico()));
        colModLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Modulo> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getPacote().getModulo()));
        colProLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Projeto> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getPacote().getModulo().getProjeto()));
        colPacoteLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Pacote> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getPacote()));
        colAtividadeLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Atividade> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade()));
        colEstimativaPFLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .35)));
        colDetalhadaPFLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .35)));
        colEstimativaContratoLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .35 * valorContrato)));
        colEstimativaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .35 * valorRepasse)));
        colDetalhadaContratoLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .35 * valorContrato)));
        colDetalhadaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .35 * valorRepasse)));
//Xing ling nao e boa pratica comentar codigo fonte
//        colIdLev.setStyle( "-fx-alignment: CENTER");
//        colOsLev.setStyle( "-fx-alignment: CENTER");
//        colModLev.setStyle( "-fx-alignment: CENTER");
//        colProLev.setStyle( "-fx-alignment: CENTER");
//        colPacoteLev.setStyle( "-fx-alignment: CENTER");
//        colAtividadeLev.setStyle( "-fx-alignment: CENTER");
        colEstimativaPFLev.setStyle("-fx-alignment: CENTER_RIGHT");
        colDetalhadaPFLev.setStyle("-fx-alignment: CENTER_RIGHT");
        colEstimativaContratoLev.setStyle("-fx-alignment: CENTER_RIGHT");
        colEstimativaRepasseLev.setStyle("-fx-alignment: CENTER_RIGHT");
        colDetalhadaContratoLev.setStyle("-fx-alignment: CENTER_RIGHT");
        colDetalhadaRepasseLev.setStyle("-fx-alignment: CENTER_RIGHT");

        colIdDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param1) -> {
            return new SimpleStringProperty(String.valueOf(tvDev.getItems().indexOf(param1.getValue()) + 1));
        });
        colOsDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, OrdemServico> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getOrdemServico()));
        colModDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Modulo> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getPacote().getModulo()));
        colProDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Projeto> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getPacote().getModulo().getProjeto()));
        colPacoteDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Pacote> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getPacote()));
        colAtividadeDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Atividade> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade()));
        colEstimativaPFDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .4)));
        colDetalhadaPFDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .4)));
        colEstimativaContratoDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .4 * valorContrato)));
        colEstimativaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .4 * valorRepasse)));
        colDetalhadaContratoDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .4 * valorContrato)));
        colDetalhadaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .4 * valorRepasse)));
//TODO xingling apaga isso
//        colIdDev.setStyle( "-fx-alignment: CENTER");
//        colOsDev.setStyle( "-fx-alignment: CENTER");
//        colModDev.setStyle( "-fx-alignment: CENTER");
//        colProDev.setStyle( "-fx-alignment: CENTER");
//        colPacoteDev.setStyle( "-fx-alignment: CENTER");
//        colAtividadeDev.setStyle( "-fx-alignment: CENTER");
        colEstimativaPFDev.setStyle("-fx-alignment: CENTER_RIGHT");
        colDetalhadaPFDev.setStyle("-fx-alignment: CENTER_RIGHT");
        colEstimativaContratoDev.setStyle("-fx-alignment: CENTER_RIGHT");
        colEstimativaRepasseDev.setStyle("-fx-alignment: CENTER_RIGHT");
        colDetalhadaContratoDev.setStyle("-fx-alignment: CENTER_RIGHT");
        colDetalhadaRepasseDev.setStyle("-fx-alignment: CENTER_RIGHT");
        colIdTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param1) -> {
            return new SimpleStringProperty(String.valueOf(tvTst.getItems().indexOf(param1.getValue()) + 1));
        });

        tcIdAli.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param1) -> {
            return new SimpleStringProperty(String.valueOf(tvAli.getItems().indexOf(param1.getValue()) + 1));
        });
        tcAtividade.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(param.getValue().getId().getAtividade().getDescricao()));
        tcNome.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(param.getValue().getId().getAtividade().getNomeAli()));
        tcNome.setCellFactory((TableColumn<ProgressoAtividade, String> param) -> new TableCell<ProgressoAtividade, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                    setText("");
                } else {
                    if (item == null || item.isEmpty()) {
                        setGraphic(null);
                        setText("");
                    } else {
                        ListView<String> alies = new ListView<>();
                        alies.getItems().setAll(item.split(Atividade.SCAPE));
                        alies.setPrefHeight(alies.getItems().size() * 25);
                        setGraphic(alies);
                    }
                }
            }
        });
        tcPfDetalhada.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Double> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getAliDetalhada()));
        tcPfEstimada.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Double> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getAliEstimada()));
        tcValorContratoDetalhada.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getAliDetalhada() * valorContrato)));
        tcValorRepassseDetalhada.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getAliDetalhada() * valorRepasse)));
        tcValorContratoEstimada.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getAliDetalhada() * valorContrato)));
        tcValorRepassseEstimada.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getAliDetalhada() * valorRepasse)));
        colOsTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, OrdemServico> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getOrdemServico()));
        colModTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Modulo> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getPacote().getModulo()));
        colProTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Projeto> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getPacote().getModulo().getProjeto()));
        colPacoteTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Pacote> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade().getPacote()));
        colAtividadeTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Atividade> param) -> new SimpleObjectProperty<>(param.getValue().getId().getAtividade()));
        colEstimativaPFTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .25)));
        colDetalhadaPFTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .25)));
        colEstimativaContratoTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .25 * valorContrato)));
        colEstimativaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .25 * valorRepasse)));
        colDetalhadaContratoTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .25 * valorContrato)));
        colDetalhadaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .25 * valorRepasse)));
//Carai xing tu comenta mesma coisa 3x por codigo
//        colIdTst.setStyle( "-fx-alignment: CENTER");
//        colOsTst.setStyle( "-fx-alignment: CENTER");
//        colModTst.setStyle( "-fx-alignment: CENTER");
//        colProTst.setStyle( "-fx-alignment: CENTER");
//        colPacoteTst.setStyle( "-fx-alignment: CENTER");
//        colAtividadeTst.setStyle( "-fx-alignment: CENTER");
        colEstimativaPFTst.setStyle("-fx-alignment: CENTER_RIGHT");
        colDetalhadaPFTst.setStyle("-fx-alignment: CENTER_RIGHT");
        colEstimativaContratoTst.setStyle("-fx-alignment: CENTER_RIGHT");
        colEstimativaRepasseTst.setStyle("-fx-alignment: CENTER_RIGHT");
        colDetalhadaContratoTst.setStyle("-fx-alignment: CENTER_RIGHT");
        colDetalhadaRepasseTst.setStyle("-fx-alignment: CENTER_RIGHT");

    }

    @Override
    public void buildAnalista() {
//        colEstimativaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colDetalhadaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colEstimativaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));   
//        colDetalhadaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colEstimativaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colDetalhadaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        lbTxTotalDetalhadoRepasse.setVisible(false);
//        lbTotalDetalhadoRepasse.setVisible(false);        
//        lbTxTotalEstimadaoRepasse.setVisible(false);
//        lbTotalEstimadaoRepasse.setVisible(false);

    }

    @Override
    public void buildBancoDados() {
//        colEstimativaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colDetalhadaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colEstimativaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));   
//        colDetalhadaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colEstimativaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colDetalhadaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        lbTxTotalDetalhadoRepasse.setVisible(false);
//        lbTotalDetalhadoRepasse.setVisible(false);        
//        lbTxTotalEstimadaoRepasse.setVisible(false);
//        lbTotalEstimadaoRepasse.setVisible(false);

    }

    @Override
    public void buildBDMG() {
        colEstimativaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
        colDetalhadaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
        colEstimativaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
        colDetalhadaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
        colEstimativaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
        colDetalhadaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));

        lbTxTotalDetalhadoRepasse.setVisible(false);
        lbTotalDetalhadoRepasse.setVisible(false);
        lbTxTotalEstimadaoRepasse.setVisible(false);
        lbTotalEstimadaoRepasse.setVisible(false);
        btPlanilhaSTEFANINI.setVisible(false);
        btPlanilhaBDMG.setVisible(true);

        colAcoesLev.setCellFactory((TableColumn<ProgressoAtividade, ProgressoAtividade> param) -> new TableCell<ProgressoAtividade, ProgressoAtividade>() {

            @Override
            protected void updateItem(ProgressoAtividade item, boolean empty) {
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    HBox gerenciadorLayout = new HBox();
                    Button btExcluir = new Button();
                    ImageView ivExcluir = new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/excluir.png")));
                    ivExcluir.setFitHeight(15d);
                    ivExcluir.setFitWidth(15d);
                    btExcluir.setGraphic(ivExcluir);
                    btExcluir.setVisible(false);
                    gerenciadorLayout.setSpacing(5);
                    gerenciadorLayout.setAlignment(Pos.CENTER);
                    gerenciadorLayout.getChildren().addAll(btExcluir);
                    setGraphic(gerenciadorLayout);
                    setAlignment(Pos.CENTER);
                    btExcluir.setTooltip(new Tooltip("Excluir"));
                    btExcluir.setOnAction((ActionEvent event) -> {
                        if (MessageUtil.confirmMessage("Você realmente deseja excluir este Progresso?")) {
                            tvLev.getItems().remove(item);
                            calcularTotais();
                        }
                    });
                }
            }
        });

    }

    @Override
    public void buildDesenvolvedor() {
//        colEstimativaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colDetalhadaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colEstimativaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));   
//        colDetalhadaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colEstimativaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colDetalhadaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        lbTxTotalDetalhadoRepasse.setVisible(false);
//        lbTotalDetalhadoRepasse.setVisible(false);        
//        lbTxTotalEstimadaoRepasse.setVisible(false);
//        lbTotalEstimadaoRepasse.setVisible(false);

    }

    @Override
    public void buildGerente() {
        colEstimativaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .35 * valorRepasse)));
        colDetalhadaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .35 * valorRepasse)));

        colEstimativaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .4 * valorRepasse)));
        colDetalhadaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .4 * valorRepasse)));

        colEstimativaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemEstimada() * .25 * valorRepasse)));
        colDetalhadaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getId().getAtividade().getContagemDetalhada() * .25 * valorRepasse)));
        lbTxTotalDetalhadoRepasse.setVisible(true);
        lbTotalDetalhadoRepasse.setVisible(true);
        lbTxTotalEstimadaoRepasse.setVisible(true);
        lbTotalEstimadaoRepasse.setVisible(true);
        btPlanilhaSTEFANINI.setVisible(true);
        btPlanilhaBDMG.setVisible(true);
//        btFaturar.setVisible(true);
//        
//        lbTotalEstimativaRepasseLev.setVisible(true);
//        lbTotalDetalhadaRepasseLev.setVisible(true);
        colAcoesLev.setCellFactory((TableColumn<ProgressoAtividade, ProgressoAtividade> param) -> new TableCell<ProgressoAtividade, ProgressoAtividade>() {

            @Override
            protected void updateItem(ProgressoAtividade item, boolean empty) {
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    HBox gerenciadorLayout = new HBox();
                    Button btExcluir = new Button();
                    ImageView ivExcluir = new ImageView(new Image(getClass().getResourceAsStream(GerenciadorDeJanela.PACOTE_VIEW + "image/excluir.png")));
                    ivExcluir.setFitHeight(15d);
                    ivExcluir.setFitWidth(15d);
                    btExcluir.setGraphic(ivExcluir);
                    btExcluir.setVisible(true);
                    gerenciadorLayout.setSpacing(5);
                    gerenciadorLayout.setAlignment(Pos.CENTER);
                    gerenciadorLayout.getChildren().addAll(btExcluir);
                    setGraphic(gerenciadorLayout);
                    setAlignment(Pos.CENTER);
                    btExcluir.setTooltip(new Tooltip("Excluir"));
                    btExcluir.setOnAction((ActionEvent event) -> {
                        if (MessageUtil.confirmMessage("Você realmente deseja excluir este Progresso?")) {
                            tvLev.getItems().remove(item);
                            calcularTotais();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void buildQualidade() {
//        colEstimativaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colDetalhadaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colEstimativaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));   
//        colDetalhadaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colEstimativaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        colDetalhadaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty("-----"));
//        lbTxTotalDetalhadoRepasse.setVisible(false);
//        lbTotalDetalhadoRepasse.setVisible(false);        
//        lbTxTotalEstimadaoRepasse.setVisible(false);
//        lbTotalEstimadaoRepasse.setVisible(false);

    }
}
