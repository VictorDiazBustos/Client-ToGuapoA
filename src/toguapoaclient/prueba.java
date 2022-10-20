package toguapoaclient;

import java.util.Scanner;

public class prueba {
    public static void main(String[] args) throws Exception {
        System.out.println("Escribe algo: ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(TextFormatter.formatText(str));
    }
}