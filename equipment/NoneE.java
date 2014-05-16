package textrpg.equipment;

public class NoneE extends Equipment {

    public NoneE() {
        int[] stats = {0, 0, 0, 0, 0, 0, 0};
        super.setGoldWorth(0);
        super.setEquipmentType(2);
        super.setEquipmentStats(stats); //Need this since it cannot be null
        super.setName("None");
    }
}
