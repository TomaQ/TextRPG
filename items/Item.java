package textrpg.items;

public class Item 
{
    private int itemType; //the type of item it is
    //1 = consumable item(pots, elixers, food, and such that modifies stats or states)
    //2 = key items/quest items/materials
    //should work on this.....
    
    private int goldWorth;
    private int[] statsModified;
    
    private String name;
    private String itemDescription;
    
    public int getItemType(){return itemType;}
    public void setItemType(int t){itemType = t;}
    
    public int getGoldWorth(){return goldWorth;}
    public void setGoldWorth(int g){goldWorth = g;}
    
    public String getName(){return name;}
    public void setName(String s){name = s;}
    
    public String getItemDescription(){return itemDescription;}
    public void setItemDescription(String s){itemDescription = s;}
    
    public int[] getStatsModified(){return statsModified;}
    public void setStatsModified(int[] i){statsModified = i;}
    
    public int[] use(){int[] i = {-1};return i;}//NEED TO OVERWRITE
    
    protected String itemErrorString = "You cannot use this item!";
    public void itemError()
    {
        System.out.println(itemErrorString);
    }
}
