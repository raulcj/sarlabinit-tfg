/*
 * 
 */
package es.nebsyst.sarlabinit.daemon.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author marcoa
 */
@XmlRootElement
@XmlType(propOrder = {"nameEjsApp", "settings", "descriction"})
public class EjsApp extends MarshalClass implements Serializable {

    String nameEjsApp;
    String descriction;
    String settings;

    public EjsApp() {
        this.nameEjsApp = "";
        this.descriction = "";
        this.settings = "";
    }

    public EjsApp(String nameEjsApp, String descriction, String settings) {
        this.nameEjsApp = nameEjsApp;
        this.descriction = descriction;
        this.settings = settings;
    }

    public String getNameEjsApp() {
        return nameEjsApp;
    }

    public void setNameEjsApp(String nameEjsApp) {
        this.nameEjsApp = nameEjsApp;
    }

    public String getDescriction() {
        return descriction;
    }

    public void setDescriction(String descriction) {
        this.descriction = descriction;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public boolean equals(String nameEjsApp, String descriction, String settings) {
        return (this.nameEjsApp.equals(nameEjsApp) && this.descriction.equals(descriction) && this.settings.equals(settings));
    }

    @Override
    public String toString() {
        String patron = "%-45s %-70s  %-80s";
        return String.format(patron, nameEjsApp, settings, descriction);
    }

}
