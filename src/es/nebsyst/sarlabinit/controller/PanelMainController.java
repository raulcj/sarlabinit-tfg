/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.controller;

import es.nebsyst.sarlabinit.main.StageManager;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class PanelMainController implements Initializable {
    
    private Stage panelMainStage;
    @FXML
    private ComboBox<String> langCombo;
    @FXML
    private Button bAccept;
    
    @FXML
    void bAcceptActionPerformed() {
        panelMainStage = StageManager.getInstance().getStage("PanelMain");
        panelMainStage.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       String [] languages = {"Español", "English"};
       langCombo.getItems().addAll(languages);
       langCombo.setValue("Español"); // valor por defecto
       
       langCombo.setOnAction(e -> {
           String seleccion = langCombo.getSelectionModel().getSelectedItem();
           if (seleccion.equals("English")) {
               Locale.setDefault(Locale.UK);
           } else {
               Locale.setDefault(Locale.getDefault());
           }
       });
    }
    
}