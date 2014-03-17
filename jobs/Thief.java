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
        
        p.setHealth(85);
        p.setMana(40);
        p.setAgility(25);
        p.setDefense(10);
        p.setMagic(8);
        p.setStrength(13);
        p.setMagicDefense(8);
        try {
            this.initSkills();
        } catch (Exception ex) {Logger.getLogger(Warrior.class.getName()).log(Level.SEVERE, null, ex);}
        
    }
}
