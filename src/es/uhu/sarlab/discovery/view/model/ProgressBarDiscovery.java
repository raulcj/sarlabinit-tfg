/*     */ package es.uhu.sarlab.discovery.view.model;
/*     */ 
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class ProgressBarDiscovery {
    
/*     */   private Label jLabelFase;
/*     */   public static ProgressBar progressBar;
 
/*     */   public ProgressBarDiscovery() {
/*  23 */     progressBar = new ProgressBar();
/*  59 */     this.jLabelFase = new Label();
/*  67 */     this.jLabelFase.setText("SARLAB: Find ....");
              Tooltip jLabelFaseTooltip = new Tooltip("");
              this.jLabelFase.setTooltip(jLabelFaseTooltip);
/*  25 */     progressBar.setProgress(0.05);
              //progressBar.setVisible(true);
              //this.jLabelFase.setVisible(true);
              // NO ES NECESARIO; POR DEFECTO ES INDETERMINADA
/*  26 */     //progressBar.setIndeterminate(true);
/*     */   }

            public Label getjLabelFase() {
                return jLabelFase;
            }

            public void setjLabelFase(Label jLabelFase) {
                this.jLabelFase = jLabelFase;
            }

            public static ProgressBar getProgressBar() {
                return progressBar;
            }

            public static void setProgressBar(ProgressBar progressBar) {
                ProgressBarDiscovery.progressBar = progressBar;
            }
/*     */   
/*     */   public void setVar(double var) {
/*  30 */     progressBar.setProgress(var);
/*     */   }
/*     */   
/*     */   public void setIndeterminate() {
/*  34 */     progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
/*     */   }
/*     */   
/*     */   public void setLabel(String text) {
/*  38 */     this.jLabelFase.setText(text);
/*     */   }

            public void setVisible(boolean value) {
                this.progressBar.setVisible(value);
            }

 }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\ProgressBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */