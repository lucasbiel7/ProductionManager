/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.productionmanager.MainApp;
import java.io.IOException;
import java.util.Map;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.annotations.common.util.impl.LoggerFactory;

/**
 *
 * @author lucas
 */
public class GerenciadorDeJanela {

    public static final String PACOTE_VIEW = "/br/com/stefanini/view/";
    private Scene scene;
    private MainApp main = null;
    
    public GerenciadorDeJanela() {
    }

    public GerenciadorDeJanela(Scene scene) {
        this.scene = scene;
    }

    public Stage mostrarJanela(Stage primaryStage, Parent parent, String title) {
        primaryStage.setTitle(title);
        scene = new Scene(parent);
        primaryStage.setScene(scene);
        return primaryStage;
    }

    public void abrirModal(String key, Map<String,Object> params, String title) {
        Stage stage = new Stage();                
        stage.setTitle(title);
        params.put("modalStage", stage);
        Parent parent = carregarComponente(key, params);
        if(parent.getScene() == null){
            scene = new Scene(parent);
        }else{
            scene = parent.getScene();    
        }                
        stage.setScene(scene);
        stage.initOwner(main.mainStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
    
    public void trocarCena(Parent parent, String key) {
        if (parent != null) {
            Object o = getMain().loaders.get(key).getController();
            if(o instanceof PainelDeControleController){
                ((PainelDeControleController)o).teste();
            }
//            getMain().mainStage.setScene(parent.getScene());
//            getMain().mainStage.show();
//            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));
//            fadeTransition.setNode(scene.getRoot());
//            fadeTransition.setFromValue(0);
//            fadeTransition.setToValue(1);
//            fadeTransition.play();
            mostrarJanela(getMain().mainStage, parent, key);
        }
    }

    public Parent carregarComponente(String tela) {
        return getMain().componentes.get(tela);
//        try {
//            return FXMLLoader.load(getClass().getResource(PACOTE_VIEW + "" + tela + ".fxml"));
//        } catch (IOException e) {
//            LoggerFactory.logger(this.getClass()).error(e);
//            return null;
//        }
    }

    public Parent carregarComponente(String tela, Object object) {
        Parent parent = carregarComponente(tela);
        if (parent != null) {
            parent.setUserData(object);
            Object o = getMain().loaders.get(tela).getController();
            if(o instanceof StatusMensalComponentController){
                ((StatusMensalComponentController)o).teste();
            }
            if(o instanceof PesquisarAtividadeController){
                ((PesquisarAtividadeController)o).teste();
            }
            if(o instanceof ManterAtividadeController){
                ((ManterAtividadeController)o).teste();
            }
            if(o instanceof FaturarAtividadeController){
                ((FaturarAtividadeController)o).teste();
            }
            if(o instanceof AlterarEscopoAtividadeController){
                ((AlterarEscopoAtividadeController)o).teste();
            }
            if(o instanceof VisualizarDetalheAtividadeController){
               ((VisualizarDetalheAtividadeController)o).teste();
            }
            
        }
        return parent;
    }

    public Parent procurarComponente(String id, Parent component) {
        if (id.equals(component.getId())) {
            return component;
        } else {
            return procurarComponente(id, component.getParent());
        }
    }

    /**
     * @return the main
     */
    public MainApp getMain() {
        return main;
    }

    /**
     * @param main the main to set
     */
    public void setMain(MainApp main) {
        this.main = main;
    }
}
