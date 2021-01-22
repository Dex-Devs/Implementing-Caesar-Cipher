package org.CaesarTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaesarCipher {
    
    private String encrypt_decrypt(String encrypted, int key) {
    // Make string builder with encrypted
    StringBuilder sb = new StringBuilder(encrypted.toLowerCase());
  
    // Write down alphabet
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    // Computed shifted alphabet
    String shifted = alphabet.substring(key) + alphabet.substring(0,key);
    
    // Count from 0 to sb length - 1
    System.out.println("Shifted Alphabet: " + shifted);
    for(int k=0 ; k < sb.length() ; k++){
      // find index of current char in the origianl alphabet
      int origIndex = alphabet.indexOf(sb.charAt(k));

      // current char should be in the alphabet, hence it should not be have an index of -1
      if(origIndex != -1) {
        // replace k-th character of string in sb with the shifted's character on index 'origIndex'
        sb.setCharAt(k, shifted.charAt(origIndex));
      }
    }

    return sb.toString();

  }
     
    String messageFromFile(String fileName) {
      File file = new File("C:\\Users\\320\\Documents\\Dexter\\File Handling Test\\" + fileName);

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

      File file = new File("C:\\Users\\320\\Documents\\Dexter\\File Handling Test\\toEncrypt.txt");

      String message = messageFromFile("file1.txt");

      System.out.println(message);

      String encrypted = encrypt_decrypt(message, key);
      System.out.println(encrypted);

      String decrypt = encrypt_decrypt(encrypted, 26-key);
      System.out.println(decrypt);
    }
    
    public static void main(String[] args) {
      new CaesarCipher().testCaesar();

    }
}
