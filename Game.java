//This is where the game runs

package textrpg;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import textrpg.equipment.*;
import textrpg.items.*;
import textrpg.monsters.Slime;
import textrpg.rooms.*;
import textrpg.weapons.*;
import com.google.gson.*;

public class Game 
{
    Scanner scan = new Scanner(System.in);
    
    static String lineBreak = "==========================";
    Room currentRoom = null; //the current room the player is in
    String userInput = "";
    
    //loadRooms() maybe
    
    public Game(Player hero)
    {
        printBreak();

        currentRoom = hero.getLocation();
        currentRoom.enterRoomText();
               
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
    
    public final void command(Player hero)
    { 
        switch(userInput.trim())
        {
            case "b": Slime m = new Slime();
                new Battle(hero, m);
                break;
            case "help": System.out.println("'quit' to quit");
                break;
            case "n": if(currentRoom.getNExit() != null){currentRoom = hero.getLocation().getNExit();
                                                    hero.setLocation(currentRoom); currentRoom.enterRoomText();}
                else{Room.noExit();}
                break;
            case "s": if(currentRoom.getSExit() != null){currentRoom = hero.getLocation().getSExit();
                                                    hero.setLocation(currentRoom); currentRoom.enterRoomText();}
                else{Room.noExit();}
                break;
            case "e": if(currentRoom.getEExit() != null){currentRoom = hero.getLocation().getEExit();
                                                    hero.setLocation(currentRoom); currentRoom.enterRoomText();}
                else{Room.noExit();}
                break;
            case "w": if(currentRoom.getWExit() != null){currentRoom =hero.getLocation().getWExit();
                                                    hero.setLocation(currentRoom); currentRoom.enterRoomText();}
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
            case "save": saveTheGame(hero);
                break;
            case "equipment": hero.printEquipment();
                break;
            default: System.out.println("Command not recognized.");   
                break;
        }
    }
    
    private void startingThingsForTesting(Player hero)
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

    public static void saveTheGame(Player hero){
        String fileName = "player" + hero.getName() + ".dat";
        Date now = new Date(System.currentTimeMillis());//for including date and time(I'm not sure that it should be)
        ObjectOutputStream out;
        try{
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            //out.writeObject(now);
            out.writeObject(hero);
            System.out.println("Saved at: " + now.toString());
        }catch (FileNotFoundException ex){

        }catch (IOException ex){ex.printStackTrace();}

    }

    public static Player loadTheGame(String heroName){
        String fileName = "player" + heroName + ".dat";
        ObjectInputStream in;
        Player player = null;
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            player = (Player) in.readObject();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return player;
    }
}
