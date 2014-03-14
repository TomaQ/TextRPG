//This is the main game

package textrpg;

import java.util.Scanner;
import textrpg.monsters.Slime;
import textrpg.rooms.*;

public class Game 
{
    Scanner scan = new Scanner(System.in);
    
    static String lineBreak = "==========================";
    Room currentRoom = null; //the current room the player is in
    String userInput = "";
    
    //loadRooms()
    
    public Game(Player hero)
    {
        //load all rooms here right now?? hmm hmmmmmmmmm......
        printBreak();
        
        currentRoom = new StartingRoom();//generates the starting room, for testing atm
        //need player to know which room they're in
        
        System.out.println("There is much testing to be done.\n'b' for battle and 'help' for help~~");
        while(!userInput.equals("exit"))
        {
            userInput = scan.nextLine();
            command(hero);
        }
        
        System.out.println("Bye!!");
    } 
    
    public static final void printBreak()//prints a LINE_BREAKERRR!
    {
        System.out.println(lineBreak);
    }
    
    public final void command(Player hero)
    {
        
        if(userInput.equals("b"))//method n stuff ya know, just for testing
        {
            Slime m = new Slime(); //for testing atm
            new Battle(hero, m); 
        }
        if(userInput.equals("help"))//for testing
        {
            System.out.println("Type stuff in and things happen.");
        }
        else{System.out.println("Command not Recognized.");}
    }
}
