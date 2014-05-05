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
                    break;
                default:
                    TextRPG.invalidInput();
                    break;
            }
        }
    }
    
    private void buy(Player hero) {
        System.out.println("What will you buy?");

        String inven = "";
        for (int i = 0; i < inventory.length; i++) {
            inven += inventory[i].getName() + "(" + i + "), ";
        }

        inven = inven.substring(0, (inven.length() - 2));
        System.out.println(inven + " [" + inventory.length + "]exit");

        int chosen = -1;
        boolean loop = true;
        while (loop) {
            try {
                System.out.print(">");
                chosen = scan.nextInt();
                scan.nextLine();
                loop = false;
            } catch (InputMismatchException e) {
                TextRPG.invalidInput(); //do i need this here?
                scan.next();
            }
        }

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
        else if(chosen == inventory.length){}
        else {
            TextRPG.invalidInput();
        }
    }

    private void sell(Player hero) {
        System.out.println("What do you want to sell?");

        String[][] inven = hero.getCountedInventory();

        String formattedInven = "";
        for (int i = 0; i < inven.length; i++) {
            if (inven[i][0] != null) {
                if (Integer.valueOf(inven[i][1]) > 1) {
                    formattedInven += "[" + i + "]" + inven[i][0] + "(" + inven[i][1] + ")" + ", ";
                }
                else {
                    formattedInven += "[" + i + "]" + inven[i][0] + ", ";
                }
            }
        }

        if (formattedInven.length() > 2) {
            formattedInven = formattedInven.substring(0, formattedInven.length() - 2);
            System.out.println(formattedInven + " [" + hero.getInventory().size() + "]exit");
        }
        else {
            System.out.println("Inventory empty. [0]exit");
        }

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
    
        if (!hero.getInventory().isEmpty() && chosen < hero.getInventory().size() && chosen > -1) {
            hero.setGold(hero.getGold() + hero.getInventory().get(chosen).getGoldWorth());
            System.out.println("Sold " + hero.getInventory().get(chosen).getName() + ".");
            hero.getInventory().remove(chosen);
        }
        else if(chosen == hero.getInventory().size()){}
        else {
            TextRPG.invalidInput();
        }
    }
}
