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

    private Image iconPanelJarProcess;
    
    @Override
    public void start(Stage stage) throws IOException {
        // SE CARGA LA RAIZ PADRE DEL DOCUMENTO FXML
        FXMLLoader fxmlLoader = new FXMLLoader(PanelJarProcessMain.class.getResource("../view/PanelJarProcess.fxml"));
        // TITULO DE LA VENTANA
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Poner titulo en esta ventana (PanelJarProcess)");
        // SE PONE EL ICONO (DA ERROR)
        //iconPanelJarProcess = new Image("../resource/execjar128.png");   
        //stage.getIcons().add(iconPanelJarProcess);
        stage.setScene(scene);
        stage.show();  
    }
    
    
     public static void main(String[] args) {
        launch();
    }
}
