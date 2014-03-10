package textrpg.rooms;

public class StartingRoom extends Room
{
    public StartingRoom()
    {
        super.setRoomName("Starting Room");
        super.setRoomDescription("You are in a room, it feels like a starting room. Lol." /*+ super.getExits()*/);
    }
}
