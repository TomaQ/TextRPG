package textrpg;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import textrpg.rooms.Room;

public class DataHandler 
{
    static final File dir = new File("src/textrpg/data");
    
    public static Room getRoom(int id) throws FileNotFoundException, IOException
    {
        File roomFile = new File(dir, "rooms.dat");
        String roomFileString = "";
        
        BufferedReader bfr = new BufferedReader(new FileReader(roomFile));
        while(bfr.readLine() != null){
            roomFileString += bfr.readLine();}
        
        Gson gson = new Gson();
        
        Room[] roomFileRooms = gson.fromJson(roomFileString, Room[].class);
        for(int i = 0; i < roomFileRooms.length; i++)
        {
            if(roomFileRooms[i].getId() == id)
                return roomFileRooms[i];
        }
        return null;
    }
}
