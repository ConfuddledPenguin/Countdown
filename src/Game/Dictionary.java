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
	private Tree wordtree = new Tree();
	
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
	 * @param letters Random string to be solved
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getBestWords(String letters){
		
		ArrayList<String> bestWords;
		
		bestWords = wordtree.findbestWord(letters);
		
		return bestWords;
	}
	
	/**
	 * Gets all nine letter words that could be made from the anagram
	 * @param letters
	 * @return ArrayList<String> All matches
	 * @deprecated use {@link Dictionary#getBestWords(String letters)} instead
	 */
	@Deprecated
	public ArrayList<String> getWords(String letters){
		
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
					wordtree.addWord(CurrentLine);
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
				
				nineLetterWordsChars.add(charList);
			}
		}
	}

}
