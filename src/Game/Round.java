package Game;

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
	
	/**
	 * Prints out a welcome message
	 */
	public void printWelcome() {

		System.out.println("----------------------------------------------------");
		System.out.println("----------------------------------------------------");
		System.out.println("Welcome to the " + roundType + " round");
		System.out.println("---------------------------\n");
	}
	
}
