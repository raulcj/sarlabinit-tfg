/*      */ package es.uhu.sarlab.discovery.controller;

/*      */ import clientsarlab.EJSClientSarlab2;
/*      */ import clientsarlab.GIOTClientSarlab3;

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
import es.uhu.sarlab.discovery.vista.BarLogo;
import es.uhu.sarlab.discovery.vista.InterfazBarLogo;
import javafx.scene.layout.Pane;
import es.uhu.sarlab.discovery.vista.PanelListGateway;
/*      */ import es.uhu.sarlab.discovery.webservlet.model.HashMapExperienceWeb;
/*      */ import es.uhu.sarlab.discovery.webservlet.model.ListForwardingL;
import es.uhu.sarlab.utils.DirectoryFilesUtility;
/*      */ import es.uhu.sarlab.utils.OS;
/*      */ import es.uhu.sarlab.utils.PropertiesFiles;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.Image;
import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusAdapter;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.KeyAdapter;
/*      */ import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
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
/*      */ import java.util.Properties;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ButtonGroup;
/*      */ import javax.swing.DefaultListModel;
/*      */ import javax.swing.GroupLayout;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JDialog;
import javax.swing.JFrame;
/*      */ import javax.swing.JList;
/*      */ import javax.swing.JOptionPane;

/*      */ import javax.swing.LayoutStyle;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.UnsupportedLookAndFeelException;
/*      */ import modbussarlab.MasterModbus;

import org.apache.log4j.PropertyConfigurator;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;


/*      */ 
/*      */ public class PanelClientServerNameController implements Initializable {
    
/*   62 */   public final static Logger log = Logger.getLogger(PanelClientServerNameController.class.getName());
/*      */   String userLog;
/*      */   String passwdLog;
/*      */   boolean enteruserpas;
/*      */   BarLogo barLogo;
/*   71 */   String url = "";
/*      */   GIOTClientSarlab3 sarlab;
/*   73 */   DirectoryServiceRegisterName directoryRegisterNameSarlab = null;
/*   74 */   RegisterGateway registerGatewayOld = null;
/*   75 */   String myirtualFrontierOld = null;
/*   76 */   String myURIMyGatewayOld = null;
/*      */   RegisterName selecRegisterName;
/*   78 */   int portLocalVLC = 0;
/*   79 */   int portLocalHTTP = 0;
/*   80 */   //PanelListGateway listGateway = new PanelListGateway(this);
/*   81 */   //PanelEditConfigLocal editor = new PanelEditConfigLocal(this);
/*   82 */   //PanelNodoFrowardingL panelNodoFrowardingL = new PanelNodoFrowardingL(this);
/*   83 */   //PanelConectionLocalhost panelConectionLocalhost = new PanelConectionLocalhost(this);
/*   84 */   //PanelConectionExperience panelConectionExperience = new PanelConectionExperience(this);
/*   85 */   //ProgressBar progressBar = new ProgressBar();
/*   86 */   //ProgressBar progressBarSave = new ProgressBar();
/*      */   ProgressMonitor progressMonitor;
/*   88 */   //PanelConsole consoleText = new PanelConsole(this);
/*      */   private Task task;
/*      */   private Sentinel sentinel;
/*   92 */   String fileNameBase = "host.properties"; 
/*      */   ServerUDPThread serverUDPThread;
/*   95 */   ByteArrayOutputStream baos = null;
/*   96 */   PrintStream ps = null;
/*   97 */   PrintStream old = System.out;
/*   99 */   String locale = null; 
private ButtonGroup buttonGroup1; 
private Button jButton1; 
public Button jButtonActualizar; 
private Button jButtonConfiguracionLocal; 
private Button jButtonDesktop; 
public Button jButtonForwarding; 
public Button jButtonForwardingLook; 
private Button jButtonLocal; 
private Button jButtonLogin; 
private Button jButtonModbus; 
private Button jButtonNavegador; 
public Button jButtonProfile; 
private Button jButtonRemoto; 
private Button jButtonSFDTP; 
private Button jButtonSsh; 
private Button jButtonTelnet; 
public Button jButtonTools; 
private Button jButtonVNC; 
private JDialog jDialog1; 
private Label jLabel1; 
private Label jLabel2; 
private Label jLabel3; 
private Label jLabel4; 
private Label jLabel5;
public Label jLabel6;
private Label jLabel7;
private Label jLabel8; 
            
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
/*      */       
/*      */       }
/*      */     } 
/*  119 */     this.baos = new ByteArrayOutputStream();
/*  120 */     this.ps = new PrintStream(this.baos);
/*  121 */     System.setOut(this.ps);
/*  122 */     //initComponents();
/*  123 */     //setLocationRelativeTo((Component)null);
/*  124 */     ButtonEnabled(false);
/*  125 */     this.jButtonTools.setDisable(true);
/*  126 */     this.jButtonForwarding.setDisable(true);
/*  127 */     this.jButtonForwardingLook.setDisable(true);
/*  128 */     if (!OS.isWindows()) {
/*  129 */       this.jButtonLocal.setVisible(false);
/*      */     }
/*  131 */     this.jButtonProfile.setDisable(true);
/*  132 */     this.sentinel = new Sentinel();
/*      */   }
private Label jLabelList; 
public Label jLabelNetConnect; 
private Label jLabelTituloService; 
public JList<String> jList1; 
private Pane jPanel1; 
private Pane jPanel2; 
private Pane jPanel3; 
private Pane jPanel4; 
private Pane jPanel5; 
private Pane jPanel6; 
private Pane jPanel7; 
private Pane jPanelBotones; 
private Pane jPanelNavegador; 
private RadioButton jRadioAxi; 
private RadioButton jRadioEnlarge; 
private RadioButton jRadioFree; 
private ScrollPane jScrollPane1; 
private TextField jTextFieldDesktop; 
private TextField jTextFieldModbus; 
public TextField jTextFieldPortNav; 
private TextField jTextFieldSsh; 
private TextField jTextFieldTelnet; 
public TextField jTextFieldURL; 
private TextField jTextFieldVNC; 
private TextField jTextFieldsFTP; 
private TextField lineapass; 
private TextField lineausuario;

/*      */   public void ButtonEnabled(boolean enable) {
/*  136 */     this.jButtonDesktop.setVisible(enable);
/*  137 */     this.jButtonModbus.setVisible(enable);
/*  138 */     this.jButtonSFDTP.setVisible(enable);
/*  139 */     this.jButtonSsh.setVisible(enable);
/*  140 */     this.jButtonTelnet.setVisible(enable);
/*  141 */     this.jButtonVNC.setVisible(enable);
/*  142 */     this.jButtonNavegador.setVisible(enable);
/*  143 */     this.jButtonActualizar.setVisible(enable);
/*  144 */     this.jTextFieldURL.setVisible(enable);
/*  145 */     this.jTextFieldDesktop.setVisible(enable);
/*  146 */     this.jTextFieldModbus.setVisible(enable);
/*  147 */     this.jTextFieldPortNav.setVisible(enable);
/*  148 */     this.jTextFieldSsh.setVisible(enable);
/*  149 */     this.jTextFieldTelnet.setVisible(enable);
/*  150 */     this.jTextFieldVNC.setVisible(enable);
/*  151 */     this.jTextFieldsFTP.setVisible(enable);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Image getIconImage() {
/*  157 */     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("es/uhu/sarlab/discovery/vista/resources/Remote-Desktop32x32.png"));
/*  158 */     return retValue;
/*      */   }
/*      */   
/*      */   public void init() {
/*  162 */     this.barLogo = new BarLogo((InterfazBarLogo) this);
/*  163 */     this.barLogo.init();
/*      */   }
/*      */   
/*      */   public String ipInit() {
/*  167 */     if (this.directoryRegisterNameSarlab == null) {
/*  168 */       return "";
/*      */     }
/*  170 */     return this.directoryRegisterNameSarlab.iPInit();
/*      */   }
/*      */   
/*      */   public void setListModel(DefaultListModel<String> defaultListModel) {
/*  174 */     this.jList1.setModel(defaultListModel);
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
/*  185 */     setListModel(serverUDPThread.getListModel());
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
/*  201 */     this.sarlab.addconnectionLocalhostL(deviceName, ipRemote, portRemote, portLocal);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addForwardingLocalAdressL(String deviceName, String ipRemote, int portRemote, int portLocal) {
/*  206 */     this.sarlab.addconnectionLocalAddressL(deviceName, ipRemote, portRemote, portLocal);
/*      */   }
/*      */   
/*      */   public void addForwardingExperienceLocalhostL(ListForwardingL listForwardingL) {
/*  210 */     ArrayList<Connection> a = listForwardingL.getListconnect();
/*  211 */     for (Connection c : a) {
/*  212 */       addForwardingLocalhostL(c.getDescription(), c.getIpRemote(), c.getPortRemote(), c.getPortLocal());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initComponents() {
/*  225 */     this.buttonGroup1 = new ButtonGroup();
/*  226 */     this.jDialog1 = new JDialog();
/*  227 */     this.jPanel4 = new Pane();
/*  228 */     this.jLabel2 = new Label();
/*  229 */     this.jPanel5 = new Pane();
/*  230 */     this.lineausuario = new TextField();
/*  231 */     this.jLabel7 = new Label();
/*  232 */     this.jLabel8 = new Label();
/*  233 */     this.lineapass = new PasswordField();
/*  234 */     this.jPanel7 = new Pane();
/*  235 */     this.jButtonLogin = new Button();
/*  236 */     this.jPanelBotones = new Pane();
/*  237 */     this.jButtonActualizar = new Button();
/*  238 */     this.jButtonRemoto = new Button();
/*  239 */     this.jButtonLocal = new Button();
/*  240 */     this.jButtonConfiguracionLocal = new Button();
/*  241 */     this.jButton1 = new Button();
/*  242 */     this.jPanel2 = new Pane();
/*  243 */     this.jLabelList = new Label();
/*  244 */     this.jPanel1 = new Pane();
/*  245 */     this.jScrollPane1 = new ScrollPane();
/*  246 */     this.jList1 = new JList<>();
/*  247 */     this.jPanel3 = new Pane();
/*  248 */     this.jLabel4 = new Label();
/*  249 */     this.jLabel5 = new Label();
/*  250 */     this.jLabel6 = new Label();
/*  251 */     this.jLabelNetConnect = new Label();
/*  252 */     this.jPanel6 = new Pane();
/*  253 */     this.jButtonDesktop = new Button();
/*  254 */     this.jTextFieldDesktop = new TextField();
/*  255 */     this.jButtonVNC = new Button();
/*  256 */     this.jTextFieldVNC = new TextField();
/*  257 */     this.jButtonTelnet = new Button();
/*  258 */     this.jTextFieldTelnet = new TextField();
/*  259 */     this.jButtonSsh = new Button();
/*  260 */     this.jTextFieldSsh = new TextField();
/*  261 */     this.jButtonSFDTP = new Button();
/*  262 */     this.jTextFieldsFTP = new TextField();
/*  263 */     this.jButtonModbus = new Button();
/*  264 */     this.jTextFieldModbus = new TextField();
/*  265 */     this.jLabelTituloService = new Label();
/*  266 */     this.jButtonTools = new Button();
/*  267 */     this.jButtonForwarding = new Button();
/*  268 */     this.jButtonForwardingLook = new Button();
/*  269 */     this.jButtonProfile = new Button();
/*  270 */     this.jPanelNavegador = new Pane();
/*  271 */     this.jButtonNavegador = new Button();
/*  272 */     this.jLabel1 = new Label();
/*  273 */     this.jTextFieldPortNav = new TextField();
/*  274 */     this.jLabel3 = new Label();
/*  275 */     this.jTextFieldURL = new TextField();
/*  276 */     this.jRadioAxi = new RadioButton();
/*  277 */     this.jRadioEnlarge = new RadioButton();
/*  278 */     this.jRadioFree = new RadioButton();
/*      */     
/*  280 */     this.jDialog1.setIconImage(getIconImage());
/*  281 */     this.jDialog1.setMinimumSize(new Dimension(400, 219));
/*  282 */     this.jDialog1.setModal(true);
/*  283 */     this.jDialog1.setResizable(false);
/*  284 */     this.jDialog1.setSize(new Dimension(400, 219));
/*      */     
/*  286 */     
/*      */     
/*  288 */     
/*  289 */    
/*  290 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vistaTFG/Bundle_PanelClientServerName");
/*  291 */     this.jLabel2.setText(bundle.getString("PanelClientServerName.jLabel2.text_1"));
/*      */     
/*  293 */    
/*  294 */    
/*  295 */    
/*      */     
/*  302 */ 
/*      */     
/*  310 */     this.lineausuario.addKeyListener(new KeyAdapter() {
/*      */           public void keyPressed(KeyEvent evt) {
/*  312 */             PanelClientServerNameController.this.lineausuarioKeyPressed(evt);
/*      */           }
/*      */         });
/*      */     
/*  316 */   
/*  317 */     this.jLabel7.setText(bundle.getString("PanelClientServerName.jLabel7.text_1"));
/*      */     
/*  319 */    
/*  320 */     this.jLabel8.setText(bundle.getString("PanelClientServerName.jLabel8.text_1"));
/*      */     /*      */     
/*  322 */     this.lineapass.addKeyListener(new KeyAdapter() {
/*      */           public void keyPressed(KeyEvent evt) {
/*  324 */             PanelClientServerNameController.this.lineapassKeyPressed(evt);
/*      */           }
/*      */         });
/*      */     
/*  328 */   
/*      */     
/*  343 */   
/*      */ 
/*      */     
/*  357 */     
/*      */     
/*  359 */     this.jButtonLogin.setText(bundle.getString("PanelClientServerName.jButtonLogin.text_1"));
/*  360 */     this.jButtonLogin.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  362 */             PanelClientServerNameController.this.jButtonLoginActionPerformed(evt);
/*      */           }
/*      */         });
/*  365 */     this.jButtonLogin.addKeyListener(new KeyAdapter() {
/*      */           public void keyPressed(KeyEvent evt) {
/*  367 */             PanelClientServerNameController.this.jButtonLoginKeyPressed(evt);
/*      */           }
/*      */         });
/*      */     
/*  371 */  
/*      */     
/*  380 */    
/*      */ 
/*      */     
/*  388 */     
/*      */     
/*  400 */    
/*      */ 
/*      */     
/*  412 */     //setTitle(bundle.getString("PanelClientServerName.title"));
/*  413 */    
/*  415 */     //setIconImage(getIconImage());
/*      */     
/*  417 */    
/*  418 */     this.jPanelBotones.setBorder(BorderFactory.createTitledBorder(""));
/*      */     
/*  420 */     this.jButtonActualizar.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/actualiza.png")));
/*  421 */     this.jButtonActualizar.setText(bundle.getString("PanelClientServerName.jButtonActualizar.text"));
/*  422 */     this.jButtonActualizar.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  424 */             PanelClientServerName.this.jButtonActualizarActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  428 */     this.jButtonRemoto.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/remota.png")));
/*  429 */     this.jButtonRemoto.setText(bundle.getString("PanelClientServerName.jButtonRemoto.text"));
/*  430 */     this.jButtonRemoto.setToolTipText(bundle.getString("PanelClientServerName.jButtonRemoto.toolTipText"));
/*  431 */     this.jButtonRemoto.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  433 */             PanelClientServerName.this.jButtonRemotoActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  437 */     this.jButtonLocal.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/config.png")));
/*  438 */     this.jButtonLocal.setText(bundle.getString("PanelClientServerName.jButtonLocal.text"));
/*  439 */     this.jButtonLocal.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  441 */             PanelClientServerName.this.jButtonLocalActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  445 */     this.jButtonConfiguracionLocal.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/lan24x24.png")));
/*  446 */     this.jButtonConfiguracionLocal.setText(bundle.getString("PanelClientServerName.jButtonConfiguracionLocal.text"));
/*  447 */     this.jButtonConfiguracionLocal.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  449 */             PanelClientServerName.this.jButtonConfiguracionLocalActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  453 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/demo.png")));
/*  454 */     this.jButton1.setText(bundle.getString("PanelClientServerName.jButton1.text_1"));
/*  455 */     this.jButton1.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  457 */             PanelClientServerName.this.jButton1ActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  461 */  
/*      */ 
/*      */     
/*  494 */     this.jPanel2.setBackground(new Color(102, 153, 255));
/*  495 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder(bundle.getString("PanelClientServerName.jPanel2.border.title")));
/*      */     
/*  497 */     this.jLabelList.setFont(new Font("Tahoma", 1, 16));
/*  498 */     
/*  499 */     this.jLabelList.setText(bundle.getString("PanelClientServerName.jLabelList.text"));
/*      */     
/*  501 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(""));
/*      */     
/*  503 */     this.jList1.setFont(new Font("Courier New", 0, 16));
/*  504 */     this.jList1.setToolTipText(bundle.getString("PanelClientServerName.jList1.toolTipText"));
/*  505 */     this.jList1.addMouseListener(new MouseAdapter() {
/*      */           public void mouseClicked(MouseEvent evt) {
/*  507 */             PanelClientServerName.this.jList1MouseClicked(evt);
/*      */           }
/*      */         });
/*  510 */     this.jScrollPane1.setViewportView(this.jList1);
/*      */     
/*  512 */   
/*      */ 
/*      */     
/*  529 */   
/*      */    
/*      */ 
/*      */     
/*  549 */     this.jPanel3.setBackground(new Color(153, 204, 255));
/*  550 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder(""));
/*      */     
/*  552 */     this.jLabel4.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/Remote-Desktop64x64.png")));
/*      */     
/*  554 */     this.jLabel5.setFont(new Font("Arial Black", 1, 24));
/*  555 */     this.jLabel5.setHorizontalAlignment(0);
/*  556 */     this.jLabel5.setText(bundle.getString("PanelClientServerName.jLabel5.text"));
/*      */     
/*  558 */     this.jLabel6.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/LAN64x64.png")));
/*      */     
/*  560 */     this.jLabelNetConnect.setFont(new Font("Tahoma", 1, 11));
/*  561 */     this.jLabelNetConnect.setText(bundle.getString("PanelClientServerName.jLabelNetConnect.text"));
/*      */     
/*  563 */     
/*      */ 
/*      */     
/*  596 */     this.jPanel6.setBackground(new Color(102, 153, 255));
/*  597 */     this.jPanel6.setBorder(BorderFactory.createTitledBorder(bundle.getString("PanelClientServerName.jPanel6.border.title")));
/*      */     
/*  599 */     this.jButtonDesktop.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/Display 48_24x24-32.gif")));
/*  600 */     this.jButtonDesktop.setText(bundle.getString("PanelClientServerName.jButtonDesktop.text"));
/*  601 */     this.jButtonDesktop.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  603 */             PanelClientServerName.this.jButtonDesktopActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  607 */     this.jTextFieldDesktop.setText(bundle.getString("PanelClientServerName.jTextFieldDesktop.text"));
/*  608 */     this.jTextFieldDesktop.addFocusListener(new FocusAdapter() {
/*      */           public void focusGained(FocusEvent evt) {
/*  610 */             PanelClientServerName.this.jTextFieldDesktopFocusGained(evt);
/*      */           }
/*      */           public void focusLost(FocusEvent evt) {
/*  613 */             PanelClientServerName.this.jTextFieldDesktopFocusLost(evt);
/*      */           }
/*      */         });
/*  616 */     this.jTextFieldDesktop.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  618 */             PanelClientServerName.this.jTextFieldDesktopActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  622 */     this.jButtonVNC.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/vnc.png")));
/*  623 */     this.jButtonVNC.setText(bundle.getString("PanelClientServerName.jButtonVNC.text"));
/*  624 */     this.jButtonVNC.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  626 */             PanelClientServerName.this.jButtonVNCActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  630 */     this.jTextFieldVNC.setText(bundle.getString("PanelClientServerName.jTextFieldVNC.text"));
/*  631 */     this.jTextFieldVNC.addFocusListener(new FocusAdapter() {
/*      */           public void focusGained(FocusEvent evt) {
/*  633 */             PanelClientServerName.this.jTextFieldVNCFocusGained(evt);
/*      */           }
/*      */           public void focusLost(FocusEvent evt) {
/*  636 */             PanelClientServerName.this.jTextFieldVNCFocusLost(evt);
/*      */           }
/*      */         });
/*  639 */     this.jTextFieldVNC.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  641 */             PanelClientServerName.this.jTextFieldVNCActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  645 */     this.jButtonTelnet.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/telnet.png")));
/*  646 */     this.jButtonTelnet.setText(bundle.getString("PanelClientServerName.jButtonTelnet.text"));
/*  647 */     this.jButtonTelnet.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  649 */             PanelClientServerName.this.jButtonTelnetActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  653 */     this.jTextFieldTelnet.setText(bundle.getString("PanelClientServerName.jTextFieldTelnet.text"));
/*  654 */     this.jTextFieldTelnet.addFocusListener(new FocusAdapter() {
/*      */           public void focusGained(FocusEvent evt) {
/*  656 */             PanelClientServerName.this.jTextFieldTelnetFocusGained(evt);
/*      */           }
/*      */           public void focusLost(FocusEvent evt) {
/*  659 */             PanelClientServerName.this.jTextFieldTelnetFocusLost(evt);
/*      */           }
/*      */         });
/*  662 */     this.jTextFieldTelnet.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  664 */             PanelClientServerName.this.jTextFieldTelnetActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  668 */     this.jButtonSsh.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/ssh.png")));
/*  669 */     this.jButtonSsh.setText(bundle.getString("PanelClientServerName.jButtonSsh.text"));
/*  670 */     this.jButtonSsh.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  672 */             PanelClientServerName.this.jButtonSshActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  676 */     this.jTextFieldSsh.setText(bundle.getString("PanelClientServerName.jTextFieldSsh.text"));
/*  677 */     this.jTextFieldSsh.addFocusListener(new FocusAdapter() {
/*      */           public void focusGained(FocusEvent evt) {
/*  679 */             PanelClientServerName.this.jTextFieldSshFocusGained(evt);
/*      */           }
/*      */           public void focusLost(FocusEvent evt) {
/*  682 */             PanelClientServerName.this.jTextFieldSshFocusLost(evt);
/*      */           }
/*      */         });
/*  685 */     this.jTextFieldSsh.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  687 */             PanelClientServerName.this.jTextFieldSshActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  691 */     this.jButtonSFDTP.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/Folder 102_24x24-32.gif")));
/*  692 */     this.jButtonSFDTP.setText(bundle.getString("PanelClientServerName.jButtonSFDTP.text"));
/*  693 */     this.jButtonSFDTP.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  695 */             PanelClientServerName.this.jButtonSFDTPActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  699 */     this.jTextFieldsFTP.setText(bundle.getString("PanelClientServerName.jTextFieldsFTP.text"));
/*  700 */     this.jTextFieldsFTP.setToolTipText(bundle.getString("PanelClientServerName.jTextFieldsFTP.toolTipText"));
/*  701 */     this.jTextFieldsFTP.addFocusListener(new FocusAdapter() {
/*      */           public void focusGained(FocusEvent evt) {
/*  703 */             PanelClientServerName.this.jTextFieldsFTPFocusGained(evt);
/*      */           }
/*      */           public void focusLost(FocusEvent evt) {
/*  706 */             PanelClientServerName.this.jTextFieldsFTPFocusLost(evt);
/*      */           }
/*      */         });
/*  709 */     this.jTextFieldsFTP.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  711 */             PanelClientServerName.this.jTextFieldsFTPActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  715 */     this.jButtonModbus.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/modbus.png")));
/*  716 */     this.jButtonModbus.setText(bundle.getString("PanelClientServerName.jButtonModbus.text"));
/*  717 */     this.jButtonModbus.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  719 */             PanelClientServerName.this.jButtonModbusActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  723 */     this.jTextFieldModbus.setText(bundle.getString("PanelClientServerName.jTextFieldModbus.text"));
/*  724 */     this.jTextFieldModbus.setToolTipText(bundle.getString("PanelClientServerName.jTextFieldModbus.toolTipText"));
/*  725 */     this.jTextFieldModbus.addFocusListener(new FocusAdapter() {
/*      */           public void focusGained(FocusEvent evt) {
/*  727 */             PanelClientServerName.this.jTextFieldModbusFocusGained(evt);
/*      */           }
/*      */           public void focusLost(FocusEvent evt) {
/*  730 */             PanelClientServerName.this.jTextFieldModbusFocusLost(evt);
/*      */           }
/*      */         });
/*  733 */     this.jTextFieldModbus.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  735 */             PanelClientServerName.this.jTextFieldModbusActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  739 */     this.jLabelTituloService.setFont(new Font("Tahoma", 1, 12));
/*  740 */     this.jLabelTituloService.setText(bundle.getString("PanelClientServerName.jLabelTituloService.text"));
/*      */     
/*  742 */     this.jButtonTools.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/tools.png")));
/*  743 */     this.jButtonTools.setText(bundle.getString("PanelClientServerName.jButtonTools.text"));
/*  744 */     this.jButtonTools.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  746 */             PanelClientServerName.this.jButtonToolsActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  750 */     this.jButtonForwarding.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/tunel.png")));
/*  751 */     this.jButtonForwarding.setText(bundle.getString("PanelClientServerName.jButtonForwarding.text"));
/*  752 */     this.jButtonForwarding.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  754 */             PanelClientServerName.this.jButtonForwardingActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  758 */     this.jButtonForwardingLook.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/busco.png")));
/*  759 */     this.jButtonForwardingLook.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  761 */             PanelClientServerName.this.jButtonForwardingLookActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  765 */     this.jButtonProfile.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/ver.png")));
/*  766 */     this.jButtonProfile.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  768 */             PanelClientServerName.this.jButtonProfileActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  772 */     
/*      */     
/*  857 */     this.jPanelNavegador.setBackground(new Color(51, 153, 255));
/*  858 */     this.jPanelNavegador.setBorder(BorderFactory.createTitledBorder(bundle.getString("PanelClientServerName.jPanelNavegador.border.title")));
/*      */     
/*  860 */     this.jButtonNavegador.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/navegador.png")));
/*  861 */     this.jButtonNavegador.setText(bundle.getString("PanelClientServerName.jButtonNavegador.text"));
/*  862 */     this.jButtonNavegador.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  864 */             PanelClientServerName.this.jButtonNavegadorActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  868 */     this.jLabel1.setFont(new Font("Tahoma", 1, 16));
/*  869 */     this.jLabel1.setText(bundle.getString("PanelClientServerName.jLabel1.text"));
/*      */     
/*  871 */     this.jTextFieldPortNav.setText(bundle.getString("PanelClientServerName.jTextFieldPortNav.text"));
/*  872 */     this.jTextFieldPortNav.addFocusListener(new FocusAdapter() {
/*      */           public void focusGained(FocusEvent evt) {
/*  874 */             PanelClientServerName.this.jTextFieldPortNavFocusGained(evt);
/*      */           }
/*      */           public void focusLost(FocusEvent evt) {
/*  877 */             PanelClientServerName.this.jTextFieldPortNavFocusLost(evt);
/*      */           }
/*      */         });
/*  880 */     this.jTextFieldPortNav.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  882 */             PanelClientServerName.this.jTextFieldPortNavActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  886 */     this.jLabel3.setFont(new Font("Tahoma", 1, 16));
/*  887 */     this.jLabel3.setText(bundle.getString("PanelClientServerName.jLabel3.text"));
/*      */     
/*  889 */     this.jTextFieldURL.setToolTipText(bundle.getString("PanelClientServerName.jTextFieldURL.toolTipText"));
/*  890 */     this.jTextFieldURL.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  892 */             PanelClientServerName.this.jTextFieldURLActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  896 */     this.buttonGroup1.add(this.jRadioAxi);
/*  897 */     this.jRadioAxi.setFont(new Font("Tahoma", 1, 12));
/*  898 */     this.jRadioAxi.setText(bundle.getString("PanelClientServerName.jRadioAxi.text"));
/*  899 */     this.jRadioAxi.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  901 */             PanelClientServerName.this.jRadioAxiActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  905 */     this.buttonGroup1.add(this.jRadioEnlarge);
/*  906 */     this.jRadioEnlarge.setFont(new Font("Tahoma", 1, 12));
/*  907 */     this.jRadioEnlarge.setText(bundle.getString("PanelClientServerName.jRadioEnlarge.text"));
/*  908 */     this.jRadioEnlarge.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  910 */             PanelClientServerName.this.jRadioEnlargeActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  914 */     this.buttonGroup1.add(this.jRadioFree);
/*  915 */     this.jRadioFree.setFont(new Font("Tahoma", 1, 12));
/*  916 */     this.jRadioFree.setText(bundle.getString("PanelClientServerName.jRadioFree.text"));
/*  917 */     this.jRadioFree.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  919 */             PanelClientServerName.this.jRadioFreeActionPerformed(evt);
/*      */           }
/*      */         });
/*      */     
/*  923 */    
/*      */   }
/*      */   
/*      */   public void toUpdate() {
/*  989 */     DefaultListModel<String> defaultListModel = new DefaultListModel();
/*  990 */     this.jList1.setModel(defaultListModel);
/*  991 */     if (this.sarlab == null) {
/*  992 */       this.barLogo.init();
/*  993 */       ButtonEnabled(false);
/*  994 */       this.jButtonActualizar.setDisable(true);
/*      */     } else {
/*  996 */       GetParameters getParameters = new GetParameters();
/*  997 */       this.directoryRegisterNameSarlab = getParameters.getDirectoryRegisterNameSarlab(this.registerGatewayOld);
/*  998 */       if (this.directoryRegisterNameSarlab != null) {
/*      */         
/* 1000 */         defaultListModel = this.directoryRegisterNameSarlab.getListModel();
/* 1001 */         this.jList1.setModel(defaultListModel);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jButtonActualizarActionPerformed(ActionEvent evt) {
/* 1007 */     removeService();
/* 1008 */     toUpdate();
/* 1009 */     if (this.directoryRegisterNameSarlab != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jTextFieldDesktopActionPerformed(ActionEvent evt) {
/* 1016 */     this.jTextFieldDesktop.setBackground(Color.WHITE);
/* 1017 */     String port = this.jTextFieldDesktop.getText();
/* 1018 */     if ("".equals(port)) {
/* 1019 */       this.jTextFieldDesktop.setText("3389");
/* 1020 */       port = "3389";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jList1MouseClicked(MouseEvent evt) {
/*      */     RegisterName registerName;
/* 1026 */     ButtonEnabled(true);
/* 1027 */     int ind = this.jList1.getSelectedIndex();
/*      */ 
/*      */     
/* 1030 */     if (this.sarlab == null) {
/*      */       
/* 1032 */       registerName = this.serverUDPThread.getRegisterNametoArray(ind);
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
/*      */       
/* 1046 */       this.selecRegisterName = registerName;
/* 1047 */       selectService(registerName);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void selectService(RegisterName registerName) {
/* 1055 */     this.jTextFieldDesktop.setVisible(registerName.isRdesktop());
/* 1056 */     this.jTextFieldDesktop.setText(Integer.toString(registerName.getRdesktopPort()));
/* 1057 */     this.jButtonDesktop.setVisible(registerName.isRdesktop());
/* 1058 */     this.jTextFieldVNC.setVisible(registerName.isVnc());
/* 1059 */     this.jTextFieldVNC.setText(Integer.toString(registerName.getVncPort()));
/* 1060 */     this.jButtonVNC.setVisible(registerName.isVnc());
/* 1061 */     this.jTextFieldTelnet.setVisible(registerName.isTelnet());
/* 1062 */     this.jTextFieldTelnet.setText(Integer.toString(registerName.getTelnetPort()));
/* 1063 */     this.jButtonTelnet.setVisible(registerName.isTelnet());
/* 1064 */     this.jTextFieldSsh.setVisible(registerName.isSsh());
/* 1065 */     this.jTextFieldSsh.setText(Integer.toString(registerName.getSshPort()));
/* 1066 */     this.jButtonSsh.setVisible(registerName.isSsh());
/* 1067 */     this.jTextFieldsFTP.setVisible(registerName.isSftp());
/* 1068 */     this.jTextFieldsFTP.setText(Integer.toString(registerName.getSftpPort()));
/* 1069 */     this.jButtonSFDTP.setVisible(registerName.isSftp());
/* 1070 */     this.jTextFieldModbus.setVisible(registerName.isModbus());
/* 1071 */     this.jTextFieldModbus.setText(Integer.toString(registerName.getModbusPort()));
/* 1072 */     this.jButtonModbus.setVisible(registerName.isModbus());
/* 1073 */     this.jTextFieldURL.setVisible(registerName.isHttp());
/* 1074 */     this.jTextFieldPortNav.setVisible(registerName.isHttp());
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
/*      */     
/* 1091 */     initURL();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeService() {
/* 1097 */     this.jTextFieldDesktop.setDisable(true);
/* 1098 */     this.jTextFieldDesktop.setText("");
/* 1099 */     this.jButtonDesktop.setDisable(true);
/* 1100 */     this.jTextFieldVNC.setDisable(true);
/* 1101 */     this.jTextFieldVNC.setText("");
/* 1102 */     this.jButtonVNC.setDisable(true);
/* 1103 */     this.jTextFieldTelnet.setDisable(true);
/* 1104 */     this.jTextFieldTelnet.setText("");
/* 1105 */     this.jButtonTelnet.setDisable(true);
/* 1106 */     this.jTextFieldSsh.setDisable(true);
/* 1107 */     this.jTextFieldSsh.setText("");
/* 1108 */     this.jButtonSsh.setDisable(true);
/* 1109 */     this.jTextFieldsFTP.setDisable(true);
/* 1110 */     this.jTextFieldsFTP.setText("");
/* 1111 */     this.jButtonSFDTP.setDisable(true);
/* 1112 */     this.jTextFieldModbus.setDisable(true);
/* 1113 */     this.jTextFieldModbus.setText("");
/* 1114 */     this.jButtonModbus.setDisable(true);
/* 1115 */     this.jTextFieldURL.setDisable(true);
/* 1116 */     this.jTextFieldPortNav.setDisable(true);
/* 1117 */     this.jRadioAxi.setDisable(true);
/* 1118 */     this.jRadioEnlarge.setDisable(true);
/* 1119 */     this.jRadioFree.setDisable(true);
/* 1120 */     this.jTextFieldURL.setText("");
/* 1121 */     this.jTextFieldPortNav.setText("");
/*      */   }
/*      */   private void jButtonDesktopActionPerformed(ActionEvent evt) {
/*      */     int port;
/* 1125 */     RegisterName registerName = null;
/*      */ 
/*      */     
/* 1128 */     int ind = this.jList1.getSelectedIndex();
/* 1129 */     if (this.sarlab == null) {
/*      */       
/* 1131 */       registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1133 */       registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1135 */     String sPort = this.jTextFieldDesktop.getText();
/*      */     try {
/* 1137 */       port = Integer.parseInt(sPort);
/* 1138 */     } catch (NumberFormatException ex) {
/* 1139 */       this.jTextFieldDesktop.setText("3389");
/* 1140 */       port = 3389;
/*      */     } 
/* 1142 */     if (registerName != null) {
/* 1143 */       this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1144 */       ExecApp exec = new ExecApp();
/* 1145 */       String deviceName = registerName.getIp() + ":" + sPort;
/* 1146 */       if (this.sarlab == null) {
/* 1147 */         if (OS.isWindows()) {
/* 1148 */           exec.rdesktop(registerName.getIp(), port);
/*      */         } else {
/* 1150 */           exec.exec("/usr/bin/rdesktop " + registerName.getIp() + ":" + port);
/*      */         } 
/*      */       } else {
/* 1153 */         int portLocal; if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1154 */           portLocal = this.sarlab.getPortLocal(deviceName);
/*      */         } else {
/* 1156 */           portLocal = freePort();
/* 1157 */           this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, portLocal);
/*      */         } 
/* 1159 */         if (OS.isWindows()) {
/* 1160 */           exec.rdesktop((EJSClientSarlab2)this.sarlab, registerName.getIp(), port);
/*      */         } else {
/* 1162 */           exec.exec("/usr/bin/rdesktop 127.0.0.1:" + portLocal);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void jTextFieldsFTPActionPerformed(ActionEvent evt) {
/* 1170 */    
/* 1171 */     String port = this.jTextFieldsFTP.getText();
/* 1172 */     if ("".equals(port)) {
/* 1173 */       this.jTextFieldsFTP.setText("22");
/* 1174 */       port = "22";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldModbusActionPerformed(ActionEvent evt) {
/* 1179 */    
/* 1180 */     String port = this.jTextFieldModbus.getText();
/* 1181 */     if ("".equals(port)) {
/* 1182 */       this.jTextFieldModbus.setText("502");
/* 1183 */       port = "502";
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void jTextFieldPortNavActionPerformed(ActionEvent evt) {
/* 1189 */    
/* 1190 */     updateURL();
/*      */   }
/*      */   
/*      */   private void jTextFieldPortNavFocusLost(FocusEvent evt) {
/* 1194 */    
/* 1195 */     updateURL();
/*      */   }
/*      */   
/*      */   private void jTextFieldPortNavFocusGained(FocusEvent evt) {
/* 1199 */    
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
/*      */   private void jButtonVNCActionPerformed(ActionEvent evt) {
/*      */     RegisterName registerName;
/* 1227 */     int port = 0;
/* 1228 */     int ind = this.jList1.getSelectedIndex();
/*      */ 
/*      */     
/* 1231 */     if (this.sarlab == null) {
/*      */       
/* 1233 */       registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1235 */       registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1237 */     String sPort = this.jTextFieldVNC.getText();
/*      */     try {
/* 1239 */       port = Integer.parseInt(sPort);
/* 1240 */     } catch (NumberFormatException ex) {
/* 1241 */       this.jTextFieldVNC.setText("5900");
/* 1242 */       sPort = "5900";
/*      */     } 
/* 1244 */     if (registerName != null) {
/* 1245 */       this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1246 */       ExecApp exec = new ExecApp();
/* 1247 */       String programVNC = "C:/Program Files/RealVNC/VNC Viewer/vncviewer.exe ";
/*      */       
/* 1249 */       String deviceName = registerName.getIp() + ":" + sPort;
/* 1250 */       if (this.sarlab == null) {
/* 1251 */         if (OS.isWindows()) {
/* 1252 */           exec.exec(programVNC + registerName.getIp() + ":" + sPort);
/*      */         } else {
/* 1254 */           exec.exec("/usr/bin/vncviewer " + registerName.getIp() + ":" + sPort);
/*      */         } 
/*      */       } else {
/* 1257 */         if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1258 */           this.portLocalVLC = this.sarlab.getPortLocal(deviceName);
/*      */         } else {
/* 1260 */           this.portLocalVLC = freePort();
/* 1261 */           this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, this.portLocalVLC);
/*      */         } 
/* 1263 */         if (OS.isWindows()) {
/* 1264 */           exec.exec(programVNC + "127.0.0.1:" + Integer.toString(this.portLocalVLC));
/*      */         } else {
/* 1266 */           exec.exec("/usr/bin/vncviewer 127.0.0.1:" + Integer.toString(this.portLocalVLC));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void jTextFieldDesktopFocusGained(FocusEvent evt) {
/* 1275 */    
/*      */   }
/*      */   
/*      */   private void jTextFieldVNCFocusGained(FocusEvent evt) {
/* 1279 */    
/*      */   }
/*      */   
/*      */   private void jTextFieldTelnetFocusGained(FocusEvent evt) {
/* 1283 */    
/*      */   }
/*      */   
/*      */   private void jTextFieldSshFocusGained(FocusEvent evt) {
/* 1287 */    
/*      */   }
/*      */   
/*      */   private void jTextFieldsFTPFocusGained(FocusEvent evt) {
/* 1291 */    
/*      */   }
/*      */   
/*      */   private void jTextFieldModbusFocusGained(FocusEvent evt) {
/* 1295 */     
/*      */   }
/*      */   
/*      */   private void jTextFieldDesktopFocusLost(FocusEvent evt) {
/* 1299 */    
/* 1300 */     String port = this.jTextFieldDesktop.getText();
/* 1301 */     if ("".equals(port)) {
/* 1302 */       this.jTextFieldDesktop.setText("3389");
/* 1303 */       port = "3389";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldVNCFocusLost(FocusEvent evt) {
/* 1308 */     
/* 1309 */     String port = this.jTextFieldVNC.getText();
/* 1310 */     if ("".equals(port)) {
/* 1311 */       this.jTextFieldVNC.setText("5900");
/* 1312 */       port = "5900";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldTelnetFocusLost(FocusEvent evt) {
/* 1317 */     
/* 1318 */     String port = this.jTextFieldTelnet.getText();
/* 1319 */     if ("".equals(port)) {
/* 1320 */       this.jTextFieldTelnet.setText("23");
/* 1321 */       port = "23";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldSshFocusLost(FocusEvent evt) {
/* 1326 */     
/* 1327 */     String port = this.jTextFieldSsh.getText();
/* 1328 */     if ("".equals(port)) {
/* 1329 */       this.jTextFieldSsh.setText("22");
/* 1330 */       port = "22";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldsFTPFocusLost(FocusEvent evt) {
/* 1335 */    
/* 1336 */     String port = this.jTextFieldsFTP.getText();
/* 1337 */     if ("".equals(port)) {
/* 1338 */       this.jTextFieldsFTP.setText("22");
/* 1339 */       port = "22";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldModbusFocusLost(FocusEvent evt) {
/* 1344 */     
/* 1345 */     String port = this.jTextFieldModbus.getText();
/* 1346 */     if ("".equals(port)) {
/* 1347 */       this.jTextFieldModbus.setText("502");
/* 1348 */       port = "502";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldVNCActionPerformed(ActionEvent evt) {
/* 1353 */    
/* 1354 */     String port = this.jTextFieldVNC.getText();
/* 1355 */     if ("".equals(port)) {
/* 1356 */       this.jTextFieldVNC.setText("5900");
/* 1357 */       port = "5900";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldTelnetActionPerformed(ActionEvent evt) {
/* 1362 */     
/* 1363 */     String port = this.jTextFieldTelnet.getText();
/* 1364 */     if ("".equals(port)) {
/* 1365 */       this.jTextFieldTelnet.setText("23");
/* 1366 */       port = "23";
/*      */     } 
/*      */   }
/*      */   
/*      */   private void jTextFieldSshActionPerformed(ActionEvent evt) {
/* 1371 */     
/* 1372 */     String port = this.jTextFieldSsh.getText();
/* 1373 */     if ("".equals(port)) {
/* 1374 */       this.jTextFieldSsh.setText("22");
/* 1375 */       port = "22";
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButtonSshActionPerformed(ActionEvent evt) {
/*      */     RegisterName registerName;
/* 1382 */     int port, ind = this.jList1.getSelectedIndex();
/*      */ 
/*      */ 
/*      */     
/* 1386 */     if (this.sarlab == null) {
/*      */       
/* 1388 */       registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1390 */       registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1392 */     String sPort = this.jTextFieldSsh.getText();
/*      */     try {
/* 1394 */       port = Integer.parseInt(sPort);
/* 1395 */     } catch (NumberFormatException ex) {
/* 1396 */       this.jTextFieldSsh.setText("22");
/* 1397 */       port = 22;
/*      */     } 
/* 1399 */     if (registerName != null) {
/* 1400 */       this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1401 */       ExecApp exec = new ExecApp();
/* 1402 */       String deviceName = registerName.getIp() + ":" + sPort;
/*      */       
/* 1404 */       if (this.sarlab == null) {
/* 1405 */         if (OS.isWindows()) {
/* 1406 */           exec.ssh(registerName.getIp(), port);
/*      */         } else {
/* 1408 */           String seleccion = JOptionPane.showInputDialog("User");
/* 1409 */           if (seleccion == null) {
/*      */             return;
/*      */           }
/*      */           
/* 1413 */           exec.exec("/usr/bin/xterm -fa 'Monospace' -fs 14 -T ssh_" + registerName.getIp() + ":" + sPort + " -e /usr/bin/ssh " + seleccion + "@" + registerName.getIp() + " -p " + sPort);
/*      */         } 
/*      */       } else {
/* 1416 */         int portLocal; if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1417 */           portLocal = this.sarlab.getPortLocal(deviceName);
/*      */         } else {
/* 1419 */           portLocal = freePort();
/* 1420 */           this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, portLocal);
/*      */         } 
/* 1422 */         if (OS.isWindows()) {
/* 1423 */           exec.ssh((EJSClientSarlab2)this.sarlab, registerName.getIp(), port);
/*      */         } else {
/* 1425 */           String seleccion = JOptionPane.showInputDialog("User");
/* 1426 */           if (seleccion == null) {
/*      */             return;
/*      */           }
/*      */           
/* 1430 */           exec.exec("/usr/bin/xterm -fa 'Monospace' -fs 14 -T ssh_" + registerName.getIp() + ":" + sPort + " -e /usr/bin/ssh " + seleccion + "@127.0.0.1 -p " + portLocal);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButtonTelnetActionPerformed(ActionEvent evt) {
/*      */     RegisterName registerName;
/* 1439 */     int port, ind = this.jList1.getSelectedIndex();
/*      */ 
/*      */     
/* 1442 */     if (this.sarlab == null) {
/*      */       
/* 1444 */       registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1446 */       registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1448 */     String sPort = this.jTextFieldTelnet.getText();
/*      */     try {
/* 1450 */       port = Integer.parseInt(sPort);
/* 1451 */     } catch (NumberFormatException ex) {
/* 1452 */       this.jTextFieldTelnet.setText("23");
/* 1453 */       port = 23;
/*      */     } 
/* 1455 */     if (registerName != null) {
/* 1456 */       this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1457 */       ExecApp exec = new ExecApp();
/* 1458 */       String deviceName = registerName.getIp() + ":" + sPort;
/*      */       
/* 1460 */       if (this.sarlab == null) {
/* 1461 */         if (OS.isWindows()) {
/* 1462 */           exec.telnet(registerName.getIp(), port);
/*      */         } else {
/* 1464 */           exec.exec("/usr/bin/xterm -fa 'Monospace' -fs 14 -T telnet_" + registerName.getIp() + ":" + sPort + " -e /usr/bin/telnet " + registerName.getIp() + " " + sPort);
/*      */         } 
/*      */       } else {
/*      */         int portLocal;
/* 1468 */         if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1469 */           portLocal = this.sarlab.getPortLocal(deviceName);
/*      */         } else {
/* 1471 */           portLocal = freePort();
/* 1472 */           this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, portLocal);
/*      */         } 
/* 1474 */         if (OS.isWindows()) {
/* 1475 */           exec.telnet((EJSClientSarlab2)this.sarlab, registerName.getIp(), port);
/*      */         } else {
/* 1477 */           exec.exec("/usr/bin/xterm -fa 'Monospace' -fs 14 -T telnet_" + registerName.getIp() + ":" + sPort + " -e /usr/bin/telnet  127.0.0.1 " + portLocal);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButtonSFDTPActionPerformed(ActionEvent evt) {
/*      */     RegisterName registerName;
/* 1486 */     int port, ind = this.jList1.getSelectedIndex();
/*      */ 
/*      */     
/* 1489 */     if (this.sarlab == null) {
/*      */       
/* 1491 */       registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1493 */       registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1495 */     String sPort = this.jTextFieldsFTP.getText();
/*      */     try {
/* 1497 */       port = Integer.parseInt(sPort);
/* 1498 */     } catch (NumberFormatException ex) {
/* 1499 */       this.jTextFieldDesktop.setText("22");
/* 1500 */       port = 22;
/*      */     } 
/* 1502 */     if (registerName != null) {
/* 1503 */       this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1504 */       ExecApp exec = new ExecApp();
/* 1505 */       String deviceName = registerName.getIp() + ":" + sPort;
/*      */       
/* 1507 */       if (this.sarlab == null) {
/* 1508 */         if (OS.isWindows()) {
/* 1509 */           exec.sftp(registerName.getIp(), port);
/*      */         } else {
/* 1511 */           this.jDialog1.setLocationRelativeTo((Component)null);
/* 1512 */           this.jDialog1.setVisible(true);
/* 1513 */           exec.exec("/usr/bin/filezilla sftp://" + this.userLog + ":" + this.passwdLog + "@" + registerName.getIp() + ":" + sPort);
/*      */         } 
/*      */       } else {
/* 1516 */         int portLocal; if (this.sarlab.isConnectionLocalhost(deviceName)) {
/* 1517 */           portLocal = this.sarlab.getPortLocal(deviceName);
/*      */         } else {
/* 1519 */           portLocal = freePort();
/* 1520 */           this.sarlab.addconnectionLocalhostL(deviceName, registerName.getIp(), port, portLocal);
/*      */         } 
/* 1522 */         if (OS.isWindows()) {
/* 1523 */           exec.sftp((EJSClientSarlab2)this.sarlab, registerName.getIp(), port);
/*      */         } else {
/* 1525 */           this.jDialog1.setLocationRelativeTo((Component)null);
/* 1526 */           this.jDialog1.setVisible(true);
/* 1527 */           exec.exec("/usr/bin/filezilla sftp://" + this.userLog + ":" + this.passwdLog + "@127.0.0.1:" + portLocal);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButtonModbusActionPerformed(ActionEvent evt) {
/*      */     RegisterName registerName;
/* 1536 */     int port, ind = this.jList1.getSelectedIndex();
/*      */ 
/*      */     
/* 1539 */     if (this.sarlab == null) {
/*      */       
/* 1541 */       registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1543 */       registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1545 */     String sPort = this.jTextFieldModbus.getText();
/*      */     try {
/* 1547 */       port = Integer.parseInt(sPort);
/* 1548 */     } catch (NumberFormatException ex) {
/* 1549 */       this.jTextFieldModbus.setText("502");
/* 1550 */       port = 502;
/*      */     } 
/* 1552 */     if (registerName != null) {
/* 1553 */       this.log.info("Ind = " + ind + " ip= " + registerName.getIp());
/* 1554 */       if (this.sarlab == null) {
/* 1555 */         MasterModbus modBusMaster = new MasterModbus(registerName.getIp(), this.jTextFieldModbus.getText(), "1");
/* 1556 */         modBusMaster.connect();
/* 1557 */         modBusMaster.monitorInit();
/*      */       } else {
/* 1559 */         int portLocal = freePort();
/* 1560 */         this.sarlab.addconnectionLocalhostL("Monitor Modbus", registerName.getIp(), port, portLocal);
/* 1561 */         MasterModbus modBusMaster = new MasterModbus("127.0.0.1", Integer.toString(portLocal), "1");
/* 1562 */         modBusMaster.connect();
/* 1563 */         modBusMaster.monitorInit();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButtonNavegadorActionPerformed(ActionEvent evt) {
/* 1570 */     String urlNav = generaURL();
/* 1571 */     ExecApp exec = new ExecApp();
/* 1572 */     if (OS.isWindows()) {
/* 1573 */       exec.browser(urlNav);
/*      */     } else {
/* 1575 */       exec.exec("/usr/bin/google-chrome --no-sandbox --user-data-dir " + urlNav);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void jTextFieldURLActionPerformed(ActionEvent evt) {
/* 1581 */     String urlNav = generaURL();
/* 1582 */     ExecApp exec = new ExecApp();
/* 1583 */     if (OS.isWindows()) {
/* 1584 */       exec.browser(urlNav);
/*      */     } else {
/* 1586 */       exec.exec("/usr/bin/google-chrome --no-sandbox --user-data-dir " + urlNav);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButtonRemotoActionPerformed(ActionEvent evt) {
/* 1593 */     removeService();
/* 1594 */     //this.jLabel6.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/wan64x64.png")));
/* 1595 */     DefaultListModel<String> defaultListModel = new DefaultListModel();
/* 1596 */     this.jList1.setModel(defaultListModel);
/* 1597 */     if (OS.isWindows()) {
/* 1598 */       this.jButtonTools.setDisable(false);
/*      */     }
/* 1600 */     this.jButtonForwarding.setDisable(false);
/* 1601 */     this.jButtonForwardingLook.setDisable(false);
/* 1602 */     this.jButtonProfile.setDisable(false);
/* 1603 */     //this.listGateway.setVisible(true);
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButtonLocalActionPerformed(ActionEvent evt) {
/* 1608 */     //this.editor.setVisible(true);
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButtonConfiguracionLocalActionPerformed(ActionEvent evt) {
/* 1613 */     removeService();
/* 1614 */     this.jLabelNetConnect.setText("LAN");
/* 1615 */     this.jButtonTools.setDisable(true);
/* 1616 */     this.jButtonForwarding.setDisable(true);
/* 1617 */     this.jButtonForwardingLook.setDisable(true);
/* 1618 */     this.jButtonProfile.setDisable(true);
/* 1619 */     //this.jLabel6.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/LAN64x64.png")));
/* 1620 */     DefaultListModel<String> defaultListModel = new DefaultListModel();
/* 1621 */     this.jList1.setModel(defaultListModel);
/* 1622 */     if (this.sarlab != null) {
/* 1623 */       this.sarlab.disconnect();
/* 1624 */       this.sarlab.delay(500);
/*      */     } 
/* 1626 */     this.sarlab = null;
/* 1627 */     this.barLogo.init();
/* 1628 */     ButtonEnabled(false);
/* 1629 */     this.jButtonActualizar.setDisable(true);
/*      */   }
/*      */   
/*      */   private void jRadioAxiActionPerformed(ActionEvent evt) {
/*      */     RegisterName registerName;
/* 1634 */     this.jTextFieldURL.setDisable(true);
/*      */ 
/*      */     
/* 1637 */     int ind = this.jList1.getSelectedIndex();
/* 1638 */     if (this.sarlab == null) {
/* 1639 */       registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1641 */       registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1643 */     String sPort = this.jTextFieldPortNav.getText();
/* 1644 */     if (!"80".equals(sPort)) {
/* 1645 */       this.url = "http://" + registerName.getIp() + ":" + this.jTextFieldPortNav.getText();
/*      */     } else {
/* 1647 */       this.url = "http://" + registerName.getIp();
/*      */     } 
/* 1649 */     this.jTextFieldURL.setText(this.url + "/axis-cgi/mjpg/video.cgi");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jRadioEnlargeActionPerformed(ActionEvent evt) {
/*      */     RegisterName registerName;
/* 1658 */     this.jTextFieldURL.setDisable(true);
/*      */ 
/*      */     
/* 1661 */     int ind = this.jList1.getSelectedIndex();
/* 1662 */     if (this.sarlab == null) {
/* 1663 */       registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1665 */       registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1667 */     String sPort = this.jTextFieldPortNav.getText();
/* 1668 */     if (!"80".equals(sPort)) {
/* 1669 */       this.url = "http://" + registerName.getIp() + ":" + this.jTextFieldPortNav.getText();
/*      */     } else {
/* 1671 */       this.url = "http://" + registerName.getIp();
/*      */     } 
/* 1673 */     this.jTextFieldURL.setText(this.url + "/stream/video.mjpeg");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jRadioFreeActionPerformed(ActionEvent evt) {
/*      */     RegisterName registerName;
/* 1684 */     int ind = this.jList1.getSelectedIndex();
/* 1685 */     if (this.sarlab == null) {
/* 1686 */       registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/*      */     } else {
/* 1688 */       registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
/*      */     } 
/* 1690 */     String sPort = this.jTextFieldPortNav.getText();
/* 1691 */     if (!"80".equals(sPort)) {
/* 1692 */       this.url = "http://" + registerName.getIp() + ":" + this.jTextFieldPortNav.getText();
/*      */     } else {
/* 1694 */       this.url = "http://" + registerName.getIp();
/*      */     } 
/* 1696 */     this.jTextFieldURL.setText(this.url);
/* 1697 */     this.jTextFieldURL.setDisable(false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void jButtonToolsActionPerformed(ActionEvent evt) {
/* 1702 */     if (this.sarlab != null) {
/* 1703 */       ExecApp exec = new ExecApp();
/* 1704 */       if (OS.isWindows()) {
/* 1705 */         exec.utility((EJSClientSarlab2)this.sarlab);
/*      */       } else {
/* 1707 */         JOptionPane.showMessageDialog(null, "Option not supported on linux");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButtonForwardingLookActionPerformed(ActionEvent evt) {
/* 1715 */     //this.panelConectionLocalhost.setVisible(true);
/* 1716 */     DefaultListModel modelo = new DefaultListModel();
/* 1717 */     if (this.sarlab != null) {
/* 1718 */       modelo = this.sarlab.getDefaultListModel();
/*      */     }
/*      */     
/* 1721 */     //this.panelConectionLocalhost.init(modelo);
/* 1722 */     //this.panelConectionLocalhost.set();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButtonForwardingActionPerformed(ActionEvent evt) {
/* 1728 */     //this.panelNodoFrowardingL.setVisible(true);
/* 1729 */     //this.panelNodoFrowardingL.setIpInit();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void jButtonProfileActionPerformed(ActionEvent evt) {
/* 1738 */     String url = "";
/* 1739 */     if (this.sarlab != null) {
/* 1740 */       HashMapExperienceWeb hashMapExperienceWeb = new HashMapExperienceWeb();
/*      */       
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
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
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
/* 1783 */     this.jDialog1.dispose();
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
/* 1795 */       this.jDialog1.dispose();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void lineausuarioActionPerformed(ActionEvent evt) {}
/*      */ 
/*      */   
/*      */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 1805 */     //this.consoleText.setVisible(true);
/* 1806 */     //this.consoleText.startTimer();
/*      */   }
/*      */ 
/*      */   
/*      */   public void initURL() {
/* 1811 */     String sPort, urlLocal = "";
/*      */ 
/*      */     
/* 1814 */     int ind = this.jList1.getSelectedIndex();
/* 1815 */     if (this.sarlab == null) {
/* 1816 */       RegisterName registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/* 1817 */       sPort = Integer.toString(registerName.getHttpPort());
/* 1818 */       if (!"80".equals(sPort)) {
/* 1819 */         urlLocal = "http://" + registerName.getIp() + ":" + sPort;
/*      */       } else {
/* 1821 */         urlLocal = "http://" + registerName.getIp();
/*      */       } 
/*      */     } else {
/* 1824 */       RegisterName registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
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
/* 1850 */     int ind = this.jList1.getSelectedIndex();
/* 1851 */     if (this.sarlab == null) {
/* 1852 */       RegisterName registerName = this.serverUDPThread.getRegisterNametoArray(ind);
/* 1853 */       sPort = this.jTextFieldPortNav.getText();
/* 1854 */       if (!"80".equals(sPort)) {
/* 1855 */         urlLocal = "http://" + registerName.getIp() + ":" + sPort;
/*      */       } else {
/* 1857 */         urlLocal = "http://" + registerName.getIp();
/*      */       } 
/*      */     } else {
/* 1860 */       RegisterName registerName = this.directoryRegisterNameSarlab.getRegisterNametoArray(ind);
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
/* 1886 */     int ind = this.jList1.getSelectedIndex();
/* 1887 */     if (this.sarlab == null) {
/* 1888 */       RegisterName registerName = this.serverUDPThread.getRegisterNametoArray(ind);
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
/*      */ 
/*      */   
/*      */   public String giveMeFileNameConfig() {
/* 1947 */     if (OS.isWindows()) {
/* 1948 */       return "C:/sarlab/config/" + this.fileNameBase;
/*      */     }
/* 1950 */     return "/sarlab/config/" + this.fileNameBase;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initBarLogo() {
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
/* 1969 */         String name = JOptionPane.showInputDialog(null, "Indique el nombre ", "Identificación del equipo", 2);
/*      */         
/* 1971 */         if (name.equals("")) {
/* 1972 */           name = hostname;
/*      */         }
/* 1974 */         p.setProperty("nameSarlab", name);
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
/*      */   
/*      */   public void endBarLogo() {
/* 1987 */     this.serverUDPThread.end();
/* 1988 */     DirectoryRegisterName directoryRegisterName = this.serverUDPThread.getDirectoryRegisterName();
/* 1989 */     setListModel(this.serverUDPThread.getListModel());
/* 1990 */     this.jButtonActualizar.setDisable(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   /*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void connectGIOT(final RegisterGateway registerGateway) {
/* 1997 */     initProgressBar(50);
/*      */ 
/*      */     /*      */ 
/*      */     
/* 2000 */     SwingUtilities.invokeLater(new Runnable()
/*      */         {
/*      */           public void run() {
/* 2003 */             if (PanelClientServerName.this.sarlab != null) {
/* 2004 */               PanelClientServerName.this.sarlab.disconnect();
/*      */             }
/*      */             /*      */             
/* 2007 */             PanelClientServerName.this.sarlab = new GIOTClientSarlab3();
/*      */             
/* 2009 */             System.out.println("IpServerTunnel " + registerGateway.getIpServerSarlab());
/* 2010 */             if (!registerGateway.getPortInputGIOTTunnel().equals("")) {
/* 2011 */               String uri = registerGateway.getIdGIOT().replace("GIoT_", "").toLowerCase();
/* 2012 */               PanelClientServerName.this.sarlab.connectMyGateway(registerGateway.getIpServerSarlab(), registerGateway.getPortServerSarlab(), registerGateway.getIdGIOT(), registerGateway.getKeyGIOT(), registerGateway.getIpPublicherMyGateway(), registerGateway.getPortInputGIOTTunnel(), registerGateway.getPortInputGIOTWeb(), registerGateway.getUserGIOT(), registerGateway.getPassGIOT(), registerGateway.getIdExp(), registerGateway.getTimeOUT());
/*      */             }
/*      */             else {
/*      */               
/* 2016 */               PanelClientServerName.this.sarlab.connect(registerGateway.getIpServerSarlab(), registerGateway.getPortServerSarlab(), registerGateway.getUserGIOT(), registerGateway.getPassGIOT(), registerGateway.getIdExp(), registerGateway.getTimeOUT());
/*      */             } 
/* 2018 */             System.out.println(PanelClientServerName.this.url);
/* 2019 */             GetParameters getParameters = new GetParameters();
/* 2020 */             PanelClientServerName.this.directoryRegisterNameSarlab = getParameters.getDirectoryRegisterNameSarlab(registerGateway);
/* 2021 */             if (!registerGateway.getPortInputGIOTTunnel().equals("")) {
/* 2022 */               RegisterName regMyGateway = new RegisterName("10.254.254.254", "myGateway_10", "myGateway10");
/* 2023 */               regMyGateway.setVnc(true);
/* 2024 */               regMyGateway.setVncPort(5901);
/* 2025 */               regMyGateway.setSsh(true);
/* 2026 */               regMyGateway.setSshPort(22);
/* 2027 */               regMyGateway.setSftp(true);
/* 2028 */               regMyGateway.setSftpPort(22);
/* 2029 */               PanelClientServerName.this.directoryRegisterNameSarlab.add(regMyGateway);
/*      */             } 
/* 2031 */             PanelClientServerName.this.registerGatewayOld = registerGateway;
/*      */ 
/*      */             /*      */ 
/*      */             
/* 2034 */             if (PanelClientServerName.this.directoryRegisterNameSarlab != null) {
/* 2035 */               DefaultListModel<String> defaultListModel = PanelClientServerName.this.directoryRegisterNameSarlab.getListModel();
/* 2036 */               PanelClientServerName.this.jList1.setModel(defaultListModel);
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   public String getIdGIoT(String IdGIOT) {
/* 2043 */     return IdGIOT.substring(5).toLowerCase();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/*      */     try {
/* 2058 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 2059 */         if ("Nimbus".equals(info.getName())) {
/* 2060 */           UIManager.setLookAndFeel(info.getClassName());
/*      */           break;
/*      */         } 
/*      */       } 
/* 2064 */     } catch (ClassNotFoundException ex) {
/* 2065 */       Logger.getLogger(PanelClientServerNameController.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 2066 */     } catch (InstantiationException ex) {
/* 2067 */       Logger.getLogger(PanelClientServerNameController.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 2068 */     } catch (IllegalAccessException ex) {
/* 2069 */       Logger.getLogger(PanelClientServerNameController.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 2070 */     } catch (UnsupportedLookAndFeelException ex) {
/* 2071 */       Logger.getLogger(PanelClientServerNameController.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     /*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2086 */     SwingUtilities.invokeLater(new Runnable() {
/*      */           public void run() {
/* 2088 */             //PanelClientServerName clientName = new PanelClientServerName();
/* 2089 */             //clientName.setVisible(true);
/* 2090 */             //clientName.init();
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void propertyChange(PropertyChangeEvent evt) {
/* 2157 */     if ("progress" == evt.getPropertyName()) {
/* 2158 */       //this.progressBar.setIndeterminate(false);
/* 2159 */       int progress = ((Integer)evt.getNewValue()).intValue();
/* 2160 */       //this.progressBar.setVar(progress);
/* 2161 */       if (progress == 100) {
/* 2162 */         //this.progressBar.setVisible(false);
/*      */       }
/*      */       
/* 2165 */       if (this.sarlab != null) {
/* 2166 */         if (this.sarlab.getStatusSarlab().contains("Error")) {
/* 2167 */           this.sentinel.stop();
/* 2168 */           //this.progressBar.setVisible(false);
/*      */         } 
/* 2170 */         if (this.sarlab.getStatusSarlab().contains("failure")) {
/* 2171 */           this.sentinel.stop();
/* 2172 */           //this.progressBar.setVisible(false);
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
/*      */   
/*      */   public void initProgressBar(int t) {
/* 2185 */     //this.progressBar.setVisible(true);
/* 2186 */     //this.progressBar.setIndeterminate(true);
/* 2187 */     //this.progressBar.setVar(5);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 class Task
/*      */     extends SwingWorker<Void, Void> {
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Void doInBackground() throws Exception {
/* 2264 */       retardation(this.t);
/* 2265 */       return null;
/*      */     }
/*      */

        private void addPropertyChangeListener(PanelClientServerName aThis) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
   }
/*      */ 
/*      */ 
/*      */   /*      */ 
/*      */ 
/*      */   
/*      */   class Sentinel
/*      */     implements ActionListener
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
/*      */ 
/*      */ 
/*      */ 
/*      */     /*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 2289 */       if (PanelClientServerName.this.sarlab != null) {
/* 2290 */         if (PanelClientServerName.this.sarlab.clientSarlab != null) {
/* 2291 */           PanelClientServerName.this.sarlab.clientSarlab.sendCommandServer("ping");
/*      */         }
/* 2293 */         if (PanelClientServerName.this.sarlab.clienteGIOT != null)
/* 2294 */           PanelClientServerName.this.sarlab.clienteGIOT.sendCommandServer("ping"); 
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelClientServerName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */