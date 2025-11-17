/*      */ package es.uhu.sarlab.discovery.controller;

/*      */ import clientsarlab.EJSClientSarlab2;
/*      */ import clientsarlab.GIOTClientSarlab3;
import es.nebsyst.sarlabinit.controller.PanelMainController;
import es.nebsyst.sarlabinit.main.FXMLLoaderManager;
import es.nebsyst.sarlabinit.main.StageManager;
/*      */ import es.uhu.execapp.system.ExecApp;
/*      */ import es.uhu.sarlab.digexlab.experience.model.Connection;
/*      */ import es.uhu.sarlab.discovery.ClientGIOTLocatorService;
/*      */ import es.uhu.sarlab.discovery.GetParameters.GetParameters;
/*      */ import es.uhu.sarlab.discovery.ServerUDPThread;
import es.uhu.sarlab.discovery.UtilIp;
/*      */ import es.uhu.sarlab.discovery.model.DirectoryRegisterName;
import es.uhu.sarlab.discovery.model.DirectoryServiceRegisterName;
/*      */ import es.uhu.sarlab.discovery.model.RegisterGateway;
/*      */ import es.uhu.sarlab.discovery.model.RegisterName;
import es.uhu.sarlab.discovery.view.model.ProgressBarDiscovery;
import es.uhu.sarlab.discovery.vista.BarLogo;
import es.uhu.sarlab.discovery.vista.InterfazBarLogo;
import javafx.scene.layout.Pane;
/*      */ import es.uhu.sarlab.discovery.webservlet.model.HashMapExperienceWeb;
/*      */ import es.uhu.sarlab.discovery.webservlet.model.ListForwardingL;
import es.uhu.sarlab.utils.DirectoryFilesUtility;
/*      */ import es.uhu.sarlab.utils.OS;
/*      */ import es.uhu.sarlab.utils.PropertiesFiles;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
/*      */ import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.net.InetAddress;
/*      */ import java.net.ServerSocket;
import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.Locale;
import java.util.Optional;
/*      */ import java.util.Properties;
/*      */ import java.util.ResourceBundle;

import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import modbussarlab.MasterModbus;

import org.apache.log4j.PropertyConfigurator;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import static javafx.stage.Modality.APPLICATION_MODAL;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/*      */ 

public class PanelClientServerNameController implements Initializable, InterfazBarLogo, PropertyChangeListener {
    
/*   62 */  public final static Logger log = Logger.getLogger(PanelClientServerNameController.class.getName());
/*      */  String userLog;
/*      */  String passwdLog;
/*      */  boolean enteruserpas;
/*      */  BarLogo barLogo;
/*   71 */  String url = "";
/*      */  GIOTClientSarlab3 sarlab;
/*   73 */  DirectoryServiceRegisterName directoryRegisterNameSarlab = null;
/*   74 */  RegisterGateway registerGatewayOld = null;
/*   75 */  String myirtualFrontierOld = null;
/*   76 */  String myURIMyGatewayOld = null;
/*      */  RegisterName selecRegisterName;
/*   78 */  int portLocalVLC = 0;
/*   79 */  int portLocalHTTP = 0;
/*   85 */  ProgressBarDiscovery progressBar = new ProgressBarDiscovery();
/*      */  ProgressMonitor progressMonitor;
/*      */  private Task task;
/*      */  private Sentinel sentinel;
/*   92 */  String fileNameBase = "host.properties"; 
/*      */  ServerUDPThread serverUDPThread;
/*   95 */  ByteArrayOutputStream baos = null;
/*   96 */  PrintStream ps = null;
/*   97 */  PrintStream old = System.out;
/*   99 */  String locale = null; 

            private Stage panelListGatewayStage;
            private Stage panelEditConfigLocalStage;
            private Stage panelConectionLocalhostStage;
            private Stage panelNodoFrowardingLStage;
            private Stage panelConectionExperienceStage;
            private Stage panelConsoleStage;
            
            private PanelMainController panelMainController;
            
            private PanelConectionLocalhostController panelConectionLocalhostController;
            private PanelNodoFrowardingLController panelNodoFrowardingLController;
            private PanelConectionExperienceController panelConectionExperienceController;
            private PanelConsoleController panelConsoleController;
            private PanelListGatewayController panelListGatewayController;
            
            private ToggleGroup buttonGroup1; // misma funcionalidad que ButtonGroup pero diferente funcionamiento
            @FXML
            private Button jButton1; 
            @FXML
            public Button jButtonActualizar;
            @FXML
            private Button jButtonConfiguracionLocal; 
            @FXML
            private Button jButtonDesktop; 
            @FXML
            public Button jButtonForwarding; 
            @FXML
            public Button jButtonForwardingLook; 
            @FXML
            private Button jButtonLocal; 
            private Button jButtonLogin; 
            @FXML
            private Button jButtonModbus; 
            @FXML
            private Button jButtonNavegador; 
            @FXML
            public Button jButtonProfile; 
            @FXML
            private Button jButtonRemoto; 
            @FXML
            private Button jButtonSFDTP;
            @FXML
            private Button jButtonSsh; 
            @FXML
            private Button jButtonTelnet; 
            @FXML
            public Button jButtonTools; 
            @FXML
            private Button jButtonVNC;           
            @FXML      
            private Dialog jDialog1; 
            @FXML
            private Label jLabel1; 
            private Label jLabel2; 
            private Label jLabel3; 
            private Label jLabel4; 
            private Label jLabel5;
            public Label jLabel6;
            private Label jLabel7;
            private Label jLabel8; 
            @FXML
            private Label jLabel10;
            @FXML
            private Label title;
            @FXML
            private Label jLabelList; 
            public Label jLabelNetConnect; 
            @FXML
            private Label jLabelTituloService; 
            @FXML
            public ListView<String> jList1; 
             
            @FXML
            private TitledPane jPanel2;
            @FXML
            private TitledPane jPanel6;
            
            
            @FXML
            private RadioButton jRadioAxi; 
            @FXML
            private RadioButton jRadioEnlarge; 
            @FXML
            private RadioButton jRadioFree; 
            private ScrollPane jScrollPane1; 
            @FXML
            private TextField jTextFieldDesktop;
            @FXML
            private TextField jTextFieldModbus; 
            @FXML
            public TextField jTextFieldPortNav;
            @FXML
            private TextField jTextFieldSsh; 
            @FXML
            private TextField jTextFieldTelnet; 
            @FXML
            public TextField jTextFieldURL; 
            @FXML
            private TextField jTextFieldVNC; 
            @FXML
            private TextField jTextFieldsFTP; 
            private TextField lineapass; 
            private TextField lineausuario;
            
            public ByteArrayOutputStream getBaos() { 
                return this.baos; 
            }
             
/*      */   private void initPanelClientServerName() {
/*  105 */     String configLog4j = OS.isWindows() ? "C:/sarlab/config/log4jsarlabdiscoveryview.properties" : "/sarlab/config/log4jsarlabdiscoveryview.properties";
/*  106 */     PropertyConfigurator.configure(configLog4j);
/*  107 */     this.sarlab = null;
/*  108 */     if (this.locale == null) {
/*  109 */       this.locale = PropertiesFiles.getPropertiesValueFile("locale", PropertiesFiles.CONFIG_FILE);
/*  110 */       if (this.locale != null) {
/*  111 */         switch (this.locale) { case "es":
/*  112 */             Locale.setDefault(new Locale("es", "ES")); break;
/*      */           default:
/*  114 */             Locale.setDefault(new Locale("en", "GB"));
/*      */             break; }
/*      */       }
/*      */     } 
/*  119 */     this.baos = new ByteArrayOutputStream();
/*  120 */     this.ps = new PrintStream(this.baos);
/*  121 */     System.setOut(this.ps);
               initPanelMainController();
/*  122 */     initComponents();
/*  124 */     ButtonEnabled(false); // poner en false
/*  125 */     this.jButtonTools.setDisable(false); // poner en true
/*  126 */     this.jButtonForwarding.setDisable(false); // poner en true
/*  127 */     this.jButtonForwardingLook.setDisable(false); // poner en true
               
/*  128 */     if (!OS.isWindows()) {
/*  129 */       this.jButtonLocal.setVisible(false);
/*      */     }
/*  131 */     this.jButtonProfile.setDisable(false); // poner en true
/*  132 */     this.sentinel = new Sentinel();
               // Se añade para que se muestre el botón Actualizar al abrir el panel
               this.jButtonActualizar.setVisible(true);          
               // Grupo de los botones Radio
               ToggleGroup tGroup = new ToggleGroup();
               jRadioFree.setToggleGroup(tGroup);
               jRadioEnlarge.setToggleGroup(tGroup);
               jRadioAxi.setToggleGroup(tGroup);  
/*      */   }

       // INICIO - Added backend
       public void initList() {
           ObservableList<String> ejemplos = FXCollections.observableArrayList("Ejemplo 1", "Ejemplo 2", "Ejemplo 3");
           jList1.setItems(ejemplos); 
       }
       // FIN - Added backend
       
       // INICIO - Added backend
       private void initPanelMainController() {
           FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelMain");
           this.panelMainController = fxmlLoader.getController();
       }
       // FIN - Added backend
    
       // INICIO - Added backend
       private String getBundleLanguage() { 
           String language;          
           if (this.panelMainController.getSelectedLanguage().equals("English")) {
               language = "_en_GB";
           } else {
               language = "_es_ES";
           }  
           return language;
       }
       // FIN - Added backend
       
       // INICIO - Added backend
       private void addCss(Scene scene) {
            if (scene.getStylesheets().isEmpty()) {
                String[] cssPaths = new String[] {
                    "../../../../styles/base.css",
                    "../../../../styles/utilities.css",
                    "../../../../styles/buttons.css",
                    "../../../../styles/inputs.css",
                    "../../../../styles/lists_tables.css",
                    "../../../../styles/containers.css",
                    "../../../../styles/extras.css",
                };
                for (String p : cssPaths) {
                    URL url = getClass().getResource(p);
                    if (url != null) {
                        scene.getStylesheets().add(url.toExternalForm());
                    }
                }         
            }     
       }
       // FIN - Added backend
       
       // METODO PARA REALIZAR PRUEBAS
       @FXML
       void jButtonTestActionPerformed() throws IOException {          
            if (panelConectionExperienceStage == null) {
                panelConectionExperienceStage = StageManager.getInstance().getStage("PanelConectionExperience");
                FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelConectionExperience");
                Scene scene = new Scene(fxmlLoader.load());
                addCss(scene);
                panelConectionExperienceStage.setScene(scene);
            }
            if (panelConectionExperienceController == null) {
                FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelConectionExperience");
                panelConectionExperienceController = fxmlLoader.getController();
            }
            panelConectionExperienceController.initList();      
            panelConectionExperienceStage.show();
       }

/*      */   public void ButtonEnabled(boolean enable) {
/*  136 */     this.jButtonDesktop.setDisable(enable); // Previous value: !enable
/*  137 */     this.jButtonModbus.setDisable(enable); // Previous value: !enable
/*  138 */     this.jButtonSFDTP.setDisable(enable); // Previous value: !enable
/*  139 */     this.jButtonSsh.setDisable(enable); // Previous value: !enable
/*  140 */     this.jButtonTelnet.setDisable(enable); // Previous value: !enable
/*  141 */     this.jButtonVNC.setDisable(enable); // Previous value: !enable

/*  142 */     this.jButtonNavegador.setDisable(!enable);
/*  143 */     this.jButtonActualizar.setDisable(enable); // debe estar activado al inicio
/*  144 */     this.jTextFieldURL.setEditable(enable);
/*  145 */     this.jTextFieldDesktop.setEditable(enable);
/*  146 */     this.jTextFieldModbus.setEditable(enable);
/*  147 */     this.jTextFieldPortNav.setEditable(enable);
/*  148 */     this.jTextFieldSsh.setEditable(enable);
/*  149 */     this.jTextFieldTelnet.setEditable(enable);
/*  150 */     this.jTextFieldVNC.setEditable(enable);
/*  151 */     this.jTextFieldsFTP.setEditable(enable);
/*      */   }

/*      */   public void init() {
                if (this.barLogo == null) {
                    this.barLogo = new BarLogo((InterfazBarLogo) this);
/*  163 */          this.barLogo.init();
                    this.barLogo = null; // añadido para correcto funcionamiento
                }             
/*      */   }
/*      */   
/*      */   public String ipInit() {
/*  167 */     if (this.directoryRegisterNameSarlab == null) {
/*  168 */       return "";
/*      */     }
/*  170 */     return this.directoryRegisterNameSarlab.iPInit();
/*      */   }
/*      */   
/*      */   public void setListModel(ObservableList<String> observableListModel) {
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/*  174 */     //this.jList1.setItems(observableListModel); POR BACKEND
/*      */   }
/*      */   
/*      */   public void findSarlabComplet() {
/*  178 */     ServerUDPThread serverUDPThread = new ServerUDPThread(4448, 1024);
/*  179 */     serverUDPThread.start();
/*  180 */     ClientGIOTLocatorService adClientName = new ClientGIOTLocatorService();
/*  181 */     adClientName.multicast("Marco Marquez 3", "224.0.0.251", 4446, 4449);
/*  182 */     delay(5000);
/*  183 */     serverUDPThread.end();
/*  184 */     DirectoryRegisterName directoryRegisterName = serverUDPThread.getDirectoryRegisterName();
/*  185 */     setListModel((ObservableList<String>) serverUDPThread.getListModel());
/*      */   }
/*      */   
/*      */   public static void delay(int t) {
/*      */     try {
/*  190 */       Thread.sleep(t);
/*  191 */     } catch (InterruptedException interruptedException) {}
/*      */   }
/*      */ 
/*      */   
/*      */   public void titleConnect(String titulo) {
/*  196 */     this.jLabelNetConnect.setText(titulo);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addForwardingLocalhostL(String deviceName, String ipRemote, int portRemote, int portLocal) {
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAKL
/*  201 */     //this.sarlab.addconnectionLocalhostL(deviceName, ipRemote, portRemote, portLocal);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addForwardingLocalAdressL(String deviceName, String ipRemote, int portRemote, int portLocal) {
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAKL
/*  206 */     //this.sarlab.addconnectionLocalAddressL(deviceName, ipRemote, portRemote, portLocal);
/*      */   }
/*      */   
/*      */   public void addForwardingExperienceLocalhostL(ListForwardingL listForwardingL) {
/*  210 */     ArrayList<Connection> a = listForwardingL.getListconnect();
/*  211 */     for (Connection c : a) {
/*  212 */       addForwardingLocalhostL(c.getDescription(), c.getIpRemote(), c.getPortRemote(), c.getPortLocal());
/*      */     }
/*      */   }
/*      */ 
/*      */   private void initComponents() {
/*  225 */     this.buttonGroup1 = new ToggleGroup();
/*  226 */     this.jDialog1 = new Dialog();
/*  228 */     this.jLabel2 = new Label();
/*  230 */     this.lineausuario = new TextField();
/*  231 */     this.jLabel7 = new Label();
/*  232 */     this.jLabel8 = new Label();
/*  233 */     this.lineapass = new PasswordField();
/*  235 */     this.jButtonLogin = new Button();
/*  245 */     this.jScrollPane1 = new ScrollPane();
/*  248 */     this.jLabel4 = new Label();
/*  249 */     this.jLabel5 = new Label();
/*  250 */     this.jLabel6 = new Label();
/*  251 */     this.jLabelNetConnect = new Label();
/*  274 */     this.jLabel3 = new Label();  ;
               this.jDialog1.setWidth(400);
               this.jDialog1.setHeight(219);
               // COMPROBAR SI LA MODALIDAD APLICADA ES OK
/*  282 */     this.jDialog1.initModality(APPLICATION_MODAL);
/*  283 */     this.jDialog1.setResizable(false);

/*  290 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelClientServerName");
/*  291 */     this.title.setText(bundle.getString("PanelClientServerName.title"));
               this.jLabel2.setText(bundle.getString("PanelClientServerName.jLabel2.text_1"));
/*      */     
/*      */     /*   APLICAR EN EVENTO AL SELECCIONAR LA LINEA DE USUARIO  */ 
/*  310 */   //  this.lineausuario.addKeyListener(new KeyAdapter() {
/*      */   //        public void keyPressed(KeyEvent evt) {
/*  312 */   //          PanelClientServerNameController.this.lineausuarioKeyPressed(evt);
/*      */   //        }
/*      */   //     });
/*      */     
/*  316 */   
/*  317 */     this.jLabel7.setText(bundle.getString("PanelClientServerName.jLabel7.text_1"));  
/*  319 */    
/*  320 */     this.jLabel8.setText(bundle.getString("PanelClientServerName.jLabel8.text_1"));
/*      */     /* APLICAR EN EVENTO AL SELECCIONAR LA LINEA DE PASSWORD  */     
/*  322 *///     this.lineapass.addKeyListener(new KeyAdapter() {
/*      *///           public void keyPressed(KeyEvent evt) {
/*  324 *///             PanelClientServerNameController.this.lineapassKeyPressed(evt);
/*      *///           }
/*      *///         });
  
/*  359 */     this.jButtonLogin.setText(bundle.getString("PanelClientServerName.jButtonLogin.text_1"));
/*  360 *///     this.jButtonLogin.addActionListener(new ActionListener() {
/*      *///           public void actionPerformed(ActionEvent evt) {
/*  362 *///             PanelClientServerNameController.this.jButtonLoginActionPerformed(evt);
/*      *///           }
/*      *///         });
/*  365 *///     this.jButtonLogin.addKeyListener(new KeyAdapter() {
/*      *///           public void keyPressed(KeyEvent evt) {
/*  367 *///             PanelClientServerNameController.this.jButtonLoginKeyPressed(evt);
/*      *///           }
/*      *///         });
/*      */    
/*  418 */     //this.jPanelBotones.setBorder(BorderFactory.createTitledBorder(""));
/*      */     
               Image iconJButtonActualizar = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/actualiza.png").toExternalForm());
               ImageView iconViewJButtonActualizar = new ImageView(iconJButtonActualizar);
               this.jButtonActualizar.setGraphic(iconViewJButtonActualizar);    
/*  421 */     this.jButtonActualizar.setText(bundle.getString("PanelClientServerName.jButtonActualizar.text"));

               Image iconJButtonRemoto = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/remota.png").toExternalForm());
               ImageView iconViewJButtonRemoto = new ImageView(iconJButtonRemoto);
               this.jButtonRemoto.setGraphic(iconViewJButtonRemoto);
/*  429 */     this.jButtonRemoto.setText(bundle.getString("PanelClientServerName.jButtonRemoto.text"));
               Tooltip jButtonRemotoTooltip = new Tooltip("PanelClientServerName.jButtonRemoto.toolTipText");
               this.jButtonRemoto.setTooltip(jButtonRemotoTooltip);
/*  431 */       
               Image iconJButtonLocal = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/lan24x24.png").toExternalForm());
               ImageView iconViewJButtonLocal = new ImageView(iconJButtonLocal);
/*  437 */     this.jButtonLocal.setGraphic(iconViewJButtonLocal);
/*  438 */     this.jButtonLocal.setText(bundle.getString("PanelClientServerName.jButtonConfiguracionLocal.text")); // permutado con jButtonConfiguracionLocal
              
               Image iconJButtonConfiguracionLocal = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/config.png").toExternalForm());
               ImageView iconViewJButtonConfiguracionLocal = new ImageView(iconJButtonConfiguracionLocal);
/*  437 */     this.jButtonConfiguracionLocal.setGraphic(iconViewJButtonConfiguracionLocal);   
/*  446 */     this.jButtonConfiguracionLocal.setText(bundle.getString("PanelClientServerName.jButtonLocal.text")); // permutado con jButtonLocal
/*  447 */    
               Image iconJButton1 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/demo.png").toExternalForm());
               ImageView iconViewJButton1 = new ImageView(iconJButton1);
/*  437 */     this.jButton1.setGraphic(iconViewJButton1);  
/*  454 */     this.jButton1.setText(bundle.getString("PanelClientServerName.jButton1.text_1"));
 
               this.jPanel2.setText(bundle.getString("PanelClientServerName.jPanel2.border.title"));
                       
/*  499 */     this.jLabelList.setText(bundle.getString("PanelClientServerName.jLabelList.text"));
/*      */     
/*  501 */     //this.jPanel1.setBorder(BorderFactory.createTitledBorder(""));
/*      */     
               Tooltip jList1Tooltip = new Tooltip("PanelClientServerName.jList1.toolTipText");
               this.jList1.setTooltip(jList1Tooltip);
/*  505 */    
/*  550 */     //this.jPanel3.setBorder(BorderFactory.createTitledBorder(""));
/*      */     

               Image iconJLabel4 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/Remote-Desktop64x64.png").toExternalForm());
               ImageView iconViewJLabel4 = new ImageView(iconJLabel4);
/*  437 */     this.jLabel4.setGraphic(iconViewJLabel4);  

/*  556 */     this.jLabel5.setText(bundle.getString("PanelClientServerName.jLabel5.text"));
/*      */     
               Image iconJLabel6 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/LAN64x64.png").toExternalForm());
               ImageView iconViewJLabel6 = new ImageView(iconJLabel6);
/*  437 */     this.jLabel4.setGraphic(iconViewJLabel6);  
/*  558 */    
/*  561 */     this.jLabelNetConnect.setText(bundle.getString("PanelClientServerName.jLabelNetConnect.text"));
/*      */     
               this.jPanel6.setText(bundle.getString("PanelClientServerName.jPanel6.border.title"));


               Image iconJButtonDesktop = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/Display 48_24x24-32.gif").toExternalForm());
               ImageView iconViewJButtonDesktop = new ImageView(iconJButtonDesktop);
/*  437 */     this.jButtonDesktop.setGraphic(iconViewJButtonDesktop); 
/*  600 */     this.jButtonDesktop.setText(bundle.getString("PanelClientServerName.jButtonDesktop.text"));
/*  601 */       
/*  607 */     this.jTextFieldDesktop.setText(bundle.getString("PanelClientServerName.jTextFieldDesktop.text"));
               this.jTextFieldDesktop.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
                        // TextField gana el foco del teclado
                        if (newValue) {
                            jTextFieldDesktopFocusGained();
                        // TextField pierde el foco del teclado
                        } else {
                            jTextFieldDesktopFocusLost();
                        }
                    } 
               });
/*  608 */    
               Image iconJButtonVNC = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/vnc.png").toExternalForm());
               ImageView iconViewJButtonVNC = new ImageView(iconJButtonVNC);
/*  437 */     this.jButtonVNC.setGraphic(iconViewJButtonVNC);  
/*  623 */     this.jButtonVNC.setText(bundle.getString("PanelClientServerName.jButtonVNC.text"));
/*  630 */     this.jTextFieldVNC.setText(bundle.getString("PanelClientServerName.jTextFieldVNC.text"));
               this.jTextFieldVNC.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
                        // TextField gana el foco del teclado
                        if (newValue) {
                            jTextFieldVNCFocusGained();
                        // TextField pierde el foco del teclado
                        } else {
                            jTextFieldVNCFocusLost();
                        }
                    } 
               });
/*      */     
               Image iconJButtonTelnet = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/telnet.png").toExternalForm());
               ImageView iconViewJButtonTelnet = new ImageView(iconJButtonTelnet);
/*  437 */     this.jButtonTelnet.setGraphic(iconViewJButtonTelnet);  
/*  646 */     this.jButtonTelnet.setText(bundle.getString("PanelClientServerName.jButtonTelnet.text"));
/*  653 */     this.jTextFieldTelnet.setText(bundle.getString("PanelClientServerName.jTextFieldTelnet.text"));
               this.jTextFieldTelnet.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
                        // TextField gana el foco del teclado
                        if (newValue) {
                            jTextFieldTelnetFocusGained();
                        // TextField pierde el foco del teclado
                        } else {
                            jTextFieldTelnetFocusLost();
                        }
                    } 
               });

               Image iconJButtonSsh = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/ssh.png").toExternalForm());
               ImageView iconViewJButtonSsh = new ImageView(iconJButtonSsh);
/*  437 */     this.jButtonSsh.setGraphic(iconViewJButtonSsh);   
/*  669 */     this.jButtonSsh.setText(bundle.getString("PanelClientServerName.jButtonSsh.text"));
/*  676 */     this.jTextFieldSsh.setText(bundle.getString("PanelClientServerName.jTextFieldSsh.text"));
               this.jTextFieldSsh.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
                        // TextField gana el foco del teclado
                        if (newValue) {
                            jTextFieldSshFocusGained();
                        // TextField pierde el foco del teclado
                        } else {
                            jTextFieldSshFocusLost();
                        }
                    } 
               });
/*  677 */     
               Image iconJButtonSFDTP = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/Folder 102_24x24-32.gif").toExternalForm());
               ImageView iconViewJButtonSFDTP = new ImageView(iconJButtonSFDTP);
/*  437 */     this.jButtonSFDTP.setGraphic(iconViewJButtonSFDTP);    
/*  692 */     this.jButtonSFDTP.setText(bundle.getString("PanelClientServerName.jButtonSFDTP.text"));
/*  699 */     this.jTextFieldsFTP.setText(bundle.getString("PanelClientServerName.jTextFieldsFTP.text"));
               Tooltip jTextFieldsFTPTooltip = new Tooltip("PanelClientServerName.jTextFieldsFTP.toolTipText");
               this.jTextFieldsFTP.setTooltip(jTextFieldsFTPTooltip);
               this.jTextFieldsFTP.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
                        // TextField gana el foco del teclado
                        if (newValue) {
                            jTextFieldsFTPFocusGained();
                        // TextField pierde el foco del teclado
                        } else {
                            jTextFieldsFTPFocusLost();
                        }
                    } 
               });
/*  701 */     
               Image iconJButtonModbus = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/modbus.png").toExternalForm());
               ImageView iconViewJButtonModbus = new ImageView(iconJButtonModbus);
/*  437 */     this.jButtonModbus.setGraphic(iconViewJButtonModbus);
/*  716 */     this.jButtonModbus.setText(bundle.getString("PanelClientServerName.jButtonModbus.text"));
/*  723 */     this.jTextFieldModbus.setText(bundle.getString("PanelClientServerName.jTextFieldModbus.text"));
               Tooltip jTextFieldModbusTooltip = new Tooltip("PanelClientServerName.jTextFieldModbus.toolTipText");
               this.jTextFieldModbus.setTooltip(jTextFieldModbusTooltip);
/*  725 */     this.jTextFieldModbus.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
                        // TextField gana el foco del teclado
                        if (newValue) {
                            jTextFieldModbusFocusGained();
                        // TextField pierde el foco del teclado
                        } else {
                            jTextFieldModbusFocusLost();
                        }
                    } 
               });

/*  740 */     this.jLabelTituloService.setText(bundle.getString("PanelClientServerName.jLabelTituloService.text"));
/*      */     
               Image iconJButtonTools = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/tools.png").toExternalForm());
               ImageView iconViewJButtonTools = new ImageView(iconJButtonTools);
/*  437 */     this.jButtonTools.setGraphic(iconViewJButtonTools);
/*  743 */     this.jButtonTools.setText(bundle.getString("PanelClientServerName.jButtonTools.text"));

               Image iconJButtonForwarding = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/tunel.png").toExternalForm());
               ImageView iconViewJButtonForwarding = new ImageView(iconJButtonForwarding);
/*  437 */     this.jButtonForwarding.setGraphic(iconViewJButtonForwarding);
/*  751 */     this.jButtonForwarding.setText(bundle.getString("PanelClientServerName.jButtonForwarding.text"));

               Image iconJButtonForwardingLook = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/busco.png").toExternalForm());
               ImageView iconViewJButtonForwardingLook = new ImageView(iconJButtonForwardingLook);
/*  437 */     this.jButtonForwardingLook.setGraphic(iconViewJButtonForwardingLook);
               if (this.panelMainController.getSelectedLanguage().equals("English")) {
                   this.jButtonForwardingLook.setText("RedirectionLook");
               } else {
                   this.jButtonForwardingLook.setText("RedireccionBuscar");
               } 
/*  759 */    
               Image iconJButtonProfile = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/ver.png").toExternalForm());
               ImageView iconViewJButtonProfile = new ImageView(iconJButtonProfile);
/*  437 */     this.jButtonProfile.setGraphic(iconViewJButtonProfile);
               if (this.panelMainController.getSelectedLanguage().equals("English")) {
                   this.jButtonProfile.setText("Profile");
               } else {
                   this.jButtonProfile.setText("Perfil");
               } 

/*  858 */     //this.jPanelNavegador.setBorder(BorderFactory.createTitledBorder(bundle.getString("PanelClientServerName.jPanelNavegador.border.title")));
/*      */     Image iconJButtonNavegador = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/navegador.png").toExternalForm());
               ImageView iconViewJButtonNavegador = new ImageView(iconJButtonNavegador);
/*  437 */     this.jButtonNavegador.setGraphic(iconViewJButtonNavegador);
/*  861 */     this.jButtonNavegador.setText(bundle.getString("PanelClientServerName.jButtonNavegador.text"));
/*  862 */     
/*  869 */     this.jLabel1.setText(bundle.getString("PanelClientServerName.jLabel1.text"));
               this.jLabel10.setText(bundle.getString("PanelClientServerName.jLabel1.text"));
/*      */     
/*  871 */     this.jTextFieldPortNav.setText(bundle.getString("PanelClientServerName.jTextFieldPortNav.text"));
/*  872 */     this.jTextFieldPortNav.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
                        // TextField gana el foco del teclado
                        if (newValue) {
                            jTextFieldPortNavFocusGained();
                        // TextField pierde el foco del teclado
                        } else {
                            jTextFieldPortNavFocusLost();
                        }
                    } 
               });
/*  880 */     
/*  887 */     this.jLabel3.setText(bundle.getString("PanelClientServerName.jLabel3.text"));
/*      */     
               Tooltip jTextFieldURLTooltip = new Tooltip("PanelClientServerName.jTextFieldURL.toolTipText");
               this.jTextFieldURL.setTooltip(jTextFieldURLTooltip);

/*  896 */     //this.buttonGroup1.add(this.jRadioAxi);
/*  898 */     this.jRadioAxi.setText(bundle.getString("PanelClientServerName.jRadioAxi.text"));
 
/*  905 */     //this.buttonGroup1.add(this.jRadioEnlarge);
/*  907 */     this.jRadioEnlarge.setText(bundle.getString("PanelClientServerName.jRadioEnlarge.text"));
 
/*  914 */     //this.buttonGroup1.add(this.jRadioFree);
/*  916 */     this.jRadioFree.setText(bundle.getString("PanelClientServerName.jRadioFree.text"));

/*      */   }
/*      */   
/*      */   public void toUpdate() {
/*  989 */     ObservableList<String> observableListModel = observableArrayList(new ArrayList<String>());
/*  990 */     this.jList1.setItems(observableListModel);
/*  991 */     if (this.sarlab == null) {
                 if (this.barLogo == null) {
                     this.barLogo = new BarLogo((InterfazBarLogo) this);
                     this.barLogo.init();
                     this.barLogo = null; // añadido para correcto funcionamiento
                 } 
/*  993 */       ButtonEnabled(false); // poner en false
/*  994 */       this.jButtonActualizar.setDisable(true); // poner en true
/*      */     } else {
/*  996 */       GetParameters getParameters = new GetParameters();
/*  997 */       this.directoryRegisterNameSarlab = getParameters.getDirectoryRegisterNameSarlab(this.registerGatewayOld);
/*  998 */       if (this.directoryRegisterNameSarlab != null) {        
/* 1000 */         observableListModel = (ObservableList<String>) this.directoryRegisterNameSarlab.getListModel();
/* 1001 */         this.jList1.setItems(observableListModel);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
             @FXML
/*      */   void jButtonActualizarActionPerformed() {
/* 1007 */     removeService();
/* 1008 */     toUpdate();
/* 1009 */     if (this.directoryRegisterNameSarlab != null);
               // INICIO - Added backed
               initList();
               // FIN - Added backed
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jTextFieldDesktopActionPerformed() {  
/* 1017 */     String port = this.jTextFieldDesktop.getText();
/* 1018 */     if ("".equals(port)) {
/* 1019 */       this.jTextFieldDesktop.setText("3389");
/* 1020 */       port = "3389";
/*      */     } 
/*      */   }
/*      */   
             @FXML
/*      */   void jList1MouseClicked() {
/*      */     RegisterName registerName;
/* 1026 */     ButtonEnabled(true);
/* 1027 */     
               int ind = this.jList1.getSelectionModel().getSelectedIndex();
/*      */ 
/*      */     
/* 1030 */     if (this.sarlab == null) {
/*      */       // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1032 */       //registerName = this.serverUDPThread.getRegisterNametoArray(ind);
                 // INICIO - Added backend
                 registerName = new RegisterName("127.0.0.1", "SarlabNET", "SarlabINIT");
                 // FIN - Added backend
/*      */     } else {
/* 1034 */       registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1036 */     if (registerName != null) {
/* 1037 */       if (this.sarlab == null) {
/* 1038 */         this.url = "http://" + registerName.getIp();
/* 1039 */         String port = this.jTextFieldPortNav.getText();
/* 1040 */         if (!"80".equals(port)) {
/* 1041 */           this.url = "http://" + registerName.getIp() + ":" + this.jTextFieldPortNav.getText();
/*      */         }
/* 1043 */         this.jTextFieldURL.setText(this.url);
/*      */       } 
/*      */       // INICIO - Added backend
                 registerName = new RegisterName("127.0.0.1", "SarlabNET", "SarlabINIT");
                 // FIN - Added backend
/* 1046 */       this.selecRegisterName = registerName;
                 // INICIO - Added backend
                 registerName = new RegisterName("127.0.0.1", "SarlabNET", "SarlabINIT");
                 // FIN - Added backend
/* 1047 */       selectService(registerName);
/*      */     } 
/*      */   }
/*      */ 
/*      */   public void selectService(RegisterName registerName) {
/* 1055 */     this.jTextFieldDesktop.setEditable(registerName.isRdesktop());
/* 1056 */     this.jTextFieldDesktop.setText(Integer.toString(registerName.getRdesktopPort()));
/* 1057 */     this.jButtonDesktop.setVisible(registerName.isRdesktop());
/* 1058 */     this.jTextFieldVNC.setEditable(registerName.isVnc());
/* 1059 */     this.jTextFieldVNC.setText(Integer.toString(registerName.getVncPort()));
/* 1060 */     this.jButtonVNC.setVisible(registerName.isVnc());
/* 1061 */     this.jTextFieldTelnet.setEditable(registerName.isTelnet());
/* 1062 */     this.jTextFieldTelnet.setText(Integer.toString(registerName.getTelnetPort()));
/* 1063 */     this.jButtonTelnet.setVisible(registerName.isTelnet());
/* 1064 */     this.jTextFieldSsh.setEditable(registerName.isSsh());
/* 1065 */     this.jTextFieldSsh.setText(Integer.toString(registerName.getSshPort()));
/* 1066 */     this.jButtonSsh.setVisible(registerName.isSsh());
/* 1067 */     this.jTextFieldsFTP.setEditable(registerName.isSftp());
/* 1068 */     this.jTextFieldsFTP.setText(Integer.toString(registerName.getSftpPort()));
/* 1069 */     this.jButtonSFDTP.setVisible(registerName.isSftp());
/* 1070 */     this.jTextFieldModbus.setEditable(registerName.isModbus());
/* 1071 */     this.jTextFieldModbus.setText(Integer.toString(registerName.getModbusPort()));
/* 1072 */     this.jButtonModbus.setVisible(registerName.isModbus());
/* 1073 */     this.jTextFieldURL.setEditable(registerName.isHttp());
/* 1074 */     this.jTextFieldPortNav.setEditable(registerName.isHttp());
/* 1075 */     this.jRadioAxi.setVisible(registerName.isHttp());
/* 1076 */     this.jRadioEnlarge.setVisible(registerName.isHttp());
/* 1077 */     this.jRadioFree.setVisible(registerName.isHttp());
/* 1078 */     this.jTextFieldURL.setText(registerName.getHttpUri());
/* 1079 */     String sPort = Integer.toString(registerName.getHttpPort());
/* 1080 */     String deviceName = registerName.getIp() + ":" + sPort;
/* 1081 */     if (this.sarlab == null) {
/* 1082 */       this.jTextFieldPortNav.setText(sPort);
/*      */     } else {
/* 1084 */       int portLocal = 0;
/* 1085 */       if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1086 */         portLocal = this.sarlab.getPortLocal(deviceName);
/*      */       }
/* 1088 */       this.jTextFieldPortNav.setText(Integer.toString(portLocal));
/*      */     } 
/* 1091 */     initURL();
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
               //registerName = new RegisterName("127.0.0.1", "SarlabNET", "SarlabINIT");
/*      */   }
/*      */ 
/*      */   public void removeService() {
/* 1097 */     this.jTextFieldDesktop.setEditable(true);
/* 1098 */     this.jTextFieldDesktop.setText("");
/* 1099 */     this.jButtonDesktop.setDisable(true);
/* 1100 */     this.jTextFieldVNC.setEditable(true);
/* 1101 */     this.jTextFieldVNC.setText("");
/* 1102 */     this.jButtonVNC.setDisable(true);
/* 1103 */     this.jTextFieldTelnet.setEditable(true);
/* 1104 */     this.jTextFieldTelnet.setText("");
/* 1105 */     this.jButtonTelnet.setDisable(true);
/* 1106 */     this.jTextFieldSsh.setEditable(true);
/* 1107 */     this.jTextFieldSsh.setText("");
/* 1108 */     this.jButtonSsh.setDisable(true);
/* 1109 */     this.jTextFieldsFTP.setEditable(true);
/* 1110 */     this.jTextFieldsFTP.setText("");
/* 1111 */     this.jButtonSFDTP.setDisable(true);
/* 1112 */     this.jTextFieldModbus.setEditable(true);
/* 1113 */     this.jTextFieldModbus.setText("");
/* 1114 */     this.jButtonModbus.setDisable(true);
/* 1115 */     this.jTextFieldURL.setEditable(true);
/* 1116 */     this.jTextFieldPortNav.setEditable(true);
/* 1117 */     this.jRadioAxi.setDisable(true);
/* 1118 */     this.jRadioEnlarge.setDisable(true);
/* 1119 */     this.jRadioFree.setDisable(true);
/* 1120 */     this.jTextFieldURL.setText("");
/* 1121 */     this.jTextFieldPortNav.setText("");
/*      */   }
/*      */  
            @FXML
            void jButtonDesktopActionPerformed() {
/*      */     int port;
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1125 */     //RegisterName registerName = null;
/*      */ 
/* 1128 */     int ind = this.jList1.getSelectionModel().getSelectedIndex();
/* 1129 */     if (this.sarlab == null) {
/* 1131 */        //registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1133 */        //registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1135 */     String sPort = this.jTextFieldDesktop.getText();
/*      */     try {
/* 1137 */       port = Integer.parseInt(sPort);
/* 1138 */     } catch (NumberFormatException ex) {
/* 1139 */       this.jTextFieldDesktop.setText("3389");
/* 1140 */       port = 3389;
/*      */     } 
/* 1142 */      //if (registerName != null) {
/* 1143 */        //this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1144 */       ExecApp exec = new ExecApp();
/* 1145 */        //String deviceName = registerName.getIp() + ":" + sPort;
/* 1146 */       if (this.sarlab == null) {
/* 1147 */         if (OS.isWindows()) {
/* 1148 */            //exec.rdesktop(registerName.getIp(), port);
/*      */         } else {
/* 1150 */            //exec.exec("/usr/bin/rdesktop " + registerName.getIp() + ":" + port);
/*      */         } 
/*      */       } else {
/* 1153 */          //int portLocal; if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1154 */            //portLocal = this.sarlab.getPortLocal(deviceName);
/*      */          //} else {
/* 1156 */            //portLocal = freePort();
/* 1157 */            //this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, portLocal);
/*      */         } 
/* 1159 */         if (OS.isWindows()) {
/* 1160 */            //exec.rdesktop((EJSClientSarlab2)this.sarlab, registerName.getIp(), port);
/*      */         } else {
/* 1162 */            //exec.exec("/usr/bin/rdesktop 127.0.0.1:" + portLocal);
/*      */         } 
/*      */        //} 
/*      */      //} 
             // INICIO - Added message
             Alert mensaje = new Alert(AlertType.INFORMATION);
             if (this.panelMainController.getSelectedLanguage().equals("English")) { 
                mensaje.setTitle("Information");
                mensaje.setHeaderText("Successful RDP connection.");
             } else { 
                mensaje.setTitle("Información");
                mensaje.setHeaderText("Conexión RDP establecida.");
             }       
             Stage stage = (Stage) mensaje.getDialogPane().getScene().getWindow();
             stage.getIcons().add(new Image(getClass().getResourceAsStream("/es/uhu/sarlab/discovery/vista/resources/Display 48_24x24-32.gif")));
             mensaje.show();
             // FIN - Added message
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jTextFieldsFTPActionPerformed() {   
/* 1171 */     String port = this.jTextFieldsFTP.getText();
/* 1172 */     if ("".equals(port)) {
/* 1173 */       this.jTextFieldsFTP.setText("22");
/* 1174 */       port = "22";
/*      */     } 
/*      */   }
/*      */   
             @FXML
/*      */   void jTextFieldModbusActionPerformed() {   
/* 1180 */     String port = this.jTextFieldModbus.getText();
/* 1181 */     if ("".equals(port)) {
/* 1182 */       this.jTextFieldModbus.setText("502");
/* 1183 */       port = "502";
/*      */     } 
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jTextFieldPortNavActionPerformed() {
/* 1189 */     // CAMBIAR COLOR??
/* 1190 */     updateURL();
/*      */   }
/*      */   
/*      */   private void jTextFieldPortNavFocusLost() {
/* 1194 */     // CAMBIAR COLOR??
/* 1195 */     updateURL();
/*      */   }
/*      */   
/*      */   private void jTextFieldPortNavFocusGained() {
/* 1199 */     // CAMBIAR COLOR??
/*      */   }
/*      */ 
/*      */   
/*      */   public int freePort() {
/* 1204 */     ServerSocket sock = null;
/* 1205 */     int port = 0;
/*      */     try {
/* 1207 */       sock = new ServerSocket(0);
/* 1208 */       if (sock == null) {
/* 1209 */         System.out.println("No puedo localizar port libre");
/* 1210 */         return -1;
/*      */       } 
/* 1212 */       port = sock.getLocalPort();
/* 1213 */       sock.close();
/* 1214 */     } catch (IOException iOException) {}
/*      */ 
/*      */     
/* 1217 */     System.out.println("listening on port: " + port);
/* 1218 */     return port;
/*      */   }
/*      */   
/*      */   public String freePorttoString() {
/* 1222 */     return Integer.toString(freePort());
/*      */   }
/*      */   
             @FXML
/*      */   void jButtonVNCActionPerformed() {
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/*      */     //RegisterName registerName;
/* 1227 */     int port = 0;
/* 1228 */     int ind = this.jList1.getSelectionModel().getSelectedIndex();
/*      */    
/* 1231 */     if (this.sarlab == null) {  
/* 1233 */       //registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1235 */       //registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1237 */     String sPort = this.jTextFieldVNC.getText();
/*      */     try {
/* 1239 */       port = Integer.parseInt(sPort);
/* 1240 */     } catch (NumberFormatException ex) {
/* 1241 */       this.jTextFieldVNC.setText("5900");
/* 1242 */       sPort = "5900";
/*      */     } 
/* 1244 */     //if (registerName != null) {
/* 1245 */       //this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1246 */       ExecApp exec = new ExecApp();
/* 1247 */       String programVNC = "C:/Program Files/RealVNC/VNC Viewer/vncviewer.exe ";
/*      */       
/* 1249 */       //String deviceName = registerName.getIp() + ":" + sPort;
/* 1250 */       if (this.sarlab == null) {
/* 1251 */         if (OS.isWindows()) {
/* 1252 */           //exec.exec(programVNC + registerName.getIp() + ":" + sPort);
/*      */         } else {
/* 1254 */           //exec.exec("/usr/bin/vncviewer " + registerName.getIp() + ":" + sPort);
/*      */         } 
/*      */       } else {
/* 1257 */         //if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1258 */          //this.portLocalVLC = this.sarlab.getPortLocal(deviceName);
/*      */         //} else {
/* 1260 */           //this.portLocalVLC = freePort();
/* 1261 */           //this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, this.portLocalVLC);
/*      */         } 
/* 1263 */         if (OS.isWindows()) {
/* 1264 */           exec.exec(programVNC + "127.0.0.1:" + Integer.toString(this.portLocalVLC));
/*      */         } else {
/* 1266 */           exec.exec("/usr/bin/vncviewer 127.0.0.1:" + Integer.toString(this.portLocalVLC));
/*      */         } 
/*      */       //} 
/*      */     //} 
             // INICIO - Added message
             Alert mensaje = new Alert(AlertType.INFORMATION);
             if (this.panelMainController.getSelectedLanguage().equals("English")) { 
                mensaje.setTitle("Information");
                mensaje.setHeaderText("Successful VNC connection.");
             } else { 
                mensaje.setTitle("Información");
                mensaje.setHeaderText("Conexión VNC establecida.");
             }    
             Stage stage = (Stage) mensaje.getDialogPane().getScene().getWindow();
             stage.getIcons().add(new Image(getClass().getResourceAsStream("/es/uhu/sarlab/discovery/vista/resources/vnc.png")));
             mensaje.show();
             // FIN - Added message
/*      */   }
/*      */    
/*      */   private void jTextFieldDesktopFocusGained() {
/* 1275 */     // CAMBIAR COLOR? mirar la clase original
/*      */   }
/*      */   
/*      */   private void jTextFieldVNCFocusGained() {
/* 1279 */    // CAMBIAR COLOR? mirar clase original
/*      */   }
/*      */   
/*      */   private void jTextFieldTelnetFocusGained() {
/* 1283 */    // CAMBIAR COLOR??
/*      */   }
/*      */   
/*      */   private void jTextFieldSshFocusGained() {
/* 1287 */     // CAMBIAR COLOR?? mirar clase oirignal
/*      */   }
/*      */   
/*      */   private void jTextFieldsFTPFocusGained() {
/* 1291 */    // CAMBIAR COLOR? mirar clase original
/*      */   }
/*      */   
/*      */   private void jTextFieldModbusFocusGained() {
/* 1295 */     // cambiar color? ...
/*      */   }
/*      */   
/*      */   private void jTextFieldDesktopFocusLost() {
/* 1300 */     String port = this.jTextFieldDesktop.getText();
/* 1301 */     if ("".equals(port)) {
/* 1302 */       this.jTextFieldDesktop.setText("3389");
/* 1303 */       port = "3389";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldVNCFocusLost() {
/* 1309 */     String port = this.jTextFieldVNC.getText();
/* 1310 */     if ("".equals(port)) {
/* 1311 */       this.jTextFieldVNC.setText("5900");
/* 1312 */       port = "5900";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldTelnetFocusLost() {
/* 1318 */     String port = this.jTextFieldTelnet.getText();
/* 1319 */     if ("".equals(port)) {
/* 1320 */       this.jTextFieldTelnet.setText("23");
/* 1321 */       port = "23";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldSshFocusLost() {
/* 1327 */     String port = this.jTextFieldSsh.getText();
/* 1328 */     if ("".equals(port)) {
/* 1329 */       this.jTextFieldSsh.setText("22");
/* 1330 */       port = "22";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldsFTPFocusLost() {
/* 1336 */     String port = this.jTextFieldsFTP.getText();
/* 1337 */     if ("".equals(port)) {
/* 1338 */       this.jTextFieldsFTP.setText("22");
/* 1339 */       port = "22";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldModbusFocusLost() {
/* 1345 */     String port = this.jTextFieldModbus.getText();
/* 1346 */     if ("".equals(port)) {
/* 1347 */       this.jTextFieldModbus.setText("502");
/* 1348 */       port = "502";
/*      */     } 
/*      */   }
/*      */   
             @FXML
/*      */   void jTextFieldVNCActionPerformed() {
/* 1354 */     String port = this.jTextFieldVNC.getText();
/* 1355 */     if ("".equals(port)) {
/* 1356 */       this.jTextFieldVNC.setText("5900");
/* 1357 */       port = "5900";
/*      */     } 
/*      */   }
/*      */   
             @FXML
/*      */   void jTextFieldTelnetActionPerformed() {  
/* 1363 */     String port = this.jTextFieldTelnet.getText();
/* 1364 */     if ("".equals(port)) {
/* 1365 */       this.jTextFieldTelnet.setText("23");
/* 1366 */       port = "23";
/*      */     } 
/*      */   }
/*      */   
             @FXML
/*      */   void jTextFieldSshActionPerformed() {   
/* 1372 */     String port = this.jTextFieldSsh.getText();
/* 1373 */     if ("".equals(port)) {
/* 1374 */       this.jTextFieldSsh.setText("22");
/* 1375 */       port = "22";
/*      */     } 
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jButtonSshActionPerformed() {
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/*      */     //RegisterName registerName;
/* 1382 */     int port; 
               int ind = this.jList1.getSelectionModel().getSelectedIndex();
  
/* 1386 */     if (this.sarlab == null) {     
/* 1388 */       //registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1390 */       //registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1392 */     String sPort = this.jTextFieldSsh.getText();
/*      */     try {
/* 1394 */       port = Integer.parseInt(sPort);
/* 1395 */     } catch (NumberFormatException ex) {
/* 1396 */       this.jTextFieldSsh.setText("22");
/* 1397 */       port = 22;
/*      */     } 
/* 1399 */     //if (registerName != null) {
/* 1400 */       //this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1401 */       ExecApp exec = new ExecApp();
/* 1402 */       //String deviceName = registerName.getIp() + ":" + sPort;
/*      */       
/* 1404 */       if (this.sarlab == null) {
/* 1405 */         if (OS.isWindows()) {
/* 1406 */           //exec.ssh(registerName.getIp(), port);
/*      */         } else {
/* 1408 */           TextInputDialog dialog = new TextInputDialog();
                     dialog.setContentText("user");
                     Optional<String> seleccion = dialog.showAndWait();
/* 1409 */           if (seleccion == null) {
/*      */             return;
/*      */           }       
/* 1413 */           //exec.exec("/usr/bin/xterm -fa 'Monospace' -fs 14 -T ssh_" + registerName.getIp() + ":" + sPort + " -e /usr/bin/ssh " + seleccion + "@" + registerName.getIp() + " -p " + sPort);
/*      */         } 
/*      */       } else {
/* 1416 */         //int portLocal; 
                   //if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1417 */           //portLocal = this.sarlab.getPortLocal(deviceName);
/*      */         //} else {
/* 1419 */           //portLocal = freePort();
/* 1420 */           //this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, portLocal);
/*      */         } 
/* 1422 */         if (OS.isWindows()) {
/* 1423 */           //exec.ssh((EJSClientSarlab2)this.sarlab, registerName.getIp(), port);
/*      */         } else {
                     TextInputDialog dialog = new TextInputDialog();
                     dialog.setContentText("user");
                     Optional<String> seleccion = dialog.showAndWait();
/* 1426 */           if (seleccion == null) {
/*      */             return;
/*      */           }  
/* 1430 */           //exec.exec("/usr/bin/xterm -fa 'Monospace' -fs 14 -T ssh_" + registerName.getIp() + ":" + sPort + " -e /usr/bin/ssh " + seleccion + "@127.0.0.1 -p " + portLocal);
/*      */         } 
/*      */       //} 
/*      */     //} 
             // INICIO - Added message
             Alert mensaje = new Alert(AlertType.INFORMATION);
             if (this.panelMainController.getSelectedLanguage().equals("English")) { 
                mensaje.setTitle("Information");
                mensaje.setHeaderText("Successful SSH connection.");
             } else { 
                mensaje.setTitle("Información");
                mensaje.setHeaderText("Conexión SSH establecida.");
             }    
             Stage stage = (Stage) mensaje.getDialogPane().getScene().getWindow();
             stage.getIcons().add(new Image(getClass().getResourceAsStream("/es/uhu/sarlab/discovery/vista/resources/ssh.png")));
             mensaje.show();
             // FIN - Added message
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jButtonTelnetActionPerformed() {
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/*      */     //RegisterName registerName;
/* 1439 */     int port;
               int ind = this.jList1.getSelectionModel().getSelectedIndex();
/*      */     
/* 1442 */     if (this.sarlab == null) { 
/* 1444 */        //registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1446 */        //registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1448 */     String sPort = this.jTextFieldTelnet.getText();
/*      */     try {
/* 1450 */       port = Integer.parseInt(sPort);
/* 1451 */     } catch (NumberFormatException ex) {
/* 1452 */       this.jTextFieldTelnet.setText("23");
/* 1453 */       port = 23;
/*      */     } 
/* 1455 */      //if (registerName != null) {
/* 1456 */        //this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1457 */       ExecApp exec = new ExecApp();
/* 1458 */        // deviceName = registerName.getIp() + ":" + sPort;
/*      */       
/* 1460 */       if (this.sarlab == null) {
/* 1461 */         if (OS.isWindows()) {
/* 1462 */            //exec.telnet(registerName.getIp(), port);
/*      */         } else {
/* 1464 */            //exec.exec("/usr/bin/xterm -fa 'Monospace' -fs 14 -T telnet_" + registerName.getIp() + ":" + sPort + " -e /usr/bin/telnet " + registerName.getIp() + " " + sPort);
/*      */         } 
/*      */       } else {
/*      */         int portLocal;
/* 1468 */          //if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1469 */            //portLocal = this.sarlab.getPortLocal(deviceName);
/*      */          //} else {
/* 1471 */            //portLocal = freePort();
/* 1472 */            //this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, portLocal);
/*      */         } 
/* 1474 */         if (OS.isWindows()) {
/* 1475 */            //exec.telnet((EJSClientSarlab2)this.sarlab, registerName.getIp(), port);
/*      */         } else {
/* 1477 */            //exec.exec("/usr/bin/xterm -fa 'Monospace' -fs 14 -T telnet_" + registerName.getIp() + ":" + sPort + " -e /usr/bin/telnet  127.0.0.1 " + portLocal);
/*      */         } 
/*      */        //} 
/*      */      //} 
             // INICIO - Added message
             Alert mensaje = new Alert(AlertType.INFORMATION);
             if (this.panelMainController.getSelectedLanguage().equals("English")) { 
                mensaje.setTitle("Information");
                mensaje.setHeaderText("Successful Telnet connection.");
             } else { 
                mensaje.setTitle("Información");
                mensaje.setHeaderText("Conexión Telnet establecida.");
             }    
             Stage stage = (Stage) mensaje.getDialogPane().getScene().getWindow();
             stage.getIcons().add(new Image(getClass().getResourceAsStream("/es/uhu/sarlab/discovery/vista/resources/telnet.png")));
             mensaje.show();
             // FIN - Added message
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jButtonSFDTPActionPerformed() {
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/*      */     //RegisterName registerName;
/* 1486 */     int port, ind = this.jList1.getSelectionModel().getSelectedIndex();

/* 1489 */     if (this.sarlab == null) {     
/* 1491 */       //registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1493 */       //registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1495 */     String sPort = this.jTextFieldsFTP.getText();
/*      */     try {
/* 1497 */       port = Integer.parseInt(sPort);
/* 1498 */     } catch (NumberFormatException ex) {
/* 1499 */       this.jTextFieldsFTP.setText("22");
/* 1500 */       port = 22;
/*      */     } 
/* 1502 */     //if (registerName != null) {
/* 1503 */       //this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1504 */       ExecApp exec = new ExecApp();
/* 1505 */       //String deviceName = registerName.getIp() + ":" + sPort;
/* 1507 */       if (this.sarlab == null) {
/* 1508 */         if (OS.isWindows()) {
/* 1509 */           //exec.sftp(registerName.getIp(), port);
/*      */         } else {
/* 1512 */           // this.jDialog1.setVisible(true); ¿cumple esta funciona la linea de debajo?
                     this.jDialog1.initStyle(StageStyle.DECORATED);
/* 1513 */           //exec.exec("/usr/bin/filezilla sftp://" + this.userLog + ":" + this.passwdLog + "@" + registerName.getIp() + ":" + sPort);
/*      */         } 
/*      */       } else {
/* 1516 */         //int portLocal; if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1517 */           //portLocal = this.sarlab.getPortLocal(deviceName);
/*      */         //} else {
/* 1519 */           //portLocal = freePort();
/* 1520 */           //this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, portLocal);
/*      */         } 
/* 1522 */         if (OS.isWindows()) {
/* 1523 */           //exec.sftp((EJSClientSarlab2)this.sarlab, registerName.getIp(), port);
/*      */         } else {
/* 1526 */           // this.jDialog1.setVisible(true); ¿cumple esta funciona la linea de debajo?
                     this.jDialog1.initStyle(StageStyle.DECORATED);
/* 1527 */           //exec.exec("/usr/bin/filezilla sftp://" + this.userLog + ":" + this.passwdLog + "@127.0.0.1:" + portLocal);
/*      */         } 
/*      */       //} 
/*      */     //} 
             // INICIO - Added message
             Alert mensaje = new Alert(AlertType.INFORMATION);
             if (this.panelMainController.getSelectedLanguage().equals("English")) { 
                mensaje.setTitle("Information");
                mensaje.setHeaderText("Successful sFTP connection.");
             } else { 
                mensaje.setTitle("Información");
                mensaje.setHeaderText("Conexión sFTP establecida.");
             }    
             Stage stage = (Stage) mensaje.getDialogPane().getScene().getWindow();
             stage.getIcons().add(new Image(getClass().getResourceAsStream("/es/uhu/sarlab/discovery/vista/resources/Folder 102_24x24-32.gif")));
             mensaje.show();
             // FIN - Added message
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jButtonModbusActionPerformed() {
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/*      */     //RegisterName registerName;
/* 1536 */     int port;
               int ind = this.jList1.getSelectionModel().getSelectedIndex();
/*      */      
/* 1539 */     if (this.sarlab == null) {
/* 1541 */       //registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1543 */       //registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1545 */     String sPort = this.jTextFieldModbus.getText();
/*      */     try {
/* 1547 */       port = Integer.parseInt(sPort);
/* 1548 */     } catch (NumberFormatException ex) {
/* 1549 */       this.jTextFieldModbus.setText("502");
/* 1550 */       port = 502;
/*      */     } 
/* 1552 */     //if (registerName != null) {
/* 1553 */       //this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1554 */       if (this.sarlab == null) {
/* 1555 */         //MasterModbus modBusMaster = new MasterModbus(registerName.getIp(), this.jTextFieldModbus.getText(), "1");
/* 1556 */         //modBusMaster.connect();
/* 1557 */         //modBusMaster.monitorInit();
/*      */       } else {
/* 1559 */         int portLocal = freePort();
/* 1560 */         //this.sarlab.addconnectionLocalhostL("Monitor Modbus", registerName.getIp(), port, portLocal);
/* 1561 */         MasterModbus modBusMaster = new MasterModbus("127.0.0.1", Integer.toString(portLocal), "1");
/* 1562 */         modBusMaster.connect();
/* 1563 */         modBusMaster.monitorInit();
/*      */       } 
/*      */     //} 
             // INICIO - Added message
             Alert mensaje = new Alert(AlertType.INFORMATION);
             if (this.panelMainController.getSelectedLanguage().equals("English")) { 
                mensaje.setTitle("Information");
                mensaje.setHeaderText("Successful Modbus connection.");
             } else { 
                mensaje.setTitle("Información");
                mensaje.setHeaderText("Conexión Modbus establecida.");
             }    
             Stage stage = (Stage) mensaje.getDialogPane().getScene().getWindow();
             stage.getIcons().add(new Image(getClass().getResourceAsStream("/es/uhu/sarlab/discovery/vista/resources/modbus.png")));
             mensaje.show();
             // FIN - Added message
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jButtonNavegadorActionPerformed() {
/* 1570 */     String urlNav = generaURL();
/* 1571 */     ExecApp exec = new ExecApp();
/* 1572 */     if (OS.isWindows()) {
/* 1573 */       exec.browser(urlNav);
/*      */     } else {
/* 1575 */       exec.exec("/usr/bin/google-chrome --no-sandbox --user-data-dir " + urlNav);
/*      */     } 
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jTextFieldURLActionPerformed() {
/* 1581 */     String urlNav = generaURL();
/* 1582 */     ExecApp exec = new ExecApp();
/* 1583 */     if (OS.isWindows()) {
/* 1584 */       exec.browser(urlNav);
/*      */     } else {
/* 1586 */       exec.exec("/usr/bin/google-chrome --no-sandbox --user-data-dir " + urlNav);
/*      */     } 
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jButtonRemotoActionPerformed() throws IOException{
/* 1593 */     removeService();
               Image iconJLabel61 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/wan64x64.png").toExternalForm());
               ImageView iconViewJLabel61 = new ImageView(iconJLabel61);
/*  437 */     this.jLabel6.setGraphic(iconViewJLabel61);
               ObservableList<String> observableListModel = observableArrayList(new ArrayList<String>());
/*  990 */     this.jList1.setItems(observableListModel);
/* 1597 */     if (OS.isWindows()) {
/* 1598 */       this.jButtonTools.setDisable(false);
/*      */     }
/* 1600 */     this.jButtonForwarding.setDisable(false);
/* 1601 */     this.jButtonForwardingLook.setDisable(false);
/* 1602 */     this.jButtonProfile.setDisable(false);
               
               if (this.panelListGatewayStage == null) {
                   this.panelListGatewayStage = StageManager.getInstance().getStage("PanelListGateway");
                   FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelListGateway");     
                   Scene scene = new Scene(fxmlLoader.load());
                   addCss(scene);
                   this.panelListGatewayStage.setScene(scene);
               }    
               
               if (this.panelListGatewayController == null) {
                   FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelListGateway");
                   this.panelListGatewayController = fxmlLoader.getController();
               }
               // Inicia la lista de backend de prueba
               this.panelListGatewayController.initList();
              
               // Evento al cerrar la ventana principal
               this.panelListGatewayStage.setOnCloseRequest( e -> {
                    if (this.sarlab == null) {
                        // Podria dar ERROR: jLabel 6 es de tipo Label y no ImageView...
                        Image iconJLabel62 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/LAN64x64.png").toExternalForm());
                        ImageView iconViewJLabel62 = new ImageView(iconJLabel62);
                        this.jLabel6.setGraphic(iconViewJLabel62);
                        this.jLabelNetConnect.setText("LAN");
                        this.jButtonTools.setDisable(true); // setDisable
                        this.jButtonForwarding.setDisable(true);
                        this.jButtonForwardingLook.setDisable(true);
                        this.jButtonProfile.setDisable(true);
                    } 
                    toUpdate(); 
                });   
               this.panelListGatewayStage.setResizable(false);
               this.panelListGatewayStage.show();
/*      */   }
 
/*      */   @FXML
/*      */   void jButtonConfiguracionLocalActionPerformed() throws IOException {       
               if (panelEditConfigLocalStage == null) {
                   panelEditConfigLocalStage = StageManager.getInstance().getStage("PanelEditConfigLocal");
                   FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelEditConfigLocal");
                   Scene scene = new Scene(fxmlLoader.load());
                   addCss(scene);
                   panelEditConfigLocalStage.setScene(scene);
               }
               panelEditConfigLocalStage.show();
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jButtonLocalActionPerformed() {
/* 1613 */     removeService();
/* 1614 */     this.jLabelNetConnect.setText("LAN");
/* 1615 */     this.jButtonTools.setDisable(true);
/* 1616 */     this.jButtonForwarding.setDisable(true);
/* 1617 */     this.jButtonForwardingLook.setDisable(true);
/* 1618 */     this.jButtonProfile.setDisable(true);
               Image iconJLabel6 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/LAN64x64.png").toExternalForm());
               ImageView iconViewJLabel6 = new ImageView(iconJLabel6);
/*  437 */     this.jLabel6.setGraphic(iconViewJLabel6);
/* 1620 */     ObservableList<String> observableListModel = observableArrayList(new ArrayList<String>());
/*  990 */     this.jList1.setItems(observableListModel);
/* 1622 */     if (this.sarlab != null) {
/* 1623 */       this.sarlab.disconnect();
/* 1624 */       this.sarlab.delay(500);
/*      */     } 
/* 1626 */     this.sarlab = null;
               if (this.barLogo == null) {
                   this.barLogo = new BarLogo((InterfazBarLogo) this);
                   this.barLogo.init();
                   this.barLogo = null; // añadido para correcto funcionamiento
               }   
/* 1628 */     ButtonEnabled(false);
/* 1629 */     this.jButtonActualizar.setDisable(true);
/*      */   }
/*      */   
             @FXML
/*      */   void jRadioAxiActionPerformed() {
/*      */     RegisterName registerName;
/* 1634 */     this.jTextFieldURL.setDisable(true);
/*      */ 
/* 1637 */     int ind = this.jList1.getSelectionModel().getSelectedIndex();
/* 1638 */     if (this.sarlab == null) {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1639 */       //registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1641 */       //registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1643 */     String sPort = this.jTextFieldPortNav.getText();
/* 1644 */     if (!"80".equals(sPort)) {
/* 1645 */       //this.url = "http://" + registerName.getIp() + ":" + this.jTextFieldPortNav.getText();
/*      */     } else {
/* 1647 */       //this.url = "http://" + registerName.getIp();
/*      */     } 
/* 1649 */     this.jTextFieldURL.setText(this.url + "/axis-cgi/mjpg/video.cgi");
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jRadioEnlargeActionPerformed() {
/*      */     RegisterName registerName;
/* 1658 */     this.jTextFieldURL.setEditable(true);
/*      */ 
/*      */     
/* 1661 */     int ind = this.jList1.getSelectionModel().getSelectedIndex();
/* 1662 */     if (this.sarlab == null) {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1663 */       //registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1665 */       //registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1667 */     String sPort = this.jTextFieldPortNav.getText();
/* 1668 */     if (!"80".equals(sPort)) {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1669 */       //this.url = "http://" + registerName.getIp() + ":" + this.jTextFieldPortNav.getText();
/*      */     } else {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1671 */       //this.url = "http://" + registerName.getIp();
/*      */     } 
/* 1673 */     this.jTextFieldURL.setText(this.url + "/stream/video.mjpeg");
/*      */   }

/*      */   @FXML
/*      */   void jRadioFreeActionPerformed() {
/*      */     RegisterName registerName;
/* 1684 */     int ind = this.jList1.getSelectionModel().getSelectedIndex();
/* 1685 */     if (this.sarlab == null) {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1686 */       //registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1688 */       //registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1690 */     String sPort = this.jTextFieldPortNav.getText();
/* 1691 */     if (!"80".equals(sPort)) {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1692 */       //this.url = "http://" + registerName.getIp() + ":" + this.jTextFieldPortNav.getText();
/*      */     } else {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1694 */       //this.url = "http://" + registerName.getIp();
/*      */     } 
/* 1696 */     this.jTextFieldURL.setText(this.url);
/* 1697 */     this.jTextFieldURL.setEditable(false);
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jButtonToolsActionPerformed() {
/* 1702 */     if (this.sarlab != null) {
/* 1703 */       ExecApp exec = new ExecApp();
/* 1704 */       if (OS.isWindows()) {
/* 1705 */         exec.utility((EJSClientSarlab2)this.sarlab);
/*      */       } else {
                   Alert alert = new Alert(AlertType.INFORMATION);
                   alert.setContentText("Option not supported on linux");
                   alert.showAndWait();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jButtonForwardingLookActionPerformed() throws IOException {  
/* 1715 */     //this.panelConectionLocalhost.setVisible(true);
/* 1716 */     //ObservableList<String> modelo = observableArrayList(new ArrayList<String>());
/* 1717 */     //if (this.sarlab != null) {
/* 1718 */     //  modelo = (ObservableList<String>) this.sarlab.getDefaultListModel();
/*      */     //}
/* 1721 */     //this.panelConectionLocalhost.init(modelo);
/* 1722 */     //this.panelConectionLocalhost.set();
               // IMPLEMENTADO: (PROBAR SI FUNCIONA)   
               if (panelConectionLocalhostStage == null) {
                   panelConectionLocalhostStage = StageManager.getInstance().getStage("PanelConectionLocalhost");
                   FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelConectionLocalhost");
                   Scene scene = new Scene(fxmlLoader.load());
                   addCss(scene);
                   panelConectionLocalhostController = fxmlLoader.getController();             
                   panelConectionLocalhostStage.setScene(scene);
               }
               if (panelConectionLocalhostController == null) {
                   FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelConectionLocalhost");
                   panelConectionLocalhostController = fxmlLoader.getController();          
               }          
               panelConectionLocalhostStage.show();              
               ObservableList<String> modelo = observableArrayList(new ArrayList<>());
/* 1717 */     if (this.sarlab != null) {
/* 1718 */       modelo = (ObservableList<String>) this.sarlab.getDefaultListModel();
/*      */     }
               panelConectionLocalhostController.init(modelo);
               panelConectionLocalhostController.set(); 
               // INICIO - Added backend
               panelConectionLocalhostController.initList();
               // FIN - Added backend
/*      */   }
/*      */ 
/*      */ 
/*      */   @FXML
/*      */   void jButtonForwardingActionPerformed() throws IOException { 
               if (panelNodoFrowardingLStage == null) {
                   panelNodoFrowardingLStage = StageManager.getInstance().getStage("PanelNodoFrowardingL");
                   FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelNodoFrowardingL");
                   Scene scene = new Scene(fxmlLoader.load());
                   addCss(scene);
                   panelNodoFrowardingLController = fxmlLoader.getController();             
                   panelNodoFrowardingLStage.setScene(scene);
               }
               ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelNodoFrowardingL" + getBundleLanguage());
               panelNodoFrowardingLStage.setTitle(bundle.getString("PanelNodoFrowardingL.title"));
               panelNodoFrowardingLStage.setResizable(false);
               panelNodoFrowardingLStage.show();
               panelNodoFrowardingLController.setIpInit();
/*      */   }
/*      */ 
/*      */   @FXML
/*      */   void jButtonProfileActionPerformed() throws IOException {
/* 1738 */     String url = "";
/* 1739 */     if (this.sarlab != null) {
/* 1740 */       HashMapExperienceWeb hashMapExperienceWeb = new HashMapExperienceWeb();      
/* 1742 */       GetParameters getParameters = new GetParameters();
/* 1743 */       hashMapExperienceWeb = getParameters.getHashMapExperienceWeb(this.registerGatewayOld);
/* 1744 */       if (hashMapExperienceWeb == null) {
/*      */         return;
/*      */       }
/* 1747 */       //this.panelConectionExperience.setModel(hashMapExperienceWeb);
/* 1748 */       //this.panelConectionExperience.setRegisterGateway(this.registerGatewayOld);
/* 1749 */       //this.panelConectionExperience.setMyirtualFrontierOld(this.myirtualFrontierOld);
/* 1750 */       //this.panelConectionExperience.setMyURIMyGatewayOld(this.myURIMyGatewayOld);
/* 1751 */       //this.panelConectionExperience.setVisible(true);      
                 panelConectionExperienceController.setModel(hashMapExperienceWeb);
                 panelConectionExperienceController.setRegisterGateway(this.registerGatewayOld);
                 panelConectionExperienceController.setMyirtualFrontierOld(this.myirtualFrontierOld);
                 panelConectionExperienceController.setMyURIMyGatewayOld(this.myURIMyGatewayOld);                            

/*      */     } 
               if (panelConectionExperienceStage == null) {
                   panelConectionExperienceStage = StageManager.getInstance().getStage("PanelConectionExperience");
                   FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelConectionExperience");
                   Scene scene = new Scene(fxmlLoader.load());
                   addCss(scene);
                   panelConectionExperienceStage.setScene(scene);
               }
               if (panelConectionExperienceController == null) {
                   FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelConectionExperience");
                   panelConectionExperienceController = fxmlLoader.getController();
               }
               panelConectionExperienceController.initList();
               panelConectionExperienceStage.show();
/*      */   }

/*      */   private void lineausuarioKeyPressed(KeyEvent evt) {
/* 1758 */     char ch = evt.getKeyChar();
/* 1759 */     if (ch == '\n') {
/* 1760 */       this.lineapass.requestFocus();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void lineapassKeyPressed(KeyEvent evt) {
/* 1767 */     char ch = evt.getKeyChar();
/* 1768 */     if (ch == '\n') {
/* 1769 */       this.userLog = this.lineausuario.getText();
/* 1770 */       this.passwdLog = this.lineapass.getText();
/* 1771 */       this.enteruserpas = true;
/*      */       
/* 1773 */       this.jButtonLogin.requestFocus();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jButtonLoginActionPerformed(ActionEvent evt) {
/* 1778 */     this.userLog = this.lineausuario.getText();
/* 1779 */     this.passwdLog = this.lineapass.getText();
/* 1780 */     this.lineausuario.setText("");
/* 1781 */     this.lineapass.setText("");
/* 1782 */     this.enteruserpas = true;
/* 1783 */     this.jDialog1.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButtonLoginKeyPressed(KeyEvent evt) {
/* 1788 */     char ch = evt.getKeyChar();
/* 1789 */     if (ch == '\n') {
/* 1790 */       this.userLog = this.lineausuario.getText();
/* 1791 */       this.passwdLog = this.lineapass.getText();
/* 1792 */       this.lineausuario.setText("");
/* 1793 */       this.lineapass.setText("");
/* 1794 */       this.enteruserpas = true;
/* 1795 */       this.jDialog1.close();
/*      */     } 
/*      */   }

/*      */   private void lineausuarioActionPerformed(ActionEvent evt) {}
/*      */          
/*      */   @FXML
/*      */   void jButton1ActionPerformed() throws IOException {   
                if (panelConsoleStage == null) {
                    panelConsoleStage = StageManager.getInstance().getStage("PanelConsole");
                    FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelConsole");
                    Scene scene = new Scene(fxmlLoader.load());
                    addCss(scene);
                    panelConsoleController = fxmlLoader.getController();
                    panelConsoleStage.setScene(scene);
                }
                    panelConsoleStage.show();
           //Platform.runLater( () -> {      
                //panelConsoleStage = StageManager.getInstance().getStage("PanelConsole");
                //panelConsoleStage.show();
                //FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelConsole");
                // Se llamada a load() si no se ha obtenido el nodo padre anteriormente
                //if (fxmlLoader.getRoot() == null) {
                //    try {
                //        fxmlLoader.load();
                //    } catch (IOException ex) {
                //        Logger.getLogger(PanelClientServerNameController.class.getName()).log(Level.SEVERE, null, ex);
                //    }
                //}
                
                // Evento en cierre
                    panelConsoleStage.setOnCloseRequest( e -> {
                      System.out.println("*******Close console *********");
                      if (panelConsoleController.timer != null)
                          panelConsoleController.stopTimer(); 
                    }); 
                    panelConsoleController.startTimer();
              //});
/*      */   }
/*      */  
/*      */   public void initURL() {
/* 1811 */     String sPort, urlLocal = "";
/*      */ 
/*      */     
/* 1814 */     int ind = this.jList1.getSelectionModel().getSelectedIndex();
/* 1815 */     if (this.sarlab == null) {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1816 */       //RegisterName registerName = this.serverUDPThread.getRegisterNametoArray(ind);
                 // INICIO - Added backend
                 RegisterName registerName = new RegisterName("127.0.0.1", "SarlabNET", "SarlabINIT");
                 // FIN - Added backend
/* 1817 */       sPort = Integer.toString(registerName.getHttpPort());
/* 1818 */       if (!"80".equals(sPort)) {
/* 1819 */         urlLocal = "http://" + registerName.getIp() + ":" + sPort;
/*      */       } else {
/* 1821 */         urlLocal = "http://" + registerName.getIp();
/*      */       } 
/*      */     } else {
/* 1824 */       RegisterName registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
                 // INICIO - Added backend
                 registerName = new RegisterName("127.0.0.1", "SarlabNET", "SarlabINIT");
                 // FIN - Added backend
/* 1825 */       sPort = Integer.toString(registerName.getHttpPort());
/* 1826 */       String deviceName = registerName.getIp() + ":" + sPort;
/*      */       
/* 1828 */       int portLocal = this.sarlab.getPortLocal(deviceName);
/* 1829 */       if (!"80".equals(sPort)) {
/* 1830 */         urlLocal = "http://" + registerName.getIp() + ":" + sPort;
/*      */       } else {
/* 1832 */         urlLocal = "http://" + registerName.getIp();
/*      */       } 
/*      */     } 

/* 1835 */     if (this.jRadioEnlarge.isSelected()) {
/* 1836 */       urlLocal = urlLocal + "/stream/video.mjpeg";
/*      */     }
/* 1838 */     if (this.jRadioAxi.isSelected()) {
/* 1839 */       urlLocal = urlLocal + "/axis-cgi/mjpg/video.cgi";
/*      */     }
/* 1841 */     this.url = urlLocal;
/* 1842 */     this.jTextFieldURL.setText(this.url);
/* 1843 */     this.jTextFieldPortNav.setText(sPort);
/*      */   }
/*      */   
/*      */   public void updateURL() {
/* 1847 */     String sPort, urlLocal = "";
/*      */ 
/*      */     
/* 1850 */     int ind = this.jList1.getSelectionModel().getSelectedIndex();
/* 1851 */     if (this.sarlab == null) {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1852 */       //RegisterName registerName = this.serverUDPThread.getRegisterNametoArray(ind);
                 // INICIO - Added backend
                 RegisterName registerName = new RegisterName("127.0.0.1", "SarlabNET", "SarlabINIT");
                 // FIN - Added backend
/* 1853 */       sPort = this.jTextFieldPortNav.getText();
/* 1854 */       if (!"80".equals(sPort)) {
/* 1855 */         urlLocal = "http://" + registerName.getIp() + ":" + sPort;
/*      */       } else {
/* 1857 */         urlLocal = "http://" + registerName.getIp();
/*      */       } 
/*      */     } else {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1860 */       //RegisterName registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
                 // INICIO - Added backend
                 RegisterName registerName = new RegisterName("127.0.0.1", "SarlabNET", "SarlabINIT");
                 // FIN - Added backend
/* 1861 */       sPort = this.jTextFieldPortNav.getText();
/* 1862 */       if (!"80".equals(sPort)) {
/* 1863 */         urlLocal = "http://" + registerName.getIp() + ":" + sPort;
/*      */       } else {
/* 1865 */         urlLocal = "http://" + registerName.getIp();
/*      */       } 
/*      */     } 
/* 1868 */     if (this.jRadioEnlarge.isSelected()) {
/* 1869 */       urlLocal = urlLocal + "/stream/video.mjpeg";
/*      */     }
/* 1871 */     if (this.jRadioAxi.isSelected()) {
/* 1872 */       urlLocal = urlLocal + "/axis-cgi/mjpg/video.cgi";
/*      */     }
/* 1874 */     if (this.jRadioFree.isSelected()) {
/* 1875 */       urlLocal = urlLocal + generateURI(this.jTextFieldURL.getText());
/*      */     }
/* 1877 */     this.url = urlLocal;
/* 1878 */     this.jTextFieldURL.setText(this.url);
/* 1879 */     this.jTextFieldPortNav.setText(sPort);
/*      */   }
/*      */   
/*      */   public String generaURL() {
/* 1883 */     String urlLocal = "";
/*      */ 
/*      */     
/* 1886 */     int ind = this.jList1.getSelectionModel().getSelectedIndex();
/* 1887 */     if (this.sarlab == null) {
                 // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1888 */       //RegisterName registerName = this.serverUDPThread.getRegisterNametoArray(ind);
                 // INICIO - Added backend
                 RegisterName registerName = new RegisterName("127.0.0.1", "SarlabNET", "SarlabINIT");
                 // FIN - Added backend
/* 1889 */       String sPort = this.jTextFieldPortNav.getText();
/* 1890 */       if (!"80".equals(sPort)) {
/* 1891 */         urlLocal = "http://" + registerName.getIp() + ":" + this.jTextFieldPortNav.getText();
/*      */       } else {
/* 1893 */         urlLocal = "http://" + registerName.getIp();
/*      */       } 
/*      */     } else {
/* 1896 */       int port, portLocal; RegisterName registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/* 1897 */       String sPort = this.jTextFieldPortNav.getText();
/* 1898 */       String deviceName = registerName.getIp() + ":" + sPort;
/*      */       
/*      */       try {
/* 1901 */         port = Integer.parseInt(sPort);
/* 1902 */       } catch (NumberFormatException ex) {
/* 1903 */         this.jTextFieldPortNav.setText("80");
/* 1904 */         port = 80;
/*      */       } 
/*      */       
/* 1907 */       if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1908 */         portLocal = this.sarlab.getPortLocal(deviceName);
/*      */       } else {
/* 1910 */         portLocal = freePort();
/* 1911 */         this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, portLocal);
/*      */       } 
/*      */       
/* 1914 */       urlLocal = "http://127.0.0.1:" + portLocal;
/*      */     } 
/*      */     
/* 1917 */     if (this.jRadioEnlarge.isSelected()) {
/* 1918 */       urlLocal = urlLocal + "/stream/video.mjpeg";
/*      */     }
/* 1920 */     if (this.jRadioAxi.isSelected()) {
/* 1921 */       urlLocal = urlLocal + "/axis-cgi/mjpg/video.cgi";
/*      */     }
/* 1923 */     if (this.jRadioFree.isSelected()) {
/* 1924 */       urlLocal = urlLocal + generateURI(this.jTextFieldURL.getText());
/*      */     }
/* 1926 */     return urlLocal;
/*      */   }
/*      */   
/*      */   public static String generateURI(String url) {
/* 1930 */     String uri = url.trim();
/* 1931 */     if (uri.contains("http://") && uri.indexOf("http://") == 0) {
/* 1932 */       uri = uri.substring(7, uri.length());
/* 1933 */       if (uri.contains("/")) {
/* 1934 */         uri = "/" + uri.substring(uri.indexOf("/") + 1, uri.length());
/*      */       } else {
/*      */         
/* 1937 */         uri = "";
/*      */       } 
/* 1939 */       return uri;
/*      */     } 
/* 1941 */     return "";
/*      */   }
/*      */ 

/*      */   public String giveMeFileNameConfig() {
/* 1947 */     if (OS.isWindows()) {
/* 1948 */       return "C:/sarlab/config/" + this.fileNameBase;
/*      */     }
/* 1950 */     return "/sarlab/config/" + this.fileNameBase;
/*      */   }
/*      */ 

/*      */   @Override
             public void initBarLogo() {
/*      */     try {
/* 1959 */       this.serverUDPThread = new ServerUDPThread(4448, 1024);
/* 1960 */       this.serverUDPThread.start();
/* 1961 */       ClientGIOTLocatorService adClientName = new ClientGIOTLocatorService();
/* 1962 */       Date fecha = new Date();
/* 1963 */       String myIp = UtilIp.ipHost();
/* 1964 */       this.log.info(" IP =" + myIp);
/* 1965 */       InetAddress addr = InetAddress.getByName(myIp);
/* 1966 */       String hostname = addr.getHostName();
/* 1967 */       Properties p = new Properties();
/* 1968 */       if (!DirectoryFilesUtility.existsFile(giveMeFileNameConfig())) {
                     TextInputDialog dialog = new TextInputDialog();
                     dialog.setTitle("Identificación del equipo");
                     dialog.setContentText("Indique el nombre");
                     Optional<String> name = dialog.showAndWait();
  
/* 1971 */         if (name.isPresent() && name.get().equals("")) {
                     p.setProperty("nameSarlab", hostname);
/*      */         } else if(name.isPresent()) {
                     p.setProperty("nameSarlab", name.get());
                   }
/* 1975 */         p.store(new FileWriter(giveMeFileNameConfig()), "Creado por el servicio GIOT Name");
/*      */       } 
/*      */       
/* 1978 */       p.load(new FileReader(giveMeFileNameConfig()));
/* 1979 */       String nameSarlab = p.getProperty("nameSarlab", hostname);
/* 1980 */       adClientName.multicast(fecha + " Cliente " + nameSarlab, "224.0.0.251", 4446, 4449);
/* 1981 */     } catch (IOException iOException) {}
/*      */   }
/*      */ 
/*      */ 
/*      */   @Override
/*      */   public void endBarLogo() {
/* 1987 */     this.serverUDPThread.end();
/* 1988 */     DirectoryRegisterName directoryRegisterName = this.serverUDPThread.getDirectoryRegisterName();
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
/* 1989 */     //setListModel((ObservableList<String>) this.serverUDPThread.getListModel());
               // Volcado de lista DefaultListModel en lista ObservableList
               ObservableList<String> copiedList = observableArrayList(new ArrayList<>());
               for (int i = 0; i < this.serverUDPThread.getListModel().size(); i++) {
                   copiedList.add((String) this.serverUDPThread.getListModel().get(i));
               }
               // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
               //setListModel(copiedList);
/* 1990 */     this.jButtonActualizar.setDisable(false);
/*      */   }
/*      */ 
/*      */   public void connectGIOT(final RegisterGateway registerGateway) {
/* 1997 */     initProgressBar(5);

/* 2000 */     SwingUtilities.invokeLater(new Runnable()
/*      */         {
/*      */           public void run() {
/* 2003 */             if (PanelClientServerNameController.this.sarlab != null) {
/* 2004 */               PanelClientServerNameController.this.sarlab.disconnect();
/*      */             }
/*      */             /*      */             
/* 2007 */             PanelClientServerNameController.this.sarlab = new GIOTClientSarlab3();
/*      */             
/* 2009 */             System.out.println("IpServerTunnel " + registerGateway.getIpServerSarlab());
/* 2010 */             if (!registerGateway.getPortInputGIOTTunnel().equals("")) {
/* 2011 */               String uri = registerGateway.getIdGIOT().replace("GIoT_", "").toLowerCase();
/* 2012 */               PanelClientServerNameController.this.sarlab.connectMyGateway(registerGateway.getIpServerSarlab(), registerGateway.getPortServerSarlab(), registerGateway.getIdGIOT(), registerGateway.getKeyGIOT(), registerGateway.getIpPublicherMyGateway(), registerGateway.getPortInputGIOTTunnel(), registerGateway.getPortInputGIOTWeb(), registerGateway.getUserGIOT(), registerGateway.getPassGIOT(), registerGateway.getIdExp(), registerGateway.getTimeOUT());
/*      */             }
/*      */             else {
/* 2016 */               PanelClientServerNameController.this.sarlab.connect(registerGateway.getIpServerSarlab(), registerGateway.getPortServerSarlab(), registerGateway.getUserGIOT(), registerGateway.getPassGIOT(), registerGateway.getIdExp(), registerGateway.getTimeOUT());
/*      */             } 
/* 2018 */             System.out.println(PanelClientServerNameController.this.url);
/* 2019 */             GetParameters getParameters = new GetParameters();
/* 2020 */             PanelClientServerNameController.this.directoryRegisterNameSarlab = getParameters.getDirectoryRegisterNameSarlab(registerGateway);
/* 2021 */             if (!registerGateway.getPortInputGIOTTunnel().equals("")) {
/* 2022 */               RegisterName regMyGateway = new RegisterName("10.254.254.254", "myGateway_10", "myGateway10");
/* 2023 */               regMyGateway.setVnc(true);
/* 2024 */               regMyGateway.setVncPort(5901);
/* 2025 */               regMyGateway.setSsh(true);
/* 2026 */               regMyGateway.setSshPort(22);
/* 2027 */               regMyGateway.setSftp(true);
/* 2028 */               regMyGateway.setSftpPort(22);
/* 2029 */               PanelClientServerNameController.this.directoryRegisterNameSarlab.add(regMyGateway);
/*      */             } 
/* 2031 */             PanelClientServerNameController.this.registerGatewayOld = registerGateway;
/*      */ 
/*      */             /*      */ 
/*      */             
/* 2034 */             if (PanelClientServerNameController.this.directoryRegisterNameSarlab != null) {
/* 2035 */               ObservableList<String> observableListModel = (ObservableList<String>) PanelClientServerNameController.this.directoryRegisterNameSarlab.getListModel();
/* 2036 */               PanelClientServerNameController.this.jList1.setItems(observableListModel);
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   public String getIdGIoT(String IdGIOT) {
/* 2043 */     return IdGIOT.substring(5).toLowerCase();
/*      */   }
/*      */ 
             @Override
/*      */   public void propertyChange(PropertyChangeEvent evt) {
/* 2157 */     if ("progress" == evt.getPropertyName()) {
/* 2158 */       //this.progressBar.setIndeterminate(false);
                 // EQUIVALE A NO SER INDETERMINADO (SUSTITUYE A LA DE ARRIBA):
                 this.progressBar.setVar(0.1);
/* 2159 */       int progress = ((Integer)evt.getNewValue()).intValue();
/* 2160 */       this.progressBar.setVar(progress);
/* 2161 */       if (progress == 100) {
/* 2162 */         this.progressBar.setVisible(false);
/*      */       }
/*      */       
/* 2165 */       if (this.sarlab != null) {
/* 2166 */         if (this.sarlab.getStatusSarlab().contains("Error")) {
/* 2167 */           this.sentinel.stop();
/* 2168 */           this.progressBar.setVisible(false);
/*      */         } 
/* 2170 */         if (this.sarlab.getStatusSarlab().contains("failure")) {
/* 2171 */           this.sentinel.stop();
/* 2172 */           this.progressBar.setVisible(false);
/*      */         } 
/* 2174 */         if (this.sarlab.openConnectionSarlab()) {
/* 2175 */           this.task.endProgress();
/*      */           
/* 2177 */           this.sentinel.start();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   public void initProgressBar(int t) {
/* 2185 */     this.progressBar.setVisible(true);
/* 2186 */     this.progressBar.setIndeterminate();
/* 2187 */     this.progressBar.setVar(0.5);
/* 2188 */     this.task = new Task();
/* 2189 */     this.task.setT(t);
/* 2190 */     this.task.addPropertyChangeListener(this);
/* 2191 */     this.task.execute();
/* 2192 */     this.sentinel.stop();
/*      */   }
/*      */   /*      */   
/*      */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            initPanelClientServerName();   
    }
    
 class Task extends SwingWorker<Void, Void> {
/*      */     boolean end = false;
/*      */     public boolean slow = false;
/* 2199 */     int t = 10;
/*      */     
/*      */     public void setT(int t) {
/* 2202 */       this.t = t;
/*      */     }
/*      */     
/*      */     public void endProgress() {
/* 2206 */       this.end = true;
/*      */     }
/*      */     
/*      */     public void delay(int ret) {
/*      */       try {
/* 2211 */         Thread.sleep(ret);
/* 2212 */       } catch (InterruptedException ex) {
/* 2213 */         System.out.println("Error en delay");
/*      */       } 
/*      */     }
/*      */     
/*      */     public void retardation(int t) {
/* 2218 */       long dura = t * 1000L;
/* 2219 */       long t0 = System.currentTimeMillis();
/* 2220 */       long t1 = t0;
/* 2221 */       long t2 = t0 + dura;
/*      */ 
/*      */       
/* 2224 */       setProgress(0);
/* 2225 */       while (t1 < t2) {
/* 2226 */         long dt = t1 - t0;
/* 2227 */         long rt = 100L * dt / dura;
/* 2228 */         t1 = System.currentTimeMillis();
/* 2229 */         int val = (int)rt;
/* 2230 */         delay(100);
/* 2231 */         setProgress(val);
/*      */         
/* 2233 */         if (this.end) {
/*      */           break;
/*      */         }
/*      */       } 
/* 2237 */       if (this.end) {
/* 2238 */         setProgress(100);
/* 2239 */         t1 = System.currentTimeMillis();
/* 2240 */         t2 = t1 + 1000L;
/* 2241 */         while (t1 < t2) {
/* 2242 */           t1 = System.currentTimeMillis();
/* 2243 */           delay(100);
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/* 2248 */       this.slow = true;
/*      */     }
/*      */ 
/*      */     protected Void doInBackground() throws Exception {
/* 2264 */       retardation(this.t);
/* 2265 */       return null;
/*      */     }
/*      */

        private void addPropertyChangeListener(PanelClientServerNameController aThis) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
          
   }
/*      */   
/*      */   class Sentinel implements ActionListener
/*      */   {
/* 2274 */     Timer timer = new Timer(100, this);
/*      */ 
/*      */     
/*      */     public void stop() {
/* 2278 */       this.timer.stop();
/*      */     }
/*      */     
/*      */     public void start() {
/* 2282 */       this.timer.start();
/*      */     }
 
/*      */     public void actionPerformed(ActionEvent e) {
/* 2289 */       if (PanelClientServerNameController.this.sarlab != null) {
/* 2290 */         if (PanelClientServerNameController.this.sarlab.clientSarlab != null) {
/* 2291 */           PanelClientServerNameController.this.sarlab.clientSarlab.sendCommandServer("ping");
/*      */         }
/* 2293 */         if (PanelClientServerNameController.this.sarlab.clienteGIOT != null)
/* 2294 */           PanelClientServerNameController.this.sarlab.clienteGIOT.sendCommandServer("ping"); 
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelClientServerName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */