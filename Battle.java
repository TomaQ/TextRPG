//This class handles all of the battle functions needed for a standard battle

package textrpg;

import textrpg.monsters.Monster;
import java.util.Scanner;
import textrpg.skills.Skill;

public final class Battle 
{
    Scanner scan = new Scanner(System.in);
    Player hero;//not sure if health will recover after battle
    Monster monster;
    
    
    public Battle(Player h, Monster m)
    {
        hero = h;
        monster = m;
        Game.printBreak();
        System.out.println("You have engaged in battle with " + m.getName() + "!");
        
        while(hero.getCurrentHealth() > 0 && monster.getCurrentHealth() > 0)
        {
            if(moveFirst()){playerTurn();}//need to make it switch to monsters turn if not that's like omg totally like unfair~
            else{monsterTurn();}
        }
        
        System.out.println("The battle is like done yo.");
        Game.printBreak();
    }
    
    public boolean moveFirst()//calculates who gets the first turn based on agility
    {
        if(hero.getCurrentAgility() > monster.getCurrentAgility())
            return true;
        else
            return false;
    }
    
    public void playerTurn()//need to organize this like totally better
    {   
        System.out.println("Your turn!");
        System.out.println("Attack = 1, Skills = 2");//should get an array of skills
        int input = scan.nextInt();
        scan.nextLine();
        
        int dmg = 0;
        if(input == 1) //separate method make you should
        {
            for (Skill skill : hero.getJob().getSkills()) 
                if (skill.getSkillName().equals("Attack")) 
                    dmg = skill.use();
            
            monster.setCurrentHealth(monster.getCurrentHealth() - dmg);
            System.out.println("Did " + dmg + " damage! Remaining HP of monster:" + monster.getCurrentHealth());
        }
        else
        {
            for(int i = 0; i < hero.getJob().getSkills().length; i++) 
            {
                if(!hero.getJob().getSkills()[i].getSkillName().equals("Attack"))//Need to remove getSkills from adding Attack  
                    System.out.print(hero.getJob().getSkills()[i].getSkillName() + "(" + i + ")" + ", ");
            }
            System.out.println();
            
            System.out.println("Which skill will you use?");
            int skillChosen = scan.nextInt();
            scan.nextLine();//maybe i should just cast ints...
            
            dmg = hero.getJob().getSkills()[skillChosen].use();
            monster.setCurrentHealth(monster.getCurrentHealth() - dmg);
            System.out.println("Did " + dmg + " damage! Remaining HP of monster:" + monster.getCurrentHealth());
        }
    }
    
    public void monsterTurn()
    {
        System.out.println(monster.getName() + "'s turn!");
        //monster.getSkills();
    }
}
