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
	Input i;
	Timer t;
	boolean twoPlayer = false;
	
	
	/**
	 * Constructor
	 * 
	 * @param dict the dictionary for the game
	 * @param pOne player one
	 * @param pTwo player two
	 * @param i the input handler
	 */
	public Round(Dictionary dict, Player pOne, Player pTwo, Input i){
		
		this.dict = dict;
		this.pOne = pOne;
		this.pTwo = pTwo;
		this.i = i;
		
		t = new Timer();
		
		if (pTwo != null){
			twoPlayer = true;
		}
	}
	
	/**
	 * This method is for playing the round
	 */
	abstract public void play();
	
}
