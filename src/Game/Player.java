package Game;

public class Player {
	
	private String name;
	private int score;
	
	public Player(String n) {
		
		name = n;
		score = 0;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public int getScore() {
		
		return score;
		
	}
	
	public void updateScore(int points) {
		
		score = score + points;
		
	}	

}
