package textrpg.skills;

public class Skill 
{
    private String name;
    private int levelRequirement;
    private String[] itemsRequired;
    
    public String getNameZ(){return name;}
    public void setName(String n){name = n;}
    
    public int getLevelRequirement(){return levelRequirement;}
    public void setLevelRequirement(int l){levelRequirement = l;}
    
    public String[] getItemsRequired(){return itemsRequired;}
    public void setItemsRequired(String[] s){itemsRequired = s;}
}
