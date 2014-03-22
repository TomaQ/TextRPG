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
        p.initCurrentStats();
        try {
            this.initSkills(p);
        } catch (Exception ex) {Logger.getLogger(Warrior.class.getName()).log(Level.SEVERE, null, ex);}
        
    }
    
    @Override
    public void levelUp(Player p)
    {
        p.setCurrentExp(0);
        p.setNextLevelExp((int)(p.getNextLevelExp()*1.2));
        
        p.setBaseHealth((int)(p.getBaseHealth() * 1.2));
        p.setBaseMana((int)(p.getBaseMana() * 1.1));
        p.setBaseAgility((int)(p.getBaseAgility() * 1.2));
        p.setBaseDefense((int)(p.getBaseDefense() * 1.3));
        p.setBaseMagic((int)(p.getBaseMagic() * 1.1));
        p.setBaseStrength((int)(p.getBaseStrength() * 1.25));
        p.setBaseMagicDefense((int)(p.getBaseMagicDefense() * 1.15));
    }
}
