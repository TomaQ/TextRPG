//Need to make quests and have objects associated in here n such ya know

package textrpg;

import java.util.ArrayList;
import java.util.List;
import textrpg.equipment.*;
import textrpg.jobs.*;
import textrpg.weapons.*;
import textrpg.items.*;

public class Player extends Entity {

    private Job job;

    private Weapon weapon = new NoneW();
    private Weapon offHand = new NoneW(); //shield or offhand goes here
    private Equipment chest = new NoneE();
    private Equipment legs = new NoneE();
    private Equipment bracers = new NoneE();
    private Equipment boots = new NoneE();
    private Equipment gloves = new NoneE();
    private Equipment ring = new NoneE();
    private Equipment hat = new NoneE();
    private Equipment goggles = new NoneE();//not sure if i want these...

    private List<Item> inventory = new ArrayList<>();

    private int level;
    private int currentExp;
    private int nextLevelExp = 27; //exp needed to reach the next level - Temporary number

    private int gold = 0;

    public Player(String n, int j) { //sets the level to 1 and initializes the players job
        level = 1;
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
    
    public Equipment getRing(){return ring;}
    public void setRing(Equipment e){ring = e;initCurrentStats();}
    
    public Equipment getHat(){return hat;}
    public void setHat(Equipment e){hat = e;initCurrentStats();}
    
    public Equipment getGoggles(){return goggles;}
    public void setGoggles(Equipment e){goggles = e;initCurrentStats();}
    
    public Equipment[] getEquipment() {
        Equipment[] temp = {weapon, offHand, chest, legs, bracers, boots, gloves, ring, hat, goggles};
        return temp;
    }
    
    public List<Item> getInventory(){return inventory;}//used for removing and looping
    public void addInventory(Item i){inventory.add(i);}
    
    public Job getJob(){return job;}
    public void setJob(Job j){job = j;} //if it is ever possible to switch jobs then need to do more than this
    
    //Returns a 2d array of the item name and the quantity of each
    public String[][] getCountedInventory() {
        String[][] invenCount = new String[inventory.size()][2];

        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j - 1 < i; j++) {//i should use a map here instead lol
                //short circuits here
                if (invenCount[j][0] != null && invenCount[j][0].equals(inventory.get(i).getName())) {//if the names match
                    invenCount[j][1] = Integer.toString(Integer.valueOf(invenCount[j][1]) + 1);
                    break; //dont need to check rest of array if similar
                }
                else if (invenCount[j][0] == null) { //if it doesnt find the item then it adds it to the string
                    invenCount[j][0] = inventory.get(i).getName();
                    invenCount[j][1] = "1";
                    break; //dont need to check the rest of array if inserted
                }
            }
        }
        return invenCount;
    }
    
    //Returns the players inventory without duplicates
    public Item[] getCountedInventoryItems() {
        Item[] invenCount = new Item[inventory.size()];

        for (int i = 0; i < inventory.size(); i++) {
            for(int j = 0; j - 1 < i; j++) {
                if(invenCount[j] != null && invenCount[j] == inventory.get(i)) {
                    invenCount[i] = inventory.get(j);
                    break;
                }
                else if(invenCount[j] == null){
                    invenCount[j] = inventory.get(i);
                }
            }
        }
        return invenCount;
    }
    
    //Prints the players inventory in the format 'Item name(quantity)'
    public void printInventory() {   
        Game.printBreak();
        System.out.println("Your inventory:");
        String inven = "Gold: " + this.getGold() + ", ";//the inventory string that gets printed out
        String[][] invenCount = this.getCountedInventory();
        
        for (int i = 0; i < invenCount.length; i++){ //prints the inventory and counter if > 1
            if (invenCount[i][0] != null) {
                if (Integer.valueOf(invenCount[i][1]) > 1) {
                    inven += invenCount[i][0] + "(" + invenCount[i][1] + "), ";
                }
                else {
                    inven += invenCount[i][0] + ", ";
                }
            }
        }
        if (inven.length() > 2) {//formatting
            inven = inven.substring(0, inven.length() - 2);
        }

        System.out.println(inven);
    }
    
    public int getCurrentExp(){return currentExp;}
    public void setCurrentExp(int i) {
        if (nextLevelExp < (currentExp + i)) { //carrys over left over experience
            currentExp = (currentExp + i) - nextLevelExp;
            levelUp();
        }
        else {
            currentExp = i;
        }
    }
    
    public int getNextLevelExp(){return nextLevelExp;}
    public void setNextLevelExp(int i){nextLevelExp = i;}
    
    public int getLevel(){return level;}
    public void levelUp(){
        level += 1;
        System.out.println("You reached level " + level + "!");

        job.levelUp(this);
        initCurrentStats(); //need to set currect stats since they changed
    }
    
    public int getGold(){return gold;}
    public void setGold(int i){gold = i;}
    
    public void printStatus() {
        Game.printBreak();
        System.out.println("Name: " + getName() + "\tJob: " + getJobName() + "\tLevel:" + getLevel());
        System.out.println("Health:" + getCurrentHealth() + "\nMana:" + getCurrentMana() + "\nStrength:" + getCurrentStrength() + "\nMagic:" + getCurrentMagic() + "\nAgility:" + getCurrentAgility() + "\nDefense:" + getCurrentDefense() + "\nMagic Defense:" + getCurrentMagicDefense());
    }

    public void printEquipment() {
        Game.printBreak();
        System.out.println("Weapons: " + getWeapon().getName() + "/" + getOffHand().getName());
        System.out.println("Chest: " + getChest().getName() + "\tLegs: " + getLegs().getName());
        System.out.println("Bracers: " + getBracers().getName() + "\tBoots: " + getBoots().getName());
        System.out.println("Gloves: " + getGloves().getName() + "\tRing: " + getRing().getName());
        System.out.println("Helmet: " + getHat().getName() + "\tGoggles: " + getGoggles().getName());
    }
    
    @Override //sets the current stats by calculating the base and bonus from equipment
    public void initCurrentStats() {
        super.setCurrentHealth(super.getBaseHealth() + calculateBonusHealth());
        super.setMaxHealth(super.getCurrentHealth());
        super.setCurrentMana(super.getBaseMana() + calculateBonusMana());
        super.setMaxMana(super.getCurrentMana());
        super.setCurrentStrength(super.getBaseStrength() + calculateBonusStrength());
        super.setCurrentMagic(super.getBaseMagic() + calculateBonusMagic());
        super.setCurrentAgility(super.getBaseAgility() + calculateBonusAgility());
        super.setCurrentDefense(super.getBaseDefense() + calculateBonusDefense());
        super.setCurrentMagicDefense(super.getBaseMagicDefense() + calculateBonusMagicDefense());
    }

    private int calculateBonusHealth() {//calculates the bonus HP you get from equipment
        int bonus = 0;
        bonus += getWeapon().getEquipmentStats()[0]; //0 is the index of the hp modifier stat
        bonus += getOffHand().getEquipmentStats()[0];
        bonus += getChest().getEquipmentStats()[0];
        bonus += getLegs().getEquipmentStats()[0];
        bonus += getBracers().getEquipmentStats()[0];
        bonus += getBoots().getEquipmentStats()[0];
        bonus += getGloves().getEquipmentStats()[0];
        bonus += getRing().getEquipmentStats()[0];
        bonus += getHat().getEquipmentStats()[0];
        bonus += getGoggles().getEquipmentStats()[0];
        return bonus;
    }

    private int calculateBonusMana() {
        int bonus = 0;
        bonus += getWeapon().getEquipmentStats()[1];
        bonus += getOffHand().getEquipmentStats()[1];
        bonus += getChest().getEquipmentStats()[1];
        bonus += getLegs().getEquipmentStats()[1];
        bonus += getBracers().getEquipmentStats()[1];
        bonus += getBoots().getEquipmentStats()[1];
        bonus += getGloves().getEquipmentStats()[1];
        bonus += getRing().getEquipmentStats()[1];
        bonus += getHat().getEquipmentStats()[1];
        bonus += getGoggles().getEquipmentStats()[1];
        return bonus;
    }

    private int calculateBonusStrength() {
        int bonus = 0;
        bonus += getWeapon().getEquipmentStats()[2];
        bonus += getOffHand().getEquipmentStats()[2];
        bonus += getChest().getEquipmentStats()[2];
        bonus += getLegs().getEquipmentStats()[2];
        bonus += getBracers().getEquipmentStats()[2];
        bonus += getBoots().getEquipmentStats()[2];
        bonus += getGloves().getEquipmentStats()[2];
        bonus += getRing().getEquipmentStats()[2];
        bonus += getHat().getEquipmentStats()[2];
        bonus += getGoggles().getEquipmentStats()[2];
        return bonus;
    }

    private int calculateBonusMagic() {
        int bonus = 0;
        bonus += getWeapon().getEquipmentStats()[3];
        bonus += getOffHand().getEquipmentStats()[3];
        bonus += getChest().getEquipmentStats()[3];
        bonus += getLegs().getEquipmentStats()[3];
        bonus += getBracers().getEquipmentStats()[3];
        bonus += getBoots().getEquipmentStats()[3];
        bonus += getGloves().getEquipmentStats()[3];
        bonus += getRing().getEquipmentStats()[3];
        bonus += getHat().getEquipmentStats()[3];
        bonus += getGoggles().getEquipmentStats()[3];
        return bonus;
    }

    private int calculateBonusAgility() {
        int bonus = 0;
        bonus += getWeapon().getEquipmentStats()[4];
        bonus += getOffHand().getEquipmentStats()[4];
        bonus += getChest().getEquipmentStats()[4];
        bonus += getLegs().getEquipmentStats()[4];
        bonus += getBracers().getEquipmentStats()[4];
        bonus += getBoots().getEquipmentStats()[4];
        bonus += getGloves().getEquipmentStats()[4];
        bonus += getRing().getEquipmentStats()[4];
        bonus += getHat().getEquipmentStats()[4];
        bonus += getGoggles().getEquipmentStats()[4];
        return bonus;
    }

    private int calculateBonusDefense() {
        int bonus = 0;
        bonus += getWeapon().getEquipmentStats()[5];
        bonus += getOffHand().getEquipmentStats()[5];
        bonus += getChest().getEquipmentStats()[5];
        bonus += getLegs().getEquipmentStats()[5];
        bonus += getBracers().getEquipmentStats()[5];
        bonus += getBoots().getEquipmentStats()[5];
        bonus += getGloves().getEquipmentStats()[5];
        bonus += getRing().getEquipmentStats()[5];
        bonus += getHat().getEquipmentStats()[5];
        bonus += getGoggles().getEquipmentStats()[5];
        return bonus;
    }

    private int calculateBonusMagicDefense() {
        int bonus = 0;
        bonus += getWeapon().getEquipmentStats()[6];
        bonus += getOffHand().getEquipmentStats()[6];
        bonus += getChest().getEquipmentStats()[6];
        bonus += getLegs().getEquipmentStats()[6];
        bonus += getBracers().getEquipmentStats()[6];
        bonus += getBoots().getEquipmentStats()[6];
        bonus += getGloves().getEquipmentStats()[6];
        bonus += getRing().getEquipmentStats()[6];
        bonus += getHat().getEquipmentStats()[6];
        bonus += getGoggles().getEquipmentStats()[6];
        return bonus;
    }
    
    //Takes an array of stats from an item and adds it to the players
    //Used for HP pots and MP pots and such (for now)
    //Look at Item use documentation for order of array
    public void useItem(int[] statsModified) {
        if(statsModified[7] == 1){//if it can increase max hp or mp, can probably make if-else better
        
            super.setCurrentHealth(super.getCurrentHealth() + statsModified[0]);
        }
        else{
            if(super.getCurrentHealth() + statsModified[0] > super.getMaxHealth())
                super.setCurrentHealth(super.getMaxHealth());
            else
                super.setCurrentHealth(super.getCurrentHealth() + statsModified[0]);
        }
        
        if(statsModified[7] == 1){//if it can increase max hp or mp, can probably make if-else better
        
            super.setCurrentMana(super.getCurrentMana() + statsModified[1]);
        }
        else{
            if(super.getCurrentMana() + statsModified[1] > super.getMaxMana())
                super.setCurrentMana(super.getMaxMana());
            else
                super.setCurrentMana(super.getCurrentMana() + statsModified[1]);
        }
        
        super.setCurrentStrength(super.getCurrentStrength() + statsModified[2]);
        super.setCurrentMagic(super.getCurrentMagic() + statsModified[3]);
        super.setCurrentAgility(super.getCurrentAgility() + statsModified[4]);
        super.setCurrentDefense(super.getCurrentDefense() + statsModified[5]);
        super.setCurrentMagicDefense(super.getCurrentMagicDefense() + statsModified[6]);
    }
    
    //Sets the equipment based on it's type
    //Look at docs for equipment type
    public void setEquipment(Equipment e) {
        switch (e.getEquipmentType()) {
            case 1:
                setWeapon((Weapon)e);
                break;
            case 2:
                setChest(e);
                break;
            case 3:
                setLegs(e);
                break;
            case 4:
                setBracers(e);
                break;
            case 5:
                setBoots(e);
                break;
            case 6:
                setGloves(e);
                break;
            case 7:
                setOffHand((Weapon)e);
                break;
            case 8:
                setOffHand((Weapon)e);
                break;
            case 9:
                setRing(e);
                break;
            case 10:
                setHat(e);
                break;
            case 11:
                setGoggles(e);
                break;
        }
    }
}
