/*TODO:
 need to fix magic numbers
 need to fix formating of code
 need to comment code

 change text if play takes items from room?
 add random gold and item drops from mobs
 add talking to npcs
 add shops
 add learning new skills while leveling up
 */
package textrpg;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextRPG {

    public TextRPG() throws IOException {
        System.out.println("New Game = 1, Continue = 2");

        int ans = 0;
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        
        while (loop) {
            try {
                ans = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                scan.next();
            }

            if (ans == 1) {
                newGame();
                loop = false;
            }
            else if (ans == 2) {
                continueGame();
            }
            else {
                invalidInput();
            }
        }
    }

    public static void newGame() throws IOException {
        Scanner scan = new Scanner(System.in);
        String nameInput;
        int jobSelector;
        String ans = "n";

        boolean loop = true;

        Player p = null;

        while (!"y".equalsIgnoreCase(ans)) {
            jobSelector = 0;//resets to 0 everytime it loops

            System.out.println("What is your name?"); //maybe make all strings variables? later or something
            nameInput = scan.nextLine();

            System.out.println("What is your job? (Warrior = 1, Mage = 2, Thief = 3)"); //array of jobs later?
            while (loop) {
                try {
                    jobSelector = scan.nextInt();
                    scan.nextLine(); //blank readline for int(maybe convert string to int)
                } 
                catch (InputMismatchException e) {
                    scan.next();
                }
                if (jobSelector > 0 && jobSelector < 4) {
                    loop = false;
                }
                else {
                    invalidInput();
                }
            }

            p = new Player(nameInput, jobSelector);

            p.printStatus();

            System.out.println("Your new adventure is about to begin!~ Are you ready?(Y/y)");

            ans = scan.nextLine();
            loop = true;
        }
        new Game(p);
    }

    public static void continueGame() throws IOException {
        System.out.println("This doesn't work atm lol.");
        newGame();
    }

    public static void main(String[] args) throws IOException {
        final double version = 0.3;

        System.out.println("TextRPG! v" + version);
        new TextRPG();
    }

    public static void invalidInput() {
        System.out.println("Invalid input.");
    }
}
