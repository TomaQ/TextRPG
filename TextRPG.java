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

            System.out.println("Name: " + p.getName() + "\tJob: " + p.getJobName());
            System.out.println("Health:" + p.getHealth() + "\nMana:" + p.getMana() + "\nStrength:" + p.getStrength() + "\nMagic:" + p.getMagic()+ "\nAgility:" + p.getAgility()+ "\nDefense:" + p.getDefense()+ "\nMagic Defense:" + p.getMagicDefense());

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
       final double version = 0.1;
        
       System.out.println("TextRPG! v" + version);
       new TextRPG();
   }    
}
