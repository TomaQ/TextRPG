package textrpg.jobs;

import textrpg.Player;

public class Warrior implements Job
{
    private final String class_name = "Warrior";
    Player hero = null;
    
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
    }
    
    public int attack()
    {
        return (int)(hero.getStrength() * .3) + 50;
    }
    public String getJob()
    {
        return class_name;
    }
}
