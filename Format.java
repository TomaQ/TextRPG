package textrpg;

public class Format {

    /**
     * Removes the last two characters from a String
     * 
     * @return String
     */
    public static String removeEndingComma(String input) {

        if (input.length() > 2) {//formatting
            input = input.substring(0, input.length() - 2); //Removes the last 2 characters from a string
        }
        return input;
    }
    
    public static void printBreak() {
        //String for breaking up likes, might get rid of
        String lineBreak = "==========================";  
        System.out.println(lineBreak);         
    }
}
