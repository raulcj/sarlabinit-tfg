/*    */ package es.uhu.sarlab.discovery.vistaTFG;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.FlowLayout;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.JProgressBar;
/*    */ 
/*    */ class BarraDeProgreso extends JDialog {
/*    */   private Thread hilo;
/*    */   private JProgressBar barra;
/* 15 */   private int totalDeLaBarra = 2000;
/* 16 */   private int velocidad = 1;
/* 17 */   private int limi = 500;
/*    */   
/*    */   BarraDeProgreso() {
/* 20 */     this.hilo = new Thread(this::contador);
/* 21 */     this.barra = new JProgressBar(0, this.totalDeLaBarra);
/* 22 */     this.barra.setValue(0);
/* 23 */     add(this.barra);
/* 24 */     setLayout(new FlowLayout());
/* 25 */     setLocationRelativeTo((Component)null);
/* 26 */     setSize(new Dimension(200, 300));
/* 27 */     setModal(true);
/* 28 */     setDefaultCloseOperation(0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVisible(boolean b) {
/* 34 */     this.hilo.start();
/* 35 */     super.setVisible(b);
/*    */   }
/*    */   
/*    */   public void contador() {
/* 39 */     int contador = 0;
/* 40 */     while (contador <= this.totalDeLaBarra + 1) {
/*    */       try {
/* 42 */         this.barra.setValue(contador);
/* 43 */         Thread.sleep(this.velocidad);
/* 44 */         contador++;
/*    */       }
/* 46 */       catch (InterruptedException ex) {
/* 47 */         Logger.getLogger(BarraDeProgreso.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */       } 
/*    */     } 
/* 50 */     JOptionPane.showMessageDialog(null, "Se va a cerrar la barra!");
/* 51 */     dispose();
/*    */   }
/*    */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\BarraDeProgreso.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */