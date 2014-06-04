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
    Player hero;//not sure if i want health to recover after battle but for now it will
    Monster monster;
    int turns;
    Random rand = new Random();
    boolean ranAway = false; //flag to see if the player ran away

    boolean pass = false; //used for loops

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

    //Calculates who gets the first turn based on agility
    public boolean moveFirst() {
        return hero.getCurrentAgility() > monster.getCurrentAgility();
    }

    private void playerTurn() { //formatting?
        System.out.println(hero.getName() + " - " + "HP: " + hero.getCurrentHealth() + "/" + hero.getMaxHealth() + " MP: " + hero.getCurrentMana() + "/" + hero.getMaxMana());
        System.out.println(monster.getName() + " - " + "HP: " + monster.getCurrentHealth());
        decidePlayerAction();
    }

    //Figures out the players action
    private void decidePlayerAction() {
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
                        if (runAway()) {
                            loop = false;
                        }
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

    //Player uses attack
    private void playerAttack(int dmg) {
        for (Skill skill : hero.getJob().getSkills()) {
            if (skill.getSkillName().equals("Attack")) {
                dmg = skill.use();
            }
        }

        monster.setCurrentHealth(monster.getCurrentHealth() - dmg);
        System.out.println("Did " + dmg + " damage! Remaining HP of monster:" + monster.getCurrentHealth());
        pass = true;//PASSED!
    }

    //Player uses a skill
    private boolean playerSkill(int dmg) {
        String skillz = "";
        for (int i = 0; i < hero.getJob().getSkills().length; i++) {
            if (!hero.getJob().getSkills()[i].getSkillName().equals("Attack")) {//Need to remove getSkills from adding Attack
                skillz += "[" + i + "]" + hero.getJob().getSkills()[i].getSkillName() +  ", ";
            }
        }

        skillz = skillz.substring(0, skillz.length() - 2);
        System.out.println(skillz);

        System.out.println("Which skill will you use?");

        int skillChosen = -1;
        boolean loop = true;
        while (loop) {
            try {
                System.out.print(">");
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
        if (hero.getCurrentMana() >= hero.getJob().getSkills()[skillChosen].getManaCost()) { //If they have enough mana
            dmg = hero.getJob().getSkills()[skillChosen].use();

            if (monster.getWeakness() == hero.getJob().getSkills()[skillChosen].getType()) {
                dmg = (int) (dmg * 1.5);//number uncertain about I am
            }
            monster.setCurrentHealth(monster.getCurrentHealth() - dmg);
            hero.setCurrentMana(hero.getCurrentMana() - hero.getJob().getSkills()[skillChosen].getManaCost());//uses up mana

            System.out.println("Did " + dmg + " damage! Remaining HP of monster:" + monster.getCurrentHealth());
            pass = true;//PASSED!
            return true;//do I need both of these...
        }
        else {
            System.out.println("Not enough mana!");
            return false;
        }
    }

    //Player uses an item
    private boolean playerItem() {
        if (!hero.getInventory().isEmpty()) {
            String[][] inven = Game.getCountedInventory(hero.getInventory()); //Gets the individual strings for each item in the hero's inventory

            String formattedInven = "";
            for (int i = 0; i < inven.length; i++) {
                if (inven[i][0] != null) {
                    if (Integer.valueOf(inven[i][1]) > 1) { //If they're multiple items of the same name
                        formattedInven += "[" + i + "]" + inven[i][0] + "(" + inven[i][1] + "), ";
                    }
                    else {
                        formattedInven += "[" + i + "]" + inven[i][0] + ", ";
                    }
                }
            }
            if (formattedInven.length() > 2) { //method plis
                formattedInven = formattedInven.substring(0, formattedInven.length() - 2);
            }
            System.out.println(formattedInven);

            System.out.println("Which item will you use?");

            boolean loop = true;
            int itemChosen = -1;

            while (loop) {
                try {
                    System.out.print(">");
                    itemChosen = scan.nextInt();
                    scan.nextLine();
                    if (itemChosen < inven.length && itemChosen >= 0) {
                        loop = false;
                    }
                    else {
                        TextRPG.invalidInput();
                    }
                }
                catch (InputMismatchException e) {
                    TextRPG.invalidInput();
                    scan.next();
                }
            }

            Item chosenItem = Game.getItem(inven[itemChosen][0], hero.getInventory());
            if (chosenItem.getItemType() == 1) {//if the item type is consumable

                //probably a better way to do this
                hero.useItem(chosenItem.use());
                if (chosenItem.isConsumable()) {
                    hero.getInventory().remove(chosenItem);
                }

                pass = true;//PASSED!
                return true;//Why are both of these here...
            }
            else {
                chosenItem.itemError();
                return false;
            }
        }
        else {
            System.out.println("Inventory is empty.");
            return false;
        }
    }

    //Player runs away
    private boolean runAway() {
        if (monster.isEscapable()) {
            if ((rand.nextInt(10) + 1) < 7) {//generates a number from 1-10 and checks if < 8                             //should have it check agility and stuff
                monster.setCurrentHealth(0);
                ranAway = true;
                System.out.println("Ran away safely!");
            }
            else {
                System.out.println("Failed to escape!");
            }

            pass = true;
            return true;
        }
        else {
            System.out.println("You cannot run away!");
            return false;
        }
    }

    private void monsterTurn() {
        System.out.println(monster.getName() + "'s turn!");
        //monster.getSkills(); blah stuff
        System.out.println("The " + monster.getName() + " looks displeased.");
    }
}
