package textrpg;

public class Being 
{
    private String name;
    private int health;
    private int mana;
    private int strength;
    private int magic;
    private int agility;
    private int defense;
    private int magicDefense;
    
    public String getName(){return name;}
    public void setName(String input){name = input;}
    
    public int getHealth(){return health;}
    public void setHealth(int input){health = input;}
    
    public int getMana(){return mana;}
    public void setMana(int input){mana = input;}
    
    public int getStrength(){return strength;}
    public void setStrength(int input){strength = input;}
    
    public int getMagic(){return magic;}
    public void setMagic(int input){magic = input;}
    
    public int getAgility(){return agility;}
    public void setAgility(int input){agility = input;}
    
    public int getDefense(){return defense;}
    public void setDefense(int input){defense = input;}
    
    public int getMagicDefense(){return magicDefense;}
    public void setMagicDefense(int input){magicDefense = input;}
}