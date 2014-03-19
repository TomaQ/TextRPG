package textrpg.rooms;

public class StartingRoom extends Room
{
    public StartingRoom()
    {
        Room r = new PowerRangerRoom();
        super.setExits(r, null, null, null);
        super.setRoomName("Starting Room");
        super.setRoomDescription("You are in a room, it feels like a starting room. Lol.");
        
        super.enterRoomText();
    }
}
