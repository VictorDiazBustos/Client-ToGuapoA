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
    public static void main(String[] args) throws Exception {
        //The user has to enter the Port and the Host to connect to a server.
        Scanner scanner = new Scanner(System.in);
        ClientManager manager;
        int port;
        String host;

        // Try conection
        do{
            // Get port
            port = -1;
            do{
                try{
                    System.out.print("Please enter the Port: ");
                    port = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e){
                    System.out.println("Invalid format for port.\n");
                }
            } while (port == -1);

            // Get host
            System.out.print("Please enter the Host: ");
            host = scanner.nextLine();

            manager = new ClientManager(port, host);

            if(!manager.isConnected())
                System.out.println("Invalid connection.\n");
        }while(!manager.isConnected());

        //The user has to enter whether they want to login or register
        int option = -1;
        
        do{
            try{
                System.out.println("Please choose whether you want to login or register");
                System.out.print("Enter (1) for login or (2) for register: ");
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Invalid option selected.\n");
            }
        }while (option != 1 && option != 2);
        
        // Try login/register
        boolean access = false;
        while(!access){
            System.out.println("Please enter your username");
            String username = scanner.nextLine();
            System.out.println("Please enter your password");
            String password = scanner.nextLine();

            if(option==1)
                access = manager.login(username, password);
            
            else if (option == 2)
                access = manager.register(username, password);
        }

        // Start reader daemon
        ReaderDaemon reader = new ReaderDaemon(manager);

        String myMessage;
        // The message that the user intents to send is read. This is performed by a
        // Deamon
        // in order to keep the ability to read messages while writing one.
        while (true) {
            myMessage = scanner.nextLine();
            manager.write(myMessage);
        }
    }
}
