/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.nebsyst.sarlabinit.controller;

import es.nebsyst.sarlabinit.daemon.GesJarProcess;
import es.nebsyst.sarlabinit.daemon.ServerRuntime;
import es.nebsyst.sarlabinit.daemon.model.DirectoryEjsApp;
import es.nebsyst.sarlabinit.daemon.model.EjsApp;
import es.nebsyst.sarlabinit.main.FXMLLoaderManager;
import es.nebsyst.sarlabinit.main.Main;
import es.nebsyst.sarlabinit.main.MySystemTray;
import es.nebsyst.sarlabinit.main.StageManager;
import es.nebsyst.util.OS;
import java.awt.SystemTray;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
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
    // FUNCIONA: ObservableList<String> listModel = observableArrayList(new ArrayList<>());
    ObservableList<String> listModel;
    
    /*Copied*/
    boolean newApp = true;
    /*Copied*/
    String labelBoton;
    String bAnadir = "Añadir...";
    String bModificar = "Modificar";
    
    private Stage panelJarProcessStage;
    //private PanelJarProcessController panelJarProcessController;
    
    @FXML
    private ListView<String> jList1;
    
    @FXML
    private ScrollPane jScrollPane1;
    @FXML
    private TitledPane jPanel3;
    @FXML
    private Button jButtonEdit;
    @FXML
    private Button jButtonErase;
    @FXML
    private Button jButtonNew;
    @FXML
    private Button jButton1;
    @FXML
    private Button jButton3;
    @FXML
    private Button jButton4;
    @FXML
    private Button jButton5;
    @FXML
    private Button jButton2;
    @FXML
    private Button jButton7;
    @FXML 
    private Button jButtonAñadirModificar;
    @FXML
    private TextField jTextFieldNameEJSApp;
    @FXML
    private TextField jTextFieldParam;
    @FXML
    private TextField jTextFieldDescrip;
    
    private void initPanelEJSAppList() throws IOException {
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
        jList1 = new ListView<String>();
        jList1.setItems(listModel);
        jPanel3 = new TitledPane();
        jPanel3.setText(Estado0);
        //jTextFieldNameEJSApp.requestFocus();
        newApp = true;
        GesJarProcess.initProperties();
        pathConfig = GesJarProcess.pathConfig;
        initProperties();
        //printProperties();
        directoryEjsApp.execJar();
        
        // Se inician los componentes de initComponents() de la clase PanelEJSAppList.java original de Swing
        
        jList1.prefWidthProperty().bind(jScrollPane1.widthProperty());
        jScrollPane1.setContent(jList1);
        
        // INICIO - Added backend
        ObservableList<String> ejemplos = FXCollections.observableArrayList("Ejemplo 1", "Ejemplo 2", "Ejemplo 3");
        jList1.setItems(ejemplos); 
        // FIN - Added backend
    }
    
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
            mensaje.showAndWait();
            
        } catch (IOException ex) {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setContentText("No puedo acceder al fichero de propiedades " + pathConfig);
            mensaje.showAndWait();
        }
    }
    
    public static void printProperties() {
        System.out.println("fileConfig    = " + fileConfig);
        System.out.println("pathDirConfig = " + pathDirConfig);
    }
    
    public boolean exitConfig() {
        File fichero = new File(fileConfig);
        return fichero.exists();
    }
    
    public void clearText() {
        jTextFieldNameEJSApp.setText("");
        jTextFieldParam.setText("");
        jTextFieldDescrip.setText("");
        jTextFieldNameEJSApp.requestFocus();
    }
     
    public void selectFile() throws IOException {
        FileChooser chooser = new FileChooser();
        //chooser.setInitialDirectory(new java.io.File(pathDirConfig)) ESTA RUTA DA ERROR;
        chooser.setInitialDirectory(new java.io.File(pathDirEJSExport));
        //chooser.showOpenDialog(this);
        //Titulo que llevara la ventana
        chooser.setTitle("Selector de fichero");
        //Elegiremos archivos del directorio
        //SE PUEDE BORRAR PORQUE FileChooser por defecto solo permite archivos
        //chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
        //chooser.setAcceptAllFileFilterUsed(false);
        //Creamos el filtro
        //FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jar", "jar");
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("*.jar", "jar");
        chooser.getExtensionFilters().add(filtro);
        //chooser.setFileFilter(filtro);
        //Seleccionamos el filtro
        chooser.setSelectedExtensionFilter(filtro);
        //Si seleccionamos algún archivo retornaremos su directorio
        // COMPROBAR: si el stage principal (PanelEJSAppList) es el que hay que elegir o no
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/PanelEJSAppList.fxml"));
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
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("*.jar", "jar");
        chooser.getExtensionFilters().add(filtro);
        //chooser.setFileFilter(filtro);
        //Seleccionamos el filtro
        chooser.setSelectedExtensionFilter(filtro);
        //Si seleccionamos algún archivo retornaremos su directorio
        // COMPROBAR: si el stage principal (PanelEJSAppList) es el que hay que elegir o no
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/PanelEJSAppList.fxml"));
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

    @FXML
    void jButtonEditActionPerformed() {
         // TODO add your handling code here:
        newApp = true;
        int ind = jList1.getSelectionModel().getSelectedIndex();
        if (ind != -1) {
            jPanel3.setText(Estado1);  
            jButtonAñadirModificar.setText("Modificar");
            // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
            //EjsApp ejsApp = directoryEjsApp.get(ind);
            //jTextFieldNameEJSApp.setText(ejsApp.getNameEjsApp());
            //jTextFieldParam.setText(ejsApp.getSettings());
            //jTextFieldDescrip.setText(ejsApp.getDescriction());
        }   
    }
    
    @FXML
    void jButtonEraseActionPerformed() {
        // TODO add your handling code here:
        newApp = true;
        jPanel3.setText(Estado0);
        jButtonAñadirModificar.setText("Anadir ...");
        int ind = jList1.getSelectionModel().getSelectedIndex();
        if (OS.isWindows()) {
            fileConfig = "C:/sarlab/service/servicesarlab.xml";
        }
        if (ind != -1) {
            // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
            //listModel.remove(ind);
            //directoryEjsApp.remove(ind);
            //directoryEjsApp.generateXML(fileConfig);
            //clearText();
        }
    }
    
    @FXML
    void jButtonNewActionPerformed() {                                           
        newApp = true;
        jPanel3.setText(Estado0);
        jButtonAñadirModificar.setText("Anadir ...");
        clearText();
    }         
    
    @FXML
    void jButton1ActionPerformed() {                                         
        // TODO add your handling code here:
        directoryEjsApp.execJar();
    }  
    
    @FXML
    void jButton3ActionPerformed() throws IOException {                                         
        // TODO add your handling code here:
        directoryEjsApp.killJar();
    }    
    
    @FXML
    void jButton4ActionPerformed() throws IOException {                                          
        if (panelJarProcessStage == null) {
            panelJarProcessStage = StageManager.getInstance().getStage("PanelJarProcess");
            FXMLLoader fxmlLoader = FXMLLoaderManager.getInstance().getLoader("PanelJarProcess");
            Scene scene = new Scene(fxmlLoader.load());
            panelJarProcessStage.setScene(scene);       
        }
        panelJarProcessStage.show();
    }  
    
    @FXML
    void jButton5ActionPerformed() throws IOException {                                         
        copyFile();
    } 
     
    @FXML
    void jButton2ActionPerformed() throws IOException {                                         
        selectFile();
    } 
    @FXML
    void jButton7ActionPerformed() {                                         
        newApp = true;
        jPanel3.setText(Estado0);
        jButtonAñadirModificar.setText("Anadir ...");
        clearText();
    }    
    
    @FXML
    void jButtonAñadirModificarActionPerformed() {                                                       
        String patron = "%-45s %-70s  %-80s";
        String nameEjsApp, settings, descriction;
        nameEjsApp = jTextFieldNameEJSApp.getText();
        settings = jTextFieldParam.getText();
        descriction = jTextFieldDescrip.getText();
        
        if (OS.isWindows()) {
            fileConfig = "C:/sarlab/service/servicesarlab.xml";
        }
        if (newApp) {
            if (directoryEjsApp.add(nameEjsApp, descriction, settings)) {
                listModel.addAll(String.format(patron, nameEjsApp, settings, descriction));
            }
            clearText();
            // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
            //directoryEjsApp.generateXML(fileConfig);
        } else {
            int ind = jList1.getSelectionModel().getSelectedIndex();
            if (ind != -1) {
                directoryEjsApp.set(ind, new EjsApp(nameEjsApp, descriction, settings));
                listModel.set(ind, String.format(patron, nameEjsApp, settings, descriction));
                // ANULADO PARA BACKEND DE PRUEBA FUNCIONAL
                //directoryEjsApp.generateXML(fileConfig);
            }
        }
    }              
    
    @FXML
    void jList1ValueChanged() {                                    
        // TODO add your handling code here:
        newApp = true;
        int ind = jList1.getSelectionModel().getSelectedIndex();
        if (ind != -1) {
            jPanel3.setText(Estado1);
            jButtonAñadirModificar.setText("Modificar");
            EjsApp ejsApp = directoryEjsApp.get(ind);
            jTextFieldNameEJSApp.setText(ejsApp.getNameEjsApp());
            jTextFieldParam.setText(ejsApp.getSettings());
            jTextFieldDescrip.setText(ejsApp.getDescriction());
        }
    }                                   
   
    @FXML
    private void jList1MouseClicked() {                                    
        newApp = false;
        int ind = jList1.getSelectionModel().getSelectedIndex();
        if (ind != -1) {
            jPanel3.setText(Estado1);
            jButtonAñadirModificar.setText("Modificar");
            EjsApp ejsApp = directoryEjsApp.get(ind);
            jTextFieldNameEJSApp.setText(ejsApp.getNameEjsApp());
            jTextFieldParam.setText(ejsApp.getSettings());
            jTextFieldDescrip.setText(ejsApp.getDescriction());
        }
    }            
     
    @FXML
    void jList1MousePressed() {                                    
        newApp = false;
        int ind = jList1.getSelectionModel().getSelectedIndex();
        if (ind != -1) {
            jPanel3.setText(Estado1);
            jButtonAñadirModificar.setText("Modificar");
            EjsApp ejsApp = directoryEjsApp.get(ind);
            jTextFieldNameEJSApp.setText(ejsApp.getNameEjsApp());
            jTextFieldParam.setText(ejsApp.getSettings());
            jTextFieldDescrip.setText(ejsApp.getDescriction());
        }
    }          
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initPanelEJSAppList();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanelEJSAppListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
