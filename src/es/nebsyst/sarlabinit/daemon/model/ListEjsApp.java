/*
*  Copyright (C) 2012 Marco A. Márquez Sánchez
*
* Esta clase es parte  de la implementación para TCP/IP de  SARLAB
*(Sistema de Acceso Remoto a LAboratorios) desarrollada en la Tesis Doctoral del
* Departamento de Ingeniería Electrónica, de Sistemas Informáticos y Automática
*                  UNIVERSIDAD DE HUELVA.
*    Realizado por    D. Marco A. Márquez Sánchez
*    Dirigido  por el Dt. D. José Manuel Andújar  Márquez
*
*  Esta clase implementa la gestión de acceso
 */
package es.nebsyst.sarlabinit.daemon.model;

import es.nebsyst.sarlabinit.daemon.model.EjsApp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marco A. Márquez Sánchez
 *
 */
@XmlRootElement(namespace = "SARLAB5.Experiment3")
public class ListEjsApp extends ArrayList<EjsApp> implements Serializable {

    public boolean add(String nameEjsApp, String descriction, String settings) {
        if (lastIndexOf(nameEjsApp, descriction, settings) != -1) {
            return false;
        }
        return super.add(new EjsApp(nameEjsApp, descriction, settings));
    }

    public void setElementAt(int ind, String nameEjsApp, String descriction, String settings) {
        EjsApp n = get(ind);
        n.setNameEjsApp(nameEjsApp);
        n.setDescriction(descriction);
        n.setSettings(settings);
    }

    public int lastIndexOf(String nameEjsApp, String descriction, String settings) {
        int aux = -1;
        EjsApp ejsApp;
        Iterator it = this.iterator();
        int i = 0;
        while (it.hasNext()) {
            ejsApp = (EjsApp) it.next();
            if (ejsApp.equals(nameEjsApp, descriction, settings)) {
                aux = i;
                break;
            }
            i++;
        }
        return aux;
    }

    public EjsApp get(String nameEjsApp, String descriction, String settings) {

        if (nameEjsApp == null) {
            return null;
        } else {
            return get(lastIndexOf(nameEjsApp, descriction, settings));
        }
    }

    public String getNameEjsApp(int ind) {
        return get(ind).getNameEjsApp();
    }

    public String getDescriction(int ind) {
        return get(ind).getDescriction();
    }

    public boolean remove(String nameEjsApp, String descriction, String settings) {
        return super.remove(get(nameEjsApp, descriction, settings));
    }

    public DefaultListModel<String> getListModel() {

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (EjsApp s : this) {
            listModel.addElement(s.toString());
        }
        return listModel;

    }

}
