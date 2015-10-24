package nks2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Michal Majzel & Martin Krajcir
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
        
//      NAHODNE VYTVARANIE HESIEL BEZ DUPLICIT
        Integer[] indexAdj = new Integer[arrAdj.length];
        Integer[] indexNoun = new Integer[arrNoun.length];
        Integer[] indexNum = new Integer[arrNum.length];
       
        for (int i = 0; i < indexAdj.length; i++) {
            indexAdj[i] = i;
        }
        Collections.shuffle(Arrays.asList(indexAdj));
       
        for (int i = 0; i < indexNoun.length; i++) {
            indexNoun[i] = i;
        }
        Collections.shuffle(Arrays.asList(indexNoun));
       
        for (int i = 0; i < indexNum.length; i++) {
            indexNum[i] = i;
        }
        Collections.shuffle(Arrays.asList(indexNum));
        
        for(int i=0; i<count; i++){
            arrPass[i] =  userName + arrAdj[indexAdj[i%arrAdj.length]] + arrNoun[indexNoun[i%arrNoun.length]] + arrNum[indexNum[i%arrNum.length]];    
        }
		
        
//        POSTUPNE VYTVARANIE HESIEL
//        int index = 0;
//        for(int i = 0; i < arrAdj.length-1; i++){
//            for(int j = 0; j < arrNoun.length-1; j++){
//                for(int k = 0; k < arrNum.length; k++){
//                    if(index != count){
//                        //System.out.println(userName + arrAdj[i] + arrNoun[j] + arrNum[k]);
//                        arrPass[index++] =  userName + arrAdj[i] + arrNoun[j] + arrNum[k];
//                    }else{
//                        return arrPass;
//                    }
//                }
//            }
//        }
        
        return arrPass;
    }
 
}
