package textrpg.rooms;

import textrpg.items.*;

public class StartingRoom extends Room
{    
    public StartingRoom(Room n, Room s, Room e, Room w)
    {
        super.setExits(n, s, e, w);
        super.setRoomName("Starting Room");
        super.setRoomDescription("You are in a room, it feels like a starting room. Lol. You notice a potion sitting on a table next to you.");
        
        HealthPotion pot = new HealthPotion();
        super.getRoomLoot().add(pot);
    }
}
