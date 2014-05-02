package textrpg.shops;

import textrpg.Player;
import textrpg.items.*;

public class GeneralShop extends Shop {
    
    public GeneralShop(Player hero){
        
        Item s = new SlimeExtract();
        Item h = new HealthPotion();
        Item[] inven = {s, h};
        
        super.setInventory(inven);
        super.enter(hero);
    }

}
