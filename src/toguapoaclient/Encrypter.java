package toguapoaclient;

import java.util.HashMap;
import java.util.Map;

public class Encrypter {

    // Atributes
    private HashMap<Character, Integer> rule = new HashMap<Character, Integer>();
    private HashMap<Integer, Character> inverserule = new HashMap<Integer, Character>();

    // constructor
    public Encrypter() {
        String alphabet = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ /*_~1234567890+-.,?¿!;@#=$()[]";
        int num = 1;
        int index = 0;

        do {
            if (isPrime(num)) {
                rule.put(alphabet.charAt(index), num);
                index++;
            }
            num++;
        } while (index < alphabet.length());
        for (Map.Entry<Character, Integer> entry : rule.entrySet()) {
            this.inverserule.put(entry.getValue(), entry.getKey());
        }

    }

    public HashMap<Character, Integer> getRule() {
        return rule;
    }

    public HashMap<Integer, Character> getInverserule() {
        return inverserule;
    }

    public static boolean isPrime(int num) {
        boolean prime = true;
        for (int i = 2; i <= num / 2; i++) {
            // Si es divisible por cualquiera de estos números, no
            // es primo
            if (num % i == 0) {
                prime = false;
            }
        }
        return prime;
    }

    /**
     * @param word
     * @return
     */
    public String encrypt(String word, HashMap<Character, Integer> rule) {
        String encryptedWord = "";
        for (int i = 0; i < word.length(); i++) {

            encryptedWord += rule.get(word.charAt(i)) + " ";
        }
        encryptedWord.trim();

        return encryptedWord;
    }

    /**
     * @param word
     * @return
     */
    public String inverseEncrypt(String encryptedWord, HashMap<Integer, Character> inverserule) {
        String[] separatedencryptedWord = encryptedWord.split(" ");
        String word = "";

        for (int i = 0; i < separatedencryptedWord.length; i++) {
            word += inverserule.get(Integer.parseInt(separatedencryptedWord[i]));
        }
        return word;

    }

}
