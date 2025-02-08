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
public class PanelJarProcessMain extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        // SE CARGA LA RAIZ PADRE DEL DOCUMENTO FXML
        FXMLLoader fxmlLoader = new FXMLLoader(PanelJarProcessMain.class.getResource("../view/PanelJarProcess.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Poner titulo en esta ventana (PanelJarProcess)");
        Image iconPanelJarProcess = new Image("file:src/es/nebsyst/sarlabinit/resource/execjar128.png");   
        stage.getIcons().add(iconPanelJarProcess);
        stage.setScene(scene);
        stage.show();  
    }
    
     public static void main(String[] args) {
        launch();
    }
}
