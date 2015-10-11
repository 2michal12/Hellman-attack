package nks2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Michal 8822
 */
public class Data {
    
    public String[] wordsLoad(String file) throws IOException{
        ArrayList<String> wList = new ArrayList<>();
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        while(true){
            String adj = reader.readLine();
            if( adj == null) break;
            wList.add(adj);
        }
        reader.close();
        
        String[] wArray = wList.toArray(new String[0]);
        return wArray;
    }
    
    public String[] parsePWDlist(String file, String userName, int count) throws IOException{
        String[] pwdArray = new String[count];
        int pwdIndex = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        while(true){
            String pwdLine = reader.readLine();
            if( pwdLine == null) break;
            if( pwdLine.contains(userName) ){
                String[] tmpArr = pwdLine.split("'");
                pwdArray[pwdIndex] = tmpArr[3];
                pwdIndex++;
            }
        }
        
        return pwdArray;
    }
    
    public String[] createNumArray(int size){
        String[] arrNum = new String[size];
        
        for(Integer i = 0; i < arrNum.length; i++){
            if(i.toString().length() < 3){
                if(i.toString().length() < 2)
                    arrNum[i] = "00"+i.toString();
                else
                    arrNum[i] = "0"+i.toString();
            }else{
                arrNum[i] = i.toString();
            }
        }
        
        return arrNum;
    }
    
    public String[] randomPass(int count, String userName, String[] arrAdj, String[] arrNoun, String[] arrNum){
        String[] arrPass = new String[count];
        Random rand = new Random();
                
        for(int i=0; i<count; i++){
            arrPass[i] =  userName + arrAdj[rand.nextInt(arrAdj.length)] + arrNoun[rand.nextInt(arrNoun.length)] + arrNum[rand.nextInt(arrNum.length)];
        }
        
        return arrPass;
    }
 
}
