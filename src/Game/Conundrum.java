package Game;

import java.util.Timer;

public class Conundrum extends Round {
	
	private String answer1;
	private String answer2;
	
	public Conundrum(Dictionary dict, Player pOne, Player pTwo, Input i) {
		super(dict, pOne, pTwo, i);
	}
	
	public void play() {

		System.out.println("Conundrum Round");
		
		String[] temp = dict.getAnagram();
		
		String answer = temp[0];
		String anagram = temp[1];
		
		System.out.println("The conundrum is: " + anagram + " " + answer); //answer just for testing
		
		for(int i = 5; i > 0; i--) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
		}
		
		System.out.println("Player 1 enter answer: ");
		answer1 = i.getWord();
		System.out.println("Your answer is: " + answer1);
		
		System.out.println("Player 2 enter answer: ");
		answer2 = i.getWord();
		System.out.println("Your answer is: " + answer2);
		
		if(answer1.equals(answer) && !answer2.equals(answer)) {
			
			System.out.println("Player 1 wins");
			pOne.updateScore(10);
			
		} else if(!answer1.equals(answer) && answer2.equals(answer)) {
			
			System.out.println("Player 2 wins");
			pTwo.updateScore(10);
			
		} else if(answer1.equals(answer) && answer2.equals(answer)) {
			
			System.out.println("Both correct");
			pOne.updateScore(5);
			pTwo.updateScore(5);
			
		} else {
			
			System.out.println("Both wrong. The answer was: " + answer);
			
		}
		
	}

}
