//This is where the game runs
package textrpg;

import java.util.Scanner;
import textrpg.equipment.*;
import textrpg.items.*;
import textrpg.monsters.Slime;
import textrpg.npcs.NPC;
import textrpg.rooms.*;
import textrpg.weapons.*;

public class Game {

    Scanner scan = new Scanner(System.in);
    static String lineBreak = "=========================="; //String for breaking up likes, might get rid of

    Room currentRoom = null; //the current room the player is in
    String userInput = ""; //does this need to be global?

    //loadRooms() maybe
    public Game(Player hero) {
        //Eventually there'll be a database that stores all of the rooms and other things
        printBreak();

        //This block is temp for now since rooms are just objects sitting in a class right now
        Room prRoom = new PowerRangerRoom(null, null, null, null);
        Room startRoom = new StartingRoom(null, null, null, null);//there's a better way to do this...
        prRoom.setExits(null, startRoom, null, null);
        startRoom.setExits(prRoom, null, null, null);

        currentRoom = startRoom;//for testing atm

        //need player to know which room they're in
        currentRoom.enterRoomText();//need to print every time they enter a new room

        startingThingsForTesting(hero);//all of the testing stuff goes here

        //This is the main loop for the game
        while (!userInput.equals("quit")) {
            System.out.print(">");
            userInput = scan.nextLine();
            command(hero);
        }

        System.out.println("Bye!!");
    }

    public static final void printBreak() {//prints a LINE_BREAKERRR!
        System.out.println(lineBreak);
    }

    private void command(Player hero) {//checks what to do from the users input
        userInput = userInput.toLowerCase();
        int i = userInput.indexOf(' '); //Gets the space in the command
        String firstUserInput, restofUserInput = ""; //The first input is commands such as 'take' or 'equip' while the rest can be item names or such

        if (i > 0) {// if there's a space then parse it
            firstUserInput = userInput.substring(0, i);
            restofUserInput = userInput.substring(i + 1, userInput.length());
        }
        else {
            firstUserInput = userInput;
        }

        firstUserInput = parseUserInput(firstUserInput);

        switch (firstUserInput) { //this will be better later....
            case "battle":
                Slime m = new Slime();//temporary
                new Battle(hero, m);
                break;
            case "help":
                printCommands();
                break;
            case "quit":
                break;
            case "north":
                if (currentRoom.getNExit() != null) {
                    currentRoom = currentRoom.getNExit();
                    currentRoom.enterRoomText();
                }//methods for these, also need
                else {
                    Room.noExit();
                }
                break;
            case "south":
                if (currentRoom.getSExit() != null) {
                    currentRoom = currentRoom.getSExit();
                    currentRoom.enterRoomText();
                }
                else {
                    Room.noExit();
                }
                break;
            case "east":
                if (currentRoom.getEExit() != null) {
                    currentRoom = currentRoom.getEExit();
                    currentRoom.enterRoomText();
                }
                else {
                    Room.noExit();
                }
                break;
            case "west":
                if (currentRoom.getWExit() != null) {
                    currentRoom = currentRoom.getWExit();
                    currentRoom.enterRoomText();
                }
                else {
                    Room.noExit();
                }
                break;
            case "exit":
                currentRoom.getExits();
                break;
            case "look":
                System.out.println(currentRoom.getRoomDescription());
                break;
            case "inventory":
                hero.printInventory();
                break;
            case "skills":
                hero.getJob().printSkills();
                break;
            case "stats":
                hero.printStatus();
                break;
            case "equipment":
                hero.printEquipment();
                break;
            case "take":
                takeCommand(restofUserInput, hero);
                break;
            case "equip":
                equipCommand(restofUserInput, hero);
                break;
            case "talk":
                talkCommand(restofUserInput);
                break;
            case "shop":
                if (currentRoom.getShop() != null) {
                    currentRoom.getShop().enter(hero);
                }
                else {
                    System.out.println("There isn't a shop here.");
                }
                break;
            case "drop":
                dropCommand(restofUserInput, hero);
                break;
            case "unequip":
                unequipCommand(restofUserInput, hero);
                break;
            default:
                System.out.println("Command not recognized.");
                break;
        }
    }

    //Method for testing purpose
    private void startingThingsForTesting(Player hero) {
        System.out.println("There is much testing to be done.\n'b' for battle and 'help' for help~~");
        System.out.println("Here are some items for you!");
        Item s = new SlimeExtract();
        hero.addInventory(s);
        Item hp = new HealthPotion();
        hero.addInventory(hp);

        System.out.println("Here's some gear!");
        Weapon iron = new IronSword();
        Equipment bronze = new BronzeChest();
        hero.getInventory().add(iron);
        hero.setChest(bronze);
    }

    //Formats for shortcuts
    private String parseUserInput(String input) {
        String[] north = {"north", "n"};
        String[] south = {"south", "s"};
        String[] east = {"east", "e"};
        String[] west = {"west", "w"};
        String[] exits = {"exit", "exits", "escapes", "entrance", "entrances"};
        String[] battle = {"battle", "b"}; //Temp
        String[] inventory = {"inventory", "i"};
        String[] look = {"look", "search", "l"};
        String[] stats = {"stats", "status"};
        String[] take = {"take", "get"};
        String[] skills = {"skills", "skill"};

        String[][] commands = {north, south, east, west, exits, battle, inventory, look, stats, take, skills};
        for (int i = 0; i < commands.length; i++) {
            for (int j = 0; j < commands[i].length; j++) {
                if (input.equalsIgnoreCase(commands[i][j])) {
                    input = commands[i][0]; //Sets the input  to the first index of the array
                }
            }
        }
        return input;
    }
    
    //Prints all of the available commands
    private void printCommands() {
        printBreak();
        System.out.println("north, south, east, west - Moves you arround to different rooms.");
        System.out.println("battle - Starts a testing fight against a slime.");
        System.out.println("look - Tells you the room description.");
        System.out.println("inventory - Tells you all of the items that you have on yourself.");
        System.out.println("exits - Tells you where all of the exits are in the room.");
        System.out.println("skills - Tells you all of the skills that you know.");
        System.out.println("status - Tells your stats, name, and class.");
        System.out.println("equipment - Tells you the current equipment that you're wearing.");
        System.out.println("take - Takes an item from something or somewhere. Usage is take 'object' where object is what you want to take.");
        System.out.println("equip - Equips something to youreself. Usage is equip 'object' where object is what you want to equip to yourself.");
        System.out.println("talk - Talks to someone, including yourself! Usage is talk 'person' where person is who you want to talk to. If you don't specify someone then it will talk to whoever it deems is most important.");
        System.out.println("shop - Enters the shop if there is currently one in the room.");
        System.out.println("drop - Removes an item from your inventory and drops it in the room you are currently in.");
        System.out.println("unequip - Unequips something from yourself. Useage is unequip 'object' where object is what you want to unequip from yourself.");
        System.out.println("quit - Quits the game.");
        printBreak();
    }

    //Figures out what to take
    private void takeCommand(String rest, Player hero) {
        boolean pass = false;
        for (int i = 0; i < currentRoom.getRoomLoot().size(); i++) {//for some reason using nested for each loops crashes here
            for (String tag : currentRoom.getRoomLoot().get(i).getTags()) {
                if (tag.equalsIgnoreCase(rest)) {
                    Item temp = currentRoom.getRoomLoot().get(i);//sets the item to be taken to a temp variable

                    hero.addInventory(temp);
                    currentRoom.getRoomLoot().remove(temp);
                    System.out.println("Took " + temp.getName() + ".");
                    pass = true;
                }
            }
        }
        if (!pass) { //If there was no item let them know
            System.out.println("There's no item here called that.");
        }
    }

    
    //REFACTORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
    //Equips something
    private void equipCommand(String input, Player hero) {
        boolean pass = false;
        Equipment temp = null;
        Equipment j = null; //the item that we searched for
        
        for (Item i : hero.getInventory()) {
            if ((i.getName().toLowerCase().equals(input))) {
                if (i.getClass().getSuperclass().equals(Equipment.class) || i.getClass().getSuperclass().equals(Weapon.class)) {
                    pass = true; //^^^ i dont like that line ^^^
                    j = (Equipment)i;//need to access equipmentType method
                    
                    //Look at docs for weapon types (when I write them...)
                    switch (j.getEquipmentType()) {
                        case 1:
                            temp = hero.getWeapon();
                            hero.setWeapon((Weapon) j);
                            break;
                        case 2:
                            temp = hero.getChest();
                            hero.setChest(j);
                            break;
                        case 3:
                            temp = hero.getLegs();
                            hero.setLegs(j);
                            break;
                        case 4:
                            temp = hero.getBracers();
                            hero.setBracers(j);
                            break;
                        case 5:
                            temp = hero.getBoots();
                            hero.setBoots(j);
                            break;
                        case 6:
                            temp = hero.getGloves();
                            hero.setGloves(j);
                            break;
                        case 7:
                            temp = hero.getOffHand();
                            hero.setOffHand((Weapon) j);
                            break;
                        case 8:
                            temp = hero.getOffHand();
                            hero.setOffHand((Weapon) j);
                            break;
                        case 9:
                            temp = hero.getRing();
                            hero.setRing(j);
                            break;
                        case 10:
                            temp = hero.getHat();
                            hero.setHat(j);
                            break;
                        case 11:
                            temp = hero.getGoggles();
                            hero.setGoggles(j);
                            break;
                        default: System.out.println(j.getItemType()); //For debugging
                            break;
                    }
                    System.out.println("Equiped " + i.getName());
                }
                else {
                    System.out.println("You can't equip that!");
                    pass = true;
                }
            }
        }
        
        hero.getInventory().remove(j); //removes the item from the inventory
        
        if (!pass) {
            System.out.println("There's no item here called that.");
        }
        else if(temp != null && !temp.getName().equals("None")){ //Switch the equipment from inventory
            hero.addInventory(temp);
        }
    }

    //Talks to an NPC, 
    private void talkCommand(String input) {
        if (currentRoom.getNPCsInRoom() != null) {
            boolean pass = false;
            
            if (input.trim().equals("")) { //If the player doesn't specify someone
                currentRoom.getNPCsInRoom()[0].printDefaultDialogue();
            }
            else {
                for (NPC n : currentRoom.getNPCsInRoom()) {    //As of right now it can talk to multiple people
                    if (n.getName().equalsIgnoreCase(input)) { //if they have the same name
                        n.printDefaultDialogue();
                        pass = true;
                    }
                }
                if (!pass) {
                    System.out.println("There isn't anyone called that here.");
                }
            }
        }
        else {
            System.out.println("No one's here!");
        }
    }
 
    //Drops an item and puts it in a room
    private void dropCommand(String input, Player hero) {
        boolean found = false;
        for (Item i : hero.getInventory()) { //Loops through the players inventory
            if (searchItem(i, input) != null) { //maybe return item and do the rest in here?
                currentRoom.getRoomLoot().add(i);
                hero.getInventory().remove(i);
                i.setItemRoomDescription("There is a " + i.getName().toLowerCase() + " laying on the ground."); //shows the item in the look command
                System.out.println("Dropped " + i.getName() + ".");
                found = true;
                break; //Don't need to search for other items if it is found already
            }
        }
        if (!found) {
            System.out.println("You don't have an item called that.");
        }
    }

    //Searches for an item, called from the drop command only as of right now
    private Item searchItem(Item i, String input) {
        for (String tag : i.getTags()) { //Loops through all of the items tags
            if (tag.equalsIgnoreCase(input)) {
                if (i.getItemType() != 3) { //If it is not a quest item
                    return i;
                }
                else {
                    System.out.println("You cannot drop that item!");
                    return null;
                }
            }
        }
        return null;
    }

    //Unequips a piece of armor or weapon and places it in the players inventory
    private void unequipCommand(String input, Player hero) {
        boolean pass = false; //checks if something was unequiped

        switch (input) { //separate inputs for boots, boot, weapons (both of them), and all
            case "weapon":
                if (!hero.getWeapon().getName().equals("None")) {
                    unEquip(hero.getWeapon(), hero);
                    pass = true;
                }
                break;
            case "chest":
                if (!hero.getChest().getName().equals("None")) {
                    unEquip(hero.getChest(), hero);
                    pass = true;
                }
                break;
            case "legs":
                if (!hero.getLegs().getName().equals("None")) {
                    unEquip(hero.getLegs(), hero);
                    pass = true;
                }
                break;
            case "bracers":
                if (!hero.getBracers().getName().equals("None")) {
                    unEquip(hero.getBracers(), hero);
                    pass = true;
                }
                break;
            case "boots":
                if (!hero.getBoots().getName().equals("None")) {
                    unEquip(hero.getBoots(), hero);
                    pass = true;
                }
                break;
            case "gloves":
                if (!hero.getGloves().getName().equals("None")) {
                    unEquip(hero.getGloves(), hero);
                    pass = true;
                }
                break;
            case "shield":
                if (!hero.getOffHand().getName().equals("None")) {
                    unEquip(hero.getOffHand(), hero);
                    pass = true;
                }
                break;
            case "offhand":
                if (!hero.getOffHand().getName().equals("None")) {
                    unEquip(hero.getOffHand(), hero);
                    pass = true;
                }
                break;
            case "ring":
                if (!hero.getRing().getName().equals("None")) {
                    unEquip(hero.getRing(), hero);
                    pass = true;
                }
                break;
            case "hat":
                if (!hero.getHat().getName().equals("None")) {
                    unEquip(hero.getHat(), hero);
                    pass = true;
                }
                break;
            case "goggles":
                if (!hero.getGoggles().getName().equals("None")) {
                    unEquip(hero.getGoggles(), hero);
                    pass = true;
                }
                break;
        }

        if (!pass) { //If it did not get unequiped already from above
            for (Equipment e : hero.getEquipment()) {
                if (input.equalsIgnoreCase(e.getName())) {
                    unEquip(e, hero);
                    pass = true;
                }
            }
        }
        if (!pass) { //If nothing was unequiped
            System.out.println("You don't have anything equipped called that.");
        }
    }

    //Does the actual unequiping
    private void unEquip(Equipment e, Player hero) {
        hero.addInventory(e);
        System.out.println("Unequiped " + e.getName());
        Equipment none = new NoneE(); //Need to set the slot now to empty
        hero.setEquipment(none, e.getEquipmentType());
    }
}
