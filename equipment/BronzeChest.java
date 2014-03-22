package textrpg.equipment;

public class BronzeChest extends Equipment
{
    public BronzeChest()
    {
        int[] stats = {5, 2, 0,0, 0, 0, 0};//testing not official
        super.setGoldWorth(50);
        super.setEquipmentType(1);
        super.setEquipmentStats(stats);
        super.setName("Bronze Chest");
    }
}