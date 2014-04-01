package Game;

/**
 * The conundrum round for the countdown game
 */
import java.util.ArrayList;
import java.util.Timer;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;

public class ConundrumRound extends Round {
	
	//stores the answer entered by the player
	private String playerAnswer;
	//Used to track which player has 'buzzed'
	//true for player one and false for player 2
	boolean player;
	
	private UserIO i;
	
	Timer timer;
	
	public ConundrumRound(Dictionary dict, Player pOne, Player pTwo) {
		
		super(dict, pOne, pTwo, "Conundrum Round");
		
		i = new UserIO();
		timer = new Timer();
	}
	
	public void play() {

		System.out.println("Conundrum Round\n");
		
		String anagram = dict.getAnagram();
		ArrayList<String> answer = dict.getBestWords(anagram);
		
		String keyPress = "";
		
		//currently printing the answer for testing purposes
		System.out.println("The conundrum is: " + anagram + ", Answer: " + answer + "\n");
		
		timer.schedule(new TheTimer(), 10000);
		
		while (keyPress.equalsIgnoreCase("1") == false && keyPress.equalsIgnoreCase("2") == false) {
			
			System.out.println("Player 1 press '1' to enter answer\nPlayer 2 press '2' to enter answer\n");
			
			keyPress = i.getString();
		       
		       if(keyPress.equals("1")) {
					
		    	    timer.cancel();
		    	    player = true;
					collectAnswer(player);
					
				} else if(keyPress.equals("2")) {
					
					timer.cancel();
		    	    player = false;
		    	    collectAnswer(player);
					
				}
		}
		
		/*
		 * if the first answer is correct then award points to the player whom entered it
		 * else get answer from the other player
		 * 		if that is correct award points
		 * 		else no points for anyone
		 */
		if(playerAnswer.equals(answer)) {
			
			awardPoints(player);
			
		} else {
			
			System.out.println("Incorrect!");
			
			if (player){
				collectAnswer(!player);
			} else {
				collectAnswer(player);
			}
			
			if (playerAnswer.equals(answer)){
				awardPoints(!player);
			} else {
				System.out.println("Both wrong. The answer was " + answer + "\n");
			}
			
		}
		
	}
	
	private void awardPoints(boolean player){
		
		if (player){
			System.out.println("Player 1 wins");
			pOne.updateScore(10);
		} else {
			System.out.println("Player 2 wins");
			pTwo.updateScore(10);
		}
	}
	
	private void collectAnswer(Boolean whatPlayer) {
		
		int playerNo;
		
		if(whatPlayer) {
			playerNo = 1;
		} else {
			playerNo = 2;
		}
		
		System.out.println("Player " + playerNo + " enter answer: ");
		playerAnswer = i.getString();
		System.out.println("Your answer is: " + playerAnswer + "\n");
		
	}

}
