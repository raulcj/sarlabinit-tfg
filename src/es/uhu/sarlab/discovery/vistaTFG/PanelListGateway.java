/*     */ package es.uhu.sarlab.discovery.vistaTFG;
/*     */ import es.nebsyst.encrypt.CryptographyAESSarlab2;
/*     */ import es.uhu.sarlab.digexlab.giot.model.ParameterAccess;
/*     */ import es.uhu.sarlab.digexlab.giot.model.ParameterTunnel;
/*     */ import es.uhu.sarlab.digexlab.giot.model.ParameterVirtualFrontier;
/*     */ import es.uhu.sarlab.digexlab.giot.model.PublicPort;
/*     */ import es.uhu.sarlab.discovery.GetParameters.GetParameters;
/*     */ import es.uhu.sarlab.discovery.model.DirectoryGatewayRemote;
/*     */ import es.uhu.sarlab.discovery.model.RegisterGateway;
import es.uhu.sarlab.utils.CommunicationsUtilities;
import es.uhu.sarlab.utils.OS;
/*     */ import es.uhu.sarlab.utils.PropertiesFiles;
/*     */ import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
/*     */ import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
import javax.swing.AbstractListModel;
/*     */ import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LayoutStyle;
import javax.swing.ListModel;
/*     */ import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/*     */ import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/*     */ 
/*     */ public class PanelListGateway extends JFrame {
/*  32 */   String fileNameBaseXML = "gateway.xml";
/*     */   DirectoryGatewayRemote directoryGatewayRemote;
/*     */   ListModel listModel;
/*  35 */   RegisterGateway registerGateway = new RegisterGateway();
/*     */   
/*     */   boolean select = false;
/*     */   PanelClientServerName clientName;
/*  39 */   PropertiesFiles propertiesFiles = new PropertiesFiles(PropertiesFiles.CONFIG_FILE); String initVirtualFrontier; String initPortVirtualFrontier; String initPathVirtualFrontier; private JLabel icoHD; private JButton jButtonDel; public JButton jButtonNew; public JButton jButtonSave; public JButton jButtonSelect; public JCheckBox jCheckBoxGIoT; private JLabel jLabel1; private JLabel jLabelIdConnect; private JLabel jLabelIdConnectT; private JLabel jLabelIdExp;
/*     */   private JLabel jLabelIdGIOT;
/*     */   private JLabel jLabelIpServerSarlab;
/*     */   private JLabel jLabelKeyGIOT;
/*     */   private JLabel jLabelPass;
/*     */   private JLabel jLabelPortInputGIOTTunnel;
/*     */   private JLabel jLabelPortInputGIOTWeb;
/*     */   /*     */   /*     */   /*     */   
/*     */   public PanelListGateway(PanelClientServerName clientName) {
/*  48 */     testParameter();
/*  49 */     this.clientName = clientName;
/*  50 */     initComponents();
/*  51 */     setLocationRelativeTo(null);
/*  52 */     this.icoHD.setVisible(true);
/*  53 */     this.jLabel1.setVisible(true);
/*  54 */     this.jLabel1.setEnabled(true);
/*  55 */     initJText();
/*  56 */     this.directoryGatewayRemote = new DirectoryGatewayRemote();
/*  57 */     this.directoryGatewayRemote = (DirectoryGatewayRemote)this.directoryGatewayRemote.generateTfromFileXML(giveMeFileNameConfigXML(), DirectoryGatewayRemote.class);
/*  58 */     if (this.directoryGatewayRemote == null) {
/*  59 */       this.directoryGatewayRemote = new DirectoryGatewayRemote();
/*     */     }
/*  61 */     this.listModel = this.directoryGatewayRemote.getDefaultListModel();
/*  62 */     this.jList1.setModel(this.listModel);
/*  63 */     this.jTextKeyGIOT.setVisible(false);
/*  64 */     this.jLabelKeyGIOT.setVisible(false);
/*  65 */     this.jButtonSelect.setEnabled(false);
/*  66 */     this.jButtonDel.setEnabled(false);
/*  67 */     this.jCheckBoxGIoT.setEnabled(false);
/*  68 */     this.jTextPortInputGIOTTunnel.setEditable(false);
/*  69 */     this.jTextPortInputGIOTWeb.setEditable(false);
/*  70 */     ButtonGIOT(true);
/*     */   }
/*     */   private JLabel jLabelPortServerSarlab; private JLabel jLabelTimeOut; private JLabel jLabelUser; public JList<String> jList1; private JPanel jPanel1; private JPanel jPanel2; private JPanel jPanel3; private JPanel jPanel5; public JPasswordField jPasswordPassGIOT; private JScrollPane jScrollPane1; public JTextField jTextIdExp; public JTextField jTextIdGIOT; public JTextField jTextIpServerSarlab; public JTextField jTextKeyGIOT; public JTextField jTextPortInputGIOTTunnel; public JTextField jTextPortInputGIOTWeb; public JTextField jTextPortServerSarlab1; public JTextField jTextTimeOUT; public JTextField jTextUserGIOT; public JTextField jTextlIdConnect;
/*     */   public void testParameter() {
/*  74 */     boolean change = false;
/*  75 */     this.initVirtualFrontier = this.propertiesFiles.getPropertiesValue("initVirtualFrontier");
/*  76 */     if (this.initVirtualFrontier == null) {
/*  77 */       this.propertiesFiles.setPropertiesValue("initVirtualFrontier", "edutechfrontier.nebsyst.com");
/*  78 */       change = true;
/*     */     } 
/*  80 */     this.initPortVirtualFrontier = this.propertiesFiles.getPropertiesValue("initPortVirtualFrontier");
/*  81 */     if (this.initPortVirtualFrontier == null) {
/*  82 */       this.propertiesFiles.setPropertiesValue("initPortVirtualFrontier", "443");
/*  83 */       change = true;
/*     */     } 
/*  85 */     this.initPathVirtualFrontier = this.propertiesFiles.getPropertiesValue("initPathVirtualFrontier");
/*  86 */     if (this.initPathVirtualFrontier == null) {
/*  87 */       this.propertiesFiles.setPropertiesValue("initPathVirtualFrontier", "SARLABV8.0");
/*  88 */       change = true;
/*     */     } 
/*  90 */     if (change) {
/*  91 */       this.propertiesFiles.saveProperties();
/*  92 */       this.initPathVirtualFrontier = this.propertiesFiles.getPropertiesValue("initPathVirtualFrontier");
/*  93 */       this.initPortVirtualFrontier = this.propertiesFiles.getPropertiesValue("initPortVirtualFrontier");
/*  94 */       this.initVirtualFrontier = this.propertiesFiles.getPropertiesValue("initVirtualFrontier");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getIconImage() {
/* 101 */     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("es/uhu/sarlab/discovery/vista/resources/Remote-Desktop32x32.png"));
/* 102 */     return retValue;
/*     */   }
/*     */   
/*     */   public void ButtonGIOT(boolean value) {
/* 106 */     this.jTextPortInputGIOTTunnel.setVisible(value);
/* 107 */     this.jLabelPortInputGIOTTunnel.setVisible(value);
/* 108 */     this.jTextPortInputGIOTWeb.setVisible(value);
/* 109 */     this.jLabelPortInputGIOTWeb.setVisible(value);
/* 110 */     this.jLabelIdExp.setVisible(!value);
/* 111 */     this.jTextIdExp.setVisible(!value);
/* 112 */     if (value) {
/* 113 */       setMyGateway();
/*     */     } else {
/* 115 */       setFrontier();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMyGateway() {
/* 121 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
/* 122 */     this.jLabelIdGIOT.setText(bundle.getString("PanelListGateway.jLabelIdGIOT.text2"));
/* 123 */     this.jLabelIpServerSarlab.setText(bundle.getString("myVirtualFrontierIP"));
/* 124 */     this.jLabelPortServerSarlab.setText(bundle.getString("myVirtualFrontierPort"));
/* 125 */     this.jLabelUser.setText(bundle.getString("myGatewayUser"));
/* 126 */     this.jLabelPass.setText(bundle.getString("myGatewayPassword"));
/*     */   }
/*     */   
/*     */   public void setFrontier() {
/* 130 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
/* 131 */     this.jLabelIdGIOT.setText(bundle.getString("PanelListGateway.jLabelIdGIOT.text"));
/* 132 */     this.jLabelIpServerSarlab.setText(bundle.getString("PanelListGateway.jLabelIpServerSarlab.text"));
/* 133 */     this.jLabelPortServerSarlab.setText(bundle.getString("PanelListGateway.jLabelPortServerSarlab.text"));
/* 134 */     this.jLabelUser.setText(bundle.getString("PanelListGateway.jLabelUser.text"));
/* 135 */     this.jLabelPass.setText(bundle.getString("PanelListGateway.jLabelPass.text"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/* 145 */     this.jPanel3 = new JPanel();
/* 146 */     this.icoHD = new JLabel();
/* 147 */     this.jButtonSave = new JButton();
/* 148 */     this.jButtonNew = new JButton();
/* 149 */     this.jButtonDel = new JButton();
/* 150 */     this.jButtonSelect = new JButton();
/* 151 */     this.jCheckBoxGIoT = new JCheckBox();
/* 152 */     this.jLabel1 = new JLabel();
/* 153 */     this.jPanel2 = new JPanel();
/* 154 */     this.jLabelIdConnect = new JLabel();
/* 155 */     this.jTextlIdConnect = new JTextField();
/* 156 */     this.jLabelIpServerSarlab = new JLabel();
/* 157 */     this.jTextIpServerSarlab = new JTextField();
/* 158 */     this.jLabelPortServerSarlab = new JLabel();
/* 159 */     this.jTextPortServerSarlab1 = new JTextField();
/* 160 */     this.jLabelIdGIOT = new JLabel();
/* 161 */     this.jTextIdGIOT = new JTextField();
/* 162 */     this.jLabelUser = new JLabel();
/* 163 */     this.jTextUserGIOT = new JTextField();
/* 164 */     this.jLabelPass = new JLabel();
/* 165 */     this.jPasswordPassGIOT = new JPasswordField();
/* 166 */     this.jLabelTimeOut = new JLabel();
/* 167 */     this.jTextTimeOUT = new JTextField();
/* 168 */     this.jLabelIdExp = new JLabel();
/* 169 */     this.jTextIdExp = new JTextField();
/* 170 */     this.jLabelKeyGIOT = new JLabel();
/* 171 */     this.jTextKeyGIOT = new JPasswordField();
/* 172 */     this.jLabelPortInputGIOTTunnel = new JLabel();
/* 173 */     this.jTextPortInputGIOTTunnel = new JTextField();
/* 174 */     this.jLabelPortInputGIOTWeb = new JLabel();
/* 175 */     this.jTextPortInputGIOTWeb = new JTextField();
/* 176 */     this.jPanel1 = new JPanel();
/* 177 */     this.jLabelIdConnectT = new JLabel();
/* 178 */     this.jPanel5 = new JPanel();
/* 179 */     this.jScrollPane1 = new JScrollPane();
/* 180 */     this.jList1 = new JList<>();
/*     */     
/* 182 */     setBackground(new Color(102, 153, 255));
/* 183 */     setIconImage(getIconImage());
/* 184 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowClosing(WindowEvent evt) {
/* 186 */             PanelListGateway.this.formWindowClosing(evt);
/*     */           }
/*     */         });
/*     */     
/* 190 */     this.jPanel3.setBackground(new Color(102, 153, 255));
/* 191 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder(""));
/* 192 */     this.jPanel3.setMaximumSize(new Dimension(944, 60));
/* 193 */     this.jPanel3.setMinimumSize(new Dimension(944, 60));
/*     */     
/* 195 */     this.icoHD.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/HD.gif")));
/* 196 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
/* 197 */     this.icoHD.setText(bundle.getString("PanelListGateway.icoHD.text"));
/*     */     
/* 199 */     this.jButtonSave.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/salvar.png")));
/* 200 */     this.jButtonSave.setText(bundle.getString("PanelListGateway.jButtonSave.text"));
/* 201 */     this.jButtonSave.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 203 */             PanelListGateway.this.jButtonSaveActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 207 */     this.jButtonNew.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/plus24x24.png")));
/* 208 */     this.jButtonNew.setText(bundle.getString("PanelListGateway.jButtonNew.text"));
/* 209 */     this.jButtonNew.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 211 */             PanelListGateway.this.jButtonNewActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 215 */     this.jButtonDel.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/borrar.png")));
/* 216 */     this.jButtonDel.setText(bundle.getString("PanelListGateway.jButtonDel.text"));
/* 217 */     this.jButtonDel.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 219 */             PanelListGateway.this.jButtonDelActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 223 */     this.jButtonSelect.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/select.png")));
/* 224 */     this.jButtonSelect.setText(bundle.getString("PanelListGateway.jButtonSelect.text"));
/* 225 */     this.jButtonSelect.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 227 */             PanelListGateway.this.jButtonSelectActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 231 */     this.jCheckBoxGIoT.setText(bundle.getString("PanelListGateway.jCheckBoxGIoT.text"));
/* 232 */     this.jCheckBoxGIoT.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 234 */             PanelListGateway.this.jCheckBoxGIoTActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 238 */     this.jLabel1.setHorizontalAlignment(2);
/* 239 */     this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/fileInternet.gif")));
/* 240 */     this.jLabel1.setText(bundle.getString("PanelListGateway.jLabel1.text"));
/*     */     
/* 242 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 243 */     this.jPanel3.setLayout(jPanel3Layout);
/* 244 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/* 245 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 246 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 247 */           .addContainerGap()
/* 248 */           .addComponent(this.jButtonSave)
/* 249 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 250 */           .addComponent(this.jButtonNew)
/* 251 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 252 */           .addComponent(this.jButtonDel)
/* 253 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 254 */           .addComponent(this.jButtonSelect)
/* 255 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 256 */           .addComponent(this.jCheckBoxGIoT)
/* 257 */           .addGap(78, 78, 78)
/* 258 */           .addComponent(this.jLabel1)
/* 259 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 260 */           .addComponent(this.icoHD)
/* 261 */           .addGap(19, 19, 19)));
/*     */     
/* 263 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 264 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 265 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 266 */           .addContainerGap()
/* 267 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 268 */             .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 269 */               .addComponent(this.jLabel1, GroupLayout.Alignment.TRAILING)
/* 270 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 271 */                 .addComponent(this.jButtonNew)
/* 272 */                 .addComponent(this.jButtonDel)
/* 273 */                 .addComponent(this.jButtonSelect)
/* 274 */                 .addComponent(this.jCheckBoxGIoT)))
/* 275 */             .addComponent(this.icoHD)
/* 276 */             .addComponent(this.jButtonSave, GroupLayout.Alignment.LEADING))
/* 277 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 280 */     this.jPanel2.setBackground(new Color(102, 153, 255));
/* 281 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder(bundle.getString("PanelListGateway.jPanel2.border.title")));
/* 282 */     this.jPanel2.setLayout(new GridLayout(11, 0));
/*     */     
/* 284 */     this.jLabelIdConnect.setFont(new Font("Tahoma", 1, 11));
/* 285 */     this.jLabelIdConnect.setText(bundle.getString("PanelListGateway.jLabelIdConnect.text"));
/* 286 */     this.jPanel2.add(this.jLabelIdConnect);
/*     */     
/* 288 */     this.jTextlIdConnect.setText(bundle.getString("PanelListGateway.jTextlIdConnect.text"));
/* 289 */     this.jPanel2.add(this.jTextlIdConnect);
/*     */     
/* 291 */     this.jLabelIpServerSarlab.setFont(new Font("Tahoma", 1, 11));
/* 292 */     this.jLabelIpServerSarlab.setText(bundle.getString("PanelListGateway.jLabelIpServerSarlab.text"));
/* 293 */     this.jPanel2.add(this.jLabelIpServerSarlab);
/*     */     
/* 295 */     this.jTextIpServerSarlab.setText(bundle.getString("PanelListGateway.jTextIpServerSarlab.text"));
/* 296 */     this.jPanel2.add(this.jTextIpServerSarlab);
/*     */     
/* 298 */     this.jLabelPortServerSarlab.setFont(new Font("Tahoma", 1, 11));
/* 299 */     this.jLabelPortServerSarlab.setText(bundle.getString("PanelListGateway.jLabelPortServerSarlab.text"));
/* 300 */     this.jPanel2.add(this.jLabelPortServerSarlab);
/*     */     
/* 302 */     this.jTextPortServerSarlab1.setText(bundle.getString("PanelListGateway.jTextPortServerSarlab1.text"));
/* 303 */     this.jPanel2.add(this.jTextPortServerSarlab1);
/*     */     
/* 305 */     this.jLabelIdGIOT.setFont(new Font("Tahoma", 1, 11));
/* 306 */     this.jLabelIdGIOT.setText(bundle.getString("PanelListGateway.jLabelIdGIOT.text"));
/* 307 */     this.jPanel2.add(this.jLabelIdGIOT);
/*     */     
/* 309 */     this.jTextIdGIOT.setText(bundle.getString("PanelListGateway.jTextIdGIOT.text"));
/* 310 */     this.jPanel2.add(this.jTextIdGIOT);
/*     */     
/* 312 */     this.jLabelUser.setFont(new Font("Tahoma", 1, 11));
/* 313 */     this.jLabelUser.setText(bundle.getString("PanelListGateway.jLabelUser.text"));
/* 314 */     this.jPanel2.add(this.jLabelUser);
/*     */     
/* 316 */     this.jTextUserGIOT.setText(bundle.getString("PanelListGateway.jTextUserGIOT.text"));
/* 317 */     this.jPanel2.add(this.jTextUserGIOT);
/*     */     
/* 319 */     this.jLabelPass.setFont(new Font("Tahoma", 1, 11));
/* 320 */     this.jLabelPass.setText(bundle.getString("PanelListGateway.jLabelPass.text"));
/* 321 */     this.jPanel2.add(this.jLabelPass);
/*     */     
/* 323 */     this.jPasswordPassGIOT.setText(bundle.getString("PanelListGateway.jPasswordPassGIOT.text"));
/* 324 */     this.jPanel2.add(this.jPasswordPassGIOT);
/*     */     
/* 326 */     this.jLabelTimeOut.setFont(new Font("Tahoma", 1, 11));
/* 327 */     this.jLabelTimeOut.setText(bundle.getString("PanelListGateway.jLabelTimeOut.text"));
/* 328 */     this.jPanel2.add(this.jLabelTimeOut);
/*     */     
/* 330 */     this.jTextTimeOUT.setText(bundle.getString("PanelListGateway.jTextTimeOUT.text"));
/* 331 */     this.jPanel2.add(this.jTextTimeOUT);
/*     */     
/* 333 */     this.jLabelIdExp.setFont(new Font("Tahoma", 1, 11));
/* 334 */     this.jLabelIdExp.setText(bundle.getString("PanelListGateway.jLabelIdExp.text"));
/* 335 */     this.jPanel2.add(this.jLabelIdExp);
/*     */     
/* 337 */     this.jTextIdExp.setText(bundle.getString("PanelListGateway.jTextIdExp.text"));
/* 338 */     this.jPanel2.add(this.jTextIdExp);
/*     */     
/* 340 */     this.jLabelKeyGIOT.setFont(new Font("Tahoma", 1, 11));
/* 341 */     this.jLabelKeyGIOT.setText(bundle.getString("PanelListGateway.jLabelKeyGIOT.text"));
/* 342 */     this.jPanel2.add(this.jLabelKeyGIOT);
/*     */     
/* 344 */     this.jTextKeyGIOT.setText(bundle.getString("PanelListGateway.jTextKeyGIOT.text"));
/* 345 */     this.jPanel2.add(this.jTextKeyGIOT);
/*     */     
/* 347 */     this.jLabelPortInputGIOTTunnel.setFont(new Font("Tahoma", 1, 11));
/* 348 */     this.jLabelPortInputGIOTTunnel.setText(bundle.getString("PanelListGateway.jLabelPortInputGIOTTunnel.text"));
/* 349 */     this.jPanel2.add(this.jLabelPortInputGIOTTunnel);
/*     */     
/* 351 */     this.jTextPortInputGIOTTunnel.setText(bundle.getString("PanelListGateway.jTextPortInputGIOTTunnel.text"));
/* 352 */     this.jPanel2.add(this.jTextPortInputGIOTTunnel);
/*     */     
/* 354 */     this.jLabelPortInputGIOTWeb.setFont(new Font("Tahoma", 1, 11));
/* 355 */     this.jLabelPortInputGIOTWeb.setText(bundle.getString("PanelListGateway.jLabelPortInputGIOTWeb.text"));
/* 356 */     this.jPanel2.add(this.jLabelPortInputGIOTWeb);
/*     */     
/* 358 */     this.jTextPortInputGIOTWeb.setText(bundle.getString("PanelListGateway.jTextPortInputGIOTWeb.text"));
/* 359 */     this.jPanel2.add(this.jTextPortInputGIOTWeb);
/*     */     
/* 361 */     this.jPanel1.setBackground(new Color(102, 153, 255));
/* 362 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(bundle.getString("PanelListGateway.jPanel1.border.title")));
/*     */     
/* 364 */     this.jLabelIdConnectT.setFont(new Font("Tahoma", 1, 11));
/* 365 */     this.jLabelIdConnectT.setText(bundle.getString("PanelListGateway.jLabelIdConnectT.text"));
/*     */     
/* 367 */     this.jPanel5.setBorder(BorderFactory.createTitledBorder(""));
/*     */     
/* 369 */     this.jList1.setModel(new AbstractListModel<String>() {
/* 370 */           String[] strings = new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
/* 371 */           public int getSize() { return this.strings.length; }
/* 372 */           public String getElementAt(int i) { return this.strings[i]; }
/*     */         });
/* 374 */     this.jList1.addListSelectionListener(new ListSelectionListener() {
/*     */           public void valueChanged(ListSelectionEvent evt) {
/* 376 */             PanelListGateway.this.jList1ValueChanged(evt);
/*     */           }
/*     */         });
/* 379 */     this.jScrollPane1.setViewportView(this.jList1);
/*     */     
/* 381 */     GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
/* 382 */     this.jPanel5.setLayout(jPanel5Layout);
/* 383 */     jPanel5Layout.setHorizontalGroup(jPanel5Layout
/* 384 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 385 */         .addGroup(jPanel5Layout.createSequentialGroup()
/* 386 */           .addContainerGap()
/* 387 */           .addComponent(this.jScrollPane1)
/* 388 */           .addContainerGap()));
/*     */     
/* 390 */     jPanel5Layout.setVerticalGroup(jPanel5Layout
/* 391 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 392 */         .addGroup(jPanel5Layout.createSequentialGroup()
/* 393 */           .addContainerGap()
/* 394 */           .addComponent(this.jScrollPane1)
/* 395 */           .addContainerGap()));
/*     */ 
/*     */     
/* 398 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 399 */     this.jPanel1.setLayout(jPanel1Layout);
/* 400 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 401 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 402 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 403 */           .addComponent(this.jPanel5, -1, -1, 32767)
/* 404 */           .addContainerGap())
/* 405 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 406 */           .addGap(232, 232, 232)
/* 407 */           .addComponent(this.jLabelIdConnectT, -2, 101, -2)
/* 408 */           .addContainerGap(153, 32767)));
/*     */     
/* 410 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 411 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 412 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
/* 413 */           .addComponent(this.jLabelIdConnectT)
/* 414 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 415 */           .addComponent(this.jPanel5, -1, -1, 32767)));
/*     */ 
/*     */     
/* 418 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 419 */     getContentPane().setLayout(layout);
/* 420 */     layout.setHorizontalGroup(layout
/* 421 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 422 */         .addGroup(layout.createSequentialGroup()
/* 423 */           .addContainerGap()
/* 424 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 425 */             .addComponent(this.jPanel3, -1, -1, 32767)
/* 426 */             .addGroup(layout.createSequentialGroup()
/* 427 */               .addComponent(this.jPanel1, -2, -1, -2)
/* 428 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 429 */               .addComponent(this.jPanel2, -2, 498, -2)))
/* 430 */           .addGap(0, 10, 32767)));
/*     */     
/* 432 */     layout.setVerticalGroup(layout
/* 433 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 434 */         .addGroup(layout.createSequentialGroup()
/* 435 */           .addContainerGap()
/* 436 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 437 */             .addComponent(this.jPanel2, -1, -1, 32767)
/* 438 */             .addComponent(this.jPanel1, -1, -1, 32767))
/* 439 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 440 */           .addComponent(this.jPanel3, -2, -1, -2)
/* 441 */           .addContainerGap()));
/*     */ 
/*     */     
/* 444 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   private void jList1ValueChanged(ListSelectionEvent evt) {
/* 449 */     this.jCheckBoxGIoT.setEnabled(false);
/* 450 */     int ind = this.jList1.getAnchorSelectionIndex();
/* 451 */     ind = this.jList1.getSelectedIndex();
/* 452 */     if (ind != -1) {
/* 453 */       String key = (String) this.listModel.getElementAt(ind);
/*     */       
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
/* 473 */       this.jButtonSelect.setEnabled(true);
/* 474 */       this.jButtonDel.setEnabled(true);
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
/*     */   
/*     */   public void initJText() {
/* 488 */     this.jTextlIdConnect.setText("");
/* 489 */     this.jTextIpServerSarlab.setText(this.initVirtualFrontier);
/* 490 */     this.jTextPortServerSarlab1.setText(this.initPortVirtualFrontier);
/* 491 */     this.jTextIdGIOT.setText(this.initPathVirtualFrontier);
/* 492 */     this.jTextKeyGIOT.setText("");
/* 493 */     this.jTextPortInputGIOTTunnel.setText("");
/* 494 */     this.jTextPortInputGIOTWeb.setText("");
/* 495 */     this.jTextUserGIOT.setText("");
/* 496 */     this.jPasswordPassGIOT.setText("");
/* 497 */     this.jTextIdExp.setText("");
/* 498 */     this.jTextTimeOUT.setText("600");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void jButtonSaveActionPerformed(ActionEvent evt) {
/* 504 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
/* 505 */     this.jButtonSelect.setEnabled(false);
/* 506 */     this.jButtonDel.setEnabled(false);
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
/* 519 */     registerGateway.setPassGIOT(new String(this.jPasswordPassGIOT.getPassword()));
/* 520 */     registerGateway.setIdExp(this.jTextIdExp.getText());
/* 521 */     registerGateway.setTimeOUT(this.jTextTimeOUT.getText());
/*     */     
/* 523 */     ParameterVirtualFrontier parameterVirtualFrontier = new ParameterVirtualFrontier();
/* 524 */     if (!parameterVirtualFrontier.load(registerGateway.getVirtualFrontier())) {
/* 525 */       JOptionPane.showMessageDialog(null, bundle.getString("PanelListGateway.messagebox6"));
/* 526 */       this.jTextIpServerSarlab.grabFocus();
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
/* 582 */       this.listModel = this.directoryGatewayRemote.getDefaultListModel();
/* 583 */       this.jList1.setModel(this.listModel);
/*     */     } 
/* 585 */     this.directoryGatewayRemote.toFileXML(giveMeFileNameConfigXML());
/* 586 */     this.jButtonSelect.setEnabled(true);
/* 587 */     this.jButtonDel.setEnabled(true);
/*     */   }
/*     */   
/*     */   private void jButtonNewActionPerformed(ActionEvent evt) {
/* 591 */     this.jButtonSelect.setEnabled(false);
/* 592 */     this.jButtonDel.setEnabled(false);
/* 593 */     this.jCheckBoxGIoT.setSelected(false);
/* 594 */     ButtonGIOT(false);
/* 595 */     this.listModel = this.directoryGatewayRemote.getDefaultListModel();
/* 596 */     this.jList1.setModel(this.listModel);
/* 597 */     initJText();
/* 598 */     this.jCheckBoxGIoT.setEnabled(true);
/* 599 */     this.jTextIdGIOT.setText(this.initPathVirtualFrontier);
/*     */   }
/*     */   
/*     */   private void jButtonDelActionPerformed(ActionEvent evt) {
/* 603 */     this.jButtonSelect.setEnabled(false);
/* 604 */     this.jButtonDel.setEnabled(false);
/* 605 */     int ind = this.jList1.getAnchorSelectionIndex();
/* 606 */     this.jCheckBoxGIoT.setSelected(false);
/* 607 */     this.jCheckBoxGIoT.setEnabled(true);
/* 608 */     String key = (String) this.listModel.getElementAt(ind);
/*     */     
/* 610 */     this.registerGateway = this.directoryGatewayRemote.get(key);
/* 611 */     this.directoryGatewayRemote.remove(this.registerGateway);
/* 612 */     DefaultListModel modelo = (DefaultListModel)this.jList1.getModel();
/* 613 */     modelo.remove(ind);
/* 614 */     this.listModel = this.directoryGatewayRemote.getDefaultListModel();
/* 615 */     this.jList1.setModel(this.listModel);
/* 616 */     this.directoryGatewayRemote.toFileXML(giveMeFileNameConfigXML());
/* 617 */     initJText();
/* 618 */     this.jLabelPortInputGIOTTunnel.setVisible(false);
/* 619 */     this.jTextPortInputGIOTTunnel.setVisible(false);
/* 620 */     this.jLabelPortInputGIOTWeb.setVisible(false);
/* 621 */     this.jTextPortInputGIOTWeb.setVisible(false);
/*     */   }
/*     */   
/*     */   private void jButtonSelectActionPerformed(ActionEvent evt) {
/* 625 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelListGateway");
/* 626 */     int ind = this.jList1.getAnchorSelectionIndex();
/* 627 */     String key = (String) this.listModel.getElementAt(ind);
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
/* 667 */     this.clientName.titleConnect(this.registerGateway.getIdConnect());
/* 668 */     setVisible(false);
/* 669 */     if (this.registerGateway.getIdExp().equals("")) {
/* 670 */       this.registerGateway.setIdExp("Null");
/*     */     }
/* 672 */     if (this.registerGateway.getTimeOUT().equals("")) {
/* 673 */       this.registerGateway.setTimeOUT("600");
/*     */     }
/*     */ 
/*     */     
/* 677 */     this.clientName.connectGIOT(this.registerGateway);
/*     */   }
/*     */   
/*     */   private void jCheckBoxGIoTActionPerformed(ActionEvent evt) {
/* 681 */     ButtonGIOT(this.jCheckBoxGIoT.isSelected());
/* 682 */     this.jCheckBoxGIoT.setEnabled(false);
/* 683 */     this.jTextIpServerSarlab.setText(this.initVirtualFrontier);
/* 684 */     this.jTextPortServerSarlab1.setText(this.initPortVirtualFrontier);
/* 685 */     this.jTextIdGIOT.setText("");
/*     */   }
/*     */   
/*     */   private void formWindowClosing(WindowEvent evt) {
/* 689 */     if (this.clientName.sarlab == null) {
/* 690 */       this.clientName.jLabel6.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/LAN64x64.png")));
/* 691 */       this.clientName.jLabelNetConnect.setText("LAN");
/* 692 */       this.clientName.jButtonTools.setEnabled(false);
/* 693 */       this.clientName.jButtonForwarding.setEnabled(false);
/* 694 */       this.clientName.jButtonForwardingLook.setEnabled(false);
/* 695 */       this.clientName.jButtonProfile.setEnabled(false);
/*     */     } 
/* 697 */     this.clientName.toUpdate();
/*     */   }
/*     */   
/*     */   public String giveMeFileNameConfigXML() {
/* 701 */     if (OS.isWindows()) {
/* 702 */       return "C:/sarlab/config/" + this.fileNameBaseXML;
/*     */     }
/* 704 */     return "/sarlab/config/" + this.fileNameBaseXML;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws UnsupportedLookAndFeelException {
/*     */     try {
/* 717 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 718 */         if ("Nimbus".equals(info.getName())) {
/* 719 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 723 */     } catch (ClassNotFoundException ex) {
/* 724 */       Logger.getLogger(PanelListGateway.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 725 */     } catch (InstantiationException ex) {
/* 726 */       Logger.getLogger(PanelListGateway.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 727 */     } catch (IllegalAccessException ex) {
/* 728 */       Logger.getLogger(PanelListGateway.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 729 */     } catch (UnsupportedLookAndFeelException ex) {
/* 730 */       Logger.getLogger(PanelListGateway.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */     
/* 734 */     Locale.setDefault(new Locale("en", "GB"));
/*     */     /*     */     /*     */     /*     */     
/* 736 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 738 */             (new PanelListGateway(null)).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelListGateway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */