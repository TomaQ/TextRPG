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
        
        while(hero.getHealth() > 0 && monster.getHealth() > 0)
        {
            if(moveFirst()){playerTurn();}//need to make it switch to monsters turn if not that's like omg totally like unfair~
            else{monsterTurn();}
        }
        
        System.out.println("The battle is like done yo.");
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
            int dmg;
            dmg = hero.attack();
            monster.setHealth(monster.getHealth() - dmg);
            
            System.out.println("Did " + dmg + " damage! Remaining HP of monster:" + monster.getHealth());
        }
    }
    
    public void monsterTurn()
    {
        System.out.println(monster.getName() + "'s turn!");
        //monster.getSkills();
    }
}
