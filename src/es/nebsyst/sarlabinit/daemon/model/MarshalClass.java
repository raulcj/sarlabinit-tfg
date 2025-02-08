package es.nebsyst.sarlabinit.daemon.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MarshalClass {

    public void generateXML(String nameFile) {

        try {
            File file = new File(nameFile);
            if (file.exists()) {
                file.delete();
            }
            FileWriter fileM = new FileWriter(nameFile, true);
            JAXBContext jc = JAXBContext.newInstance(this.getClass());
            Marshaller jaxbMarshaller = jc.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, fileM);
            fileM.close();
            //jaxbMarshaller.marshal(this, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object loadURLToXML(String surl) {
        InputStream is;
        URL url;
        try {
            url = new URL(surl);
            URLConnection urlCon = url.openConnection();
            // Esto saca por pantalla "image/jpeg"
            //System.out.println(urlCon.getContentType());
            // acceso al contenido web
            is = urlCon.getInputStream();
            try {
                JAXBContext jc;
                jc = JAXBContext.newInstance(this.getClass());
                Unmarshaller jaxbMarshaller = jc.createUnmarshaller();
                return jaxbMarshaller.unmarshal(is);

            } catch (JAXBException ex) {

            }
            // Cierre de conexion y fichero.
            is.close();
            return null;
        } catch (MalformedURLException ex) {
            //
            return null;
        } catch (IOException ex) {
            //
            return null;
        }
    }

    public void printXML() {

        try {

            JAXBContext jc = JAXBContext.newInstance(this.getClass());
            Marshaller jaxbMarshaller = jc.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Object reloadXML(String nameFile) {

        try {
            File file = new File(nameFile);

            if (file.exists()) {
                //System.out.println("El fichero " + nameFile + " existe");
                JAXBContext jc = JAXBContext.newInstance(this.getClass());
                //JAXBContext jc = JAXBContext.newInstance(MarshalClass.class);
                Unmarshaller jaxbMarshaller = jc.createUnmarshaller();
                return jaxbMarshaller.unmarshal(file);
            } else {
                //System.out.println("El fichero " + nameFile + " no existe");
                return null;
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toStringXML() {

        try {
            String s;
            JAXBContext jc = JAXBContext.newInstance(this.getClass());
            Marshaller jaxbMarshaller = jc.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            jaxbMarshaller.marshal(this, buffer);
            return buffer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object loadStringInXML(String cadena) {

        try {
            JAXBContext jc = JAXBContext.newInstance(this.getClass());
            Unmarshaller jaxbMarshaller = jc.createUnmarshaller();
            byte[] buf = cadena.getBytes();
            ByteArrayInputStream buffer = new ByteArrayInputStream(buf);
            return jaxbMarshaller.unmarshal(buffer);
        } catch (JAXBException ex) {
            // Logger.getLogger(MarshalClass.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
