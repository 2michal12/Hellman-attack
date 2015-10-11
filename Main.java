package nks2;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author Michal
 */
public class Main {
    private static final String USER_NAME = "user8822";
    private static final int COUNT_PWD_FOR_USER = 99;
    private static final int COUNT_NUMBERS = 1000;
    
    private static final String ADJ_TXT = "adj.txt";
    private static final String NOUNT_TXT = "noun.txt";
    private static final String PWD_TXT = "pwdlist.txt";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        
        Data load = new Data();
        String[] arrAdj = load.wordsLoad(ADJ_TXT);
        String[] arrNoun = load.wordsLoad(NOUNT_TXT);  
        String[] arrNum = load.createNumArray(COUNT_NUMBERS);
        String[] pwdArray = load.parsePWDlist(PWD_TXT, USER_NAME, COUNT_PWD_FOR_USER);
        
        //nahodne vybrana mnozina hesiel, pre ktore budu generovane "chains"
        final int countPass = 2;
        String[] initialPass = load.randomPass(countPass ,USER_NAME, arrAdj, arrNoun, arrNum);
        
        
        Attack attack = new Attack(USER_NAME, arrAdj, arrNoun, arrNum);
        final int chainLength = 5;
        String[][] rainbowTable = attack.createTable(initialPass, chainLength);     

    }
    
}
