/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.nebsyst.sarlabinit.main;

import es.uhu.sarlab.discovery.controller.PanelClientServerNameController;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    
    private static Stage DiscoveryStage;
    private static Stage PanelListGatewayStage;
    private static Stage PanelJarProcessStage;
    private static Stage ProgressBarStage;
    private static Stage PanelConectionLocalhostStage;
    private static Stage PanelConsoleStage;
    private static Stage PanelEditConfigLocalStage;
    private static Stage PanelConectionExperienceStage;
    private static Stage PanelNodoFrowardingLStage;
    private static PanelClientServerNameController discoveryController;
        
    @Override
    public void start(Stage panelEJSAppListStage) throws IOException {
        // Main stage
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/PanelEJSAppList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());       
        panelEJSAppListStage.setTitle("Poner titulo en esta ventana? (PanelEJSAppList)");
        Image icono = new Image("file:src/es/nebsyst/sarlabinit/resource/execjar128.png");   
        panelEJSAppListStage.getIcons().add(icono);
        panelEJSAppListStage.setScene(scene);
        // Permite abrir las opciones del SystemTray mas de una vez
        Platform.setImplicitExit(false);
        // Evita que se muestra la pantalla
        //panelEJSAppListStage.initStyle(StageStyle.UTILITY);
        //panelEJSAppListStage.show();  
        MySystemTray mySystemTray = new MySystemTray(panelEJSAppListStage, getDiscoveryStage(), getProgressBarStage()); 
    }
    
    public static Stage getPanelJarProcessStage() throws IOException {
        if (PanelJarProcessStage == null) {
            PanelJarProcessStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/PanelJarProcess.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            PanelJarProcessStage.setTitle("Poner titulo en esta ventana? (PanelJarProcess)");
            Image icono = new Image("file:src/es/nebsyst/sarlabinit/resource/execjar128.png");   
            PanelJarProcessStage.getIcons().add(icono);
            PanelJarProcessStage.setScene(scene);
        }
        return PanelJarProcessStage;
    }
    
    public static Stage getProgressBarStage() throws IOException {
        if (ProgressBarStage == null) {
            ProgressBarStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/ProgressBar.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ProgressBarStage.setTitle("Poner titulo en esta ventana? (ProgressBar)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/SARLAB32.png");      
            ProgressBarStage.getIcons().add(icono);
            ProgressBarStage.setScene(scene);
        }
        return ProgressBarStage;
    }
    
    public static FXMLLoader getDiscoveryLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelClientServerName.fxml"));
    }
    
    public static PanelClientServerNameController getDiscoveryController() throws IOException {
        FXMLLoader fxmlLoader = getDiscoveryLoader();
        fxmlLoader.load();
        return fxmlLoader.getController();
        
    }
    
    public static Stage getDiscoveryStage() throws IOException {
        if (DiscoveryStage == null) {
            DiscoveryStage = new Stage(); 
            //Parent root = getDiscoveryParent(); // quitar?
            Scene scene = new Scene(getDiscoveryLoader().load());       
            ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vistaTFG/Bundle_PanelClientServerName");
            DiscoveryStage.setTitle(bundle.getString("PanelClientServerName.title"));
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
            DiscoveryStage.getIcons().add(icono);
            DiscoveryStage.setScene(scene);
        }
        return DiscoveryStage;
    }

    public static Stage getPanelListGatewayStage() throws IOException {
        if (PanelListGatewayStage == null) {
            PanelListGatewayStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelListGateway.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            PanelListGatewayStage.setTitle("Poner titulo en esta ventana? (PanelListGateway)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
            PanelListGatewayStage.getIcons().add(icono);
            PanelListGatewayStage.setScene(scene);
        }
        return PanelListGatewayStage;
    }
    
    public static FXMLLoader getPanelConectionLocalhostLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConectionLocalhost.fxml"));
    }
    
    public static Stage getPanelConectionLocalhostStage() throws IOException {
        if (PanelConectionLocalhostStage == null) {
            PanelConectionLocalhostStage = new Stage();        
            Scene scene = new Scene(getPanelConectionLocalhostLoader().load());
            PanelConectionLocalhostStage.setTitle("Poner titulo en esta ventana? (PanelConectionLocalhost)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
            PanelConectionLocalhostStage.getIcons().add(icono);
            PanelConectionLocalhostStage.setScene(scene);
        }
        return PanelConectionLocalhostStage;
    }
    
    public static FXMLLoader getPanelConsoleLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConsole.fxml"));
    }
    
    public static Stage getPanelConsoleStage() throws IOException {
        if (PanelConsoleStage == null) {
            PanelConsoleStage = new Stage();         
            Scene scene = new Scene(getPanelConsoleLoader().load());
            PanelConsoleStage.setTitle("Poner titulo en esta ventana? (PanelConsole)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
            PanelConsoleStage.getIcons().add(icono);
            PanelConsoleStage.setScene(scene);
        }
        return PanelConsoleStage;
    }
    
    public static Stage getPanelEditConfigLocalStage() throws IOException {
        if (PanelEditConfigLocalStage == null) {
            PanelEditConfigLocalStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelEditConfigLocal.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            PanelEditConfigLocalStage.setTitle("Poner titulo en esta ventana? (PanelEditConfigLocal)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
            PanelEditConfigLocalStage.getIcons().add(icono);
            PanelEditConfigLocalStage.setScene(scene);
        }
        return PanelEditConfigLocalStage;
    }
    
    public static FXMLLoader getPanelConectionExperienceLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConectionExperience.fxml"));
    }
         
    public static Stage getPanelConectionExperienceStage() throws IOException {
        if (PanelConectionExperienceStage == null) {
            PanelConectionExperienceStage = new Stage();      
            Scene scene = new Scene(getPanelConectionExperienceLoader().load());
            PanelConectionExperienceStage.setTitle("Poner titulo en esta ventana? (PanelConectionExperience)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
            PanelConectionExperienceStage.getIcons().add(icono);
            PanelConectionExperienceStage.setScene(scene);
        }
        return PanelConectionExperienceStage;
    }
    
    public static FXMLLoader getPanelNodoFrowardingLLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelNodoFrowardingL.fxml"));
    }
    
    public static Stage getPanelNodoFrowardingLStage() throws IOException {
        if (PanelNodoFrowardingLStage == null) {
            PanelNodoFrowardingLStage = new Stage();  
            Scene scene = new Scene(getPanelNodoFrowardingLLoader().load());
            ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelNodoFrowardingL");
            PanelNodoFrowardingLStage.setTitle(bundle.getString("PanelNodoFrowardingL.title"));
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
            PanelNodoFrowardingLStage.getIcons().add(icono);
            PanelNodoFrowardingLStage.setScene(scene);
        }
        return PanelNodoFrowardingLStage;
    }
    
    public static void main(String[] args) {
        launch();
    }

}