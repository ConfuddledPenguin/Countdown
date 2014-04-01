package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The word round of the game countdown
 */
public class WordRound extends Round{

	//stores the answer entered by the player
	private String player1Answer;
	private String player2Answer;
	//Used to track which player has 'buzzed'
	//true for player one and false for player 2
	boolean player = true;
	
	private UserIO i;

	public WordRound(Dictionary dict, Player pOne, Player pTwo) {
		
		super(dict, pOne, pTwo, "Word round");
		i = new UserIO();
	}

	public void play() {
		
		System.out.println("Word\n");

		String letters = generateLetters();
		ArrayList<String> bestAnswers = dict.getBestWords(letters);

		//currently printing the answer for testing purposes
		System.out.println("The letters are: " + letters+ "\n");

		while(!dict.checkWord(player1Answer) || !checkLetters(player1Answer) /*|| !player1Answer.equals("!")*/) {
			System.out.println("Please enter a valid word");
			collectAnswer(player); 
		}

		while(!dict.checkWord(player2Answer) || !checkLetters(player2Answer) /*|| !player2Answer.equals("!")*/) {
			System.out.println("Please enter a valid word");
			collectAnswer(!player);
		}
		
		System.out.println("p1 ans: " + player1Answer + " " + player1Answer.length());
		System.out.println("p2 ans: " + player2Answer + " " + player2Answer.length());
		
		if(player1Answer.equals("!") && !player2Answer.equals("!")) {
			awardPoints(!player, player2Answer);
		} else if(!player1Answer.equals("!") && player2Answer.equals("!")) {
			awardPoints(player, player1Answer);
		} else if(player1Answer.equals("!") && player2Answer.equals("!")) {
			System.out.println("Both entered no answer. No points awarded");			
		} else {
			if(player1Answer.length() > player2Answer.length()) {
				System.out.println("Player 1 Wins");
				awardPoints(player, player1Answer);
			} else if(player1Answer.length() < player2Answer.length()) {
				System.out.println("Player 2 Wins");
				awardPoints(!player, player2Answer);
			} else {
				System.out.println("Draw");
				awardPoints(player, player1Answer);
				awardPoints(!player, player2Answer);
			}
		}
		
		System.out.println("The best answers were: ");
		for(String word: bestAnswers) {
			System.out.println(word);			
		}
		
	}
	
	/**
	 * asks the user for the number of vowels required and then
	 * generates and returns a string
	 * 
	 * @return A string of generated letters
	 */
	private String generateLetters() {
		
		boolean loop = true;
		int noVowles = -1;
		ArrayList<Character> letters = new ArrayList<>();
		Random r = new Random();
		
		while( loop){
			System.out.println("How many vowels would you like?"); 
			
			if ((noVowles = i.getNumber()) < 3){
				System.out.println("Must have at least 3 vowels");
			}else if (noVowles > 5){
				
				System.out.println("Cant have more than 5 vowels");
			}else{
				loop = false;
			}
		}
		
		//get all vowels
		for (int i = 0; i < noVowles; i++){
			
			int temp = r.nextInt(67);
			
			if (temp < 15 ){
				letters.add('a');
			} else if(temp < 36){
				letters.add('e');
			} else if(temp < 49){
				letters.add('i');
			} else if(temp < 62){
				letters.add('o');
			} else {
				letters.add('u');
			}
		}
		
		//get all constantants
		for (int i = 0; i < (9 - noVowles); i++){
			
			int temp = r.nextInt(74);
			
			/*
			 * This is truly horrible
			 * its on my to do list
			 */
			if (temp < 2 ){
				letters.add('b');
			} else if(temp < 5){
				letters.add('c');
			} else if(temp < 11){
				letters.add('d');
			} else if(temp < 13){
				letters.add('f');
			} else if(temp < 16){
				letters.add('g');
			} else if(temp < 18){
				letters.add('h');
			} else if(temp < 19){
				letters.add('j');
			} else if(temp < 20){
				letters.add('k');
			} else if(temp < 25){
				letters.add('l');
			} else if(temp < 29){
				letters.add('m');
			} else if(temp < 37){
				letters.add('n');
			} else if(temp < 41){
				letters.add('p');
			} else if(temp < 42){
				letters.add('q');
			} else if(temp < 51){
				letters.add('r');
			} else if(temp < 60){
				letters.add('s');
			} else if(temp < 69){
				letters.add('t');
			} else if(temp < 70){
				letters.add('v');
			} else if(temp < 71){
				letters.add('w');
			} else if(temp < 72){
				letters.add('x');
			} else if(temp < 73){
				letters.add('y');
			} else{
				letters.add('z');
			}
		}//close horrible for
		
		//mix up the letters
		Collections.shuffle(letters);
		
		//put string back together for returning
		StringBuilder sb = new StringBuilder(letters.size());
				
		for (char ch: letters){
					
			sb.append(ch);
		}
				
		return sb.toString();		
	}//close horrible method
	
	private boolean checkLetters(String answer) {

		return true;

	}

	private void awardPoints(boolean player, String answer) {
		
		int points = answer.length();
		
		if(points == 9)
			points = points * 2;

		if (player){
			pOne.updateScore(points);
		} else {
			pTwo.updateScore(points);
		}
	}

	private void collectAnswer(Boolean whatPlayer) {

		if(whatPlayer) {
			System.out.println("Player 1 enter answer: ");
			player1Answer = i.getString();
			System.out.println("Your answer is: " + player1Answer + "\n");
		} else {
			System.out.println("Player 2 enter answer: ");
			player2Answer = i.getString();
			System.out.println("Your answer is: " + player2Answer + "\n");
		}

	}

}
