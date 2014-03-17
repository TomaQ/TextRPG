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
        
        p.setHealth(100);
        p.setMana(50);
        p.setAgility(15);
        p.setDefense(20);
        p.setMagic(6);
        p.setStrength(22);
        p.setMagicDefense(11);
        try {
            this.initSkills(p);
        } catch (Exception ex) {Logger.getLogger(Warrior.class.getName()).log(Level.SEVERE, null, ex);}
        
    }    
}
