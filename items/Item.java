package textrpg.items;

public class Item 
{
    private int itemType; //the type of item it is
    //1 = consumable item(pots, elixers, food, and such that modifies stats or states)
    //2 = key items/quest items/materials
    //should work on this.....
    
    public final int[] no_stats_modified = {0, 0, 0, 0, 0, 0, 0, 0};
    
    private String[] tags = null;//these are tags that the item can be refered to
    //say there is a HealthPotion in the room , if someone wants to take it they would type "take pot" or "take 'tag'" etc.
    
    private int goldWorth;
    private int[] statsModified = new int[8];
    //the stats the item modifies
    //hp, mana, str, mag, agi, def, mdef, (1 or 0)
    //if index 7 is 1 then it can increase the hp or mana over the max else it can't
    
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
    
    public String[] getTags(){return tags;}
    public void setTags(String[] t){tags = t;}
    
    public int[] use(){int[] i = {-1};return i;}//NEED TO OVERWRITE
    
    protected String itemErrorString = "You cannot use this item!";
    public void itemError()
    {
        System.out.println(itemErrorString);
    }
}
