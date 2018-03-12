/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.AtividadeDAO;
import br.com.stefanini.control.dao.ParametroDAO;
import br.com.stefanini.control.dao.ProgressoAtividadeDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.enuns.TipoParametro;
import br.com.stefanini.model.util.DateUtil;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

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
    private TitledPane tpDetalhada;
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

    public void teste(){
        Map param = (Map) apPrincipal.getUserData();
        inicio = (Date) param.get("data");
        idProjeto = (String) param.get("projeto");
        idModulo = (String) param.get("modulo");
        idPacote = (String) param.get("pacote");
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
        }


        lbTitulo.setText(new SimpleDateFormat("MM - MMMM").format(inicio));
        lbTotal.setText(" - Total: " + String.valueOf(atividades.size()));
        lbLevantamento.setText(" - Levantamentos 100%: " + qtdLevantamento);
        lbDesenvolvimento.setText(" - Desenvolvimento 100% : " + qtdDesenvolvimento);
        lbTeste.setText(" - Testes/Homologação 100% : " + qtdTeste);

        lbPfEstimada.setText("Pontos de função: " + contagemEstimada);
        lbValorContratoEstimada.setText("Valor Contrato: " + totalContratoEstimada);
        lbValorRepasseEstimada.setText("Valor Repasse: " + totalRepasseEstimada);

        lbPfDetalhada.setText("Pontos de função: " + contagemDetalhada);
        lbValorContratoDetalhada.setText("Valor Contrato: " + totalContratoDetalhada);
        lbValorRepasseDetalhada.setText("Valor Repasse: " + totalRepasseDetalhada);
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
    }

    @Override
    public void buildBancoDados() {
        lbValorRepasseDetalhada.setVisible(false);
        lbValorRepasseEstimada.setVisible(false);
    }

    @Override
    public void buildBDMG() {
        lbValorRepasseDetalhada.setVisible(false);
        lbValorRepasseEstimada.setVisible(false);
    }

    @Override
    public void buildDesenvolvedor() {
        lbValorRepasseDetalhada.setVisible(false);
        lbValorRepasseEstimada.setVisible(false);
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
    }


}
