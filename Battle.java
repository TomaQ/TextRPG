//This class handles all of the battle functions needed for a standard battle

package textrpg;

import textrpg.monster_types.Monster;
import java.util.Scanner;

public final class Battle 
{
    Scanner scan = new Scanner(System.in);
    
    public Battle(Player hero, Monster m)
    {
        Game.printBreak();
        System.out.println("You have engaged in battle with " + m.getName() + "!");
        
        if(moveFirst(hero, m)){playerTurn(hero, m);}
        else{System.out.println(m.getName() + "'s turn!");}
    }
    
    public boolean moveFirst(Player hero, Monster enemy)//calculates who gets the first turn based on agility
    {
        if(hero.getAgility() > enemy.getAgility())
            return true;
        else
            return false;
    }
    
    public void playerTurn(Player hero, Monster m)
    {   
        System.out.println("Your turn!");
        System.out.println("Attack = 1, Skills = 2");//should get an array of skills
        int input = scan.nextInt();
        scan.nextLine();
        
        if(input == 1) //separate method make you should
        {
            hero.attack();
            System.out.println("Did " + hero.attack() + " damage!"); //make a variable for hero.attack()
        }
    }
}
