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
import javafx.scene.control.MenuItem;

/**
 *
 * @author alumno
 */

public class MySystemTrayFX {

    private Stage restoreStage;
    private Stage discoveryStage;
    private PanelClientServerNameController discoveryController;
    private FXTrayIcon trayIcon;

    public MySystemTrayFX(Stage primaryStage) {
        this.restoreStage = primaryStage;
        setupTrayIcon();
    }

    private void setupTrayIcon() {
        trayIcon = new FXTrayIcon(restoreStage, getClass().getResource("../resource/execjar128.png")); 
        trayIcon.setTooltip("Servicio SARLAB");
        trayIcon.show();
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
        Platform.runLater(() -> {
            if (!this.restoreStage.isShowing()) {
                this.restoreStage.show();
            }
            this.restoreStage.toFront();
        });
    }

    private void showDiscovery() {
        Platform.runLater(() -> {
            if (this.discoveryStage == null) {
                try {
                    this.discoveryStage = StageManager.getInstance().getStage("PanelClientServerName");
                    FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelClientServerName");
                    Scene scene = new Scene(fxmlLoader.load()); 
                    this.discoveryController = fxmlLoader.getController();
                    this.discoveryStage.setScene(scene);
                    this.discoveryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                this.discoveryStage.show();
                if (this.discoveryStage.isIconified()) {
                    this.discoveryStage.show();
                }               
            } 
            this.discoveryController.init();
        });
    }

    private void exitApplication() {
        trayIcon.hide();
        Platform.exit();
        System.exit(0);
    }
}
