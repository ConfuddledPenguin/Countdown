package Game;

public class TheTimer implements Runnable {

	Monitor m;
	int time;

	public TheTimer() {

		m = new Monitor();
		time = 30;

	}

	public void run() {

		System.out.print(time + " ");
		time--;
		if(time == 0) {
			System.out.println("\nTime's up!");
			doNotify();
		}
		
	}

	public void doWait(){
		synchronized(m){
			try{
				m.wait();
			} catch(InterruptedException e){}
		}
	}

	public void doNotify(){
		synchronized(m){
			m.notify();
		}
	}
	
	//Object used to pass signals between threads
	private class Monitor {
		private Monitor() {}
	}
	
}
