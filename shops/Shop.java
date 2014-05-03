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
        System.out.println(inven);

        int chosen = -1;
        try {
            System.out.print(">");
            chosen = scan.nextInt();
            scan.nextLine();
        }
        catch (InputMismatchException e) {
            TextRPG.invalidInput(); //do i need this here?
            scan.next();
        }

        if (chosen < inventory.length && chosen > -1) { //need to check if its not out of bounds
            if (inventory[chosen].getGoldWorth() <= hero.getGold()) {
                hero.getInventory().add(inventory[chosen]);
                hero.setGold(hero.getGold() - inventory[chosen].getGoldWorth());
                System.out.println("Bought " + inventory[chosen].getName() + ".");
            }
            else {
                System.out.println("Not enough gold!");
            }
        }
        else {
            TextRPG.invalidInput();
        }
    }

    private void sell(Player hero) {
        System.out.println("What do you want to sell?");
        String inven = "";

        for (int i = 0; i < hero.getInventory().size(); i++) {
            inven += hero.getInventory().get(i).getName() + "(" + i + "), ";
        }
        if (inven.length() > 2) {
            inven = inven.substring(0, (inven.length() - 2));
        }
        System.out.println(inven);

        int chosen = -1;
        try{
            System.out.print(">");
            chosen = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            TextRPG.invalidInput();
            scan.next();
        }
    
        if (chosen < inventory.length && chosen > -1) { //need to check if its not out of bounds
            hero.getInventory().remove(chosen);
            hero.setGold(hero.getGold() + inventory[chosen].getGoldWorth());
            System.out.println("Sold " + inventory[chosen].getName() + ".");
        }
        else {
            TextRPG.invalidInput();
        }
    }
}
