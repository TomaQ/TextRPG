//This is where the game runs

package textrpg;

import java.util.Scanner;
import textrpg.equipment.*;
import textrpg.items.*;
import textrpg.monsters.Slime;
import textrpg.rooms.*;
import textrpg.weapons.*;

public class Game 
{
    Scanner scan = new Scanner(System.in);
    
    static String lineBreak = "==========================";
    Room currentRoom = null; //the current room the player is in
    String userInput = "";
    
    //loadRooms() maybe
    
    public Game(Player hero)
    {
        
        //load rooms
        //set room exits
        
        printBreak();
        
        Room prRoom = new PowerRangerRoom(null, null, null, null);
        
        currentRoom = new StartingRoom(prRoom, null, null, null);//generates the starting room, for testing atm
        
        //need player to know which room they're in
        
        currentRoom.enterRoomText();//need to print every time they enter a new room
               
        startingThingsForTesting(hero);//all of the testing stuff goes here
        
        while(!userInput.equals("quit"))
        {
            userInput = scan.nextLine();
            command(hero);
        }
        
        System.out.println("Bye!!");
    } 
    
    public static final void printBreak()//prints a LINE_BREAKERRR!
    {
        System.out.println(lineBreak);
    }
    
    public final void command(Player hero)//checks what to do from the users input, need to parse spaces
    { 
        int i = userInput.indexOf(' ');
        String firstUserInput, restofUserInput = "";
        if(i > 0)// if there's a space then parse it
        {
            firstUserInput = userInput.substring(0, i);
            restofUserInput = userInput.substring(i+1, userInput.length());
        }
        else
            firstUserInput = userInput;
        
        switch(firstUserInput)
        {
            case "b": Slime m = new Slime();//temporary
                new Battle(hero, m);
                break;
            case "help": printCommands();
                break;
            case "n": if(currentRoom.getNExit() != null){currentRoom = currentRoom.getNExit();}//methods for these, also need
                else{Room.noExit();}                                                           //to parse spaces
                break;
            case "s": if(currentRoom.getSExit() != null){currentRoom = currentRoom.getSExit();}
                else{Room.noExit();}
                break;
            case "e": if(currentRoom.getEExit() != null){currentRoom = currentRoom.getEExit();}
                else{Room.noExit();}
                break;
            case "w": if(currentRoom.getWExit() != null){currentRoom = currentRoom.getWExit();}
                else{Room.noExit();}
                break;    
            case "exits": currentRoom.getExits();
                break;
            case "look": System.out.println(currentRoom.getRoomDescription());
                break;
            case "inventory": hero.printInventory();
                break;
            case "skills": hero.getJob().printSkills();
                break;
            case "status": hero.printStatus();  
                break;
            case "equipment": hero.printEquipment();
                break;
            case "take": takeCommand(restofUserInput, hero);
                break;
            default: System.out.println("Command not recognized.");   
                break;
        }
    }
    
    private void startingThingsForTesting(Player hero)//method for testing purposes
    {
        System.out.println("There is much testing to be done.\n'b' for battle and 'help' for help~~");
        System.out.println("Here are some items for you!");
        Item s = new SlimeExtract();
        hero.addInventory(s);
        Item hp = new HealthPotion();
        hero.addInventory(hp);
        
        System.out.println("Here's some gear!");
        Weapon iron = new IronSword();
        Equipment bronze = new BronzeChest();
        hero.setWeapon(iron);
        hero.setChest(bronze);
        
    }
    
    public void printCommands()
    {
        System.out.println("Commands: n, s, e, w, b, exits, look, inventory, skills, status, equipment, take");
    }
    
    public void takeCommand(String rest, Player hero)//figures out what to take
    {
        boolean pass = false;
        for(int i = 0; i < currentRoom.getRoomLoot().size(); i++)//for some reason using nested for each loops crashes here
        {
            for (String tag : currentRoom.getRoomLoot().get(i).getTags()) 
            {
                if (tag.equals(rest)) 
                {
                    Item temp = currentRoom.getRoomLoot().get(i);//sets the item to be taken to a temp variable
                    
                    hero.addInventory(temp);
                    currentRoom.getRoomLoot().remove(temp);
                    System.out.println("Took " + temp.getName() + ".");
                    pass = true;
                }
            }
        }
        if(!pass) //if there was no item let them know
        {
            System.out.println("There's no item here called that.");
        }
    }
}
