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
    //final int PORT = 49080;
    //final String HOST = "192.168.3.240";
    private int PORT;
    private String HOST = null;
    private Socket sc;
    private PrintStream toServer;
    private BufferedReader fromServer;
    private boolean connected;
    
    //LOGGER
    private Logger log;

    public ClientManager(int port, String host) {
        this.PORT = port;
        this.HOST = host;
        this.connected = false;
        
        // Create logger
        try {
            log = Logger.getLogger(ClientManager.class.getName());
            log.addHandler(new FileHandler("log.txt"));
        } catch (IOException e) {
            log.severe( e.getMessage());
        } catch (SecurityException e) {
            log.severe(e.getMessage());
        }
        
        // Try connexion
        try {
            log.info("Connecting...");
            sc = new Socket(HOST, PORT);
            toServer = new PrintStream(sc.getOutputStream());
            fromServer = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            log.info("Connected successfully");
            connected = true;
        } catch (IOException e) {
            log.severe("Error while trying to connect to the server");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            log.severe("Error while trying to connect to the server");
            System.out.println(e.getMessage());
        }
    }

    public ClientManager(Socket sc, PrintStream toServer, BufferedReader fromServer) {
        this.connected = false;
        
        // Create logger
        try {
            log = Logger.getLogger(ClientManager.class.getName());
            log.addHandler(new FileHandler("log.txt"));
        } catch (IOException | SecurityException e) {
            System.out.println(e.getMessage());
        }
        
        // Retry connexion
        try{
            log.info("Connecting...");
            this.sc = sc;
            this.toServer = toServer;
            this.fromServer = fromServer;
            log.info("Connected successfully");
            connected = true;
        } catch (Exception e) {
            log.severe( "Error while trying to connect to the server");
            System.out.println(e.getMessage());
        }
        this.PORT = 0;
    }

    /**
     * Getter for atribute connected
     *
     * @return The value of the atribute connected
     */
    public boolean isConnected(){
        return this.connected;
    }

    /**
     * @param message Phrase written by the client
     * @throws WriteException
     *This method read a String written by the client from the
     *and writes it in the interface created by the server.
     */
    public void write(String message) throws WriteException {
        try{
            this.toServer.println(message);
            this.toServer.flush();
            log.log(Level.INFO, "Writtinng message: {0}", message);
        } catch (Exception e){
            log.severe(e.getMessage());
            throw new WriteException();
        }
    }

    /**
     * This method reads a String from the
     * server and returns the readed String.
     *
     * @return String The message that the server sent.
     *
     */
    public String read() {
        String line = "";

        try {
            line = fromServer.readLine();
            log.log(Level.INFO, "Received message{0}", line);
        } catch (IOException e) {
            log.severe(e.getMessage());
        }

        return line;
    }
    
    /**
     * This method reads a username and password
     * from the server and returns they are correct.
     *
     * @param username
     * @param password
     * @return Boolean True if credentials match
     * @throws IOException
     *
     */
    public boolean sendCredentials(String username, String password) throws IOException{
        // Server asks for username
        String server_message = read();
        if(server_message.toUpperCase().trim().equals("USER:"))
            this.toServer.println(username);

        // Server asks for password
        server_message = read();
        if(server_message.toUpperCase().trim().equals("PASSWORD:"))
            this.toServer.println(password);
        
        // Server answers 'successful' or 'Error'
        server_message = read();
        if (server_message.trim().toUpperCase().equals("SUCCESSFUL")){
            log.info("Crendentials matched succesfully");
            return true;
        }else{
            log.log(Level.SEVERE, "Wrong credentials: {0}", server_message);
            return false;
        }
    }

    /**
     * This method logs in a user into
     * the server
     *
     * @param username
     * @param password
     * @return Boolean True if login is succesful
     * @throws IOException
     *
     */
    public boolean login(String username, String password) throws IOException{
        // Send login request
        this.toServer.println("LOGIN");

        return sendCredentials(username, password);
    }

    /**
     * This method registers a user into
     * the server
     *
     * @param username
     * @param password
     * @return Boolean True if registration is succesful
     * @throws IOException
     *
     */
    public boolean register(String username, String password) throws IOException{
        // Send register request
        toServer.println("REGISTER");
        
        return sendCredentials(username, password);
    }
    /**
    * Calls /List to cause the server to list all available rooms.
    *
    * @return String of all available rooms
    */
    public String listChatRooms() throws WriteException{
        // Request server to create a new chat
        write("/LIST");
        String chat_rooms = "";
        
        try{
            chat_rooms = read();
        } catch (Exception e){
            log.severe("Error. Error listing chat room.");
        }

        return chat_rooms;
    }
    /**
    * Calls /join with an id number
    *
    * @return boolean: true or false depending whether the join was succesull or not.
    */
    public boolean joinChat(int chat_room) throws WriteException{
        // Request server to join the selected chat room
        boolean result;
        
        try{
            write("/JOIN " + chat_room);
            log.log(Level.INFO, read());
            result = true;
        } catch (Exception e){
            log.log(Level.SEVERE, "Error. Error joined chat room {0}.", chat_room);
            result = false;
        }

        return result;
    }
    
    public boolean joinChat(String chat_room) throws WriteException{
        // Request server to join the selected chat room
        boolean result;
        
        try{
            write("/JOIN " + chat_room);
            log.log(Level.INFO, read());
            result = true;
        } catch (Exception e){
            log.log(Level.SEVERE, "Error. Error joined chat room {0}.", chat_room);
            result = false;
        }

        return result;
    }
}

