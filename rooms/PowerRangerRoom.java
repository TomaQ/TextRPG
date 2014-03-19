package textrpg.rooms;

public class PowerRangerRoom extends Room
{
    public PowerRangerRoom()
    {
        Room r = new StartingRoom();//need to work on since it creates a new startingRoom object
        super.setExits(null, r, null, null);
        super.setRoomName("Power Ranger Room");
        super.setRoomDescription("Mighty morphing power rangers!");
        
        super.enterRoomText();
    }
}
