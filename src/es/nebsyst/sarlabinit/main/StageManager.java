/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.main;

import java.util.HashMap;
import java.util.Map;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */

public class StageManager {
    
    private static final StageManager instance = new StageManager();
    private final Map<String, Stage> stageMap = new HashMap<>();

    private StageManager() {} 

    public static StageManager getInstance() {
        return instance;
    }

    public void registerStage(String name, Stage stage) {
        stageMap.put(name, stage);
    }

    public Stage getStage(String name) {
        return stageMap.get(name);
    }
    
}

