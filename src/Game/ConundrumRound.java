package Game;

import java.util.ArrayList;

/**
 * The conundrum round for the countdown game
 */
public class ConundrumRound extends Round {
	
	//stores the answer entered by the player
	private String ans1;
	private String ans2;
	
	
	/**
	 * A constructor for the conundrum round
	 * 
	 * @param dict the dictionary file
	 * @param pOne Player one
	 * @param pTwo Player two
	 * @param board the leaderboard
	 * @param timerActive if the timer is active
	 */
	public ConundrumRound(Dictionary dict, Player pOne, Player pTwo, LeaderBoard board, boolean timerActive) {
		
		super(dict, pOne, pTwo, board, LeaderBoard.CONUNDRUMROUND, timerActive);
	}
	
	/**
	 * A constructor for the conundrum round
	 * 
	 * @param dict the dictionary file
	 * @param pOne Player one
	 * @param pTwo Player two
	 * @param board the leaderboard
	 */
	public ConundrumRound(Dictionary dict, Player pOne, Player pTwo, LeaderBoard board) {
		
		super(dict, pOne, pTwo, board, LeaderBoard.CONUNDRUMROUND, true);
	}
	
	/**
	 * A constructor for the number round
	 * @param o A gameObjects object.
	 * @param timerActive if the timer is active
	 */
	public ConundrumRound(GameObjects o, boolean timerActive) {
		
		this(o.dict, o.pOne, o.pTwo, o.leaders, timerActive);
	}
	
	/**
	 * A constructor for the number round
	 * @param o A gameObjects object.
	 */
	public ConundrumRound(GameObjects o) {
		
		this(o.dict, o.pOne, o.pTwo, o.leaders, true);
	}
	
	public void play() {

		printWelcome();
		
		String anagram = dict.getAnagram();
		ArrayList<String> answer = dict.getBestWords(anagram);
		
		//currently printing the answer for testing purposes
		System.out.println("The conundrum is: " + anagram + ", Answer: " + answer + "\n");
		
		if(timerActive)
			CountdownTimer();
		else
			buzzer(null);
		
		if(keyPress == 1) {
			getAnswer(pOne);
			if(!checkAnswer(pOne, answer) && twoPlayer) {
				getAnswer(pTwo);
				checkAnswer(pTwo, answer);
			}
		} else if(keyPress == 2) {
			getAnswer(pTwo);
			if(!checkAnswer(pTwo, answer)) {
				getAnswer(pOne);
				checkAnswer(pOne, answer);
			}
		} else {
			System.out.println("There was no buzz in time therefore no points are awarded :(");
		}
	}
	
	private void awardPoints(Player p){
		
		System.out.println("Player " + p.getNumber() + " is correct!!!");
		
		if (p.getNumber() == 1) {
			if (timerActive)
				board.addScore(p.getName(), roundType, 10);
			pOne.updateScore(10);
		} else {
			if (timerActive)
				board.addScore(p.getName(), roundType, 10);
			pTwo.updateScore(10);
		}
	}
	
	private boolean checkAnswer(Player p, ArrayList<String> ans) {
		
		if(p.getNumber() == 1) {
			
			if(ans.contains(ans1)) {
				awardPoints(pOne);
				return true;
			}
			
		} else {
			
			if(ans.contains(ans2)) {
				awardPoints(pTwo);
				return true;
			}
		}
		
		System.out.println(p.getName() + " is wrong!!!");
		return false;
		
	}
	
	private void getAnswer(Player p) {
		
		System.out.println(p.getName() + " enter your answer: ");

		if (p.getNumber() == 1) {
			ans1 = io.getString();
			System.out.println("Your answer is: " + ans1);
		} else {
			ans2 = io.getString();
			System.out.println("Your answer is: " + ans2);
		}

		System.out.println();
		
	}
}