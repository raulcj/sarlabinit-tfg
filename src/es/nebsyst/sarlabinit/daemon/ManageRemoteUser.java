/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nebsyst.sarlabinit.daemon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Luis de la Torre
 */
public class ManageRemoteUser {
    
    private final static boolean LIMITWRITEACCESS = false;
    
    public static void main(String[] args) throws IOException {
        final int argc = args.length;
    }
    
    public void InstallUser(String username) {
        try {
            try {
                ProcessBuilder pb = new ProcessBuilder();
                pb.redirectErrorStream(true);
                // Disable asking for password when returning from sleep mode
                String[] comm1 = {"cmd", "/c", "powercfg", "/SETACVALUEINDEX", "SCHEME_CURRENT", "SUB_NONE", "CONSOLELOCK", "0"};
                pb.command(comm1);
                Process process = pb.start();
                process.waitFor();
                process.destroy();
                String[] comm2 = {"cmd", "/c", "powercfg", "/SETDCVALUEINDEX", "SCHEME_CURRENT", "SUB_NONE", "CONSOLELOCK", "0"};
                pb.command(comm2);
                process = pb.start();
                process.waitFor();
                process.destroy();
                // Create and initialize user account for remote desktop connections
                String[] comm3 = {"cmd", "/c", "net", "user", username, username, "/add", "/active:yes"};
                pb.command(comm3);
                process = pb.start();
                process.waitFor();
                process.destroy();
                // Run a process as the new user to create the associated profile and user ditectory
                String[] comm4 = {"cmd", "/c", "C:\\sarlab\\bin\\psexec.exe", "-u", username, "-p", username, "-x", "cmd.exe", "/c", "/exit"};
                pb.command(comm4);
                process = pb.start();
                process.waitFor();
                process.destroy();
                if (LIMITWRITEACCESS) {
                    // Deny write access for the created user
                    String[] comm5 = {"cmd", "/c", "icacls", "c:\\*", "/c", "/deny", username+":w"};
                    pb.command(comm5);
                    Process processIcacls1 = pb.start();
                    processIcacls1.waitFor();
                    processIcacls1.destroy();
                    String[] comm6 = {"cmd", "/c", "icacls", "d:\\*", "/c", "/deny", username+":w"};
                    pb.command(comm6);
                    Process processIcacls2 = pb.start();
                    processIcacls2.waitFor();
                    processIcacls2.destroy();
                }
                // Enable auto logon with the created user
                String[] comm7 = {"cmd", "/c", "REG", "ADD", "HKLM\\Software\\Microsoft\\Windows NT\\CurrentVersion\\Winlogon", "/v", "AutoAdminLogon", "/t", "REG_SZ", "/d", "1", "/f"};
                pb.command(comm7);
                process = pb.start();
                process.waitFor();
                process.destroy();
                String[] comm8 = {"cmd", "/c", "REG", "ADD", "HKLM\\Software\\Microsoft\\Windows NT\\CurrentVersion\\Winlogon", "/v", "DefaultDomainName", "/t", "REG_SZ", "/d", "\"\"", "/f"};
                pb.command(comm8);
                process = pb.start();
                process.waitFor();
                process.destroy();
                String[] comm9 = {"cmd", "/c", "REG", "ADD", "HKLM\\Software\\Microsoft\\Windows NT\\CurrentVersion\\Winlogon", "/v", "DefaultUserName", "/t", "REG_SZ", "/d", username, "/f"};
                pb.command(comm9);
                process = pb.start();
                process.waitFor();
                process.destroy();
                String[] comm10 = {"cmd", "/c", "REG", "ADD", "HKLM\\Software\\Microsoft\\Windows NT\\CurrentVersion\\Winlogon", "/v", "DefaultPassword", "/t", "REG_SZ", "/d", username, "/f"};
                pb.command(comm10);
                process = pb.start();
                process.waitFor();
                process.destroy();
                Thread.sleep(5000);
                // Copy files in user directory for restore
                String[] command11 = {"cmd", "/c", "robocopy", "C:\\Users\\"+username, "C:\\sarlab\\userfiles\\"+username, "/xjd", "/mir", "/xd", "AppData"};
                pb.command(command11);
                pb.start();
            } catch (InterruptedException ex) {
            }
        } catch (IOException ex) {
        }
    }
    
    public void UninstallUser(String username) {
        try {
            try {
                ProcessBuilder pb = new ProcessBuilder();
                pb.redirectErrorStream(true);
                // Disable auto logon
                String[] comm1 = {"cmd", "/c", "REG", "ADD", "HKLM\\Software\\Microsoft\\Windows NT\\CurrentVersion\\Winlogon", "/v", "AutoAdminLogon", "/t", "REG_SZ", "/d", "0", "/f"};
                pb.command(comm1);
                Process process = pb.start();
                process.waitFor();
                process.destroy();
                // Enable asking for password when returning from sleep mode
                String[] comm2 = {"cmd", "/c", "powercfg", "/SETACVALUEINDEX", "SCHEME_CURRENT", "SUB_NONE", "CONSOLELOCK", "1"};
                pb.command(comm2);
                process = pb.start();
                process.waitFor();
                process.destroy();
                String[] comm3 = {"cmd", "/c", "powercfg", "/SETDCVALUEINDEX", "SCHEME_CURRENT", "SUB_NONE", "CONSOLELOCK", "1"};
                pb.command(comm3);
                process = pb.start();
                process.waitFor();
                process.destroy();
                // Delete created user account files
                String stringPath = "C:\\Users\\"+username;
                Path path = Paths.get(stringPath);
                if (Files.exists(path)) {
                    String[] comm4 = {"cmd", "/c", "del", "/f", "/s", "/q", stringPath, ">", "nul"};
                    pb.command(comm4);
                    process = pb.start();
                    process.waitFor();
                    process.destroy();
                    String[] comm5 = {"cmd", "/c", "rmdir", "/s", "/q", stringPath};
                    pb.command(comm5);
                    process = pb.start();
                    process.waitFor();
                    process.destroy();
                }
                // Delete created user account for remote desktop connections
                String[] comm6 = {"cmd", "/c", "net", "user", username, "/delete"};
                pb.command(comm6);
                process = pb.start();
                process.waitFor();
                process.destroy();
            } catch (InterruptedException ex) {}
        } catch (IOException ex) {
        }
    }
    
}
