package textrpg.jobs;

import textrpg.Player;

public class Thief extends Job
{
    private final String class_name = "Thief";
    
    public Thief(Player p)
    {
        jobName = class_name;
        
        p.setHealth(85);
        p.setMana(40);
        p.setAgility(25);
        p.setDefense(10);
        p.setMagic(8);
        p.setStrength(13);
        p.setMagicDefense(8);
    }
}
