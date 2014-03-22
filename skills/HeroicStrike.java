package textrpg.skills;

import textrpg.Player;

public class HeroicStrike extends Skill
{
    Player hero;
    public HeroicStrike(Player h)
    {
        hero = h;
        super.setSkillName("Heroic Strike");
        super.setLevelRequirement(1);
        super.setItemsRequired(null);
    }
      
    @Override
    public int use()
    {
        return (int)(hero.getCurrentStrength() * 2.5);
    }
}
