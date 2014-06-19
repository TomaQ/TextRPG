package textrpg.jobs;

import java.util.logging.Level;
import java.util.logging.Logger;
import textrpg.Player;

public class Warrior extends Job {

    private final String CLASS_NAME = "Warrior";
    final int BASE_HEALTH = 100;
    final int BASE_MANA = 50;
    final int BASE_AGILITY = 15;
    final int BASE_MAGIC = 7;
    final int BASE_STRENGTH = 22;
    final int BASE_DEFENSE = 20;
    final int BASE_MAGIC_DEFENSE = 11;
    final double NEXT_LEVEL_EXP_INCREMENT = 1.2;
    final double BASE_HEALTH_INCREMENT = 1.2;
    final double BASE_MANA_INCREMENT = 1.1;
    final double BASE_AGILITY_INCREMENT = 1.2;
    final double BASE_MAGIC_INCREMENT = 1.15;
    final double BASE_STRENGTH_INCREMENT = 1.25;
    final double BASE_DEFENSE_INCREMENT = 1.3;
    final double BASE_MAGIC_DEFENSE_INCREMENT = 1.15;

    final int[] BASE_STATS = {BASE_HEALTH, BASE_MANA, BASE_AGILITY, BASE_MAGIC, BASE_STRENGTH, BASE_DEFENSE, BASE_MAGIC_DEFENSE};
    final double[] BASE_INCREMENTS = {NEXT_LEVEL_EXP_INCREMENT, BASE_HEALTH_INCREMENT, BASE_MANA_INCREMENT, BASE_AGILITY_INCREMENT, BASE_MAGIC_INCREMENT, BASE_STRENGTH_INCREMENT, BASE_DEFENSE_INCREMENT, BASE_MAGIC_DEFENSE_INCREMENT};

    final String[] initialSkills = {"HeroicStrike", "Attack"};

    public Warrior(Player p) {//need way to check for new available skills to be learnable
        super.initializeJobStats(BASE_STATS, CLASS_NAME, initialSkills, p); //Initializes the base stats and other variables for the job

        try {
            this.initSkills(p); //temp
        }
        catch (Exception ex) {
            Logger.getLogger(Warrior.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void levelUp(Player p) {
        super.incrementStats = BASE_INCREMENTS; //Sets the increments in the Job class to this class's and then calls the super method
        super.levelUp(p);
    }
}
