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
	private String letters;

	/**
	 * Constructor for the word round
	 * @param dict the Dictionary file
	 * @param pOne Player one
	 * @param pTwo Player two
	 * @param board the leaderboard
	 * @param timerActive if the timer is active
	 */
	public WordRound(Dictionary dict, Player pOne, Player pTwo, LeaderBoard board, boolean timerActive) {

		super(dict, pOne, pTwo, board, LeaderBoard.WORDROUND, timerActive);
		//io = new UserIO();
	}
	
	/**
	 * Constructor for the word round
	 * @param dict the Dictionary file
	 * @param pOne Player one
	 * @param pTwo Player two
	 */
	public WordRound(Dictionary dict, Player pOne, Player pTwo, LeaderBoard board) {

		this(dict, pOne, pTwo, board, true);
	}

	/**
	 * A constructor for the word round
	 * @param o A gameObjects object.
	 * @param timerActive if the timer is active
	 */
	public WordRound(GameObjects o, boolean timerActive) {

		this(o.dict, o.pOne, o.pTwo, o.leaders, timerActive);
	}
	
	/**
	 * A constructor for the word round
	 * @param o A gameObjects object.
	 */
	public WordRound(GameObjects o) {

		this(o.dict, o.pOne, o.pTwo, o.leaders, true);
	}

	/**
	 * Play the word round
	 */
	public void play() {

		printWelcome("word");

		letters = generateLetters();
		ArrayList<String> bestAnswers = dict.getBestWords("penguinsz");

		int pOneLen;
		int pTwoLen;

		System.out.println("The letters are: " + letters+ "\n");

		if(timerActive)
			CountdownTimer();

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

		pOneLen = checkAnswer(pOne);

		if(pTwo != null) {

			pTwoLen = checkAnswer(pTwo);

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
		int noVowels = -1;
		ArrayList<Character> letters = new ArrayList<>();
		Random r = new Random();
		//frequency taken from http://www.thecountdownpage.com/letters.htm
		char[] constantants = {'b','b','c','c','c','d','d','d','d','d','d','f','f','g','g','g',
					'h','h','j','k','l','l','l','l','l','m','m','m','m','n','n','n','n','n','n',
					'n','n','p','p','p','p','q','r','r','r','r','r','r','r','r','r','s','s','s',
					's','s','s','s','s','s','t','t','t','t','t','t','t','t','t','v','w','x','y',
					'z'}; 

		while( loop){
			System.out.println("How many vowels would you like?"); 

			if ((noVowels = io.getNumber()) < 3){
				System.out.println("Must have at least 3 vowels");
			}else if (noVowels > 5){

				System.out.println("Cant have more than 5 vowels");
			}else{
				loop = false;
			}
		}

		//get all vowels
		for (int i = 0; i < noVowels; i++){

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
		for (int i = 0; i < (9 - noVowels); i++){

			int temp = r.nextInt(74);

			letters.add(constantants[temp]);
		}//close

		//mix up the letters
		Collections.shuffle(letters);

		//put string back together for returning
		StringBuilder sb = new StringBuilder(letters.size());

		for (char ch: letters){

			sb.append(ch);
		}

		return sb.toString().toUpperCase();		
	}//close horrible method

	/**
	 * Checks the players answer
	 * 
	 * @param The Player who's answer is to be checked
	 * @return the length of the word or 0 for an incorrect word
	 */
	private int checkAnswer(Player p) {

		if(p.getNumber() == 1) {
			if(validWord(ans1))
				return ans1.length();
			else
				return 0;
		} else {
			if(validWord(ans2))
				return ans2.length();
			else
				return 0;
		}

	}
	
	/**
	 * Checks if the word is valid
	 * 
	 * @param the word to be checked
	 * @return true if valid word, false otherwise
	 */
	private boolean validWord(String a) {

		int count = 0;

		if(dict.checkWord(a)) {
			for(int i = 0; i < a.length(); i++) {
				
				if(letters.contains(a.subSequence(i, i + 1)))
					count++;

			}

		}

		if(count == a.length())
			return true;
		else
			return false;

	}

	/**
	 * Award the user points based on the length of their answer
	 * 
	 * @param The Player to be awarded points
	 * @param the number of points to be awarded
	 */
	private void awardPoints(Player p, int points) {

		System.out.println(p.getName() + " recieves " + points + " Points");

		if(points == 9)
			points = points * 2;

		if(p.getNumber() == 1) {
			if (timerActive)
				board.addScore(p.getName(), roundType, 10);
			pOne.updateScore(points);
		} else {
			if (timerActive)
				board.addScore(p.getName(), roundType, 10);
			pTwo.updateScore(points);
		}
	}

	/**
	 * Get the players answer
	 * 
	 * @param Player who's answer is to be collected
	 */
	private void getAnswer(Player p) {

		System.out.println(p.getName() + " enter your answer: ");

		if (p.getNumber() == 1) {
			ans1 = io.getString().toUpperCase();
			System.out.println("Your answer is: " + ans1);
		} else {
			ans2 = io.getString().toUpperCase();
			System.out.println("Your answer is: " + ans2);
		}

		System.out.println();

	}

}
