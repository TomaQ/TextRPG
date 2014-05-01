package textrpg.shops;

import java.util.Scanner;
import textrpg.items.Item;

public class Shop {

    Item[] inventory; //Shop should always have items
    Scanner scan = new Scanner(System.in);

    public Shop() {

        String input = "n"; //should it be a string?
        while (!input.equals("3")) {
            System.out.println("Buy - 1, Sell - 2, Leave - 3");
            input = scan.nextLine();

            switch (input) {
                case "1":
                    buy();
                    break;
                default:
                    break;
            }
        }

    }

    private Item buy() {
        System.out.println("What will you buy?");
        
        String inven = "";
        for (int i = 0; i < inventory.length; i++) {
            inven += inventory[i].getName() + "(" + i + "), ";
        }

        inven = inven.substring(0, (inven.length() - 2));
        System.out.println(inven);
        
        

        return null;
    }

}
