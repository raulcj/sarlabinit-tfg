/*     */ package es.uhu.sarlab.discovery.controller;
/*     */ 
import es.uhu.sarlab.discovery.view.model.ProgressBarDiscovery;
import static es.uhu.sarlab.discovery.view.model.ProgressBarDiscovery.progressBar;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class ProgressBarDiscoveryController implements Initializable {

/*     */   public static ProgressBarDiscovery progressBarDiscovery;

            @FXML
            private StackPane stackPaneProgressBar;

/*     */   public void initProgressBar() {
               progressBarDiscovery = new ProgressBarDiscovery();
               stackPaneProgressBar = new StackPane(ProgressBarDiscovery.getProgressBar(),progressBarDiscovery.getjLabelFase());    
               //progressBar.setVisible(true);
               //progressBarDiscovery.getjLabelFase().setVisible(true);
               
/*     */   }

            @Override
            public void initialize(URL url, ResourceBundle rb) {
                initProgressBar();
                //stackPaneProgressBar.setDisable(false);
            }
 }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\ProgressBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */