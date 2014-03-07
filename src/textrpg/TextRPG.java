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
       else{}
   }
   
   public final void newGame() throws IOException
   {
        Scanner scan = new Scanner(System.in);
        String x;    
        int y;
        String ans = "n";
        Player p = new Player();
        
        while(!"y".equalsIgnoreCase(ans))
        {
            System.out.println("What is your name?"); //maybe make all strings variables?
            x = scan.nextLine();
            System.out.println("What is your job? (Warrior = 1, Mage = 2, Thief = 3)");
            y = scan.nextInt();
            scan.nextLine(); //blank readline for int(maybe convert string to int)
     
            p.setName(x);
            p.setJob(y);
            p.initJob();

            System.out.println("Name: " + p.getName() + "\tJob: " + p.getJob());
            System.out.println("Health:" + p.getHealth() + "\nMana:" + p.getMana() + "\nStrength:" + p.getStrength() + "\nMagic:" + p.getMagic()+ "\nAgility:" + p.getAgility()+ "\nDefense:" + p.getDefense()+ "\nMagic Defense:" + p.getMagicDefense());

            System.out.println("Your new adventure is about to begin!~ Are you ready?(Y/N)");
            
            ans = scan.nextLine();
        }
        new Game(p);
   }

    public static void main(String[] args) throws IOException 
    {
        double version = 0.1;
        
        System.out.println("TextRPG! v" + version);
        new TextRPG();
    }    
}
