package Game;

/**
 * The main class for the program
 */
public class Launcher {
	
	private Player playerOne;
	private Player playerTwo;

	private IO i;
	private Scoreboard s;
	
	private Dictionary dict;

	public Launcher() {

		i = new IO();
		s = new Scoreboard();
		dict = new Dictionary();
		getPlayerDetials();
		
		getCommand();

	}
	
	/**
	 * Get the details of the players an if there is one or two players
	 */
	private void getPlayerDetials(){
		
		System.out.println("How many players are there: 1, or the better 2:");
		int playerNo = i.getNumber();
		while (playerNo < 1 || playerNo > 2){
		
			System.err.println("There can only be one or two players, try again:");
			playerNo = i.getNumber();
		}
		
		System.out.println("Player One enter name: ");
		playerOne = new Player(i.getString(), 1);
		
		if (playerNo == 2){
			
			System.out.println("Player Twoe enter name: ");
			playerTwo = new Player(i.getString(), 2);
		}
	}

	private void getCommand() {

		boolean loop = true;

		while(loop == true) {

			i.printLine();
			
			System.out.println("\nWhat would you like to do?\n\n" +
							"Play full game\t\t\t- 1\n" +
							"Play single round:\n" +
							"\tWord\t\t\t- 2\n" +
							"\tNumber\t\t\t- 3\n" +
							"\tConundrum\t\t- 4\n\n" +
							"View Scoreboard\t\t\t- 5\n" +
							"Quit\t\t\t\t- 6\n");

			switch(i.getNumber()) {

				case 0: break;

				case 1: System.out.println("1");
						break;

				case 2: WordRound w = new WordRound(dict, playerOne, playerTwo, i);
						w.play();
						break;

				case 3: NumberRound n = new NumberRound(dict, playerOne, playerTwo, i);
						n.play();
						break;

				case 4: ConundrumRound c = new ConundrumRound(dict, playerOne, playerTwo, i);
						c.play();
						break;

				case 5: s.loadScores();
						s.viewScores();
						break;

				case 6: loop = false;
						System.out.print("Exiting");
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

