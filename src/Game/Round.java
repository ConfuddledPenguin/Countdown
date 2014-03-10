package Game;

import java.util.Timer;
import java.util.TimerTask;

abstract public class Round {
	
	Dictionary dict;
	Player pOne;
	Player pTwo;
	Input i;
	Timer t;
	boolean twoPlayer = false;
	
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
	
	abstract public void play();
	
}
