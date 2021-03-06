/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.control.dao.CustoDAO;
import br.com.stefanini.model.entity.Custo;
import br.com.stefanini.model.entity.Projeto;
import br.com.stefanini.model.util.DoubleConverter;
import br.com.stefanini.model.util.StringUtil;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author higo
 */
public class CustoController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    private Date dtInclusao;
    @FXML
    private TextField tfCustoPlanejado;
    @FXML
    private TextField tfCustoRealizado;
    private Custo custo;
    private Projeto projeto;
    private Stage stage;
    private GerenciadorDeJanela gerenciadorDeJanela;
    Map<String,Object> params = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apPrincipal.sceneProperty().addListener((ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) -> {
            params = (Map) apPrincipal.getUserData();
            gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        });
    }

    public void teste(){
        params = (Map) apPrincipal.getUserData();
        gerenciadorDeJanela = (GerenciadorDeJanela) params.get("gerenciador");
        dtInclusao = (Date) params.get("dtInclusao");
        custo = (Custo) params.get("custo");
        projeto = (Projeto) params.get("projeto");
        stage = (Stage) params.get("modalStage");
        if(null != custo.getCustoTecnicoPlanejado()){
            tfCustoPlanejado.setText(String.valueOf(custo.getCustoTecnicoPlanejado()));
        }else{
            tfCustoPlanejado.setText("0.0");
        }
        
        if(null != custo.getCustoTecnicoRealizado()){
            tfCustoRealizado.setText(String.valueOf(custo.getCustoTecnicoRealizado()));
        }else{
            tfCustoRealizado.setText("0.0");
        }
    }
    
    @FXML
    private void salvarCusto() {
        custo.setDtInclusao(dtInclusao);
        custo.setProjeto(projeto);
        if(StringUtil.isEmpty(tfCustoPlanejado.getText())){
            custo.setCustoTecnicoPlanejado(0.0);
        }else{
            String auxPlanejado = tfCustoPlanejado.getText();
            auxPlanejado.replaceAll("[,]*[.]*", "");
            custo.setCustoTecnicoPlanejado(DoubleConverter.stringToDouble(auxPlanejado));
        }
        
        if(StringUtil.isEmpty(tfCustoRealizado.getText())){
            custo.setCustoTecnicoRealizado(0.0);
        }else{
            String auxRealizado = tfCustoRealizado.getText();
            auxRealizado.replaceAll("[,]*[.]*", "");
            custo.setCustoTecnicoRealizado(DoubleConverter.stringToDouble(auxRealizado));
        }
        if(null == custo.getId()){
            CustoDAO.getInstance().salvar(custo);
            new Alert(Alert.AlertType.INFORMATION, "Custo cadastro com sucesso.").show();
            stage.close();
        }else{
            CustoDAO.getInstance().editar(custo);
            new Alert(Alert.AlertType.INFORMATION, "Custo atualizado com sucesso.").show();
            stage.close();
        }
        params.put("CustoAux", custo); 
    }
}
