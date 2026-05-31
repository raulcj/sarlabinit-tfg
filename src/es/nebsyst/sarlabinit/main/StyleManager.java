/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.main;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author alumno
 */
public class StyleManager 
{ 
    private static StyleManager instance;
    private String cssPath;
    
    private StyleManager() { 
        // La hoja 'sarlab-main.css' es el estilo predeterminado
        cssPath = StyleManager.class.getResource("/es/styles/sarlab-main-blue.css").toExternalForm();
    }
    
    public static StyleManager getInstance() { 
        if (instance == null) {
            instance = new StyleManager();
        }
        return instance; 
    }
     /**
     *  Aplica la hoja de estilo recibida como cadena de texto
     * 
     * @param cssSheet
     */
    public void setupCss(String cssSheet) {
        if (cssSheet.equals("Main Blue")) {
            cssPath = StyleManager.class.getResource("/es/styles/sarlab-main-blue.css").toExternalForm();
        } else if (cssSheet.equals("Main Orange")) {
            cssPath = StyleManager.class.getResource("/es/styles/sarlab-main-orange.css").toExternalForm();
        } else if (cssSheet.equals("Dark Orange")) {
            cssPath = StyleManager.class.getResource("/es/styles/sarlab-dark-orange.css").toExternalForm();
        } else if (cssSheet.equals("Dark Fluor Green")) {
            cssPath = StyleManager.class.getResource("/es/styles/sarlab-dark-fluor-green.css").toExternalForm();
        } else if (cssSheet.equals("Soft Dark Green")) {
            cssPath = StyleManager.class.getResource("/es/styles/sarlab-soft-dark-green.css").toExternalForm();
        } else if (cssSheet.equals("Lila")) {
            cssPath = StyleManager.class.getResource("/es/styles/sarlab-lila.css").toExternalForm();
        }
    }
    /**
     *  Aplica la hoja de estilo a la Scene recibida como argumento
     * 
     * @param scene 
     */
    private void applyCss(Scene scene) {
        if (scene.getStylesheets().isEmpty()) {
            scene.getStylesheets().add(cssPath);
        }
    }

    /**
     * Devuelve un objeto Scene con el estilo aplicado
     * 
     * @param parent
     * @return Scene con estilo aplicado
     */
    public Scene createSceneCss(Parent parent) {
        Scene scene = new Scene(parent);
        applyCss(scene);
        return scene;
    }
    
}
