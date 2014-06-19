package textrpg.jobs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import textrpg.Game;
import textrpg.Player;
import textrpg.skills.Skill;

public class Job {

    final int SUBSTRING_OFFSET = 2; //The offset for the length of a String that has ', ' added at the end

    protected String jobName;
    private final String skills_package_path = "textrpg.skills.";

    protected List<Skill> skillsLearned;//the skills that the player currently has learned
    protected String[] availableSkills;//skills that the job is able to learn
    //ALL JOBS MUST LEARN ATTACK
    
    protected double[] incrementStats;

    public Job(){}

    //Sets the players job
    public Job(Player h, int i) {
        if (i == 1) {
            h.setJob(new Warrior(h));
        }
        else if (i == 2) {
            h.setJob(new Mage(h));
        }
        else {
            h.setJob(new Thief(h));
        }
        //do stuff here with if's to check job and stuff
        //?? i need to write better comments
    }

    public String getJobName(){return jobName;}

    //Returns all of the skills learned as objects in an array
    public Skill[] getSkills() {
        Skill[] s = new Skill[skillsLearned.size()];
        for (int i = 0; i < skillsLearned.size(); i++) {
            s[i] = skillsLearned.get(i);
        }
        return s;
    }

    //Prints all of the skills learned
    public void printSkills() {
        Game.printBreak();
        String skil = "";
        for (int i = 0; i < skillsLearned.size(); i++) {
            skil += (skillsLearned.get(i).getSkillName() + ", ");
        }
        skil = skil.substring(0, skil.length() - SUBSTRING_OFFSET); //This removes the ", " at the end of the string
        System.out.println(skil);
    }

    //initializes all of the skills that are learned at level 1
    //This is temporary...
    public void initSkills(Player hero) throws ClassNotFoundException, InstantiationException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (String s : availableSkills) {
            Class skillz = Class.forName(skills_package_path + s);
            Constructor constructor = skillz.getConstructor(Player.class);
            Skill skill = (Skill)constructor.newInstance(hero);
            skillsLearned.add(skill);
        }
    }

    public void initializeJobStats(int[] baseStats, String job_Name, String[] initialSkills, Player p) {
        availableSkills = initialSkills;//this is just temp
        jobName = job_Name;
        skillsLearned = new ArrayList<>();

        p.setBaseHealth(baseStats[0]);
        p.setBaseMana(baseStats[1]);
        p.setBaseAgility(baseStats[2]);
        p.setBaseDefense(baseStats[5]);
        p.setBaseMagic(baseStats[3]);
        p.setBaseStrength(baseStats[4]);
        p.setBaseMagicDefense(baseStats[6]);
        p.initCurrentStats();
    }

    public void levelUp(Player p) {
        p.setNextLevelExp((int) (p.getNextLevelExp() * incrementStats[0]));

        p.setBaseHealth((int) (p.getBaseHealth() * incrementStats[1]));
        p.setBaseMana((int) (p.getBaseMana() * incrementStats[2]));
        p.setBaseAgility((int) (p.getBaseAgility() * incrementStats[3]));
        p.setBaseDefense((int) (p.getBaseDefense() * incrementStats[6]));
        p.setBaseMagic((int) (p.getBaseMagic() * incrementStats[4]));
        p.setBaseStrength((int) (p.getBaseStrength() * incrementStats[5]));
        p.setBaseMagicDefense((int) (p.getBaseMagicDefense() * incrementStats[7]));
    }
}
