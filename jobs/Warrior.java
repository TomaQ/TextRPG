package textrpg.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import textrpg.Player;
import textrpg.skills.Skill;

public class Warrior extends Job
{
    private String[] sk = {"HeroicStrike"};

    Class[] s = {};
    public Warrior(Player p)
    {
        this.availableSkills  = sk;
        this.jobName = "Warrior";
        this.skillsLearned = new ArrayList<>();
        
        p.setHealth(100);
        p.setMana(50);
        p.setAgility(15);
        p.setDefense(20);
        p.setMagic(6);
        p.setStrength(22);
        p.setMagicDefense(11);
        try {
            this.initSkills();
        } catch (Exception ex) {Logger.getLogger(Warrior.class.getName()).log(Level.SEVERE, null, ex);}
        
    }
    
    /*public int attack()
    {
        return (int)(p.getStrength() * .3) + 50;
    }*/
    
    //public String getJob(){return class_name;}
      
}
