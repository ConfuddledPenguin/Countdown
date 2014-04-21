package Game;

public class Buzzer implements Runnable {

	Monitor m;
	boolean twoPlayer;
	UserIO io;

	int keyPress;

	public Buzzer(boolean twoPlayer, UserIO io) {

		m = new Monitor();
		this.twoPlayer = twoPlayer;
		this.io = io;

	}

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

	public int getKeyPress() {

		return keyPress;

	}

	public void doWait(){
		synchronized(m){
			try{
				m.wait(32000,0);
			} catch(InterruptedException e){}
		}
	}

	public void doNotify(){
		synchronized(m){
			m.notifyAll();
		}
	}
}
