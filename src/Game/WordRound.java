package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The word round of the game countdown
 */
public class WordRound extends Round{

	//stores the answer entered by the player
	private String ans1;
	private String ans2;

	private UserIO i;

	/**
	 * Constructor for the number round
	 * @param dict the Dictionary file
	 * @param pOne Player one
	 * @param pTwo Player two
	 */
	public WordRound(Dictionary dict, Player pOne, Player pTwo, LeaderBoard board) {

		super(dict, pOne, pTwo, board, LeaderBoard.WORDROUND);
		i = new UserIO();
	}

	/**
	 * A constructor for the number round
	 * @param o A gameObjects object.
	 */
	public WordRound(GameObjects o) {

		this(o.dict, o.pOne, o.pTwo, o.leaders);
	}

	/**
	 * Call to play a round
	 */
	public void play() {

		printWelcome();

		String letters = generateLetters();
		ArrayList<String> bestAnswers = dict.getBestWords(letters);
		
		System.out.println("The answers are: ");
		for(String word: bestAnswers) {
			System.out.println(word);			
		}
		System.out.println();
		
		int pOneLen;
		int pTwoLen;

		//currently printing the answer for testing purposes
		System.out.println("The letters are: " + letters+ "\n");

		getAnswer(pOne);

		if(pTwo != null) {

			getAnswer(pTwo);

			if (ans1.equals(ans2))
				System.out.println("You both have the same answer, let's see if they are correct");
			else if(ans1.length() == ans2.length())
				System.out.println("Both answers are the same length, let's see if they are correct");
			else if (ans1.length() > ans2.length())
				System.out.println(pOne.getName() + " has the longer word, let's see if it is correct");
			else
				System.out.println(pTwo.getName() + " has the longer word, let's see if it is correct");
		}
		
		pOneLen = checkAnswer(pOne, bestAnswers);
		
		if(pTwo != null) {
			
			pTwoLen = checkAnswer(pTwo, bestAnswers);
			
			if(pOneLen > pTwoLen) {
				System.out.println("Player 1 Wins");
				awardPoints(pOne, pOneLen);
			} else if(pOneLen < pTwoLen) {
				System.out.println("Player 2 Wins");
				awardPoints(pTwo, pTwoLen);
			} else if(pOneLen + pTwoLen == 0) {
				System.out.println("Both players entered an incorrect answer or no answer at all. No Points awarded");
			} else {
				System.out.println("It's a draw");
				awardPoints(pOne, pOneLen);
				awardPoints(pTwo, pTwoLen);
			}
			
		} else {
			
			if(pOneLen > 0)
				awardPoints(pOne, pOneLen);
			else
				System.out.println("You entered an incorrect answer or no answer at all");
			
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

	private int checkAnswer(Player p, ArrayList<String> ans) {

		if(p.getNumber() == 1) {
			if(ans.contains(ans1))
				return ans1.length();
			else
				return 0;
		} else {
			if(ans.contains(ans2))
				return ans2.length();
			else
				return 0;
		}

	}

	private void awardPoints(Player p, int points) {
		
		System.out.println(p.getName() + " recieves " + points + " Points");
		
		if(points == 9)
			points = points * 2;

		if(p.getNumber() == 1) {
			board.addScore(p.getName(), roundType, points);
			pOne.updateScore(points);
		} else {
			board.addScore(p.getName(), roundType, points);
			pTwo.updateScore(points);
		}
	}

	private void getAnswer(Player p) {

		System.out.println(p.getName() + " enter your answer: ");

		if (p.getNumber() == 1) {
			ans1 = i.getString();
			System.out.println("Your answer is: " + ans1);
		} else {
			ans2 = i.getString();
			System.out.println("Your answer is: " + ans2);
		}

		System.out.println();

	}

}
