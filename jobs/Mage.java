//package textrpg.jobs;
//
//import textrpg.Player;
//
//public class Mage implements Job
//{
//    private final String class_name = "Mage";
//    Player hero = null;
//    
//    public Mage(Player p)
//    {
//        hero = p;
//        hero.setHealth(80);
//        hero.setMana(80);
//        hero.setAgility(10);
//        hero.setDefense(10);
//        hero.setMagic(25);
//        hero.setStrength(6);
//        hero.setMagicDefense(15);
//    }
//    
//    public int attack()
//    {
//        return (int)(hero.getStrength() * .3) + 10;//magic number!!!!!!!
//    }
//    
//    public String getJob(){return class_name;}
//    
//    public String getSkills(){return "";};
//    
//}
