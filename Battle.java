//This class handles all of the battle functions needed for a standard battle

package textrpg;

import textrpg.monsters.Monster;
import java.util.Scanner;

public final class Battle 
{
    Scanner scan = new Scanner(System.in);
    Player hero;
    Monster monster;
    
    
    public Battle(Player h, Monster m)
    {
        hero = h;
        monster = m;
        Game.printBreak();
        System.out.println("You have engaged in battle with " + m.getName() + "!");
        
        if(moveFirst()){playerTurn();}
        else{monsterTurn();}
    }
    
    public boolean moveFirst()//calculates who gets the first turn based on agility
    {
        if(hero.getAgility() > monster.getAgility())
            return true;
        else
            return false;
    }
    
    public void playerTurn()
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
    
    public void monsterTurn()
    {
        System.out.println(monster.getName() + "'s turn!");
        //monster.getSkills();
    }
}
