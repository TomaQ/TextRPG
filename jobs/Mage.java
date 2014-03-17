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
        
        p.setHealth(80);
        p.setMana(80);
        p.setAgility(10);
        p.setDefense(10);
        p.setMagic(25);
        p.setStrength(6);
        p.setMagicDefense(15);
        try {
            this.initSkills();
        } catch (Exception ex) {Logger.getLogger(Warrior.class.getName()).log(Level.SEVERE, null, ex);}
        
    }      
}
