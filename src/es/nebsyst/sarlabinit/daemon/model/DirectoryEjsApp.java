/*
 * 
 */
package es.nebsyst.sarlabinit.daemon.model;

import es.nebsyst.sarlabinit.daemon.GesJarProcess;
import static es.nebsyst.sarlabinit.daemon.GesJarProcess.startJarCMD;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 *
 */
@XmlRootElement(name = "DirectoryEjsApp")
public class DirectoryEjsApp extends MarshalClass implements Serializable {

    ListEjsApp directory;

    public DirectoryEjsApp() {
        this.directory = new ListEjsApp();
    }

    public ListEjsApp getDirectory() {
        return directory;
    }

    @XmlElement(name = "EJSApp")
    public void setDirectory(ListEjsApp directory) {
        this.directory = directory;
    }

    public boolean add(String nameEjsApp, String descriction, String settings) {
        return directory.add(nameEjsApp, descriction, settings);
    }

    public void remove(int ind) {
        directory.remove(ind);
    }

    public EjsApp get(int ind) {
        return directory.get(ind);
    }

    public void set(int ind, EjsApp element) {
        directory.set(ind, element);
    }

    public DefaultListModel<String> getListModel() {
        return directory.getListModel();
    }

    public void execJar() {
        GesJarProcess.initProperties();
        for (EjsApp e : directory) {
            try {
                GesJarProcess.startJarCMD(e.getNameEjsApp() + " " + e.getSettings());
            } catch (IOException ex) {
                //Logger.getLogger(DirectoryEjsApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void killJar() {
        for (EjsApp e : directory) {
            GesJarProcess.killJarProcessRunning(e); //Logger.getLogger(DirectoryEjsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        DirectoryEjsApp directoryEjsApp = new DirectoryEjsApp();

        //directoryEjsApp = (DirectoryEjsApp) directoryEjsApp.reloadXML("C:/sarlab/datos51.xml");
        //directoryEjsApp.printXML();
        //String s=(String)directoryEjsApp.toStringXML();
        //System.out.println(s);
        //DirectoryEjsApp directoryEjsApp1= (DirectoryEjsApp) directoryEjsApp.loadStringInXML(s);
        // directoryEjsApp1.printXML();
        directoryEjsApp.add("prueba.jar", "Prueba", "pepe pepe2 pepe3");
        directoryEjsApp.add("prueba1.jar", "Prueba 1", "pepe pepe2 pepe3");
        directoryEjsApp.add("prueba2.jar", "Prueba 2", "pepe pepe2 pepe3");
        directoryEjsApp.add("prueba3.jar", "Prueba 3", "pepe pepe2 pepe3");

        directoryEjsApp.generateXML("C:/sarlab/service/servicesarlab.xml");
    }

}
