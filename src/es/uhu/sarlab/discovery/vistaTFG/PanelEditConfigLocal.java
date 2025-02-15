/*     */ package es.uhu.sarlab.discovery.vistaTFG;

import es.uhu.sarlab.discovery.GIOTLocatorService;
import es.uhu.sarlab.discovery.model.RegisterName;
import es.uhu.sarlab.utils.DirectoryFilesUtility;
import es.uhu.sarlab.utils.OS;
/*     */ import java.awt.Color;
import java.awt.EventQueue;
/*     */ import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
import java.util.Locale;
/*     */ import java.util.ResourceBundle;
import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.logging.Logger;
import java.util.logging.Level;

/*     */ 
/*     */ public class PanelEditConfigLocal extends JFrame {
/*     */   RegisterName registerName;
/*     */   PanelClientServerName clientName;
/*     */   private JButton jButtonSave;
/*     */   private JCheckBox jCheckBoxDesktop;
/*     */   private JCheckBox jCheckBoxHtml;
/*     */   private JCheckBox jCheckBoxModbus;
/*     */   private JCheckBox jCheckBoxSsh;
/*     */   private JCheckBox jCheckBoxTelnet;
/*     */   private JCheckBox jCheckBoxVNC;
/*     */   
/*     */   public PanelEditConfigLocal(PanelClientServerName clientName) {
/*  28 */     this.clientName = clientName;
/*  29 */     initComponents();
/*  30 */     setLocationRelativeTo(null);
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
/*  41 */     this.jTextFieldDesktop.setEnabled(this.registerName.isRdesktop());
/*  42 */     this.jTextFieldVNC.setText(Integer.toString(this.registerName.getVncPort()));
/*  43 */     this.jCheckBoxVNC.setSelected(this.registerName.isVnc());
/*  44 */     this.jTextFieldVNC.setEnabled(this.registerName.isVnc());
/*  45 */     this.jTextFieldTelnet.setText(Integer.toString(this.registerName.getTelnetPort()));
/*  46 */     this.jCheckBoxTelnet.setSelected(this.registerName.isTelnet());
/*  47 */     this.jTextFieldTelnet.setEnabled(this.registerName.isTelnet());
/*  48 */     this.jTextFieldSsh.setText(Integer.toString(this.registerName.getSshPort()));
/*  49 */     this.jCheckBoxSsh.setSelected(this.registerName.isSsh());
/*  50 */     this.jTextFieldSsh.setEnabled(this.registerName.isSsh());
/*  51 */     this.jTextFieldsFTP.setText(Integer.toString(this.registerName.getSftpPort()));
/*  52 */     this.jCheckBoxsFTP.setSelected(this.registerName.isSftp());
/*  53 */     this.jTextFieldsFTP.setEnabled(this.registerName.isSftp());
/*  54 */     this.jTextFieldModbus.setText(Integer.toString(this.registerName.getModbusPort()));
/*  55 */     this.jCheckBoxModbus.setSelected(this.registerName.isModbus());
/*  56 */     this.jTextFieldModbus.setEnabled(this.registerName.isModbus());
/*  57 */     this.jTextFieldHttp.setText(Integer.toString(this.registerName.getHttpPort()));
/*  58 */     this.jTextFieldURI.setText(this.registerName.getHttpUri());
/*  59 */     this.jCheckBoxHtml.setSelected(this.registerName.isHttp());
/*  60 */     this.jTextFieldHttp.setEnabled(this.registerName.isHttp());
/*  61 */     if (!OS.isWindows())
/*  62 */       this.jButtonSave.setVisible(false); 
/*     */   }
/*     */   private JCheckBox jCheckBoxsFTP; private JLabel jLabel1; private JLabel jLabel11; private JLabel jLabel2; private JLabel jLabel3; private JLabel jLabel4; private JLabel jLabel5; private JLabel jLabelDesktop; private JLabel jLabelHttp; private JLabel jLabelModbus; private JLabel jLabelSftp; private JLabel jLabelSsh; private JLabel jLabelTelnet; private JLabel jLabelVNC; private JPanel jPanel1; private JPanel jPanel2; private JPanel jPanel3; private JPanel jPanel4; private JTextField jTextFieldDesktop; private JTextField jTextFieldHttp; private JTextField jTextFieldModbus; private JTextField jTextFieldName; private JTextField jTextFieldSsh; private JTextField jTextFieldTelnet; private JTextField jTextFieldURI;
/*     */   private JTextField jTextFieldVNC;
/*     */   private JTextField jTextFieldsFTP;
/*     */   
/*     */   public Image getIconImage() {
/*  69 */     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("es/uhu/sarlab/discovery/vista/resources/Remote-Desktop32x32.png"));
/*  70 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/* 100 */     this.jPanel3 = new JPanel();
/* 101 */     this.jPanel2 = new JPanel();
/* 102 */     this.jLabel2 = new JLabel();
/* 103 */     this.jLabel1 = new JLabel();
/* 104 */     this.jLabel3 = new JLabel();
/* 105 */     this.jLabelDesktop = new JLabel();
/* 106 */     this.jTextFieldDesktop = new JTextField();
/* 107 */     this.jCheckBoxDesktop = new JCheckBox();
/* 108 */     this.jLabelVNC = new JLabel();
/* 109 */     this.jTextFieldVNC = new JTextField();
/* 110 */     this.jCheckBoxVNC = new JCheckBox();
/* 111 */     this.jLabelTelnet = new JLabel();
/* 112 */     this.jTextFieldTelnet = new JTextField();
/* 113 */     this.jCheckBoxTelnet = new JCheckBox();
/* 114 */     this.jLabelSsh = new JLabel();
/* 115 */     this.jTextFieldSsh = new JTextField();
/* 116 */     this.jCheckBoxSsh = new JCheckBox();
/* 117 */     this.jLabelSftp = new JLabel();
/* 118 */     this.jTextFieldsFTP = new JTextField();
/* 119 */     this.jCheckBoxsFTP = new JCheckBox();
/* 120 */     this.jLabelModbus = new JLabel();
/* 121 */     this.jTextFieldModbus = new JTextField();
/* 122 */     this.jCheckBoxModbus = new JCheckBox();
/* 123 */     this.jLabelHttp = new JLabel();
/* 124 */     this.jTextFieldHttp = new JTextField();
/* 125 */     this.jCheckBoxHtml = new JCheckBox();
/* 126 */     this.jPanel4 = new JPanel();
/* 127 */     this.jLabel11 = new JLabel();
/* 128 */     this.jTextFieldURI = new JTextField();
/* 129 */     this.jButtonSave = new JButton();
/* 130 */     this.jPanel1 = new JPanel();
/* 131 */     this.jLabel4 = new JLabel();
/* 132 */     this.jLabel5 = new JLabel();
/* 133 */     this.jTextFieldName = new JTextField();
/*     */     
/* 135 */     setIconImage(getIconImage());
/*     */     
/* 137 */     this.jPanel3.setBackground(new Color(102, 153, 255));
/* 138 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_editor");
/* 139 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder(null, bundle.getString("PanelEditConfigLocal.jPanel3.border.title"), 0, 0, new Font("Tahoma", 1, 11)));
/*     */     
/* 141 */     this.jPanel2.setBackground(new Color(102, 153, 255));
/* 142 */     this.jPanel2.setLayout(new GridLayout(8, 0));
/*     */     
/* 144 */     this.jLabel2.setFont(new Font("Tahoma", 1, 16));
/* 145 */     this.jLabel2.setHorizontalAlignment(0);
/* 146 */     this.jLabel2.setText(bundle.getString("PanelEditConfigLocal.jLabel2.text"));
/* 147 */     this.jPanel2.add(this.jLabel2);
/*     */     
/* 149 */     this.jLabel1.setFont(new Font("Tahoma", 1, 16));
/* 150 */     this.jLabel1.setHorizontalAlignment(0);
/* 151 */     this.jLabel1.setText(bundle.getString("PanelEditConfigLocal.jLabel1.text"));
/* 152 */     this.jPanel2.add(this.jLabel1);
/*     */     
/* 154 */     this.jLabel3.setFont(new Font("Tahoma", 1, 16));
/* 155 */     this.jLabel3.setHorizontalAlignment(0);
/* 156 */     this.jLabel3.setText(bundle.getString("PanelEditConfigLocal.jLabel3.text"));
/* 157 */     this.jPanel2.add(this.jLabel3);
/*     */     
/* 159 */     this.jLabelDesktop.setFont(new Font("Tahoma", 1, 16));
/* 160 */     this.jLabelDesktop.setHorizontalAlignment(0);
/* 161 */     this.jLabelDesktop.setText(bundle.getString("PanelEditConfigLocal.jLabelDesktop.text"));
/* 162 */     this.jPanel2.add(this.jLabelDesktop);
/*     */     
/* 164 */     this.jTextFieldDesktop.setText(bundle.getString("PanelEditConfigLocal.jTextFieldDesktop.text"));
/* 165 */     this.jTextFieldDesktop.addFocusListener(new FocusAdapter() {
/*     */           public void focusGained(FocusEvent evt) {
/* 167 */             PanelEditConfigLocal.this.jTextFieldDesktopFocusGained(evt);
/*     */           }
/*     */           public void focusLost(FocusEvent evt) {
/* 170 */             PanelEditConfigLocal.this.jTextFieldDesktopFocusLost(evt);
/*     */           }
/*     */         });
/* 173 */     this.jTextFieldDesktop.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 175 */             PanelEditConfigLocal.this.jTextFieldDesktopActionPerformed(evt);
/*     */           }
/*     */         });
/* 178 */     this.jPanel2.add(this.jTextFieldDesktop);
/*     */     
/* 180 */     this.jCheckBoxDesktop.setHorizontalAlignment(0);
/* 181 */     this.jCheckBoxDesktop.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 183 */             PanelEditConfigLocal.this.jCheckBoxDesktopActionPerformed(evt);
/*     */           }
/*     */         });
/* 186 */     this.jPanel2.add(this.jCheckBoxDesktop);
/*     */     
/* 188 */     this.jLabelVNC.setFont(new Font("Tahoma", 1, 16));
/* 189 */     this.jLabelVNC.setHorizontalAlignment(0);
/* 190 */     this.jLabelVNC.setText(bundle.getString("PanelEditConfigLocal.jLabelVNC.text"));
/* 191 */     this.jPanel2.add(this.jLabelVNC);
/*     */     
/* 193 */     this.jTextFieldVNC.setText(bundle.getString("PanelEditConfigLocal.jTextFieldVNC.text"));
/* 194 */     this.jTextFieldVNC.addFocusListener(new FocusAdapter() {
/*     */           public void focusGained(FocusEvent evt) {
/* 196 */             PanelEditConfigLocal.this.jTextFieldVNCFocusGained(evt);
/*     */           }
/*     */           public void focusLost(FocusEvent evt) {
/* 199 */             PanelEditConfigLocal.this.jTextFieldVNCFocusLost(evt);
/*     */           }
/*     */         });
/* 202 */     this.jTextFieldVNC.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 204 */             PanelEditConfigLocal.this.jTextFieldVNCActionPerformed(evt);
/*     */           }
/*     */         });
/* 207 */     this.jPanel2.add(this.jTextFieldVNC);
/*     */     
/* 209 */     this.jCheckBoxVNC.setHorizontalAlignment(0);
/* 210 */     this.jCheckBoxVNC.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 212 */             PanelEditConfigLocal.this.jCheckBoxVNCActionPerformed(evt);
/*     */           }
/*     */         });
/* 215 */     this.jPanel2.add(this.jCheckBoxVNC);
/*     */     
/* 217 */     this.jLabelTelnet.setFont(new Font("Tahoma", 1, 16));
/* 218 */     this.jLabelTelnet.setHorizontalAlignment(0);
/* 219 */     this.jLabelTelnet.setText(bundle.getString("PanelEditConfigLocal.jLabelTelnet.text"));
/* 220 */     this.jPanel2.add(this.jLabelTelnet);
/*     */     
/* 222 */     this.jTextFieldTelnet.setText(bundle.getString("PanelEditConfigLocal.jTextFieldTelnet.text"));
/* 223 */     this.jTextFieldTelnet.addFocusListener(new FocusAdapter() {
/*     */           public void focusGained(FocusEvent evt) {
/* 225 */             PanelEditConfigLocal.this.jTextFieldTelnetFocusGained(evt);
/*     */           }
/*     */           public void focusLost(FocusEvent evt) {
/* 228 */             PanelEditConfigLocal.this.jTextFieldTelnetFocusLost(evt);
/*     */           }
/*     */         });
/* 231 */     this.jTextFieldTelnet.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 233 */             PanelEditConfigLocal.this.jTextFieldTelnetActionPerformed(evt);
/*     */           }
/*     */         });
/* 236 */     this.jPanel2.add(this.jTextFieldTelnet);
/*     */     
/* 238 */     this.jCheckBoxTelnet.setHorizontalAlignment(0);
/* 239 */     this.jCheckBoxTelnet.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 241 */             PanelEditConfigLocal.this.jCheckBoxTelnetActionPerformed(evt);
/*     */           }
/*     */         });
/* 244 */     this.jPanel2.add(this.jCheckBoxTelnet);
/*     */     
/* 246 */     this.jLabelSsh.setFont(new Font("Tahoma", 1, 16));
/* 247 */     this.jLabelSsh.setHorizontalAlignment(0);
/* 248 */     this.jLabelSsh.setText(bundle.getString("PanelEditConfigLocal.jLabelSsh.text"));
/* 249 */     this.jPanel2.add(this.jLabelSsh);
/*     */     
/* 251 */     this.jTextFieldSsh.setText(bundle.getString("PanelEditConfigLocal.jTextFieldSsh.text"));
/* 252 */     this.jTextFieldSsh.addFocusListener(new FocusAdapter() {
/*     */           public void focusGained(FocusEvent evt) {
/* 254 */             PanelEditConfigLocal.this.jTextFieldSshFocusGained(evt);
/*     */           }
/*     */           public void focusLost(FocusEvent evt) {
/* 257 */             PanelEditConfigLocal.this.jTextFieldSshFocusLost(evt);
/*     */           }
/*     */         });
/* 260 */     this.jTextFieldSsh.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 262 */             PanelEditConfigLocal.this.jTextFieldSshActionPerformed(evt);
/*     */           }
/*     */         });
/* 265 */     this.jPanel2.add(this.jTextFieldSsh);
/*     */     
/* 267 */     this.jCheckBoxSsh.setHorizontalAlignment(0);
/* 268 */     this.jCheckBoxSsh.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 270 */             PanelEditConfigLocal.this.jCheckBoxSshActionPerformed(evt);
/*     */           }
/*     */         });
/* 273 */     this.jPanel2.add(this.jCheckBoxSsh);
/*     */     
/* 275 */     this.jLabelSftp.setFont(new Font("Tahoma", 1, 16));
/* 276 */     this.jLabelSftp.setHorizontalAlignment(0);
/* 277 */     this.jLabelSftp.setText(bundle.getString("PanelEditConfigLocal.jLabelSftp.text"));
/* 278 */     this.jPanel2.add(this.jLabelSftp);
/*     */     
/* 280 */     this.jTextFieldsFTP.setText(bundle.getString("PanelEditConfigLocal.jTextFieldsFTP.text"));
/* 281 */     this.jTextFieldsFTP.setToolTipText(bundle.getString("PanelEditConfigLocal.jTextFieldsFTP.toolTipText"));
/* 282 */     this.jTextFieldsFTP.addFocusListener(new FocusAdapter() {
/*     */           public void focusGained(FocusEvent evt) {
/* 284 */             PanelEditConfigLocal.this.jTextFieldsFTPFocusGained(evt);
/*     */           }
/*     */           public void focusLost(FocusEvent evt) {
/* 287 */             PanelEditConfigLocal.this.jTextFieldsFTPFocusLost(evt);
/*     */           }
/*     */         });
/* 290 */     this.jTextFieldsFTP.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 292 */             PanelEditConfigLocal.this.jTextFieldsFTPActionPerformed(evt);
/*     */           }
/*     */         });
/* 295 */     this.jPanel2.add(this.jTextFieldsFTP);
/*     */     
/* 297 */     this.jCheckBoxsFTP.setHorizontalAlignment(0);
/* 298 */     this.jCheckBoxsFTP.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 300 */             PanelEditConfigLocal.this.jCheckBoxsFTPActionPerformed(evt);
/*     */           }
/*     */         });
/* 303 */     this.jPanel2.add(this.jCheckBoxsFTP);
/*     */     
/* 305 */     this.jLabelModbus.setFont(new Font("Tahoma", 1, 16));
/* 306 */     this.jLabelModbus.setHorizontalAlignment(0);
/* 307 */     this.jLabelModbus.setText(bundle.getString("PanelEditConfigLocal.jLabelModbus.text"));
/* 308 */     this.jPanel2.add(this.jLabelModbus);
/*     */     
/* 310 */     this.jTextFieldModbus.setText(bundle.getString("PanelEditConfigLocal.jTextFieldModbus.text"));
/* 311 */     this.jTextFieldModbus.setToolTipText(bundle.getString("PanelEditConfigLocal.jTextFieldModbus.toolTipText"));
/* 312 */     this.jTextFieldModbus.addFocusListener(new FocusAdapter() {
/*     */           public void focusGained(FocusEvent evt) {
/* 314 */             PanelEditConfigLocal.this.jTextFieldModbusFocusGained(evt);
/*     */           }
/*     */           public void focusLost(FocusEvent evt) {
/* 317 */             PanelEditConfigLocal.this.jTextFieldModbusFocusLost(evt);
/*     */           }
/*     */         });
/* 320 */     this.jTextFieldModbus.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 322 */             PanelEditConfigLocal.this.jTextFieldModbusActionPerformed(evt);
/*     */           }
/*     */         });
/* 325 */     this.jPanel2.add(this.jTextFieldModbus);
/*     */     
/* 327 */     this.jCheckBoxModbus.setHorizontalAlignment(0);
/* 328 */     this.jCheckBoxModbus.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 330 */             PanelEditConfigLocal.this.jCheckBoxModbusActionPerformed(evt);
/*     */           }
/*     */         });
/* 333 */     this.jPanel2.add(this.jCheckBoxModbus);
/*     */     
/* 335 */     this.jLabelHttp.setFont(new Font("Tahoma", 1, 16));
/* 336 */     this.jLabelHttp.setHorizontalAlignment(0);
/* 337 */     this.jLabelHttp.setText(bundle.getString("PanelEditConfigLocal.jLabelHttp.text"));
/* 338 */     this.jPanel2.add(this.jLabelHttp);
/*     */     
/* 340 */     this.jTextFieldHttp.setText(bundle.getString("PanelEditConfigLocal.jTextFieldHttp.text"));
/* 341 */     this.jTextFieldHttp.setToolTipText(bundle.getString("PanelEditConfigLocal.jTextFieldHttp.toolTipText"));
/* 342 */     this.jTextFieldHttp.addFocusListener(new FocusAdapter() {
/*     */           public void focusGained(FocusEvent evt) {
/* 344 */             PanelEditConfigLocal.this.jTextFieldHttpFocusGained(evt);
/*     */           }
/*     */           public void focusLost(FocusEvent evt) {
/* 347 */             PanelEditConfigLocal.this.jTextFieldHttpFocusLost(evt);
/*     */           }
/*     */         });
/* 350 */     this.jTextFieldHttp.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 352 */             PanelEditConfigLocal.this.jTextFieldHttpActionPerformed(evt);
/*     */           }
/*     */         });
/* 355 */     this.jPanel2.add(this.jTextFieldHttp);
/*     */     
/* 357 */     this.jCheckBoxHtml.setHorizontalAlignment(0);
/* 358 */     this.jCheckBoxHtml.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 360 */             PanelEditConfigLocal.this.jCheckBoxHtmlActionPerformed(evt);
/*     */           }
/*     */         });
/* 363 */     this.jPanel2.add(this.jCheckBoxHtml);
/*     */     
/* 365 */     this.jPanel4.setBackground(new Color(102, 153, 255));
/*     */     
/* 367 */     this.jLabel11.setFont(new Font("Tahoma", 1, 16));
/* 368 */     this.jLabel11.setHorizontalAlignment(0);
/* 369 */     this.jLabel11.setText(bundle.getString("PanelEditConfigLocal.jLabel11.text"));
/*     */     
/* 371 */     this.jTextFieldURI.setText(bundle.getString("PanelEditConfigLocal.jTextFieldURI.text"));
/* 372 */     this.jTextFieldURI.setToolTipText(bundle.getString("PanelEditConfigLocal.jTextFieldURI.toolTipText"));
/* 373 */     this.jTextFieldURI.addFocusListener(new FocusAdapter() {
/*     */           public void focusGained(FocusEvent evt) {
/* 375 */             PanelEditConfigLocal.this.jTextFieldURIFocusGained(evt);
/*     */           }
/*     */           public void focusLost(FocusEvent evt) {
/* 378 */             PanelEditConfigLocal.this.jTextFieldURIFocusLost(evt);
/*     */           }
/*     */         });
/* 381 */     this.jTextFieldURI.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 383 */             PanelEditConfigLocal.this.jTextFieldURIActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 387 */     GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
/* 388 */     this.jPanel4.setLayout(jPanel4Layout);
/* 389 */     jPanel4Layout.setHorizontalGroup(jPanel4Layout
/* 390 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 391 */         .addGroup(jPanel4Layout.createSequentialGroup()
/* 392 */           .addComponent(this.jLabel11, -1, 141, 32767)
/* 393 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 394 */           .addComponent(this.jTextFieldURI, -2, 292, -2)));
/*     */     
/* 396 */     jPanel4Layout.setVerticalGroup(jPanel4Layout
/* 397 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 398 */         .addGroup(jPanel4Layout.createSequentialGroup()
/* 399 */           .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 400 */             .addComponent(this.jLabel11)
/* 401 */             .addComponent(this.jTextFieldURI, -2, -1, -2))
/* 402 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 405 */     this.jButtonSave.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/salvar.png")));
/* 406 */     this.jButtonSave.setText(bundle.getString("PanelEditConfigLocal.jButtonSave.text"));
/* 407 */     this.jButtonSave.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 409 */             PanelEditConfigLocal.this.jButtonSaveActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 413 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 414 */     this.jPanel3.setLayout(jPanel3Layout);
/* 415 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/* 416 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 417 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 418 */           .addContainerGap()
/* 419 */           .addComponent(this.jPanel4, -1, -1, 32767)
/* 420 */           .addContainerGap())
/* 421 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
/* 422 */           .addContainerGap(-1, 32767)
/* 423 */           .addComponent(this.jButtonSave)
/* 424 */           .addGap(178, 178, 178))
/* 425 */         .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 426 */           .addGroup(jPanel3Layout.createSequentialGroup()
/* 427 */             .addContainerGap()
/* 428 */             .addComponent(this.jPanel2, -1, 365, 32767))));
/*     */     
/* 430 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 431 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 432 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 433 */           .addContainerGap(243, 32767)
/* 434 */           .addComponent(this.jPanel4, -2, 25, -2)
/* 435 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 436 */           .addComponent(this.jButtonSave)
/* 437 */           .addContainerGap())
/* 438 */         .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 439 */           .addGroup(jPanel3Layout.createSequentialGroup()
/* 440 */             .addContainerGap()
/* 441 */             .addComponent(this.jPanel2, -2, 233, -2)
/* 442 */             .addContainerGap(58, 32767))));
/*     */ 
/*     */     
/* 445 */     this.jPanel1.setBackground(new Color(102, 153, 255));
/* 446 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, bundle.getString("PanelEditConfigLocal.jPanel1.border.title"), 0, 0, new Font("Tahoma", 1, 11)));
/*     */     
/* 448 */     this.jLabel4.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/logoSN64x64.png")));
/* 449 */     this.jLabel4.setText(bundle.getString("PanelEditConfigLocal.jLabel4.text"));
/*     */     
/* 451 */     this.jLabel5.setFont(new Font("Tahoma", 1, 14));
/* 452 */     this.jLabel5.setText(bundle.getString("PanelEditConfigLocal.jLabel5.text"));
/*     */     
/* 454 */     this.jTextFieldName.setText(bundle.getString("PanelEditConfigLocal.jTextFieldName.text"));
/* 455 */     this.jTextFieldName.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 457 */             PanelEditConfigLocal.this.jTextFieldNameActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 461 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 462 */     this.jPanel1.setLayout(jPanel1Layout);
/* 463 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 464 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 465 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 466 */           .addComponent(this.jLabel4)
/* 467 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 468 */           .addComponent(this.jLabel5)
/* 469 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 470 */           .addComponent(this.jTextFieldName)
/* 471 */           .addContainerGap()));
/*     */     
/* 473 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 474 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 475 */         .addComponent(this.jLabel4)
/* 476 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 477 */           .addGap(16, 16, 16)
/* 478 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 479 */             .addComponent(this.jLabel5)
/* 480 */             .addComponent(this.jTextFieldName, -2, -1, -2))));
/*     */ 
/*     */     
/* 483 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 484 */     getContentPane().setLayout(layout);
/* 485 */     layout.setHorizontalGroup(layout
/* 486 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 487 */         .addGroup(layout.createSequentialGroup()
/* 488 */           .addContainerGap()
/* 489 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 490 */             .addComponent(this.jPanel3, -1, -1, 32767)
/* 491 */             .addComponent(this.jPanel1, -1, -1, 32767))
/* 492 */           .addContainerGap()));
/*     */     
/* 494 */     layout.setVerticalGroup(layout
/* 495 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 496 */         .addGroup(layout.createSequentialGroup()
/* 497 */           .addContainerGap()
/* 498 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 499 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 500 */           .addComponent(this.jPanel3, -2, -1, -2)
/* 501 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 504 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   private void jTextFieldURIFocusGained(FocusEvent evt) {
/* 509 */     this.jTextFieldURI.setBackground(Color.lightGray);
/*     */   }
/*     */ 
/*     */   
/*     */   private void jTextFieldURIFocusLost(FocusEvent evt) {
/* 514 */     this.jTextFieldURI.setBackground(Color.WHITE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void jTextFieldURIActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   private void jTextFieldDesktopFocusGained(FocusEvent evt) {
/* 524 */     this.jTextFieldDesktop.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldDesktopFocusLost(FocusEvent evt) {
/* 528 */     this.jTextFieldDesktop.setBackground(Color.WHITE);
/* 529 */     String port = this.jTextFieldDesktop.getText();
/* 530 */     if ("".equals(port)) {
/* 531 */       this.jTextFieldDesktop.setText("3389");
/* 532 */       port = "3389";
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jTextFieldDesktopActionPerformed(ActionEvent evt) {
/* 537 */     this.jTextFieldDesktop.setBackground(Color.WHITE);
/* 538 */     String port = this.jTextFieldDesktop.getText();
/* 539 */     if ("".equals(port)) {
/* 540 */       this.jTextFieldDesktop.setText("3389");
/* 541 */       port = "3389";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void jCheckBoxDesktopActionPerformed(ActionEvent evt) {
/* 547 */     this.registerName.setRdesktop(this.jCheckBoxDesktop.isSelected());
/* 548 */     this.jTextFieldDesktop.setEnabled(this.registerName.isRdesktop());
/*     */   }
/*     */ 
/*     */   
/*     */   private void jTextFieldVNCFocusGained(FocusEvent evt) {
/* 553 */     this.jTextFieldVNC.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldVNCFocusLost(FocusEvent evt) {
/* 557 */     this.jTextFieldVNC.setBackground(Color.WHITE);
/* 558 */     String port = this.jTextFieldVNC.getText();
/* 559 */     if ("".equals(port)) {
/* 560 */       this.jTextFieldVNC.setText("5900");
/* 561 */       port = "5900";
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jTextFieldVNCActionPerformed(ActionEvent evt) {
/* 566 */     this.jTextFieldVNC.setBackground(Color.WHITE);
/* 567 */     String port = this.jTextFieldVNC.getText();
/* 568 */     if ("".equals(port)) {
/* 569 */       this.jTextFieldVNC.setText("5900");
/* 570 */       port = "5900";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void jCheckBoxVNCActionPerformed(ActionEvent evt) {
/* 576 */     this.registerName.setVnc(this.jCheckBoxVNC.isSelected());
/* 577 */     this.jTextFieldVNC.setEnabled(this.registerName.isVnc());
/*     */   }
/*     */   
/*     */   private void jTextFieldTelnetFocusGained(FocusEvent evt) {
/* 581 */     this.jTextFieldTelnet.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldTelnetFocusLost(FocusEvent evt) {
/* 585 */     this.jTextFieldTelnet.setBackground(Color.WHITE);
/* 586 */     String port = this.jTextFieldTelnet.getText();
/* 587 */     if ("".equals(port)) {
/* 588 */       this.jTextFieldTelnet.setText("23");
/* 589 */       port = "23";
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jTextFieldTelnetActionPerformed(ActionEvent evt) {
/* 594 */     this.jTextFieldTelnet.setBackground(Color.WHITE);
/* 595 */     String port = this.jTextFieldTelnet.getText();
/* 596 */     if ("".equals(port)) {
/* 597 */       this.jTextFieldTelnet.setText("23");
/* 598 */       port = "23";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void jCheckBoxTelnetActionPerformed(ActionEvent evt) {
/* 604 */     this.registerName.setTelnet(this.jCheckBoxTelnet.isSelected());
/* 605 */     this.jTextFieldTelnet.setEnabled(this.registerName.isTelnet());
/*     */   }
/*     */   
/*     */   private void jTextFieldSshFocusGained(FocusEvent evt) {
/* 609 */     this.jTextFieldSsh.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldSshFocusLost(FocusEvent evt) {
/* 613 */     this.jTextFieldSsh.setBackground(Color.WHITE);
/* 614 */     String port = this.jTextFieldSsh.getText();
/* 615 */     if ("".equals(port)) {
/* 616 */       this.jTextFieldSsh.setText("22");
/* 617 */       port = "22";
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jTextFieldSshActionPerformed(ActionEvent evt) {
/* 622 */     this.jTextFieldSsh.setBackground(Color.WHITE);
/* 623 */     String port = this.jTextFieldSsh.getText();
/* 624 */     if ("".equals(port)) {
/* 625 */       this.jTextFieldSsh.setText("22");
/* 626 */       port = "22";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void jCheckBoxSshActionPerformed(ActionEvent evt) {
/* 632 */     this.registerName.setSsh(this.jCheckBoxSsh.isSelected());
/* 633 */     this.jTextFieldSsh.setEnabled(this.registerName.isSsh());
/*     */   }
/*     */   
/*     */   private void jTextFieldsFTPFocusGained(FocusEvent evt) {
/* 637 */     this.jTextFieldsFTP.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldsFTPFocusLost(FocusEvent evt) {
/* 641 */     this.jTextFieldsFTP.setBackground(Color.WHITE);
/* 642 */     String port = this.jTextFieldsFTP.getText();
/* 643 */     if ("".equals(port)) {
/* 644 */       this.jTextFieldsFTP.setText("22");
/* 645 */       port = "22";
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jTextFieldsFTPActionPerformed(ActionEvent evt) {
/* 650 */     this.jTextFieldsFTP.setBackground(Color.WHITE);
/* 651 */     String port = this.jTextFieldsFTP.getText();
/* 652 */     if ("".equals(port)) {
/* 653 */       this.jTextFieldsFTP.setText("22");
/* 654 */       port = "22";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void jCheckBoxsFTPActionPerformed(ActionEvent evt) {
/* 660 */     this.registerName.setSftp(this.jCheckBoxsFTP.isSelected());
/* 661 */     this.jTextFieldsFTP.setEnabled(this.registerName.isSftp());
/*     */   }
/*     */   
/*     */   private void jTextFieldModbusFocusGained(FocusEvent evt) {
/* 665 */     this.jTextFieldModbus.setBackground(Color.lightGray);
/*     */   }
/*     */   
/*     */   private void jTextFieldModbusFocusLost(FocusEvent evt) {
/* 669 */     this.jTextFieldModbus.setBackground(Color.WHITE);
/* 670 */     String port = this.jTextFieldModbus.getText();
/* 671 */     if ("".equals(port)) {
/* 672 */       this.jTextFieldModbus.setText("502");
/* 673 */       port = "502";
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jTextFieldModbusActionPerformed(ActionEvent evt) {
/* 678 */     this.jTextFieldModbus.setBackground(Color.WHITE);
/* 679 */     String port = this.jTextFieldModbus.getText();
/* 680 */     if ("".equals(port)) {
/* 681 */       this.jTextFieldModbus.setText("502");
/* 682 */       port = "502";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void jCheckBoxModbusActionPerformed(ActionEvent evt) {
/* 688 */     this.registerName.setModbus(this.jCheckBoxModbus.isSelected());
/* 689 */     this.jTextFieldModbus.setEnabled(this.registerName.isModbus());
/*     */   }
/*     */ 
/*     */   
/*     */   private void jTextFieldHttpFocusGained(FocusEvent evt) {
/* 694 */     this.jTextFieldModbus.setBackground(Color.lightGray);
/*     */   }
/*     */ 
/*     */   
/*     */   private void jTextFieldHttpFocusLost(FocusEvent evt) {
/* 699 */     this.jTextFieldHttp.setBackground(Color.WHITE);
/* 700 */     String port = this.jTextFieldHttp.getText();
/* 701 */     if ("".equals(port)) {
/* 702 */       this.jTextFieldHttp.setText("80");
/* 703 */       port = "3389";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void jTextFieldHttpActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */   
/*     */   private void jCheckBoxHtmlActionPerformed(ActionEvent evt) {
/* 713 */     this.registerName.setHttp(this.jCheckBoxHtml.isSelected());
/* 714 */     this.jTextFieldHttp.setEnabled(this.registerName.isHttp());
/* 715 */     this.jTextFieldURI.setEnabled(this.registerName.isHttp());
/*     */   }
/*     */   
/*     */   private void jButtonSaveActionPerformed(ActionEvent evt) {
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
/* 734 */     this.registerName.setHttp(this.jCheckBoxHtml.isSelected());
/* 735 */     this.registerName.toFileXML(giveMeFileNameConfigXML());
/* 736 */     setVisible(false);
/* 737 */     if (this.clientName != null) this.clientName.toUpdate();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void jTextFieldNameActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws UnsupportedLookAndFeelException {
/*     */     try {
/* 755 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 756 */         if ("Nimbus".equals(info.getName())) {
/* 757 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 761 */     } catch (ClassNotFoundException ex) {
/* 762 */       Logger.getLogger(PanelEditConfigLocal.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 763 */     } catch (InstantiationException ex) {
/* 764 */       Logger.getLogger(PanelEditConfigLocal.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 765 */     } catch (IllegalAccessException ex) {
/* 766 */       Logger.getLogger(PanelEditConfigLocal.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 767 */     } catch (UnsupportedLookAndFeelException ex) {
/* 768 */       Logger.getLogger(PanelEditConfigLocal.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 776 */     Locale.setDefault(new Locale("en", "GB"));
/* 777 */     System.out.println("Current Locale: " + Locale.getDefault());
/*     */     
/* 779 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 781 */             (new PanelEditConfigLocal(null)).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelEditConfigLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */