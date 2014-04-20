package Game;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This abstract class is deigned to be used as a basis for the individual rounds of the game
 * 
 * @author Tom
 */
abstract public class Round {

	Dictionary dict;
	Player pOne;
	Player pTwo;
	boolean twoPlayer = false;
	String roundType;
	LeaderBoard board;
	int keyPress;
	UserIO i;

	/**
	 * Constructor
	 * 
	 * @param dict the dictionary for the game
	 * @param pOne player one
	 * @param pTwo player two
	 * @param board the leaderBoard
	 * @param roundType the type of round
	 */
	public Round(Dictionary dict, Player pOne, Player pTwo, LeaderBoard board, String roundType){

		this.dict = dict;
		this.pOne = pOne;
		this.pTwo = pTwo;
		this.board = board;
		this.roundType = roundType;
		
		i = new UserIO();

		if (pTwo != null){
			twoPlayer = true;
		}
	}

	public void CountdownTimer() {

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		TheTimer timer = new TheTimer();
		
		scheduler.scheduleAtFixedRate(timer, 0, 1, TimeUnit.SECONDS);
		
		if(roundType.equals("ConundrumRound")) {
			
			buzzer(scheduler);
			
		} else {
			timer.doWait();
			scheduler.shutdown();
		}
		
		i.clear();
		
	}
	
	public void buzzer(ScheduledExecutorService scheduler) {
		
		System.out.println("Player 1, press 1 to buzz with answer");
		if(twoPlayer)
			System.out.println("Player 2, press 2 to buzz with answer");
		
		keyPress = 0;
		
		while (keyPress == 0) {
			
			keyPress = i.getNumber();
			
			System.out.println(keyPress);
			
			if(keyPress == 1) {
				System.out.println("Player 1 Buzzed First!");
				scheduler.shutdown();
			} else if(keyPress == 2 && twoPlayer) {
				System.out.println("Player 2 Buzzed First!");
				scheduler.shutdown();
			} else {
				keyPress = 0;
			}
		}
	}

	/**
	 * Returns the type of round
	 * 
	 * @return The type of round
	 */
	public String roundType() {
		return roundType;
	}


	/**
	 * This method is for playing the round
	 */
	abstract public void play();

	/**
	 * Prints out a welcome message
	 */
	public void printWelcome() {
		i.printLines(2);
		System.out.println("Welcome to the " + roundType + " round");
		i.printShortLines(1);
	}

}
