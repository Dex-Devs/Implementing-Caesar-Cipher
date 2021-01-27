package org.CaesarTest;

import edu.duke.FileResource;


public class WordLengths {
    
    // count word lengths
    void countWordLengths(FileResource resource, int [] counts){
        // count words of each length, store counts in the array
        for(String word : resource.words()){
            // should not include punctuations other than comma, hyphen, and apostrophe
            int finalLength = 0;
            for(int k = 0 ; k < word.length() ; k++){
                
                // if word has more than counts.length-1 letters
                
                // if words character is a letter or an apostrophe, include it to the length
                if(Character.isLetter(word.charAt(k)) || word.charAt(k) == '\'') {
                    finalLength += 1;
                }
            }
            
            // if a word has more characters than the specified array length
            if(finalLength > word.length())
                // increment last element of the array
                counts[word.length()-1] +=1;
                
            // ELSE increment element of the array with finalLength index
            counts[finalLength] += 1;
            

        }
       
    }
    
    // initialize an array and a file resource then pass as parameters to method countWordLengths
    void textCountWordLengths(){
        
        FileResource resource = new FileResource("C:\\Users\\320\\Documents\\NetBeansProjects\\Implementing-Caesar-Cipher\\src\\files\\errors.txt"); // your file location here
        int [] counts = new int[31];
        
        countWordLengths(resource, counts);
        
        System.out.println("Max Index is: " + indexOfMax(counts));
        for(int i = 0 ; i < counts.length ; i++){
            System.out.println("Words of length " + i + " =\t" + counts[i]);
        }
        
    }
    
    // return index of element with the largest count
    int indexOfMax(int [] values) {
        int maxIndex = 0;
        for(int j = 0 ; j < values.length ; j++){
            if(values[j] > values[maxIndex]){
                maxIndex = j;
            }
        }
        
        return maxIndex;
    }
    
    
    public static void main(String [] args){
        
        new WordLengths().textCountWordLengths();
    }
}
