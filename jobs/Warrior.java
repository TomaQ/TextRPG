package textrpg.jobs;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import textrpg.Player;

public class Warrior extends Job
{
    private final String class_name = "Warrior";

    private final String[] initialSkills = {"HeroicStrike", "Attack"};
    
    public Warrior(Player p)//need way to check for new available skills to be learnable
    {
        availableSkills  = initialSkills;
        jobName = class_name;
        skillsLearned = new ArrayList<>();
        
        p.setBaseHealth(100);
        p.setBaseMana(50);
        p.setBaseAgility(15);
        p.setBaseDefense(20);
        p.setBaseMagic(6);
        p.setBaseStrength(22);
        p.setBaseMagicDefense(11);
        p.initCurrentStats();
        try {
            this.initSkills(p);
        } catch (Exception ex) {Logger.getLogger(Warrior.class.getName()).log(Level.SEVERE, null, ex);}
        
    }    
    
    @Override
    public void levelUp(Player p)
    {
        p.setBaseHealth((int)(p.getBaseHealth() * 1.2));
        p.setBaseMana(50);
        p.setBaseAgility(15);
        p.setBaseDefense(20);
        p.setBaseMagic(6);
        p.setBaseStrength(22);
        p.setBaseMagicDefense(11);
        p.initCurrentStats();
    }
}
