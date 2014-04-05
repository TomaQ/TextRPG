package textrpg.monsters;

import textrpg.items.*;

public class Slime extends Monster //should be resistant to physical attacks!
{
    public Slime()
    {
        super.setName("Slime");
        super.setBaseHealth(200);
        super.setBaseMana(10);        
        super.setBaseStrength(5);
        super.setBaseMagic(2);
        super.setBaseAgility(3);
        super.setBaseDefense(6);
        super.setBaseMagicDefense(2);
        
        super.setGoldWorth();
        super.setExpWorth(25);
        super.setWeakness(2);
        super.setEscapable(1);
        
        Item slimeE = new SlimeExtract();
        Item[] loot = {slimeE};
        super.setLoot(loot);
        super.initCurrentStats();
    }
    
    public int slash()
    {
        return (int)(super.getBaseStrength() * 0.5) + 10;
    }
}
