/*
 * 
 */
package es.nebsyst.sarlabinit.daemon;

import es.nebsyst.sarlabinit.main.PanelJarProcessMain;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import org.apache.log4j.Logger;


/**
 *
 * @author marcoa
 */
public class ServerRuntime {
    
    private final static Logger log = Logger.getLogger(PanelJarProcessMain.class);
    
    public static boolean done;
    
    /**
     * Constructor por defecto. Hace todo lo que hace el ejemplo.
     */
    public ServerRuntime(){
        try ( // Se crea un socket servidor atendiendo a un determinado puerto.
            ServerSocket socket = new ServerSocket (8886)) {
            done=false;
            while (!done) {
                // Se acepata una conexión con un cliente. Esta llamada se
                // queda bloqueada hasta que se arranque el cliente.
                //System.out.println ("Esperando cliente");
                Socket client = socket.accept();
                //System.out.println ("Conectado con cliente de " + cliente.getInetAddress());
                start(client);
            }
            // Se cierra el socket encargado de aceptar clientes.
            socket.close();
        } catch (IOException ex) {
        }
    }
    
    private void start(Socket client) {
        ThreadClient threadClient = new ThreadClient(client);
        threadClient.start();
    }

    public static void setDone(boolean done) {
        ServerRuntime.done = done;
    }

    public static void main(String[] args) {
        // Se instancia la clase principal para ejecutar su constructor
        new ServerRuntime();
    }
    
    class ThreadClient extends Thread {
        Socket client;
        ParsingSarlabInit parsing;
        long t;
        private String result;
        
        public ThreadClient(Socket client) {
            this.client = client;
        }
        
        public void delay(int t){
            try {Thread.sleep(t);} catch (InterruptedException ex) {   }
        }

        @Override
        public void run() {
            try {
                client.setSoLinger (true, 10);
                // Se prepara un flujo de salida de datos simples con el socket.
                DataOutputStream bufferOut = new DataOutputStream (client.getOutputStream());
                DataInputStream bufferIn = new DataInputStream(client.getInputStream());
                parsing=new ParsingSarlabInit();
                parsing.setClient(bufferOut);
                bufferOut.writeUTF("Hola");
                t=System.currentTimeMillis()+60*1000;
                while(System.currentTimeMillis()<t){
                    if(bufferIn.available()>0){
                        String command=bufferIn.readUTF();
                        log.info("Recibido comando en Server Runtime ");
                        result = parsing.commandCom(command);
                        bufferOut.writeUTF(result);
                        break;
                   }
                }
                delay(500);
                // Se cierra el socket con el cliente.
                // La llamada anterior a setSoLinger() hará
                // que estos cierres esperen a que el cliente retire los datos.
                client.close();
            } catch (SocketException ex) {
                //Logger.getLogger(ServerRuntime.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(ServerRuntime.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }   
    }
    
}
