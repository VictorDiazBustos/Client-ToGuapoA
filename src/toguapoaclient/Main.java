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
        
        // Try login/register
        boolean access = false;
        int option;
        while(!access){
            //The user has to enter whether they want to login or register
            option = -1;

            do{
                try{
                    // Show initial message
                    System.out.println(manager.read());
                    
                    // Show options from server
                    System.out.print("Enter (1) for login or (2) for register: ");
                    option = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e){
                    System.out.println("Invalid option selected.\n");
                }
            }while (option != 1 && option != 2);
        
            System.out.println("Please enter your username");
            String username = scanner.nextLine();
            System.out.println("Please enter your password");
            String password = scanner.nextLine();

            if(option==1)
                access = manager.login(username, password);
            
            else if (option == 2)
                access = manager.register(username, password);
        }
        
        System.out.println("*Connected successfully\n");

        while (true){
            // Join chat
            option = -1;


            boolean chat_accessed = false;
            while(!chat_accessed){
                do{
                    try{
                        // Show initial message
                        System.out.print("Press '1' to join in a chat, '2' to list the available chats: ");
                        option = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println("Invalid option selected.\n");
                    }
                }while (option != 1 && option != 2);

                if(option == 1){
                    // Select chat room
                    System.out.print("Select the chat room: ");
                    int chat_room = Integer.parseInt(scanner.nextLine());
                    chat_accessed = manager.joinChat(chat_room);
                }
                else if (option == 2){
                    System.out.println(manager.listChatRooms());
                }
            }

            System.out.println("*Chat joined. \nInsert '/LEAVE' to leave the chat room, \n'/JOIN to join in other chat room, \n'/LIST' to list the available chats.\n");

            // Start reader daemon
            ReaderDaemon reader = new ReaderDaemon(manager);

            String myMessage;
            // The message that the user intents to send is read. This is performed by a
            // Deamon in order to keep the ability to read messages while writing one.
            while (chat_accessed) {
                myMessage = scanner.nextLine();
                manager.write(myMessage);
                if (myMessage.equals("/LEAVE"));
                    chat_accessed = false;
            }

            // Kill reader daemon
            reader.stopProcess();
        }
    }
}
