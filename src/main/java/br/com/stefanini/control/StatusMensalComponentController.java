/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.CustoDAO;
import br.com.stefanini.model.entity.Custo;
import br.com.stefanini.model.entity.ProgressoAtividade;
import br.com.stefanini.model.entity.Projeto;
import br.com.stefanini.model.enuns.Faturamento;
import br.com.stefanini.model.util.DateUtil;
import br.com.stefanini.model.util.DoubleConverter;
import br.com.stefanini.model.util.MessageUtil;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class StatusMensalComponentController extends ControllerBase implements Initializable {

    private Date inicio;
    private String idProjeto;
    private String idModulo;
    private String idPacote;
    private Projeto projetoObject;
//    private List<Atividade> atividades = new ArrayList<>();
//    private List<ProgressoAtividade> progressos = new ArrayList<>();
    private Double paramContrato;
    private Double paramRepasse;
    private List<ProgressoAtividade> levantamentosAno = new ArrayList<>();
    private List<ProgressoAtividade> desenvolvimentosAno = new ArrayList<>();
    private List<ProgressoAtividade> testesAno = new ArrayList<>();
    private List<ProgressoAtividade> servicosAno = new ArrayList<>();

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Button btAddCusto;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbPfEstimada;
    @FXML
    private Label lbValorContratoEstimada;
    @FXML
    private Label lbValorRepasseEstimada;
    @FXML
    private Label lbPfDetalhada;
    @FXML
    private Label lbValorContratoDetalhada;
    @FXML
    private Label lbValorRepasseDetalhada;
    
    //    @FXML
    //    private Label lbTotal;
    
    @FXML
    private Label lbLevantamento;
    
    @FXML
    private Label lbDesenvolvimento;
    @FXML
    private Label lbTeste;

    @FXML
    private Label lbProjeto;
    @FXML
    private Label lbCustoPlanejado;
    @FXML
    private Label lbCustoRealizado;
    @FXML
    private Label lbTipoPF;
    @FXML
    private Label lbRepasse;
    @FXML
    private Label lbResultadoTecnico;
    @FXML
    private Label lbFaturamento;
    @FXML
    private Label lbCustoComercial;
    @FXML
    private Label lbResultadoComercial;
    @FXML
    private Label lbResultadoCombinado;
    @FXML
    private Label lbRentabilidade;
    @FXML
    private GridPane gpLayout;

    private Custo custoMes;
    private Double repasse = 0.0;
    private Double contrato = 0.0;
    @FXML
    private VBox vbPlanejamento;

    private GerenciadorDeJanela gerenciadorDeJanela;

    Map<String, Object> params = new HashMap<>();

//    private Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btAddCusto.setTooltip(new Tooltip("Atualizar Custos"));
        Platform.runLater(() -> {
//            stage = (Stage) apPrincipal.getScene().getWindow();
            gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
            params = (Map) apPrincipal.getUserData();
        });
    }

    @FXML
    private void btAdicionarAction() {
        if (null == projetoObject.getDescricao()) {
            MessageUtil.messageInformation("Favor pesquisar com um projeto selecionado para atualizar custos.");
        } else {
            if (null != custoMes.getId()) {
            
            params.put("custo", CustoDAO.getInstance().pegarPorId(custoMes.getId()));
        } else {
            Custo custo = new Custo();
            params.put("custo", custo);
        }
        params.put("dtInclusao", inicio);
        params.put("projeto", projetoObject);
        gerenciadorDeJanela.abrirModal("CustoModal", params, "Custo");
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        Custo custoAux = (Custo) params.get("CustoAux");
        Double resultadoTecnico = 0.0;
        if (custoAux != null) {
            lbCustoPlanejado.setText("Téc. Planejado: " + DoubleConverter.doubleToString(custoAux.getCustoTecnicoPlanejado()));
            lbCustoRealizado.setText("Téc. Realizado: " + DoubleConverter.doubleToString(custoAux.getCustoTecnicoRealizado()));
        
            if (custoAux.getCustoTecnicoRealizado() > 0.0) {
            resultadoTecnico = repasse - custoAux.getCustoTecnicoRealizado();
            lbResultadoTecnico.setText("Resultado Técnico: " + DoubleConverter.doubleToString(resultadoTecnico));
            } else {
                resultadoTecnico = repasse - custoAux.getCustoTecnicoPlanejado();
                lbResultadoTecnico.setText("Resultado Técnico: " + DoubleConverter.doubleToString(resultadoTecnico));
            }
        }
        
        Double porcentagem = 26.5;
        Double custoComercial = ((contrato * porcentagem) / 100) + repasse;
        Double resultadoComercial = contrato - custoComercial;
        Double resultadoCombinado = resultadoTecnico + resultadoComercial;
        lbResultadoCombinado.setText("Resultado Combinado: " + DoubleConverter.doubleToString(resultadoCombinado));
        }
    }

    public void teste() {
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        Map param = (Map) apPrincipal.getUserData();
        inicio = (Date) param.get("data");
        idProjeto = (String) param.get("projeto");
        idModulo = (String) param.get("modulo");
        idPacote = (String) param.get("pacote");
        projetoObject = (Projeto) param.get("projetoObject");
//        atividades = (List<Atividade>) param.get("atividades");
//        progressos = (List<ProgressoAtividade>) param.get("progressos");
        paramContrato = (Double) param.get("paramContrato");
        paramRepasse = (Double) param.get("paramRepasse");
        levantamentosAno = (List<ProgressoAtividade>) param.get("levantamentosAno");
        desenvolvimentosAno = (List<ProgressoAtividade>) param.get("desenvolvimentosAno");
        testesAno = (List<ProgressoAtividade>) param.get("testesAno");
        servicosAno = (List<ProgressoAtividade>) param.get("servicosAno");

        String dataParam = DateUtil.formatterDate(inicio, "yyyy-MM-dd");
//        Double estimadaLevPrevisao = 0.0;
//        Double detalhadaLevPrevisao = 0.0;
        Double estimadaLevFaturado = 0.0;
        Double detalhadaLevFaturado = 0.0;
        List<ProgressoAtividade> levantamentosMesPrevisao = new ArrayList<>();
        List<ProgressoAtividade> levantamentosMesFaturado = new ArrayList<>();
        for (ProgressoAtividade progress : levantamentosAno) {
            String dataBanco = DateUtil.formatterDate(progress.getId().getAtividade().getPrevisaoInicio(), "yyyy-MM-dd");
            String dataBancoAux = DateUtil.formatterDate(progress.getDataFaturamento(), "yyyy-MM-dd");
            if ((dataBanco.equals(dataParam)) 
                    && ((progress.getFaturamento().equals(Faturamento.EF)) 
                    || (progress.getFaturamento().equals(Faturamento.FO)))) {
                levantamentosMesPrevisao.add(progress);
//                estimadaLevPrevisao += progress.getId().getAtividade().getContagemEstimada() * .35;
//                detalhadaLevPrevisao += progress.getId().getAtividade().getContagemDetalhada()* .35;
            }
            if((dataBancoAux.equals(dataParam)) && (progress.getFaturamento().equals(Faturamento.FO))){
                levantamentosMesFaturado.add(progress);
                estimadaLevFaturado += progress.getId().getAtividade().getContagemEstimada() * .35;
                detalhadaLevFaturado += progress.getId().getAtividade().getContagemDetalhada()* .35;
            }
        }

//        Double estimadaDevPrevisao = 0.0;
//        Double detalhadaDevPrevisao = 0.0;
        Double estimadaDevFaturado = 0.0;
        Double detalhadaDevFaturado = 0.0;
        Double estimadaAliFaturado = 0.0;
        Double detalhadaAliFaturado = 0.0;
        
        List<ProgressoAtividade> desenvolvimenetosMesPrevisao = new ArrayList<>();
        List<ProgressoAtividade> desenvolvimenetosMesFaturado = new ArrayList<>();
        for (ProgressoAtividade progress : desenvolvimentosAno) {
            String dataBanco = DateUtil.formatterDate(progress.getId().getAtividade().getPrevisaoInicio(), "yyyy-MM-dd");
            String dataBancoAux = DateUtil.formatterDate(progress.getDataFaturamento(), "yyyy-MM-dd");
            if ((dataBanco.equals(dataParam)) 
                    && ((progress.getFaturamento().equals(Faturamento.EF)) 
                    || (progress.getFaturamento().equals(Faturamento.FO)))) {
                desenvolvimenetosMesPrevisao.add(progress);
//                estimadaDevPrevisao += progress.getId().getAtividade().getContagemEstimada() * .4;
//                detalhadaDevPrevisao += progress.getId().getAtividade().getContagemDetalhada()* .4;
            }
            if ((dataBancoAux.equals(dataParam)) && (progress.getFaturamento().equals(Faturamento.FO))) {
                desenvolvimenetosMesFaturado.add(progress);
                estimadaDevFaturado += progress.getId().getAtividade().getContagemEstimada() * .4;
                detalhadaDevFaturado += progress.getId().getAtividade().getContagemDetalhada()* .4;
                estimadaAliFaturado += progress.getId().getAtividade().getAliEstimada();
                detalhadaAliFaturado += progress.getId().getAtividade().getAliDetalhada();
                
            }
        }

//        Double estimadaTestPrevisao = 0.0;
//        Double detalhadaTestPrevisao = 0.0;
        Double estimadaTestFaturado = 0.0;
        Double detalhadaTestFaturado = 0.0;
        List<ProgressoAtividade> testesMesPrevisao = new ArrayList<>();
        List<ProgressoAtividade> testesMesFaturado = new ArrayList<>();
        for (ProgressoAtividade progress : testesAno) {
            String dataBanco = DateUtil.formatterDate(progress.getId().getAtividade().getPrevisaoInicio(), "yyyy-MM-dd");
            String dataBancoAux = DateUtil.formatterDate(progress.getDataFaturamento(), "yyyy-MM-dd");
            if ((dataBanco.equals(dataParam)) 
                    && ((progress.getFaturamento().equals(Faturamento.EF)) 
                    || (progress.getFaturamento().equals(Faturamento.FO)))) {
                testesMesPrevisao.add(progress);
//                estimadaTestPrevisao += progress.getId().getAtividade().getContagemEstimada() * .25;
//                detalhadaTestPrevisao += progress.getId().getAtividade().getContagemDetalhada()* .25;
            }
            if ((dataBancoAux.equals(dataParam)) 
                    && (progress.getFaturamento().equals(Faturamento.FO))) {
                testesMesFaturado.add(progress);
                estimadaTestFaturado += progress.getId().getAtividade().getContagemEstimada() * .25;
                detalhadaTestFaturado += progress.getId().getAtividade().getContagemDetalhada()* .25;
            }
        }
        
        Double estimadaServFaturado = 0.0;
        Double detalhadaServFaturado = 0.0;
        for (ProgressoAtividade progress : servicosAno) {
            String dataBancoAux = DateUtil.formatterDate(progress.getDataFaturamento(), "yyyy-MM-dd");
            if ((dataBancoAux.equals(dataParam)) 
                    && (progress.getFaturamento().equals(Faturamento.FO))) {
                estimadaServFaturado += progress.getId().getAtividade().getContagemEstimada();
                detalhadaServFaturado += progress.getId().getAtividade().getContagemDetalhada();
            }
        }

        Double contagemEstimada = estimadaLevFaturado + estimadaDevFaturado + estimadaAliFaturado + estimadaTestFaturado + estimadaServFaturado;
        Double contagemDetalhada = detalhadaLevFaturado + detalhadaDevFaturado + detalhadaAliFaturado + detalhadaTestFaturado + detalhadaServFaturado;

        //        PARTE 1
        Double totalContratoEstimada = contagemEstimada * paramContrato;
        Double totalRepasseEstimada = contagemEstimada * paramRepasse;
        Double totalContratoDetalhada = contagemDetalhada * paramContrato;
        Double totalRepasseDetalhada = contagemDetalhada * paramRepasse;
        int qtdLevantamentoLabelPrevisao = levantamentosMesPrevisao.size();
        int qtdLevantamentoLabelFaturado = levantamentosMesFaturado.size();
        int qtdDesenvolvimentoLabelPrevisao = desenvolvimenetosMesPrevisao.size();
        int qtdDesenvolvimentoLabelFaturado = desenvolvimenetosMesFaturado.size();
        int qtdTesteLabelPrevisao = testesMesPrevisao.size();
        int qtdTesteLabelFaturado = testesMesFaturado.size();

        repasse = totalRepasseDetalhada;
        contrato = totalContratoDetalhada;

        lbTitulo.setText(new SimpleDateFormat("MM - MMMM").format(inicio));
        lbLevantamento.setText("Levantamentos: " + qtdLevantamentoLabelPrevisao + "/" + qtdLevantamentoLabelFaturado);
        lbDesenvolvimento.setText("Desenvolvimento: " + qtdDesenvolvimentoLabelPrevisao + "/" + qtdDesenvolvimentoLabelFaturado);
        lbTeste.setText("Testes/Homologação: " + qtdTesteLabelPrevisao  + "/" + qtdTesteLabelFaturado);

        lbPfEstimada.setText("Pontos de função: " + DoubleConverter.doubleToString(contagemEstimada));
        lbValorContratoEstimada.setText("Valor Contrato: " + DoubleConverter.doubleToString(totalContratoEstimada));
        lbValorRepasseEstimada.setText("Valor Repasse: " + DoubleConverter.doubleToString(totalRepasseEstimada));

        lbPfDetalhada.setText("Pontos de função: " + DoubleConverter.doubleToString(contagemDetalhada));
        lbValorContratoDetalhada.setText("Valor Contrato: " + DoubleConverter.doubleToString(totalContratoDetalhada));
        lbValorRepasseDetalhada.setText("Valor Repasse: " + DoubleConverter.doubleToString(totalRepasseDetalhada));

//        PARTE 2
        custoMes = (Custo) param.get("Custo");
        if (null == projetoObject.getDescricao()) {
            lbProjeto.setText("Projeto: Todos");
        } else {
            lbProjeto.setText("Projeto: " + projetoObject.getDescricao());
        }
        if (!(0.0 == contagemEstimada) && !(0.0 == contagemDetalhada)) {
            lbCustoPlanejado.setText("Téc. Planejado: " + DoubleConverter.doubleToString(custoMes.getCustoTecnicoPlanejado()));
            lbCustoRealizado.setText("Téc. Realizado: " + DoubleConverter.doubleToString(custoMes.getCustoTecnicoRealizado()));

            Double resultadoTecnico = 0.0;
            if (custoMes.getCustoTecnicoRealizado() > 0.0) {
                resultadoTecnico = totalRepasseDetalhada - custoMes.getCustoTecnicoRealizado();
                lbResultadoTecnico.setText("Resultado Técnico: " + DoubleConverter.doubleToString(resultadoTecnico));
            } else {
                resultadoTecnico = totalRepasseDetalhada - custoMes.getCustoTecnicoPlanejado();
                lbResultadoTecnico.setText("Resultado Técnico: " + DoubleConverter.doubleToString(resultadoTecnico));
            }

            Double custoComercial = 0.0;
            Double porcentagem = 26.5;
            Double resultadoComercial = 0.0;
            Double resultadoCombinado = 0.0;
            Double rentabilidade = 0.0;
            lbRentabilidade.setText("Rentabilidade: " + DoubleConverter.doubleToString(rentabilidade) + "%");
            if (contagemDetalhada > 0.0) {
                lbTipoPF.setText("Tipo de Contagem PF: Detalhada");
                lbRepasse.setText("Repasse: " + DoubleConverter.doubleToString(totalRepasseDetalhada));
                lbFaturamento.setText("Faturamento: " + DoubleConverter.doubleToString(totalContratoDetalhada));

                custoComercial = ((totalContratoDetalhada * porcentagem) / 100) + totalRepasseDetalhada;
                lbCustoComercial.setText("Custo Comercial: " + DoubleConverter.doubleToString(custoComercial));
                resultadoComercial = totalContratoDetalhada - custoComercial;
                lbResultadoComercial.setText("Resultado Comercial: " + DoubleConverter.doubleToString(resultadoComercial));
                resultadoCombinado = resultadoTecnico + resultadoComercial;
                lbResultadoCombinado.setText("Resultado Combinado: " + DoubleConverter.doubleToString(resultadoCombinado));

                if (totalContratoDetalhada > 0) {
                    rentabilidade = (resultadoCombinado / totalContratoDetalhada) * 100;
                    lbRentabilidade.setText("Rentabilidade: " + DoubleConverter.doubleToString(rentabilidade) + "%");
                }
            } else {
                lbTipoPF.setText("Tipo de Contagem PF: Estimada");
                lbRepasse.setText("Repasse: " + DoubleConverter.doubleToString(totalRepasseEstimada));
                lbFaturamento.setText("Faturamento: " + DoubleConverter.doubleToString(totalContratoEstimada));

                custoComercial = ((totalContratoEstimada * porcentagem) / 100) + totalRepasseEstimada;
                lbCustoComercial.setText("Custo Comercial: " + DoubleConverter.doubleToString(custoComercial));
                resultadoComercial = totalContratoEstimada - custoComercial;
                lbResultadoComercial.setText("Resultado Comercial: " + DoubleConverter.doubleToString(resultadoComercial));
                resultadoCombinado = resultadoTecnico + resultadoComercial;
                lbResultadoCombinado.setText("Resultado Combinado: " + DoubleConverter.doubleToString(resultadoCombinado));

                if (totalContratoEstimada > 0) {
                    rentabilidade = (resultadoCombinado / totalContratoEstimada) * 100;
                    lbRentabilidade.setText("Rentabilidade: " + DoubleConverter.doubleToString(rentabilidade) + "%");
                }
            }
        }else{
            lbCustoPlanejado.setText("Téc. Planejado: ");
            lbCustoRealizado.setText("Téc. Realizado: ");
            lbResultadoTecnico.setText("Resultado Técnico: ");
            lbRentabilidade.setText("Rentabilidade: ");
            lbTipoPF.setText("Tipo de Contagem PF: ");
            lbCustoComercial.setText("Custo Comercial: ");
            lbResultadoComercial.setText("Resultado Comercial: ");
            lbResultadoCombinado.setText("Resultado Combinado: ");
            lbRepasse.setText("Repasse: ");
            lbFaturamento.setText("Faturamento: ");
        }
    }

    @FXML
    private void labelAtividadeActionEvent() {
        if (null == projetoObject.getDescricao()) {
            MessageUtil.messageInformation("Favor pesquisar com um projeto selecionado para visualizar atividades.");
        } else {
            GerenciadorDeJanela gerenciadorDeJanela = new GerenciadorDeJanela();
            ScrollPane scrollPane = (ScrollPane) gerenciadorDeJanela.procurarComponente("spContainer", apPrincipal);
            params.put("dataInicio", inicio);
            params.put("projetoObject", projetoObject);
            scrollPane.setContent(gerenciadorDeJanela.carregarComponente("PesquisarAtividade", params));
        }
    }

    @Override
    public void buildAnalista() {
        lbValorRepasseDetalhada.setVisible(false);
        lbValorRepasseEstimada.setVisible(false);
        gpLayout.getChildren().remove(vbPlanejamento);
    }

    @Override
    public void buildBancoDados() {
        lbValorRepasseDetalhada.setVisible(false);
        lbValorRepasseEstimada.setVisible(false);
        gpLayout.getChildren().remove(vbPlanejamento);
    }

    @Override
    public void buildBDMG() {
        lbValorRepasseDetalhada.setVisible(false);
        lbValorRepasseEstimada.setVisible(false);
        gpLayout.getChildren().remove(vbPlanejamento);
    }

    @Override
    public void buildDesenvolvedor() {
        lbValorRepasseDetalhada.setVisible(false);
        lbValorRepasseEstimada.setVisible(false);
        gpLayout.getChildren().remove(vbPlanejamento);
    }

    @Override
    public void buildGerente() {
        lbValorRepasseDetalhada.setVisible(true);
        lbValorRepasseEstimada.setVisible(true);
    }

    @Override
    public void buildQualidade() {
        lbValorRepasseDetalhada.setVisible(false);
        lbValorRepasseEstimada.setVisible(false);
        gpLayout.getChildren().remove(vbPlanejamento);
    }

}
