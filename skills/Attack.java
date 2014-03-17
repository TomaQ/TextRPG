package textrpg.skills;

import textrpg.Player;

public class Attack extends Skill
{
    Player hero;
    public Attack(Player h)
    {
        super.setSkillName("Attack");
        super.setLevelRequirement(1);
        super.setItemsRequired(null);
        hero = h;
    }
      
    public int use()
    {
        return (int)(hero.getStrength() * .3) + 50;
    }
}
