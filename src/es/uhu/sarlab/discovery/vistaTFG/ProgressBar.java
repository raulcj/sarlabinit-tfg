/*     */ package es.uhu.sarlab.discovery.vistaTFG;
/*     */ 
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class ProgressBar extends JFrame {
/*     */   int var;
/*     */   private JLabel jLabelFase;
/*     */   public static JProgressBar progressBar;
/*     */   
/*     */   public ProgressBar() {
/*  23 */     initComponents();
/*  24 */     setLocationRelativeTo(null);
/*  25 */     progressBar.setValue(5);
/*  26 */     progressBar.setIndeterminate(true);
/*     */   }
/*     */   
/*     */   public void setVar(int var) {
/*  30 */     progressBar.setValue(var);
/*     */   }
/*     */   
/*     */   public void setIndeterminate(boolean var) {
/*  34 */     progressBar.setIndeterminate(var);
/*     */   }
/*     */   
/*     */   public void setLabel(String text) {
/*  38 */     this.jLabelFase.setText(text);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getIconImage() {
/*  44 */     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("es/uhu/sarlab/discovery/vista/resources/SARLAB32.png"));
/*  45 */     return retValue;
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
/*     */   private void initComponents() {
/*  58 */     progressBar = new JProgressBar();
/*  59 */     this.jLabelFase = new JLabel();
/*     */     
/*  61 */     setIconImage(getIconImage());
/*     */     
/*  63 */     progressBar.setStringPainted(true);
/*     */     
/*  65 */     this.jLabelFase.setFont(new Font("Tahoma", 1, 11));
/*  66 */     this.jLabelFase.setHorizontalAlignment(0);
/*  67 */     this.jLabelFase.setText("SARLAB: Find ....");
/*  68 */     this.jLabelFase.setToolTipText("");
/*     */     
/*  70 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  71 */     getContentPane().setLayout(layout);
/*  72 */     layout.setHorizontalGroup(layout
/*  73 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  74 */         .addGroup(layout.createSequentialGroup()
/*  75 */           .addContainerGap()
/*  76 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  77 */             .addComponent(this.jLabelFase, -1, 380, 32767)
/*  78 */             .addComponent(progressBar, -1, 380, 32767))
/*  79 */           .addContainerGap()));
/*     */     
/*  81 */     layout.setVerticalGroup(layout
/*  82 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  83 */         .addGroup(layout.createSequentialGroup()
/*  84 */           .addComponent(this.jLabelFase)
/*  85 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  86 */           .addComponent(progressBar, -2, 41, -2)
/*  87 */           .addContainerGap(-1, 32767)));
/*     */ 
/*     */     
/*  90 */     pack();
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
/*     */   public static void main(String[] args) {
/*     */     try {
/* 103 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 104 */         if ("Nimbus".equals(info.getName())) {
/* 105 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 109 */     } catch (ClassNotFoundException ex) {
/* 110 */       Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 111 */     } catch (InstantiationException ex) {
/* 112 */       Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 113 */     } catch (IllegalAccessException ex) {
/* 114 */       Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 115 */     } catch (UnsupportedLookAndFeelException ex) {
/* 116 */       Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 126 */             ProgressBar progresBar = new ProgressBar();
/* 127 */             progresBar.setVisible(true);
/* 128 */             progresBar.setVar(50);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\ProgressBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */