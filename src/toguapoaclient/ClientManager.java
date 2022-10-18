/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vdiazbus
 */

package toguapoaclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

// This class conects reads and writes from the machine to a local server to provide message exchange.
public class ClientManager {
    // SERVER PORT AND HOST
    final int PORT = 49080;
    final String HOST = "192.168.3.240";
    Socket sc;
    PrintStream toServer;
    BufferedReader fromServer;
    
    //LOGGER
    Logger log;

    public ClientManager() {
        try {
            log = Logger.getLogger(ClientManager.class.getName());
            log.addHandler(new FileHandler("log.txt"));
        } catch (IOException e) {
            log.log(java.util.logging.Level.SEVERE, e.getMessage());
        } catch (SecurityException e) {
            log.log(java.util.logging.Level.SEVERE, e.getMessage());
        }
        
        try {
            log.info("Connecting...");
            sc = new Socket(this.HOST, this.PORT);
            toServer = new PrintStream(sc.getOutputStream());
            fromServer = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            log.info("Connected successfully");
        } catch (IOException e) {
            log.log(java.util.logging.Level.SEVERE, "Error while trying to connect to the server");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error while trying to connect to the server");
            System.out.println(e.getMessage());
        }
    }

    public ClientManager(Socket sc, PrintStream toServer, BufferedReader fromServer) {
        try {
            log = Logger.getLogger(ClientManager.class.getName());
            log.addHandler(new FileHandler("log.txt"));
        } catch (IOException | SecurityException e) {
            System.out.println(e.getMessage());
        }
        
        log.info("Connecting...");
        this.sc = sc;
        this.toServer = toServer;
        this.fromServer = fromServer;
        log.info("Connected successfully");
    }

    /**
     * This method conects the machine to the server via Socket
     * If the conection is succesfull the machine will be able to write and
     * read Strings from and to the server.
     *
     * @return Returns True if the conection is succesfull, if the conection fails,
     *         the method will return false.
     */

    /**
     * @param message Phrase written by the client
     * 
     *This method read a String written by the client from the
     *and writes it in the interface created by the server.
     */

    public void write(String message) throws WriteException {
        try{
            this.toServer.println(message);
            log.log(Level.INFO, "Writtinng message: {0}", message);
        } catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            throw new WriteException();
        }
    }

    /**
     * This method read a String from the
     * server and returns the readed String.
     *
     * @return String The message that the server sent.
     *
     */
    public String read() {
        String line = "";

        try {
            line = fromServer.readLine();
            log.log(Level.INFO, "Reading message{0}", line);
        } catch (IOException e) {
            log.log(java.util.logging.Level.SEVERE, e.getMessage());
        }

        return line;
    }
    
}
