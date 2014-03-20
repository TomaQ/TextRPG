package textrpg.jobs;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import textrpg.Player;

public class Mage extends Job
{
    private final String class_name = "Mage";
    
    private final String[] initialSkills = {"Attack"};
    
    public Mage(Player p)
    {
        availableSkills  = initialSkills;
        jobName = class_name;
        skillsLearned = new ArrayList<>();
        
        p.setBaseHealth(80);
        p.setBaseMana(80);
        p.setBaseAgility(10);
        p.setBaseDefense(10);
        p.setBaseMagic(25);
        p.setBaseStrength(6);
        p.setBaseMagicDefense(15);
        try {
            this.initSkills(p);
        } catch (Exception ex) {Logger.getLogger(Warrior.class.getName()).log(Level.SEVERE, null, ex);}
        
    }      
}
