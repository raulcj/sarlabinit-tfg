/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.nebsyst.sarlabinit.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class PanelEJSAppListMain extends Application {
    
    @Override
    public void start(Stage panelEJSAppListStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PanelEJSAppListMain.class.getResource("../view/PanelEJSAppList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        panelEJSAppListStage.setTitle("Poner titulo en esta ventana? (PanelEJSAppList)");
        Image icono = new Image("file:src/es/nebsyst/sarlabinit/resource/execjar128.png");   
        panelEJSAppListStage.getIcons().add(icono);
        panelEJSAppListStage.setScene(scene);
        panelEJSAppListStage.show();  
    }
    
    public static void startPanelJarProcess() throws IOException {
        // secondary Stage
        Stage PanelJarProcessStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(PanelEJSAppListMain.class.getResource("../view/PanelJarProcess.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PanelJarProcessStage.setTitle("Poner titulo en esta ventana? (PanelJarProcess)");
        Image icono = new Image("file:src/es/nebsyst/sarlabinit/resource/execjar128.png");   
        PanelJarProcessStage.getIcons().add(icono);
        PanelJarProcessStage.setScene(scene);
        PanelJarProcessStage.show();  
    }
    
    public static void main(String[] args) {
        launch();
    }
}