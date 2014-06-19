package textrpg.jobs;

import java.util.logging.Level;
import java.util.logging.Logger;
import textrpg.Player;

public class Thief extends Job {

    private final String CLASS_NAME = "Thief";
    final int BASE_HEALTH = 85;
    final int BASE_MANA = 40;
    final int BASE_AGILITY = 25;
    final int BASE_MAGIC = 9;
    final int BASE_STRENGTH = 13;
    final int BASE_DEFENSE = 10;
    final int BASE_MAGIC_DEFENSE = 8;
    final double NEXT_LEVEL_EXP_INCREMENT = 1.2;
    final double BASE_HEALTH_INCREMENT = 1.2;
    final double BASE_MANA_INCREMENT = 1.1;
    final double BASE_AGILITY_INCREMENT = 1.2;
    final double BASE_MAGIC_INCREMENT = 1.15;
    final double BASE_STRENGTH_INCREMENT = 1.15;
    final double BASE_DEFENSE_INCREMENT = 1.2;
    final double BASE_MAGIC_DEFENSE_INCREMENT = 1.15;

    final int[] BASE_STATS = {BASE_HEALTH, BASE_MANA, BASE_AGILITY, BASE_MAGIC, BASE_STRENGTH, BASE_DEFENSE, BASE_MAGIC_DEFENSE};
    final double[] BASE_INCREMENTS = {NEXT_LEVEL_EXP_INCREMENT, BASE_HEALTH_INCREMENT, BASE_MANA_INCREMENT, BASE_AGILITY_INCREMENT, BASE_MAGIC_INCREMENT, BASE_STRENGTH_INCREMENT, BASE_DEFENSE_INCREMENT, BASE_MAGIC_DEFENSE_INCREMENT};

    final String[] initialSkills = {"Attack", "Backstab"};

    public Thief(Player p) {
        super.initializeJobStats(BASE_STATS, CLASS_NAME, initialSkills, p); //Initializes the base stats and other variables for the job

        try {
            this.initSkills(p); //temp
        }
        catch (Exception ex) {
            Logger.getLogger(Thief.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void levelUp(Player p) {
        super.incrementStats = BASE_INCREMENTS; //Sets the increments in the Job class to this class's and then calls the super method
        super.levelUp(p);
    }
}
