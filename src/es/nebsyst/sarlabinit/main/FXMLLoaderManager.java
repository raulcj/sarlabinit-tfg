/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.main;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author alumno
 */
public class FXMLLoaderManager {
    
    private static final FXMLLoaderManager instance = new FXMLLoaderManager();
    private final Map<String, FXMLLoader> stageMap = new HashMap<>();

    private FXMLLoaderManager() {} 

    public static FXMLLoaderManager getInstance() {
        return instance;
    }

    public void registerLoader(String name, FXMLLoader loader) {
        stageMap.put(name, loader);
    }

    public FXMLLoader getLoader(String name) {
        return stageMap.get(name);
    }
    
}
