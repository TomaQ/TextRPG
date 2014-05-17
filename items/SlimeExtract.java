package textrpg.items;

public class SlimeExtract extends Item {

    public SlimeExtract() {
        super.setItemType(2);//is a material
        super.setGoldWorth(3);
        super.setName("Slime Extract");
        super.setItemDescription("The goo from a slime.");

        String[] t = {"slime"};
        super.setTags(t);
        
        super.setConsumable(false);
    }
}
