package textrpg.jobs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import textrpg.Player;
import textrpg.skills.*;

public class Job
{
    Player h;
    protected String jobName;
    public String getJob()
    {
        return jobName;
    }
    public String[] getSkills()
    {
        return availableSkills;
    }
    public void printSkills()
    {
        for(int i = 0; i < availableSkills.length; i++)
        {
            System.out.println(skillsLearned.getSkillName());
        }
    }
    protected List<Object> skillsLearned;
    protected String[] availableSkills;
    
    public void initSkills() throws ClassNotFoundException, InstantiationException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        for(int i = 0; i < availableSkills.length; i++)
        {
            String s = availableSkills[i];
            Class clazz = Class.forName("textrpg.skills." + s);
            Constructor constructor = clazz.getConstructor(Player.class);
            Object skill = constructor.newInstance(h);
            skillsLearned.add(skill);
        }
    }
}
