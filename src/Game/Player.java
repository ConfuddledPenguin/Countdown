package Game;

/**
 * This class holds all the details of one player of the game
 * 
 * @author Thomas Sinclair
 *
 */
public class Player {
	
	private String name;
	private int score;
	
	/**
	 * Constructor for the player class
	 * @param n of the player
	 */
	public Player(String n) {
		
		name = n;
		score = 0;
		
	}
	
	/**
	 * Gets the players name
	 * 
	 * @return Players name
	 */
	public String getName() {
		
		return name;
		
	}
	
	/**
	 * Gets the players score
	 * 
	 * @return Players Score
	 */
	public int getScore() {
		
		return score;
		
	}
	
	/**
	 * Update the players score
	 * 
	 * @param points The number of points to be given to the player
	 */
	public void updateScore(int points) {
		
		score =+ points;
		
	}	

}
