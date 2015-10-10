package nks2;

import java.math.BigInteger;

/**
 *
 * @author Michal
 */
public class Attack {
    
    public String reductionFunction(String hash, String[] adjArr, String[] nounArr, String[] numArr){
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

        return adjArr[adjIndex] + nounArr[nounIndex] + numArr[numIndex];
    }
    
}
