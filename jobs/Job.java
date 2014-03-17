package textrpg.jobs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import textrpg.Player;
import textrpg.skills.Skill;

public class Job
{
    Player hero;
    protected String jobName;
    
    private final String skills_package_path = "textrpg.skills.";
    
    protected List<Skill> skillsLearned;
    protected String[] availableSkills;
    
    public Job(){}//default
    
    public Job(Player h, int i)
    {
        if(i == 1){h.setJob(new Warrior(h));}
        else if(i == 2){h.setJob(new Mage(h));}
        else{h.setJob(new Thief(h));}
        //do stuff here with if's to check job and stuff
    }
    
    public String getJobName()
    {
        return jobName;
    }
    public String[] getSkills()
    {
        return availableSkills;
    }
    public void printSkills()
    {
        for(int i = 0; i < skillsLearned.size(); i++)
        {
            System.out.print(skillsLearned.get(i).getSkillName() + ", ");
        }
        System.out.println();
    }
    
    public void initSkills() throws ClassNotFoundException, InstantiationException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        for (String s : availableSkills) {
            Class clazz = Class.forName(skills_package_path + s);
            Constructor constructor = clazz.getConstructor(Player.class);
            Skill skill = (Skill) constructor.newInstance(hero);
            skillsLearned.add(skill);
        }
    }
}
