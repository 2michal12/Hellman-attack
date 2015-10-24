package nks2;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Michal Majzel & Martin Krajcir
 */
public class Attack {
        private final String userName;
        private final String[] adjArr;
        private final String[] nounArr;
        private final String[] numArr;
    
    public Attack(String userName, String[] adjArr, String[] nounArr, String[] numArr){
        this.userName = userName;
        this.adjArr = adjArr; 
        this.nounArr = nounArr;
        this.numArr = numArr;
    }
        
    //funkcia zo vstupneho hešu "pwdArray[0]" vráti redukciou vytvorené nové heslo
    public String reductionFunction(String hash){
        int third = hash.getBytes().length/3;
        int balance = hash.getBytes().length - 2*third;
        
        byte[] nounPart = new byte[third];
        byte[] adjPart = new byte[third];
        byte[] numPart = new byte[balance];
        
        System.arraycopy(hash.getBytes(), 0, nounPart, 0, third);
        System.arraycopy(hash.getBytes(), third, adjPart, 0, third);
        System.arraycopy(hash.getBytes(), 2*third, numPart, 0, balance);
       
        int adjIndex = new BigInteger(adjPart).intValue() % adjArr.length;
        int nounIndex = new BigInteger(nounPart).intValue() % nounArr.length;
        int numIndex = new BigInteger(numPart).intValue() % numArr.length;

        return userName + adjArr[adjIndex] + nounArr[nounIndex] + numArr[numIndex];
    }
    
    private String hashFunction(String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return DatatypeConverter.printBase64Binary((digest.digest(pwd.getBytes("UTF-8"))));
    }
    
    public String[][] createTable(String[] startingPoints, int chainLength) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String[][] table = new String[startingPoints.length][2];
        
        String tempHash;
        String tempPass;
        
        for(int i=0; i<startingPoints.length; i++){
            table[i][0] = startingPoints[i];
            tempPass = startingPoints[i];
            for(int j=0; j<chainLength; j++){
                tempHash = hashFunction(tempPass);
                tempPass = reductionFunction(tempHash);
                
                if(j == chainLength-1){
                    table[i][1] = tempPass;
                }
            }
        }
        
        return table;
    }
    
    public String[] findPassword(String[] hashPass, String[][] table, int chainLength) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String tempHash;
        String tempPass;
        String recreateChainReturn;
        int count = 0; //pocet pouziti redukcnej a hashovacej funkcie na vstupny hash
        Integer falseAlarms = 0;
        String brokenPass[] = new String[hashPass.length+1];
        
        for(int i=0; i<hashPass.length; i++){
            tempHash = hashPass[i];
                while(count < chainLength){
                    tempPass = reductionFunction(tempHash);      
                    tempHash = hashFunction(tempPass);
                    for(int j=0; j<table.length; j++){
                        if( tempPass.equals(table[j][1]) ){
                            recreateChainReturn = recreateChain(table[j][0], hashPass[i], chainLength);
                            if( recreateChainReturn == null ){
                                falseAlarms++;
                            }else{
                                brokenPass[i] = recreateChainReturn;
                           }
                        }
                    }
                    count++;
                }
            count = 0;
        }
        brokenPass[hashPass.length] = falseAlarms.toString(); //vrati pocet falosnych najdeni hesla
        return brokenPass;
    }
   
    private String recreateChain(String startingPoint, String hash, int chainLength) throws NoSuchAlgorithmException, UnsupportedEncodingException{       
        String tempHash;
        String tempPass = startingPoint;
        for(int i=0; i<chainLength; i++){
            tempHash = hashFunction(tempPass);
            if( tempHash.equals(hash) ){
                return tempPass;
            }
            tempPass = reductionFunction(tempHash);
        }
        return null;
    }
    
}
