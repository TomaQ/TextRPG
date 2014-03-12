package textrpg.weapons;

import textrpg.equipment.Equipment;

public class Weapon extends Equipment
{
    private int weaponType;
    private int weaponDmg;
    
    public int getWeaponType(){return weaponType;/*sort of, need a method to determine types of weapons*/}
    public void setWeaponType(int w){weaponType = w;}
    
    public int getWeaponDmg(){return weaponDmg;}
    public void setWeaponDmg(int w){weaponDmg = w;}
}
