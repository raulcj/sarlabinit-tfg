/*
 * 
 */
package es.nebsyst.sarlabinit.daemon;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;
import java.util.Arrays;

/**
 *
 * @author marcoa
 */
public class ParsingSarlabInit {

    private final static String OS = System.getProperty("os.name").toLowerCase();

    private final static Logger log = Logger.getLogger(ParsingSarlabInit.class);

    DataOutputStream bufferOut;

    String[] listCommands = {"", "print", "help", "cmd", "exec", "execjar", 
        "inuser", "unuser", "winconinfo", "close"};

    HashMap<String, Integer> commands;
    
    private final String USERNAME = "Remote";

    public ParsingSarlabInit() {
        /*
        if (isWindows()) {
            PropertyConfigurator.configure("C:/sarlab/config/log4jsarlabinit.properties");
        }
        if (isUnix()) {
            PropertyConfigurator.configure("/sarlab/config/log4jsarlabinit.properties");
        }*/
        this.bufferOut = null;
        this.commands = new HashMap<>();
        for (int i = 0; i < listCommands.length; i++) {
            commands.put(listCommands[i], i);
        }
    }

    public void setClient(DataOutputStream client) {
        this.bufferOut = client;
    }

    public int getIndCommand(String key) {
        if (commands.get(key) == null) {
            return -1;
        }
        return commands.get(key);
    }

    public static String[] tokensStrings(String phrase, String delims) {
        return phrase.split(delims);
    }

    public void delay(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException ex) {
            
        }
    }

    public String commandCom(String command) {
        String response = "";
        String param;
        int indCommand = -1;
        String[] tokens = tokensStrings(command, " ");
        /*int numParam = tokens.length - 1;
        for(int i=0;i<numParam;i++) System.out.println("Parametro "+tokens[i]);*/
        if (tokens[0] != null) {
            indCommand = getIndCommand(tokens[0]);
        }
        log.info("Comando " + tokens[0]);
        ManageRemoteUser manager;
        //String commandString;
        //log.info(indCommand);
        switch (indCommand) {
            case 1:
                if (bufferOut != null) {
                    // client.printSarlab();
                    // client.write("\n\r>");
                }
                break;
            case 2:
                //commandString = "help";
                help();
                break;
            case 3: 
                //commandString = "cmd";
                int maxparams = 5;
                tokens[maxparams - 2] = tokens[maxparams - 2].replaceAll(",", " ");
                String filename = tokens[maxparams - 2];                
                String ntimes = "1";
                if (tokens.length < maxparams) {
                    int last = filename.lastIndexOf(".");
                    if (last != 0) {
                        String extension = filename.substring(last + 1);
                        String[] exes = new String[]
                        {
                            "bat", "bin", "cmd", "com", "exe", "jse", "msc", "lnk", 
                            "reg", "vb", "vbe", "vbs", "vbscript", "jar"
                        };
                        if (!Arrays.stream(exes).anyMatch(extension::equals)) {
                            ntimes = "2";
                        }
                    }
                }
                String execTokens[] = {tokens[0], tokens[1], tokens[2], "\"\"", "\"" + tokens[3] + "\""};
                execProcess(execTokens);
                if (tokens.length < maxparams) {
                    response = callControlActiveWindow(ntimes);
                } else {
                    response = callControlActiveWindow(tokens[maxparams-1]);
                }
                break;
            case 4:
                //commandString = "exec";
                tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
                execProcess(tokens);
                break;
            case 5:
                //commandString = "execJar";
                if (tokens.length < 3) {
                    return response;
                }
                param = tokens[2];
                param = param.trim();
                execJarProcess(tokens[1], param);
                break;
            case 6:
                //commandString = "inuser";
                manager = new ManageRemoteUser();
                manager.InstallUser(USERNAME);
                break;
            case 7:
                //commandString = "unuser";
                manager = new ManageRemoteUser();
                manager.UninstallUser(USERNAME);
                break;
            case 8:
                //commandString = "winconinfo";
                response = callGetWinConInfo();
                break;
            case 9:
                //commandString = "close";
                callCloseActiveWindow();
                break;
            default:
                //commandString = "Invalid ";
                break;
        }
        //System.out.println(commandString);
        return response;
    }

    public void help() {
        if (bufferOut != null) {
            try {
                bufferOut.writeUTF(">command,parameter_1,parameter_2,.....,parameter_n \n\r>");
                bufferOut.flush();
            } catch (IOException ex) {
                // java.util.logging.Logger.getLogger(Parsing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void execProcess(String[] command) {
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            try {
                process.waitFor();
            } catch(InterruptedException ex) {
            }
        } catch (IOException ex) {
            if (bufferOut != null) {
                try {
                    bufferOut.writeUTF("Error " + ex.toString());
                } catch (IOException ex1) {
                }
            }
        }
    }
    
    private String callControlActiveWindow(String param) {
        String result = "";
        try {
            Process process = callWindowControl("config", param);
            BufferedReader breader = new BufferedReader(new InputStreamReader(process.getInputStream(), "ISO-8859-1"));
            String rline = breader.readLine();
            rline = rline != null ? rline.trim() : "";
            result = ">Window name = " + rline;
        } catch (IOException ex) {
            
        }
        return result;
    }
    
    private void callCloseActiveWindow() {
        callWindowControl("close");
    }
    
    private Process callWindowControl(String... option) {
        try {
            String[] command = {"C:\\sarlab\\bin\\WindowControl.exe", option[0]};
            if (option.length > 1) {
                command = Arrays.copyOf(command, command.length + 1);
                command[command.length - 1] = option[1];
            }
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            return process;
        } catch (IOException ex) {
            log.error("Error IOException");
            return null;
        }
    }
    
    private String callGetWinConInfo() {
        String result = "";
        try {
            String[] command = {"C:\\sarlab\\bin\\GetWindowControlInfo.exe"};
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader breader = new BufferedReader(new InputStreamReader(process.getInputStream(), "ISO-8859-1"));
            String next, rline = breader.readLine();
            for (boolean last = (rline == null); !last; rline = next) {
                rline = rline != null ? rline.trim() : "";
                last = ((next = breader.readLine()) == null);
                if (!last) {
                    result += ">File path = " + rline + "\n";
                } else {
                    result += ">Target window name = " + rline;
                }
            }
        } catch (IOException ex) {
            log.error("Error IOException");
        }
        return result;
    }

    public void loadJarProcess(String fileURL) {
        HttpDownloadUtility.downloadFile(fileURL);
    }

    public void execJarProcess(String fileURL, String param) {
        HttpDownloadUtility.startJarCMD(fileURL, param);
    }

    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    public static boolean isMac() {
        return (OS.contains("mac"));
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }

    public static boolean isSolaris() {
        return (OS.contains("sunos"));
    }

    public static void main(String[] args) throws IOException {
        ParsingSarlabInit parsing = new ParsingSarlabInit();
        //System.out.println("idComando = "+parsing.get("exec"));
        parsing.commandCom("exec:notepad.exe:kk.txt");
        parsing.commandCom("execjar,https://sidilab.nebsyst.com/jarfile/carga_cond_def7M.jar,150.214.163.142 443 cisco cisco sidilab_condensadores_01 60");
        //Runtime.getRuntime().exec("notepad.exe");
        String[] tokens = tokensStrings("sarlab", "\" \"");
        int numParam = tokens.length - 1;
        for (int i = 0; i < numParam; i++) {
            System.out.println("Parametro " + tokens[i]);
        }
    }

}
