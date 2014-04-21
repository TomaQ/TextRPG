//Need to look at json stuff

package textrpg.rooms;

import textrpg.items.*;
import textrpg.monsters.*;

public class Room
{
    
    private String roomName;
    private String roomDescription;
    private Room nExit = null;
    private Room sExit = null;
    private Room eExit = null;
    private Room wExit = null;
    
    private Item[] roomLoot = null;
    private Monster[] monsterEncounters;
    private int[] monsterEncounterChance;//out of 100, must match index's with monsterEncounters
    
    public String getRoomName(){return roomName;}
    public void setRoomName(String n){roomName = n;}
     
    public String getRoomDescription(){return roomDescription;}
    public void setRoomDescription(String n){roomDescription = n;}
    
    public Item[] getRoomLoot(){return roomLoot;}//returns an array of items
    public void setRoomLoot(Item[] i){roomLoot = i;}//have to pass an array of items
    
    
    public void getExits()//tells you the exits that are available from the current room NEED TO FIX FORMATTTTT
    {
        if(getNExit() == null && getSExit() == null && getEExit() == null && getWExit() == null)
        {
            System.out.println("There are no exits!");
        }
        else
        {
            String sExits = "";//the string of exits
            if(oneExit() == true)
                System.out.print("There is one exit to the ");
            else
                System.out.print("There are exits to the ");
            if(getNExit() != null)
                sExits += "north, ";
            if(getSExit() != null)
                sExits += "south, ";
            if(getEExit() != null)
                sExits += "east, ";
            if(getWExit() != null)
                sExits += "west, ";
            
            sExits = sExits.substring(0, sExits.length() - 2);
            System.out.print(sExits);
            System.out.println(".");
        }
    }
    
    public Room getNExit()
    {
        return nExit;
    }
    public Room getSExit()
    {
        return sExit;
    }
    public Room getEExit()
    {
        return eExit;
    }
    public Room getWExit()
    {
        return wExit;
    }
    
    public void setExits(Room n, Room s, Room e, Room w)
    {
        nExit = n;
        sExit = s;
        eExit = e;
        wExit = w;
    }
    
    public boolean oneExit()//returns true if there is only one exit
    {                                    //can make these ints intead of checking room (?)
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
   public void enterRoomText()
    {
        System.out.println("You entered " + getRoomName());
        System.out.println(getRoomDescription());
    }
    
    public static void noExit(){System.out.println("There is no exit this way!");}
    
    public Monster[] getMonsterEncounters(){return monsterEncounters;}
    public void setMonsterEncounters(Monster[] m){monsterEncounters = m;}
    
    public int[] getMonsterEncounterChance(){return monsterEncounterChance;}
    public void setMonsterEncounterChance(int[] i){monsterEncounterChance = i;}
}
