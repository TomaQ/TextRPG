package textrpg;

import textrpg.jobs.*;

public class Player extends Being
{
    private int job;

    public void setJob(int input){job=input;}
    public String getJob()
    {
        if(job == 1){return "Warrior";}
        if(job == 2){return "Mage";}
        else{return"Thief";}
    }
    
    public void initJob()
    {
        if(job == 1)
        {
            super.setHealth(100);
            super.setMana(50);
            super.setAgility(15);
            super.setDefense(20);
            super.setMagic(6);
            super.setStrength(22);
            super.setMagicDefense(11);
        }
        else if(job == 2)
        {
            super.setHealth(80);
            super.setMana(80);
            super.setAgility(10);
            super.setDefense(10);
            super.setMagic(25);
            super.setStrength(6);
            super.setMagicDefense(15);
        }
        else
        {
            super.setHealth(85);
            super.setMana(40);
            super.setAgility(25);
            super.setDefense(10);
            super.setMagic(8);
            super.setStrength(13);
            super.setMagicDefense(8);
        }
    }
    
    public int getAttack()
    {
        //something like this, make player class pass to every class
        Warrior war = new Warrior(this);
        if(job == 1){return war.attack();}
        else{return 1;}
    }
}
