//This is where the game runs
package textrpg;

import java.util.Scanner;
import textrpg.equipment.*;
import textrpg.items.*;
import textrpg.monsters.Slime;
import textrpg.rooms.*;
import textrpg.weapons.*;

public class Game {

    Scanner scan = new Scanner(System.in);

    static String lineBreak = "==========================";
    Room currentRoom = null; //the current room the player is in
    String userInput = "";

    //loadRooms() maybe
    public Game(Player hero) {

        //load rooms here
        //set room exits somewhere
        printBreak();

        Room prRoom = new PowerRangerRoom(null, null, null, null);//new method for now and such
        Room startRoom = new StartingRoom(null, null, null, null);//there's a better way to do this...
        prRoom.setExits(null, startRoom, null, null); //dont look at me!
        startRoom.setExits(prRoom, null, null, null);

        currentRoom = startRoom;//for testing atm

        //need player to know which room they're in
        currentRoom.enterRoomText();//need to print every time they enter a new room

        startingThingsForTesting(hero);//all of the testing stuff goes here

        while (!userInput.equals("quit")) {
            userInput = scan.nextLine();
            command(hero);
        }

        System.out.println("Bye!!");
    }

    public static final void printBreak() {//prints a LINE_BREAKERRR!

        System.out.println(lineBreak);
    }

    public final void command(Player hero) {//checks what to do from the users input

        userInput = userInput.toLowerCase();
        int i = userInput.indexOf(' '); //gets the space in the command
        String firstUserInput, restofUserInput = "";

        if (i > 0) {// if there's a space then parse it

            firstUserInput = userInput.substring(0, i);
            restofUserInput = userInput.substring(i + 1, userInput.length());
        }
        else {
            firstUserInput = userInput;
        }

        firstUserInput = parseUserInput(firstUserInput);

        switch (firstUserInput) { //this will be better later....
            case "b":
                Slime m = new Slime();//temporary
                new Battle(hero, m);
                break;
            case "help":
                printCommands();
                break;
            case "n":
                if (currentRoom.getNExit() != null) {
                    currentRoom = currentRoom.getNExit();
                    currentRoom.enterRoomText();
                }//methods for these, also need
                else {
                    Room.noExit();
                }
                break;
            case "s":
                if (currentRoom.getSExit() != null) {
                    currentRoom = currentRoom.getSExit();
                    currentRoom.enterRoomText();
                }
                else {
                    Room.noExit();
                }
                break;
            case "e":
                if (currentRoom.getEExit() != null) {
                    currentRoom = currentRoom.getEExit();
                    currentRoom.enterRoomText();
                }
                else {
                    Room.noExit();
                }
                break;
            case "w":
                if (currentRoom.getWExit() != null) {
                    currentRoom = currentRoom.getWExit();
                    currentRoom.enterRoomText();
                }
                else {
                    Room.noExit();
                }
                break;
            case "exits":
                currentRoom.getExits();
                break;
            case "look":
                System.out.println(currentRoom.getRoomDescription());
                break;
            case "i":
                hero.printInventory();
                break;
            case "skills":
                hero.getJob().printSkills();
                break;
            case "status":
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
            default:
                System.out.println("Command not recognized.");
                break;
        }
    }

    private void startingThingsForTesting(Player hero) {//method for testing purposes

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

    public String parseUserInput(String input) { //formats for shortcuts, need to make an array later of acceptable commands....
        switch (input) {
            case "north":
                input = "n";
                break;
            case "south":
                input = "s";
                break;
            case "east":
                input = "e";
                break;
            case "west":
                input = "w";
                break;
            case "exit":
                input = "exits";
                break;
            case "battle":
                input = "b";
                break;
            case "inventory":
                input = "i";
                break;
            case "search":
                input = "look";
                break;
            case "stats":
                input = "status";
                break;
        }
        return input;
    }

    public void printCommands() { //prints all of the available commands
        printBreak();
        System.out.println("Commands: n, s, e, w, b, exits, look, inventory, skills, status, equipment, take, quit");
        printBreak();
    }

    public void takeCommand(String rest, Player hero) {//figures out what to take

        boolean pass = false;
        for (int i = 0; i < currentRoom.getRoomLoot().size(); i++)//for some reason using nested for each loops crashes here
        {
            for (String tag : currentRoom.getRoomLoot().get(i).getTags()) {
                if (tag.equals(rest)) {
                    Item temp = currentRoom.getRoomLoot().get(i);//sets the item to be taken to a temp variable

                    hero.addInventory(temp);
                    currentRoom.getRoomLoot().remove(temp);
                    System.out.println("Took " + temp.getName() + ".");
                    pass = true;
                }
            }
        }
        if (!pass) { //if there was no item let them know
            System.out.println("There's no item here called that.");
        }
    }

    public void equipCommand(String input, Player hero) {
        boolean pass = false;
        
        for (Item i : hero.getInventory()) {
            if ((i.getName().toLowerCase().equals(input))) {
                System.out.println(i.getClass().getSuperclass().toString());
                if (i.getClass().toString().equals("class textrpg.weapons.Weapon") || i.getClass().toString().equals("class textrpg.equipment.Equipment")) {
                    pass = true; //^^^ i dont like that line ^^^

                    Equipment temp = (Equipment) i;//look at docs for weapon types
                    hero.addInventory(temp);

                    switch (i.getItemType()) {
                        case 1:
                            hero.setWeapon((Weapon) i);
                            break;
                        case 2:
                            hero.setChest((Equipment) i);
                            break;
                        case 3:
                            hero.setLegs((Equipment) i);
                            break;
                        case 4:
                            hero.setBracers((Equipment) i);
                            break;
                        case 5:
                            hero.setBoots((Equipment) i);
                            break;
                        case 6:
                            hero.setGloves((Equipment) i);
                            break;
                        case 7:
                            hero.setOffHand((Weapon) i);
                            break;
                        case 8:
                            hero.setOffHand((Weapon) i);
                            break;
                        case 9:
                            hero.setRing((Equipment) i);
                            break;
                        case 10:
                            hero.setHat((Equipment) i);
                            break;
                        case 11:
                            hero.setGoggles((Equipment) i);
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
        if (!pass) {
            System.out.println("There's no item here called that.");
        }

    }
}
