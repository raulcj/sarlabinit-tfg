package es.uhu.sarlab.discovery.controller;

import es.nebsyst.sarlabinit.main.Main;
import es.uhu.sarlab.utils.BoardListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/*     */  /**
 * 
 *  TO-DO: El metodo startTimer() debería ejecutarse cada vez que se abre la ventana
 *  como consecuencia: deberia aparecer el mensaje "TimerTask start" en pantalla
 * 
 * @author alumno
 */
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
                       
            private PanelClientServerNameController panelClientServerNameController;
            private Stage panelConsoleStage;

            private void initPanelConsole() throws IOException {
              FXMLLoader fxmlLoader = Main.getDiscoveryLoader();
              fxmlLoader.load();     
              this.panelClientServerNameController = fxmlLoader.getController();
              this.panelConsoleStage = Main.getPanelConsoleStage();
              this.timer = new Timer(true);  
/*  30 */     this.timerTask = new MyTimerTask();
              startTimer();
/*  32 */     initComponents();
              // Evento en cierre
              this.panelConsoleStage.setOnCloseRequest( e -> {
                  System.out.println("*******Close console *********");
                  if (this.timer != null)
                      stopTimer(); 
              });          
/*     */   }
/*     */   
            public TextArea getJTextConsole() {
                return jTextConsole;
            }
                
/*     */   public void setVisible(boolean b) {   
                this.jCheckBox1.setSelected(true);
/*  46 */       this.jCheckBox1.setText("Stop");
/*     */   }
/*     */  
/*     */   public void startTimer() {
    /*  51 */ this.timerTask = new MyTimerTask(); 
              //if (timer == null) {
                this.timer = new Timer(true);
              //}
    /*  53 */ this.timer.scheduleAtFixedRate(this.timerTask, 0L, 1000L);
              System.out.println("TimerTask start");   
            }

/*     */   public void stopTimer() {
                if (this.jTextConsole != null) {     
                    this.jTextConsole.setText(panelClientServerNameController.baos.toString());
                }
                this.timerTask.cancel();
/*     */   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
             try {
                initPanelConsole();   
                setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(PanelConsoleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });                 
    }
    
 public class MyTimerTask
/*     */     extends TimerTask
/*     */   {
/*     */       @Override
                public void run() {
                Platform.runLater(() -> {
                    if (PanelConsoleController.this.jTextConsole != null) {                    
                        getJTextConsole().setText(panelClientServerNameController.baos.toString());
        /*     */   } else { 
                        stopTimer();
        /*     */   }
                });        
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initComponents() {
        
/* 107 */     this.jScrollPane1.setContent(this.jTextConsole);
              
              Image iconJButton1 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/actualiza.png").toExternalForm());
              ImageView iconViewJButton1 = new ImageView(iconJButton1);
              this.jButton1.setGraphic(iconViewJButton1);  
              
/* 110 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelConsole");
/* 111 */     this.jButton1.setText(bundle.getString("PanelConsole.jButton1.text"));
/*     */     
/* 118 */     this.jCheckBox1.setText(bundle.getString("PanelConsole.jCheckBox1.text"));
/* 119 */     // Path del recurso imagen icono modificado a path local del proyecto (y no de la libreria) porque se añadio icono nuevo "edit_copy2.png"
              Image iconJButton2 = new Image(getClass().getResource("/es/uhu/sarlab/discovery/view/resources/edit_copy2.png").toExternalForm());
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
/*     */   }

            @FXML
/*     */   void jCheckBox1ActionPerformed() {
/* 201 */     if (this.jCheckBox1.isSelected()) {
/* 202 */       this.jCheckBox1.setText("Stop");
/* 203 */       startTimer();
/*     */     } else {
/* 205 */       this.jCheckBox1.setText("Start"); 
                System.out.println("TimerTask stop");
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
