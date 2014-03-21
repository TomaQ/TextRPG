package textrpg;

import java.util.ArrayList;
import java.util.List;
import textrpg.equipment.*;
import textrpg.jobs.*;
import textrpg.weapons.*;
import textrpg.items.*;

public class Player extends Entity
{
    private Job job;
    
    private Weapon weapon = null;
    private Weapon offHand = null; //shield or offhand goes here
    private Equipment chest = null;
    private Equipment legs = null;
    private Equipment bracers = null;
    private Equipment boots = null;
    private Equipment gloves = null;
    private Equipment ring1 = null;
    private Equipment ring2 = null;
    private Equipment hat = null;
    private Equipment goggles = null;
    
    private List<Item> inventory = new ArrayList<>();
    
    private int level;
    private int currentExp;
    private int nextLevelExp;//exp needed to reach the next level
    
    public Player(){level = 1;}
    
    public Player(String n, int j)
    {
        super.setName(n);
        new Job(this, j);
    }
    
    public String getJobName(){return job.getJobName();}
    
    public Weapon getWeapon(){return weapon;}
    public void setWeapon(Weapon e){weapon = e;}
    
    public Weapon getOffHand(){return offHand;}
    public void setOffHand(Weapon e){offHand = e;}
    
    public Equipment getChest(){return chest;}
    public void setChest(Equipment e){chest = e;}
    
    public Equipment getLegs(){return legs;}
    public void setLegs(Equipment e){legs = e;}
    
    public Equipment getBracers(){return bracers;}
    public void setBracers(Equipment e){bracers = e;}
    
    public Equipment getBoots(){return boots;}
    public void setBoots(Equipment e){boots = e;}
    
    public Equipment getGloves(){return gloves;}
    public void setGloves(Equipment e){gloves = e;}
    
    public Equipment getRing1(){return ring1;}
    public void setRing1(Equipment e){ring1 = e;}
    
    public Equipment getRing2(){return ring2;}
    public void setRing2(Equipment e){ring2 = e;}
    
    public Equipment getHat(){return hat;}
    public void setHat(Equipment e){hat = e;}
    
    public Equipment getGoggles(){return goggles;}
    public void setGoggles(Equipment e){goggles = e;}
    
    public List<Item> getInventory(){return inventory;}
    public void addInventory(Item i){inventory.add(i);}
    
    public Job getJob(){return job;}
    public void setJob(Job j){job = j;}
    
    public void printInventory()
    {
        System.out.println("Your inventory:");
        for(Item i: inventory)
        {
            System.out.print(i.getName() + " ");
        }
        System.out.println();
    }
    
    public int getLevel(){return level;}
    public void levelUp()//need more than just this
    {
        level += 1;
        job.levelUp(this);
    }
}
