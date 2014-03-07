package textrpg;

import textrpg.monster_types.*;

public class Game 
{
    static String lineBreak = "==========================";
    public Game(Player hero)
    {
        printBreak();
        System.out.println("You ran into a monster!");
        Slime m = new Slime();
        new Battle(hero, m);
    } 
    
    public static final void printBreak()//prints a LINE_BREAKERRR!
    {
        System.out.println(lineBreak);
    }
}
