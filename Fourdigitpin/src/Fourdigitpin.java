/* Brute force 4-digit PIN password cracker using SHA-1
 * Written by: Zi Zhang
 * Purpose: For computer security course assignment.
 */

import java.security.MessageDigest;

public class Fourdigitpin 
{
    public static void main(String[] args) throws Exception
    {
    	long startTime, endTime, totalTime;
    	MessageDigest md = MessageDigest.getInstance("SHA-1");
    	String password = "";
    	String hash = "";
    	boolean hashFound = false;
    	
    	startTime = System.currentTimeMillis( );
    	
    	for(int i = 0; i < 10000; i++) {
			// SHA-1 hash a password in this format 'AAXXX'
			// where X is a single digit number
    		password = "AA" + String.format("%04d", i);
    		//System.out.println(password);
    		md.update(password.getBytes());
    		 
            byte byteData[] = md.digest();
     
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < byteData.length; j++) {
            	sb.append(Integer.toString((byteData[j] & 0xff) + 0x100, 16).substring(1));
            }
            
            hash = sb.toString().toUpperCase();
            //System.out.println(hash);
            //System.out.println(hash.length());
			
			// Tries all 4 digit combinations until hash matches the one below
            if(hash.equals("7D9F7DAA92CF05E5D4814CF216B31DD8E00E683F")) {
            	hashFound = true;
            	break;
            }
    	}
    	
        endTime = System.currentTimeMillis( );
        
        totalTime = endTime - startTime;
        if(hashFound) {
        	System.out.println("Hash found!");
        	System.out.println("digit: " + password + " Hash: " + hash);
            System.out.println(totalTime + "ms");
        } else {
        	//System.out.println(hash);
        	System.out.println("No matches found");
        }
    }
}