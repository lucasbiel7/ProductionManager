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
import br.com.stefanini.model.entity.Parametro;
import br.com.stefanini.model.enuns.TipoAtividade;
import br.com.stefanini.model.enuns.TipoParametro;
import br.com.stefanini.model.util.DateUtil;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class StatusMensalComponentController implements Initializable {
    
    private Date inicio;
    private Stage stage;
    private String idProjeto;
    private String idModulo;
    private String idPacote;

    @FXML
    private VBox vbVisible;
            
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            stage = (Stage) apPrincipal.getScene().getWindow();
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
            ParametroDAO daoParam = new ParametroDAO();
            Double totalContratoEstimada = contagemEstimada * daoParam.buscaParametroRecente(TipoParametro.CONTRATO).getValor();
            Double totalRepasseEstimada = contagemEstimada * daoParam.buscaParametroRecente(TipoParametro.REPASSE).getValor();
            
            Double totalContratoDetalhada = contagemDetalhada * daoParam.buscaParametroRecente(TipoParametro.CONTRATO).getValor();
            Double totalRepasseDetalhada = contagemDetalhada * daoParam.buscaParametroRecente(TipoParametro.REPASSE).getValor();
            
            ProgressoAtividadeDAO daoProgress = new ProgressoAtividadeDAO();
            Long qtdLevantamento = daoProgress.pegarProgressoAtividade(inicio, TipoAtividade.LE, idProjeto, idModulo, idPacote);
            Long qtdDesenvolvimento = daoProgress.pegarProgressoAtividade(inicio, TipoAtividade.DE, idProjeto, idModulo, idPacote);
            Long qtdTeste = daoProgress.pegarProgressoAtividade(inicio, TipoAtividade.TE, idProjeto, idModulo, idPacote);
            
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
            
//            if(contagemDetalhada != 0.0){
//                vbVisible.getChildren().get(1).setVisible(false);
//            }
        });
    }

    @FXML
    private void labelAtividadeActionEvent() {
        GerenciadorDeJanela gerenciadorDeJanela = new GerenciadorDeJanela();
        ScrollPane scrollPane = (ScrollPane) gerenciadorDeJanela.procurarComponente("spContainer", apPrincipal);
        scrollPane.setContent(gerenciadorDeJanela.carregarComponente("PesquisarAtividade", inicio));
    }
}
