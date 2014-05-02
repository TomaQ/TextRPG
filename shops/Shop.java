package textrpg.shops;

import java.util.InputMismatchException;
import java.util.Scanner;
import textrpg.Player;
import textrpg.TextRPG;
import textrpg.items.Item;

public class Shop {

    Item[] inventory; //Shop should always have items
    Scanner scan = new Scanner(System.in);

    public Shop(Player hero) {

        String input = "n"; //should it be a string?
        while (!input.equals("3")) {
            System.out.println("Buy - 1, Sell - 2, Leave - 3");
            input = scan.nextLine();

            switch (input) {
                case "1":
                    buy(hero);
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
            chosen = scan.nextInt();
            scan.nextLine();
        }
        catch (InputMismatchException e) {
            TextRPG.invalidInput();
            scan.next();
        }

        if (chosen != -1) {
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
}
