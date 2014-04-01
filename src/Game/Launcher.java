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

	public Launcher() {

		io = new UserIO();
		leaders = new LeaderBoard();
		dict = new Dictionary();
		objects = new GameObjects(null, null, dict, leaders);
		
		printWelcome();
		getPlayerDetials();
		
		playGame();
	}

	/**
	 * Prints a welcome message to the program
	 */
	private void printWelcome(){
		
		io.printLines(2);
		System.out.println("Welcome to countdown\n" +
				"I hope you are ready to play!!\n\n"+
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
			
			System.out.println("\nWhat would you like to do?\n\n" +
							"Play full game\t\t\t- 1\n" +
							"Play single round:\n" +
							"\tWord\t\t\t- 2\n" +
							"\tNumber\t\t\t- 3\n" +
							"\tConundrum\t\t- 4\n\n" +
							"View Scoreboard\t\t\t- 5\n" +
							"Quit\t\t\t\t- 6\n");

			switch(io.getNumber()) {

				case 0: break;

				case 1: System.out.println("1");
						break;

				case 2: WordRound w = new WordRound(dict, playerOne, playerTwo);
						w.play();
						break;

				case 3: NumberRound n = new NumberRound(objects);
						n.play();
						break;

				case 4: ConundrumRound c = new ConundrumRound(dict, playerOne, playerTwo);
						c.play();
						break;

				case 5: leaders.printScores();
						break;

				case 6: loop = false;
						io.printLines(2);
						System.out.println("Exiting");
						io.printLines(1);
						System.exit(1);
						break;

				default:
					System.err.println("Please enter a valid option");
					break;

			} //end switch

		} //end while

	} //end method

	public static void main(String args[]) {
		
		Launcher l = new Launcher();
	}

}

