package textrpg.rooms;

public class StartingRoom extends Room
{
    public StartingRoom()
    {
        super.setId(1);
        super.setExits(2, 0, 0, 0);
        super.setRoomName("Starting Room");
        super.setRoomDescription("You are in a room, it feels like a starting room. Lol.");
    }
}
