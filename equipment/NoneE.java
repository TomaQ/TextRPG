package textrpg.equipment;

public class NoneE extends Equipment
{
    public NoneE()
    {
        int[] stats = {0, 0, 0,0, 0, 0, 0};//testing not official
        super.setGoldWorth(0);
        super.setEquipmentType(1);
        super.setEquipmentStats(stats);
        super.setName("None");
    }
}