package textrpg.items;

public class SlimeExtract extends Item
{
    public SlimeExtract()
    {
        super.setItemType(2);
        super.setGoldWorth(3);
        super.setName("Slime Extract");
        super.setItemDescription("The goo from a slime.");
        
        super.setConsumable(false);
    }
}
