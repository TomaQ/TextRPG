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
    
    private Weapon weapon = new NoneW();
    private Weapon offHand = new NoneW(); //shield or offhand goes here
    private Equipment chest = new NoneE();
    private Equipment legs = new NoneE();
    private Equipment bracers = new NoneE();
    private Equipment boots = new NoneE();
    private Equipment gloves = new NoneE();
    private Equipment ring1 = new NoneE();
    private Equipment ring2 = new NoneE();
    private Equipment hat = new NoneE();
    private Equipment goggles = new NoneE();//not sure if i want these...
    
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
    public void setWeapon(Weapon e){weapon = e;initCurrentStats();}
    
    public Weapon getOffHand(){return offHand;}
    public void setOffHand(Weapon e){offHand = e;initCurrentStats();}
    
    public Equipment getChest(){return chest;}
    public void setChest(Equipment e){chest = e;initCurrentStats();}
    
    public Equipment getLegs(){return legs;}
    public void setLegs(Equipment e){legs = e;initCurrentStats();}
    
    public Equipment getBracers(){return bracers;}
    public void setBracers(Equipment e){bracers = e;initCurrentStats();}
    
    public Equipment getBoots(){return boots;}
    public void setBoots(Equipment e){boots = e;initCurrentStats();}
    
    public Equipment getGloves(){return gloves;}
    public void setGloves(Equipment e){gloves = e;initCurrentStats();}
    
    public Equipment getRing1(){return ring1;}
    public void setRing1(Equipment e){ring1 = e;initCurrentStats();}
    
    public Equipment getRing2(){return ring2;}
    public void setRing2(Equipment e){ring2 = e;initCurrentStats();}
    
    public Equipment getHat(){return hat;}
    public void setHat(Equipment e){hat = e;initCurrentStats();}
    
    public Equipment getGoggles(){return goggles;}
    public void setGoggles(Equipment e){goggles = e;initCurrentStats();}
    
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
    
    public int getCurrentExp(){return currentExp;}
    public void setCurrentExp(int i){currentExp = i;}
    public int getNextLevelExp(){return nextLevelExp;}
    public void setNextLevelExp(int i){nextLevelExp = i;}
    
    public int getLevel(){return level;}
    public void levelUp()//need more than just this
    {
        level += 1;
        job.levelUp(this);
        initCurrentStats();
    }
    
    public void printStatus()
    {
        System.out.println("Name: " + getName() + "\tJob: " + getJobName());
        System.out.println("Health:" + getCurrentHealth() + "\nMana:" + getCurrentMana() + "\nStrength:" + getCurrentStrength() + "\nMagic:" + getCurrentMagic()+ "\nAgility:" + getCurrentAgility()+ "\nDefense:" + getCurrentDefense()+ "\nMagic Defense:" + getCurrentMagicDefense());
    }
    
    public void printEquipment()//need to fix null pointer
    {
        System.out.println("Weapons: " + getWeapon().getName() + "/" + getOffHand().getName());
        System.out.println("Chest: " + getChest().getName() + "\tLegs: " + getLegs().getName());
        System.out.println("Bracers: " + getBracers().getName() + "\tBoots: " + getBoots().getName());
        System.out.println("Gloves: " + getGloves().getName() + "\tRings: " + getRing1().getName() + "/" + getRing2().getName());
        System.out.println("Hat: " + getHat().getName() + "\tGoggles: " + getGoggles().getName());
    }
    
    @Override    
    public void initCurrentStats() //NEED TO FINISH THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    {
        super.setCurrentHealth(super.getBaseHealth());
        super.setCurrentMana(super.getBaseMana());
        super.setCurrentStrength(super.getBaseStrength() + weapon.getEquipmentStats()[0]);
        super.setCurrentMagic(super.getBaseMagic());
        super.setCurrentAgility(super.getBaseAgility());
        super.setCurrentDefense(super.getBaseDefense());
        super.setCurrentMagicDefense(super.getBaseMagicDefense());
    }
}
