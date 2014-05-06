package textrpg.rooms;

import textrpg.items.*;
import textrpg.shops.GeneralShop;
import textrpg.shops.Shop;

public class StartingRoom extends Room
{    
    public StartingRoom(Room n, Room s, Room e, Room w)
    {
        super.setExits(n, s, e, w);
        super.setRoomName("Starting Room");
        super.setRoomDescription("You are in a room, it feels like a starting room. There's a merchant sitting in the corner of the room.");
        
        HealthPotion pot = new HealthPotion();
        pot.setItemRoomDescription("There is a health potion sitting on a table next to you.");
        super.getRoomLoot().add(pot);
        
        Shop store = new GeneralShop();
        super.setShop(store);
    }
}
