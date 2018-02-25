package br.com.stefanini.productionmanager;

import br.com.stefanini.control.GerenciadorDeJanela;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

    public static Map<String,Parent> componentes = new HashMap<String, Parent>();   
    public static Map<String,FXMLLoader> loaders = new HashMap<String, FXMLLoader>(); 
    public static Stage mainStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        //Starting database now
        GerenciadorDeJanela gerenciadorDeJanela = new GerenciadorDeJanela();
        stage.setOnCloseRequest((WindowEvent event) -> {
            Platform.exit();
            System.exit(0);
        });        
        mainStage = stage;
        gerenciadorDeJanela.setMain(this);
        inicializar(gerenciadorDeJanela);
        
//        
        //Atalho para entrar direto no projeot
//        stage.setMaximized(true);
//        gerenciadorDeJanela.mostrarJanela(stage, gerenciadorDeJanela.carregarComponente("PainelDeControle"), "Início").show();
        //Profile de produção
        gerenciadorDeJanela.mostrarJanela(stage, gerenciadorDeJanela.carregarComponente("Login"), "Autenticação").show();

        
    }

    private void inicializar(GerenciadorDeJanela gerenciadorDeJanela){       
        try {
            componentes.put("Login",init("Login",gerenciadorDeJanela,""));
            componentes.put("PainelDeControle",init("PainelDeControle",gerenciadorDeJanela,""));        
            componentes.put("StatusMensalComponent0",init("StatusMensalComponent",gerenciadorDeJanela,"0")); 
            componentes.put("StatusMensalComponent1",init("StatusMensalComponent",gerenciadorDeJanela,"1"));            
            componentes.put("StatusMensalComponent2",init("StatusMensalComponent",gerenciadorDeJanela,"2"));  
            componentes.put("StatusMensalComponent3",init("StatusMensalComponent",gerenciadorDeJanela,"3"));  
            componentes.put("StatusMensalComponent4",init("StatusMensalComponent",gerenciadorDeJanela,"4"));  
            componentes.put("StatusMensalComponent5",init("StatusMensalComponent",gerenciadorDeJanela,"5"));  
            componentes.put("StatusMensalComponent6",init("StatusMensalComponent",gerenciadorDeJanela,"6"));  
            componentes.put("StatusMensalComponent7",init("StatusMensalComponent",gerenciadorDeJanela,"7"));  
            componentes.put("StatusMensalComponent8",init("StatusMensalComponent",gerenciadorDeJanela,"8"));  
            componentes.put("StatusMensalComponent9",init("StatusMensalComponent",gerenciadorDeJanela,"9"));  
            componentes.put("StatusMensalComponent10",init("StatusMensalComponent",gerenciadorDeJanela,"10"));  
            componentes.put("StatusMensalComponent11",init("StatusMensalComponent",gerenciadorDeJanela,"11"));  
            
            componentes.put("AlterarEscopoAtividade",init("AlterarEscopoAtividade",gerenciadorDeJanela,"")); 
            componentes.put("AtualizarUsuario",init("AtualizarUsuario",gerenciadorDeJanela,"")); 
            componentes.put("FaturarAtividade",init("FaturarAtividade",gerenciadorDeJanela,"")); 
            componentes.put("ManterAtividade",init("ManterAtividade",gerenciadorDeJanela,"")); 
            componentes.put("ManterAtuacao",init("ManterAtuacao",gerenciadorDeJanela,""));             
            componentes.put("ManterModulo",init("ManterModulo",gerenciadorDeJanela,"")); 
            componentes.put("ManterOrdemServico",init("ManterOrdemServico",gerenciadorDeJanela,""));             
            componentes.put("ManterPacote",init("ManterPacote",gerenciadorDeJanela,"")); 
            componentes.put("ManterParametro",init("ManterParametro",gerenciadorDeJanela,"")); 
            componentes.put("ManterPerfil",init("ManterPerfil",gerenciadorDeJanela,"")); 
            componentes.put("ManterProjetos",init("ManterProjetos",gerenciadorDeJanela,"")); 
            componentes.put("ManterUsuario",init("ManterUsuario",gerenciadorDeJanela,"")); 
            componentes.put("PesquisarAtividade",init("PesquisarAtividade",gerenciadorDeJanela,"")); 
            componentes.put("VisualizarDetalheAtividade",init("VisualizarDetalheAtividade",gerenciadorDeJanela,"")); 
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(componentes.size() + " ------ Must have 27"); 
    }
    
    private Parent init(String fxml,GerenciadorDeJanela gerenciadorDeJanela,String index) throws IOException{
        Map<String,Object> params = new HashMap<>();
        FXMLLoader l = new FXMLLoader(getClass().getResource(GerenciadorDeJanela.PACOTE_VIEW + fxml+".fxml"));
        Parent p = l.load();
        loaders.put(fxml+index, l);
        params.put("gerenciador", gerenciadorDeJanela);
        p.setUserData(params);
        return p;
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
