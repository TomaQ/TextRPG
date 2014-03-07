package textrpg;

public final class Battle 
{
    public Battle(Player hero, Monster m)
    {
        System.out.println("You have engaged in battle with " + m.getName() + "!");
        
        if(moveFirst(hero, m)){System.out.println("Your turn!");}
        else{System.out.println(m.getName() + "'s turn!");}
    }
    
    public boolean moveFirst(Player hero, Monster enemy)
    {
        if(hero.getAgility() > enemy.getAgility())
            return true;
        else
            return false;
    }
    
    public int slash()//skills go here?
    {
        return 1-0;
    }
}
