package textrpg;

import java.util.Scanner;

public class Engine {

    static Scanner scan = new Scanner(System.in);
    
    public static String getString() {
        String input = scan.nextLine();
        return input;
    }
    
    public static int getInt() {
        int input = scan.nextInt();
        scan.nextLine();
        return input;
    }
}
