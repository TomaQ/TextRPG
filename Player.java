package textrpg;

import java.util.ArrayList;
import java.util.List;
import textrpg.equipment.*;
import textrpg.jobs.*;
import textrpg.weapons.*;
import textrpg.items.*;

public class Player extends Entity
{
    Job job;
    
    Weapon weapon = null;
    Weapon offHand = null; //shield or offhand goes here
    Equipment chest = null;
    Equipment legs = null;
    Equipment bracers = null;
    Equipment boots = null;
    Equipment gloves = null;
    Equipment ring1 = null;
    Equipment ring2 = null;
    Equipment hat = null;
    Equipment goggles = null;
    
    List<Item> inventory = new ArrayList<>();
    
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
    
    public void printInventory()
    {
        System.out.println("Your inventory:");
        for(Item i: inventory)
        {
            System.out.print(i.getName() + " ");
        }
        System.out.println();
    }
}
