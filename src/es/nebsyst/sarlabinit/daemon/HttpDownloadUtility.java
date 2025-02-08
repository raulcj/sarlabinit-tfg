/*
 * 
 */
package es.nebsyst.sarlabinit.daemon;

/**
 *
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.getProperty;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;

/**
 *
 *
 *
 */
public class HttpDownloadUtility {

    private final static Logger log = Logger.getLogger(HttpDownloadUtility.class);

    private static final int BUFFER_SIZE = 4096;

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String sarlabDir;   //Directorio de trabajo de sarlab

    public String getSarlabDir() {
        return sarlabDir;
    }

    public void setSarlabDir(String sarlabDir) {
        this.sarlabDir = sarlabDir;
    }

    public static String giveMeJarFileDir(String urlFile) {
        //https://sidilab.nebsyst.com/pluginfile.php/75/mod_ejsapp/compressed/14/carga_cond_def7M.jar
        String aux;
        //String aux=urlFile.substring(urlFile.indexOf("/jarfiles")+9, urlFile.length());
        if (urlFile.startsWith("http")) {
            if (urlFile.indexOf("https://") != -1) {
                aux = urlFile.substring(urlFile.indexOf("https://") + 7, urlFile.length());
            } else {
                aux = urlFile.substring(urlFile.indexOf("http://") + 6, urlFile.length());
            }
        } else {
            aux = isWindows() ? urlFile.replace("C:/sarlab/jarfile", "") : urlFile.replace("/sarlab/jarfile", "");
            return aux;
        }
        return aux;
    }

    public static String giveMeServer(String urlFile) {

        String[] tokens = urlFile.split("/");
        return tokens[2];
    }

    public static String giveMeURLServer(String urlFile) {
        if (urlFile.indexOf("https://") != -1) {
            return "https://" + giveMeServer(urlFile);
        } else {
            return "http://" + giveMeServer(urlFile);
        }
    }

    /**
     * Crea el directorio de almacenamiento de los jar si no existe
     *
     * @param fileURL el URL de desacarga del fichero
     *
     */
    public static String createDitEJSApp(String fileURL) {
        try {
            String server;      //Nombre canonico del servidor
            String fileName;    //Nom bre del fichero
            String saveEJSApp;  //Directorio ejsApp
            fileURL = fileURL.trim();
            fileURL = giveMeJarFileDir(fileURL);
            fileName = fileURL.substring(fileURL.lastIndexOf('/') + 1, fileURL.length());
            saveEJSApp = fileURL.substring(0, fileURL.indexOf(fileName) - 1);
            //System.out.println("Cadena = "+saveEJSApp+" "+fileName); 
            if (isWindows()) {
                sarlabDir = "C:/sarlab/jarfile" + saveEJSApp;
            }
            if (isUnix()) {
                sarlabDir = "/sarlab/jarfile" + saveEJSApp;
            }
            //System.out.println("Cadena = "+sarlabDir); 
            if (!Files.exists(Paths.get(sarlabDir)));
            Files.createDirectories(Paths.get(sarlabDir));
            return sarlabDir;
        } catch (IOException ex) {
            log.error("Error IOException createDitEJSApp");
            return null;
        }
    }

    /**
     * Downloads a file from a URL
     *
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     *
     */
    public static void downloadFileHttpConnection(String fileURL, String saveDir) {
        try {
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();
                if (disposition != null) {
                    // extracts file name from header field
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 10,
                                disposition.length() - 1);
                    }
                } else {
                    // extracts file name from URL
                    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                            fileURL.length());
                }

                //System.out.println("Content-Type = " + contentType);
                //System.out.println("Content-Disposition = " + disposition);
                //System.out.println("Content-Length = " + contentLength);
                //System.out.println("fileName = " + fileName);
                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;

                // opens an output stream to save into file
                FileOutputStream outputStream = new FileOutputStream(saveFilePath);

                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                inputStream.close();

                log.info("File downloaded");
            } else {
                log.info("No file to download. Server replied HTTP code: " + responseCode);
            }
            httpConn.disconnect();
        } catch (MalformedURLException ex) {
            //Logger.getLogger(HttpDownloadUtility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(HttpDownloadUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean verifyJarFile(String fileURL) {

        String pathDirDaemon;

        try {
            if (isWindows()) {
                // Windows Commands
                pathDirDaemon = "C:/sarlab/jarfile" + giveMeJarFileDir(fileURL);
                //System.out.println(pathDirDaemon);

            } else {
                // Linux Commands
                pathDirDaemon = "/sarlab/jarfile" + giveMeJarFileDir(fileURL);
            }
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String date = httpConn.getHeaderField("Date");
                String last_Modified = httpConn.getHeaderField("Last-Modified");
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();
                int endChar = pathDirDaemon.indexOf(".jar");
                if (endChar == -1) {
                    return false;
                }
                fileName = pathDirDaemon.substring(0, endChar);
                fileName += ".ver";
                //System.out.println("Date = "+date);
                //System.out.println("Last-Modified = "+last_Modified);
                //System.out.println("Content-Type = " + contentType);
                //System.out.println("Content-Disposition = " + disposition);
                //System.out.println("Content-Length = " + contentLength);
                //System.out.println("fileName = " + fileName);
                Properties prop = new Properties();
                InputStream is = null;
                is = new FileInputStream(fileName);
                prop.load(is);
                if (last_Modified.equals(prop.getProperty("Last-Modified"))) {
                    if (contentType.equals(prop.getProperty("Content-Type"))) {
                        if (contentLength == Integer.parseInt(prop.getProperty("Content-Length"))) {
                            return true;
                        }
                    }
                }
                is.close();
            }
        } catch (MalformedURLException ex) {
            //Logger.getLogger(HttpDownloadUtility.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            // Logger.getLogger(HttpDownloadUtility.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public static boolean createVerifyJarFile(String fileURL) {

        String pathDirDaemon;

        try {
            if (isWindows()) {
                // Windows Commands
                pathDirDaemon = "C:/sarlab/jarfile" + giveMeJarFileDir(fileURL);
                //System.out.println(pathDirDaemon);

            } else {
                // Linux Commands
                pathDirDaemon = "/sarlab/jarfile" + giveMeJarFileDir(fileURL);
            }
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String date = httpConn.getHeaderField("Date");
                String last_Modified = httpConn.getHeaderField("Last-Modified");
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();
                int endChar = pathDirDaemon.indexOf(".jar");
                if (endChar == -1) {
                    return false;
                }
                fileName = pathDirDaemon.substring(0, endChar);
                fileName += ".ver";

                //System.out.println("Date = "+date);
                //System.out.println("Last-Modified = "+last_Modified);
                //System.out.println("Content-Type = " + contentType);
                //System.out.println("Content-Disposition = " + disposition);
                //System.out.println("Content-Length = " + contentLength);
                //System.out.println("fileName = " + fileName);
                Properties prop = new Properties();
                OutputStream is = null;
                is = new FileOutputStream(fileName);
                prop.setProperty("Content-Type", contentType);
                prop.setProperty("Date", date);
                prop.setProperty("Last-Modified", last_Modified);
                prop.setProperty("Content-Length", Integer.toString(contentLength));
                prop.store(is, "Designed by sarlabinit ");
                is.close();
                return true;
            }
        } catch (MalformedURLException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return false;
    }

    public static void downloadFileURL(String fileURL, String saveDir) {
        log.info("opening connection download " + fileURL);
        try {
            // extracts file name from URL
            String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
            String saveFilePath = saveDir + File.separator + fileName;
            URL url = new URL(fileURL);
            InputStream in = url.openStream();
            //FileOutputStream fos = new FileOutputStream(new File("C:/sarlab/SIGNED_condensador1.jar"));
            FileOutputStream fos = new FileOutputStream(saveFilePath);
            //System.out.println("reading from resource and writing to file...");
            int length = -1;
            byte[] buffer = new byte[BUFFER_SIZE];// buffer for portion of data from connection
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();
            log.info("File downloaded");
        } catch (MalformedURLException ex) {
            log.info("Error 101 MalformedURLException");
        } catch (IOException ex) {
            log.info("Error 100 IOException");
        }
    }

    public static void downloadFile(String fileURL, String saveDir) {
        try {
            // extracts file name from URL
            String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
            //String saveFilePath = saveDir + File.separator + fileName;
            String saveFilePath = saveDir + "/" + fileName;
            System.out.println("Directorio = " + saveFilePath + " " + fileURL);
            //URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/c/cb/The_Green_and_Golden_Bell_Frog.jpg");
            URL url = new URL(fileURL);
            InputStream in = url.openStream();
            //Files.copy(in, Paths.get("someFile.jpg"), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(in, Paths.get(saveFilePath), StandardCopyOption.REPLACE_EXISTING);
            in.close();
        } catch (MalformedURLException ex) {
            log.error("Error MalformedURLException downloadFile");
            //Logger.getLogger(HttpDownloadUtility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(HttpDownloadUtility.class.getName()).log(Level.SEVERE, null, ex);
            log.error("Error IOException downloadFile");
        }

    }

    public static void downloadFile(String fileURL) {
        downloadFile(fileURL, createDitEJSApp(fileURL));
    }

    public static void startJarCMD(String urlJarName, String param) {

        String pathDirDaemon;

        if (isWindows()) {
            // Windows Commands
            pathDirDaemon = "C:/sarlab/jarfile" + giveMeJarFileDir(urlJarName);
            System.out.println(pathDirDaemon);
            File fichero = new File(pathDirDaemon);
            if (!fichero.exists()) {
                if (createVerifyJarFile(urlJarName)) {
                    System.out.println(pathDirDaemon);
                }
                downloadFile(urlJarName);

            } else {
                if (!verifyJarFile(urlJarName)) {
                    if (createVerifyJarFile(urlJarName)) {
                        downloadFile(urlJarName);
                    }
                }

            }

            try {
                Runtime.getRuntime().exec("cmd /C start \"" + pathDirDaemon + "\" C:/sarlab/jdk/bin/java -jar " + pathDirDaemon + " " + param);
                log.info("cmd /C start \"" + pathDirDaemon + "\" C:/sarlab/jdk/bin/java -jar " + pathDirDaemon + " ");
                log.debug("cmd /C start \"" + pathDirDaemon + "\" C:/sarlab/jdk/bin/java -jar " + pathDirDaemon + " " + param);
            } catch (IOException ex) {
                // Logger.getLogger(HttpDownloadUtility.class.getName()).log(Level.SEVERE, null, ex);
                log.info("Error 103 IOException");
            }
        } else {
            // Linux Commands
            pathDirDaemon = "/sarlab/jarfile" + giveMeJarFileDir(urlJarName);
            //Process p = Runtime.getRuntime().exec(new String[]{"sh","-c","cat /home/narek/pk.txt"});

            File fichero = new File(pathDirDaemon);
            if (!fichero.exists()) {
                //System.out.println("Pues va a ser que no");
                downloadFile(urlJarName);
            }
            try {
                Runtime.getRuntime().exec("xterm -T " + pathDirDaemon + " -e java -jar " + pathDirDaemon + " " + param);
            } catch (IOException ex) {
                //Logger.getLogger(HttpDownloadUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public static void main(String[] args) throws MalformedURLException, IOException {
        //HttpDownloadUtility.downloadFile("https://sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar");

        System.out.println(HttpDownloadUtility.giveMeJarFileDir("https://sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar"));
        System.out.println(HttpDownloadUtility.giveMeServer("https://sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar"));
        System.out.println(HttpDownloadUtility.giveMeURLServer("https://sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar"));

        //HttpDownloadUtility.createDitEJSApp("https://sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar");
        //HttpDownloadUtility.downloadFile("https://sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar");
        startJarCMD("https://sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar", "150.214.163.142 443 cisco cisco sidilab_condensadores_01 60");
        //startJarCMD("C:/sarlab/jarfile/sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar", "150.214.163.142 443 cisco cisco sidilab_condensadores_01 60");

        //createVerifyJarFile("https://sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar");
        //System.out.println(verifyJarFile("https://sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar"));
    }

}
