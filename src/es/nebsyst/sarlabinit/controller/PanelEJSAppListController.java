/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.controller;

import es.nebsyst.sarlabinit.daemon.GesJarProcess;
import es.nebsyst.sarlabinit.daemon.ServerRuntime;
import es.nebsyst.sarlabinit.daemon.model.DirectoryEjsApp;
import es.nebsyst.sarlabinit.main.PanelEJSAppListMain;
import es.nebsyst.util.OS;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author alumno
 */
public class PanelEJSAppListController implements Initializable {

    /*Copied*/
    private final static Logger log = Logger.getLogger(PanelEJSAppListController.class);
    
    /*Copied*/
    //MySystemTray mySystemTray = new MySystemTray(this);
    
    /*Copied*/
    public static ServerRuntime serverRuntime;
    
    /*Copied*/
    String Estado0 = "Nueva aplicación EJS";
    String Estado1 = "Editando una aplicación EJS";
    String Estado = Estado0;
    static boolean oSWin = OS.isWindows();
    static String fileConfig = oSWin ? "C:/sarlab/service/servicesarlab.xml" : "/sarlab/service/servicesarlab.xml";
    static String pathDirConfig = oSWin ? "C:/sarlab/init.d" : "/sarlab/init.d";
    static String configLog4j = oSWin ? "C:/sarlab/config/log4jsarlabinit.properties" : "/sarlab/config/log4jsarlabinit.properties";
    //static String pathDirEJSExport = "/EJS/workspace/export";
    static String pathDirEJSExport = initPathDirEJSExport();
    static String pathConfig;
    
    /*Copied*/
    DirectoryEjsApp directoryEjsApp = new DirectoryEjsApp();
    // MIRAR: si puede cambiarse por ObservableList
    // FUNCIONA: ObservableList<String> listModel = observableArrayList(new ArrayList<>());
    ObservableList<String> listModel;
    
    /*Copied*/
    boolean newApp = true;
    /*Copied*/
    String labelBoton;
    String bAnadir = "Añadir...";
    String bModificar = "Modificar";
    
    
    private ListView<String> jList1;
    
    // SUSTITUIR EN SCENE BUILDER
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextFieldNameEJSApp;
    
    
    static public String initPathDirEJSExport() {
        String userHome = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");
        String configEjs = userHome + fileSeparator + ".EjsConsole.txt";

        Properties pro = new Properties();
        try {
            pro.load(new FileReader(configEjs));
            String ejsWorkspace = pro.getProperty("UserDir");
            if (ejsWorkspace != null) {
                return ejsWorkspace + "export";
            } else {
                return ejsWorkspace;
            }
        } catch (FileNotFoundException ex) {
            //java.util.logging.Logger.getLogger(PanelEJSAppList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //java.util.logging.Logger.getLogger(PanelEJSAppList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/";
    }
    
    private void initPanelEJSAppList() {
        PropertyConfigurator.configure(configLog4j);
        log.debug("****** Iniciando SARLAB init **********");
        log.debug("fileConfig = " + fileConfig);
        log.debug("pathDirConfig = " + pathDirConfig);
        log.debug("configLog4j = " + configLog4j);
        log.debug("pathDirEJSExport = " + pathDirEJSExport);
        if (exitConfig()) {
            directoryEjsApp = (DirectoryEjsApp) directoryEjsApp.reloadXML(fileConfig);
            //directoryEjsApp.printXML();
            listModel = (ObservableList<String>) directoryEjsApp.getListModel();
        } else {
            listModel = observableArrayList(new ArrayList<>());
        }
        jList1 = new ListView<>();
        jList1.setItems(listModel);
        //jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(Estado0));
        //jTextFieldNameEJSApp.requestFocus();
        newApp = true;
        GesJarProcess.initProperties();
        pathConfig = GesJarProcess.pathConfig;
        initProperties();
        //printProperties();
        directoryEjsApp.execJar();
    }
    
    public static void initProperties() {
        try {
            Properties p = new Properties();
            p.load(new FileReader(pathConfig));
            String aux = p.getProperty("fileConfig");
            if (aux != null) {
                fileConfig = aux;
            }
            aux = p.getProperty("pathDirConfig");
            if (aux != null) {
                pathDirConfig = aux;
            }
        } catch (FileNotFoundException ex) {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setContentText("No encuentro el fichero de propiedades " + pathConfig);
            
        } catch (IOException ex) {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setContentText("No puedo acceder al fichero de propiedades " + pathConfig);
        }
    }
    
    public boolean exitConfig() {
        File fichero = new File(fileConfig);
        if (fichero.exists()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void printProperties() {
        System.out.println("fileConfig    = " + fileConfig);
        System.out.println("pathDirConfig = " + pathDirConfig);
    }
    
    public void selectFile() throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new java.io.File(pathDirConfig));
        //chooser.showOpenDialog(this);
        //Titulo que llevara la ventana
        chooser.setTitle("Selector de fichero");
        //Elegiremos archivos del directorio
        //SE PUEDE BORRAR PORQUE FileChooser por defecto solo permite archivos
        //chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
        //chooser.setAcceptAllFileFilterUsed(false);
        //Creamos el filtro
        //FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jar", "jar");
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("JAVA files", "*.jar", "jar");
        chooser.getExtensionFilters().add(filtro);
        //chooser.setFileFilter(filtro);
        //Seleccionamos el filtro
        chooser.setSelectedExtensionFilter(filtro);
        //Si seleccionamos algún archivo retornaremos su directorio
        // COMPROBAR: si el stage principal (PanelEJSAppList) es el que hay que elegir o no
        FXMLLoader fxmlLoader = new FXMLLoader(PanelEJSAppListMain.class.getResource("../view/PanelEJSAppList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        File selectedFile = chooser.showOpenDialog(scene.getWindow());
        if (selectedFile != null) {
            // jTextFieldNameEJSApp.setText(chooser.getSelectedFile().getName());
            //Si no seleccionamos nada retornaremos No seleccion
        } else {
            // System.out.println("No seleccion ");
        }
    }
     
    public void copyFile() throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new java.io.File(pathDirEJSExport));
        //chooser.showOpenDialog(this);
        //Titulo que llevara la ventana
        chooser.setTitle("Selector de fichero");
        //Elegiremos archivos del directorio
        //chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //chooser.setAcceptAllFileFilterUsed(false);
        //Creamos el filtro
        //FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jar", "jar");
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("JAVA files", "*.jar", "jar");
        chooser.getExtensionFilters().add(filtro);
        //chooser.setFileFilter(filtro);
        //Seleccionamos el filtro
        chooser.setSelectedExtensionFilter(filtro);
        //Si seleccionamos algún archivo retornaremos su directorio
        // COMPROBAR: si el stage principal (PanelEJSAppList) es el que hay que elegir o no
        FXMLLoader fxmlLoader = new FXMLLoader(PanelEJSAppListMain.class.getResource("../view/PanelEJSAppList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        File selectedFile = chooser.showOpenDialog(scene.getWindow());
        if (selectedFile != null) {
            //System.out.println("Directorio: " + chooser.getCurrentDirectory());
            //System.out.println("Fichero: " + chooser.getSelectedFile().getName());
            Runtime rt = Runtime.getRuntime();
            String command = "";
            String s = File.separator;
            if (OS.isWindows()) {
                command = "cmd /C copy " + chooser.getInitialDirectory() + s + selectedFile.getName() + " C:\\sarlab\\init.d";
            } else {
                command = "cp " + chooser.getInitialDirectory() + s + selectedFile.getName() + " /sarlab/init.d";
            }
            try {
                //rt.exec("cmd /C copy "+chooser.getCurrentDirectory()+s+chooser.getSelectedFile().getName()+" C:\\sarlab\\init.d");
                rt.exec(command);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            //jTextFieldNameEJSApp.setText(chooser.getSelectedFile().getName());
            //Si no seleccionamos nada retornaremos No seleccion
        } else {
            // System.out.println("No seleccion ");
        }
    }
    
    /*
    * Comprueba el nº total de Stages abiertos
    */
    private int getOpenStagesCount() {
        return (int) Window.getWindows().stream().filter(w -> w instanceof Stage).count();
    }
    
    @FXML
    void startPanelJarProcess() throws IOException {
        // nº de PanelJarProcess abiertos <= 1
        if (getOpenStagesCount() < 2) {
            PanelEJSAppListMain.startPanelJarProcess();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initPanelEJSAppList();
    }
    
}
