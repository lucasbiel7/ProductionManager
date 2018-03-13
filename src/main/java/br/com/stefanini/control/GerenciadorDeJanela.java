/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.productionmanager.MainApp;
import java.util.Map;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(PACOTE_VIEW+"image/ico.png")));
        scene = new Scene(parent);
        primaryStage.setScene(scene);
        return primaryStage;
    }

    public void abrirModal(String key, Map<String,Object> params, String title) {
        Stage stage = new Stage(); 
        stage.getIcons().add(new Image(getClass().getResourceAsStream(PACOTE_VIEW+"image/ico.png")));
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
            mostrarJanela(getMain().mainStage, parent, key);
        }
    }

    public Parent carregarComponente(String tela) {
        return getMain().componentes.get(tela);
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
            
            if(o instanceof AtualizarUsuarioController){
               ((AtualizarUsuarioController)o).teste();
            }
            
            if(o instanceof CustoController){
                ((CustoController)o).teste();
            }
            
            if(o instanceof ControllerBase){   
                GerenciadorDeJanela gen = ((GerenciadorDeJanela) ((Map<String,Object>)object).get("gerenciador"));
                if(gen!=null && gen.getMain().user!=null){
                    ((ControllerBase)o).validarPermissoes(gen.getMain().user.getPerfil());
                }
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
