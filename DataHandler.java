package textrpg;

import java.sql.*;
import com.google.gson.*;
import textrpg.items.*;

public class DataHandler {

    public static void doTheStuff() {
        Connection c = null;
        Statement stmt = null;
        
        Gson gson = new Gson();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:./src/textrpg/data/items.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE ITEMS (ID INT PRIMARY KEY NOT NULL, OBJECT TEXT NOT NULL)";
            stmt.executeUpdate(sql);
           
            Item hpPot = new HealthPotion();
            String hpPotJ = gson.toJson(hpPot);

            sql = "INSERT INTO ITEMS (ID, OBJECT) VALUES (1, '" + hpPotJ + "')";
            stmt.executeUpdate(sql);

            ResultSet rs = stmt.executeQuery("SELECT * FROM ITEMS where ID=1");
            Item newHpPot = gson.fromJson(rs.getString("object"), Item.class);
            System.out.println("CHECK CHEK CHECK OK OK OK THIS ITEM ISSSSS: " + newHpPot.getName());

            stmt.close();
            c.close();
        }
        catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
