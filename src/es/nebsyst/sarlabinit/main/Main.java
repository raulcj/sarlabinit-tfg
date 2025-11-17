/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.nebsyst.sarlabinit.main;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * En esta clase Main principal se aplica el patrón Singleton para evitar que exista más de
 * una instancia de una misma ventana
 * 
 * PTE: ver si es posible crear un Main Stage que no sea panelEJSAPPListStage
 * 
 * @author alumno
 */
public class Main extends Application {
    
    public static void main(String[] args) {
        launch();
    }
       
    @Override
    public void start(Stage panelMainStage) throws IOException {    
        // Cargar modulo principal
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/PanelMain.fxml"));    
        Scene scene = new Scene(fxmlLoader.load()); 
        // Inicializa las clases gestoras Manager
        FXMLLoaderManager fxmlLoaderManager = FXMLLoaderManager.getInstance();
        StageManager stageManager = StageManager.getInstance();    
        // Registra los FXML de cada vista
        // Clase Main
        fxmlLoaderManager.registerLoader("PanelMain", fxmlLoader);
        // es.nebsyst.sarlabinit
        fxmlLoaderManager.registerLoader("PanelEJSAppList", getPanelEJSAppListLoader());
        fxmlLoaderManager.registerLoader("PanelJarProcess", getPanelJarProcessLoader());
        // es.uhu.sarlab.discovery  
        fxmlLoaderManager.registerLoader("PanelListGateway", getPanelListGatewayLoader());
        fxmlLoaderManager.registerLoader("PanelConectionLocalhost", getPanelConectionLocalhostLoader());
        fxmlLoaderManager.registerLoader("PanelEditConfigLocal", getPanelEditConfigLocalLoader());
        fxmlLoaderManager.registerLoader("PanelConsole", getPanelConsoleLoader());
        fxmlLoaderManager.registerLoader("PanelConectionExperience", getPanelConectionExperienceLoader());
        fxmlLoaderManager.registerLoader("PanelNodoFrowardingL", getPanelNodoFrowardingLLoader());
        fxmlLoaderManager.registerLoader("PanelClientServerName", getDiscoveryLoader());
        // Registra los Stage
        // es.nebsyst.sarlabinit
        stageManager.registerStage("PanelEJSAppList", getPanelEJSAppListStage());
        stageManager.registerStage("PanelJarProcess", getPanelJarProcessStage());
        // es.uhu.sarlab.discovery
        stageManager.registerStage("PanelListGateway", getPanelListGatewayStage());
        stageManager.registerStage("PanelConectionLocalhost", getPanelConectionLocalhostStage());
        stageManager.registerStage("PanelEditConfigLocal", getPanelEditConfigLocalStage());
        stageManager.registerStage("PanelConsole", getPanelConsoleStage());
        stageManager.registerStage("PanelConectionExperience", getPanelConectionExperienceStage());
        stageManager.registerStage("PanelNodoFrowardingL", getPanelNodoFrowardingLStage());
        stageManager.registerStage("PanelClientServerName", getDiscoveryStage());     
        // Añadir estilos
        addCss(scene);
        panelMainStage.setTitle("Servicio SARLAB");
        Image icono = new Image("file:src/es/nebsyst/sarlabinit/resource/sarlab.png");   
        panelMainStage.getIcons().add(icono);
        panelMainStage.setScene(scene);    
        // Registrar stage principal
        stageManager.registerStage("PanelMain", panelMainStage);
        panelMainStage.setResizable(false);
        panelMainStage.show();
        // Permite abrir las opciones del SystemTray mas de una vez
        Platform.setImplicitExit(false);
        // Cargar SystemTrayFX
        MySystemTrayFX mySystemTrayFX = new MySystemTrayFX(panelMainStage);
    }
    
    private void addCss(Scene scene) {
        if (scene.getStylesheets().isEmpty()) {
            String[] cssPaths = new String[] {
                "../../../styles/base.css",
                "../../../styles/utilities.css",
                "../../../styles/buttons.css",
                "../../../styles/inputs.css",
                "../../../styles/lists_tables.css",
                "../../../styles/containers.css",
                "../../../styles/extras.css",
            };
            for (String p : cssPaths) {
                URL url = getClass().getResource(p);
                if (url != null) {
                    scene.getStylesheets().add(url.toExternalForm());
                }
            }         
        }     
    }
    
    // SARLABINIT (componentes)
    public FXMLLoader getPanelEJSAppListLoader() {
        return new FXMLLoader(Main.class.getResource("../view/PanelEJSAppList.fxml"));
    }
    
    public Stage getPanelEJSAppListStage() {
        Stage stage = new Stage();
        Image icono = new Image("file:src/es/nebsyst/sarlabinit/resource/execjar128.png");   
        stage.getIcons().add(icono);
        return stage;
    }

    public FXMLLoader getPanelJarProcessLoader() { 
        return new FXMLLoader(Main.class.getResource("../view/PanelJarProcess.fxml"));
    }
    
    public Stage getPanelJarProcessStage() {
        Stage stage = new Stage(); 
        Image icono = new Image("file:src/es/nebsyst/sarlabinit/resource/execjar128.png");   
        stage.getIcons().add(icono);
        return stage;
    }
    
    // SARLAB DISCOVERY (componentes)
    
    public FXMLLoader getDiscoveryLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelClientServerName.fxml"));
    }
    
    public Stage getDiscoveryStage() {
        Stage stage = new Stage();      
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        return stage;
    }

     public FXMLLoader getPanelListGatewayLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelListGateway.fxml"));
    }
     
    public Stage getPanelListGatewayStage() {
        Stage stage = new Stage();
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        return stage;
    }
    
    public FXMLLoader getPanelConectionLocalhostLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConectionLocalhost.fxml"));
    }
    
    public Stage getPanelConectionLocalhostStage() {
        Stage stage = new Stage();        
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        return stage;
    }
    
    public FXMLLoader getPanelConsoleLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConsole.fxml"));
    }
    
    public Stage getPanelConsoleStage() {
        Stage stage = new Stage();         
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        return stage;
    }
    
    public FXMLLoader getPanelEditConfigLocalLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelEditConfigLocal.fxml"));
    }
    
    public Stage getPanelEditConfigLocalStage() {
        Stage stage = new Stage();
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        return stage;
    }
    
    public FXMLLoader getPanelConectionExperienceLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConectionExperience.fxml"));
    }
         
    public Stage getPanelConectionExperienceStage() {
        Stage stage = new Stage();      
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        return stage;
    }
    
    public FXMLLoader getPanelNodoFrowardingLLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelNodoFrowardingL.fxml"));
    }
    
    public Stage getPanelNodoFrowardingLStage() throws IOException {
        Stage stage = new Stage();  
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        return stage;
    }
    
}