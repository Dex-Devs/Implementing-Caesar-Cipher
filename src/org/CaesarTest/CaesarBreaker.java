package org.CaesarTest;

public class CaesarBreaker {
    
//    count letter occurence
    void countLetters(String encrypted, int [] counts){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        for(int i = 0 ; i < encrypted.length() ; i++){
//            index of letters from message
            int index = alphabet.indexOf(Character.toLowerCase(encrypted.charAt(i)));
            
            if(index != -1)
                counts[index] += 1;
        }
    }
    
    // get index of character with highest counts
    int maxIndex(int [] counts) {
        
        int maxIndex = 0;
        for(int k = 0 ; k < counts.length ; k++){
            if(counts[k] > counts[maxIndex])
                maxIndex = k;
        }
        return maxIndex;
    }
    
    String decrypt(String encrypted, int key){
        CaesarCipher cipher = new CaesarCipher();

        return cipher.encrypt_decrypt(encrypted, 26-key);
    }
    
//     // print decrypted message
//    void testDecrypt(String encrypted, int key){
//        
//        System.out.println("Decrypted message: " + decrypt(encrypted, key));
//    }
        
    // get every other character in a string
    String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        
        for(int i = start ; i < message.length() ; i+=2){
            sb.append(message.charAt(i));
        }
        
        return sb.toString();
    }
    
    // get key -- Letter 'e' assumably has the highest frequency
    int getKey(String s){
        int [] counts = new int[26];
        
        countLetters(s, counts);
        
        int maxIndex = maxIndex(counts);
        if(maxIndex < 4)
            return 26 - (4 - maxIndex);
        
        return maxIndex-4;
    }
    
    
    // decrypt with 2 keys
    String decryptTwoKeys(String encrypted) {
        // The encryption used the 2nd key to encrypt every other character of the message
        // Hence we should encrypt those messages separately
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        
        // get keys of two Strings
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);

        CaesarCipher cipher = new CaesarCipher();
        
        System.out.println("Decryption keys [" + (key1) + ", " + (key2) + "]");
        return cipher.encrypt_decrypt2Keys(encrypted, 26-key1, 26-key2);
    }
    
    public static void main(String [] args){
        
        //        // TEST DECRYPT
        
//        String message = "Then calculate the key used to encrypt each half String.";
//        int key = 15;
//        
//        System.out.println("Message: " + message);
//        String encryptedMessage = new CaesarCipher().encrypt_decrypt(message, key);
//        System.out.println("Encrypted message: " + encryptedMessage );
//        new CaesarBreaker().testDecrypt(encryptedMessage, key);

        //        // TEST HALFOFSTRING
        
//        String message = "Qbkm Zgis";
//        int start = 0;
//        
//        System.out.println(new CaesarBreaker().halfOfString(message, start));

//            // TEST DECRYPT WITH 2 KEYS
//        
//        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
//        int key1 = 4, key2 = 15;
//        
//        CaesarBreaker breaker = new CaesarBreaker();
//        CaesarCipher cipher = new CaesarCipher();
//        
//        // enrypting
//        String encryptedMessage = cipher.encrypt_decrypt2Keys(message, key1, key2);
//        System.out.println("Encryption keys [" + key1 + ", " + key2 + "]");
//        System.out.println("Encrypted message: " + encryptedMessage);
//        
//        // decrypting
//        String decryptedMessage = breaker.decryptTwoKeys(message);
//        System.out.println("Decrypted message: " + decryptedMessage);
    
        // TEST DECRYPTION FROM TEXT FILE
        CaesarCipher cipher = new CaesarCipher();
        CaesarBreaker breaker = new CaesarBreaker();
        
        String encrypted = cipher.messageFromFile("mysteryTwoKeys.txt");
        System.out.println("Encrypted message: " + encrypted);
        
        String decryptedMessage = breaker.decryptTwoKeys(encrypted);
        System.out.println("Decrypted message: " + decryptedMessage);
        
        
    }
        
        
}
