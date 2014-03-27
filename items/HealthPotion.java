package textrpg.items;

public class HealthPotion extends Item
{
    public HealthPotion()
    {
        super.setItemType(1);//is consumable
        super.setGoldWorth(5);
        super.setName("Health Potion");
    }
    
    @Override
    public int[] use()//should make a separate method for use and use text
    {
        int[] i = {50, 0, 0, 0, 0, 0, 0};//should make a variable in Item class
        System.out.println("Gained " + i[0] + " Health!");
        return i;
    }
}
