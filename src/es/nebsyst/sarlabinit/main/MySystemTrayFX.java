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
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.MenuItem;

/**
 *
 * @author alumno
 */

public class MySystemTrayFX {

    private Stage mainStage;
    private Stage restoreStage;
    private Stage discoveryStage;
    private PanelClientServerNameController discoveryController;
    private FXTrayIcon trayIcon;

    public MySystemTrayFX(Stage primaryStage) {
        this.mainStage = primaryStage;
        setupTrayIcon();
    }

    private void setupTrayIcon() {
        trayIcon = new FXTrayIcon(mainStage, getClass().getResource("../resource/execjar128.png")); 
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
            if (this.restoreStage == null) {
                try {
                    this.restoreStage = StageManager.getInstance().getStage("PanelEJSAppList");
                    FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelEJSAppList");
                    Scene scene = new Scene(fxmlLoader.load());    
                    addCss(scene);
                    this.restoreStage.setScene(scene);
                    this.restoreStage.show();
                } catch (IOException e) {
                    Logger.getLogger(MySystemTrayFX.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                this.restoreStage.show();
                if (this.restoreStage.isIconified()) {
                    this.restoreStage.show();
                }
            }
        });
    }

    private void showDiscovery() {
        Platform.runLater(() -> {
            if (this.discoveryStage == null) {
                try {
                    this.discoveryStage = StageManager.getInstance().getStage("PanelClientServerName");
                    FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelClientServerName");
                    Scene scene = new Scene(fxmlLoader.load()); 
                    addCss(scene);
                    this.discoveryController = fxmlLoader.getController();
                    this.discoveryStage.setScene(scene);
                    this.discoveryStage.show();
                } catch (IOException e) {
                    Logger.getLogger(MySystemTrayFX.class.getName()).log(Level.SEVERE, null, e);
                }
                if (this.discoveryController == null) {
                    FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelClientServerName");
                    this.discoveryController = fxmlLoader.getController();       
                }
                this.discoveryController.initList();
            } else {
                this.discoveryStage.show();
                if (this.discoveryStage.isIconified()) {
                    this.discoveryStage.show();
                }     
                if (this.discoveryController == null) {
                    FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelClientServerName");
                    this.discoveryController = fxmlLoader.getController();       
                }
                this.discoveryController.initList();
            } 
            this.discoveryController.init();
            // Esconder PanelMain al abrir Discovery
            mainStage.hide();
        });
    }
    
    private void addCss(Scene scene) {
        if (scene.getStylesheets().isEmpty()) {
            String[] cssPaths = new String[] {
                "../../../styles/base.css",
                "../../../styles/utilities.css",
                "../../../styles/buttons.css",
                "../../../styles/inputs.css",
                "../../../styles/lists_tables.css",
                "../../../styles/containers.css",
                "../../../styles/extras.css",
            };
            for (String p : cssPaths) {
                URL url = getClass().getResource(p);
                if (url != null) {
                    scene.getStylesheets().add(url.toExternalForm());
                }
            }         
        }     
    }

    private void exitApplication() {
        trayIcon.hide();
        Platform.exit();
        System.exit(0);
    }
}
