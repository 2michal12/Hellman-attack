package nks2;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author Michal
 */
public class Main {
    private static final String USER_NAME = "user8822"; //user na ktoreho hesla "utocime"
    private static final int COUNT_PWD_FOR_USER = 99; //pocet hash-ov pre jedneho user-a
    private static final int COUNT_NUMARRAY_NUMBERS = 1000; //pocet moznych kombinacii ciselneho pola pre generovanie hesiel
    
    private static final int COUNT_INITIAL_PASSWORDS = 10000; //pocet inicializacnich hesiel (potrebne zistit ako spravne ich vyberat)
    private static final int LENGTH_OF_CHAINS = 10; //dlzka retaze hashov (najst optimalny pocet pre dane hesla)
    
    private static final String ADJ_TXT = "adj.txt";
    private static final String NOUNT_TXT = "noun.txt";
    private static final String PWD_TXT = "pwdlist.txt";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        
        Data load = new Data();
        String[] arrAdj = load.wordsLoad(ADJ_TXT);
        String[] arrNoun = load.wordsLoad(NOUNT_TXT);  
        String[] arrNum = load.createNumArray(COUNT_NUMARRAY_NUMBERS);
        String[] pwdArray = load.parsePWDlist(PWD_TXT, USER_NAME, COUNT_PWD_FOR_USER);
        
        //nahodne vybrana mnozina hesiel, pre ktore budu generovane "chains"
        String[] initialPass = load.randomPass(COUNT_INITIAL_PASSWORDS ,USER_NAME, arrAdj, arrNoun, arrNum);
        
        Attack attack = new Attack(USER_NAME, arrAdj, arrNoun, arrNum);
        //vytvorenie rainbow tabulky
        String[][] rainbowTable = attack.createTable(initialPass, LENGTH_OF_CHAINS);   
        
        //hladanie hesiel 
        String[] passwords = attack.findPassword(pwdArray, rainbowTable, LENGTH_OF_CHAINS);
        for(int i=0; i < passwords.length-1; i++){
            if( passwords[i] != null )
                System.out.println(i+". "+(char)27 + "[32m"+ passwords[i] + (char)27 + "[0m");
        }
        System.out.println("\nFalse alarms: "+(char)27 + "[31m"+ passwords[passwords.length-1] + (char)27 + "[0m");

    }
    
}
