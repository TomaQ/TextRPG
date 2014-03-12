package textrpg.weapons;

public class IronSword extends Weapon
{
    public IronSword()
    {
        int[] stats = {5, 2, 0,0, 0, 0, 0};//testing not official
        super.setGoldWorth(50);
        super.setEquipmentType(1);
        super.setEquipmentStats(stats);
        
        super.setWeaponType(1);//testing not for realzz
        super.setWeaponDmg(9001);//lololz
    }
}
