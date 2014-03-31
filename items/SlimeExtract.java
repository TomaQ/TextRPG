package textrpg.items;

public class SlimeExtract extends Item
{
    public SlimeExtract()
    {
        super.setItemType(2);
        super.setGoldWorth(3);
        super.setName("Slime Extract");
        super.setItemDescription("The goo from a slime.");
        
        int[] i = {-1};
        super.setStatsModified(i);
    }
    
    @Override
    public int[] use()
    {
        super.itemError();
        return super.getStatsModified();
    }
}
