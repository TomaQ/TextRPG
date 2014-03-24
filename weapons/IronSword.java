package textrpg.weapons;

public class IronSword extends Weapon
{
    public IronSword()
    {
        int[] s = {5, 2, 0, 0, 0, 0, 0};//testing not official {str, def, ?, ?, ?, ?, ?}
        super.setGoldWorth(50);
        super.setEquipmentType(1);
        super.setEquipmentStats(s);
        super.setName("Iron Sword");
        
        super.setWeaponType(1);//testing not for realzz
        super.setWeaponDmg(9001);//lololz
    }
}
