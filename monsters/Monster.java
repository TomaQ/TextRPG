package textrpg.monsters;

import textrpg.Entity;
import textrpg.items.*;
import textrpg.skills.Skill;

public class Monster extends Entity
{
    private int level;
    private Item[] loot;
    private int goldWorth;
    private int expWorth;
    private int weakness;//the elemental weakness the monster has
    //1 = physical, 2 = fire, 3 = water, 4 = lightning, 5 = ice, more?!
    
    private int escapable;//if you can't run away then set to 0
    
    
    public Item[] getLoot(){return loot;}
    public void setLoot(Item[] l){loot = l;}

    public int getGoldWorth(){return goldWorth;}//usually called in a statement multiplied by a random decimal
    public void setGoldWorth(){}
    public void setGoldWorth(int g){goldWorth = g;}//I think this will be needed in the future, when added bonus coins to room's bosses
    public int countGoldWorth(){
        return (int)Math.random()*level + 1;//sketch for formula of calculation golden coins from monster
    }
    
    public int getExpWorth(){return expWorth;}
    public void setExpWorth(int i){expWorth = i;}
    
    public int getWeakness(){return weakness;}
    public void setWeakness(int i){weakness = i;}
    
    public int getEscapable(){return escapable;}
    public void setEscapable(int i){escapable = i;}

    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}

    //public Skill[] getSkills(){return blahhh;}
}
