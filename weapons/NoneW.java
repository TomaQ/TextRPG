package textrpg.weapons;

public final class NoneW extends Weapon
{
    public NoneW()
    {
        int[] s = {0, 0, 0, 0, 0, 0, 0};//testing not official {str, def, ?, ?, ?, ?, ?}
        super.setGoldWorth(0);
        super.setEquipmentType(1);
        super.setEquipmentStats(s);
        super.setName("None");
        
        super.setWeaponType(0);//testing not for realzz
        super.setWeaponDmg(0);//lololz
    }
}
