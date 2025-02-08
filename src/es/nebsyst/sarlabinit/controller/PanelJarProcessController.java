/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.controller;

import es.nebsyst.sarlabinit.daemon.GesJarProcess;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import org.apache.log4j.Logger;

/**
 *
 * @author alumno
 */
public class PanelJarProcessController implements Initializable {

    /*Copied*/
    private final static Logger log = Logger.getLogger(PanelJarProcessController.class);
    
    private ObservableList<String> listModel;
    private ListView<String> jList1;
        
    private void initList() {
         jList1 = new ListView<>();
    }
    private void setList() {
        try {               
            listModel = (ObservableList<String>) GesJarProcess.listProcessJarRunning();
            jList1.setItems(listModel);
        } catch (Exception ex) {
            // Logger.getLogger(PanelJarProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @FXML
    void actualizar() {
        try {
            listModel = (ObservableList<String>) GesJarProcess.listProcessJarRunning();
            jList1.setItems(listModel);
        } catch (Exception ex) {
            //Logger.getLogger(PanelJarProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                //delay(500);
                listModel = (ObservableList<String>) GesJarProcess.listProcessJarRunning();
                jList1.setItems(listModel);
            } catch (Exception ex) {
                //Logger.getLogger(PanelJarProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initList();
        setList();
    }

}
