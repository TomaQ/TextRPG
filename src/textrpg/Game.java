package textrpg;

import textrpg.monster_types.*;

public class Game 
{
    public Game(Player hero)
    {
        
        System.out.println("You ran into a monster!");
        Slime m = new Slime();
        new Battle(hero, m);
    } 
}
