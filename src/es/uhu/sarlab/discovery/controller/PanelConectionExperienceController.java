package es.uhu.sarlab.discovery.controller;

import es.nebsyst.sarlabinit.main.Main;
/*     */ import es.uhu.sarlab.discovery.GetParameters.GetParameters;
/*     */ import es.uhu.sarlab.discovery.model.RegisterGateway;
/*     */ import es.uhu.sarlab.discovery.webservlet.model.HashMapExperienceWeb;
/*     */ import es.uhu.sarlab.discovery.webservlet.model.ListForwardingL;
import java.io.IOException;
import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*     */ public class PanelConectionExperienceController implements Initializable {
    
/*  44 */   public String mode = "new";
/*     */   //PanelClientServerName clientName;
/*     */   ObservableList<String> modelExperience;
/*     */   HashMapExperienceWeb hashMapExperienceWeb;
/*     */   RegisterGateway registerGatewayOld;
/*  50 */   String myirtualFrontierOld = null;
/*  51 */   String myURIMyGatewayOld = null;  
/*  53 */   int selectorListExperience = -1; 
            String url; 
            
            @FXML
            private Label LabelSARLAB;
            @FXML
            private Button jButton1; // Select
            @FXML
/*     */   private Button jButton2; // Cancel
            @FXML
/*     */   private ListView jListHardware;
            @FXML
            private ScrollPane jScrollPane2; 
            @FXML
            private TitledPane jTitledPane1;
/*     */   
/*     */   public void initPanelConectionExperience() {
/*  59 */     //this.clientName = clientName;
/*  60 */     initComponents();
/*     */   }

/*     */   public void init(ObservableList<String> modelHardware) {
/*  66 */     this.modelExperience = modelHardware;
/*  67 */     this.jListHardware.setItems(this.modelExperience);
/*     */   }
/*     */   
/*     */   public void setModel(HashMapExperienceWeb hashMapExperienceWeb) {
/*  71 */     this.hashMapExperienceWeb = hashMapExperienceWeb;
/*  72 */     this.modelExperience = observableArrayList(new ArrayList<>());
/*  73 */     Set<String> st = this.hashMapExperienceWeb.getDirectory().keySet();
/*  74 */     ArrayList<String> ar = new ArrayList<>(st);
/*  75 */     for (String s : ar) this.modelExperience.add(s); 
/*  76 */     this.jListHardware.setItems(this.modelExperience);
/*     */   }
/*     */   
/*     */   public void setUrl(String url) {
/*  80 */     this.url = url;
/*     */   }
/*     */   
/*     */   public void setRegisterGateway(RegisterGateway registerGateway) {
/*  84 */     this.registerGatewayOld = registerGateway;
/*     */   }
/*     */   
/*     */   public void setMyirtualFrontierOld(String myirtualFrontierOld) {
/*  88 */     this.myirtualFrontierOld = myirtualFrontierOld;
/*     */   }
/*     */   
/*     */   public void setMyURIMyGatewayOld(String myURIMyGatewayOld) {
/*  92 */     this.myURIMyGatewayOld = myURIMyGatewayOld;
/*     */   }

/*     */   private void initComponents() {

/* 122 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelConectionExperience");
/* 123 */     this.LabelSARLAB.setText(bundle.getString("PanelConectionExperience.LabelSARLAB.text"));

              this.jListHardware.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);     
              this.jListHardware.getSelectionModel().selectedItemProperty().addListener(
                (var observable, var oldValue, var newValue) -> {
                    this.selectorListExperience = this.jListHardware.getSelectionModel().getSelectedIndex();
              });

/* 161 */     this.jScrollPane2.setContent(this.jListHardware);

              this.jTitledPane1.setText(bundle.getString("PanelConectionExperience.jLabel2.text"));
              
              Image iconJButton1 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/select2.png").toExternalForm());
              ImageView iconViewJButton1 = new ImageView(iconJButton1);
              this.jButton1.setGraphic(iconViewJButton1);
/* 209 */     this.jButton1.setText(bundle.getString("PanelConectionExperience.jButton1.text"));
/* 210 */     
/*     */     Image iconJButton2 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/cancel.png").toExternalForm());
              ImageView iconViewJButton2 = new ImageView(iconJButton2);
              this.jButton2.setGraphic(iconViewJButton2);
/* 217 */     this.jButton2.setText(bundle.getString("PanelConectionExperience.jButton2.text"));
/*     */   }

            @FXML
/*     */   void jButton1ActionPerformed() throws IOException {
/* 279 */     if (this.selectorListExperience != -1) {
/* 280 */       String s = (String) this.modelExperience.get(this.selectorListExperience);
/* 281 */       System.out.println(s);
/* 282 */       String nameFile = (String)this.hashMapExperienceWeb.getDirectory().get(s);
/* 283 */       System.out.println(nameFile);
/* 284 */       ListForwardingL listForwardingL = new ListForwardingL();
/* 285 */       GetParameters getParameters = new GetParameters();
/* 286 */       listForwardingL = getParameters.getListForwardingL(this.registerGatewayOld, nameFile);
/* 287 */       if (listForwardingL != null)
/*     */       {
/* 289 */         //this.clientName.addForwardingExperienceLocalhostL(listForwardingL);
                  FXMLLoader fxmlLoader = Main.getDiscoveryLoader();
                  fxmlLoader.load();
                  PanelClientServerNameController panelClientServerNameController = fxmlLoader.getController();
                  panelClientServerNameController.addForwardingExperienceLocalhostL(listForwardingL);
/*     */       }
/* 291 */       Main.getPanelConectionExperienceStage().hide();
/*     */     } else {
                Alert mensaje = new Alert(Alert.AlertType.WARNING);
                mensaje.setContentText("Debe seleccionar una experiencia");
                mensaje.showAndWait();
/*     */     } 
/*     */   }
/*     */ 
            @FXML
/*     */   void jButton2ActionPerformed() throws IOException {
/* 307 */     Main.getPanelConectionExperienceStage().hide();
/*     */   }
  
/*     */   public static void main(String[] args) {
              Platform.runLater(()-> {
                //PanelConectionExperience panelConectionLocalhost = new PanelConectionExperience(null);
/* 318 */       //panelConectionLocalhost.setVisible(true);
                //ObservableList<String> modelo = observableArrayList(new ArrayList<String>());
/* 320 */       //modelo.add("Marco");
/* 321 */       //panelConectionLocalhost.init(modelo);
                FXMLLoader fxmlLoader = Main.getPanelConectionExperienceLoader();
                try {
                    fxmlLoader.load();
                  } catch (IOException ex) {
                      Logger.getLogger(PanelConectionExperienceController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                PanelConectionExperienceController panelConectionLocalhost = fxmlLoader.getController();
                ObservableList<String> modelo = observableArrayList(new ArrayList<String>());
/* 320 */       modelo.add("Marco");
                panelConectionLocalhost.init(modelo);
              }); 
/*     */   }
/*     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initPanelConectionExperience();
    }
 }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelConectionExperience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
