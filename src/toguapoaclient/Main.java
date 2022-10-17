/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package toguapoaclient;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author vdiazbus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ConnectException, IOException {
        ClientManager manager = new ClientManager();

        // The manager attempts to conect to the local server
        if(!manager.connect()){
            throw new ConnectException("Server connection failed");
        }


        Scanner scanner = new Scanner(System.in);
        String myMessage;
        // The message that the user intents to send is read. This is performed by a Deamon
        // in order to keep the ability to read messages while writing one.
        while(true){
            myMessage = scanner.nextLine();
            manager.write(myMessage);
        }
    }
    
}
