//Need to look at json stuff

package textrpg.rooms;

import java.util.ArrayList;
import java.util.List;
import textrpg.Game;
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
        String[][] inven = Game.getCountedInventory(getRoomLoot());

        for (int i = 0; i < inven.length; i++) {
            Item tempItem = Game.getItem(inven[i][0], getRoomLoot());
            if (tempItem != null) {
                if (tempItem.getItemRoomDescription() != null) {
                    temp += " " + getRoomLoot().get(i).getItemRoomDescription();
                }
                else {
                    if (Integer.valueOf(inven[i][1]) > 1) {
                        temp += " There are " + inven[i][1] + " " + inven[i][0] + "s laying on the ground.";
                    }
                    else {
                        temp += " There is a " + inven[i][0] + " laying on the ground";
                    }
                }
            }
        }
   
//        for (int i = 0; i < getRoomLoot().size(); i++) {
//            if (getRoomLoot().get(i).getItemRoomDescription() != null) {
//                temp += " " + getRoomLoot().get(i).getItemRoomDescription();
//            }
//            else {
//                temp += " " + "There is a " + getRoomLoot().get(i).getName().toLowerCase() + " laying on the ground.";
//            }
//        }
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