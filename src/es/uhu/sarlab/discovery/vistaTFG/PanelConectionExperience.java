/*     */ package es.uhu.sarlab.discovery.vistaTFG;
/*     */ 
/*     */ import es.uhu.sarlab.discovery.GetParameters.GetParameters;
/*     */ import es.uhu.sarlab.discovery.model.RegisterGateway;
/*     */ import es.uhu.sarlab.discovery.webservlet.model.HashMapExperienceWeb;
/*     */ import es.uhu.sarlab.discovery.webservlet.model.ListForwardingL;
/*     */ import java.awt.Color;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Set;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
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
/*     */ public class PanelConectionExperience
/*     */   extends JFrame
/*     */ {
/*  44 */   public String mode = "new";
/*     */   
/*     */   PanelClientServerName clientName;
/*     */   DefaultListModel modelExperience;
/*     */   HashMapExperienceWeb hashMapExperienceWeb;
/*     */   RegisterGateway registerGatewayOld;
/*  50 */   String myirtualFrontierOld = null;
/*  51 */   String myURIMyGatewayOld = null;
/*     */   
/*  53 */   int selectorListExperience = -1; String url; private JLabel LabelSARLAB; private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel2;
/*     */   private JList jListHardware;
/*     */   
/*     */   public PanelConectionExperience(PanelClientServerName clientName) {
/*  59 */     this.clientName = clientName;
/*  60 */     initComponents();
/*  61 */     setLocationRelativeTo(null);
/*     */   }
/*     */   private JPanel jPanel1; private JPanel jPanel2; private JPanel jPanel3; private JPanel jPanel7; private JScrollPane jScrollPane2; private JLabel logo;
/*     */   
/*     */   public void init(DefaultListModel modelHardware) {
/*  66 */     this.modelExperience = modelHardware;
/*  67 */     this.jListHardware.setModel(this.modelExperience);
/*     */   }
/*     */   
/*     */   public void setModel(HashMapExperienceWeb hashMapExperienceWeb) {
/*  71 */     this.hashMapExperienceWeb = hashMapExperienceWeb;
/*  72 */     this.modelExperience = new DefaultListModel();
/*  73 */     Set<String> st = this.hashMapExperienceWeb.getDirectory().keySet();
/*  74 */     ArrayList<String> ar = new ArrayList<>(st);
/*  75 */     for (String s : ar) this.modelExperience.addElement(s); 
/*  76 */     this.jListHardware.setModel(this.modelExperience);
/*     */   }
/*     */   
/*     */   public void setUrl(String url) {
/*  80 */     this.url = url;
/*     */   }
/*     */   
/*     */   public void setRegisterGateway(RegisterGateway registerGateway) {
/*  84 */     this.registerGatewayOld = registerGateway;
/*     */   }
/*     */   
/*     */   public void setMyirtualFrontierOld(String myirtualFrontierOld) {
/*  88 */     this.myirtualFrontierOld = myirtualFrontierOld;
/*     */   }
/*     */   
/*     */   public void setMyURIMyGatewayOld(String myURIMyGatewayOld) {
/*  92 */     this.myURIMyGatewayOld = myURIMyGatewayOld;
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
/*     */   private void initComponents() {
/* 104 */     this.jPanel1 = new JPanel();
/* 105 */     this.LabelSARLAB = new JLabel();
/* 106 */     this.logo = new JLabel();
/* 107 */     this.jPanel2 = new JPanel();
/* 108 */     this.jPanel7 = new JPanel();
/* 109 */     this.jScrollPane2 = new JScrollPane();
/* 110 */     this.jListHardware = new JList();
/* 111 */     this.jLabel2 = new JLabel();
/* 112 */     this.jPanel3 = new JPanel();
/* 113 */     this.jButton1 = new JButton();
/* 114 */     this.jButton2 = new JButton();
/*     */     
/* 116 */     setIconImage(getIconImage());
/*     */     
/* 118 */     this.jPanel1.setBackground(new Color(255, 204, 102));
/* 119 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(""));
/*     */     
/* 121 */     this.LabelSARLAB.setFont(new Font("Tahoma", 1, 18));
/* 122 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelConectionExperience");
/* 123 */     this.LabelSARLAB.setText(bundle.getString("PanelConectionExperience.LabelSARLAB.text"));
/*     */     
/* 125 */     this.logo.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/Remote-Desktop64x64.png")));
/*     */     
/* 127 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 128 */     this.jPanel1.setLayout(jPanel1Layout);
/* 129 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 130 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 131 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 132 */           .addContainerGap()
/* 133 */           .addComponent(this.logo, -2, 74, -2)
/* 134 */           .addGap(18, 18, 18)
/* 135 */           .addComponent(this.LabelSARLAB, -1, 340, 32767)
/* 136 */           .addGap(48, 48, 48)));
/*     */     
/* 138 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 139 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 140 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
/* 141 */           .addContainerGap()
/* 142 */           .addComponent(this.logo, -1, 66, 32767))
/* 143 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 144 */           .addGap(26, 26, 26)
/* 145 */           .addComponent(this.LabelSARLAB)
/* 146 */           .addGap(0, 0, 32767)));
/*     */ 
/*     */     
/* 149 */     this.jPanel2.setBackground(new Color(200, 208, 250));
/* 150 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder(""));
/*     */     
/* 152 */     this.jPanel7.setBorder(BorderFactory.createTitledBorder(""));
/*     */     
/* 154 */     this.jListHardware.setFont(new Font("Courier New", 1, 12));
/* 155 */     this.jListHardware.setSelectionMode(0);
/* 156 */     this.jListHardware.addListSelectionListener(new ListSelectionListener() {
/*     */           public void valueChanged(ListSelectionEvent evt) {
/* 158 */             PanelConectionExperience.this.jListHardwareValueChanged(evt);
/*     */           }
/*     */         });
/* 161 */     this.jScrollPane2.setViewportView(this.jListHardware);
/*     */     
/* 163 */     GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
/* 164 */     this.jPanel7.setLayout(jPanel7Layout);
/* 165 */     jPanel7Layout.setHorizontalGroup(jPanel7Layout
/* 166 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 167 */         .addGroup(jPanel7Layout.createSequentialGroup()
/* 168 */           .addContainerGap()
/* 169 */           .addComponent(this.jScrollPane2)
/* 170 */           .addContainerGap()));
/*     */     
/* 172 */     jPanel7Layout.setVerticalGroup(jPanel7Layout
/* 173 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 174 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
/* 175 */           .addContainerGap(-1, 32767)
/* 176 */           .addComponent(this.jScrollPane2, -2, 141, -2)
/* 177 */           .addContainerGap()));
/*     */ 
/*     */     
/* 180 */     this.jLabel2.setFont(new Font("Tahoma", 1, 12));
/* 181 */     this.jLabel2.setHorizontalAlignment(0);
/* 182 */     this.jLabel2.setText(bundle.getString("PanelConectionExperience.jLabel2.text"));
/*     */     
/* 184 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 185 */     this.jPanel2.setLayout(jPanel2Layout);
/* 186 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout
/* 187 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 188 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
/* 189 */           .addContainerGap()
/* 190 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 191 */             .addComponent(this.jLabel2, -1, -1, 32767)
/* 192 */             .addComponent(this.jPanel7, -1, -1, 32767))
/* 193 */           .addContainerGap()));
/*     */     
/* 195 */     jPanel2Layout.setVerticalGroup(jPanel2Layout
/* 196 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 197 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 198 */           .addContainerGap()
/* 199 */           .addComponent(this.jLabel2)
/* 200 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 201 */           .addComponent(this.jPanel7, -2, -1, -2)
/* 202 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 205 */     this.jPanel3.setBackground(new Color(200, 208, 250));
/* 206 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder(""));
/*     */     
/* 208 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/select2.png")));
/* 209 */     this.jButton1.setText(bundle.getString("PanelConectionExperience.jButton1.text"));
/* 210 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 212 */             PanelConectionExperience.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 216 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/cancel.png")));
/* 217 */     this.jButton2.setText(bundle.getString("PanelConectionExperience.jButton2.text"));
/* 218 */     this.jButton2.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 220 */             PanelConectionExperience.this.jButton2ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 224 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 225 */     this.jPanel3.setLayout(jPanel3Layout);
/* 226 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/* 227 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 228 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 229 */           .addGap(105, 105, 105)
/* 230 */           .addComponent(this.jButton1, -2, 116, -2)
/* 231 */           .addGap(18, 18, 18)
/* 232 */           .addComponent(this.jButton2)
/* 233 */           .addContainerGap(-1, 32767)));
/*     */     
/* 235 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 236 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 237 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
/* 238 */           .addContainerGap(-1, 32767)
/* 239 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 240 */             .addComponent(this.jButton1)
/* 241 */             .addComponent(this.jButton2))
/* 242 */           .addContainerGap()));
/*     */ 
/*     */     
/* 245 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 246 */     getContentPane().setLayout(layout);
/* 247 */     layout.setHorizontalGroup(layout
/* 248 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 249 */         .addGroup(layout.createSequentialGroup()
/* 250 */           .addContainerGap()
/* 251 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 252 */             .addComponent(this.jPanel2, -1, -1, 32767)
/* 253 */             .addComponent(this.jPanel1, -1, -1, 32767)
/* 254 */             .addComponent(this.jPanel3, -1, -1, 32767))
/* 255 */           .addContainerGap(-1, 32767)));
/*     */     
/* 257 */     layout.setVerticalGroup(layout
/* 258 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 259 */         .addGroup(layout.createSequentialGroup()
/* 260 */           .addGap(6, 6, 6)
/* 261 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 262 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 263 */           .addComponent(this.jPanel2, -2, -1, -2)
/* 264 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 265 */           .addComponent(this.jPanel3, -2, -1, -2)
/* 266 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 269 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   private void jListHardwareValueChanged(ListSelectionEvent evt) {
/* 274 */     this.selectorListExperience = this.jListHardware.getSelectedIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 279 */     if (this.selectorListExperience != -1) {
/* 280 */       String s = (String) this.modelExperience.get(this.selectorListExperience);
/* 281 */       System.out.println(s);
/* 282 */       String nameFile = (String)this.hashMapExperienceWeb.getDirectory().get(s);
/* 283 */       System.out.println(nameFile);
/* 284 */       ListForwardingL listForwardingL = new ListForwardingL();
/* 285 */       GetParameters getParameters = new GetParameters();
/* 286 */       listForwardingL = getParameters.getListForwardingL(this.registerGatewayOld, nameFile);
/* 287 */       if (listForwardingL != null)
/*     */       {
/* 289 */         this.clientName.addForwardingExperienceLocalhostL(listForwardingL);
/*     */       }
/* 291 */       setVisible(false);
/*     */     } else {
/* 293 */       JOptionPane.showMessageDialog(null, "Debe seleccionar una experiencia");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getIconImage() {
/* 301 */     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("es/uhu/sarlab/discovery/vista/resources/Remote-Desktop32x32.png"));
/* 302 */     return retValue;
/*     */   }
/*     */ 
/*     */   
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 307 */     setVisible(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 315 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 317 */             PanelConectionExperience panelConectionLocalhost = new PanelConectionExperience(null);
/* 318 */             panelConectionLocalhost.setVisible(true);
/* 319 */             DefaultListModel<String> modelo = new DefaultListModel();
/* 320 */             modelo.addElement("Marco");
/* 321 */             panelConectionLocalhost.init(modelo);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelConectionExperience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */