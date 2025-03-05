/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.nebsyst.sarlabinit.main;

import java.io.IOException;
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
    
    private static Stage DiscoveryStage;
    private static Stage PanelListGatewayStage;
    private static Stage PanelJarProcessStage;
    private static Stage ProgressBarStage;
    private static Stage PanelConectionLocalhost;
    private static Stage PanelConsole;
    private static Stage PanelEditConfigLocal;
        
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
     
    public static Stage getDiscoveryStage() throws IOException {
        if (DiscoveryStage == null) {
            DiscoveryStage = new Stage();         
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
      
    public static Stage getPanelConectionLocalhostStage() throws IOException {
        if (PanelConectionLocalhost == null) {
            PanelConectionLocalhost = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConectionLocalhost.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            PanelConectionLocalhost.setTitle("Poner titulo en esta ventana? (PanelConectionLocalhost)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
            PanelConectionLocalhost.getIcons().add(icono);
            PanelConectionLocalhost.setScene(scene);
        }
        return PanelConectionLocalhost;
    }
    
    public static FXMLLoader getPanelConsoleLoader() {
        return new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelConsole.fxml"));
    }
    
    public static Stage getPanelConsoleStage() throws IOException {
        if (PanelConsole == null) {
            PanelConsole = new Stage();         
            Scene scene = new Scene(getPanelConsoleLoader().load());
            PanelConsole.setTitle("Poner titulo en esta ventana? (PanelConsole)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
            PanelConsole.getIcons().add(icono);
            PanelConsole.setScene(scene);
        }
        return PanelConsole;
    }
    
    public static Stage getPanelEditConfigLocalStage() throws IOException {
        if (PanelEditConfigLocal == null) {
            PanelEditConfigLocal = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelEditConfigLocal.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            PanelEditConfigLocal.setTitle("Poner titulo en esta ventana? (PanelEditConfigLocal)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/view/resources/Remote-Desktop32x32.png");      
            PanelEditConfigLocal.getIcons().add(icono);
            PanelEditConfigLocal.setScene(scene);
        }
        return PanelEditConfigLocal;
    }
    
    public static void main(String[] args) {
        launch();
    }

}