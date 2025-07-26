
package es.uhu.sarlab.discovery.controller;

import es.nebsyst.sarlabinit.main.FXMLLoaderManager;
import es.nebsyst.sarlabinit.main.Main;
import es.nebsyst.sarlabinit.main.StageManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/*     */ 
/*     */ public class PanelConectionLocalhostController implements Initializable
/*     */ {
/*     */   public String user;
/*     */   public String password;
/*  40 */   public String mode = "new";
/*     */   
/*     */   //PanelClientServerName clientName;
/*     */   private Stage panelConectionLocalhostStage;

/*     */   ObservableList<String> modelHardware;
/*  45 */   int selectorListIdExpPowerDevice = -1;
/*  46 */   int selectorListHardware = -1;
            @FXML
            private Label LabelSARLAB; 
            @FXML
            private Button jButton2;
            @FXML
/*     */   private ListView jListHardware;
            @FXML
            private TitledPane jScrollPane2; 
            
            @Override
            public void initialize(URL url, ResourceBundle rb) {
                initPanelConectionLocalhost();
            }
/*     */   
/*     */   private void initPanelConectionLocalhost() {
/*  53 */     initComponents();
/*     */   }
/*     */   
/*     */   public void init(ObservableList<String> modelHardware) {
/*  59 */     this.modelHardware = modelHardware;
/*     */   }
           
/*     */   
/*     */   public void set() {
/*  64 */     this.jListHardware.setItems(this.modelHardware);
/*     */   }
/*     */ 
/*     */   private void initComponents() {
/*     */     
/*  94 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelConectionLocalhost");
/*  95 */     this.LabelSARLAB.setText(bundle.getString("PanelConectionLocalhost.LabelSARLAB.text"));      
/* 127 */     this.jListHardware.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
/* 133 */     this.jScrollPane2.setContent(this.jListHardware);     
              this.jScrollPane2.setText(bundle.getString("PanelConectionLocalhost.jLabel2.text"));

/*     */     Image iconJButton2 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/cancel.png").toExternalForm());
              ImageView iconViewJButton2 = new ImageView(iconJButton2);
              this.jButton2.setGraphic(iconViewJButton2);  
/* 181 */     this.jButton2.setText(bundle.getString("PanelConectionLocalhost.jButton2.text"));

              this.jListHardware.getSelectionModel().selectedItemProperty().addListener(
                (var observable, var oldValue, var newValue) -> {
                    this.selectorListHardware = this.jListHardware.getSelectionModel().getSelectedIndex();
              });

            }   

       // INICIO - Added backend
       public void initList() {
           ObservableList<String> ejemplos = FXCollections.observableArrayList("Ejemplo 1", "Ejemplo 2", "Ejemplo 3");
           jListHardware.setItems(ejemplos); 
       }
       // FIN - Added backend
 
/*     */   @FXML
/*     */   void jButton2ActionPerformed() throws IOException {
                this.panelConectionLocalhostStage = StageManager.getInstance().getStage("PanelConectionLocalhost");
                this.panelConectionLocalhostStage.hide(); 
/*     */   }

/*     */   public static void main(String[] args) {
/* 252 */     Locale.setDefault(new Locale("en", "EN"));
              Platform.runLater(() -> {
                  
                  //StageManager.getInstance().getStage("PanelConectionLocalhost").show();
                  
                  ObservableList<String> modelo = observableArrayList(new ArrayList<String>());
                  modelo.add("Marco");
                  //probar con controlador
                  FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelConectionLocalhost");           
                  PanelConectionLocalhostController panelConectionLocalhostController = fxmlLoader.getController();  
                  panelConectionLocalhostController.init(modelo);
                  panelConectionLocalhostController.set();
              });
              
            }

           }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelConectionLocalhost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
