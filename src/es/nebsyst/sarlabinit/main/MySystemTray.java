/*
 * 
 */
package es.nebsyst.sarlabinit.main;

import es.uhu.sarlab.discovery.vistaTFG.PanelClientServerName;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 */
public class MySystemTray {

    private Stage restoreStage;
    private Stage discoveryStage;
    private Stage progressBarStage;
    //private PanelClientServerName clientName
    private PopupMenu popup = new PopupMenu();
    private final Image image = new ImageIcon(getClass().getResource("/es/nebsyst/sarlabinit/resource/execjar128.png")).getImage();
    private final TrayIcon trayIcon = new TrayIcon(image, "Servicio SARLAB-TFG", popup);
    //para el Timer
    private Timer timer;

    /**
     * Constructor de clase
     *
     * @param stage
     * @param stage
     */
    public MySystemTray(Stage restoreStage, Stage discoveryStage, Stage progressBarStage) {
        
        this.restoreStage = restoreStage;
        this.discoveryStage = discoveryStage;
        this.progressBarStage = progressBarStage;
        //comprueba si SystemTray es soportado en el sistema
        if (SystemTray.isSupported()) {
            //obtiene instancia SystemTray
            SystemTray systemtray = SystemTray.getSystemTray();
            trayIcon.setImageAutoSize(true);

            //acciones del raton sobre el icono en la barra de tareas
            MouseListener mouseListener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent evt) {

                    //Si se presiona con el boton izquierdo en el icono
                    //y la aplicacion esta minimizada se muestra una frase
                    if (evt.getButton() == MouseEvent.BUTTON1 && restoreStage.isIconified()) {
                        MensajeTrayIcon("Para acceder al Menú debe pulse el botón derecho del ratón", MessageType.INFO);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent evt) {/*nada x aqui circulen...*/
                }

                @Override
                public void mouseExited(MouseEvent evt) {/*nada x aqui circulen...*/
                }

                @Override
                public void mousePressed(MouseEvent evt) {/*nada x aqui circulen...*/
                }

                @Override
                public void mouseReleased(MouseEvent evt) {/*nada x aqui circulen...*/
                }
            };

            /* ----------------- ACCIONES DEL MENU POPUP --------------------- */
            //Salir de aplicacion
            ActionListener exitListener = (ActionEvent e) -> {
                System.exit(0);
            };

            //Restaurar aplicacion
            ActionListener restoreListener = e -> Platform.runLater(() -> {
                this.restoreStage.show();
                //si esta minimizado restaura JFrame
                if (this.restoreStage.isIconified()) {
                    this.restoreStage.show();
                    //this.restoreStage.setExtendedState(JFrame.NORMAL);
                    this.restoreStage.setIconified(false); 
                    //this.restoreStage.setMaximized(false);
                    //this.restoreStage.repaint();
                    if (timer != null) {
                        timer.cancel();
                    }
                }
            });

            ActionListener discoveryListener = e -> Platform.runLater(() -> {
                       
                if (this.discoveryStage == null) {
                    this.discoveryStage.show();
                } else {
                    this.discoveryStage.show();
                    if (this.discoveryStage.isIconified()) {
                        this.discoveryStage.show();
                    }
                }
                // ENSEÑA EL STAGE DE LA BARRA DE PROGRESO
                if (this.progressBarStage != null && progressBarStage.getOwner() == null) {
                    this.progressBarStage.initOwner(discoveryStage);
                    this.progressBarStage.initModality(Modality.WINDOW_MODAL);
                    this.progressBarStage.setAlwaysOnTop(true);
                    this.progressBarStage.show();
                }
                   
            });

            //Se crean los Items del menu PopUp y se añaden
            MenuItem exitAppItem = new MenuItem("Salir");
            exitAppItem.addActionListener(exitListener);
            popup.add(exitAppItem);

            MenuItem restoreAppItem = new MenuItem("Restaurar");
            restoreAppItem.addActionListener(restoreListener);
            popup.add(restoreAppItem);

            MenuItem discoveryAppItem = new MenuItem("Discovery");
            discoveryAppItem.addActionListener(discoveryListener);
            popup.add(discoveryAppItem);

            /* ----------------- ACCIONES DEL MENU POPUP : END ---------------- */
            trayIcon.addMouseListener(mouseListener);

            //Añade el TrayIcon al SystemTray
            try {
                systemtray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("Error:" + e.getMessage());
            }
        } else {
            System.err.println("Error: SystemTray no es soportado");
            return;
        }

        //Cuando se minimiza JFrame, se oculta para que no aparesca en la barra de tareas
        
        this.restoreStage.iconifiedProperty().addListener(e -> new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                restoreStage.hide();//Se oculta JFrame
                //Se inicia una tarea cuando se minimiza           
                if (timer != null) {
                    timer.cancel();
                }
                timer = new Timer();
                timer.schedule(new MyTimerTask(), 2000, 12000);//Se ejecuta cada 12 segundos
            }
        } );
        

    }

    //Muestra una burbuja con la accion que se realiza
    public void MensajeTrayIcon(String texto, MessageType tipo) {
        trayIcon.displayMessage("Servicio SARLAB:", texto, tipo);
    }

    /**
     * clase interna que manejara una accion en segundo plano
     */
    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            actionBackground();
        }

        /**
         * accion a realizar cuando la aplicacion a sido minimizada
         */
        public void actionBackground() {
            //int num1 = (int) Math.floor(Math.random()*6+1);
            //int num2 = (int) Math.floor(Math.random()*6+1);
            //MensajeTrayIcon("Haciendo algo: " + num1 + " + " + num2 +  " = "+ (num1+num2), MessageType.INFO);                        
        }

    }
}
