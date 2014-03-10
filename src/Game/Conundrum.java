package Game;

import java.util.Timer;
import java.util.concurrent.Executors;

public class Conundrum extends Round {
	
	private String answer1;
	private String answer2;
	boolean player;
	
	Timer timer;
	
	public Conundrum(Dictionary dict, Player pOne, Player pTwo, Input i) {
		super(dict, pOne, pTwo, i);
		
		timer = new Timer();
	}
	
	public void play() {

		System.out.println("Conundrum Round");
		
		String[] temp = dict.getAnagram();
		
		String answer = temp[0];
		String anagram = temp[1];
		
		String keyPress = "";
		
		System.out.println("The conundrum is: " + anagram + " " + answer); //answer just for testing
		
		timer.schedule(new TheTimer(), 10000);
		
		while (keyPress.equalsIgnoreCase("q") == false && keyPress.equalsIgnoreCase("p") == false) {
			keyPress = i.getWord();
		       
		       if(keyPress.equals("q")) {
					
		    	    timer.cancel();
					System.out.println("Player 1 enter answer: ");
					answer1 = i.getWord();
					System.out.println("Your answer is: " + answer1);
					player = true;
					
				} else if(keyPress.equals("p")) {
					
					timer.cancel();
					System.out.println("Player 2 enter answer: ");
					answer1 = i.getWord();
					System.out.println("Your answer is: " + answer1);
					player = false;
					
				} else {
					
					System.out.println("Incorrect Input");
					
				}
		}
		
		/*
		 * if the first answer is correct then award points to the player whom entered it
		 * else get answer from the other player
		 * 		if that is correct award points
		 * 		else no points for anyone
		 */
		if(answer1.equals(answer)) {
			
			awardPoints(player);
			
		} else {
			
			if (player){
				System.out.println("Incorrect, player 2 enter answer: ");
				answer2 = i.getWord();
				System.out.println("Your answer is: " + answer2);
			}else{
				System.out.println("Incorrect, player 1 enter answer: ");
				answer2 = i.getWord();
				System.out.println("Your answer is: " + answer2);
			}
			
			if (answer2.equals(answer)){
				
				awardPoints(!player);
				
			}else{
				System.out.println("Both wrong. The answer was " + answer);
			}
			
		}
		
	}
	
	private void awardPoints(boolean player){
		
		if (!player){
			System.out.println("Player 1 wins");
			pOne.updateScore(10);
		}else{
			System.out.println("Player 2 wins");
			pTwo.updateScore(10);
		}
	}

}
