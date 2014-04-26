package textrpg.items;

public class SlimeExtract extends Item
{
    public SlimeExtract()
    {
        super.setItemType(2);
        super.setGoldWorth(3);
        super.setName("Slime Extract");
        super.setItemDescription("The goo from a slime.");
        
        int[] i = super.no_stats_modified;
        super.setStatsModified(i);
        
        super.setConsumable(false);
    }
    
    @Override
    public int[] use()
    {
        super.itemError();
        return super.getStatsModified();
    }
}
