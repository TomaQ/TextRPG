package textrpg.skills;

import java.lang.reflect.Method;
import textrpg.Player;

public class WarriorSkills 
{
    Player hero;
    public WarriorSkills(Player h)
    {
        hero = h;
    }
    
    public String getSkills()
    {
        int i;
        String output = "";
        Class c[] = WarriorSkills.class.getDeclaredClasses();
        for(i = 0; i < WarriorSkills.class.getDeclaredClasses().length; i++)
        {
            //if(c[i].getRequiredLevel() < hero.getLevel())
        }
        return output;
    }
    
    public class heroicStrike extends Skill
    {
        public heroicStrike()
        {
            super.setName("Heroic Strike");
            super.setLevelRequirement(1);
            super.setItemsRequired(null);
        }
        
        public int use()
        {
            return (int)(hero.getStrength() * 1.5);
        }
    }
}
