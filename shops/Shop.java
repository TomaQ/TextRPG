package textrpg.shops;

import java.util.InputMismatchException;
import java.util.Scanner;
import textrpg.Player;
import textrpg.TextRPG;
import textrpg.items.Item;

public class Shop {

    Item[] inventory; //Shop should always have items
    Scanner scan = new Scanner(System.in);

    public Item[] getInventory(){return inventory;}

    public void setInventory(Item[] i){inventory = i;}

    public void enter(Player hero) {
        String input = "n"; //should it be a string?
        while (!input.equals("3")) {
            System.out.println("Buy - 1, Sell - 2, Leave - 3");
            System.out.print(">");
            input = scan.nextLine();

            switch (input) {
                case "1":
                    buy(hero);
                    break;
                case "2":
                    sell(hero);
                    break;
                case "3":
                    System.out.println("Farewell traveller.");
                    break;
                default:
                    TextRPG.invalidInput();
                    break;
            }
        }
    }

    private void buy(Player hero) {
        System.out.println("What will you buy?");

        //Prints the inventory of the shop
        String inven = "";
        for (int i = 0; i < inventory.length; i++) {
            inven += "[" + i + "]" + inventory[i].getName() + ", ";
        }

        System.out.println(inven + "[" + inventory.length + "]exit");

        //Gets the users input
        int chosen = -1;
        boolean loop = true;
        while (loop) {
            try {
                System.out.print(">");
                chosen = scan.nextInt();
                scan.nextLine();
                loop = false;
            } catch (InputMismatchException e) {
                TextRPG.invalidInput();
                scan.next();
            }
        }

        //If the input is legal then buy the item, maybe make unsellable items
        if (chosen > -1 && chosen < inventory.length) {
            if (inventory[chosen].getGoldWorth() <= hero.getGold()) {
                hero.getInventory().add(inventory[chosen]);
                hero.setGold(hero.getGold() - inventory[chosen].getGoldWorth());
                System.out.println("Bought " + inventory[chosen].getName() + ".");
            }
            else {
                System.out.println("Not enough gold!");
            }
        }
        else if (chosen == inventory.length) {
        }//Exits the buying transaction
        else {
            TextRPG.invalidInput();
        }
    }

    private void sell(Player hero) {
        System.out.println("What do you want to sell?");

        String[][] inven = hero.getCountedInventory(); //Gets the individual strings for each item in the hero's inventory

        //Prints the items the hero has to sell with in the format [index to chose]"Item name"(quantity)
        String formattedInven = "";
        int j = 0; //The index for the exit and unique item length
        for (int i = 0; i < inven.length; i++) {
            if (inven[i][0] != null) {
                if (Integer.valueOf(inven[i][1]) > 1) { //If they're multiple items of the same name
                    formattedInven += "[" + i + "]" + inven[i][0] + "(" + inven[i][1] + ")" + ", ";
                    j++;
                }
                else {
                    formattedInven += "[" + i + "]" + inven[i][0] + ", ";
                    j++;
                }
            }
        }

        if (formattedInven.length() > 2) {
            System.out.println(formattedInven + "[" + j + "]exit");
        }
        else {
            System.out.println("Inventory empty. [0]exit");
        }

        int chosen = -1; //need to make this a method somewhere
        boolean loop = true;
        while (loop) {
            try {
                System.out.print(">");
                chosen = scan.nextInt();
                scan.nextLine();
                loop = false;
            } catch (InputMismatchException e) {
                TextRPG.invalidInput();
                scan.next();
            }
        }

        //Sells the item
        if (!hero.getInventory().isEmpty() && chosen < j && chosen > -1) {
            hero.setGold(hero.getGold() + hero.getInventory().get(chosen).getGoldWorth());
            System.out.println("Sold " + hero.getInventory().get(chosen).getName() + ".");
            hero.getInventory().remove(chosen);
        }
        else if (chosen == j) {
        } //Exit's the sell transaction
        else {
            TextRPG.invalidInput();
        }
    }
}
