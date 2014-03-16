package textrpg.skills;

import textrpg.Player;

public class HeroicStrike extends Skill
{
    Player hero;
    public HeroicStrike(Player h)
    {
        super.setSkillName("Heroic Strike");
        super.setLevelRequirement(1);
        super.setItemsRequired(null);
        hero = h;
    }
      
    public int use()
    {
        return (int)(hero.getStrength() * 1.5);
    }
}
