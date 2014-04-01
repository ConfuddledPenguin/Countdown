package Game;

import java.util.Timer;
import java.util.TimerTask;

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
	private String roundType;
	
	/**
	 * Constructor
	 * 
	 * @param dict the dictionary for the game
	 * @param pOne player one
	 * @param pTwo player two
	 */
	public Round(Dictionary dict, Player pOne, Player pTwo, String roundType){
		
		this.dict = dict;
		this.pOne = pOne;
		this.pTwo = pTwo;
		
		if (pTwo != null){
			twoPlayer = true;
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
	
}
