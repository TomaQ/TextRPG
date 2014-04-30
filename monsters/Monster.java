package textrpg.monsters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import textrpg.Entity;
import textrpg.items.*;
//import textrpg.skills.Skill;

public class Monster extends Entity
{
    private List<Item> loot = new ArrayList<Item>();
    private int[] lootChance; //don't need to return loot and chance so don't need a map
    private int goldWorth;
    private int expWorth;
    private int weakness;//the elemental weakness the monster has
    //1 = physical, 2 = fire, 3 = water, 4 = lightning, 5 = ice, more?!
    
    private int escapable;//if you can't run away then set to 0
    
    public List<Item> getLoot(){return loot;}
    public void setLoot(List<Item> l){loot = l;}
    
    public int[] getLootChance(){return lootChance;} //returns a whole number out of 100
    public void setLootChance(int[] i){lootChance = i;}
    
    public int getGoldWorth(){return goldWorth;}//usually called in a statement multiplied by a random decimal 
    public void setGoldWorth(int g){goldWorth = g;}
    
    public int getExpWorth(){return expWorth;}
    public void setExpWorth(int i){expWorth = i;}
    
    public int getWeakness(){return weakness;}
    public void setWeakness(int i){weakness = i;}
    
    public int getEscapable(){return escapable;}
    public void setEscapable(int i){escapable = i;}
    
    public void setRandomLoot() { //better way to do this, will fix later (setting loot twice and such)
        Random rand = new Random(); //should make a random int method

        for (int i = 0; i < this.getLoot().size(); i++) {
            if (rand.nextInt(101) > lootChance[i]) {
                loot.remove(loot.get(i));
            }
        }
    }
    
    //public Skill[] getSkills(){return blahhh;}
}
