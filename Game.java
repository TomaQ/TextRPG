//This is the main game

package textrpg;

import textrpg.monsters.Slime;
import textrpg.rooms.*;

public class Game 
{
    static String lineBreak = "==========================";
    Room currentRoom = null; //the current room the player is in
    
    //loadRooms()
    
    public Game(Player hero)
    {
        //load all rooms here right now?? hmm hmmmmmmmmm......
        printBreak();
        
        currentRoom = new StartingRoom();//generates the starting room, for testing atm
        //need player to know which room they're in
        
        Slime m = new Slime(); //for testing atm
        new Battle(hero, m);
    } 
    
    public static final void printBreak()//prints a LINE_BREAKERRR!
    {
        System.out.println(lineBreak);
    }
}
