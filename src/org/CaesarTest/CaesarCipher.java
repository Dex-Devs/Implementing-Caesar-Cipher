package org.CaesarTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaesarCipher {
    
    // encryption using single key
    private String encrypt_decrypt(String meessage, int key) {
    // Make string builder with encrypted
    StringBuilder sb = new StringBuilder(meessage);
  
    // Write down alphabet
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String cAlphabet = alphabet.toUpperCase(); // uppercase
    
    // Computed shifted alphabet
    String shifted = alphabet.substring(key) + alphabet.substring(0,key);
 
    
    // Count from 0 to sb length - 1
    for(int k=0 ; k < sb.length() ; k++){
      // find index of current char in the origianl alphabet
      int idx;
      
      if(Character.isUpperCase(sb.charAt(k))){
        idx = cAlphabet.indexOf(sb.charAt(k));
          
          // current char should be in the alphabet, hence it should not be have an index of -1
        if(idx != -1) {
          // replace k-th character of string in sb with the shifted's character on index 'origIndex'
          sb.setCharAt(k, Character.toUpperCase(shifted.charAt(idx)));
        }
      }else if(Character.isLowerCase(sb.charAt(k))){
        idx = alphabet.indexOf(sb.charAt(k));
          
          // current char should be in the alphabet, hence it should not be have an index of -1
        if(idx != -1) {
          // replace k-th character of string in sb with the shifted's character on index 'origIndex'
          sb.setCharAt(k, shifted.charAt(idx));
        }
      }
    }

    return sb.toString();

  } 
    
    // encryption using 2 keys
    // key1 will encrypt every character while key2 will encrypt every other character
    
    private String encrypt_decrypt(String meessage, int key1, int key2) {
    // Make string builder with encrypted
    StringBuilder sb = new StringBuilder(meessage);
  
    // Write down alphabet
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String cAlphabet = alphabet.toUpperCase(); // uppercase
    
    // Computed shifted alphabet
    String shiftedAtKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
    String shiftedAtKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
    
    // Count from 0 to sb length - 1
    for(int k=0 ; k < sb.length() ; k++){
      // find index of current char in the origianl alphabet
      int idx;
      
        if(Character.isUpperCase(sb.charAt(k))){
          idx = cAlphabet.indexOf(sb.charAt(k));

            // current char should be in the alphabet, hence it should not be have an index of -1
          if(idx != -1) {
            // replace k-th character of string in sb with the shifted's character on index 'origIndex'
            
            if(k % 2 == 0) // if char position is odd
                sb.setCharAt(k, Character.toUpperCase(shiftedAtKey1.charAt(idx)));
            else
                sb.setCharAt(k, Character.toUpperCase(shiftedAtKey2.charAt(idx)));
          }
        }else if(Character.isLowerCase(sb.charAt(k))){
          idx = alphabet.indexOf(sb.charAt(k));

            // current char should be in the alphabet, hence it should not be have an index of -1
          if(idx != -1) {
            // replace k-th character of string in sb with the shifted's character on index 'origIndex'
            
            if(k % 2 == 0) // if char position is even
                sb.setCharAt(k, shiftedAtKey1.charAt(idx));
            else
                sb.setCharAt(k, shiftedAtKey2.charAt(idx));
          }
        }
    }

    return sb.toString();

  } 
    
    // read file from computer
    String messageFromFile(String fileName) {
      File file = new File("C:\\Users\\320\\Documents\\Dexter\\File Handling Test\\" + fileName); // your file directory concatenated with the file name

      StringBuilder message = new StringBuilder();

      if(file.exists()){

          try(Scanner scan = new Scanner(file)){
              while(scan.hasNext()){
                  message.append(scan.next()).append(" ");
              }
          } catch (FileNotFoundException ex) {
              Logger.getLogger(CaesarCipher.class.getName()).log(Level.SEVERE, null, ex);
          }

      }else {
          System.out.println("FILE NOT FOUND!");
      }

      return message.toString().trim();
    }

    // encrypt
    void testCaesar(){
      int singleKey = 15, key1 = 23, key2 = 17;

      String message = messageFromFile("testCase.txt"); // passed parameter should be the file name in your computer

      System.out.println("Message from file: " + message);
      System.out.println();

//      String encrypted = encrypt_decrypt(message, singleKey);
//      System.out.println("Encrypted message: " + encrypted);
      
      String encrypted = encrypt_decrypt(message, key1, key2);
      System.out.println("Encrypted message (keys["+ key1 +","+ key2 +"]): " + encrypted);

//      String decrypt = encrypt_decrypt(encrypted, 26-singleKey);
//      System.out.println(decrypt);
      
      String decrypt = encrypt_decrypt(encrypted, 26-key1, 26-key2);
      System.out.println("Decrypted message (keys["+ (26-key1) +","+ (26-key2) +"]): " + decrypt);
    }
    
    public static void main(String[] args) {
      new CaesarCipher().testCaesar();

    }
}
