package textrpg;

import java.sql.*;
import com.google.gson.*;
import java.util.Scanner;
import textrpg.items.*;

public class DataHandler {

    private static Connection c = null;
    private static Statement stmt = null;

    public static void openDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:./src/textrpg/data/items.db");
            //c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
        }
        catch (Exception e) {
            printExceptionError(e);
        }
    }
    
    private static void printExceptionError(Exception e) {
        System.out.println(e.getClass().getName() + ": " + e.getMessage());
    }

    public static void doTheStuff() {

        try {

            Scanner scan = new Scanner(System.in);
            int input = -1;
            while (input != 0) {
                System.out.println("Create Items Database: 1\nInsert New Item: 2\nPrint Database: 3\nDelete Database: 6\nExit: 0");
                input = scan.nextInt();
                scan.nextLine();

                switch (input) {
                    case 1:
                        createDatabase(stmt);
                        break;
                    case 2:
                        insertNewItem(stmt);
                        break;
                    case 3:
                        printDatabase(stmt);
                        break;
                    case 6:
                        deleteDatabase(stmt);
                        break;
                    default:
                        break;
                }
            }

            printDatabase(stmt);

            stmt.close();
            c.close();
        }
        catch (Exception e) {
            printExceptionError(e);
            System.exit(0);
        }
    }

    private static void createDatabase(Statement stmt) {
        try {
            String sql = "CREATE TABLE ITEMS (ID INT PRIMARY KEY NOT NULL, OBJECT TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("Created table ITEMS");
        }
        catch (Exception e) {
            printExceptionError(e);
        }
    }

    private static void insertNewObject(Statement stmt) {
        try {
            Gson gson = new Gson();

            Item hpPot = new HealthPotion();
            String hpPotJ = gson.toJson(hpPot);

            String sql = "INSERT INTO ITEMS (ID, OBJECT) VALUES (1, '" + hpPotJ + "')";
            stmt.executeUpdate(sql);

            Item slime = new SlimeExtract();
            String slimeJ = gson.toJson(slime);

            sql = "INSERT INTO ITEMS (ID, OBJECT) VALUES (2, '" + slimeJ + "')";
            stmt.executeUpdate(sql);
            System.out.println("Inserted HP pot and Slime Extract");
        }
        catch (Exception e) {
            printExceptionError(e);
        }

        //ResultSet rs = stmt.executeQuery("SELECT OBJECT FROM ITEMS where ID=1");
        //Item newHpPot = gson.fromJson(rs.getString("object"), Item.class);
    }
    
    private static void insertNewItem(Statement stmt) {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Are you sure? y/n");
        String input = scan.nextLine();
        
        if(input.equalsIgnoreCase("y")) {
            Gson gson = new Gson();
            String[] newItemValues = new String[8];
          
            System.out.println("ID: ");
            newItemValues[0] = scan.nextLine();
            System.out.println("Name: ");
            newItemValues[1] = scan.nextLine();
            System.out.println("Item description: ");
            newItemValues[2] = scan.nextLine();
            System.out.println("Item type (1 is consumable, 2 is material, 3 is quest item): ");
            newItemValues[3] = scan.nextLine();
            System.out.println("Gold worth: ");
            newItemValues[4] = scan.nextLine();
            System.out.println("Stats (HP, Mana, STR, MAG, AGI, DEF, MDEF)(separate with commas): ");
            newItemValues[5] = scan.nextLine();
            System.out.println("Tags (separate with commas): ");
            newItemValues[6] = scan.nextLine();
            System.out.println("Consumable (1 or 0): ");
            newItemValues[7] = scan.nextLine();
            
            Item newItem = new Item();
            newItem.setName(newItemValues[1]);
            newItem.setItemDescription(newItemValues[2]);
            newItem.setItemType(Integer.valueOf(newItemValues[3]));
            newItem.setGoldWorth(Integer.valueOf(newItemValues[4]));
            //newItem.setStatsModified(newItemValues[5]);
            //newItem.setTags(newItemValues[6]);
            if (newItemValues[7].equals("1")) {
                newItem.setConsumable(true);
            }
            else {
                newItem.setConsumable(false);
            }

            String newItemJ = gson.toJson(newItem);

            try {
                String sql = "INSERT INTO ITEMS (ID, OBJECT) VALUES (" + newItemValues[0] + ", '" + newItemJ + "')";
                stmt.executeUpdate(sql);
            }
            catch (Exception e) {
                printExceptionError(e);
            }
            System.out.println("Inserted " + newItem.getName() + " into database.");

        }
    }

    private static void deleteDatabase(Statement stmt) {
        System.out.println("Are you sure? y/n");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        
        if (input.equalsIgnoreCase("y")) {
            try {
                String sql = "DROP TABLE ITEMS";
                stmt.executeUpdate(sql);
                System.out.println("Deleted table ITEMS");
            }
            catch (Exception e) {
                printExceptionError(e);
            }
        }
    }

    private static void printDatabase(Statement stmt) {
        try {
            System.out.println();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ITEMS");
            while(rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("object"));
            }
            System.out.println();
        }
        catch (Exception e) {
            printExceptionError(e);
        }
    }
    
    public static Item getItem(int i) {
        Gson gson = new Gson();
        ResultSet rs;
        Item newItem = null;
        try {
            rs = stmt.executeQuery("SELECT OBJECT FROM ITEMS where ID=" + i);
            System.out.println("trying....");
            newItem = gson.fromJson(rs.getString("object"), Item.class);
            System.out.println("item: " + newItem);
        }
        catch (Exception e) {
            printExceptionError(e);
        }
        
        return newItem;
    }
}
