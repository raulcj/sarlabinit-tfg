/*
 * 
 */
package es.nebsyst.sarlabinit.daemon;

import es.nebsyst.sarlabinit.daemon.model.EjsApp;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author marcoa
 */
public class GesJarProcess {

    private final static Logger log = Logger.getLogger(GesJarProcess.class);

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String pathConfig = "/sarlab/config/init.properties";

    static String pathDirBase = "/sarlab/";
    static String pathDirBin = pathDirBase + "jdk/bin/";
    static String pathDirDaemon = pathDirBase + "init.d/";

    static ArrayList<ArrayList<String>> processList;

    private static String TASKLIST = "tasklist";
    private static String KILL = "taskkill /F /IM ";
    private static String KILLPID = "taskkill /PID ";

    public static void initProperties() {
        try {

            if (isWindows()) {
                TASKLIST = "tasklist";
                KILL = "taskkill /F /IM ";
                KILLPID = "taskkill /PID ";
                pathConfig = "C:/sarlab/config/init.properties";
            }
            if (isUnix()) {
                TASKLIST = "ps -ax";
                KILL = "killall ";
                KILLPID = "kill -9 ";
                pathConfig = "/sarlab/config/init.properties";
            }
            //System.out.println(pathConfig);
            Properties p = new Properties();
            p.load(new FileReader(pathConfig));
            String aux = p.getProperty("pathDirBase");
            if (aux != null) {
                pathDirBase = aux;
            }
            aux = p.getProperty("pathDirBin");
            if (aux != null) {
                pathDirBin = aux;
            }
            aux = p.getProperty("pathDirDaemon");
            if (aux != null) {
                pathDirDaemon = aux;
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No encuentro el fichero de propiedades " + pathConfig);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No puedo acceder al fichero de propiedades " + pathConfig);
        }
    }

    public static void printProperties() {

        System.out.println("Sistema operativo   = " + pathDirBase);
        System.out.println("pathDirBase         = " + pathDirBase);
        System.out.println("pathDirBin          = " + pathDirBin);
        System.out.println("pathDirDaemon       = " + pathDirDaemon);

    }

    public static String getPathDirBase() {
        return pathDirBase;
    }

    public static void setPathDirBase(String pathDirBase) {
        GesJarProcess.pathDirBase = pathDirBase;
    }

    public static boolean isProcessRunning(String serviceName) throws Exception {

        Process p = Runtime.getRuntime().exec(TASKLIST);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            if (line.contains(serviceName)) {
                return true;
            }
        }
        return false;
    }

    public static String pidJarProcessRunning(String serviceName) {

        try {
            String pid = "";
            String process;
            Process p = Runtime.getRuntime().exec(pathDirBin + "jps -l -m ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                int longTokens = st.countTokens();
                if (longTokens < 1) {
                    continue;
                }
                pid = st.nextToken();
                if (st.hasMoreTokens()) {
                    process = st.nextToken();
                    if (process.equals(serviceName)) {
                        return pid;
                    }
                }
                //System.out.println(line);
            }
            return null;
        } catch (IOException ex) {
            //Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String pidJarProcessRunning(String[] serviceName) {

        try {
            String pid = "";
            String process;
            Process p = Runtime.getRuntime().exec(pathDirBin + "jps -l -m ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                int longTokens = st.countTokens();
                int longService = serviceName.length;
                if (longTokens < 1) {
                    continue;
                }
                if ((longTokens - 1) != longService) {
                    continue;
                }
                int i = 0;
                pid = st.nextToken();
                while (st.hasMoreTokens()) {
                    process = st.nextToken();
                    if (!process.equals(serviceName[i])) {
                        pid = null;
                    }
                    i++;
                }
                if (pid != null) {
                    return pid;
                }
            }
            return null;
        } catch (IOException ex) {
            //Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String pidJarProcessRunning(EjsApp serviceName) {

        //String line=serviceName.settings;
        String line = serviceName.getSettings();
        StringTokenizer st = new StringTokenizer(line, " ");
        int longTokens = st.countTokens() + 1;
        String[] buff = new String[longTokens];
        buff[0] = pathDirDaemon + serviceName.getNameEjsApp();
        int i = 1;
        while (st.hasMoreTokens()) {
            buff[i] = st.nextToken();
            i++;
        }
        return pidJarProcessRunning(buff);
    }

    public static void killJarProcessRunning(String serviceName) {
        try {
            killPID(pidJarProcessRunning(serviceName));
        } catch (IOException ex) {
            //Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void killJarProcessRunning(String[] serviceName) {
        try {
            killPID(pidJarProcessRunning(serviceName));
        } catch (IOException ex) {
            //Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void killJarProcessRunning(EjsApp serviceName) {
        try {
            killPID(pidJarProcessRunning(serviceName));
        } catch (IOException ex) {
            //Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<ArrayList<String>> listProcessRunning() throws Exception {
        Process p = Runtime.getRuntime().exec(pathDirBin + "jps -l -m ");
        ArrayList<ArrayList<String>> processList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            ArrayList<String> arrayLine = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(line, " ");
            while (st.hasMoreTokens()) {
                arrayLine.add(st.nextToken());
            }
            processList.add(arrayLine);
            log.info(line);
        }
        return processList;
    }

    public static DefaultListModel<String> listProcessJarRunning() throws Exception {

        DefaultListModel<String> listModel = new DefaultListModel<>();
        Process p = Runtime.getRuntime().exec(pathDirBin + "jps -l -m ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(".jar")) {
                listModel.addElement(line);
            }
        }

        return listModel;
    }

    static public ArrayList<String> pidListProcess(ArrayList<ArrayList<String>> processList) {
        ArrayList<String> pidList = new ArrayList<>();
        for (ArrayList<String> pl : processList) {
            if (pl.size() > 0) {
                pidList.add(pl.get(0));
            }
        }
        return pidList;
    }

    static public ArrayList<String> jarListProcess(ArrayList<ArrayList<String>> processList) {
        ArrayList<String> pidList = new ArrayList<>();
        for (ArrayList<String> pl : processList) {
            if (pl.size() > 1) {
                pidList.add(pl.get(1));
            }
        }
        return pidList;
    }

    public static void killProcess(String serviceName) throws Exception {

        Runtime.getRuntime().exec(KILL + serviceName);

    }

    public static void kill() throws IOException {

        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0) {
            // Windows Commands
            Runtime.getRuntime().exec("cmd /C TASKKILL /f /FI \"Windowtitle eq gestred\"");
        } else {
            // Linux Commands

        }
    }

    public static void killPID(String PID) throws IOException {

        Runtime.getRuntime().exec(KILLPID + PID);

    }

    public static void startJarCMD(String jarName) throws IOException {

        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0) {
            // Windows Commands
            //Runtime.getRuntime().exec("cmd /C start \"gestred\" java -jar "+"C:/sarlab/GESSARLAB.jar");
            //Runtime.getRuntime().exec("cmd /C start \"gestred\" java -jar "+pathDirDaemon+jarName);
            Runtime.getRuntime().exec("cmd /C start \"" + pathDirDaemon + jarName + "\" java -jar " + pathDirDaemon + jarName);
        } else {
            // Linux Commands
            //Process p = Runtime.getRuntime().exec(new String[]{"sh","-c","cat /home/narek/pk.txt"});
            Runtime.getRuntime().exec("xterm -T " + pathDirDaemon + jarName + " -e java -jar " + pathDirDaemon + jarName);
        }
    }

    public static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    public static boolean isMac() {

        return (OS.indexOf("mac") >= 0);

    }

    public static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

    }

    public static boolean isSolaris() {

        return (OS.indexOf("sunos") >= 0);

    }

    public static void main(String args[]) throws Exception {

        String processName;
        if (isWindows()) {
            processName = "notepad.exe";
        } else {
            processName = "bash";
        }
        initProperties();
        printProperties();
        log.info("El proceso " + processName + " esta en ejecucion " + isProcessRunning(processName));
        if (isWindows()) {
            Runtime.getRuntime().exec(pathDirBin + "java -jar C:/sarlab/init.d/GESSARLAB.jar");
            log.info(pidJarProcessRunning("C:/sarlab/init.d/GESSARLAB.jar"));
            killJarProcessRunning("C:/sarlab/init.d/GESSARLAB.jar");
        } else {
            Runtime.getRuntime().exec("xterm -T /home/pi/App/Arduino1.jar -e java -jar /home/pi/App/Arduino1.jar");
            log.info(pidJarProcessRunning("/home/pi/App/Arduino1.jar"));
            killJarProcessRunning("/home/pi/App/Arduino1.jar");
        }
        processList = listProcessRunning();
        ArrayList<String> a1 = pidListProcess(processList);
        ArrayList<String> a2 = jarListProcess(processList);
        startJarCMD("GESSARLAB.jar");

        //System.out.print(pidProcessRunning(new String[]{"C:/sarlab/GESSARLAB.jar","marco","luis","pepa"}));
        //killPID(pidProcessRunning(new String[]{"C:/sarlab/GESSARLAB.jar","pepe","juan"}));
        //killJarProcessRunning("C:/sarlab/init.d/GESSARLAB.jar");    
        //kill();
        //start();
        //if (isProcessRunning(processName)) 
        // killProcess(processName);
        //}
    }

}
