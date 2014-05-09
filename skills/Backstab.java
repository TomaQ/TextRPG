package textrpg.skills;

import textrpg.Player;

public class Backstab extends Skill {

    Player hero;

    public Backstab(Player h) {
        hero = h;
        super.setSkillName("Backstab");
        super.setLevelRequirement(1);
        super.setItemsRequired(null);
        super.setManaCost(10);
        super.setType(1);
    }

    @Override
    public int use() {
        return (int) ((hero.getCurrentAgility()) + 15);
    }
}

