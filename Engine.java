package textrpg;

import java.util.Scanner;

public class Engine {

    static Scanner scan = new Scanner(System.in);
    
    private static void inputFormat() {
        System.out.print(">");
    }
    
    public static String getString() {
        inputFormat();
        String input = scan.nextLine();
        return input;
    }
    
    public static int getInt() {
        inputFormat();
        int input = scan.nextInt();
        scan.nextLine();
        return input;
    }
    
}
