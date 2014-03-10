package Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Dictionary {
	
	boolean testing = false;
	File file;
	ArrayList<String> words;
	ArrayList<String> nineLetterWords;
	
	/**
	 * Constructor for the Dictionary class
	 */
	public Dictionary(){
		
		load();
		getNineLetterWords();
	}
	
	/**
	 * Get the best word for the letters
	 * 
	 * @param letters
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getBestWords(String letters){
		
		ArrayList<String> bestWords = new ArrayList<String>();
		int noLetters;
		
		//generate all possible combinations
		//check if any of them are the best
		//if so add to an arraylist
		
		return bestWords;
	}
	
	/**
	 * Check if a word is real according to dictionary
	 * 
	 * @param word
	 * @return boolean
	 */
	public boolean checkWord(String word){
		
		if( (word.length() <= 9 || word.length() > 0) && words.contains(word.toLowerCase())){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Get an anagram
	 * 
	 * @return String[]
	 */
	public String[] getAnagram(){
		
		String[] output = new String[2];
		
		int size = nineLetterWords.size();
		
		Random r = new Random();
		String word = nineLetterWords.get(r.nextInt(size));
		
		output[0] = word;
		
		ArrayList<Character> tempWord = new ArrayList<Character>();
		
		for (int i = 0; i < 9; i++){
			
			tempWord.add(i, word.charAt(i));
		}
		
		Collections.shuffle(tempWord);
		
		StringBuilder sb = new StringBuilder(tempWord.size());
		
		for (char ch: tempWord){
			
			sb.append(ch);
		}
		
		String returnWord = sb.toString();
		
		output[1] = returnWord;
		
		return output;
	}
	
	/**
	 * Loads the dictionary file
	 */
	private void load(){

		
		if (testing){
			file = new File("smalldictionary.txt");
		}else{
			file = new File("dictionary.txt");
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(file));) {
			 
			String CurrentLine;
			words = new ArrayList<String>();
 
			
 
			while ((CurrentLine = br.readLine()) != null) {
				
				if (CurrentLine.length() <= 9){
					words.add(CurrentLine);
				}
			}
 
		} catch (IOException e){
			System.err.println("I broke :(");
			e.printStackTrace();
		}
		
		if (testing){
			
			System.out.println("Dictionary loaded. \n" + words.size() + 
					" words have been loaded");
		}
	}
	
	/**
	 * Fills an array with letters from the dictionary that are 9 letters long
	 */
	private void getNineLetterWords(){
		
		nineLetterWords = new ArrayList<String>();
		
		for(String word: words){
			
			if (word.length() == 9){
				
				nineLetterWords.add(word);
			}
		}
	}

}
