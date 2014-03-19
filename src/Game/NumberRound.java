package Game;

import java.util.Random;
import java.util.StringTokenizer;

/**
 * The number round of the countdown game
 *
 */
public class NumberRound extends Round{

	private int ans1;
	private int ans2;
	private String working1;
	private String working2;
	private boolean correct1;
	private boolean correct2;
	private int target;
	
	public NumberRound(Dictionary dict, Player pOne, Player pTwo, Input i) {
		super(dict, pOne, pTwo, i);
	}
	
	/**
	 * Plays a round of countdown;
	 */
	public void play() {
		
		correct1 = false;
		correct2 = false;
		
		printWelcome();
		
		//Generate target number
		Random r = new Random();
		target = r.nextInt(1000);
		System.out.println("The target is : " + target);
		
		//Get the answer for player one
		getAnswer(pOne);
		
		//Get the answer for player two if they exist
		if (pTwo != null){
			
			getAnswer(pTwo);
			
			if ( ans1 == ans2)
				System.out.println("You both have the same number, but lets see if the working is right");
			else if ( Math.abs(target - ans1) < Math.abs(target - ans2))
				System.out.println(pOne.getName() + " is closer, but lets see if the working is right\n");
			else
				System.out.println(pTwo.getName() + " is closer, but lets see if the working is right\n");
		}
		
		
		//Get the working for player one
		getWorking(pOne);
		if ( correct1 = checkWorking(ans1, working1) )
			calulatePoints(pOne);
		else
			System.out.println("Sorry " + pOne.getName() + " you working is wrong");
				
		//Get the working for player two if they exist
		if (pTwo != null){
					
			getWorking(pTwo);
			if (correct2 = checkWorking(ans2, working2))
				calulatePoints(pTwo);
			else
				System.out.println("Sorry " + pTwo.getName() + " you working is wrong");
		}
		
		System.out.println("\n\nThe best possible solution is " + bestAnswer());
		
	}
	
	private int bestAnswer(){
		
		return 0;
	}
	
	/**
	 * Calculate a players points
	 * @param p The player the score is being calculated
	 */
	private void calulatePoints(Player p){
		
		int temp = Math.abs(target - ans1);
		
		if (temp == 0){
			
			System.out.println(p.getName() + " Got it perfectly. Is awarded 10 points");
			p.updateScore(10);
		} else if ( temp < 5 ){ 
			
			System.out.println(p.getName() + " got within 5 and is awarded 7 points");
			p.updateScore(7);
		} else if ( temp < 10){ 
			
			System.out.println(p.getName() + " got within 10 and is awarded 5 points");
			p.updateScore(5);
		}else {
				
			System.out.println("Nice try " + p.getName() + " but no points");
		}
	}
	
	/**
	 * Check a players working
	 * @param ans The answer
	 * @param working The working
	 * @return true if the working is correct, false otherwise
	 */
	private boolean checkWorking(int ans, String working){
		return true;
	}
	
	/**
	 * Get an answer from the player
	 * @param p The player the answer is from
	 */
	private void getAnswer(Player p){
		
		System.out.println(p.getName() + " enter your answer: ");
		
		if (p.getNumber() == 1)
			ans1 = i.getNumber();
		else
			ans2 = i.getNumber();
		
		printLine();
	}
	
	/**
	 * Get the working from a player
	 * @param p The layer the working is from
	 */
	private void getWorking(Player p){
		
		System.out.println(p.getName() + " using + - * / please enter your working: ");
		
		if (p.getNumber() == 1)
			working1 = i.getString();
			else
			working2 = i.getString();	
		
		printLine();
	}

	/**
	 * Prints out a welcome message
	 */
	private void printWelcome(){
		
		System.out.println("----------------------------------------------------");
		System.out.println("----------------------------------------------------");
		System.out.println("Welcome to the Number round");
		System.out.println("---------------------------\n");
	}


}
