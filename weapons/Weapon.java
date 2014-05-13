package textrpg.weapons;

import textrpg.equipment.Equipment;

public class Weapon extends Equipment {

    private int weaponType; //The type of weapon it is
    //1 = blade, 2 = mace, 3 = dagger, 4 = staff, more to come
    
    private int weaponDmg;
    
    // need a method to determine types of weapons
    //i dont remember why though
    public int getWeaponType(){return weaponType;}
    public void setWeaponType(int w){weaponType = w;}
    
    public int getWeaponDmg(){return weaponDmg;}
    public void setWeaponDmg(int w){weaponDmg = w;}
}
