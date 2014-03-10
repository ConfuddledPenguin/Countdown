package Game;

import java.util.TimerTask;

public class TheTimer extends TimerTask {
	
	boolean alive = true;
	
	public void run() {
		
		alive = false;
		System.out.println("Time's up");		
	}
	
	public boolean IsAlive(){
		return alive;
	}
}
