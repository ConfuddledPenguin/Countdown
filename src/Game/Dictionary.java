package Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The dictionary for countdown
 * 
 * It handles wordy things
 */
public class Dictionary {
	
	private boolean testing = false;
	private File file;
	private ArrayList<String> words;
	private ArrayList<String> nineLetterWords;
	private ArrayList<ArrayList<Character>> nineLetterWordsChars = new ArrayList<ArrayList<Character>>();
	private ArrayList<String> eightLetterWords;
	private ArrayList<ArrayList<Character>> eightLetterWordsChars = new ArrayList<ArrayList<Character>>();
	
	/**
	 * Constructor for the Dictionary class
	 */
	public Dictionary(){
		
		load();
		getNineLetterWords();
		getEightLetterWords();
	}
	
	/**
	 * Get the best word for the letters
	 * 
	 * @param letters Random string to be solved
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getBestWords(String letters){
		
		//Get best nine letter words
		ArrayList<String> bestWords = getWordsNine(letters);
		
		//if there is a nine letter word then return it
		if (!bestWords.isEmpty()){
			return bestWords;
		}
		
		//get best eight letter words
		bestWords = getWordEight(letters);
		
		
		/*
		 * Return bestwords.
		 * This doesn't do best words under a length of 8
		 */
		return bestWords;
	}
	
	/**
	 * Gets all nine letter words that could be made from the anagram
	 * @param letters
	 * @return ArrayList<String> All matches
	 */
	public ArrayList<String> getWordsNine(String letters){
		
		ArrayList<Character> charList = new ArrayList<Character>();
		ArrayList<String> possibleMatches = new ArrayList<String>();

		for (char ch : letters.toCharArray()) {
		  charList.add(ch);
		}
		
		Collections.sort(charList);
		
		for(ArrayList<Character> word: nineLetterWordsChars){
			
			Collections.sort(word);
			
			if (word.equals(charList)){

				int index = nineLetterWordsChars.indexOf(word);
				possibleMatches.add(nineLetterWords.get(index));
			}
		}
		
		return possibleMatches;
	}
	
	/**
	 * Gets all the best eight letter words
	 * 
	 * @param letters the letters
	 * @return the matches
	 */
	public ArrayList<String> getWordEight(String letters){

		ArrayList<String> bestWords = new ArrayList<String>();
		
		for (int i = 0; i < 9; i++){
			
			
			ArrayList<Character> charList = new ArrayList<Character>();
			charList.clear();
			for (int j = 0; j < 9 ; j++){
				
				if ( i != j){
					charList.add(letters.charAt(j));
				}
				
			}
		
			Collections.sort(charList);
			
			for(ArrayList<Character> word: eightLetterWordsChars){
				
				Collections.sort(word);
				
				if (word.equals(charList)){

					int index = eightLetterWordsChars.indexOf(word);
					bestWords.add(eightLetterWords.get(index));
				}
			}
			
		}
		
		return bestWords;
	}
	
	/**
	 * Check if a word is real according to dictionary
	 * 
	 * @param word
	 * @return boolean
	 */
	public boolean checkWord(String word){
		
		if (word == null) return false;
		
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
	public String getAnagram(){
		
		//get a random word
		int size = nineLetterWords.size();
		
		Random r = new Random();
		String word = nineLetterWords.get(r.nextInt(size));
		
		//break the word into chars to shuffle
		ArrayList<Character> tempWord = new ArrayList<Character>();
		
		for (int i = 0; i < 9; i++){
			
			tempWord.add(i, word.charAt(i));
		}
		
		Collections.shuffle(tempWord);
		
		//put string back together for returning
		StringBuilder sb = new StringBuilder(tempWord.size());
		
		for (char ch: tempWord){
			
			sb.append(ch);
		}
		
		String returnWord = sb.toString();
		
		return returnWord;
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
 
		} catch (FileNotFoundException fnfe){
			System.err.println("Error: dictionary not found");
		}catch (IOException e){
			System.err.println("Error: Reading file failed. File must be currupt");
		} catch (Exception e){
			System.out.println("I broke :( Sorry about that!");
			System.out.println("Technical junk: " + e.getClass().getName());
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
				
				//Add to list of nine letter words
				nineLetterWords.add(word);
				
				//break into chars and add to list
				ArrayList<Character> charList = new ArrayList<Character>();

				for (char ch : word.toCharArray()) {
				  charList.add(ch);
				}
				Collections.sort(charList);
				nineLetterWordsChars.add(charList);
			}
		}
	}
	
	/**
	 * Fills an array with letters from the dictionary that are 8 letters long
	 */
	private void getEightLetterWords(){
		
		eightLetterWords = new ArrayList<String>();
		
		for(String word: words){
			
			if (word.length() == 8){
				
				//Add to list of nine letter words
				eightLetterWords.add(word);
				
				//break into chars and add to list
				ArrayList<Character> charList = new ArrayList<Character>();

				for (char ch : word.toCharArray()) {
				  charList.add(ch);
				}
				Collections.sort(charList);
				eightLetterWordsChars.add(charList);
			}
		}
	}

}