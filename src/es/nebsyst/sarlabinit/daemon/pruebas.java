/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nebsyst.sarlabinit.daemon;

import es.nebsyst.sarlabinit.vista.PanelEJSAppList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;

/**
 *
 * @author marcoa
 */
public class pruebas {
    
    
    
    public static void main(String[] args) {
        
        //
        String userHome = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");
        String userDir = System.getProperty("user.dir");
        String configEjs=userHome+fileSeparator+".EjsConsole.txt";
        String ejsWorkspace;
        System.out.println(configEjs);
        Properties pro = new Properties();
        try {
            pro.load(new FileReader(configEjs));
            ejsWorkspace=pro.getProperty("UserDir",userHome);

        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelEJSAppList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanelEJSAppList.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        String fileConfig="C:/sarlab/service/servicesarlab.xml";
        DirectoryEjsApp directoryEjsApp=new DirectoryEjsApp();
        directoryEjsApp = (DirectoryEjsApp) directoryEjsApp.reloadXML(fileConfig);
        directoryEjsApp.execJar();
        //Pasa de String[] --> ArrayList
        String[] arrayString= new String[]{"pepe","juan"};
        List<String> listString =  Arrays.<String>asList(arrayString);
        ArrayList<String> arrayList = new ArrayList<String>(listString);
        for(String s:arrayList)System.out.println(s);
        
            
        //Pasa de ArrayList a String[]       
        String[] a1;    
        ArrayList<String> list=new ArrayList<>();
        list.add("apple");
        list.add("banana");
        a1=list.toArray(new String[list.size()]);
        
        for(String s:a1)System.out.println(s);
        
        //Pasa entre ListModel <--> arrayList
        
        JList<String> jlist = new JList<String>(new String[]{"a","b","c","d"});

        //DefaultListModel<String> defaultListModel = (DefaultListModel)jlist.getModel();

       // ArrayList<String> arrayList1 = Collections.list(defaultListModel.elements());
       
       JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        //Titulo que llevara la ventana
        chooser.setDialogTitle("Titulo");
        //Elegiremos archivos del directorio
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        //Si seleccionamos algún archivo retornaremos su directorio
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        System.out.println("Directorio: " + chooser.getCurrentDirectory());
        //Si no seleccionamos nada retornaremos No seleccion
        } else {
            System.out.println("No seleccion ");
        }*/
        
        
       
       
        
        
    }
    
}
