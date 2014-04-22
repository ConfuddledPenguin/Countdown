package Game;

public class Buzzer implements Runnable {

	Monitor m;
	boolean twoPlayer;
	UserIO io;

	int keyPress;

	/**
	 * Constructor for Buzzer class
	 * 
	 * @param twoPlayer to tell if the game is single or multi player
	 * @param io
	 */
	public Buzzer(boolean twoPlayer, UserIO io) {

		m = new Monitor();
		this.twoPlayer = twoPlayer;
		this.io = io;

	}
	
	/**
	 * Method from Runnable interface which is called from executor
	 */
	public void run() {

		System.out.println("Player 1, press 1 to buzz with answer");
		if(twoPlayer)
			System.out.println("Player 2, press 2 to buzz with answer");
		System.out.println("Press 0 to give up!");

		keyPress = -1;

		while (keyPress == -1) {

			keyPress = io.getNumber();

			if(keyPress == 1) {
				System.out.println("Player 1 Buzzed First!");
				this.doNotify();
			} else if(keyPress == 2 && twoPlayer) {
				System.out.println("Player 2 Buzzed First!");
				this.doNotify();
			} else if(keyPress == 0){
				System.out.println("You have given up!");
				this.doNotify();
			} else {
				keyPress = -1;
			}
		}	

	}

	/**
	 * @return an int to show who buzzed first
	 */
	public int getKeyPress() {

		return keyPress;

	}

	/**
	 * Allows an object to wait on the execution of this class completing
	 */
	public void doWait(){
		synchronized(m){
			try{
				m.wait(32000,0);
			} catch(InterruptedException e){}
		}
	}

	/**
	 * Notify waiting object that executon is complete
	 */
	public void doNotify(){
		synchronized(m){
			m.notifyAll();
		}
	}
}
