package textrpg.jobs;

import java.lang.reflect.Method;
import textrpg.Player;
import textrpg.skills.WarriorSkills;

public class Warrior implements Job
{
    private final String class_name = "Warrior";
    Player hero = null;
    WarriorSkills skillz;
        
    public Warrior(Player p)
    {
        hero = p;
        hero.setHealth(100);
        hero.setMana(50);
        hero.setAgility(15);
        hero.setDefense(20);
        hero.setMagic(6);
        hero.setStrength(22);
        hero.setMagicDefense(11);
        
        skillz = new WarriorSkills(p);
    }
    
    public int attack()
    {
        return (int)(hero.getStrength() * .3) + 50;
    }
    
    public String getJob(){return class_name;}
    
    public String getSkills()
    {
        String output = "";
        /*for(Method s: skillz)//use method for getting skills from WarriorSkills and make var for skills usable maybe
        {
            output += skillz;
        }*/
        return output;    
    }   
}
