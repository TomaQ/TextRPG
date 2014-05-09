package textrpg.npcs;

import textrpg.Entity;

public class NPC extends Entity {

    private String defaultDialogue;

    public void printDefaultDialogue() {
        System.out.println(super.getName() + ": " + defaultDialogue);
    }
    public void setDefaultDialogueDialogue(String s){defaultDialogue = s;}
    
}
