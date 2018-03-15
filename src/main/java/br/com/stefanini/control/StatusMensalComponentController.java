/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.AtividadeDAO;
import br.com.stefanini.control.dao.CustoDAO;
import br.com.stefanini.control.dao.ParametroDAO;
import br.com.stefanini.control.dao.ProgressoAtividadeDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.Custo;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.enuns.TipoParametro;
import br.com.stefanini.model.util.DateUtil;
import br.com.stefanini.model.util.DoubleConverter;
import java.net.URL;
import java.text.SimpleDateFormat;
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
    @FXML
    private Label lbTotal;
    @FXML
    private Label lbLevantamento;
    @FXML
    private Label lbDesenvolvimento;
    @FXML
    private Label lbTeste;
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
    
    Map<String,Object> params = new HashMap<>();

//    private Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
//            stage = (Stage) apPrincipal.getScene().getWindow();
            gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
            params = (Map) apPrincipal.getUserData();
        });
    }
    
    @FXML
    private void btAdicionarAction() {
        btAddCusto.setTooltip(new Tooltip("Atualizar Custos"));
        if(null != custoMes.getId()){
            Custo custo = new CustoDAO().pegarPorId(custoMes.getId());
            params.put("custo", custo);
        }else{
            Custo custo = new Custo();
            params.put("custo", custo);
        }
        params.put("dtInclusao", inicio);
        gerenciadorDeJanela.abrirModal("CustoModal", params, "Custo");
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        Custo custoAux = (Custo) params.get("CustoAux");
//        Custo custoAux = new CustoDAO().pegarPorId(custoMes.getId());
        lbCustoPlanejado.setText("Custo Técnico Planejado: " + DoubleConverter.doubleToString(custoAux.getCustoTecnicoPlanejado()));
        lbCustoRealizado.setText("Custo Técnico Realizado: " + DoubleConverter.doubleToString(custoAux.getCustoTecnicoRealizado()));
    
        Double resultadoTecnico = 0.0;
            if(custoAux.getCustoTecnicoRealizado() > 0.0){
                resultadoTecnico = repasse-custoAux.getCustoTecnicoRealizado();
                lbResultadoTecnico.setText("Resultado Técnico: " + DoubleConverter.doubleToString(resultadoTecnico));
            }else{
                resultadoTecnico = repasse-custoAux.getCustoTecnicoPlanejado();
                lbResultadoTecnico.setText("Resultado Técnico: " + DoubleConverter.doubleToString(resultadoTecnico));
            }
        Double porcentagem = 26.5;
        Double custoComercial = ((contrato * porcentagem)/100)+repasse;
        Double resultadoComercial = contrato-custoComercial;
        Double resultadoCombinado = resultadoTecnico+resultadoComercial;
        lbResultadoCombinado.setText("Resultado Combinado: " + DoubleConverter.doubleToString(resultadoCombinado));

    }

    public void teste(){
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        Map param = (Map) apPrincipal.getUserData();
        inicio = (Date) param.get("data");
        idProjeto = (String) param.get("projeto");
        idModulo = (String) param.get("modulo");
        idPacote = (String) param.get("pacote");
        
        //        PARTE 1
        AtividadeDAO daoAtv = new AtividadeDAO();
        List<Atividade> atividades = daoAtv.buscarAtividade(idProjeto, idModulo, idPacote, DateUtil.truncateDate(inicio));
        Double contagemEstimada = 0.0;
        Double contagemDetalhada = 0.0;            
        for(Atividade atv : atividades){
            contagemEstimada += atv.getContagemEstimada();
            contagemDetalhada += atv.getContagemDetalhada();
        }
        Double totalContratoEstimada = 0.0;
        Double totalRepasseEstimada = 0.0;
        Double totalContratoDetalhada = 0.0;
        Double totalRepasseDetalhada = 0.0;
        Long qtdLevantamento = 0l;
        Long qtdDesenvolvimento = 0l;
        Long qtdTeste = 0l;
        if(!atividades.isEmpty()){
            ParametroDAO daoParam = new ParametroDAO();
            totalContratoEstimada = contagemEstimada * daoParam.buscaParametroRecente(TipoParametro.CONTRATO).getValor();
            totalRepasseEstimada = contagemEstimada * daoParam.buscaParametroRecente(TipoParametro.REPASSE).getValor();

            totalContratoDetalhada = contagemDetalhada * daoParam.buscaParametroRecente(TipoParametro.CONTRATO).getValor();
            totalRepasseDetalhada = contagemDetalhada * daoParam.buscaParametroRecente(TipoParametro.REPASSE).getValor();

            ProgressoAtividadeDAO daoProgress = new ProgressoAtividadeDAO();
            qtdLevantamento = daoProgress.pegarProgressoAtividade(inicio, TipoAtividade.LE, idProjeto, idModulo, idPacote);
            qtdDesenvolvimento = daoProgress.pegarProgressoAtividade(inicio, TipoAtividade.DE, idProjeto, idModulo, idPacote);
            qtdTeste = daoProgress.pegarProgressoAtividade(inicio, TipoAtividade.TE, idProjeto, idModulo, idPacote); 
            repasse = totalRepasseDetalhada;
            contrato = totalContratoDetalhada;
        }


        lbTitulo.setText(new SimpleDateFormat("MM - MMMM").format(inicio));
        lbTotal.setText(" - Total: " + String.valueOf(atividades.size()));
        lbLevantamento.setText(" - Levantamentos 100%: " + qtdLevantamento);
        lbDesenvolvimento.setText(" - Desenvolvimento 100% : " + qtdDesenvolvimento);
        lbTeste.setText(" - Testes/Homologação 100% : " + qtdTeste);

        lbPfEstimada.setText("Pontos de função: " + contagemEstimada);
        lbValorContratoEstimada.setText("Valor Contrato: " + DoubleConverter.doubleToString(totalContratoEstimada));
        lbValorRepasseEstimada.setText("Valor Repasse: " + DoubleConverter.doubleToString(totalRepasseEstimada));

        lbPfDetalhada.setText("Pontos de função: " + contagemDetalhada);
        lbValorContratoDetalhada.setText("Valor Contrato: " + DoubleConverter.doubleToString(totalContratoDetalhada));
        lbValorRepasseDetalhada.setText("Valor Repasse: " + DoubleConverter.doubleToString(totalRepasseDetalhada));
        
//        PARTE 2
        custoMes = (Custo) param.get("Custo");
        if(!(0.0 == contagemEstimada) && !(0.0 == contagemDetalhada)){
            lbCustoPlanejado.setText("Custo Técnico Planejado: " + DoubleConverter.doubleToString(custoMes.getCustoTecnicoPlanejado()));
            lbCustoRealizado.setText("Custo Técnico Realizado: " + DoubleConverter.doubleToString(custoMes.getCustoTecnicoRealizado()));

            Double resultadoTecnico = 0.0;
            if(custoMes.getCustoTecnicoRealizado() > 0.0){
                resultadoTecnico = totalRepasseDetalhada-custoMes.getCustoTecnicoRealizado();
                lbResultadoTecnico.setText("Resultado Técnico: " + DoubleConverter.doubleToString(resultadoTecnico));
            }else{
                resultadoTecnico = totalRepasseDetalhada-custoMes.getCustoTecnicoPlanejado();
                lbResultadoTecnico.setText("Resultado Técnico: " + DoubleConverter.doubleToString(resultadoTecnico));
            }

            Double custoComercial = 0.0;
            Double porcentagem = 26.5;
            Double resultadoComercial = 0.0;
            Double resultadoCombinado = 0.0;
            Double rentabilidade = 0.0;
            lbRentabilidade.setText("Rentabilidade: " + DoubleConverter.doubleToString(rentabilidade)+"%");
            if(contagemDetalhada > 0.0){
                lbTipoPF.setText("Tipo de Contagem PF: Detalhada");
                lbRepasse.setText("Repasse: " + DoubleConverter.doubleToString(totalRepasseDetalhada));
                lbFaturamento.setText("Faturamento: " + DoubleConverter.doubleToString(totalContratoDetalhada));

                custoComercial = ((totalContratoDetalhada * porcentagem)/100)+totalRepasseDetalhada;
                lbCustoComercial.setText("Custo Comercial: " +  DoubleConverter.doubleToString(custoComercial));
                resultadoComercial = totalContratoDetalhada-custoComercial;
                lbResultadoComercial.setText("Resultado Comercial: " + DoubleConverter.doubleToString(resultadoComercial));
                resultadoCombinado = resultadoTecnico+resultadoComercial;
                lbResultadoCombinado.setText("Resultado Combinado: " + DoubleConverter.doubleToString(resultadoCombinado));

                if(totalContratoDetalhada > 0){
                    rentabilidade = (resultadoCombinado/totalContratoDetalhada)*100;
                    lbRentabilidade.setText("Rentabilidade: " + DoubleConverter.doubleToString(rentabilidade)+"%");
                }   
            }else{
                lbTipoPF.setText("Tipo de Contagem PF: Estimada");
                lbRepasse.setText("Repasse: " + DoubleConverter.doubleToString(totalRepasseEstimada));
                lbFaturamento.setText("Faturamento: " + DoubleConverter.doubleToString(totalContratoEstimada));

                custoComercial = ((totalContratoEstimada * porcentagem)/100)+totalRepasseEstimada;
                lbCustoComercial.setText("Custo Comercial: " +  DoubleConverter.doubleToString(custoComercial));
                resultadoComercial = totalContratoEstimada-custoComercial;
                lbResultadoComercial.setText("Resultado Comercial: " + DoubleConverter.doubleToString(resultadoComercial));
                resultadoCombinado = resultadoTecnico+resultadoComercial;
                lbResultadoCombinado.setText("Resultado Combinado: " + DoubleConverter.doubleToString(resultadoCombinado));

                if(totalContratoEstimada > 0){
                    rentabilidade = (resultadoCombinado/totalContratoEstimada)*100;
                    lbRentabilidade.setText("Rentabilidade: " + DoubleConverter.doubleToString(rentabilidade)+"%");
                }
            }
        }   
    }
    
    @FXML
    private void labelAtividadeActionEvent() {
        GerenciadorDeJanela gerenciadorDeJanela = new GerenciadorDeJanela();
        ScrollPane scrollPane = (ScrollPane) gerenciadorDeJanela.procurarComponente("spContainer", apPrincipal);
        params.put("dataInicio", inicio);
        scrollPane.setContent(gerenciadorDeJanela.carregarComponente("PesquisarAtividade", params));
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
