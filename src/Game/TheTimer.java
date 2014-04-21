package Game;

public class TheTimer implements Runnable {

	Monitor m;
	int time;

	/**
	 * Constructor for TheTimer class
	 */
	public TheTimer() {

		m = new Monitor();
		time = 30;

	}

	/**
	 * Method from Runnable interface which is called from executor
	 */
	public void run() {
		
		if(time == 30)
			System.out.println("Let the countdown begin, 30 Seconds Left!");
		else if(time == 20)
			System.out.println("20 Seconds Left!");
		else if(time == 10)
			System.out.println("10 Seconds Left!");
		else if(time < 6 && time > 0)
			System.out.print(time + " ");
		time--;
		if(time == 0) {
			System.out.println("\nTime's up!");
			doNotify();
		}		
	}
	
	/**
	 * Allows an object to wait on the execution of this class completing
	 */
	public void doWait(){
		synchronized(m){
			try{
				m.wait();
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