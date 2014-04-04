/*STD's
Need to fix magic numbers
Need to fix formating
*/
package textrpg;

import java.io.*;
import java.util.Scanner;

public class TextRPG 
{
    
   public TextRPG() throws IOException
   {
       System.out.println("New Game = 1, Continue = 2");
       
       Scanner scan = new Scanner(System.in);
       int ans = scan.nextInt();
       scan.nextLine();
       
       if (ans == 1){newGame();}
       else{continueGame();}
   }
   
   public static void newGame() throws IOException
   {
        Scanner scan = new Scanner(System.in);
        String nameInput;    
        int jobSelector;
        String ans = "n";
        
        Player p = null;
        
        while(!"y".equalsIgnoreCase(ans))
        {
            System.out.println("What is your name?"); //maybe make all strings variables? later or something
            nameInput = scan.nextLine();
            System.out.println("What is your job? (Warrior = 1, Mage = 2, Thief = 3)"); //array of jobs later?
            jobSelector = scan.nextInt();
            scan.nextLine(); //blank readline for int(maybe convert string to int)
     
            p = new Player(nameInput, jobSelector);

            p.printStatus();
            
            System.out.println("Your new adventure is about to begin!~ Are you ready?(Y/N)");
            
            ans = scan.nextLine();
        }
        new Game(p);
   }

   public static void continueGame() throws IOException
   {
       System.out.println("This doesn't work atm lol.");
       newGame();
   }
   
   public static void main(String[] args) throws IOException 
   {
       final double version = 0.2;
        
       System.out.println("TextRPG! v" + version);
       new TextRPG();
   }    
}
