//This is where the game runs

package textrpg;

import java.util.Scanner;
import textrpg.equipment.*;
import textrpg.items.*;
import textrpg.monsters.Slime;
import textrpg.rooms.*;
import textrpg.weapons.*;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Game 
{
    
    Scanner scan = new Scanner(System.in);
    
    static String lineBreak = "==========================";
    Room currentRoom = null; //the current room the player is in
    String userInput = "";
    
    //loadRooms() maybe
    
    public Game(Player hero) throws IOException
    {
        File dir = new File("src/textrpg");
        File testFile = new File(dir, "testfile.data");
        if(!testFile.exists())
            testFile.createNewFile();
        FileWriter fw = new FileWriter(testFile);
        fw.write("PeepQQQ");
        //BufferedReader bfr = new BufferedReader(new FileReader(testFile));
        //System.out.println(bfr.readLine());
        
        currentRoom = new StartingRoom();
        Gson gson = new Gson();
        fw.write(gson.toJson(currentRoom));
        fw.close();
        
        printBreak();
        
        currentRoom = new StartingRoom();//generates the starting room, for testing atm
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
        switch(userInput)
        {
            case "b": Slime m = new Slime();//temporary
                new Battle(hero, m);
                break;
            case "help": System.out.println("'quit' to quit");
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
}
