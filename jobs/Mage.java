package textrpg.jobs;

import textrpg.Player;

public class Mage extends Job
{
    private final String class_name = "Mage";
    
    public Mage(Player p)
    {
        jobName = class_name;
        
        p.setHealth(80);
        p.setMana(80);
        p.setAgility(10);
        p.setDefense(10);
        p.setMagic(25);
        p.setStrength(6);
        p.setMagicDefense(15);
    }      
}
