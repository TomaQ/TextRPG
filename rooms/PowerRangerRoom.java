package textrpg.rooms;

public class PowerRangerRoom extends Room
{
    public PowerRangerRoom(Room n, Room s, Room e, Room w)
    {
        super.setExits(n, s, e, w);
        super.setRoomName("Power Ranger Room");
        super.setRoomDescription("Mighty morphing power rangers!");
        
        super.enterRoomText();
    }
}
