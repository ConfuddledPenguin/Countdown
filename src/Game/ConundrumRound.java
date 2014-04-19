package Game;

import java.util.ArrayList;
import java.util.Timer;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;

/**
 * The conundrum round for the countdown game
 */
public class ConundrumRound extends Round {
	
	//stores the answer entered by the player
	private String ans1;
	private String ans2;
	
	private UserIO i;
	
	/**
	 * A constructor for the conundrum round
	 * 
	 * @param dict the dictionary file
	 * @param pOne Player one
	 * @param pTwo Player two
	 */
	public ConundrumRound(Dictionary dict, Player pOne, Player pTwo, LeaderBoard board) {
		
		super(dict, pOne, pTwo, board, LeaderBoard.CONUNDRUMROUND);
		i = new UserIO();
	}
	
	/**
	 * A constructor for the number round
	 * @param o A gameObjects object.
	 */
	public ConundrumRound(GameObjects o) {
		
		this(o.dict, o.pOne, o.pTwo, o.leaders);
	}
	
	public void play() {

		printWelcome();
		
		String anagram = dict.getAnagram();
		ArrayList<String> answer = dict.getBestWords(anagram);
		
		//currently printing the answer for testing purposes
		System.out.println("The conundrum is: " + anagram + ", Answer: " + answer + "\n");
		       
		getAnswer(pOne);
		
		if(pTwo != null) {
			
			getAnswer(pTwo);
			
			if (ans1.equals(ans2));
				System.out.println("You both have the same answer, but lets see if the working is right");
				
		}
		
		checkAnswer(pOne, answer);
		
		if(pTwo != null)
			checkAnswer(pTwo, answer);
		
	}
	
	private void awardPoints(Player p){
		
		System.out.println("Player " + p.getNumber() + " is correct!!!");
		
		if (p.getNumber() == 1) {
			board.addScore(p.getName(), roundType, 10);
			pOne.updateScore(10);
		} else {
			board.addScore(p.getName(), roundType, 10);
			pTwo.updateScore(10);
		}
	}
	
	private void checkAnswer(Player p, ArrayList<String> ans) {
		
		if(p.getNumber() == 1) {
			if(ans.contains(ans1))
				awardPoints(pOne);
			else
				System.out.println("Player 1 is wrong!!!");
		} else {
			if(ans.contains(ans2))
				awardPoints(pTwo);
			else
				System.out.println("Player 2 is wrong!!!");
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