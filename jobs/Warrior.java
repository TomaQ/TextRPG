package textrpg.jobs;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import textrpg.Player;

public class Warrior extends Job {

    private final String class_name = "Warrior";

    private final String[] initialSkills = {"HeroicStrike", "Attack"};
    
    public Warrior(Player p) {//need way to check for new available skills to be learnable
        availableSkills  = initialSkills;//this is wrong also
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
            this.initSkills(p); //temp
        } catch (Exception ex) {Logger.getLogger(Warrior.class.getName()).log(Level.SEVERE, null, ex);}
        
    }    
    
    @Override
    public void levelUp(Player p) {
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
