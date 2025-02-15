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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
//import javax.swing.ListModel;

/**
 *
 * @author alumno
 */
public class PanelListGatewayController implements Initializable {
    
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
    
    private Label icoHD; 
    private Button jButtonDel; 
    public Button jButtonNew; 
    public Button jButtonSave; 
    public Button jButtonSelect; 
    public CheckBox jCheckBoxGIoT; 
    private Label jLabel1; 
    private Label jLabelIdConnect; 
    private Label jLabelIdConnectT; 
    private Label jLabelIdExp;
    private Label jLabelIdGIOT;
    private Label jLabelIpServerSarlab;
    private Label jLabelKeyGIOT;
    private Label jLabelPass;
    private Label jLabelPortInputGIOTTunnel;
    private Label jLabelPortInputGIOTWeb;    
    private Label jLabelPortServerSarlab; 
    private Label jLabelTimeOut; 
    private Label jLabelUser; 
    private ListView<String> jList1;
    private Pane jPanel1; 
    private Pane jPanel2; 
    private Pane jPanel3; 
    private Pane jPanel5; 
    public PasswordField jPasswordPassGIOT; 
    private ScrollPane jScrollPane1; 
    public TextField jTextIdExp; 
    public TextField jTextIdGIOT; 
    public TextField jTextIpServerSarlab; 
    public TextField jTextKeyGIOT; 
    public TextField jTextPortInputGIOTTunnel; 
    public TextField jTextPortInputGIOTWeb; 
    public TextField jTextPortServerSarlab1; 
    public TextField jTextTimeOUT; 
    public TextField jTextUserGIOT; 
    public TextField jTextlIdConnect;
   
    private void initPanelListGateway() {
        
        icoHD = new Label();
        jButtonDel = new Button();
        jButtonNew = new Button();
        jButtonSave = new Button();
        jButtonSelect = new Button();
        jCheckBoxGIoT = new CheckBox();
        jLabel1 = new Label();
        jLabelIdConnect = new Label();
        jLabelIdConnectT = new Label();
        jLabelIdExp = new Label();
        jLabelIdGIOT = new Label();
        jLabelIpServerSarlab = new Label();
        jLabelKeyGIOT = new Label();
        jLabelPass = new Label();
        jLabelPortInputGIOTTunnel = new Label();
        jLabelPortInputGIOTWeb = new Label();
        jLabelPortServerSarlab = new Label();
        jLabelTimeOut = new Label();
        jLabelUser = new Label();
        jList1 = new ListView();
        jPanel1 = new Pane();
        jPanel2 = new Pane();
        jPanel3 = new Pane();
        jPanel5 = new Pane();
        jPasswordPassGIOT = new PasswordField ();
        jScrollPane1 = new ScrollPane();
        jTextIdExp = new TextField();
        jTextIdGIOT = new TextField();
        jTextIpServerSarlab = new TextField();
        jTextKeyGIOT = new TextField();
        jTextPortInputGIOTTunnel = new TextField();
        jTextPortInputGIOTWeb = new TextField();
        jTextPortServerSarlab1 = new TextField();
        jTextTimeOUT = new TextField();
        jTextUserGIOT = new TextField();
        jTextlIdConnect = new TextField();
        /////////////////SEPARACION
        testParameter();
        initJText();
        this.directoryGatewayRemote = new DirectoryGatewayRemote();
        this.directoryGatewayRemote = (DirectoryGatewayRemote)this.directoryGatewayRemote.generateTfromFileXML(giveMeFileNameConfigXML(), DirectoryGatewayRemote.class);
        if (this.directoryGatewayRemote == null) {
            this.directoryGatewayRemote = new DirectoryGatewayRemote();
        }
        this.listModel = observableArrayList(new ArrayList<>());
        for (int i =  0; i < this.directoryGatewayRemote.getDefaultListModel().size(); i++) {
            listModel.add((String)this.directoryGatewayRemote.getDefaultListModel().get(i)); 
        }
        //this.listModel = (ObservableList<String>) this.directoryGatewayRemote.getDefaultListModel();
        this.jList1.setItems(this.listModel);
        ButtonGIOT(false);
    }
    
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
    
    public String giveMeFileNameConfigXML() {
        if (OS.isWindows()) {
            return "C:/sarlab/config/" + this.fileNameBaseXML;
        }
        return "/sarlab/config/" + this.fileNameBaseXML;
    }
    
    
    private void jList1ValueChanged(ListSelectionEvent evt) {
/* 449 */     this.jCheckBoxGIoT.setDisable(true);
              int ind = jList1.getSelectionModel().getSelectedIndex();
/* 452 */     if (ind != -1) {
/* 453 */       String key = (String) this.listModel.get(ind);   
/* 455 */       RegisterGateway registerGatewayOrg = this.directoryGatewayRemote.get(key);
/* 456 */       this.registerGateway = registerGatewayOrg.clone();
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
    
     private void jButtonSaveActionPerformed(ActionEvent evt) {
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
    
     
       private void jButtonNewActionPerformed(ActionEvent evt) {
/* 591 */     this.jButtonSelect.setDisable(true);
/* 592 */     this.jButtonDel.setDisable(true);
/* 593 */     this.jCheckBoxGIoT.setSelected(false);
/* 594 */     ButtonGIOT(false);
/* 595 */     this.listModel = (ObservableList<String>) this.directoryGatewayRemote.getDefaultListModel();
/* 596 */     this.jList1.setItems(this.listModel);
/* 597 */     initJText();
/* 598 */     this.jCheckBoxGIoT.setDisable(false);
/* 599 */     this.jTextIdGIOT.setText(this.initPathVirtualFrontier);
       }
       
        private void jButtonDelActionPerformed(ActionEvent evt) {
/* 603 */     this.jButtonSelect.setDisable(true);
/* 604 */     this.jButtonDel.setDisable(true);
/* 605 */     int ind = jList1.getSelectionModel().getSelectedIndex();
/* 606 */     this.jCheckBoxGIoT.setSelected(false);
/* 607 */     this.jCheckBoxGIoT.setDisable(false);
/* 608 */     String key = (String) this.listModel.get(ind);
/*     */     
/* 610 */     this.registerGateway = this.directoryGatewayRemote.get(key);
/* 611 */     this.directoryGatewayRemote.remove(this.registerGateway);
              ObservableList<String> modelo = (ObservableList<String>) this.jList1.getSelectionModel();
              modelo.remove(ind);
/* 614 */     this.listModel = (ObservableList<String>) this.directoryGatewayRemote.getDefaultListModel();
/* 615 */     this.jList1.setItems(this.listModel);
/* 616 */     this.directoryGatewayRemote.toFileXML(giveMeFileNameConfigXML());
/* 617 */     initJText();
/* 618 */     this.jLabelPortInputGIOTTunnel.setVisible(false);
/* 619 */     this.jTextPortInputGIOTTunnel.setVisible(false);
/* 620 */     this.jLabelPortInputGIOTWeb.setVisible(false);
/* 621 */     this.jTextPortInputGIOTWeb.setVisible(false);
/*     */   }
       
    
        private void jButtonSelectActionPerformed(ActionEvent evt) {
/* 625 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
/* 626 */     int ind = jList1.getSelectionModel().getSelectedIndex();
/* 627 */     String key = (String) this.listModel.get(ind);
/*     */     
/* 629 */     RegisterGateway registerGatewayOrg = this.directoryGatewayRemote.get(key);
/*     */     
/* 631 */     this.registerGateway = registerGatewayOrg.clone();
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
/* 658 */         registerGatewayOrg.setPortInputGIOTTunnel(publicPort.getPortGIoTTunnel());
/* 659 */         this.registerGateway.setPortInputGIOTWeb(publicPort.getPortGIoTWeb());
/* 660 */         registerGatewayOrg.setPortInputGIOTWeb(publicPort.getPortGIoTWeb());
/* 661 */         this.directoryGatewayRemote.set(key, registerGatewayOrg);
/* 662 */         this.directoryGatewayRemote.toFileXML(giveMeFileNameConfigXML());
/*     */       } 
/*     */     } else {
/* 665 */       parameterAccess = new ParameterAccess(this.registerGateway.getIpServerSarlab(), this.registerGateway.getPortServerSarlab(), this.registerGateway.getUserGIOT(), this.registerGateway.getPassGIOT(), this.registerGateway.getIdGIOT());
/*     */     } 
/* 667 */     //this.clientName.titleConnect(this.registerGateway.getIdConnect());
/* 668 */     //setVisible(false);
/* 669 */     if (this.registerGateway.getIdExp().equals("")) {
/* 670 */       this.registerGateway.setIdExp("Null");
/*     */     }
/* 672 */     if (this.registerGateway.getTimeOUT().equals("")) {
/* 673 */       this.registerGateway.setTimeOUT("600");
/*     */     }
/*     */ 
/*     */     
/* 677 */     //this.clientName.connectGIOT(this.registerGateway);
/*     */   }
        
        
        private void jCheckBoxGIoTActionPerformed(ActionEvent evt) {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initPanelListGateway();
    }
    
}
