package View;

import java.util.Scanner;

public class View {
    public static String entrer(){
        String val;
        System.out.println("Entrer l'expression");
        Scanner in = new Scanner(System.in);
        val = in.nextLine();
        return val;
    }
    public static void result(String a,int b){ System.out.println(a+" = "+b);
    }
    public static void error(String a){
        System.err.println("Erreur\n"+a);
    }
}
