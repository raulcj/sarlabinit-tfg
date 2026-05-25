/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.controller;

import es.nebsyst.sarlabinit.main.FXMLLoaderManager;
import es.nebsyst.sarlabinit.main.MySystemTrayFX;
import es.nebsyst.sarlabinit.main.StageManager;
import es.nebsyst.sarlabinit.main.StyleManager;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class PanelMainController implements Initializable {
    
    private Stage mainStage;
    private Stage discoveryStage;
    @FXML
    private ComboBox<String> langCombo;
    @FXML
    private ComboBox<String> cssCombo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       // 1º ComboBox: idiomas
       String [] languages = {"Español", "English"};
       langCombo.getItems().addAll(languages);
       langCombo.setValue("Español"); // valor por defecto
       // 2º ComboBox: estilos visuales
       String [] styles = {"Main", "Second", "Third", "Fourth"};
       cssCombo.getItems().addAll(styles);
       cssCombo.setValue("Main"); // valor por defecto 
       // 1º ComboBox: elegir idioma
       langCombo.setOnAction(e -> {
       String seleccion = getSelectedLanguage();
       if (seleccion.equals("English")) {
           Locale.setDefault(Locale.UK);
           ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelClientServerName_en_GB");
           this.discoveryStage = StageManager.getInstance().getStage("PanelClientServerName");
           this.discoveryStage.setTitle(bundle.getString("PanelClientServerName.title"));
       }     
       });
       // 2º ComboBox: elegir estilo de CSS 
       cssCombo.setOnAction(e -> {
           String seleccion = getSelectedCss();
           StyleManager styleManager = StyleManager.getInstance();
           if (seleccion.equals("Second")) {
               styleManager.setupCss("Second");
           } else if (seleccion.equals("Third")) {
               // to-do
           } else if (seleccion.equals("Fourth")) {
               // to-do
           }
        });  
    }
    
    @FXML
    void bAcceptActionPerformed() 
    {
        mainStage = StageManager.getInstance().getStage("PanelMain");
        // Esconder ventana primaria
        mainStage.hide(); 
        // Invocar icono de bandeja de entrada
        MySystemTrayFX mySystemTrayFX = MySystemTrayFX.getInstance();
        mySystemTrayFX.setupTrayIcon();
        mySystemTrayFX.getTrayIcon().show();
    }
    
    public String getSelectedLanguage() { 
        return langCombo.getSelectionModel().getSelectedItem(); 
    }
    
    public String getSelectedCss() { 
        return cssCombo.getSelectionModel().getSelectedItem(); 
    }
    
}