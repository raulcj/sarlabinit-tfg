/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uhu.sarlab.discovery.controller;

import es.nebsyst.encrypt.CryptographyAESSarlab2;
import es.uhu.sarlab.digexlab.giot.model.ParameterAccess;
import es.uhu.sarlab.digexlab.giot.model.ParameterTunnel;
import es.uhu.sarlab.digexlab.giot.model.ParameterVirtualFrontier;
import es.uhu.sarlab.digexlab.giot.model.PublicPort;
import es.uhu.sarlab.discovery.GetParameters.GetParameters;
import es.uhu.sarlab.discovery.model.DirectoryGatewayRemote;
import es.uhu.sarlab.discovery.model.RegisterGateway;
import es.uhu.sarlab.utils.CommunicationsUtilities;
import es.uhu.sarlab.utils.OS;
import es.uhu.sarlab.utils.PropertiesFiles;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
//import javax.swing.ListModel;

/**
 * REVISAR Y MODIFICAR: lineas con PanelClientServerName clientName
 * 
 * @author alumno
 */
public class PanelListGatewayController implements Initializable {
    // INICIALIZACIONES DE CLASE
    String fileNameBaseXML = "gateway.xml";
    DirectoryGatewayRemote directoryGatewayRemote;
    ObservableList<String> listModel;
    RegisterGateway registerGateway = new RegisterGateway();
    boolean select = false;
    //PanelClientServerName clientName;
    PropertiesFiles propertiesFiles = new PropertiesFiles(PropertiesFiles.CONFIG_FILE); 
    String initVirtualFrontier; 
    String initPortVirtualFrontier; 
    String initPathVirtualFrontier; 
    
    @FXML
    private TitledPane jTitledPane1;
    @FXML
    private TitledPane jTitledPane2;
    @FXML
    private TitledPane jTitledPane3; 
    @FXML
    private ImageView icoHD; 
    @FXML
    private ImageView jLabel1;
    @FXML
    private Button jButtonDel; 
    @FXML
    private Button jButtonNew; 
    @FXML
    private Button jButtonSave; 
    @FXML
    private Button jButtonSelect; 
    @FXML
    private CheckBox jCheckBoxGIoT;
    // No necesario, es Title de JTitledPane3:
    //private String jLabelIdConnectT; // cambio
    @FXML
    private Label jLabelIdConnect;  
    @FXML
    private Label jLabelIdExp;
    @FXML
    private Label jLabelIdGIOT;
    @FXML
    private Label jLabelIpServerSarlab;
    // No se usa:
    //private Label jLabelKeyGIOT;
    @FXML
    private Label jLabelPass; 
    @FXML
    private Label jLabelPortInputGIOTTunnel;
    @FXML
    private Label jLabelPortInputGIOTWeb;    
    @FXML
    private Label jLabelPortServerSarlab; 
    @FXML
    private Label jLabelTimeOut;
    @FXML
    private Label jLabelUser; 
    @FXML
    private ListView<String> jList1;
    @FXML
    private PasswordField jTextKeyGIOT;
   
    private PasswordField jPasswordPassGIOT;    
    @FXML
    private TextField jTextIdExp; 
    @FXML
    private TextField jTextIdGIOT; 
    @FXML
    private TextField jTextUserGIOT; 
    @FXML
    private TextField jTextIpServerSarlab; 
    @FXML
    private TextField jTextPortInputGIOTTunnel; 
    @FXML
    private TextField jTextPortInputGIOTWeb; 
    @FXML
    private TextField jTextPortServerSarlab1;
    @FXML
    private TextField jTextTimeOUT; 
    @FXML
    private TextField jTextlIdConnect;
      
    private void initPanelListGateway() {
        // Se inicializan los componentes no presentes en .fxml
        jPasswordPassGIOT = new PasswordField();
        // Se declara el bundle
        ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
        // Seteo de componentes con texto
        this.jTitledPane1.setText(bundle.getString("PanelListGateway.jPanel1.border.title"));
        this.jTitledPane2.setText(bundle.getString("PanelListGateway.jPanel2.border.title"));
        this.jTitledPane3.setText(bundle.getString("PanelListGateway.jLabelIdConnectT.text"));
        this.jButtonSave.setText(bundle.getString("PanelListGateway.jButtonSave.text"));
        this.jButtonNew.setText(bundle.getString("PanelListGateway.jButtonNew.text"));
        this.jButtonDel.setText(bundle.getString("PanelListGateway.jButtonDel.text"));
        this.jButtonSelect.setText(bundle.getString("PanelListGateway.jButtonSelect.text"));
        this.jCheckBoxGIoT.setText(bundle.getString("PanelListGateway.jCheckBoxGIoT.text"));
        // No necesario, es Title de JTitledPane3:
        //this.jLabelIdConnectT.setText(bundle.getString("PanelListGateway.jLabelIdConnectT.text"));
        this.jLabelIdConnect.setText(bundle.getString("PanelListGateway.jLabelIdConnect.text"));
        this.jLabelIdExp.setText(bundle.getString("PanelListGateway.jLabelIdExp.text"));
        this.jLabelIdGIOT.setText(bundle.getString("PanelListGateway.jLabelIdGIOT.text"));
        this.jLabelIpServerSarlab.setText(bundle.getString("PanelListGateway.jLabelIpServerSarlab.text"));
        this.jLabelUser.setText(bundle.getString("PanelListGateway.jLabelUser.text"));
        // No se usa:
        //this.jLabelKeyGIOT.setText(bundle.getString("PanelListGateway.jLabelKeyGIOT.text"));
        this.jLabelPass.setText(bundle.getString("PanelListGateway.jLabelPass.text"));
        this.jLabelPortServerSarlab.setText(bundle.getString("PanelListGateway.jLabelPortServerSarlab.text"));
        this.jLabelTimeOut.setText(bundle.getString("PanelListGateway.jLabelTimeOut.text"));    
        this.jTextlIdConnect.setText(bundle.getString("PanelListGateway.jTextlIdConnect.text"));
        this.jTextTimeOUT.setText(bundle.getString("PanelListGateway.jTextTimeOUT.text"));
        this.jTextIdExp.setText(bundle.getString("PanelListGateway.jTextIdExp.text"));
        this.jTextUserGIOT.setText(bundle.getString("PanelListGateway.jTextUserGIOT.text"));
        this.jTextIpServerSarlab.setText(bundle.getString("PanelListGateway.jTextIpServerSarlab.text"));
        this.jTextPortServerSarlab1.setText(bundle.getString("PanelListGateway.jTextPortServerSarlab1.text"));
        this.jTextIdGIOT.setText(bundle.getString("PanelListGateway.jTextIdGIOT.text"));
        this.jPasswordPassGIOT.setText(bundle.getString("PanelListGateway.jPasswordPassGIOT.text")); 
        // Invisibles
        this.jLabelPortInputGIOTTunnel.setText(bundle.getString("PanelListGateway.jLabelPortInputGIOTTunnel.text"));
        this.jLabelPortInputGIOTWeb.setText(bundle.getString("PanelListGateway.jLabelPortInputGIOTWeb.text"));
        this.jTextPortInputGIOTTunnel.setText(bundle.getString("PanelListGateway.jTextPortInputGIOTTunnel.text"));
        this.jTextPortInputGIOTWeb.setText(bundle.getString("PanelListGateway.jTextPortInputGIOTWeb.text"));  
        // FUNCIONALIDAD
        testParameter();
        //this.clientName = clientName;
        initJText();
        this.directoryGatewayRemote = new DirectoryGatewayRemote();
        this.directoryGatewayRemote = (DirectoryGatewayRemote)this.directoryGatewayRemote.generateTfromFileXML(giveMeFileNameConfigXML(), DirectoryGatewayRemote.class);
        if (this.directoryGatewayRemote == null) {
            this.directoryGatewayRemote = new DirectoryGatewayRemote();
        }
        
        ObservableList<String> lista = observableArrayList(new ArrayList<String>());
        lista.addAll("Item 1", "Item 2", "Item 3", "Item 4", "Item 5");
    
        this.jList1.setItems(lista);

        //this.jList1.setItems(new observableArrayList() {
        //  String[] strings = new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
        //  public int getSize() { return this.strings.length; }
        // public String getElementAt(int i) { return this.strings[i]; }
        //});
        this.listModel = observableArrayList(new ArrayList<>());
        for (int i =  0; i < this.directoryGatewayRemote.getDefaultListModel().size(); i++) {
            listModel.add((String)this.directoryGatewayRemote.getDefaultListModel().get(i)); 
        }
        //this.listModel = (ObservableList<String>) this.directoryGatewayRemote.getDefaultListModel();
        this.jList1.setItems(this.listModel);   
        ButtonGIOT(false);
        
    }
    
    // INICIO - Added backend
    public void initList() {
      ObservableList<String> ejemplos = FXCollections.observableArrayList(""
           + "edutechfrontier", 
             "nebsystmain"); 
      jList1.setItems(ejemplos); 
    }
    // FIN - Added backend  
    
    public void ButtonGIOT(boolean value) {
        this.jTextPortInputGIOTTunnel.setVisible(value);
        this.jLabelPortInputGIOTTunnel.setVisible(value);
        this.jTextPortInputGIOTWeb.setVisible(value);
        this.jLabelPortInputGIOTWeb.setVisible(value);
        this.jLabelIdExp.setVisible(!value);
        this.jTextIdExp.setVisible(!value);
        if (value) {
            setMyGateway();
        } else {
            setFrontier();
        } 
    }
    
    public void setMyGateway() {
        ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
        this.jLabelIdGIOT.setText(bundle.getString("PanelListGateway.jLabelIdGIOT.text2"));
        this.jLabelIpServerSarlab.setText(bundle.getString("myVirtualFrontierIP"));
        this.jLabelPortServerSarlab.setText(bundle.getString("myVirtualFrontierPort"));
        this.jLabelUser.setText(bundle.getString("myGatewayUser"));
        this.jLabelPass.setText(bundle.getString("myGatewayPassword"));
    }
    
    public void setFrontier() {
        ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
        this.jLabelIdGIOT.setText(bundle.getString("PanelListGateway.jLabelIdGIOT.text"));
        this.jLabelIpServerSarlab.setText(bundle.getString("PanelListGateway.jLabelIpServerSarlab.text"));
        this.jLabelPortServerSarlab.setText(bundle.getString("PanelListGateway.jLabelPortServerSarlab.text"));
        this.jLabelUser.setText(bundle.getString("PanelListGateway.jLabelUser.text"));
        this.jLabelPass.setText(bundle.getString("PanelListGateway.jLabelPass.text"));
    }
    
    public void testParameter() {
        boolean change = false;
        this.initVirtualFrontier = this.propertiesFiles.getPropertiesValue("initVirtualFrontier");
        if (this.initVirtualFrontier == null) {
            this.propertiesFiles.setPropertiesValue("initVirtualFrontier", "edutechfrontier.nebsyst.com");
            change = true;
        } 
        this.initPortVirtualFrontier = this.propertiesFiles.getPropertiesValue("initPortVirtualFrontier");
        if (this.initPortVirtualFrontier == null) {
            this.propertiesFiles.setPropertiesValue("initPortVirtualFrontier", "443");
            change = true;
        } 
        this.initPathVirtualFrontier = this.propertiesFiles.getPropertiesValue("initPathVirtualFrontier");
        if (this.initPathVirtualFrontier == null) {
            this.propertiesFiles.setPropertiesValue("initPathVirtualFrontier", "SARLABV8.0");
            change = true;
        } 
        if (change) {
            this.propertiesFiles.saveProperties();
            this.initPathVirtualFrontier = this.propertiesFiles.getPropertiesValue("initPathVirtualFrontier");
            this.initPortVirtualFrontier = this.propertiesFiles.getPropertiesValue("initPortVirtualFrontier");
            this.initVirtualFrontier = this.propertiesFiles.getPropertiesValue("initVirtualFrontier");
        } 
    }
    
    public void initJText() {
        this.jTextlIdConnect.setText("");
        this.jTextIpServerSarlab.setText(this.initVirtualFrontier);
        this.jTextPortServerSarlab1.setText(this.initPortVirtualFrontier);
        this.jTextIdGIOT.setText(this.initPathVirtualFrontier);
        this.jTextKeyGIOT.setText("");
        this.jTextPortInputGIOTTunnel.setText("");
        this.jTextPortInputGIOTWeb.setText("");
        this.jTextUserGIOT.setText("");
        this.jPasswordPassGIOT.setText("");
        this.jTextIdExp.setText("");
        this.jTextTimeOUT.setText("600");
    }
    
    @FXML
    void jList1ValueChanged() {
/* 449 */     this.jCheckBoxGIoT.setDisable(true);
              int ind = jList1.getSelectionModel().getSelectedIndex();
/* 452 */     if (ind != -1) {
                // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 453 */       //String key = (String) this.listModel.get(ind);   
/* 455 */       //RegisterGateway registerGatewayOrg = this.directoryGatewayRemote.get(key);
/* 456 */       //this.registerGateway = registerGatewayOrg.clone();
/* 457 */       this.registerGateway.setPassGIOT(CryptographyAESSarlab2.decrypt(this.registerGateway.getPassGIOT()));
/* 458 */       this.jTextlIdConnect.setText(this.registerGateway.getIdConnect());
/* 459 */       String idGIOT = this.registerGateway.getIdGIOT();
/* 460 */       if (idGIOT != null) {
/* 461 */         idGIOT = idGIOT.replace("GIoT_", "");
/*     */       } else {
/* 463 */         idGIOT = "";
/*     */       } 
/* 465 */       this.jTextIdGIOT.setText(idGIOT);
/* 466 */       this.jTextKeyGIOT.setText(this.registerGateway.getKeyGIOT());
/* 467 */       this.jTextPortInputGIOTTunnel.setText(this.registerGateway.getPortInputGIOTTunnel());
/* 468 */       this.jTextPortInputGIOTWeb.setText(this.registerGateway.getPortInputGIOTWeb());
/* 469 */       this.jTextUserGIOT.setText(this.registerGateway.getUserGIOT());
/* 470 */       this.jPasswordPassGIOT.setText(this.registerGateway.getPassGIOT());
/* 471 */       this.jTextIdExp.setText(this.registerGateway.getIdExp());
/* 472 */       this.jTextTimeOUT.setText(this.registerGateway.getTimeOUT());
/* 473 */       this.jButtonSelect.setDisable(false);
/* 474 */       this.jButtonDel.setDisable(true);
/* 475 */       if (this.registerGateway.getPortInputGIOTTunnel().equals("")) {
/* 476 */         ButtonGIOT(false);
/* 477 */         this.jCheckBoxGIoT.setSelected(false);
/*     */       } else {
/* 479 */         ButtonGIOT(true);
/* 480 */         this.jCheckBoxGIoT.setSelected(true);
/*     */       } 
/* 482 */       this.jTextIpServerSarlab.setText(this.registerGateway.getVirtualFrontier());
/* 483 */       this.jTextPortServerSarlab1.setText(this.registerGateway.getPortVirtualFrontier());
/*     */     } 
/*     */   }
    
    @FXML
    void jButtonSaveActionPerformed() {
/* 504 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
/* 505 */     this.jButtonSelect.setDisable(true);
/* 506 */     this.jButtonDel.setDisable(true);
/* 507 */     if (!CommunicationsUtilities.isInternetConnection()) {
/* 508 */       JOptionPane.showMessageDialog(null, bundle.getString("PanelListGateway.messagebox5"));
/*     */       return;
/*     */     } 
/* 511 */     RegisterGateway registerGateway = new RegisterGateway();
/* 512 */     GetParameters getParameters = new GetParameters();
/*     */     
/* 514 */     registerGateway.setIdConnect(this.jTextlIdConnect.getText());
/* 515 */     registerGateway.setVirtualFrontier(this.jTextIpServerSarlab.getText());
/* 516 */     registerGateway.setPortVirtualFrontier(this.jTextPortServerSarlab1.getText());
/*     */     
/* 518 */     registerGateway.setUserGIOT(this.jTextUserGIOT.getText());
              // registerGateway.setPassGIOT(new String(this.jPasswordPassGIOT.getPassword));
/* 519 */     registerGateway.setPassGIOT(new String(this.jPasswordPassGIOT.getText()));
/* 520 */     registerGateway.setIdExp(this.jTextIdExp.getText());
/* 521 */     registerGateway.setTimeOUT(this.jTextTimeOUT.getText());
/*     */     
/* 523 */     ParameterVirtualFrontier parameterVirtualFrontier = new ParameterVirtualFrontier();
/* 524 */     if (!parameterVirtualFrontier.load(registerGateway.getVirtualFrontier())) {
/* 525 */       JOptionPane.showMessageDialog(null, bundle.getString("PanelListGateway.messagebox6"));
/* 526 */       this.jTextIpServerSarlab.requestFocus();
/*     */       
/*     */       return;
/*     */     } 
/* 530 */     if (this.jCheckBoxGIoT.isSelected()) {
/* 531 */       registerGateway.setIdExp("");
/* 532 */       registerGateway.setIdGIOT("GIoT_" + this.jTextIdGIOT.getText());
/*     */ 
/*     */       
/* 535 */       registerGateway.setIpServerSarlab(parameterVirtualFrontier.getIp());
/* 536 */       registerGateway.setPortServerSarlab(parameterVirtualFrontier.getPort());
/*     */       
/* 538 */       registerGateway.setIpPublicherMyGateway(parameterVirtualFrontier.getIpPublicher());
/*     */       
/* 540 */       ParameterAccess parameterAccess = new ParameterAccess();
/* 541 */       parameterAccess = getParameters.getParameterAccessMyGateway(registerGateway.getUserGIOT(), registerGateway.getPassGIOT(), registerGateway.getVirtualFrontier(), registerGateway.getPortVirtualFrontier(), registerGateway.getIdGIOT(), registerGateway.getIpServerSarlab());
/* 542 */       if (parameterAccess == null) {
/* 543 */         JOptionPane.showMessageDialog(null, bundle.getString("PanelListGateway.messagebox1"));
/*     */         return;
/*     */       } 
/* 546 */       if (parameterAccess.getUsuarioMyGateway().equals("") || parameterAccess.getPasswordMyGateway().equals("")) {
/* 547 */         JOptionPane.showMessageDialog(null, bundle.getString("PanelListGateway.messagebox4"));
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 552 */       if (!parameterAccess.getUsuarioMyGateway().equals(registerGateway.getIdGIOT())) {
/* 553 */         this.jTextIdGIOT.setText(parameterAccess.getUsuarioMyGateway().replace("GIoT_", ""));
/* 554 */         registerGateway.setIdGIOT(parameterAccess.getUsuarioMyGateway());
/*     */       } 
/*     */       
/* 557 */       PublicPort publicPort = new PublicPort();
/*     */       
/* 559 */       publicPort = getParameters.getPublicPortMyGateway(parameterAccess.getUsuarioMyGateway(), parameterAccess.getPasswordMyGateway(), this.jTextIpServerSarlab.getText(), this.jTextPortServerSarlab1.getText(), parameterAccess.getUriPublicPort());
/* 560 */       if (publicPort == null) {
/* 561 */         JOptionPane.showMessageDialog(null, bundle.getString("PanelListGateway.messagebox2"));
/*     */         return;
/*     */       } 
/* 564 */       registerGateway.setPortInputGIOTTunnel(publicPort.getPortGIoTTunnel());
/* 565 */       registerGateway.setPortInputGIOTWeb(publicPort.getPortGIoTWeb());
/*     */     }
/*     */     else {
/*     */       
/* 569 */       registerGateway.setIdGIOT(this.jTextIdGIOT.getText());
/* 570 */       ParameterTunnel parameterTunnel = getParameters.getParameterTunnel(registerGateway.getUserGIOT(), registerGateway.getPassGIOT(), registerGateway.getVirtualFrontier(), registerGateway.getPortVirtualFrontier(), registerGateway.getIdGIOT());
/* 571 */       if (parameterTunnel == null) {
/* 572 */         JOptionPane.showMessageDialog(null, bundle.getString("PanelListGateway.messagebox3"));
/*     */         return;
/*     */       } 
/* 575 */       registerGateway.setIpServerSarlab(parameterTunnel.getIP());
/* 576 */       registerGateway.setPortServerSarlab(parameterTunnel.getPort());
/*     */     } 
/*     */     
/* 579 */     registerGateway.setPassGIOT(CryptographyAESSarlab2.encrypt(registerGateway.getPassGIOT()));
/* 580 */     if (!this.directoryGatewayRemote.set(registerGateway.getIdConnect(), registerGateway)) {
/* 581 */       this.directoryGatewayRemote.put(registerGateway.getIdConnect(), registerGateway);
/* 582 */       this.listModel = (ObservableList<String>) this.directoryGatewayRemote.getDefaultListModel();
/* 583 */       this.jList1.setItems(this.listModel);
/*     */     } 
/* 585 */     this.directoryGatewayRemote.toFileXML(giveMeFileNameConfigXML());
/* 586 */     this.jButtonSelect.setDisable(false);
/* 587 */     this.jButtonDel.setDisable(false);
        }
    
    @FXML 
    void jButtonNewActionPerformed() {
/* 591 */     this.jButtonSelect.setDisable(true);
/* 592 */     this.jButtonDel.setDisable(true);
/* 593 */     this.jCheckBoxGIoT.setSelected(false);
/* 594 */     ButtonGIOT(false);
/* 595 */     //this.listModel = (ObservableList<String>) this.directoryGatewayRemote.getDefaultListModel();
              ObservableList<String> auxList = observableArrayList(new ArrayList<>());
              for (int i = 0; i < this.directoryGatewayRemote.getDefaultListModel().size(); i++) {
                  auxList.add((String) directoryGatewayRemote.getDefaultListModel().get(i));
              }
              this.listModel = auxList; 
/* 596 */     this.jList1.setItems(this.listModel);
/* 597 */     initJText();
/* 598 */     this.jCheckBoxGIoT.setDisable(false);
/* 599 */     this.jTextIdGIOT.setText(this.initPathVirtualFrontier);
       }
       
    @FXML
    void jButtonDelActionPerformed() {
/* 603 */     this.jButtonSelect.setDisable(true);
/* 604 */     this.jButtonDel.setDisable(true);
/* 605 */     int ind = jList1.getSelectionModel().getSelectedIndex();
/* 606 */     this.jCheckBoxGIoT.setSelected(false);
/* 607 */     this.jCheckBoxGIoT.setDisable(false);
              // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL     
/* 608 */     //String key = (String) this.listModel.get(ind);
/* 610 */     //this.registerGateway = this.directoryGatewayRemote.get(key);
/* 611 */     this.directoryGatewayRemote.remove(this.registerGateway);
              ObservableList<String> modelo = observableArrayList(this.jList1.getSelectionModel().getSelectedItem());
              // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL     
              //modelo.remove(ind);
/* 614 */     //this.listModel = (ObservableList<String>) this.directoryGatewayRemote.getDefaultListModel();
/* 615 */     this.jList1.setItems(this.listModel);
/* 616 */     this.directoryGatewayRemote.toFileXML(giveMeFileNameConfigXML());
/* 617 */     initJText();
/* 618 */     this.jLabelPortInputGIOTTunnel.setVisible(false);
/* 619 */     this.jTextPortInputGIOTTunnel.setVisible(false);
/* 620 */     this.jLabelPortInputGIOTWeb.setVisible(false);
/* 621 */     this.jTextPortInputGIOTWeb.setVisible(false);
/*     */   }
       
    @FXML
    void jButtonSelectActionPerformed() {
/* 625 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
/* 626 */     int ind = jList1.getSelectionModel().getSelectedIndex();
              // ANULADO PARA BACKED DE PRUEBA FUNCIONAL
/* 627 */     //String key = (String) this.listModel.get(ind);    
/* 629 */     //RegisterGateway registerGatewayOrg = this.directoryGatewayRemote.get(key);
/*     */     
/* 631 */     //this.registerGateway = registerGatewayOrg.clone();
/* 632 */     this.registerGateway.setPassGIOT(CryptographyAESSarlab2.decrypt(this.registerGateway.getPassGIOT()));
/*     */     
/* 634 */     ParameterAccess parameterAccess = new ParameterAccess();
/* 635 */     GetParameters getParameters = new GetParameters();
/*     */     
/* 637 */     String uri = this.registerGateway.getIdGIOT().replace("GIoT_", "").toLowerCase();
/*     */     
/* 639 */     if (!this.registerGateway.getPortInputGIOTWeb().equals("")) {
/* 640 */       PublicPort publicPort = new PublicPort();
/*     */       
/* 642 */       parameterAccess = getParameters.getParameterAccessMyGateway(this.registerGateway.getUserGIOT(), this.registerGateway.getPassGIOT(), this.registerGateway.getVirtualFrontier(), this.registerGateway.getPortVirtualFrontier(), this.registerGateway.getIdGIOT(), this.registerGateway.getIpServerSarlab());
/* 643 */       if (parameterAccess == null) {
/* 644 */         JOptionPane.showMessageDialog(null, bundle.getString("PanelListGateway.messagebox1"));
/*     */         return;
/*     */       } 
/* 647 */       this.registerGateway.setKeyGIOT(parameterAccess.getPasswordMyGateway());
/*     */ 
/*     */       
/* 650 */       publicPort = getParameters.getPublicPortMyGateway(parameterAccess.getUsuarioMyGateway(), parameterAccess.getPasswordMyGateway(), this.jTextIpServerSarlab.getText(), this.jTextPortServerSarlab1.getText(), parameterAccess.getUriPublicPort());
/*     */       
/* 652 */       if (publicPort == null) {
/* 653 */         JOptionPane.showMessageDialog(null, bundle.getString("PanelListGateway.messagebox2"));
/*     */         return;
/*     */       } 
/* 656 */       if (!publicPort.equals(this.registerGateway.getPortInputGIOTTunnel())) {
/* 657 */         this.registerGateway.setPortInputGIOTTunnel(publicPort.getPortGIoTTunnel());
                  // ANULADO PARA BACKED DE PRUEBA FUNCIONAL
/* 658 */         //registerGatewayOrg.setPortInputGIOTTunnel(publicPort.getPortGIoTTunnel());
/* 659 */         this.registerGateway.setPortInputGIOTWeb(publicPort.getPortGIoTWeb());
                  // ANULADO PARA BACKED DE PRUEBA FUNCIONAL
/* 660 */         //registerGatewayOrg.setPortInputGIOTWeb(publicPort.getPortGIoTWeb());
/* 661 */         //this.directoryGatewayRemote.set(key, registerGatewayOrg);
/* 662 */         this.directoryGatewayRemote.toFileXML(giveMeFileNameConfigXML());
/*     */       } 
/*     */     } else {
/* 665 */       parameterAccess = new ParameterAccess(this.registerGateway.getIpServerSarlab(), this.registerGateway.getPortServerSarlab(), this.registerGateway.getUserGIOT(), this.registerGateway.getPassGIOT(), this.registerGateway.getIdGIOT());
/*     */     } 
              // ANULADO PARA BACKED DE PRUEBA FUNCIONAL
/* 667 */     //this.clientName.titleConnect(this.registerGateway.getIdConnect());
/* 669 */     if (this.registerGateway.getIdExp().equals("")) {
/* 670 */       this.registerGateway.setIdExp("Null");
/*     */     }
/* 672 */     if (this.registerGateway.getTimeOUT().equals("")) {
/* 673 */       this.registerGateway.setTimeOUT("600");
/*     */     }
/*     */     // ANULADO PARA BACKED DE PRUEBA FUNCIONAL
/* 677 */     //this.clientName.connectGIOT(this.registerGateway);
/*     */   }
        
    @FXML   
    void jCheckBoxGIoTActionPerformed() {
/* 681 */     ButtonGIOT(this.jCheckBoxGIoT.isSelected());
/* 682 */     this.jCheckBoxGIoT.setDisable(true);
/* 683 */     this.jTextIpServerSarlab.setText(this.initVirtualFrontier);
/* 684 */     this.jTextPortServerSarlab1.setText(this.initPortVirtualFrontier);
/* 685 */     this.jTextIdGIOT.setText("");
/*     */   }
/*     */   
/*     private void formWindowClosing(WindowEvent evt) {
/* 689 *  if (this.clientName.sarlab == null) {
                this.clientName.jLabel6.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/LAN64x64.png")));
/* 691 *       this.clientName.jLabelNetConnect.setText("LAN");
/* 692 *       this.clientName.jButtonTools.setEnabled(false);
/* 693 *       this.clientName.jButtonForwarding.setEnabled(false);
/* 694 *       this.clientName.jButtonForwardingLook.setEnabled(false);
/* 695 *       this.clientName.jButtonProfile.setEnabled(false);
/*     *    } 
/* 697 *     this.clientName.toUpdate();
/*     * } ///*/

        public String giveMeFileNameConfigXML() {
            if (OS.isWindows()) {
                return "C:/sarlab/config/" + this.fileNameBaseXML;
        }
            return "/sarlab/config/" + this.fileNameBaseXML;
        }
        
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            initPanelListGateway();   
        }
}
