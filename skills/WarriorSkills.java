package textrpg.skills;

import textrpg.Player;

public class WarriorSkills 
{
    Player hero;
    public WarriorSkills(Player h)
    {
        hero = h;
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
