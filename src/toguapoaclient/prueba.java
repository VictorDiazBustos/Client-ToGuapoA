package toguapoaclient;

import java.util.Date;
import java.util.Scanner;

public class prueba {
    public static void main(String[] args) throws Exception {
        System.out.print("[" + new Date() + "] : ");
        System.out.println("Escribe algo: ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(TextFormatter.formatText(str));
    }
}