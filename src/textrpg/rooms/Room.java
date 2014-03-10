package textrpg.rooms;

public class Room 
{
    private String roomName;
    private String roomDescription;
    Room nExit = null;
    Room sExit = null;
    Room eExit = null;
    Room wExit = null;
    
    public String getRoomName(){return roomName;}
    public void setRoomName(String n){roomName = n;}
    
    public String getRoomDescription(){return roomDescription;}
    public void setRoomDescription(String n){roomDescription = n;}
    
    public Room getExits()
    {
        int i;
        for(i = 0; i < 3; i++)//max number of exits would be 4
        {
            
        }
        return nExit;//just a placeholder so it compiles
    }
}
