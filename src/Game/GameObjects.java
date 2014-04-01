package Game;

/**
 * I got fed up of having to pass lots of parameters so 
 * I created a class to pass them in
 */
public class GameObjects {
	
	public Player pOne;
	public Player pTwo;
	public Dictionary dict;
	public LeaderBoard leaders;
	
	/**
	 * The constructor for the GameObjects (lazy) class
	 * @param pOne Player one
	 * @param pTwo Player two
	 * @param dict The dictionary
	 * @param leaders The leaderBoard
	 */
	public GameObjects(Player pOne, Player pTwo, Dictionary dict, LeaderBoard leaders){
		
		this.pOne = pOne;
		this.pTwo = pTwo;
		this.dict = dict;
		this.leaders = leaders;
	}

}
