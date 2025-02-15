/*     */ package es.uhu.sarlab.discovery.vistaTFG;
/*     */ 
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.Timer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BarLogo
/*     */   extends JFrame
/*     */ {
/*     */   Timer timer;
/*     */   ActionListener ac;
/*     */   InterfazBarLogo clientName;
/*     */   boolean done;
/*     */   boolean end = false;
/*     */   public boolean slow = false;
/*  49 */   public String urlLogo = "http://150.214.163.142/logoSARLAB.png";
/*  50 */   public String pathLogo = "_ejs_library/resources/logoSARLAB.png";
/*  51 */   public String urlIcon = "http://150.214.163.142/SARLAB32.png";
/*  52 */   public String pathIcon = "_ejs_library/resources/SARLAB32.png";
/*  53 */   int val = 0;
/*     */   
/*     */   private JLabel jLabelFase;
/*     */   public static JProgressBar progressBar;
/*     */   
/*     */   public BarLogo(final InterfazBarLogo clientName) {
/*  59 */     this.clientName = clientName;
/*  60 */     initComponents();
/*     */ 
/*     */     
/*  63 */     setLocationRelativeTo(null);
/*  64 */     this.ac = new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/*  67 */           BarLogo.progressBar.setValue(BarLogo.this.val);
/*  68 */           BarLogo.this.val++;
/*  69 */           if (BarLogo.this.val > 110) {
/*  70 */             BarLogo.this.timer.stop();
/*  71 */             BarLogo.this.setVisible(false);
/*  72 */             clientName.endBarLogo();
/*     */           } 
/*     */         }
/*     */       };
/*  76 */     this.timer = new Timer(10, this.ac);
/*     */ 
/*     */     
/*  79 */     this.end = false;
/*     */   }
/*     */   
/*     */   public void init() {
/*  83 */     this.val = 0;
/*  84 */     progressBar.setValue(this.val);
/*  85 */     setVisible(true);
/*     */     
/*  87 */     this.timer.start();
/*  88 */     this.clientName.initBarLogo();
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
/*     */   public void iconLoad() {
/* 104 */     URL url = ClassLoader.getSystemResource(this.pathIcon);
/*     */     
/* 106 */     if (url == null) {
/*     */       try {
/* 108 */         url = new URL(this.urlIcon);
/* 109 */       } catch (MalformedURLException ex) {
/* 110 */         url = null;
/*     */       } 
/*     */     }
/* 113 */     if (url != null) {
/* 114 */       Image retValue = Toolkit.getDefaultToolkit().getImage(url);
/*     */       
/* 116 */       setIconImage(retValue);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logoLoad() {
/* 124 */     URL url = ClassLoader.getSystemResource(this.pathLogo);
/* 125 */     if (url == null) {
/*     */       try {
/* 127 */         url = new URL(this.urlLogo);
/* 128 */       } catch (MalformedURLException ex) {
/* 129 */         url = null;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 134 */     if (url != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getIconImage() {
/* 144 */     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("es/uhu/sarlab/discovery/vista/resources/SARLAB32.png"));
/* 145 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDone() {
/* 153 */     return this.done;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStatus(String stat) {
/* 158 */     this.jLabelFase.setText(stat);
/*     */   }
/*     */   
/*     */   public void setEnd(boolean end) {
/* 162 */     this.end = end;
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
/* 177 */     progressBar = new JProgressBar();
/* 178 */     this.jLabelFase = new JLabel();
/*     */     
/* 180 */     setDefaultCloseOperation(0);
/* 181 */     setTitle("Search systems");
/* 182 */     setIconImage(getIconImage());
/*     */     
/* 184 */     progressBar.setStringPainted(true);
/*     */     
/* 186 */     this.jLabelFase.setFont(new Font("Tahoma", 1, 11));
/* 187 */     this.jLabelFase.setHorizontalAlignment(0);
/* 188 */     this.jLabelFase.setText("Discovery: Find ....");
/* 189 */     this.jLabelFase.setToolTipText("");
/*     */     
/* 191 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 192 */     getContentPane().setLayout(layout);
/* 193 */     layout.setHorizontalGroup(layout
/* 194 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 195 */         .addGroup(layout.createSequentialGroup()
/* 196 */           .addContainerGap()
/* 197 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 198 */             .addComponent(this.jLabelFase, -1, 383, 32767)
/* 199 */             .addComponent(progressBar, -1, 383, 32767))
/* 200 */           .addContainerGap()));
/*     */     
/* 202 */     layout.setVerticalGroup(layout
/* 203 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 204 */         .addGroup(layout.createSequentialGroup()
/* 205 */           .addComponent(this.jLabelFase)
/* 206 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 207 */           .addComponent(progressBar, -2, 41, -2)
/* 208 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/* 211 */     pack();
/*     */   }
/*     */   
/*     */   public void delay(int ret) {
/*     */     try {
/* 216 */       Thread.sleep(ret);
/* 217 */     } catch (InterruptedException interruptedException) {}
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
/*     */   public static void main(String[] args) {
/* 231 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 233 */             BarLogo bl = new BarLogo(null);
/* 234 */             bl.setVisible(true);
/* 235 */             bl.init();
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\BarLogo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */