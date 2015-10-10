package nks2;

import java.io.IOException;


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

    public static void main(String[] args) throws IOException {
        
        Data load = new Data();
        String[] arrAdj = load.wordsLoad(ADJ_TXT);
        String[] arrNoun = load.wordsLoad(NOUNT_TXT);  
        String[] arrNum = load.createNumArray(COUNT_NUMBERS);
        String[] pwdArray = load.parsePWDlist(PWD_TXT, USER_NAME, COUNT_PWD_FOR_USER);
        
        Attack attack = new Attack();
        
        //funkcia zo vstupneho hešu "pwdArray[0]" vráti redukciou vytvorené nové heslo bez prvej casti "userXXXX" ktore treba napevno pridat
        String newPass = USER_NAME + attack.reductionFunction(pwdArray[0], arrAdj, arrNoun, arrNum);
        
        System.out.println(newPass);
        

    }
    
}
