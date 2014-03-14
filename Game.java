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
        while(!userInput.equals("quit"))
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
    
    public final void command(Player hero)//checks what to do from the users input
    { 
        switch(userInput)
        {
            case "b": Slime m = new Slime();
                new Battle(hero, m);
                break;
            case "help": System.out.println("Type stuff in and things happen.");
                break;
            case "n": if(currentRoom.getNExit() != null){currentRoom = currentRoom.getNExit();}//methods for these
                else{Room.noExit();}
                break;
            case "s": if(currentRoom.getSExit() != null){currentRoom = currentRoom.getSExit();}
                else{Room.noExit();}
                break;
            case "e": if(currentRoom.getEExit() != null){currentRoom = currentRoom.getEExit();}
                else{Room.noExit();}
                break;
            case "w": if(currentRoom.getWExit() != null){currentRoom = currentRoom.getWExit();}
                else{Room.noExit();}
                break;    
            case "exits": currentRoom.getExits();
                break;
            case "look": currentRoom.getRoomDescription();
                break;
            default: System.out.println("Command not recognized.");   
                break;             
        }
    }
}
