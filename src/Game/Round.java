package Game;

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
	UserIO io;
	boolean timerActive;

	/**
	 * Constructor
	 * 
	 * @param dict the dictionary for the game
	 * @param pOne player one
	 * @param pTwo player two
	 * @param board the leaderBoard
	 * @param roundType the type of round
	 * @param timerActive If the timer is active
	 */
	public Round(Dictionary dict, Player pOne, Player pTwo, LeaderBoard board, String roundType, boolean timerActive){

		this.dict = dict;
		this.pOne = pOne;
		this.pTwo = pTwo;
		this.board = board;
		this.roundType = roundType;
		this.timerActive = timerActive;
		io = new UserIO();

		if (pTwo != null){
			twoPlayer = true;
		}
	}

	public void CountdownTimer() {

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

		TheTimer timer = new TheTimer();
		Buzzer buzzer = new Buzzer(twoPlayer, io);

		if(roundType.equals("ConundrumRound"))
			scheduler.schedule(buzzer, 0, TimeUnit.SECONDS);
		scheduler.scheduleAtFixedRate(timer, 1, 1, TimeUnit.SECONDS);
		
		if(roundType.equals("ConundrumRound")) {
			buzzer.doWait();
			keyPress = buzzer.getKeyPress();
		} else {
			timer.doWait();
		}

		scheduler.shutdownNow();

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
	public void printWelcome(String currentRound) {
		io.printLines(2);
		System.out.println("Welcome to the " + currentRound + " round");
		io.printShortLines(1);
	}

}
