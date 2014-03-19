package textrpg.rooms;

public class StartingRoom extends Room
{
    public StartingRoom(Room n, Room s, Room e, Room w)
    {
        super.setExits(n, s, e, w);
        super.setRoomName("Starting Room");
        super.setRoomDescription("You are in a room, it feels like a starting room. Lol.");
        
        super.enterRoomText();
    }
}
