package textrpg.jobs;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import textrpg.Player;

public class Thief extends Job
{
    private final String class_name = "Thief";
    
    private final String[] initialSkills = {"Attack"};
    
    public Thief(Player p)
    {
        availableSkills  = initialSkills;
        jobName = class_name;
        skillsLearned = new ArrayList<>();
        
        p.setBaseHealth(85);
        p.setBaseMana(40);
        p.setBaseAgility(25);
        p.setBaseDefense(10);
        p.setBaseMagic(8);
        p.setBaseStrength(13);
        p.setBaseMagicDefense(8);
        try {
            this.initSkills(p);
        } catch (Exception ex) {Logger.getLogger(Warrior.class.getName()).log(Level.SEVERE, null, ex);}
        
    }
}
