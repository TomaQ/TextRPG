//Need to look at json stuff

package textrpg.rooms;

import java.util.ArrayList;
import java.util.List;
import textrpg.items.*;
import textrpg.monsters.*;
import textrpg.npcs.NPC;
import textrpg.shops.Shop;

public class Room {
 
    private String roomName;
    private String roomDescription;
    private Room nExit = null;
    private Room sExit = null;
    private Room eExit = null;
    private Room wExit = null;
    
    List<Item> roomLoot = new ArrayList<>();
    private Monster[] monsterEncounters;
    private int[] monsterEncounterChance;//out of 100%, must match index's with monsterEncounters
    
    private NPC[] npcsInRoom;
    private Shop store; //only one shop per room
    
    public String getRoomName(){return roomName;}
    public void setRoomName(String n){roomName = n;}
     
    public String getRoomDescription() {
        String temp = roomDescription;
        for(int i = 0; i < roomLoot.size(); i++){
            String iName = roomLoot.get(i).getName().toLowerCase();
            if (temp.contains(iName + " laying on the ground")){
                int index = temp.indexOf(iName + " laying on the ground") - ("AEIOU".contains(String.valueOf(iName.charAt(0))) ? 4 : 3);
                char quanity = temp.charAt(index); //checking to see if there is a digit in the line because if there is then that means there are already more than two of the same items in the same spot
                if (Character.isDigit(quanity)){
                    quanity++;
                    temp = temp.substring(0, index) + quanity + temp.substring(index+1);
                }
                else{
                    temp = temp.substring(0, index-9);
                    temp+= " There are 2 " + iName + "s laying on the ground."; 
                }
            }
            else{
                temp += " " + roomLoot.get(i).getItemRoomDescription();
            }
        }
        return temp;
    }
    public void setRoomDescription(String n){roomDescription = n;}
    
    public List<Item> getRoomLoot(){return roomLoot;}//returns a list of items
    
    //Tells you the exits that are available from the current room NEED TO FIX FORMATTTTT
    public void getExits() {
        if (getNExit() == null && getSExit() == null && getEExit() == null && getWExit() == null) {
            System.out.println("There are no exits!");
        }
        else {
            String sExits = "";//the string of exits
            if (oneExit() == true) {
                System.out.print("There is one exit to the ");
            }
            else {
                System.out.print("There are exits to the ");
            }
            if (getNExit() != null) {
                sExits += "north, ";
            }
            if (getSExit() != null) {
                sExits += "south, ";
            }
            if (getEExit() != null) {
                sExits += "east, ";
            }
            if (getWExit() != null) {
                sExits += "west, ";
            }

            sExits = sExits.substring(0, sExits.length() - 2);
            System.out.print(sExits);
            System.out.println(".");
        }
    }
    
    public Room getNExit(){return nExit;}
    public Room getSExit(){return sExit;}
    public Room getEExit(){return eExit;}
    public Room getWExit(){return wExit;}
 
    public void setExits(Room n, Room s, Room e, Room w) {
        nExit = n;
        sExit = s;
        eExit = e;
        wExit = w;
    }
    
    //Returns true if there is only one exit
    private boolean oneExit() {
        int j = 0;
        if(getNExit() != null)
            j++;
        if(getSExit() != null)
            j++;
        if(getEExit() != null)
            j++;
        if(getWExit() != null)
            j++;
        if(j == 1)
            return true;
        return false;
    }

    public void enterRoomText() {
        System.out.println("You entered " + getRoomName());
        System.out.println(getRoomDescription());
    }
    
    public static void noExit(){System.out.println("There is no exit this way!");}
    
    public Monster[] getMonsterEncounters(){return monsterEncounters;}
    public void setMonsterEncounters(Monster[] m){monsterEncounters = m;}
    
    public int[] getMonsterEncounterChance(){return monsterEncounterChance;}
    public void setMonsterEncounterChance(int[] i){monsterEncounterChance = i;}
    
    public NPC[] getNPCsInRoom(){return npcsInRoom;}
    public void setNPCsInRoom(NPC[] n){npcsInRoom = n;}
    
    public Shop getShop(){return store;}
    public void setShop(Shop s){store = s;}
}
