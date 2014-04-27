package textrpg.npcs;

import textrpg.Entity;

public class NPC extends Entity{
    
    private String defaultDialogue;
    
    public void printDefaultDialogue(){System.out.println(defaultDialogue);}
    public void setDefaultDialogueDialogue(String s){defaultDialogue = s;}
    
}
