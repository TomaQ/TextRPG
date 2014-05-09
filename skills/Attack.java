package textrpg.skills;

import textrpg.Player;

public class Attack extends Skill {

    Player hero;

    public Attack(Player h) {
        super.setSkillName("Attack");
        super.setLevelRequirement(1);
        super.setItemsRequired(null);
        hero = h;
    }

    @Override
    public int use() {
        return (int) (hero.getCurrentStrength() * .3) + 50;
    }
}
