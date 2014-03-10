package textrpg.monster_types;

public class Slime extends Monster
{
    public Slime()
    {
        super.setName("Slime");
        super.setHealth(50);
        super.setMana(10);        
        super.setStrength(5);
        super.setMagic(2);
        super.setAgility(3);
        super.setDefense(6);
        super.setMagicDefense(2);
    }
    
    public int slash()
    {
        return (int)(super.getStrength() * 0.5) + 10;
    }
}
