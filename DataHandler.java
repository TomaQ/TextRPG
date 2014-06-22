package textrpg;

import java.sql.*;
import com.google.gson.*;
import java.util.Scanner;
import textrpg.items.*;

public class DataHandler {

    public static void doTheStuff() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:./src/textrpg/data/items.db");
            //c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            
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
                        insertNewObject(stmt);
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
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
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
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
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
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }

        //ResultSet rs = stmt.executeQuery("SELECT OBJECT FROM ITEMS where ID=1");
        //Item newHpPot = gson.fromJson(rs.getString("object"), Item.class);
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
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
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
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
