package org.CaesarTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaesarCipher {
    
    private String encrypt_decrypt(String encrypted, int key) {
    // Make string builder with encrypted
    StringBuilder sb = new StringBuilder(encrypted);
  
    // Write down alphabet
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String cAlphabet = alphabet.toUpperCase(); // uppercase
    
    // Computed shifted alphabet
    String shifted = alphabet.substring(key) + alphabet.substring(0,key);
 
    
    // Count from 0 to sb length - 1
    System.out.println("Shifted Alphabet: " + shifted);
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
    
    private String encrypt_decrypt(String encrypted, int key1, int key2) {
    // Make string builder with encrypted
    StringBuilder sb = new StringBuilder(encrypted);
  
    // Write down alphabet
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String cAlphabet = alphabet.toUpperCase(); // uppercase
    
    // Computed shifted alphabet
    String shiftedAtKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
    String shiftedAtKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
    
    System.out.println("Shifted Alphabet 1: " + shiftedAtKey1);
    System.out.println("Shifted Alphabet 2: " + shiftedAtKey2);
    
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
     
    String messageFromFile(String fileName) {
      File file = new File("C:\\Users\\320\\Documents\\Dexter\\File Handling Test\\" + fileName); // your file directory

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

    void testCaesar(){
      int key = 15;

      String message = messageFromFile("testCase.txt"); // passed parameter should be the file name on your computer

      System.out.println(message);

      String encrypted = encrypt_decrypt(message, 23, 17);
      System.out.println(encrypted);

//      String decrypt = encrypt_decrypt(encrypted, 26-key);
//      System.out.println(decrypt);
    }
    
    public static void main(String[] args) {
      new CaesarCipher().testCaesar();

    }
}
