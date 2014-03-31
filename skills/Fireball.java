package textrpg.skills;

import textrpg.Player;

public class Fireball extends Skill
{
    Player hero;
    public Fireball(Player h)
    {
        hero = h;
        super.setSkillName("Fireball");
        super.setLevelRequirement(1);
        super.setItemsRequired(null);
        super.setManaCost(15);
        super.setType(2);
    }
      
    @Override
    public int use()
    {
        return (int)((hero.getCurrentMagic() * 1.25) + 25);
    }
}
