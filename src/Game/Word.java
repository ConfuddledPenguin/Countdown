package Game;

import java.util.ArrayList;
import java.util.Timer;

public class Word extends Round{

	//stores the answer entered by the player
	private String playerAnswer;
	//Used to track which player has 'buzzed'
	//true for player one and false for player 2
	boolean player;

	Timer timer;

	public Word(Dictionary dict, Player pOne, Player pTwo, Input i) {
		super(dict, pOne, pTwo, i);

		timer = new Timer();
	}

	public void play() {

		System.out.println("Word\n");
		
		String letters = getLetters();

		ArrayList<String> answers = dict.getBestWords(letters);

		String keyPress = "";

		//currently printing the answer for testing purposes
		System.out.println("The letters are: " + letters+ "\n");

		timer.schedule(new TheTimer(), 10000);

		while (/* Timer */) {

			

		}
		
		player = true;
		collectAnswer(player);
		collectAnswer(!player);
		
		if(rankAnswer(player) < rankAnswer(!player)) {
			awardPoints(player);
		} else {	
			awardPoints(!player);	
		}
	}
	
	/*return position in array of players word. Smaller is better*/
	private int rankAnswer(boolean player) {
		
		return 0;
		
	}
	
	/*get letters*/
	private String getLetters() {
		
		return null;
		
	}

	private void awardPoints(boolean player) {

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
		playerAnswer = i.getWord();
		System.out.println("Your answer is: " + playerAnswer + "\n");

	}

}
