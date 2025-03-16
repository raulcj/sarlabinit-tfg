package es.uhu.sarlab.discovery.controller;

import es.nebsyst.sarlabinit.main.FXMLLoaderManager;
import es.nebsyst.sarlabinit.main.Main;
import es.nebsyst.sarlabinit.main.StageManager;

import java.io.IOException;
/*     */ import java.net.InetAddress;
import java.net.URL;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * TO-DO: Modificar la UI en SceneBuilder: quitar BorderPane y poner VBox/HBox en su lugar
 * 
 * @author alumno
 */
/*     */ public class PanelNodoFrowardingLController
/*     */   implements Initializable {
    
/*  29 */   public String mode = "add"; 
            public String IPLocal; 
            public String portLocal; 
            public String IPRemote;
/*     */   public String portRemote;
/*     */   public String descriptionRemote;
/*     */   public String formatListSelect;

            private Stage panelNodoFrowardingLStage;
            
            @FXML
/*     */   private Button jButtonCancel;
            @FXML
/*     */   private Button jButtonOk;
            @FXML
/*     */   private CheckBox jCheckBox1;
            @FXML
/*     */   private Label jLabel10; // Puerto (lado drc.)
            @FXML
/*     */   private Label jLabel14; // Entrada en el PC del usuario
            @FXML
            private Label jLabel5; // Puerto (lado izq.)
            @FXML
            private Label jLabel6; // IP (lado izq.)
            @FXML
            private Label jLabel7; // IP (lado drc.)
            @FXML
            private Label jLabel8; // descripcion
            @FXML
            private Label jLabel9; // Conexion de salida en laboratorio remoto
            @FXML
            public TextField jTextFieldDes; // TextField descripcion
            @FXML
            public TextField jTextFieldIPL; // TextField IP (lado izq.)
            @FXML
            public TextField jTextFieldIPR; // TextField IP (lado drc.)
            @FXML
            public TextField jTextFieldPortL; // TextField Puerto (lado izq.)
            @FXML
            public TextField jTextFieldPortR; // TextField Puerto (lado drc.)
/*     */   
            private PanelClientServerNameController panelClientServerNameController;
            
/*     */   public void initPanelNodoFrowardingL() throws IOException {
              /**
               * 
               * ¡¡¡¡ IMPORTANTE !!!!
               * 
               * 
               * Los objetos declarados aqui que no se encuentren anotados con @FXML seran nulos para 
               * los metodos anotados tambien con @FXML. Los metodos que no esten anotados con @FXML
               * pueden recibir con exito las referencias pasadas a los objetos declarados aqui
               * y no obtener NPE.
               * 
               * Por ej.: la inicializacion del controller de PanelClientServerName la puede aprovechar
               * el metodo setIpInit()
               *
              // Controlador de PanelClientServerName
              //FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelClientServerName");
              //panelClientServerNameController = fxmlLoader.getController();
              //
              //(Para homogeneizar la estructura del codigo se borraran las ultimas lineas comentadas)
              
/*  44 */     initComponents();
/*  46 */     this.jTextFieldIPL.setText("127.0.0.1");
/*  47 */     this.jTextFieldIPL.setEditable(false);
/*  48 */     this.jTextFieldPortL.setText("0");
/*  49 */     this.jTextFieldPortR.setText("0");
/*  50 */     this.mode = "local";
              
/*     */   }

/*     */   public void setIpInit() {
              // Controlador de PanelClientServerName
              FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelClientServerName");
              panelClientServerNameController = fxmlLoader.getController();
/*  54 */     this.jTextFieldIPR.setText(panelClientServerNameController.ipInit());
/*     */   }
 
/*     */   private void initComponents() {
/*  72 */   
/*  95 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelNodoFrowardingL");
/*  9  
/* 107 */     this.jLabel7.setText(bundle.getString("PanelNodoFrowardingL.jLabel7.text"));
/*     */     
/* 109 */     //this.jTextFieldIPL.setColumns(15);
/* 111 */     this.jTextFieldIPL.setText(bundle.getString("PanelNodoFrowardingL.jTextFieldIPL.text"));   
/* 118 */     //this.jTextFieldPortL.setColumns(5);
/* 120 */     this.jTextFieldPortL.setText(bundle.getString("PanelNodoFrowardingL.jTextFieldPortL.text"));
/*     */     
/* 124 */     this.jLabel10.setText(bundle.getString("PanelNodoFrowardingL.jLabel10.text"));
/* 131 */     this.jLabel14.setText(bundle.getString("PanelNodoFrowardingL.jLabel14.text"));
/*     */        
/*     */     Image iconJButtonCancel = new Image(getClass().getResource("/es/uhu/sarlab/discovery/view/resources/paneladminsarlab/image/cancel.gif").toExternalForm());
              ImageView iconViewJButtonCancel = new ImageView(iconJButtonCancel);
              this.jButtonCancel.setGraphic(iconViewJButtonCancel);  
/* 170 */     this.jButtonCancel.setText(bundle.getString("PanelNodoFrowardingL.jButtonCancel.text"));
/* 171 */     
/*     */     Image iconJButtonOk = new Image(getClass().getResource("/es/uhu/sarlab/discovery/view/resources/paneladminsarlab/image/accept.gif").toExternalForm());
              ImageView iconViewJButtonOk = new ImageView(iconJButtonOk);
              this.jButtonOk.setGraphic(iconViewJButtonOk);      
/* 178 */     this.jButtonOk.setText(bundle.getString("PanelNodoFrowardingL.jButtonOk.text"));

/* 187 */     //this.jPanel3.setMaximumSize(new Dimension(400, 157));
/* 188 */     //this.jPanel3.setMinimumSize(new Dimension(400, 157));
/* 189 */     //this.jPanel3.setPreferredSize(new Dimension(400, 157));

/* 193 */     this.jLabel8.setText(bundle.getString("PanelNodoFrowardingL.jLabel8.text")); 
/* 195 */     //this.jTextFieldIPR.setColumns(15);
/* 197 */     this.jTextFieldIPR.setText(bundle.getString("PanelNodoFrowardingL.jTextFieldIPR.text"));
/* 205 */     this.jTextFieldDes.setText(bundle.getString("PanelNodoFrowardingL.jTextFieldDes.text"));
/* 209 */     this.jLabel5.setText(bundle.getString("PanelNodoFrowardingL.jLabel5.text"));   
/* 213 */     this.jLabel6.setText(bundle.getString("PanelNodoFrowardingL.jLabel6.text"));
/* 215 */     //this.jTextFieldPortR.setColumns(5);
/* 217 */     this.jTextFieldPortR.setText(bundle.getString("PanelNodoFrowardingL.jTextFieldPortR.text"));
/* 224 */     this.jLabel9.setText(bundle.getString("PanelNodoFrowardingL.jLabel9.text"));
/* 271 */     this.jCheckBox1.setText(bundle.getString("PanelNodoFrowardingL.jLabelModel.text"));    
/* 273 */     //this.jModel.setText(bundle.getString("PanelNodoFrowardingL.jModel.text"));
/*     */   }
/*     */ 
/*     */   @FXML
/*     */   void jTextFieldIPLActionPerformed() {}
/*     */ 
/*     */   @FXML
/*     */   void jButtonOkActionPerformed() throws IOException {
              // Controlador de PanelClientServerName
              FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelClientServerName");
              panelClientServerNameController = fxmlLoader.getController();
              
/* 351 */     this.IPLocal = this.jTextFieldIPL.getText();
/* 352 */     this.IPRemote = this.jTextFieldIPR.getText();
/* 353 */     this.portLocal = this.jTextFieldPortL.getText();
/* 354 */     this.portRemote = this.jTextFieldPortR.getText();
/* 355 */     this.descriptionRemote = this.jTextFieldDes.getText();
/* 356 */     int portL = 0, portR = 0;
/*     */     try {
/* 358 */       portL = Integer.parseInt(this.portLocal);
/* 359 */       portR = Integer.parseInt(this.portRemote);
/* 360 */       this.formatListSelect = String.format("[%15s,%5s]-->[%15s,%5s]|%s ", new Object[] { this.IPLocal, this.portLocal, this.IPRemote, this.portRemote, this.descriptionRemote });
/* 361 */       if (this.mode.equals("local")) { 
/* 364 */         panelClientServerNameController.addForwardingLocalhostL(this.descriptionRemote, this.IPRemote, portR, portL);
/*     */       }
/* 367 */       if (this.mode.equals("adress")) {
/* 368 */         System.out.println(this.formatListSelect + " " + portR + " " + portL);
/* 369 */         panelClientServerNameController.addForwardingLocalAdressL(this.descriptionRemote, this.IPRemote, portR, portL);
/*     */       } 
                panelNodoFrowardingLStage = StageManager.getInstance().getStage("PanelNodoFrowardingL");
                panelNodoFrowardingLStage.hide();
/*     */     }
/* 373 */     catch (NumberFormatException e) {
/* 374 */       this.jTextFieldPortL.requestFocus();
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("El puerto local no es correcto");
                mensaje.showAndWait();   
/*     */     } 
/*     */   }

/*     */   @FXML
/*     */   void jTextFieldIPRActionPerformed() {}

            @FXML
/*     */   void jButtonCancelActionPerformed() throws IOException {
              panelNodoFrowardingLStage = StageManager.getInstance().getStage("PanelNodoFrowardingL");
              panelNodoFrowardingLStage.hide();
/*     */   }
/*     */ 
/*     */   @FXML
/*     */   void jCheckBox1ActionPerformed() {
/*     */     try {
/* 399 */       if (this.jCheckBox1.isSelected()) {
/* 400 */         this.mode = "adress";
/*     */         
/* 402 */         InetAddress addressIp = InetAddress.getLocalHost();
/* 403 */         String ipadress = addressIp.getHostAddress();
/* 404 */         this.jTextFieldIPL.setText(ipadress);
/*     */       } else {
/*     */         
/* 407 */         this.mode = "local";
/* 408 */         this.jTextFieldIPL.setText("127.0.0.1");
/*     */       } 
/* 410 */     } catch (UnknownHostException unknownHostException) {}
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 419 */     Locale.setDefault(new Locale("en", "GB"));
              Platform.runLater(() -> {
               // ¿Esto es UTIL?
               StageManager.getInstance().getStage("PanelNodoFrowardingL");   
              });  
/*     */   }
/*     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initPanelNodoFrowardingL();
        } catch (IOException ex) {
         Logger.getLogger(PanelNodoFrowardingLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelNodoFrowardingL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
