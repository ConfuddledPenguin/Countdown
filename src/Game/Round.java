package Game;

abstract public class Round {
	
	Dictionary dict;
	Player pOne;
	Player pTwo;
	boolean twoPlayer = false;
	
	public Round(Dictionary dict, Player pOne, Player pTwo){
		
		this.dict = dict;
		this.pOne = pOne;
		this.pTwo = pTwo;
		
		if (pTwo != null){
			twoPlayer = true;
		}
	}
	
	abstract public void play();
	
}
