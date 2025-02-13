/*
 * 
 */
package es.nebsyst.sarlabinit.main;

import es.uhu.sarlab.discovery.vista.PanelClientServerName;
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
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 */
public class MySystemTray {

    private Stage parent;
    private PanelClientServerName clientName;
    private PopupMenu popup = new PopupMenu();
    private final Image image = new ImageIcon(getClass().getResource("/es/nebsyst/sarlabinit/resource/execjar128.png")).getImage();
    private final TrayIcon trayIcon = new TrayIcon(image, "Servicio SARLAB", popup);
    //para el Timer
    private Timer timer;

    /**
     * Constructor de clase
     *
     * @param stage
     */
    public MySystemTray(Stage stage) {
        this.parent = stage;
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
                    if (evt.getButton() == MouseEvent.BUTTON1 && parent.isIconified()) {
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
                parent.show();
                //si esta minimizado restaura JFrame
                if (parent.isIconified()) {
                    parent.show();
                    //parent.setExtendedState(JFrame.NORMAL);
                    parent.setIconified(false); 
                    //parent.setMaximized(false);
                    //parent.repaint();
                    if (timer != null) {
                        timer.cancel();
                    }
                }
            });

            ActionListener discoveryListener = (ActionEvent e) -> {

                if (clientName == null) {
                    clientName = new PanelClientServerName();
                    clientName.setVisible(true);
                    clientName.init();
                } else {
                    clientName.setVisible(true);
                    if (clientName.getExtendedState() == JFrame.ICONIFIED) {
                        clientName.setVisible(true);
                        clientName.setExtendedState(JFrame.NORMAL);
                        clientName.repaint();
                    }
                }
            };

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
        
        parent.iconifiedProperty().addListener(e -> new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                parent.hide();//Se oculta JFrame
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
