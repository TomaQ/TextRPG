//This class handles all of the battle functions needed for a standard battle
//The players health and mana reset after every battle
package textrpg;

import java.util.InputMismatchException;
import java.util.Random;
import textrpg.monsters.Monster;
import java.util.Scanner;
import textrpg.items.Item;
import textrpg.skills.Skill;

public final class Battle {

    Scanner scan = new Scanner(System.in);
    Player hero;//not sure if health will recover after battle
    Monster monster;
    int turns;
    Random rand = new Random();
    boolean ranAway = false; //flag to see if the player ran away

    boolean pass = false;//checks to 

    public Battle(Player h, Monster m) {
        hero = h;
        monster = m;
        turns = 1;

        Game.printBreak();
        System.out.println("You have engaged in battle with " + m.getName() + "!");

        if (moveFirst()) {
            playerTurn();
        }
        else {
            monsterTurn();
            turns = 2;
        }

        while (hero.getCurrentHealth() > 0 && monster.getCurrentHealth() > 0) {
            System.out.println("-----");

            if (turns % 2 == 1) {monsterTurn();}
            else {playerTurn();}

            turns++;
        }

        //if the player doesn't win then game over!!!!!!
        if (ranAway == false){//if they didnt run away then they get this stuff
        
            h.setGold(hero.getGold() + m.getGoldWorth());
            String lootName = "";
            for (Item i : m.getLoot()) {
                h.addInventory(i);
                lootName += i.getName() + ", ";
            }
            if(lootName.length() > 2){
                lootName = lootName.substring(0, lootName.length() - 2);//deletes the last comma
            }
            else{
                lootName = "None";
            }
            System.out.println(m.getName() + " defeated! Gold:" + m.getGoldWorth() + " Exp:" + m.getExpWorth());
            System.out.println("Loot: " + lootName);
            h.setCurrentExp(h.getCurrentExp() + m.getExpWorth());
        }

        Game.printBreak();
    }

    public boolean moveFirst() {//calculates who gets the first turn based on agility

        return hero.getCurrentAgility() > monster.getCurrentAgility();
    }

    public void playerTurn() {//need to organize this like totally better

        System.out.println(hero.getName() + " - " + "HP: " + hero.getCurrentHealth() + "/" + hero.getMaxHealth() + " MP: " + hero.getCurrentMana() + "/" + hero.getMaxMana());
        System.out.println(monster.getName() + " - " + "HP: " + monster.getCurrentHealth());
        decidePlayerAction();
    }

    public void decidePlayerAction() {
        pass = false;//set to true if the player makes a valid move

        boolean loop = true;

        do {
            while (loop) {
                try {
                    System.out.println("Attack = 1, Skills = 2, Items = 3, Run = 4");
                    System.out.print(">");
                    int input = scan.nextInt();
                    scan.nextLine();

                    int dmg = 0;

                    if (input == 1) {
                        playerAttack(dmg);
                        loop = false;
                    }
                    else if (input == 2) {
                        if(playerSkill(dmg))
                            loop = false;
                    }
                    else if (input == 3) {
                        if (playerItem()) {
                            loop = false;
                        }
                    }
                    else if (input == 4) {
                        runAway();
                        loop = false;
                    }
                    else {
                        TextRPG.invalidInput();
                    }
                } catch (InputMismatchException e) {
                    TextRPG.invalidInput();
                    scan.next();
                }
            }

        } while (!pass);
    }

    public void playerAttack(int dmg) {//player uses attack

        for (Skill skill : hero.getJob().getSkills()) {
            if (skill.getSkillName().equals("Attack")) {
                dmg = skill.use();
            }
        }

        monster.setCurrentHealth(monster.getCurrentHealth() - dmg);
        System.out.println("Did " + dmg + " damage! Remaining HP of monster:" + monster.getCurrentHealth());
        pass = true;//PASSED!
    }

    public boolean playerSkill(int dmg) {//player uses a skill

        String skillz = "";
        for (int i = 0; i < hero.getJob().getSkills().length; i++) {
            if (!hero.getJob().getSkills()[i].getSkillName().equals("Attack"))//Need to remove getSkills from adding Attack
            {
                skillz += hero.getJob().getSkills()[i].getSkillName() + "(" + i + ")" + ", ";
            }
        }

        skillz = skillz.substring(0, skillz.length() - 2);
        System.out.println(skillz);

        System.out.println("Which skill will you use?");

        int skillChosen = -1;
        boolean loop = true;
        while (loop) {
            try {
                skillChosen = scan.nextInt();
                scan.nextLine();//consumes empty line

                if (skillChosen < hero.getJob().getSkills().length && skillChosen >= 0 && !hero.getJob().getSkills()[skillChosen].getSkillName().equals("Attack")) {//^^ lol ^^
                    loop = false;
                }
                else {
                    TextRPG.invalidInput();
                }
            } catch (InputMismatchException e) {
                TextRPG.invalidInput();
                scan.next();
            }
        }
        if (hero.getCurrentMana() >= hero.getJob().getSkills()[skillChosen].getManaCost()) {
            dmg = hero.getJob().getSkills()[skillChosen].use();

            if (monster.getWeakness() == hero.getJob().getSkills()[skillChosen].getType()) {
                dmg = (int) (dmg * 1.5);//number uncertain about I am
            }
            monster.setCurrentHealth(monster.getCurrentHealth() - dmg);
            hero.setCurrentMana(hero.getCurrentMana() - hero.getJob().getSkills()[skillChosen].getManaCost());//uses up mana

            System.out.println("Did " + dmg + " damage! Remaining HP of monster:" + monster.getCurrentHealth());
            pass = true;//PASSED!
            return true;
        }
        else {
            System.out.println("Not enough mana!");
            return false;
        }
    }

    public boolean playerItem() {
        String inven = "";
        for (int i = 0; i < hero.getInventory().size(); i++) {
            inven += hero.getInventory().get(i).getName() + "(" + i + ")" + ", ";
        }
        if (inven.length() > 0) {
            inven = inven.substring(0, inven.length() - 2);
        }

        System.out.println(inven);

        System.out.println("Which item will you use?");

        boolean loop = true;
        int itemChosen = -1;

        while (loop) {
            try {
                itemChosen = scan.nextInt();
                scan.nextLine();
                if (itemChosen < hero.getInventory().size() && itemChosen >= 0) {
                    loop = false;
                }
                else {
                    TextRPG.invalidInput();
                }
            } catch (InputMismatchException e) {
                TextRPG.invalidInput();
                scan.next();
            }
        }
        Item chosenItem = hero.getInventory().get(itemChosen);
        if (chosenItem.getItemType() == 1) {//if the item type is consumable

            //probably a better way to do this
            hero.useItem(hero.getInventory().get(itemChosen).use());
            if (chosenItem.isConsumable()) {
                hero.getInventory().remove(chosenItem);
            }

            pass = true;//PASSED!
            return true;
        }
        else {
            hero.getInventory().get(itemChosen).itemError();
            return false;
        }
    }

    public void runAway() //player runs away
    {
        if ((rand.nextInt(10) + 1) < 7) //generates a number from 1-10 and checks if < 8
        {                              //should have it check agility and stuff
            monster.setCurrentHealth(0);
            ranAway = true;
            System.out.println("Ran away safely!");
        }
        else {
            System.out.println("Failed to escape!");
        }

        pass = true;
    }

    public void monsterTurn() {
        System.out.println(monster.getName() + "'s turn!");
        //monster.getSkills();
        System.out.println("The " + monster.getName() + " looks displeased.");
    }
}
