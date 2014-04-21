package Game;

/**
 * The main class for the program
 */
public class Launcher {
	
	private Player playerOne;
	private Player playerTwo;

	private UserIO io;
	private LeaderBoard leaders;
	
	private Dictionary dict;
	
	private GameObjects objects;
	
	//default to timer always on
	private boolean timerActive = true;

	public Launcher() {

		io = new UserIO();
		leaders = new LeaderBoard();
		dict = new Dictionary();
		objects = new GameObjects(null, null, dict, leaders);
		
		printWelcome();
		getPlayerDetials();
		
		playGame();
		
		io.close();
	}

	/**
	 * Prints a welcome message to the program
	 */
	private void printWelcome(){
		
		io.printLines(2);
		System.out.println("Welcome to countdown\n" +
				"I hope you are ready to play!!\n\n"+
				"This Game features autosave! be amazed!\n" +
				"Your games are saved by magic after every round!\n\n"+
				"(c) Thomas Maxwell and Thomas Sinclair");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e){
			
		}
		
		io.printLines(2);
	}
	
	/**
	 * Get the details of the players an if there is one or two players
	 */
	private void getPlayerDetials(){
		
		System.out.println("How many players are there: 1, or the better 2:");
		int playerNo = io.getNumber();
		
		while (playerNo < 1 || playerNo > 2){
		
			System.err.println("There can only be one or two players, try again:");
			playerNo = io.getNumber();
		}		
		
		System.out.println("Player One enter name: ");
		playerOne = new Player(io.getString(), 1);
		objects.pOne = playerOne;
		
		if (playerNo == 2){
			
			System.out.println("Player Two enter name: ");
			playerTwo = new Player(io.getString(), 2);
			objects.pTwo = playerTwo;
		}
	}

	/**
	 * Get the users idea of wonderfulness
	 */
	private void playGame() {

		boolean loop = true;

		while(loop == true) {

			io.printLines(2);
			
			//Get ready for a new game;
			playerOne.clearScore();
			if (playerTwo != null)
				playerTwo.clearScore();
			
			System.out.println("\nMain Menu\n"+
							"What would you like to do?\n\n" +
							"Play a game\t\t\t- 1\n" +
							"Play single round:\n" +
							"\tWord\t\t\t- 2\n" +
							"\tNumber\t\t\t- 3\n" +
							"\tConundrum\t\t- 4\n\n" +
							"View Scoreboard\t\t\t- 5\n" +
							"Options\t\t\t\t- 6\n" +
							"Quit\t\t\t\t- 7\n");

			switch(io.getNumber()) {

				case 0: break;

				case 1: gameSelector();
						break;

				case 2: WordRound wr = new WordRound(objects, timerActive);
						wr.play();
						break;

				case 3: NumberRound nr = new NumberRound(objects, timerActive);
						nr.play();
						break;

				case 4: ConundrumRound cr = new ConundrumRound(objects, timerActive);
						cr.play();
						break;

				case 5: leaderboard();
						break;

				case 6: options();
						break;
						
				case 7: loop = false;
						io.printLines(2);
						System.out.println("Exiting . . . ");
						io.printLines(1);
						leaders.saveScores();
						System.exit(1);
						break;

				default:
					System.err.println("Please enter a valid option");
					break;

			} //end switch
		} //end while
	} //end method
	
	private void leaderboard(){
		
		io.printLines(2);
		
		System.out.println("So you would like to see how pathetic you are compared to other players huh? Okay then");
		System.out.println("How would you like to measure your lack of performance:\n");
		
		
		System.out.println("Overall leaderboard\t\t- 1\n\n" +
				"Full game\t\t\t- 2\n" +
				"Custom game\t\t\t- 3\n\n" +
				"Word round\t\t\t- 4\n" +
				"Number round\t\t\t- 5\n" +
				"Conundrum round\t\t\t- 6\n\n" +
				"Back\t\t\t\t- 7\n");
		
		switch(io.getNumber()){
		
			case 1: leaders.printScores();
					break;
			case 2: leaders.printScoresFull();
					break;
			case 3: leaders.printScoresCustom();
					break;
			case 4: leaders.printScoresWord();
					break;
			case 5: leaders.printScoresNumber();
					break;
			case 6: leaders.printScoresConundrum();
					break;
			case 7: return;
			default: System.out.println("Pick a valid option");
					leaderboard();
					break;
		}
	}//Close leaderboard()
	
	/**
	 * A little bit of code to select a game type.
	 */
	private void gameSelector(){
		
		io.printLines(1);
		
		System.out.println("Have some options pick one:\n\n" +
				"Play a full game\t\t-1\n" +
				"Play a custom game\t\t-2\n" +
				"Load a game\t\t\t-3\n\n" + 
				"Back\t\t\t\t-4");
		
		Countdown cd = new Countdown(objects);
		
		switch(io.getNumber()){
			
			case 1: cd.play();
					break;
			
			case 2: cd.playCustomGame();
					break;
			
			case 3: cd.load();
					break;
			
			case 4: return;
			
			default: System.out.println("Please choose a valid option, not your silly one");
					gameSelector();
		}		
	}//close gameSelector()

	private void options(){
		
		io.printLines(2);
		
		System.out.println("Options:\n\n" +
					"Timer\t\t\t\t-1\n" +
					"back\t\t\t\t-2");
		
		switch (io.getNumber()){
		
		case 1: io.printShortLines(1);
				System.out.println("Would you like the timer to be active? (Yes/No) :");
				timerActive = io.getYesNo();
				if (!timerActive){
					System.out.println("Doesn't that defeat the point of countdown?");
					System.out.println("Its cheating on its highest level");
					System.out.println("Be ashamed!!!!!");
					System.out.println("As such your scores will not be stored on the leaderboard\n" +
					"Further more becuase cheating is shameful this always resets back to true, when" +
					"ever the game is luanched");
				}
				break;
		
		case 2: return;
		
		default: System.out.println("Not a valid choice. Try again!");
				break;	
		}
		
		options();
		
	}
	
	/**
	 * The almighty main
	 * @param args Nothing/nada/ziltch
	 */
	public static void main(String args[]) {
		
		Launcher l = new Launcher();
	}

}

