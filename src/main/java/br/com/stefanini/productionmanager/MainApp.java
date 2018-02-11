package br.com.stefanini.productionmanager;

import br.com.stefanini.control.GerenciadorDeJanela;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Starting database now
        GerenciadorDeJanela gerenciadorDeJanela = new GerenciadorDeJanela();
        stage.setOnCloseRequest((WindowEvent event) -> {
            Platform.exit();
            System.exit(0);
        });
//        
        //Atalho para entrar direto no projeot
//        stage.setMaximized(true);
//        gerenciadorDeJanela.mostrarJanela(stage, gerenciadorDeJanela.carregarComponente("PainelDeControle"), "Início").show();
        //
        gerenciadorDeJanela.mostrarJanela(stage, gerenciadorDeJanela.carregarComponente("Login"), "Autenticação").show();
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
