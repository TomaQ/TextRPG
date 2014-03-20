//All living things in the game (The Player, NPCs, and monsters) inherit this class

package textrpg;

public class Entity
{
    private String name;
    private int baseHealth, currentHealth;
    private int baseMana, currentMana;
    private int baseStrength, currentStrength;
    private int baseMagic, currentMagic;
    private int baseAgility, currentAgility;
    private int baseDefense, currentDefense;
    private int baseMagicDefense, currentMagicDefense;//lvl up in job objects, also need to set current of each stat somewhere
    
    public String getName(){return name;}
    public void setName(String input){name = input;}
    
    public int getCurrentHealth()
    {
        if(currentHealth < 0)
            return 0;
        return currentHealth;
    }
    public void setCurrentHealth(int input){currentHealth = input;} 
    public int getBaseHealth(){return baseHealth;}
    public void setBaseHealth(int input){baseHealth = input;}
    
    public int getCurrentMana()
    {
        if(currentMana < 0)
            return 0;
        return currentMana;
    }
    public void setCurentMana(int input){currentMana = input;}
    public int getBaseMana(){return baseMana;}
    public void setBaseMana(int input){baseMana = input;}
    
    public int getCurrentStrength()
    {
        if(currentStrength < 0)
            return 0;
        return currentStrength;
    }
    public void setCurrentStrength(int input){currentStrength = input;}
    public int getBaseStrength(){return baseStrength;}
    public void setBaseStrength(int input){baseStrength = input;}
    
    public int getCurrentMagic()
    {
        if(currentMagic < 0)
            return 0;
        return currentMagic;
    }
    public void setCurrentMagic(int input){currentMagic = input;}
    public int getBaseMagic(){return baseMagic;}
    public void setBaseMagic(int input){baseMagic = input;}
    
    public int getCurrentAgility()
    {
        if(currentAgility < 0)
            return 0;
        return currentAgility;
    }
    public void setCurrentAgility(int input){currentAgility = input;}
    public int getBaseAgility(){return baseAgility;}
    public void setBaseAgility(int input){baseAgility = input;}
    
    public int getCurrentDefense()
    {
        if(currentDefense < 0)
            return 0;
        return currentDefense;
    }
    public void setCurrentDefense(int input){currentDefense = input;}
    public int getBaseDefense(){return baseDefense;}
    public void setBaseDefense(int input){baseDefense = input;}
    
    public int getCurrentMagicDefense()
    {
        if(currentMagicDefense < 0)
            return 0;
        return currentMagicDefense;
    }
    public void setCurrentMagicDefense(int input){currentMagicDefense = input;}
    public int getBaseMagicDefense(){return baseMagicDefense;}
    public void setBaseMagicDefense(int input){baseMagicDefense = input;}
    
    public void initCurrentStats()
    {
        
    }
}