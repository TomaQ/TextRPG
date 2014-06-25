package textrpg;

import java.sql.*;
import com.google.gson.*;
import java.util.ArrayList;
import java.util.Scanner;
import textrpg.equipment.Equipment;
import textrpg.items.*;
import textrpg.monsters.Monster;
import textrpg.npcs.NPC;
import textrpg.rooms.Room;
import textrpg.shops.Shop;
import textrpg.skills.Skill;
import textrpg.weapons.Weapon;

public class DataHandler {

    private static Connection c = null;
    private static Statement stmt = null;
    private static Gson gson = new Gson();

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

            openDatabase();

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

    //For testing only atm
    private static void insertNewObject(Statement stmt) {
        try {

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
    }

    private static void insertNewItem(Statement stmt) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Are you sure? y/n");
        String input = scan.nextLine();

        if (input.equalsIgnoreCase("y")) {

            String[] newItemValues = new String[7];

            int[] statsModified = new int[7];//Fix all of this later

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

            System.out.println("Stats (HP, Mana, STR, MAG, AGI, DEF, MDEF): ");
            for (int i = 0; i < statsModified.length; i++) {
                statsModified[i] = scan.nextInt(); //no error checking, fix later
                scan.nextLine();
            }

            System.out.println("Tags (When done enter -1): ");
            String tagsInput = scan.nextLine();
            ArrayList<String> tags = new ArrayList<>();

            while (!tagsInput.equalsIgnoreCase("-1")) {
                tags.add(tagsInput);
                tagsInput = scan.nextLine();
            }

            System.out.println("Consumable (1 or 0): ");
            newItemValues[6] = scan.nextLine();

            Item newItem = new Item();
            newItem.setName(newItemValues[1]);
            newItem.setItemDescription(newItemValues[2]);
            newItem.setItemType(Integer.valueOf(newItemValues[3]));
            newItem.setGoldWorth(Integer.valueOf(newItemValues[4]));

            newItem.setStatsModified(statsModified);

            String[] tagsArray = new String[tags.size()]; //Need to test
            newItem.setTags(tags.toArray(tagsArray));

            if (newItemValues[6].equals("1")) {
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
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("object"));
            }
            System.out.println();
        }
        catch (Exception e) {
            printExceptionError(e);
        }
    }

    public static Item getItem(int i) {

        ResultSet rs;
        Item newItem = null;
        try {
            rs = stmt.executeQuery("SELECT OBJECT FROM ITEMS where ID=" + i);
            newItem = gson.fromJson(rs.getString("object"), Item.class);
        }
        catch (Exception e) {
            printExceptionError(e);
        }

        return newItem;
    }

    public static Equipment getEquipment(int i) {
        ResultSet rs;
        Equipment newEquipment = null;

        try {
            rs = stmt.executeQuery("SELECT OBJECT FROM EQUIPMENT where ID=" + i);
            newEquipment = gson.fromJson(rs.getString("object"), Equipment.class);
        }
        catch (Exception e) {
            printExceptionError(e);
        }

        return newEquipment;
    }

    public static Weapon getWeapon(int i) {
        ResultSet rs;
        Weapon newWeapon = null;

        try {
            rs = stmt.executeQuery("SELECT OBJECT FROM WEAPONS where ID=" + i);
            newWeapon = gson.fromJson(rs.getString("object"), Weapon.class);
        }
        catch (Exception e) {
            printExceptionError(e);
        }

        return newWeapon;
    }

    public static Room getRoom(int i) {
        ResultSet rs;
        Room newRoom = null;

        try {
            rs = stmt.executeQuery("SELECT OBJECT FROM ROOMS where ID=" + i);
            newRoom = gson.fromJson(rs.getString("object"), Room.class);
        }
        catch (Exception e) {
            printExceptionError(e);
        }

        return newRoom;
    }

    public static Skill getSkill(int i) {
        ResultSet rs;
        Skill newSkill = null;

        try {
            rs = stmt.executeQuery("SELECT OBJECT FROM SKILLS where ID=" + i);
            newSkill = gson.fromJson(rs.getString("object"), Skill.class);
        }
        catch (Exception e) {
            printExceptionError(e);
        }

        return newSkill;
    }

    public static Shop getShop(int i) {
        ResultSet rs;
        Shop newShop = null;

        try {
            rs = stmt.executeQuery("SELECT OBJECT FROM SHOPS where ID=" + i);
            newShop = gson.fromJson(rs.getString("object"), Shop.class);
        }
        catch (Exception e) {
            printExceptionError(e);
        }

        return newShop;
    }

    public static NPC getNPC(int i) {
        ResultSet rs;
        NPC newNPC = null;

        try {
            rs = stmt.executeQuery("SELECT OBJECT FROM NPCS where ID=" + i);
            newNPC = gson.fromJson(rs.getString("object"), NPC.class);
        }
        catch (Exception e) {
            printExceptionError(e);
        }

        return newNPC;
    }

    public static Monster getMonster(int i) {
        ResultSet rs;
        Monster newMonster = null;

        try {
            rs = stmt.executeQuery("SELECT OBJECT FROM MONSTERS where ID=" + i);
            newMonster = gson.fromJson(rs.getString("object"), Monster.class);
        }
        catch (Exception e) {
            printExceptionError(e);
        }

        return newMonster;
    }
}
