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
    public static final StyleManager instance = new StyleManager();
    private String CSS_PATH;
    
    private StyleManager() { 
        // La hoja 'sarlab-main.css' es el estilo predeterminado
        CSS_PATH = StyleManager.class.getResource("/es/styles/sarlab-main.css").toExternalForm();
    }
    
    public static StyleManager getInstance() { 
        return instance; 
    }
    
    public void setupCss(String cssSheet) {
        if (cssSheet.equals("Main")) {
            CSS_PATH = StyleManager.class.getResource("/es/styles/sarlab-main.css").toExternalForm();
        } else if (cssSheet.equals("Second")) {
            CSS_PATH = StyleManager.class.getResource("/es/styles/sarlab-main-lab.css").toExternalForm();
        }
    }
    /**
     *  Aplica la hoja de estilos a la Scene recibida como argumento
     * 
     * @param scene 
     */
    private void applyCss(Scene scene) {
        if (!scene.getStylesheets().contains(CSS_PATH)) {
            scene.getStylesheets().add(CSS_PATH);
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
