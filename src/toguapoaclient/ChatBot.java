package toguapoaclient;

import java.lang.Math;
import java.util.logging.Logger;

public class ChatBot extends Thread {
    private static ClientManager  manager;
    private static Logger log;

    public static String answerBot(String entry, String[] jokes, String[] funFacts) {
        int randomNumber;
        entry = entry.toUpperCase();
        if (entry.contains("JOKE")) {
            randomNumber = (int) Math.random() * jokes.length;
            return jokes[randomNumber];
        }

        else if (entry.contains("FUNFACTS")) {
            randomNumber = (int) Math.random() * funFacts.length;
            return funFacts[randomNumber];

        }
        return "";
    }

    public static void main(String[] args) {
        log = Logger.getLogger(ClientManager.class.getName());
        String joke1 = "- Hello, I'm Rosa.\n" +
                "- Oh, sorry, I'm color blind.";
        String joke2 = "¿Hola?\n" +
                "- Hello.\n" +
                "- ¿Is it here where you wash clothes?\n" +
                "- No.\n" +
                "- You are so dirty then.";
        String joke3 = "How did the telephone propose to its girlfriend? He gave her a ring.";

        String[] jokes = { joke1, joke2, joke3 };

        // Datos curiosos
        String funFact1 = "Did you know that the longest living insect is the termite queen/king, they can survive up to 50 years!";
        String funFact2 = "Did you know that the biggest satellite in the solar system is the moon?";
        String funFact3 = "Did you know that there are more weels than doors in the world?";
        String funFact4 = "Did you know that lighters were invented before matchs?";
        String funFact5 = "did you know plancton is the biggest contributor to Oxigen(O2) generation in the planet?";

        String funFacts[] = { funFact1, funFact2, funFact3, funFact4, funFact5 };
        

        while(true){     
            String line = manager.read();  

            try {
                manager.write(answerBot(line, jokes, funFacts));
            } catch (WriteException e) {
                log.info(e.getMessage());
            }
        }
    }
}
