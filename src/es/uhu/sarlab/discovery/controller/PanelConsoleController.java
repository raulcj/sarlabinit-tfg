package es.uhu.sarlab.discovery.controller;

import es.nebsyst.sarlabinit.main.Main;
/*     */ import es.uhu.sarlab.utils.BoardListener;
import java.awt.EventQueue;
/*     */ import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Timer;
import java.util.TimerTask;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*     */ import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class PanelConsoleController implements Initializable {
/*  21 */   Timer timer = null; 
/*  22 */   TimerTask timerTask = null;
/*  23 */   BoardListener boardListener = new BoardListener();
            @FXML
            private Button jButton1; // boton Actualizar
            @FXML
            private Button jButton2; // boton Copiar
            @FXML
            private Button jButton3; // boton Actualizar
            @FXML
/*     */   private CheckBox jCheckBox1;
            @FXML
/*     */   private ScrollPane jScrollPane1;
            @FXML
/*     */   private TextArea jTextConsole;
            
            //private static Stage panelConsoleStage;
            
            private PanelClientServerNameController panelClientServerNameController;
            private Stage panelConsoleStage;
            //private Scene panelConsoleScene;
            
            private int i = 0;
        
            private void initPanelConsole() throws IOException {
              //this.panelConsoleStage = Main.getPanelConsoleStage(); 
              
              // PRUEBA
              //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/PanelConsole.fxml"));
              //panelConsoleScene = new Scene(fxmlLoader.load());
              //panelConsoleStage.setScene(panelConsoleScene);
              FXMLLoader fxmlLoader = Main.getDiscoveryLoader();
              fxmlLoader.load();     
              this.panelClientServerNameController = fxmlLoader.getController();
              
              // FIN PRUEBA
/*  29 */     this.timer = new Timer(true);
/*  30 */     this.timerTask = new MyTimerTask();
              startTimer();
/*  32 */     initComponents();
/*     */   }
/*     */   
            public TextArea getJTextConsole() {
                return jTextConsole;
            }
            
            public void setStage(Stage stage) {
                if (panelConsoleStage == null)
                    this.panelConsoleStage = stage;
            }
            
/*     */   public void setVisible(boolean b) {
/*  45 */     this.jCheckBox1.setSelected(true);
/*  46 */     this.jCheckBox1.setText("Stop");
/*  47 */     //this.panelConsoleStage.show();
/*     */   }
/*     */  
/*     */   public void startTimer() {
              Platform.runLater(() -> {
    /*  51 */     this.timerTask = new MyTimerTask();   
    /*  53 */     this.timer.scheduleAtFixedRate(this.timerTask, 0L, 1000L);     
/*     */     });
            }

/*     */   public void stopTimer() {
              //Platform.runLater(() -> {
    /*  58 */     if (this.jTextConsole != null) {     

    /*  59 */       this.jTextConsole.setText(panelClientServerNameController.baos.toString());
    /*     */     }
    /*  61 */     this.timerTask.cancel();
              //});
/*     */   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Platform.runLater(() -> {
             try {
                initPanelConsole();   

                EventQueue.invokeLater(() -> {
                    setVisible(true);
                });
                if (panelConsoleStage != null) {
                    this.panelConsoleStage.setOnCloseRequest( e -> {
                    System.out.println("*******Close console *********");
                    if (this.timer != null)
                        stopTimer(); 
                    }
                );
                }
                     
                
            } catch (IOException ex) {
                Logger.getLogger(PanelConsoleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        //});        
    }
    
 public class MyTimerTask
/*     */     extends TimerTask
/*     */   {
/*     */       @Override
                public void run() {
                //Platform.runLater(() -> {
                   if (PanelConsoleController.this.jTextConsole != null) {  
                        if (panelClientServerNameController != null) {
                            getJTextConsole().setPromptText(panelClientServerNameController.baos.toString());
                        } else {
                            i++;
                        }        
                       
    /*     */       } else {
    /*  74 */         stopTimer();
    /*     */       }
 
                //});  
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initComponents() {
/*  88 */     this.jScrollPane1 = new ScrollPane();
/*  89 */     this.jTextConsole = new TextArea();
/*  90 */     this.jButton1 = new Button();
/*  91 */     this.jCheckBox1 = new CheckBox();
/*  92 */     this.jButton2 = new Button();
/*  93 */     this.jButton3 = new Button();
/* 105 */     //this.jTextConsole.setColumns(20);
/* 106 */     //this.jTextConsole.setRows(5);
/* 107 */     this.jScrollPane1.setContent(this.jTextConsole);
              
              Image iconJButton1 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/actualiza.png").toExternalForm());
              ImageView iconViewJButton1 = new ImageView(iconJButton1);
              this.jButton1.setGraphic(iconViewJButton1);  
              
/* 110 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelConsole");
/* 111 */     this.jButton1.setText(bundle.getString("PanelConsole.jButton1.text"));
/*     */     
/* 118 */     this.jCheckBox1.setText(bundle.getString("PanelConsole.jCheckBox1.text"));
/* 119 */      
              Image iconJButton2 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/edit_copy.png").toExternalForm());
              ImageView iconViewJButton2 = new ImageView(iconJButton2);
              this.jButton2.setGraphic(iconViewJButton2); 
/* 126 */     this.jButton2.setText(bundle.getString("PanelConsole.jButton2.text"));
   
/*     */     Image iconJButton3 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/borrar.png").toExternalForm());
              ImageView iconViewJButton3 = new ImageView(iconJButton3);
              this.jButton3.setGraphic(iconViewJButton3); 
/* 137 */     this.jButton3.setText(bundle.getString("PanelConsole.jButton3.text"));

/*     */   }
/*     */ 
/*     */   @FXML
/*     */   void jButton1ActionPerformed() throws IOException {  
             
             
              this.jTextConsole.setText(panelClientServerNameController.baos.toString());
/* 184 */     //this.jTextConsole.setText(PanelClientServerNameController.getBaos().toString());
/*     */   }
            
            // Devuelve el documento FXML de la clase PanelClientServerNameController
            private FXMLLoader getParent() {
              FXMLLoader fxmlLoader = new FXMLLoader(PanelConsoleController.class.getResource("../view/PanelClientServerName.fxml"));
             
              //Parent PanelClientServerNameParent = loader.load();
              //Scene PanelClientServerNameScene = new Scene(PanelClientServerNameParent);
              // Access the controller
              //PanelClientServerNameController controller = loader.getController();     
              return fxmlLoader;
            }
            
            public void setControllerPanelClientServerName(PanelClientServerNameController panelClientServerNameController) {
                this.panelClientServerNameController = panelClientServerNameController;
            }
            
            
/*     */   private void formWindowClosed(WindowEvent evt) {}

/*     */   private void formWindowClosing(WindowEvent evt) {
/* 195 */     System.out.println("*******Close console *********");
/* 196 */     if (this.timer != null)
/* 197 */       stopTimer(); 
/*     */   }
/*     */   
            @FXML
/*     */   void jCheckBox1ActionPerformed() {
/* 201 */     if (this.jCheckBox1.isSelected()) {
/* 202 */       this.jCheckBox1.setText("Stop");
/* 203 */       startTimer();
/*     */     } else {
/* 205 */       this.jCheckBox1.setText("Start");
/* 206 */       System.out.println("TimerTask stop");   
/* 207 */       this.jTextConsole.setText(panelClientServerNameController.baos.toString());
/* 208 */       stopTimer();
/*     */     } 
/*     */   }
/*     */  
            @FXML
/*     */   void jButton2ActionPerformed() {      
/* 213 */     this.boardListener.setClipboard(panelClientServerNameController.baos.toString());
/*     */   }
/*     */   
            @FXML
/*     */   void jButton3ActionPerformed() {
/* 217 */     panelClientServerNameController.baos.reset();
/* 218 */     this.jTextConsole.setText(panelClientServerNameController.baos.toString());
/*     */   }

}




/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelConsole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
