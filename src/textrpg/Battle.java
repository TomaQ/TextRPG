package textrpg;

import java.util.Scanner;

public final class Battle 
{
    Scanner scan = new Scanner(System.in);
    
    public Battle(Player hero, Monster m)
    {
        Game.printBreak();
        System.out.println("You have engaged in battle with " + m.getName() + "!");
        
        if(moveFirst(hero, m)){playerTurn(hero, m);}
        else{System.out.println(m.getName() + "'s turn!");}
    }
    
    public boolean moveFirst(Player hero, Monster enemy)
    {
        if(hero.getAgility() > enemy.getAgility())
            return true;
        else
            return false;
    }
    
    public void playerTurn(Player hero, Monster m)
    {   
        System.out.println("Your turn!");
        System.out.println("Attack = 1, Skills = 2");
        int input = scan.nextInt();
        scan.nextLine();
        
        if(input == 1)
        {
            /*if(hero.getJob().equals("Warrior"))
            {
                m.setHealth(hero.getAttack());
                System.out.println("Attacked! Did " +  hero.getAttack() + " damage!");
            }*/
            
            
        }
    }
}
