/*TODO:
 need to fix magic numbers
 need to fix formating of code
 need to comment code more

 change text if play takes items from room?
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

        int ans = 0; //The variable for choosing a menu choice
        Scanner scan = new Scanner(System.in);
        boolean loop = true;

        //Gets the user's input for the menu choice
        while (loop) {
            try {
                ans = scan.nextInt();
                scan.nextLine();
            }
            catch (InputMismatchException e) {
                scan.next(); //Need to 'eat' the line
            }

            if (ans == 1) {
                newGame();
                loop = false;
            }
            else if (ans == 2) {
                continueGame();
                loop = false;
            }
            else {
                TextRPG.invalidInput();
            }
        }
    }

    //Starts a new game
    public static void newGame() throws IOException {
        Scanner scan = new Scanner(System.in);
        String nameInput = "";
        int jobSelector;
        String ans = "n";

        boolean loop = true;

        Player p = null;

        //If the player enters 'Y' or 'y' then it exits out of character creation
        while (!"y".equalsIgnoreCase(ans)) {
            jobSelector = 0;//resets to 0 everytime it loops

            boolean nameLoop = true;
            do {
                System.out.println("What is your name?"); //maybe make all strings variables? later or something
                nameInput = scan.nextLine();
                if (nameInput.length() < 2 || nameInput.length() > 15) {
                    System.out.println("Please enter a name between 2-15 characters");
                }
                else {
                    nameLoop = false;
                }
            } while (nameLoop);

            System.out.println("What is your job? (Warrior = 1, Mage = 2, Thief = 3)"); //array of jobs later?
            while (loop) {
                try {
                    jobSelector = scan.nextInt();
                    scan.nextLine();
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

            //Creates a new player with the selected name and job
            p = new Player(nameInput, jobSelector);

            p.printStatus();

            System.out.println("Your new adventure is about to begin!~ Are you ready?(Y/y)");

            ans = scan.nextLine();
            loop = true; //If user doesn't say yes then need to reset the job selector loop
        }
        new Game(p); //Creates a new game with the newly created player
    }

    //Currently under construction
    public static void continueGame() throws IOException {
        System.out.println("This doesn't work atm lol.");
        newGame();
    }

    //This is the global InputMismatch method 
    public static void invalidInput() {
        System.out.println("Invalid input.");
    }

    public static void main(String[] args) throws IOException {
        final double version = 0.3;

        System.out.println("TextRPG! v" + version);
        new TextRPG(); //Starts the game
    }
}
