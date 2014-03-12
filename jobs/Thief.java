package textrpg.jobs;

import textrpg.Player;

public class Thief implements Job
{
    private final String class_name = "Thief";
    Player hero = null;
    
    public Thief(Player p)
    {
        hero = p;
        hero.setHealth(85);
        hero.setMana(40);
        hero.setAgility(25);
        hero.setDefense(10);
        hero.setMagic(8);
        hero.setStrength(13);
        hero.setMagicDefense(8);
    }
    
    public int attack()
    {
        return (int)(hero.getStrength() * .3) + 30;
    }
    public String getJob()
    {
        return class_name;
    }
}
