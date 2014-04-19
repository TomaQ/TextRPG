package textrpg.jobs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import textrpg.Player;
import textrpg.skills.Skill;

public class Job
{
    protected String jobName;
    
    private final String skills_package_path = "textrpg.skills.";
    
    protected List<Skill> skillsLearned;//the skills that the player currently has learned
    protected String[] availableSkills;//skills that the job is able to learn, ALL JOBS MUST LEARN ATTACK
    
    public Job(){}//default
    
    public Job(Player h, int i)
    {
        if(i == 1){h.setJob(new Warrior(h));}
        else if(i == 2){h.setJob(new Mage(h));}
        else{h.setJob(new Thief(h));}
        //do stuff here with if's to check job and stuff
    }
    
    public String getJobName(){return jobName;}
    
    public Skill[] getSkills()//returns all of the skills learned as objects
    {
        Skill[] s = new Skill[skillsLearned.size()];
        for(int i = 0; i < skillsLearned.size(); i++)
                s[i] = skillsLearned.get(i);
        return s;
    }
    public void printSkills()//prints all of the skills learned
    {
        String skil = "";
        for(int i = 0; i < skillsLearned.size(); i++)
        {
            skil += (skillsLearned.get(i).getSkillName() + ", ");
        }
        skil = skil.substring(0, skil.length() - 2);
        System.out.println(skil);
    }
    
    //initializes all of the skills that are learned at level 1
    public void initSkills(Player hero) throws ClassNotFoundException, InstantiationException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        for (String s : availableSkills) {
            Class clazz = Class.forName(skills_package_path + s);
            Constructor constructor = clazz.getConstructor(Player.class);
            Skill skill = (Skill)constructor.newInstance(hero);
            skillsLearned.add(skill);
        }
    }
    public void levelUp(Player p){};//OVERWRITE THIS should make an interface for things like this
}
