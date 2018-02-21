/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.ProgressoAtividadeDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.OrdemServico;
import br.com.stefanini.model.entity.Pacote;
import br.com.stefanini.model.entity.ProgressoAtividade;
import br.com.stefanini.model.entity.Projeto;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.util.DoubleConverter;
import br.com.stefanini.model.util.MessageUtil;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rkkitagawa
 */
public class VisualizarDetalheAtividadeController implements Initializable {

    @FXML
    private Label lbProjetoModulo;

    @FXML
    private Label lbDetalhamento;

    @FXML
    private Button btPlanilhaBDMG;

    @FXML
    private Button btPlanilhaSTEAFNINI;

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

    private Map<String, Object> paramsMap;

    private void calcularTotais() {
        Double totalPfEstimadaLev = 0.0;
        Double totalPfDetalhadaLev = 0.0;
        for (ProgressoAtividade progresso : tvLev.getItems()) {
            totalPfEstimadaLev += progresso.getAtividade().getContagemEstimada();
            totalPfDetalhadaLev += progresso.getAtividade().getContagemDetalhada();
        }
        lbTotalPfEstimadaLev.setText(DoubleConverter.doubleToString(totalPfEstimadaLev * .35));
        lbTotalPfDetalhadaLev.setText(DoubleConverter.doubleToString(totalPfDetalhadaLev * .35));
        //TODO Esperar o Higo e multiplicar o valor
        lbTotalEstimativaContratoLev.setText(DoubleConverter.doubleToString(totalPfEstimadaLev));
        lbTotalEstimativaRepasseLev.setText(DoubleConverter.doubleToString(totalPfDetalhadaLev));
        lbTotalDetalhadaContratoLev.setText(DoubleConverter.doubleToString(totalPfEstimadaLev));
        lbTotalDetalhadaRepasseLev.setText(DoubleConverter.doubleToString(totalPfDetalhadaLev));
        lbQtdLev.setText(String.valueOf(tvLev.getItems().size()));

        Double totalPfEstimadaDev = 0.0;
        Double totalPfDetalhadaDev = 0.0;
        for (ProgressoAtividade progresso : tvDev.getItems()) {
            totalPfEstimadaDev += progresso.getAtividade().getContagemEstimada();
            totalPfDetalhadaDev += progresso.getAtividade().getContagemDetalhada();
        }
        lbTotalPfEstimadaDev.setText(DoubleConverter.doubleToString(totalPfEstimadaDev * .4));
        lbTotalPfDetalhadaDev.setText(DoubleConverter.doubleToString(totalPfDetalhadaDev * .4));
        //TODO Esperar o Higo e multiplicar o valor
        lbTotalEstimativaContratoDev.setText(DoubleConverter.doubleToString(totalPfEstimadaDev));
        lbTotalEstimativaRepasseDev.setText(DoubleConverter.doubleToString(totalPfDetalhadaDev));
        lbTotalDetalhadaContratoDev.setText(DoubleConverter.doubleToString(totalPfEstimadaDev));
        lbTotalDetalhadaRepasseDev.setText(DoubleConverter.doubleToString(totalPfDetalhadaDev));
        lbQtdDev.setText(String.valueOf(tvDev.getItems().size()));

        Double totalPfEstimadaTst = 0.0;
        Double totalPfDetalhadaTst = 0.0;
        for (ProgressoAtividade progresso : tvTst.getItems()) {
            totalPfEstimadaTst += progresso.getAtividade().getContagemEstimada();
            totalPfDetalhadaTst += progresso.getAtividade().getContagemDetalhada();
        }
        lbTotalPfEstimadaTst.setText(DoubleConverter.doubleToString(totalPfEstimadaTst * .25));
        lbTotalPfDetalhadaTst.setText(DoubleConverter.doubleToString(totalPfDetalhadaTst * .25));
        //TODO Esperar o Higo e multiplicar o valor
        lbTotalEstimativaContratoTst.setText(DoubleConverter.doubleToString(totalPfEstimadaTst));
        lbTotalEstimativaRepasseTst.setText(DoubleConverter.doubleToString(totalPfDetalhadaTst));
        lbTotalDetalhadaContratoTst.setText(DoubleConverter.doubleToString(totalPfEstimadaTst));
        lbTotalDetalhadaRepasseTst.setText(DoubleConverter.doubleToString(totalPfDetalhadaTst));
        lbQtdTst.setText(String.valueOf(tvTst.getItems().size()));

        lbTotalEstimadaoContrato.setText(DoubleConverter.doubleToString(totalPfEstimadaLev * .35 + totalPfEstimadaDev * .4 + totalPfEstimadaTst * .25));
        lbTotalEstimadaoRepasse.setText(DoubleConverter.doubleToString(totalPfEstimadaLev * .35 + totalPfEstimadaDev * .4 + totalPfEstimadaTst * .25));
        lbTotalDetalhadoContrato.setText(DoubleConverter.doubleToString(totalPfDetalhadaLev * .35 + totalPfDetalhadaDev * .4 + totalPfDetalhadaTst * .25));
        lbTotalDetalhadoRepasse.setText(DoubleConverter.doubleToString(totalPfDetalhadaLev * .35 + totalPfDetalhadaDev * .4 + totalPfDetalhadaTst * .25));

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            paramsMap = (Map<String, Object>) apPrincipal.getUserData();
            lbDetalhamento.setText(buildLabelDetalhamento((Date) paramsMap.get("data")));
            List<Atividade> atividades = (List) paramsMap.get("atividades");
            lbProjetoModulo.setText(buildProjetoModulo(atividades));
            System.out.println("TE" + new ProgressoAtividadeDAO().pegarEmFaturamentoPorDataTipoAtividade(new Date(1514764800000l), TipoAtividade.TE).size());
            System.out.println("DE" + new ProgressoAtividadeDAO().pegarEmFaturamentoPorDataTipoAtividade(new Date(1514764800000l), TipoAtividade.DE).size());
            System.out.println("LE" + new ProgressoAtividadeDAO().pegarEmFaturamentoPorDataTipoAtividade(new Date(1514764800000l), TipoAtividade.LE).size());
            new ProgressoAtividadeDAO().pegarEmFaturamentoPorDataTipoAtividade((Date) paramsMap.get("data"), TipoAtividade.TE);
            new ProgressoAtividadeDAO().pegarEmFaturamentoPorDataTipoAtividade((Date) paramsMap.get("data"), TipoAtividade.DE);
            new ProgressoAtividadeDAO().pegarEmFaturamentoPorDataTipoAtividade((Date) paramsMap.get("data"), TipoAtividade.LE);

            tvLev.getItems().setAll(new ProgressoAtividadeDAO().pegarEmFaturamentoPorDataTipoAtividade((Date) paramsMap.get("data"), TipoAtividade.LE));
            tvDev.getItems().setAll(new ProgressoAtividadeDAO().pegarEmFaturamentoPorDataTipoAtividade((Date) paramsMap.get("data"), TipoAtividade.DE));
            tvTst.getItems().setAll(new ProgressoAtividadeDAO().pegarEmFaturamentoPorDataTipoAtividade((Date) paramsMap.get("data"), TipoAtividade.TE));

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

            colIdLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param1) -> {
                return new SimpleStringProperty(String.valueOf(tvLev.getItems().indexOf(param1.getValue()) + 1));
            });
            colOsLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, OrdemServico> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getOrdemServico()));
            colModLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Modulo> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getPacote().getModulo()));
            colProLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Projeto> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getPacote().getModulo().getProjeto()));
            colPacoteLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Pacote> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getPacote()));
            colAtividadeLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Atividade> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade()));
            colEstimativaPFLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getAtividade().getContagemEstimada() * .35)));
            colDetalhadaPFLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getAtividade().getContagemDetalhada() * .35)));
            colEstimativaContratoLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
            colEstimativaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
            colDetalhadaContratoLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
            colDetalhadaRepasseLev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));

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
            colOsDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, OrdemServico> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getOrdemServico()));
            colModDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Modulo> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getPacote().getModulo()));
            colProDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Projeto> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getPacote().getModulo().getProjeto()));
            colPacoteDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Pacote> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getPacote()));
            colAtividadeDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Atividade> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade()));
            colEstimativaPFDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getAtividade().getContagemEstimada() * .4)));
            colDetalhadaPFDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getAtividade().getContagemDetalhada() * .4)));
            colEstimativaContratoDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
            colEstimativaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
            colDetalhadaContratoDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
            colDetalhadaRepasseDev.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));

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
            colOsTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, OrdemServico> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getOrdemServico()));
            colModTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Modulo> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getPacote().getModulo()));
            colProTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Projeto> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getPacote().getModulo().getProjeto()));
            colPacoteTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Pacote> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade().getPacote()));
            colAtividadeTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, Atividade> param) -> new SimpleObjectProperty<>(param.getValue().getAtividade()));
            colEstimativaPFTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getAtividade().getContagemEstimada() * .25)));
            colDetalhadaPFTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(DoubleConverter.doubleToString(param.getValue().getAtividade().getContagemDetalhada() * .25)));
            colEstimativaContratoTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
            colEstimativaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
            colDetalhadaContratoTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));
            colDetalhadaRepasseTst.setCellValueFactory((TableColumn.CellDataFeatures<ProgressoAtividade, String> param) -> new SimpleStringProperty(""));

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
        StringBuilder sb = new StringBuilder("Detalhamento de ");
        sb.append(new SimpleDateFormat("MM/YYYY").format(date));
        return sb.toString();
    }

}
