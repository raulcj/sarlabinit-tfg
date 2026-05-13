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
    
    private static final String CSS_PATH = StyleManager.class.getResource("/es/styles/sarlab-main.css").toExternalForm();

    /**
     *  Aplica la hoja de estilos a la Scene recibida como argumento
     * 
     * @param scene 
     */
    public static void applyCss(Scene scene) {
        if (!scene.getStylesheets().contains(CSS_PATH)) {
            scene.getStylesheets().add(CSS_PATH);
        }
    }

    /**
     * Devuelve un objeto Scene con los estilos aplicados
     * 
     * @param parent
     * @return Scene con estilos aplicados
     */
    public static Scene createSceneCss(Parent parent) {
        Scene scene = new Scene(parent);
        applyCss(scene);
        return scene;
    }
    
}
