package toguapoaclient;

import java.io.IOException;
import java.lang.Math;
import java.util.logging.Logger;
import java.util.ArrayList;

public class ChatBot {
    private ClientManager manager;
    private Logger log;
    private ArrayList<String> jokes;
    private ArrayList<String> funFacts;

    public ChatBot() {
        this.log = Logger.getLogger(ClientManager.class.getName());
        String joke1 = "- Hello, I'm Rosa.\n" +
                "- Oh, sorry, I'm color blind.";
        String joke2 = "¿Hola?\n" +
                "- Hello.\n" +
                "- ¿Is it here where you wash clothes?\n" +
                "- No.\n" +
                "- You are so dirty then.";
        String joke3 = "How did the telephone propose to its girlfriend? He gave her a ring.";

        this.jokes = new ArrayList();
        this.jokes.add(joke1);
        this.jokes.add(joke2);
        this.jokes.add(joke3);

        // Datos curiosos
        String funFact1 = "Did you know that the longest living insect is the termite queen/king, they can survive up to 50 years!";
        String funFact2 = "Did you know that the biggest satellite in the solar system is the moon?";
        String funFact3 = "Did you know that there are more weels than doors in the world?";
        String funFact4 = "Did you know that lighters were invented before matchs?";
        String funFact5 = "did you know plancton is the biggest contributor to Oxigen(O2) generation in the planet?";

        this.funFacts = new ArrayList();
        this.funFacts.add(funFact1);
        this.funFacts.add(funFact2);
        this.funFacts.add(funFact3);
        this.funFacts.add(funFact4);
        this.funFacts.add(funFact5);
    }

    public String answerBot(String entry) {
        String answer = "";
        int randomNumber;
        entry = entry.toUpperCase();
        if (entry.contains("JOKE")) {
            randomNumber = (int) Math.random() * this.jokes.size();
            answer = this.jokes.get(randomNumber);
        }

        else if (entry.contains("FUNFACTS")) {
            randomNumber = (int) Math.random() * this.funFacts.size();
            answer = this.funFacts.get(randomNumber);

        }
        return answer;
    }

    public void main(String[] args) {

        // The user has to enter the Port and the Host to connect to a server.
        int port = 49080;
        String host = "192.168.3.240";

        // Try conection
        do {
            manager = new ClientManager(port, host);

            if (!manager.isConnected())
                log.info("Chatbot invalid connection");
        } while (!manager.isConnected());

        // Try login/register
        boolean access = false;

        while (!access) {
            String username = "Chatbot";
            String password = "jpioj23ufh";
            // Try to login
            try {
                if (!(access = manager.login(username, password)))
                    // If can not login, try to register
                    access = manager.register(username, password);
            } catch (IOException e) {
                // TODO Auto-generated catch block
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
