package textrpg.items;

public class Item 
{
    private int itemType; //The type of item it is
    //1 = consumable item(pots, elixers, food, and such that modifies stats or states)
    //2 = materials and such
    //3 = quest item or some item that you cannot sell or destroy
    //should work on this.....
    
    public final int[] no_stats_modified = {0, 0, 0, 0, 0, 0, 0, 0};
    
    private String[] tags = null;//These are tags that the item can be refered to
    //Say there is a HealthPotion in the room , if someone wants to take it they would type "take pot" or "take 'tag'" etc.
    
    private int goldWorth;
    private int[] statsModified = new int[8];
    //The stats the item modifies
    //hp, mana, str, mag, agi, def, mdef, (1 or 0)
    //If index 8 is 1 then it can increase the hp or mana over the max else it can't
    
    private boolean consumable;
    
    private String name;
    private String itemDescription;
    private String itemRoomDescription;
    
    public int getItemType(){return itemType;}
    public void setItemType(int t){itemType = t;}
    
    public int getGoldWorth(){return goldWorth;}
    public void setGoldWorth(int g){goldWorth = g;}
    
    public int getSellingPrice(){return goldWorth/2;}
    
    public String getName(){return name;}
    public void setName(String s){name = s;}
    
    public String getItemDescription(){return itemDescription;}
    public void setItemDescription(String s){itemDescription = s;}
    
    public String getItemRoomDescription(){return itemRoomDescription;}
    public void setItemRoomDescription(String s){itemRoomDescription = s;}
    
    public int[] getStatsModified(){return statsModified;}
    public void setStatsModified(int[] i){statsModified = i;}
    
    public String[] getTags(){return tags;}
    public void setTags(String[] t){tags = t;}
    
    public boolean isConsumable(){return consumable;}
    public void setConsumable(boolean b){consumable = b;}
    
     //Overwrite if you can use the item
    public int[] use() {
        this.itemError();
        return no_stats_modified;
    }

    private final String itemErrorString = "You cannot use this item!";

    public void itemError() {
        System.out.println(itemErrorString);
    }
}
