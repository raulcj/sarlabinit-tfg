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
            //PanelClientServerName clientName;
/*     */   /*     */   
/*     */   public PanelListGateway(PanelClientServerName clientName) {
/*  48 */    
/*  49 */     //this.clientName = clientName;
/*  50 */     
/*  51 */ 
/*  63 */     this.jTextKeyGIOT.setVisible(false);
/*  64 */     this.jLabelKeyGIOT.setVisible(false);
/*  65 */     
/*  66 */     
/*  67 */     this.jCheckBoxGIoT.setEnabled(false);
/*  68 */     this.jTextPortInputGIOTTunnel.setEditable(false);
/*  69 */     this.jTextPortInputGIOTWeb.setEditable(false);
/*  70 */     ButtonGIOT(true);
/*     */   }




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
/* 154 */     
/* 155 */     this.jTextlIdConnect = new JTextField();
/* 156 */     

/* 157 */     this.jTextIpServerSarlab = new JTextField();
/* 158 */     this.jLabelPortServerSarlab = new JLabel();
/* 159 */     this.jTextPortServerSarlab1 = new JTextField();
/* 160 */     
/* 161 */     this.jTextIdGIOT = new JTextField();
/* 162 */     this.jLabelUser = new JLabel();
/* 163 */     this.jTextUserGIOT = new JTextField();
/* 164 */     this.jLabelPass = new JLabel();
/* 165 */     this.jPasswordPassGIOT = new JPasswordField();
/* 166 */     this.jLabelTimeOut = new JLabel();
/* 167 */     this.jTextTimeOUT = new JTextField();
/* 168 */     
/* 169 */     this.jTextIdExp = new JTextField();
/* 170 */     this.jLabelKeyGIOT = new JLabel();
/* 171 */     this.jTextKeyGIOT = new JPasswordField();
/* 172 */     this.jLabelPortInputGIOTTunnel = new JLabel();
/* 173 */     this.jTextPortInputGIOTTunnel = new JTextField();
/* 174 */     this.jLabelPortInputGIOTWeb = new JLabel();
/* 175 */     this.jTextPortInputGIOTWeb = new JTextField();
/* 176 */     this.jPanel1 = new JPanel();
/* 177 */    
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
/* 200 */    
/* 201 */     this.jButtonSave.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 203 */             PanelListGateway.this.jButtonSaveActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 207 */     this.jButtonNew.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/plus24x24.png")));
/* 208 */     
/* 209 */     this.jButtonNew.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 211 */             PanelListGateway.this.jButtonNewActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 215 */     this.jButtonDel.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/borrar.png")));
/* 216 */    
/* 217 */     this.jButtonDel.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 219 */             PanelListGateway.this.jButtonDelActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 223 */     this.jButtonSelect.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/select.png")));
/* 224 */    
/* 225 */     this.jButtonSelect.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 227 */             PanelListGateway.this.jButtonSelectActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 231 */     
/* 232 */     this.jCheckBoxGIoT.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 234 */             PanelListGateway.this.jCheckBoxGIoTActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 238 */     
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
/* 284 */     
/* 285 */    
/*     */     
/* 288 */     this.jTextlIdConnect.setText(bundle.getString("PanelListGateway.jTextlIdConnect.text"));
/* 289 */     this.jPanel2.add(this.jTextlIdConnect);
/*     */     
/* 291 */     
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
/* 305 */   
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
/* 333 */
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
/* 364 */    
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
/* 398 */     
/*     */     
/* 410 */    
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
/*     */     /*     */     
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