/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vdiazbus
 */

package toguapoaclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// This class conects reads and writes from the machine to a local server to provide message exchange.
public class ClientManager {
    // SERVER PORT AND HOST
    final int PORT = 49080;
    final String HOST = "127.0.0.1";
    Socket sc;
    DataInputStream in;
    DataOutputStream out;

    public ClientManager() {

    }

    /**
     * This method conects the machine to the server via Socket
     * If the conection is succesfull the machine will be able to write and
     * read Strings from and to the server.
     *
     * @return Returns True if the conection is succesfull, if the conection fails,
     *         the method will return false.
     */

    public boolean connect() {
        boolean connected;

        try {
            sc = new Socket(this.HOST, this.PORT);
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            connected = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            connected = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            connected = false;
        }

        return connected;
    }

    /**
     * @param message Phrase written by the client
     * 
     *This method read a String written by the client from the
     *and writes it in the interface created by the server.
     *
     *
     */

    public void write(String message) throws IOException {
        out.writeUTF(message);
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
            line = in.readUTF();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return line;
    }
}
