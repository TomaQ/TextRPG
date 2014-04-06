package textrpg.rooms;

/**
 * User: katchk
 * Date: 05.04.14
 * Time: 18:10
 */

/*
* In the future this one and another one scenarios will be
* described in .txt or in other file format
* and will be readed and created dynamically using
* the JSON or other tools.
* I think it will be cool, because other users will
* be able to create and exchange their scenarios.
* */
public class ScenarioA {
    Room startingRoom = new Room();
    Room village = new Room();
    Room wood = new Room();
    Room lake = new Room();

    public ScenarioA(){
        startingRoom.setExits(village, null, null, null);
        village.setExits(wood, startingRoom, lake, null);
        wood.setExits(null, village, null, null );
        lake.setExits(null, null, null, village);
        setRoomsName();
        setRoomsDescription();
    }

    private void setRoomsName(){
        startingRoom.setRoomName("Training ground");
        village.setRoomName("STARFIELD Village");
        wood.setRoomName("The SHADOW GROVE");
        lake.setRoomName("LAKE Of SOFIREAL");

    }

    private void setRoomsDescription(){
        startingRoom.setRoomDescription("You are at the training ground, near some village. Your adventure starts!");
        village.setRoomDescription("Gods forgotten place, near the SHADOW GROVE");
        wood.setRoomDescription("Impregnated by rot and evilness air disturbs your mind...\nAnd it seems that you aren't alone here!");
        lake.setRoomDescription("A warm breeze encircles your face ... and you fall in love with place");
    }

    public Room getStartingRoom(){
        return startingRoom;
    }
}
