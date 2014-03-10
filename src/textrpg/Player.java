package textrpg;

import textrpg.jobs.*;

public class Player extends Entity
{
    Job job;
    
    public Player(){}
    
    public Player(String n, int j)
    {
        super.setName(n);
        if(j == 1){job = new Warrior(this);}
        else if(j == 2){job = new Mage(this);}
        else{job = new Thief(this);}
    }
    
    public String getJob(){return job.getJob();}
    
    public int attack(){return job.attack();}
}
