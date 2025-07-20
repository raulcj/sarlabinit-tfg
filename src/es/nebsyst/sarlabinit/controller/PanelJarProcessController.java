/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.controller;

import es.nebsyst.sarlabinit.daemon.GesJarProcess;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 *
 * @author alumno
 */
public class PanelJarProcessController implements Initializable {

    /*Copied*/
    private final static Logger log = Logger.getLogger(PanelJarProcessController.class);
    
    private ObservableList<String> listModel;
    
    @FXML
    private ListView<String> jList1;
        
    public void setList() {
        // Added backend
        ObservableList<String> ejemplos = FXCollections.observableArrayList("Ejemplo 1", "Ejemplo 2", "Ejemplo 3");
        jList1.setItems(ejemplos); 
    } 
    
    @FXML
    void actualizar() throws Exception {
        //listModel = (ObservableList<String>) GesJarProcess.listProcessJarRunning();
        //jList1.setItems(listModel);  
        // Added backend
        Alert mensaje = new Alert(AlertType.INFORMATION);
        mensaje.setTitle("Información");
        mensaje.setHeaderText("Estado del proceso actualizado.");
        Stage stage = (Stage) mensaje.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../resource/exec128.png")));
        mensaje.show();
    }
    
    @FXML
    void detener() {
        // TODO add your handling code here:
        int ind = jList1.getSelectionModel().getSelectedIndex();
        if (ind != -1) {
            try {
                String line = listModel.get(ind);
                StringTokenizer st = new StringTokenizer(line, " ");
                String pid = st.nextToken();
                log.info(pid);
                GesJarProcess.killPID(pid);
                //default title and icon
                Alert mensaje = new Alert(AlertType.INFORMATION);
                mensaje.setContentText("Se elimina el proceso PID " + pid);
                mensaje.showAndWait();
                //delay(500);
                listModel = (ObservableList<String>) GesJarProcess.listProcessJarRunning();
                jList1.setItems(listModel);
            } catch (Exception ex) {
                //Logger.getLogger(PanelJarProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        // Added backend
        Alert mensaje_info = new Alert(AlertType.INFORMATION);
        mensaje_info.setTitle("Información");
        mensaje_info.setHeaderText("Proceso detenido.");
        Stage stage = (Stage) mensaje_info.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../resource/exec128.png")));
        mensaje_info.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        setList();
    }

}
