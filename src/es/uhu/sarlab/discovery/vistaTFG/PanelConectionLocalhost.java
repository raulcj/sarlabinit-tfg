/*     */ package es.uhu.sarlab.discovery.vistaTFG;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
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
/*     */ public class PanelConectionLocalhost
/*     */   extends JFrame
/*     */ {
/*     */   public String user;
/*     */   public String password;
/*  40 */   public String mode = "new";
/*     */   
/*     */   PanelClientServerName clientName;
/*     */   
/*     */   DefaultListModel modelHardware;
/*  45 */   int selectorListIdExpPowerDevice = -1;
/*  46 */   int selectorListHardware = -1; private JLabel LabelSARLAB; private JButton jButton2;
/*     */   private JLabel jLabel2;
/*     */   private JList jListHardware;
/*     */   private JPanel jPanel1;
/*     */   
/*     */   public PanelConectionLocalhost(PanelClientServerName clientName) {
/*  52 */     this.clientName = clientName;
/*  53 */     initComponents();
/*     */   }
/*     */   private JPanel jPanel2;
/*     */   private JPanel jPanel3;
/*     */   
/*     */   public void init(DefaultListModel modelHardware) {
/*  59 */     this.modelHardware = modelHardware;
/*     */   }
/*     */   private JPanel jPanel7; private JScrollPane jScrollPane2; private JLabel logo;
/*     */   
/*     */   public void set() {
/*  64 */     this.jListHardware.setModel(this.modelHardware);
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
/*     */   private void initComponents() {
/*  79 */     this.jPanel1 = new JPanel();
/*  80 */     this.LabelSARLAB = new JLabel();
/*  81 */     this.logo = new JLabel();
/*  82 */     this.jPanel2 = new JPanel();
/*  83 */     this.jPanel7 = new JPanel();
/*  84 */     this.jScrollPane2 = new JScrollPane();
/*  85 */     this.jListHardware = new JList();
/*  86 */     this.jLabel2 = new JLabel();
/*  87 */     this.jPanel3 = new JPanel();
/*  88 */     this.jButton2 = new JButton();
/*     */     
/*  90 */     this.jPanel1.setBackground(new Color(255, 204, 102));
/*  91 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder(""));
/*     */     
/*  93 */     this.LabelSARLAB.setFont(new Font("Tahoma", 1, 18));
/*  94 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelConectionLocalhost");
/*  95 */     this.LabelSARLAB.setText(bundle.getString("PanelConectionLocalhost.LabelSARLAB.text"));
/*     */     
/*  97 */     this.logo.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/Remote-Desktop64x64.png")));
/*     */     
/*  99 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 100 */     this.jPanel1.setLayout(jPanel1Layout);
/* 101 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/* 102 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 103 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 104 */           .addContainerGap()
/* 105 */           .addComponent(this.logo, -2, 74, -2)
/* 106 */           .addGap(102, 102, 102)
/* 107 */           .addComponent(this.LabelSARLAB, -2, 257, -2)
/* 108 */           .addContainerGap(156, 32767)));
/*     */     
/* 110 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 111 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 112 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
/* 113 */           .addContainerGap()
/* 114 */           .addComponent(this.logo, -1, -1, 32767))
/* 115 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 116 */           .addGap(26, 26, 26)
/* 117 */           .addComponent(this.LabelSARLAB)
/* 118 */           .addGap(0, 0, 32767)));
/*     */ 
/*     */     
/* 121 */     this.jPanel2.setBackground(new Color(200, 208, 250));
/* 122 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder(""));
/*     */     
/* 124 */     this.jPanel7.setBorder(BorderFactory.createTitledBorder(""));
/*     */     
/* 126 */     this.jListHardware.setFont(new Font("Courier New", 1, 12));
/* 127 */     this.jListHardware.setSelectionMode(0);
/* 128 */     this.jListHardware.addListSelectionListener(new ListSelectionListener() {
/*     */           public void valueChanged(ListSelectionEvent evt) {
/* 130 */             PanelConectionLocalhost.this.jListHardwareValueChanged(evt);
/*     */           }
/*     */         });
/* 133 */     this.jScrollPane2.setViewportView(this.jListHardware);
/*     */     
/* 135 */     GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
/* 136 */     this.jPanel7.setLayout(jPanel7Layout);
/* 137 */     jPanel7Layout.setHorizontalGroup(jPanel7Layout
/* 138 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 139 */         .addGroup(jPanel7Layout.createSequentialGroup()
/* 140 */           .addContainerGap()
/* 141 */           .addComponent(this.jScrollPane2)
/* 142 */           .addContainerGap()));
/*     */     
/* 144 */     jPanel7Layout.setVerticalGroup(jPanel7Layout
/* 145 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 146 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
/* 147 */           .addContainerGap(-1, 32767)
/* 148 */           .addComponent(this.jScrollPane2, -2, 141, -2)
/* 149 */           .addContainerGap()));
/*     */ 
/*     */     
/* 152 */     this.jLabel2.setFont(new Font("Tahoma", 1, 12));
/* 153 */     this.jLabel2.setHorizontalAlignment(0);
/* 154 */     this.jLabel2.setText(bundle.getString("PanelConectionLocalhost.jLabel2.text"));
/*     */     
/* 156 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 157 */     this.jPanel2.setLayout(jPanel2Layout);
/* 158 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout
/* 159 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 160 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 161 */           .addContainerGap()
/* 162 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 163 */             .addComponent(this.jPanel7, -1, -1, 32767)
/* 164 */             .addComponent(this.jLabel2, -1, -1, 32767))
/* 165 */           .addContainerGap()));
/*     */     
/* 167 */     jPanel2Layout.setVerticalGroup(jPanel2Layout
/* 168 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 169 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 170 */           .addGap(6, 6, 6)
/* 171 */           .addComponent(this.jLabel2)
/* 172 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 173 */           .addComponent(this.jPanel7, -2, -1, -2)
/* 174 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 177 */     this.jPanel3.setBackground(new Color(200, 208, 250));
/* 178 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder(""));
/*     */     
/* 180 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/cancel.png")));
/* 181 */     this.jButton2.setText(bundle.getString("PanelConectionLocalhost.jButton2.text"));
/* 182 */     this.jButton2.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 184 */             PanelConectionLocalhost.this.jButton2ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 188 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 189 */     this.jPanel3.setLayout(jPanel3Layout);
/* 190 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/* 191 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 192 */         .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
/* 193 */           .addContainerGap(-1, 32767)
/* 194 */           .addComponent(this.jButton2)
/* 195 */           .addGap(245, 245, 245)));
/*     */     
/* 197 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 198 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 199 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 200 */           .addContainerGap()
/* 201 */           .addComponent(this.jButton2)
/* 202 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 205 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 206 */     getContentPane().setLayout(layout);
/* 207 */     layout.setHorizontalGroup(layout
/* 208 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 209 */         .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 210 */           .addContainerGap()
/* 211 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 212 */             .addComponent(this.jPanel3, -1, -1, 32767)
/* 213 */             .addComponent(this.jPanel2, GroupLayout.Alignment.LEADING, -1, -1, 32767)
/* 214 */             .addComponent(this.jPanel1, -1, -1, 32767))
/* 215 */           .addContainerGap()));
/*     */     
/* 217 */     layout.setVerticalGroup(layout
/* 218 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 219 */         .addGroup(layout.createSequentialGroup()
/* 220 */           .addContainerGap()
/* 221 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 222 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 223 */           .addComponent(this.jPanel2, -2, -1, -2)
/* 224 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 225 */           .addComponent(this.jPanel3, -2, -1, -2)
/* 226 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 229 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   private void jListHardwareValueChanged(ListSelectionEvent evt) {
/* 234 */     this.selectorListHardware = this.jListHardware.getSelectedIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getIconImage() {
/* 240 */     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("es/uhu/sarlab/discovery/vista/resources/Remote-Desktop32x32.png"));
/* 241 */     return retValue;
/*     */   }
/*     */   
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 245 */     setVisible(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 252 */     Locale.setDefault(new Locale("en", "EN"));
/* 253 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 255 */             PanelConectionLocalhost panelConectionLocalhost = new PanelConectionLocalhost(null);
/* 256 */             panelConectionLocalhost.setVisible(true);
/* 257 */             DefaultListModel<String> modelo = new DefaultListModel();
/* 258 */             modelo.addElement("Marco");
/* 259 */             panelConectionLocalhost.init(modelo);
/* 260 */             panelConectionLocalhost.set();
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelConectionLocalhost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */