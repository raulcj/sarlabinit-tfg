/*     */ package es.uhu.sarlab.discovery.vistaTFG;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LayoutStyle;
/*     */ 
/*     */ public class PanelNodoFrowardingL
/*     */   extends JFrame {
/*  29 */   public String mode = "add"; public String IPLocal; public String portLocal; public String IPRemote;
/*     */   public String portRemote;
/*     */   public String descriptionRemote;
/*     */   public String formatListSelect;
/*     */   public PanelClientServerName clientName;
/*     */   private JButton jButtonCancel;
/*     */   private JButton jButtonOk;
/*     */   private JCheckBox jCheckBox1;
/*     */   private JLabel jLabel10;
/*     */   private JLabel jLabel11;
/*     */   private JLabel jLabel13;
/*     */   private JLabel jLabel14;
/*     */   
/*     */   public PanelNodoFrowardingL(PanelClientServerName clientName) {
/*  43 */     this.clientName = clientName;
/*  44 */     initComponents();
/*  45 */     setLocationRelativeTo(null);
/*  46 */     this.jTextFieldIPL.setText("127.0.0.1");
/*  47 */     this.jTextFieldIPL.setEditable(false);
/*  48 */     this.jTextFieldPortL.setText("0");
/*  49 */     this.jTextFieldPortR.setText("0");
/*  50 */     this.mode = "local";
/*     */   }
/*     */   private JLabel jLabel5; private JLabel jLabel6; private JLabel jLabel7; private JLabel jLabel8; private JLabel jLabel9; private JLabel jLabelModel; public JLabel jModel; private JPanel jPanel1; private JPanel jPanel3; private JPanel jPanel4; public JTextField jTextFieldDes; public JTextField jTextFieldIPL; public JTextField jTextFieldIPR; public JTextField jTextFieldPortL; public JTextField jTextFieldPortR;
/*     */   public void setIpInit() {
/*  54 */     this.jTextFieldIPR.setText(this.clientName.ipInit());
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
/*     */   private void initComponents() {
/*  72 */     this.jPanel1 = new JPanel();
/*  73 */     this.jPanel4 = new JPanel();
/*  74 */     this.jLabel7 = new JLabel();
/*  75 */     this.jTextFieldIPL = new JTextField();
/*  76 */     this.jTextFieldPortL = new JTextField();
/*  77 */     this.jLabel10 = new JLabel();
/*  78 */     this.jLabel13 = new JLabel();
/*  79 */     this.jLabel14 = new JLabel();
/*  80 */     this.jButtonCancel = new JButton();
/*  81 */     this.jButtonOk = new JButton();
/*  82 */     this.jPanel3 = new JPanel();
/*  83 */     this.jLabel8 = new JLabel();
/*  84 */     this.jTextFieldIPR = new JTextField();
/*  85 */     this.jTextFieldDes = new JTextField();
/*  86 */     this.jLabel5 = new JLabel();
/*  87 */     this.jLabel6 = new JLabel();
/*  88 */     this.jTextFieldPortR = new JTextField();
/*  89 */     this.jLabel11 = new JLabel();
/*  90 */     this.jLabel9 = new JLabel();
/*  91 */     this.jLabelModel = new JLabel();
/*  92 */     this.jModel = new JLabel();
/*  93 */     this.jCheckBox1 = new JCheckBox();
/*     */     
/*  95 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelNodoFrowardingL");
/*  96 */     setTitle(bundle.getString("PanelNodoFrowardingL.title"));
/*  97 */     setIconImage(getIconImage());
/*     */     
/*  99 */     this.jPanel1.setBackground(new Color(200, 208, 250));
/*     */     
/* 101 */     this.jPanel4.setBackground(new Color(255, 204, 102));
/* 102 */     this.jPanel4.setBorder(BorderFactory.createTitledBorder(""));
/* 103 */     this.jPanel4.setPreferredSize(new Dimension(233, 157));
/*     */     
/* 105 */     this.jLabel7.setFont(new Font("Tahoma", 1, 11));
/* 106 */     this.jLabel7.setHorizontalAlignment(0);
/* 107 */     this.jLabel7.setText(bundle.getString("PanelNodoFrowardingL.jLabel7.text"));
/*     */     
/* 109 */     this.jTextFieldIPL.setColumns(15);
/* 110 */     this.jTextFieldIPL.setFont(new Font("Courier New", 0, 11));
/* 111 */     this.jTextFieldIPL.setText(bundle.getString("PanelNodoFrowardingL.jTextFieldIPL.text"));
/* 112 */     this.jTextFieldIPL.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 114 */             PanelNodoFrowardingL.this.jTextFieldIPLActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 118 */     this.jTextFieldPortL.setColumns(5);
/* 119 */     this.jTextFieldPortL.setFont(new Font("Courier New", 0, 11));
/* 120 */     this.jTextFieldPortL.setText(bundle.getString("PanelNodoFrowardingL.jTextFieldPortL.text"));
/*     */     
/* 122 */     this.jLabel10.setFont(new Font("Tahoma", 1, 11));
/* 123 */     this.jLabel10.setHorizontalAlignment(0);
/* 124 */     this.jLabel10.setText(bundle.getString("PanelNodoFrowardingL.jLabel10.text"));
/*     */     
/* 126 */     this.jLabel13.setHorizontalAlignment(0);
/* 127 */     this.jLabel13.setIcon(new ImageIcon(getClass().getResource("/paneladminsarlab/image/portatil.png")));
/*     */     
/* 129 */     this.jLabel14.setFont(new Font("Tahoma", 1, 11));
/* 130 */     this.jLabel14.setHorizontalAlignment(0);
/* 131 */     this.jLabel14.setText(bundle.getString("PanelNodoFrowardingL.jLabel14.text"));
/*     */     
/* 133 */     GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
/* 134 */     this.jPanel4.setLayout(jPanel4Layout);
/* 135 */     jPanel4Layout.setHorizontalGroup(jPanel4Layout
/* 136 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 137 */         .addGroup(jPanel4Layout.createSequentialGroup()
/* 138 */           .addContainerGap()
/* 139 */           .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 140 */             .addComponent(this.jLabel13, GroupLayout.Alignment.LEADING, -1, -1, 32767)
/* 141 */             .addGroup(GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
/* 142 */               .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 143 */                 .addComponent(this.jTextFieldIPL, -2, 110, -2)
/* 144 */                 .addComponent(this.jLabel7, -2, 102, -2))
/* 145 */               .addGap(18, 18, 18)
/* 146 */               .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 147 */                 .addComponent(this.jLabel10, -1, -1, 32767)
/* 148 */                 .addComponent(this.jTextFieldPortL)))
/* 149 */             .addComponent(this.jLabel14, GroupLayout.Alignment.LEADING, -1, -1, 32767))
/* 150 */           .addContainerGap()));
/*     */     
/* 152 */     jPanel4Layout.setVerticalGroup(jPanel4Layout
/* 153 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 154 */         .addGroup(jPanel4Layout.createSequentialGroup()
/* 155 */           .addComponent(this.jLabel14)
/* 156 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 157 */           .addComponent(this.jLabel13, -2, 74, -2)
/* 158 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 159 */           .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 160 */             .addComponent(this.jLabel10, -1, -1, 32767)
/* 161 */             .addComponent(this.jLabel7, -1, -1, 32767))
/* 162 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 163 */           .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 164 */             .addComponent(this.jTextFieldPortL)
/* 165 */             .addComponent(this.jTextFieldIPL))
/* 166 */           .addGap(20, 20, 20)));
/*     */ 
/*     */     
/* 169 */     this.jButtonCancel.setIcon(new ImageIcon(getClass().getResource("/paneladminsarlab/image/cancel.gif")));
/* 170 */     this.jButtonCancel.setText(bundle.getString("PanelNodoFrowardingL.jButtonCancel.text"));
/* 171 */     this.jButtonCancel.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 173 */             PanelNodoFrowardingL.this.jButtonCancelActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 177 */     this.jButtonOk.setIcon(new ImageIcon(getClass().getResource("/paneladminsarlab/image/accept.gif")));
/* 178 */     this.jButtonOk.setText(bundle.getString("PanelNodoFrowardingL.jButtonOk.text"));
/* 179 */     this.jButtonOk.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 181 */             PanelNodoFrowardingL.this.jButtonOkActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 185 */     this.jPanel3.setBackground(new Color(255, 204, 102));
/* 186 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder(""));
/* 187 */     this.jPanel3.setMaximumSize(new Dimension(400, 157));
/* 188 */     this.jPanel3.setMinimumSize(new Dimension(400, 157));
/* 189 */     this.jPanel3.setPreferredSize(new Dimension(400, 157));
/*     */     
/* 191 */     this.jLabel8.setFont(new Font("Tahoma", 1, 11));
/* 192 */     this.jLabel8.setHorizontalAlignment(0);
/* 193 */     this.jLabel8.setText(bundle.getString("PanelNodoFrowardingL.jLabel8.text"));
/*     */     
/* 195 */     this.jTextFieldIPR.setColumns(15);
/* 196 */     this.jTextFieldIPR.setFont(new Font("Courier New", 0, 11));
/* 197 */     this.jTextFieldIPR.setText(bundle.getString("PanelNodoFrowardingL.jTextFieldIPR.text"));
/* 198 */     this.jTextFieldIPR.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 200 */             PanelNodoFrowardingL.this.jTextFieldIPRActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 204 */     this.jTextFieldDes.setFont(new Font("Courier New", 0, 11));
/* 205 */     this.jTextFieldDes.setText(bundle.getString("PanelNodoFrowardingL.jTextFieldDes.text"));
/*     */     
/* 207 */     this.jLabel5.setFont(new Font("Tahoma", 1, 11));
/* 208 */     this.jLabel5.setHorizontalAlignment(0);
/* 209 */     this.jLabel5.setText(bundle.getString("PanelNodoFrowardingL.jLabel5.text"));
/*     */     
/* 211 */     this.jLabel6.setFont(new Font("Tahoma", 1, 11));
/* 212 */     this.jLabel6.setHorizontalAlignment(0);
/* 213 */     this.jLabel6.setText(bundle.getString("PanelNodoFrowardingL.jLabel6.text"));
/*     */     
/* 215 */     this.jTextFieldPortR.setColumns(5);
/* 216 */     this.jTextFieldPortR.setFont(new Font("Courier New", 0, 11));
/* 217 */     this.jTextFieldPortR.setText(bundle.getString("PanelNodoFrowardingL.jTextFieldPortR.text"));
/*     */     
/* 219 */     this.jLabel11.setHorizontalAlignment(0);
/* 220 */     this.jLabel11.setIcon(new ImageIcon(getClass().getResource("/paneladminsarlab/image/phidgets2.png")));
/*     */     
/* 222 */     this.jLabel9.setFont(new Font("Tahoma", 1, 11));
/* 223 */     this.jLabel9.setHorizontalAlignment(0);
/* 224 */     this.jLabel9.setText(bundle.getString("PanelNodoFrowardingL.jLabel9.text"));
/*     */     
/* 226 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 227 */     this.jPanel3.setLayout(jPanel3Layout);
/* 228 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/* 229 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 230 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 231 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 232 */             .addComponent(this.jLabel9, GroupLayout.Alignment.TRAILING, -1, 398, 32767)
/* 233 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 234 */               .addContainerGap()
/* 235 */               .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 236 */                 .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
/* 237 */                   .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 238 */                     .addComponent(this.jLabel6, -2, 96, -2)
/* 239 */                     .addComponent(this.jTextFieldIPR, -2, -1, -2))
/* 240 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 241 */                   .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 242 */                     .addComponent(this.jLabel5, -1, -1, 32767)
/* 243 */                     .addComponent(this.jTextFieldPortR))
/* 244 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 245 */                   .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 246 */                     .addComponent(this.jLabel8, -1, -1, 32767)
/* 247 */                     .addComponent(this.jTextFieldDes, -1, 224, 32767))
/* 248 */                   .addGap(0, 0, 32767))
/* 249 */                 .addComponent(this.jLabel11, -1, 388, 32767))))
/* 250 */           .addContainerGap()));
/*     */     
/* 252 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 253 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 254 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
/* 255 */           .addComponent(this.jLabel9)
/* 256 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 257 */           .addComponent(this.jLabel11)
/* 258 */           .addGap(11, 11, 11)
/* 259 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 260 */             .addComponent(this.jLabel8, -1, -1, 32767)
/* 261 */             .addComponent(this.jLabel6, -1, -1, 32767)
/* 262 */             .addComponent(this.jLabel5, -1, -1, 32767))
/* 263 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 264 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 265 */             .addComponent(this.jTextFieldIPR)
/* 266 */             .addComponent(this.jTextFieldPortR, -2, -1, -2)
/* 267 */             .addComponent(this.jTextFieldDes, -2, -1, -2))
/* 268 */           .addContainerGap()));
/*     */ 
/*     */     
/* 271 */     this.jLabelModel.setText(bundle.getString("PanelNodoFrowardingL.jLabelModel.text"));
/*     */     
/* 273 */     this.jModel.setText(bundle.getString("PanelNodoFrowardingL.jModel.text"));
/*     */     
/* 275 */     this.jCheckBox1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 277 */             PanelNodoFrowardingL.this.jCheckBox1ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 281 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 282 */     this.jPanel1.setLayout(jPanel1Layout);
/* 283 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 284 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 285 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 286 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 287 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 288 */               .addContainerGap()
/* 289 */               .addComponent(this.jPanel4, -2, -1, -2))
/* 290 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 291 */               .addGap(19, 19, 19)
/* 292 */               .addComponent(this.jLabelModel)
/* 293 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 294 */               .addComponent(this.jCheckBox1)
/* 295 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 296 */               .addComponent(this.jModel, -2, 34, -2)))
/* 297 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 298 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 299 */             .addGroup(jPanel1Layout.createSequentialGroup()
/* 300 */               .addGap(10, 10, 10)
/* 301 */               .addComponent(this.jButtonOk)
/* 302 */               .addGap(38, 38, 38)
/* 303 */               .addComponent(this.jButtonCancel))
/* 304 */             .addComponent(this.jPanel3, -1, -1, 32767))
/* 305 */           .addContainerGap()));
/*     */     
/* 307 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 308 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 309 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 310 */           .addContainerGap()
/* 311 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 312 */             .addComponent(this.jPanel3, -1, 177, 32767)
/* 313 */             .addComponent(this.jPanel4, -2, 177, -2))
/* 314 */           .addGap(18, 18, 18)
/* 315 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 316 */             .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 317 */               .addComponent(this.jButtonOk)
/* 318 */               .addComponent(this.jButtonCancel))
/* 319 */             .addComponent(this.jLabelModel)
/* 320 */             .addComponent(this.jCheckBox1)
/* 321 */             .addComponent(this.jModel))
/* 322 */           .addContainerGap()));
/*     */ 
/*     */     
/* 325 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 326 */     getContentPane().setLayout(layout);
/* 327 */     layout.setHorizontalGroup(layout
/* 328 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 329 */         .addGroup(layout.createSequentialGroup()
/* 330 */           .addContainerGap()
/* 331 */           .addComponent(this.jPanel1, -1, -1, 32767)
/* 332 */           .addContainerGap()));
/*     */     
/* 334 */     layout.setVerticalGroup(layout
/* 335 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 336 */         .addGroup(layout.createSequentialGroup()
/* 337 */           .addContainerGap()
/* 338 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 339 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 342 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void jTextFieldIPLActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */   
/*     */   private void jButtonOkActionPerformed(ActionEvent evt) {
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
/* 361 */       if (this.mode.equals("local"))
/*     */       {
/*     */         
/* 364 */         this.clientName.addForwardingLocalhostL(this.descriptionRemote, this.IPRemote, portR, portL);
/*     */       }
/*     */       
/* 367 */       if (this.mode.equals("adress")) {
/* 368 */         System.out.println(this.formatListSelect + " " + portR + " " + portL);
/* 369 */         this.clientName.addForwardingLocalAdressL(this.descriptionRemote, this.IPRemote, portR, portL);
/*     */       } 
/* 371 */       setVisible(false);
/*     */     }
/* 373 */     catch (NumberFormatException e) {
/* 374 */       this.jTextFieldPortL.requestFocus();
/* 375 */       JOptionPane.showMessageDialog(null, "El puerto local no es correcto", "Error", 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void jTextFieldIPRActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */   
/*     */   private void jButtonCancelActionPerformed(ActionEvent evt) {
/* 385 */     setVisible(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getIconImage() {
/* 392 */     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("es/uhu/sarlab/discovery/vista/resources/Remote-Desktop32x32.png"));
/* 393 */     return retValue;
/*     */   }
/*     */ 
/*     */   
/*     */   private void jCheckBox1ActionPerformed(ActionEvent evt) {
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 419 */     Locale.setDefault(new Locale("en", "GB"));
/* 420 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 422 */             (new PanelNodoFrowardingL(null)).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelNodoFrowardingL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */