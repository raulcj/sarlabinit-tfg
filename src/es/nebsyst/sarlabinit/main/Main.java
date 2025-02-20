/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.nebsyst.sarlabinit.main;

import java.io.IOException;
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
    
    private static Stage discoveryStage;
    private static Stage PanelListGatewayStage;
    private static Stage PanelJarProcessStage;
        
    @Override
    public void start(Stage panelEJSAppListStage) throws IOException {
        // main stage
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/PanelEJSAppList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());       
        panelEJSAppListStage.setTitle("Poner titulo en esta ventana? (PanelEJSAppList)");
        Image icono = new Image("file:src/es/nebsyst/sarlabinit/resource/execjar128.png");   
        panelEJSAppListStage.getIcons().add(icono);
        panelEJSAppListStage.setScene(scene);
        Platform.setImplicitExit(false);
        // Evita que se muestra la pantalla
        //panelEJSAppListStage.initStyle(StageStyle.UTILITY);
        //panelEJSAppListStage.show();  
        MySystemTray mySystemTray = new MySystemTray(panelEJSAppListStage, getDiscoveryStage()); 
    }
    
    public static Stage getDiscoveryStage() throws IOException {
        if (discoveryStage == null) {
            discoveryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelClientServerName.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            discoveryStage.setTitle("Poner titulo en esta ventana? (PanelClientServerName)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/vista/resources/Remote-Desktop32x32.png");      
            discoveryStage.getIcons().add(icono);
            discoveryStage.setScene(scene);
        }
        return discoveryStage;
    }
    
    public static Stage getPanelListGatewayStage() throws IOException {
        if (PanelListGatewayStage == null) {
            PanelListGatewayStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../../uhu/sarlab/discovery/view/PanelListGateway.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            PanelListGatewayStage.setTitle("Poner titulo en esta ventana? (PanelListGateway)");
            Image icono = new Image("file:src/es/uhu/sarlab/discovery/vista/resources/Remote-Desktop32x32.png");      
            PanelListGatewayStage.getIcons().add(icono);
            PanelListGatewayStage.setScene(scene);
        }
        return PanelListGatewayStage;
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
         
    public static void main(String[] args) {
        launch();
    }

}