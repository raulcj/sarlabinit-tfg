/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.main;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import es.uhu.sarlab.discovery.controller.PanelClientServerNameController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.MenuItem;

/**
 *
 * @author alumno
 */

public class MySystemTrayFX {

    private static MySystemTrayFX instance;
    private final Stage mainStage;
    private Stage restoreStage;
    private Stage discoveryStage;
    private PanelClientServerNameController discoveryController;
    private FXTrayIcon trayIcon;

    private MySystemTrayFX(Stage mainStage) {
        this.mainStage = mainStage;
    }
    
    public static MySystemTrayFX getInstance() { 
        if (instance == null) {
            instance = new MySystemTrayFX(StageManager.getInstance().getStage("PanelMain"));
        }
        return instance;
    }
    
    public FXTrayIcon getTrayIcon() { 
        return trayIcon; 
    }

    public void setupTrayIcon() {
        trayIcon = new FXTrayIcon(mainStage, getClass().getResource("../resource/execjar128.png")); 
        trayIcon.setTooltip("Servicio SARLAB");
        // Añadir los elementos del menú contextual
        // Opcion "Salir"
        MenuItem exit = new MenuItem("Salir");
        exit.setOnAction(e -> exitApplication());
        trayIcon.addMenuItem(exit);
        // Opcion "App Sarlabinit"
        MenuItem openRestaurar = new MenuItem("Restaurar");
        openRestaurar.setOnAction(e -> showRestaurar());
        trayIcon.addMenuItem(openRestaurar);
        // Opcion "App Discovery"
        MenuItem openDiscovery = new MenuItem("Discovery");
        openDiscovery.setOnAction(e -> showDiscovery());
        trayIcon.addMenuItem(openDiscovery);
    }

    private void showRestaurar() {
            if (this.restoreStage == null) {
                try {
                    this.restoreStage = StageManager.getInstance().getStage("PanelEJSAppList");
                    FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelEJSAppList");
                    Scene scene = StyleManager.getInstance().createSceneCss(fxmlLoader.load());
                    this.restoreStage.setScene(scene);
                    this.restoreStage.setMaximized(true);
                    this.restoreStage.show();
                } catch (IOException e) {
                    Logger.getLogger(MySystemTrayFX.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                this.restoreStage.show();
                if (this.restoreStage.isIconified()) {
                    this.restoreStage.setMaximized(true);
                    this.restoreStage.show();
                }
            }
    }

    private void showDiscovery() {
            if (this.discoveryStage == null) {
                try {
                    this.discoveryStage = StageManager.getInstance().getStage("PanelClientServerName");
                    FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelClientServerName");
                    Scene scene = StyleManager.getInstance().createSceneCss(fxmlLoader.load());
                    this.discoveryController = fxmlLoader.getController();
                    this.discoveryStage.setScene(scene);
                    this.discoveryStage.setMaximized(true);
                    this.discoveryStage.show();
                } catch (IOException e) {
                    Logger.getLogger(MySystemTrayFX.class.getName()).log(Level.SEVERE, null, e);
                }
                this.discoveryController.initList();
            } else {
                this.discoveryStage.show();
                if (this.discoveryStage.isIconified()) {
                    this.discoveryStage.setMaximized(true);
                    this.discoveryStage.show();
                }     
                if (this.discoveryController == null) {
                    FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelClientServerName");
                    this.discoveryController = fxmlLoader.getController();       
                }
                this.discoveryController.initList();
            } 
            this.discoveryController.init();
    }

    private void exitApplication() {
        trayIcon.hide();
        Platform.exit();
        System.exit(0);
    }
    
}
