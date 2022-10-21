package toguapoaclient;

import java.io.IOException;
import java.lang.Math;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    private static ArrayList<String> jokes;
    private static ArrayList<String> funFacts;

    public static String answerBot(String entry) {
        String answer = "";
        int randomNumber;
        entry = entry.toUpperCase();
        if (entry.contains("JOKE")) {
            randomNumber = (int) Math.random() * jokes.size();
            answer = jokes.get(randomNumber);
        }

        else if (entry.contains("FUNFACTS")) {
            randomNumber = (int) Math.random() * funFacts.size();
            answer = funFacts.get(randomNumber);

        }
        return answer;
    }

    public static void main(String[] args) throws WriteException {
        jokes = new ArrayList<>();
        funFacts = new ArrayList<>();

        ClientManager manager;

        Logger log = Logger.getLogger(ClientManager.class.getName());

        jokes.add("- Hello, I'm Rosa.\n" +
                "- Oh, sorry, I'm color blind.");
        jokes.add("¿Hola?\n" +
                "- Hello.\n" +
                "- ¿Is it here where you wash clothes?\n" +
                "- No.\n" +
                "- You are so dirty then.");
        jokes.add("How did the telephone propose to its girlfriend? He gave her a ring.");

        // Fun facts
        funFacts.add(
                "Did you know that the longest living insect is the termite queen/king, they can survive up to 50 years!");
        funFacts.add("Did you know that the biggest satellite in the solar system is the moon?");
        funFacts.add("Did you know that there are more weels than doors in the world?");
        funFacts.add("Did you know that lighters were invented before matchs?");
        funFacts.add("did you know plancton is the biggest contributor to Oxigen(O2) generation in the planet?");

        // The user has to enter the Port and the Host to connect to a server.
        Scanner scanner = new Scanner(System.in);
        int port;
        String host;

        // Try conection
        do {
            // Get port
            port = -1;
            do {
                try {
                    System.out.print("Please enter the Port: ");
                    port = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid format for port.\n");
                }
            } while (port == -1);

            // Get host
            System.out.print("Please enter the Host: ");
            host = scanner.nextLine();

            manager = new ClientManager(port, host);

            if (!manager.isConnected())
                log.info("Chatbot invalid connection");
        } while (!manager.isConnected());

        // Try login/register
        boolean access = false;

        while (!access) {
            // Show initial message
            System.out.println(manager.read());
            
            
            String username = "Chatbot";
            String password = "jpioj23ufh";
            // Try to login
            try {
                System.out.println("Envio credenciales");
                
                // If can not login, try to register
                if (!(access = manager.login(username, password))){
                    // Show initial message
                    System.out.println(manager.read());
                    
                    access = manager.register(username, password);
                }
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }

        System.out.println("*Connected successfully\n");

        while (true) {
            // Join chat
            boolean showTime = false;
            boolean chat_accessed = false;
            while (!chat_accessed) {
                chat_accessed = manager.joinChat("GENERAL");
            }

            System.out.println(
                    "*Chat joined. \nInsert '/LEAVE' to leave the chat room, \n'/JOIN {chatroom}' to join in other chat room, \n'/LIST' to list the available chats.\n'/TIME {state}' to show the time on messages.");

            // Start reader daemon
            ReaderDaemon reader = new ReaderDaemon(manager, showTime);

            String myMessage;
            // The message that the user intents to send is read. This is performed by a
            // Deamon in order to keep the ability to read messages while writing one.
            String message;
            while (chat_accessed) {
                message = manager.read();
                answerBot(message);
            }
        }
    }
}
