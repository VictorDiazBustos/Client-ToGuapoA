package toguapoaclient;

import java.util.Scanner;

public class prueba {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Encrypter encripter = new Encrypter();

        System.out.println(encripter.getInverserule());
        while (true) {
            String word = scanner.nextLine();

            System.out.println(encripter.encrypt(word, encripter.getRule()));
            String encriptedword = encripter.encrypt(word, encripter.getRule());
            System.out.println(encripter.inverseEncrypt(encriptedword, encripter.getInverserule()));
        }
    }
}