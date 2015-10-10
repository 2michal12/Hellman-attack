package nks2;

import java.io.IOException;

/**
 *
 * @author Michal
 */
public class Main {
    private static String USER_NAME = "user8822";
    private static final int COUNT_PWD_FOR_USER = 99;
    private static final int COUNT_NUMBERS = 1000;
    
    private static String ADJ_TXT = "adj.txt";
    private static String NOUNT_TXT = "noun.txt";
    private static String PWD_TXT = "pwdlist.txt";

    public static void main(String[] args) throws IOException {
        
        Data load = new Data();
        String[] arrAdj = load.wordsLoad(ADJ_TXT);
        String[] arrNoun = load.wordsLoad(NOUNT_TXT);
        String[] pwdArray = load.parsePWDlist(PWD_TXT, USER_NAME, COUNT_PWD_FOR_USER);
        String[] numArray = load.createNumArray(COUNT_NUMBERS);
        
        System.out.println("ok");

    }
    
}
