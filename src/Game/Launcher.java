package Game;

public class Launcher {
	
	private Player playerOne;
	private Player playerTwo;

	private Input i;
	private Scoreboard s;
	
	private Dictionary dict;

	public Launcher() {

		i = new Input();
		s = new Scoreboard();
		dict = new Dictionary();
		getCommand();

	}

	private void getCommand() {

		boolean loop = true;

		while(loop == true) {

			System.out.println("----------------------------------------------------\n\n");
			
			System.out.println("\nWhat would you like to do?\n\n" +
							"Play full game\t\t\t- 1\n" +
							"Play single round:\n" +
							"\tWord\t\t\t- 2\n" +
							"\tNumber\t\t\t- 3\n" +
							"\tConundrum\t\t- 4\n\n" +
							"View Scoreboard\t\t\t- 5\n" +
							"Quit\t\t\t\t- 6\n");

			switch(i.getMode()) {

				case 0: break;

				case 1: System.out.println("1");
						break;

				case 2: Word w = new Word();
						w.play();
						break;

				case 3: Number n = new Number();
						n.play();
						break;

				case 4: Conundrum c = new Conundrum(dict, playerOne, playerTwo);
						c.play();
						break;

				case 5: s.loadScores();
						s.viewScores();
						break;

				case 6: loop = false;
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

