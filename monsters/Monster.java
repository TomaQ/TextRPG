package textrpg.monsters;

import textrpg.Entity;
import textrpg.items.*;

public class Monster extends Entity
{
    private Item[] loot;
    private int goldWorth;
    
    
    public Item[] getLoot(){return loot;}
    public void setLoot(Item[] l){loot = l;}
    
    public int getGoldWorth(){return goldWorth;}//usually called in a statement multiplied by a random decimal 
    public void setGoldWorth(int g){goldWorth = g;}
    
    //public something getSkills(something){something something something}
}
