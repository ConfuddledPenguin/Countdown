package Game;

public class TheTimer implements Runnable {

	Monitor m;
	int time;

	public TheTimer() {

		m = new Monitor();
		time = 30;

	}

	public void run() {
		
		if(time == 30)
			System.out.println("30 Seconds Left!");
		else if(time == 20)
			System.out.println("20 Seconds Left!");
		else if(time == 10)
			System.out.println("10 Seconds Left!");
		else if(time < 6)
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