package textrpg.items;

public class HealthPotion extends Item {
    
    public HealthPotion() {
        super.setItemType(1);//is consumable
        super.setGoldWorth(5);
        super.setName("Health Potion");
        super.setItemDescription("Heals for 50 HP.");

        int[] i = {50, 0, 0, 0, 0, 0, 0, 0};
        super.setStatsModified(i);

        String[] t = {"health pot", "hp pot", "hp potion"};
        super.setTags(t);

        super.setConsumable(true);
    }

    @Override
    public int[] use() {//should make a separate method for use and use text
        System.out.println("Gained " + super.getStatsModified()[0] + " Health!");
        return super.getStatsModified();
    }
}
