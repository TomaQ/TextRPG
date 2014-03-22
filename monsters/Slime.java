package textrpg.monsters;

import textrpg.items.SlimeExtract;

public class Slime extends Monster
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
        
        super.setGoldWorth(30);
        //super.setLoot(SlimeExtract);
        super.initCurrentStats();
    }
    
    public int slash()
    {
        return (int)(super.getBaseStrength() * 0.5) + 10;
    }
}
