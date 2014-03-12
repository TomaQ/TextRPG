//This is the main game

package textrpg;

import textrpg.monster_types.*;
import textrpg.rooms.*;

public class Game 
{
    static String lineBreak = "==========================";
    public Game(Player hero)
    {
        printBreak();
        
        Room startR = new StartingRoom();//generates the starting room, for testing atm
        
        Slime m = new Slime(); //for testing atm
        new Battle(hero, m);
    } 
    
    public static final void printBreak()//prints a LINE_BREAKERRR!
    {
        System.out.println(lineBreak);
    }
}
