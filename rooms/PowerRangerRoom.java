package textrpg.rooms;

import textrpg.npcs.DrSunshine;
import textrpg.npcs.NPC;

public class PowerRangerRoom extends Room {

    public PowerRangerRoom(Room n, Room s, Room e, Room w) {
        super.setExits(n, s, e, w);
        super.setRoomName("Power Ranger Room");
        super.setRoomDescription("Mighty morphing power rangers! There's an old creep in the room.");
        
        NPC theDoc = new DrSunshine();
        NPC[] np = {theDoc};
        super.setNPCsInRoom(np);
    }
}
