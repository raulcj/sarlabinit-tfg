package es.uhu.sarlab.discovery.controller;

import es.nebsyst.sarlabinit.main.Main;
import es.uhu.sarlab.discovery.GIOTLocatorService;
import es.uhu.sarlab.discovery.model.RegisterName;
import es.uhu.sarlab.utils.DirectoryFilesUtility;
import es.uhu.sarlab.utils.OS;
import java.awt.EventQueue;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
/*     */ import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*     */ 
/**
 * TO-DO: Considerar si implementar o no eventos de Focus sobre los TextField
 * 
 * @author alumno
 */
/*     */ public class PanelEditConfigLocalController implements Initializable {
/*     */   RegisterName registerName;
/*     */   //PanelClientServerName clientName;
            @FXML
/*     */   private Button jButtonSave;
            @FXML
/*     */   private CheckBox jCheckBoxDesktop;
            @FXML
/*     */   private CheckBox jCheckBoxHttp;
            @FXML
/*     */   private CheckBox jCheckBoxModbus;
            @FXML
/*     */   private CheckBox jCheckBoxSsh;
            @FXML
/*     */   private CheckBox jCheckBoxTelnet;
            @FXML
/*     */   private CheckBox jCheckBoxVNC;
            @FXML
            private CheckBox jCheckBoxsFTP; 
            @FXML
            private Label jLabel1; // Puerto 
            @FXML
            private Label jLabel11; // URI
            @FXML
            private Label jLabel2; // Servicio
            @FXML
            private Label jLabel3; // Estado
            @FXML
            private Label jLabel5; // Nombre Nebsyst
            @FXML
            private Label jLabelDesktop; 
            @FXML
            private Label jLabelHttp; 
            @FXML
            private Label jLabelModbus; 
            @FXML
            private Label jLabelSftp; 
            @FXML
            private Label jLabelSsh; 
            @FXML
            private Label jLabelTelnet; 
            @FXML
            private Label jLabelVNC; 
            @FXML
            private TitledPane jPanel1; // Nombre del sistema
            @FXML
            private TitledPane jPanel3; // Editor de servicios 
            @FXML
            private TextField jTextFieldDesktop; 
            @FXML
            private TextField jTextFieldHttp; 
            @FXML
            private TextField jTextFieldModbus; 
            @FXML
            private TextField jTextFieldName; 
            @FXML
            private TextField jTextFieldSsh; 
            @FXML
            private TextField jTextFieldTelnet; 
            @FXML
            private TextField jTextFieldURI;
            @FXML
            private TextField jTextFieldVNC;
            @FXML
            private TextField jTextFieldsFTP;
/*     */   
/*     */   private void initPanelEditConfigLocal() {
/*  28 */     //this.clientName = clientName;
/*  29 */     initComponents();
/*  31 */     this.registerName = new RegisterName();
/*  32 */     String fileName = giveMeFileNameConfigXML();
/*  33 */     if (DirectoryFilesUtility.existsFile(fileName)) {
/*  34 */       this.registerName = (RegisterName)this.registerName.generateTfromFileXML(fileName, RegisterName.class);
/*     */     } else {
/*  36 */       this.registerName.toFileXML(fileName);
/*     */     } 
/*  38 */     this.jTextFieldName.setText(this.registerName.getSarlabName());
/*  39 */     this.jTextFieldDesktop.setText(Integer.toString(this.registerName.getRdesktopPort()));
/*  40 */     this.jCheckBoxDesktop.setSelected(this.registerName.isRdesktop());
/*  41 */     this.jTextFieldDesktop.setEditable(this.registerName.isRdesktop());
/*  42 */     this.jTextFieldVNC.setText(Integer.toString(this.registerName.getVncPort()));
/*  43 */     this.jCheckBoxVNC.setSelected(this.registerName.isVnc());
/*  44 */     this.jTextFieldVNC.setEditable(this.registerName.isVnc());
/*  45 */     this.jTextFieldTelnet.setText(Integer.toString(this.registerName.getTelnetPort()));
/*  46 */     this.jCheckBoxTelnet.setSelected(this.registerName.isTelnet());
/*  47 */     this.jTextFieldTelnet.setEditable(this.registerName.isTelnet());
/*  48 */     this.jTextFieldSsh.setText(Integer.toString(this.registerName.getSshPort()));
/*  49 */     this.jCheckBoxSsh.setSelected(this.registerName.isSsh());
/*  50 */     this.jTextFieldSsh.setEditable(this.registerName.isSsh());
/*  51 */     this.jTextFieldsFTP.setText(Integer.toString(this.registerName.getSftpPort()));
/*  52 */     this.jCheckBoxsFTP.setSelected(this.registerName.isSftp());
/*  53 */     this.jTextFieldsFTP.setEditable(this.registerName.isSftp());
/*  54 */     this.jTextFieldModbus.setText(Integer.toString(this.registerName.getModbusPort()));
/*  55 */     this.jCheckBoxModbus.setSelected(this.registerName.isModbus());
/*  56 */     this.jTextFieldModbus.setEditable(this.registerName.isModbus());
/*  57 */     this.jTextFieldHttp.setText(Integer.toString(this.registerName.getHttpPort()));
/*  58 */     this.jTextFieldURI.setText(this.registerName.getHttpUri());
/*  59 */     this.jCheckBoxHttp.setSelected(this.registerName.isHttp());
/*  60 */     this.jTextFieldHttp.setEditable(this.registerName.isHttp());
/*  61 */     if (!OS.isWindows())
/*  62 */       this.jButtonSave.setVisible(false); 
/*     */   }
/*     */  
/*     */   public String giveMeFileNameConfigXML() {
/*  76 */     if (OS.isWindows()) {
/*  77 */       if (!DirectoryFilesUtility.existsDirectory("C:/sarlab/config"))
/*  78 */         DirectoryFilesUtility.createPath("C:/sarlab/config"); 
/*  79 */       return "C:/sarlab/config/" + GIOTLocatorService.fileNameBaseXML;
/*     */     } 
/*  81 */     return "/sarlab/config/" + GIOTLocatorService.fileNameBaseXML;
/*     */   }
/*     */ 
/*     */   private void initComponents() {
/* 138 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_editor");
/* 139 */     this.jPanel3.setText(bundle.getString("PanelEditConfigLocal.jPanel3.border.title"));
/*     */     
/* 146 */     this.jLabel2.setText(bundle.getString("PanelEditConfigLocal.jLabel2.text")); 
/* 151 */     this.jLabel1.setText(bundle.getString("PanelEditConfigLocal.jLabel1.text"));
/* 156 */     this.jLabel3.setText(bundle.getString("PanelEditConfigLocal.jLabel3.text"));
/*     */     
/* 161 */     this.jLabelDesktop.setText(bundle.getString("PanelEditConfigLocal.jLabelDesktop.text"));     
/* 164 */     this.jTextFieldDesktop.setText(bundle.getString("PanelEditConfigLocal.jTextFieldDesktop.text"));
/*     */     
/* 190 */     this.jLabelVNC.setText(bundle.getString("PanelEditConfigLocal.jLabelVNC.text"));
/* 193 */     this.jTextFieldVNC.setText(bundle.getString("PanelEditConfigLocal.jTextFieldVNC.text"));
/* 194 */    
/* 219 */     this.jLabelTelnet.setText(bundle.getString("PanelEditConfigLocal.jLabelTelnet.text"));
/* 222 */     this.jTextFieldTelnet.setText(bundle.getString("PanelEditConfigLocal.jTextFieldTelnet.text"));
   
/* 248 */     this.jLabelSsh.setText(bundle.getString("PanelEditConfigLocal.jLabelSsh.text"));  
/* 251 */     this.jTextFieldSsh.setText(bundle.getString("PanelEditConfigLocal.jTextFieldSsh.text"));

/* 277 */     this.jLabelSftp.setText(bundle.getString("PanelEditConfigLocal.jLabelSftp.text"));
/* 280 */     this.jTextFieldsFTP.setText(bundle.getString("PanelEditConfigLocal.jTextFieldsFTP.text"));
              Tooltip jTextFieldsFTPTooltip = new Tooltip("PanelEditConfigLocal.jTextFieldsFTP.toolTipText");
              this.jTextFieldsFTP.setTooltip(jTextFieldsFTPTooltip);
              
/* 307 */     this.jLabelModbus.setText(bundle.getString("PanelEditConfigLocal.jLabelModbus.text"));    
/* 310 */     this.jTextFieldModbus.setText(bundle.getString("PanelEditConfigLocal.jTextFieldModbus.text"));
              Tooltip jTextFieldModbusTooltip = new Tooltip("PanelEditConfigLocal.jTextFieldModbus.toolTipText");
              this.jTextFieldModbus.setTooltip(jTextFieldModbusTooltip);
/* 312 */     
/* 337 */     this.jLabelHttp.setText(bundle.getString("PanelEditConfigLocal.jLabelHttp.text"));
/* 340 */     this.jTextFieldHttp.setText(bundle.getString("PanelEditConfigLocal.jTextFieldHttp.text"));
              Tooltip jTextFieldHttpTooltip = new Tooltip("PanelEditConfigLocal.jTextFieldHttp.toolTipText");
              this.jTextFieldHttp.setTooltip(jTextFieldHttpTooltip);
/* 342 */     
/* 369 */     this.jLabel11.setText(bundle.getString("PanelEditConfigLocal.jLabel11.text"));
/*     */     
/* 371 */     this.jTextFieldURI.setText(bundle.getString("PanelEditConfigLocal.jTextFieldURI.text"));
              Tooltip jTextFieldURITooltip = new Tooltip("PanelEditConfigLocal.jTextFieldURI.toolTipText");
              this.jTextFieldURI.setTooltip(jTextFieldURITooltip);
/* 373 */     
/*     */     Image iconJButtonSave = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/salvar.png").toExternalForm());
              ImageView iconViewJButtonSave = new ImageView(iconJButtonSave);
              this.jButtonSave.setGraphic(iconViewJButtonSave);    
/* 406 */     this.jButtonSave.setText(bundle.getString("PanelEditConfigLocal.jButtonSave.text"));
/*     */     
              this.jPanel1.setText(bundle.getString("PanelEditConfigLocal.jPanel1.border.title"));  
/* 452 */     this.jLabel5.setText(bundle.getString("PanelEditConfigLocal.jLabel5.text"));  
/* 454 */     this.jTextFieldName.setText(bundle.getString("PanelEditConfigLocal.jTextFieldName.text"));
   
/*     */   }
/*     */ 
/*     */   private void jTextFieldURIFocusGained(FocusEvent evt) {
/* 509 */     //this.jTextFieldURI.setBackground(Color.lightGray);
/*     */   }

/*     */   private void jTextFieldURIFocusLost(FocusEvent evt) {
/* 514 */     //this.jTextFieldURI.setBackground(Color.WHITE);
/*     */   }

/*     */   @FXML
/*     */   void jTextFieldURIActionPerformed() {}
/*     */ 
/*     */   private void jTextFieldDesktopFocusGained(FocusEvent evt) {
/* 524 */     //this.jTextFieldDesktop.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldDesktopFocusLost(FocusEvent evt) {
/* 528 */     //this.jTextFieldDesktop.setBackground(Color.WHITE);
/* 529 */     String port = this.jTextFieldDesktop.getText();
/* 530 */     if ("".equals(port)) {
/* 531 */       this.jTextFieldDesktop.setText("3389");
/* 532 */       port = "3389";
/*     */     } 
/*     */   }

/*     */   @FXML
/*     */   void jTextFieldDesktopActionPerformed() {
/* 537 */     //this.jTextFieldDesktop.setBackground(Color.WHITE);
/* 538 */     String port = this.jTextFieldDesktop.getText();
/* 539 */     if ("".equals(port)) {
/* 540 */       this.jTextFieldDesktop.setText("3389");
/* 541 */       port = "3389";
/*     */     } 
/*     */   }
/*     */ 
/*     */   @FXML
/*     */   void jCheckBoxDesktopActionPerformed() {
/* 547 */     this.registerName.setRdesktop(this.jCheckBoxDesktop.isSelected());
/* 548 */     this.jTextFieldDesktop.setEditable(this.registerName.isRdesktop());
/*     */   }

/*     */   private void jTextFieldVNCFocusGained(FocusEvent evt) {
/* 553 */     //this.jTextFieldVNC.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldVNCFocusLost(FocusEvent evt) {
/* 557 */     //this.jTextFieldVNC.setBackground(Color.WHITE);
/* 558 */     String port = this.jTextFieldVNC.getText();
/* 559 */     if ("".equals(port)) {
/* 560 */       this.jTextFieldVNC.setText("5900");
/* 561 */       port = "5900";
/*     */     } 
/*     */   }
/*     */   
            @FXML
/*     */   void jTextFieldVNCActionPerformed() {
/* 566 */     //this.jTextFieldVNC.setBackground(Color.WHITE);
/* 567 */     String port = this.jTextFieldVNC.getText();
/* 568 */     if ("".equals(port)) {
/* 569 */       this.jTextFieldVNC.setText("5900");
/* 570 */       port = "5900";
/*     */     } 
/*     */   }
/*     */ 
/*     */   @FXML
/*     */   void jCheckBoxVNCActionPerformed() {
/* 576 */     this.registerName.setVnc(this.jCheckBoxVNC.isSelected());
/* 577 */     this.jTextFieldVNC.setEditable(this.registerName.isVnc());
/*     */   }
/*     */   
/*     */   private void jTextFieldTelnetFocusGained(FocusEvent evt) {
/* 581 */     //this.jTextFieldTelnet.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldTelnetFocusLost(FocusEvent evt) {
/* 585 */     //this.jTextFieldTelnet.setBackground(Color.WHITE);
/* 586 */     String port = this.jTextFieldTelnet.getText();
/* 587 */     if ("".equals(port)) {
/* 588 */       this.jTextFieldTelnet.setText("23");
/* 589 */       port = "23";
/*     */     } 
/*     */   }
/*     */   
            @FXML
/*     */   void jTextFieldTelnetActionPerformed() {
/* 594 */     //this.jTextFieldTelnet.setBackground(Color.WHITE);
/* 595 */     String port = this.jTextFieldTelnet.getText();
/* 596 */     if ("".equals(port)) {
/* 597 */       this.jTextFieldTelnet.setText("23");
/* 598 */       port = "23";
/*     */     } 
/*     */   }
/*     */ 
/*     */   @FXML
/*     */   void jCheckBoxTelnetActionPerformed() {
/* 604 */     this.registerName.setTelnet(this.jCheckBoxTelnet.isSelected());
/* 605 */     this.jTextFieldTelnet.setEditable(this.registerName.isTelnet());
/*     */   }
/*     */   
/*     */   private void jTextFieldSshFocusGained(FocusEvent evt) {
/* 609 */     //this.jTextFieldSsh.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldSshFocusLost(FocusEvent evt) {
/* 613 */     //this.jTextFieldSsh.setBackground(Color.WHITE);
/* 614 */     String port = this.jTextFieldSsh.getText();
/* 615 */     if ("".equals(port)) {
/* 616 */       this.jTextFieldSsh.setText("22");
/* 617 */       port = "22";
/*     */     } 
/*     */   }
/*     */   
            @FXML
/*     */   void jTextFieldSshActionPerformed() {
/* 622 */     //this.jTextFieldSsh.setBackground(Color.WHITE);
/* 623 */     String port = this.jTextFieldSsh.getText();
/* 624 */     if ("".equals(port)) {
/* 625 */       this.jTextFieldSsh.setText("22");
/* 626 */       port = "22";
/*     */     } 
/*     */   }
/*     */ 
/*     */   @FXML
/*     */   void jCheckBoxSshActionPerformed() {
/* 632 */     this.registerName.setSsh(this.jCheckBoxSsh.isSelected());
/* 633 */     this.jTextFieldSsh.setEditable(this.registerName.isSsh());
/*     */   }
/*     */   
/*     */   private void jTextFieldsFTPFocusGained(FocusEvent evt) {
/* 637 */     //this.jTextFieldsFTP.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldsFTPFocusLost(FocusEvent evt) {
/* 641 */     //this.jTextFieldsFTP.setBackground(Color.WHITE);
/* 642 */     String port = this.jTextFieldsFTP.getText();
/* 643 */     if ("".equals(port)) {
/* 644 */       this.jTextFieldsFTP.setText("22");
/* 645 */       port = "22";
/*     */     } 
/*     */   }
/*     */   
            @FXML
/*     */   void jTextFieldsFTPActionPerformed() {
/* 650 */     //this.jTextFieldsFTP.setBackground(Color.WHITE);
/* 651 */     String port = this.jTextFieldsFTP.getText();
/* 652 */     if ("".equals(port)) {
/* 653 */       this.jTextFieldsFTP.setText("22");
/* 654 */       port = "22";
/*     */     } 
/*     */   }
/*     */ 
/*     */   @FXML
/*     */   void jCheckBoxsFTPActionPerformed() {
/* 660 */     this.registerName.setSftp(this.jCheckBoxsFTP.isSelected());
/* 661 */     this.jTextFieldsFTP.setEditable(this.registerName.isSftp());
/*     */   }
/*     */   
/*     */   private void jTextFieldModbusFocusGained(FocusEvent evt) {
/* 665 */     //this.jTextFieldModbus.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldModbusFocusLost(FocusEvent evt) {
/* 669 */     //this.jTextFieldModbus.setBackground(Color.WHITE);
/* 670 */     String port = this.jTextFieldModbus.getText();
/* 671 */     if ("".equals(port)) {
/* 672 */       this.jTextFieldModbus.setText("502");
/* 673 */       port = "502";
/*     */     } 
/*     */   }
/*     */   
            @FXML
/*     */   void jTextFieldModbusActionPerformed() {
/* 678 */     //this.jTextFieldModbus.setBackground(Color.WHITE);
/* 679 */     String port = this.jTextFieldModbus.getText();
/* 680 */     if ("".equals(port)) {
/* 681 */       this.jTextFieldModbus.setText("502");
/* 682 */       port = "502";
/*     */     } 
/*     */   }
/*     */ 
/*     */   @FXML
/*     */   void jCheckBoxModbusActionPerformed() {
/* 688 */     this.registerName.setModbus(this.jCheckBoxModbus.isSelected());
/* 689 */     this.jTextFieldModbus.setEditable(this.registerName.isModbus());
/*     */   }

/*     */   private void jTextFieldHttpFocusGained(FocusEvent evt) {
/* 694 */     //this.jTextFieldModbus.setBackground(Color.lightGray);
/*     */   }
/*     */ 
/*     */   private void jTextFieldHttpFocusLost(FocusEvent evt) {
/* 699 */     //this.jTextFieldHttp.setBackground(Color.WHITE);
/* 700 */     String port = this.jTextFieldHttp.getText();
/* 701 */     if ("".equals(port)) {
/* 702 */       this.jTextFieldHttp.setText("80");
/* 703 */       port = "3389";
/*     */     } 
/*     */   }
/*     */ 
/*     */   @FXML
/*     */   private void jTextFieldHttpActionPerformed() {}
/*     */ 
/*     */   @FXML
/*     */   void jCheckBoxHttpActionPerformed() {
/* 713 */     this.registerName.setHttp(this.jCheckBoxHttp.isSelected());
/* 714 */     this.jTextFieldHttp.setEditable(this.registerName.isHttp());
/* 715 */     this.jTextFieldURI.setEditable(this.registerName.isHttp());
/*     */   }
/*     */   
            @FXML
/*     */   void jButtonSaveActionPerformed() throws IOException {
/* 719 */     this.registerName.setSarlabName(this.jTextFieldName.getText());
/* 720 */     this.registerName.setRdesktopPort(Integer.parseInt(this.jTextFieldDesktop.getText()));
/* 721 */     this.registerName.setRdesktop(this.jCheckBoxDesktop.isSelected());
/* 722 */     this.registerName.setVncPort(Integer.parseInt(this.jTextFieldVNC.getText()));
/* 723 */     this.registerName.setVnc(this.jCheckBoxVNC.isSelected());
/* 724 */     this.registerName.setTelnetPort(Integer.parseInt(this.jTextFieldTelnet.getText()));
/* 725 */     this.registerName.setTelnet(this.jCheckBoxTelnet.isSelected());
/* 726 */     this.registerName.setSshPort(Integer.parseInt(this.jTextFieldSsh.getText()));
/* 727 */     this.registerName.setSsh(this.jCheckBoxSsh.isSelected());
/* 728 */     this.registerName.setSftpPort(Integer.parseInt(this.jTextFieldsFTP.getText()));
/* 729 */     this.registerName.setSftp(this.jCheckBoxsFTP.isSelected());
/* 730 */     this.registerName.setModbusPort(Integer.parseInt(this.jTextFieldModbus.getText()));
/* 731 */     this.registerName.setModbus(this.jCheckBoxModbus.isSelected());
/* 732 */     this.registerName.setHttpPort(Integer.parseInt(this.jTextFieldHttp.getText()));
/* 733 */     this.registerName.setHttpUri(this.jTextFieldURI.getText());
/* 734 */     this.registerName.setHttp(this.jCheckBoxHttp.isSelected());
/* 735 */     this.registerName.toFileXML(giveMeFileNameConfigXML());
/* 736 */     //setEditable(false);
/* 737 */     //if (this.clientName != null) this.clientName.toUpdate();
              Main.getPanelEditConfigLocalStage().hide();
              Main.getProgressBarStage().show();
/*     */   }
/*     */   
            @FXML
/*     */   void jTextFieldNameActionPerformed() {}
/*     */  
/*     */   public static void main(String[] args) {

/* 776 */     Locale.setDefault(new Locale("en", "GB"));
/* 777 */     System.out.println("Current Locale: " + Locale.getDefault());
/*     */     
/* 779 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 781 */             //(new PanelEditConfigLocalController(null)).setEditable(true);
/*     */           }
/*     */         });
/*     */   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initPanelEditConfigLocal();
    }
    
 }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelEditConfigLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
