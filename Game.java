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

public class Game 
{
    Scanner scan = new Scanner(System.in);
    
    static String lineBreak = "==========================";
    Room currentRoom = null; //the current room the player is in
    String userInput = "";
    
    //loadRooms() maybe
    
    public Game(Player hero)
    {
        //load all rooms here right now?? hmm hmmmmmmmmm......
        printBreak();
        
        //for(Room r: textrpg.rooms.Room.class.get)//maybe have a constant that is all of the room names
        
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
            case "b": Slime m = new Slime();
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
        Date now = new Date(System.currentTimeMillis());
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
