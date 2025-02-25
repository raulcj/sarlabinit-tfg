
package es.uhu.sarlab.discovery.controller;

import es.nebsyst.sarlabinit.main.Main;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/*     */ 
/*     */ public class PanelConectionLocalhostController implements Initializable
/*     */ {
/*     */   public String user;
/*     */   public String password;
/*  40 */   public String mode = "new";
/*     */   
/*     */   //PanelClientServerName clientName;
/*     */   
/*     */   ObservableList<String> modelHardware;
/*  45 */   int selectorListIdExpPowerDevice = -1;
/*  46 */   int selectorListHardware = -1;
            @FXML
            private Label LabelSARLAB; 
            @FXML
            private Button jButton2;
/*     */   private Label jLabel2;
            @FXML
/*     */   public ListView jListHardware;
/*     */   private Pane jPanel1;
/*     */   
/*     */   public void initPanelConectionLocalhost() {
/*  52 */     //this.clientName = clientName;
/*  53 */     initComponents();
/*     */   }
/*     */   private Pane jPanel2;
/*     */   private Pane jPanel3;
/*     */   
/*     */   public void init(ObservableList<String> modelHardware) {
/*  59 */     this.modelHardware = modelHardware;
/*     */   }
/*     */   private Pane jPanel7; 
            @FXML
            private TitledPane jScrollPane2; 
            @FXML
            private ImageView logo;
/*     */   
/*     */   public void set() {
/*  64 */     this.jListHardware.setItems(this.modelHardware);
/*     */   }
/*     */ 
/*     */   private void initComponents() {
/*  79 */     this.jPanel1 = new Pane();
/*  82 */     this.jPanel2 = new Pane();
/*  83 */     this.jPanel7 = new Pane();
/*  86 */     this.jLabel2 = new Label();
/*  87 */     this.jPanel3 = new Pane();
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
 
/*     */   @FXML
/*     */   void jButton2ActionPerformed() throws IOException {
/* 245 */     Main.getPanelConectionLocalhostStage().hide();
/*     */   }

/*     */   public static void main(String[] args) {
/* 252 */     Locale.setDefault(new Locale("en", "EN"));
/* 253 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
                        try {
                            Main.getPanelConectionLocalhostStage().show();
                        } catch (IOException ex) {
                            Logger.getLogger(PanelConectionLocalhostController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      ObservableList<String> modelo = observableArrayList(new ArrayList<String>());
/* 258 */             modelo.add("Marco");
/* 259 */             //panelConectionLocalhost.init(modelo);
/* 260 */             //panelConectionLocalhost.set();
/*     */           }
/*     */         });
/*     */   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initPanelConectionLocalhost();
    }
 }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelConectionLocalhost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
