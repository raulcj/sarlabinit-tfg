/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.nebsyst.sarlabinit.main;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
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
        // Primera ventana prompt podría ser para seleccion de idioma (ES/GB)
        Locale.setDefault(Locale.UK); // Establece idioma
        System.out.println(Locale.getDefault().toString());
        launch();
    }
       
    @Override
    public void start(Stage panelEJSAppListStage) throws IOException {
        // Inicializa las clases gestoras Manager
        FXMLLoaderManager fxmlLoaderManager = FXMLLoaderManager.getInstance();
        StageManager stageManager = StageManager.getInstance();
        // Registra la jerarquia de FXML de cada vista
        // es.nebsyst.sarlabinit
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
        stageManager.registerStage("PanelJarProcess", getPanelJarProcessStage());
        // es.uhu.sarlab.discovery
        stageManager.registerStage("PanelListGateway", getPanelListGatewayStage());
        stageManager.registerStage("PanelConectionLocalhost", getPanelConectionLocalhostStage());
        stageManager.registerStage("PanelEditConfigLocal", getPanelEditConfigLocalStage());
        stageManager.registerStage("PanelConsole", getPanelConsoleStage());
        stageManager.registerStage("PanelConectionExperience", getPanelConectionExperienceStage());
        stageManager.registerStage("PanelNodoFrowardingL", getPanelNodoFrowardingLStage());
        stageManager.registerStage("PanelClientServerName", getDiscoveryStage());
        // Main stage (PanelEJSAppList)
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/PanelEJSAppList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());       
        panelEJSAppListStage.setTitle("");
        Image icono = new Image("file:src/es/nebsyst/sarlabinit/resource/execjar128.png");   
        panelEJSAppListStage.getIcons().add(icono);
        panelEJSAppListStage.setScene(scene);    
        // Permite abrir las opciones del SystemTray mas de una vez
        Platform.setImplicitExit(false);
        // Carga SystemTrayFX con el Main Stage 
        MySystemTrayFX mySystemTrayFX = new MySystemTrayFX(panelEJSAppListStage);
    }
    
    // SARLABINIT (componentes)
    
    public FXMLLoader getPanelJarProcessLoader() { 
        return new FXMLLoader(Main.class.getResource("../view/PanelJarProcess.fxml"));
    }
    
    public Stage getPanelJarProcessStage() throws IOException {
        Stage stage = new Stage();
        //Scene scene = new Scene(getPanelJarProcessLoader().load());
        stage.setTitle("Poner titulo en esta ventana? (PanelJarProcess)");
        Image icono = new Image("file:src/es/nebsyst/sarlabinit/resource/execjar128.png");   
        stage.getIcons().add(icono);
        //stage.setScene(scene);
        return stage;
    }
    
    // SARLAB DISCOVERY (componentes)
    
    public FXMLLoader getDiscoveryLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelClientServerName.fxml"));
    }
    
    public Stage getDiscoveryStage() throws IOException {
        Stage stage = new Stage(); 
        //Scene scene = new Scene(getDiscoveryLoader().load());       
        ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vistaTFG/Bundle_PanelClientServerName");
        stage.setTitle(bundle.getString("PanelClientServerName.title"));
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        //stage.setScene(scene);
        return stage;
    }

     public FXMLLoader getPanelListGatewayLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelListGateway.fxml"));
    }
     
    public Stage getPanelListGatewayStage() throws IOException {
        Stage stage = new Stage();
        //Scene scene = new Scene(getPanelListGatewayLoader().load());
        stage.setTitle("Poner titulo en esta ventana? (PanelListGateway)");
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        //stage.setScene(scene);
        return stage;
    }
    
    public FXMLLoader getPanelConectionLocalhostLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConectionLocalhost.fxml"));
    }
    
    public Stage getPanelConectionLocalhostStage() throws IOException {
        Stage stage = new Stage();        
        //Scene scene = new Scene(getPanelConectionLocalhostLoader().load());
        stage.setTitle("Poner titulo en esta ventana? (PanelConectionLocalhost)");
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        //stage.setScene(scene);
        return stage;
    }
    
    public FXMLLoader getPanelConsoleLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConsole.fxml"));
    }
    
    public Stage getPanelConsoleStage() throws IOException {
        Stage stage = new Stage();         
        //Scene scene = new Scene(getPanelConsoleLoader().load());
        stage.setTitle("Poner titulo en esta ventana? (PanelConsole)");
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        //stage.setScene(scene);
        return stage;
    }
    
    public FXMLLoader getPanelEditConfigLocalLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelEditConfigLocal.fxml"));
    }
    
    public Stage getPanelEditConfigLocalStage() throws IOException {
        Stage stage = new Stage();
       //Scene scene = new Scene(getPanelEditConfigLocalLoader().load());
        stage.setTitle("Poner titulo en esta ventana? (PanelEditConfigLocal)");
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        //stage.setScene(scene);
        return stage;
    }
    
    public FXMLLoader getPanelConectionExperienceLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConectionExperience.fxml"));
    }
         
    public Stage getPanelConectionExperienceStage() throws IOException {
        Stage stage = new Stage();      
        //Scene scene = new Scene(getPanelConectionExperienceLoader().load());
        stage.setTitle("Poner titulo en esta ventana? (PanelConectionExperience)");
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        //stage.setScene(scene);
        return stage;
    }
    
    public FXMLLoader getPanelNodoFrowardingLLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelNodoFrowardingL.fxml"));
    }
    
    public Stage getPanelNodoFrowardingLStage() throws IOException {
        Stage stage = new Stage();  
        //Scene scene = new Scene(getPanelNodoFrowardingLLoader().load());
        ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelNodoFrowardingL");
        stage.setTitle(bundle.getString("PanelNodoFrowardingL.title"));
        Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
        stage.getIcons().add(icono);
        //stage.setScene(scene);
        return stage;
    }
    
}