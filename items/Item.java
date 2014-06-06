package textrpg.items;

public class Item 
{

    private String name;
    private String itemDescription;
    private String itemRoomDescription;

    private int itemType; //The type of item it is
    //1 = consumable item(pots, elixers, food, and such that modifies stats or states)
    //2 = materials and such
    //3 = quest item or some item that you cannot sell or destroy
    //should work on this.....

    public final int[] no_stats_modified = {0, 0, 0, 0, 0, 0, 0, 0};

    private String[] tags;//These are tags that the item can be refered to
    //Say there is a HealthPotion in the room , if someone wants to take it they would type "take pot" or "take 'tag'" etc.
    //Different items cannot have the same tag
    //One of the tags for the item must be the item name (it is not included by default)
    //All Items must have tags including child classes (Equipment/Weapons)

    private int goldWorth;
    private int[] statsModified = new int[8];
    //The stats the item modifies
    //hp, mana, str, mag, agi, def, mdef, (1 or 0)
    //If index 8 is 1 then it can increase the hp or mana over the max else it can't

    private boolean consumable;
    
    /**
     * Returns the type of the Item as an int. 1 is consumable, 2 is material, 3 is quest item.
     * @return itemType
     */
    public int getItemType(){return itemType;}
    /**
     * Sets the item type of the Item. 1 is consumable, 2 is material, 3 is quest item.
     * @param itemType
     */
    public void setItemType(int t){itemType = t;}
    
    /**
     * Returns the worth of the item in gold.
     * @return goldWorth
     */
    public int getGoldWorth(){return goldWorth;}
    
    /**
     * Sets the worth of the item in gold.
     * @param goldWorth 
     */
    public void setGoldWorth(int g){goldWorth = g;}
    
    /**
     * Returns the selling price of the item in gold.
     * @return sellingPrice
     */
    public int getSellingPrice(){return goldWorth/2;}
    
    /**
     * Returns the name of the Item.
     * @return name
     */
    public String getName(){return name;}
    
    /**
     * Sets the name of the Item.
     * @param name
     */
    public void setName(String s) {
        name = s;
        String[] temp = {s};
        tags = temp; //Sets the item tag to the name by default
    }
    
    /**
     * Returns the description of the Item.
     * @return itemDescription
     */
    public String getItemDescription(){return itemDescription;}
    
    /**
     * Sets the description of the Item.
     * @param s 
     */
    public void setItemDescription(String s){itemDescription = s;}
    
    /**
     * Returns the description of the Item as it is found in the Room.
     * @return itemRoomDescription
     */
    public String getItemRoomDescription(){return itemRoomDescription;}
    
    /**
     * Sets the description of the Item as it is found in the Room.
     * @param s
     */
    public void setItemRoomDescription(String s){itemRoomDescription = s;}
    
    /**
     * Returns the stats that are modified from the Item.
     * @return itemRoomDescript
     */
    public int[] getStatsModified(){return statsModified;}
    
    /**
     * Sets the stats that are modified from the Item.
     * @param i 
     */
    public void setStatsModified(int[] i){statsModified = i;}
    
    /**
     * Returns an array of tags that are associated with the Item. Tags are 'nicknames' that can be used to reffer to the Item.
     * @return tags
     */
    public String[] getTags(){return tags;}
    
    /**
     * Sets an array of tags that are associated with the Item. Tags are 'nicknames' that can be used to reffer to the Item.
     * @param t 
     */
    public void setTags(String[] t) {
        String[] temp = new String[t.length + 1];
        
        System.arraycopy(t, 0, temp, 0, t.length);
        
        temp[t.length] = getName().toLowerCase(); //Inserts the items name as a tag
        tags = temp;
    }
    
    /**
     * Returns true if the item is destroyed upon use.
     * @return consumable
     */
    public boolean isConsumable(){return consumable;}
    
    /**
     * Sets if the item is destroyed upon use.
     * @param b 
     */
    public void setConsumable(boolean b){consumable = b;}
    
     //Overwrite if you can use the item
    /**
     * Returns an array of the stats that are modified from the Item.
     * @return 
     */
    public int[] use() {
        itemError();
        return no_stats_modified;
    }

    private final String itemErrorString = "You cannot use this item!";

    /**
     * Prints an error stating that the Player is unable to use the Item.
     */
    public void itemError() {
        System.out.println(itemErrorString);
    }
}
