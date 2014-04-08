package Game;

/**
 * This class executes a full game of countdown
 *
 */
public class Countdown {
	
	private Player playerOne;
	private Player playerTwo;
	private Round currentRound;
	private GameObjects objects;

	public Countdown(GameObjects go) {

		this.objects = go;
		playerOne = go.pOne;
		playerTwo = go.pTwo;
	}

	public void play() {

		play(0);

	}
	
	private void play(int start){
		
		//Say hi :)
		printWelcome();
		
		//Loop for the number of rounds i.e 15
		int i = start;
		while ( i < 15){
			
			//Tempted to change the below to a list of some sort and use contains . . . .
			
			/* If its a letters round
			 * 
			 * The letters rounds are round numbers:
			 * 0, 1, 3, 4, 5, 6, 9 ,10, 11, 12
			 */
			if ( i < 2 || ( i >= 3 && i < 7) || (i >= 9 || i < 13)){
				
				//Inform the user that we are going to play a round
				if (i != 0)
					System.out.println("Next up we have a word round");
				else
					System.out.println("The first round, as always, is a word round");
				
				currentRound = new WordRound(objects);
				currentRound.play();
				i++;
				continue;
			}
			
			/* If its a numbers round
			 * 
			 * The numbers rounds are round numbers:
			 * 2, 7, 8, 13
			 */
			if ( i == 2 || i == 7 || i == 8 || i == 13){
				
				System.out.println("Next up we have a number round");
				currentRound = new  NumberRound(objects);
				currentRound.play();
				i++;
				continue;
			}
			
			/* If this part of the loop is reached then we are on the last round
			 * which is the conundrum round.
			 */
			
			System.out.println("Sadly we are nearing the end\nwith just the conundrum round left to play");
			currentRound = new ConundrumRound(objects);
			currentRound.play();
			i++;
		}// Close game loop
		
		printClose();		
	}

	private void printWelcome(){
		
		System.out.println("----------------------------------------------------");
		System.out.println("----------------------------------------------------");
		System.out.println("Hello and welcome to countdown");
		System.out.println("---------------------------\n");
		
	}
	
	/**
	 * Prints a final message
	 */
	private void printClose(){
		
		System.out.println("Well that the game over.");
		
		if (playerOne.getScore() >= playerTwo.getScore())
			printWinner(playerOne, playerTwo);
		else
			printWinner(playerTwo, playerOne);
		
		System.out.println("We hope to see you again soon for another game.");
	}
	
	/**
	 * Announces the winner of the game
	 * 
	 * @param winner the games winner
	 * @param loser the games loser
	 */
	private void printWinner( Player winner, Player loser){
		
		System.out.println("Its seems that " + winner.getName() + " has won.");
		
		int pointdiff = winner.getScore() - loser.getScore();
		
		if ( pointdiff < 10){
			System.out.println("It was close though with only a " + pointdiff + " lead.");
			System.out.println("So it was a very exciting game.");
		} else if (pointdiff > 100){
			System.out.println("It seems that " + winner.getName() + " kicked " + loser.getName() + " ass, with a " + pointdiff + 
					" differnce.");
		} else if (pointdiff == 0){
			System.out.println("A draw! Well done both players.");
		} else {
			System.out.println(winner.getName() + "won with a " + pointdiff + "lead over " + loser.getName() + ".\n" +
		"A good game all round");
			
		}
		
		//Add players the the leaderboards
		objects.leaders.addScore(playerOne.getName(), LeaderBoard.FULLGAME, playerOne.getScore());
		objects.leaders.addScore(playerTwo.getName(), LeaderBoard.FULLGAME, playerTwo.getScore());
		
		System.out.println("commiserations " + loser.getName() + " and of course well done " + winner.getName() + ".");
		
	}
	
	public void load() {



	}

	private void save() {



	}

}
