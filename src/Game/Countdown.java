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
	private char[] gameorder;
	private UserIO io;
	
	private static char WORD = 'W';
	private static char NUMBER = 'N';
	private static char CONUNDRUM = 'C';

	/**
	 * The constructor for the countdown class. Takes a GameObjects object
	 * @param go The GameObjects
	 */
	public Countdown(GameObjects go) {

		this.objects = go;
		playerOne = go.pOne;
		playerTwo = go.pTwo;
		
		io = new UserIO();
	}

	public void play() {

		gameorder = new char[15];
		
		int i = 0;
		while ( i < 14){
			
			//Tempted to change the below to a list of some sort and use contains . . . .
			
			/* If its a word round
			 * 
			 * The word rounds are round numbers:
			 * 0, 1, 3, 4, 5, 6, 9 ,10, 11, 12
			 */
			if ( i < 2 || ( i >= 3 && i < 7) || (i >= 9 || i < 13)){
				
				gameorder[i] = WORD;
				continue;
			}
			
			/* If its a numbers round
			 * 
			 * The numbers rounds are round numbers:
			 * 2, 7, 8, 13
			 */
			if ( i == 2 || i == 7 || i == 8 || i == 13){
				
				gameorder[i] = NUMBER;
				continue;
			}
			
			i++;
		}// Close game loop
		
		// finally add conundrum
		gameorder[14] = CONUNDRUM;
		
		play(0);
	}
	
	private void play(int start){
		
		//Say hi :)
		printWelcome();
		
		//Loop for the number of rounds i.e 15
		int i = start;
		while ( i < gameorder.length){
			
			//Tempted to change the below to a list of some sort and use contains . . . .
			
			// if word round
			if ( gameorder[i] == WORD ){
				
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
			
			// If its a numbers round
			if ( gameorder[i] == NUMBER ){
				
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
		
		if (playerTwo == null){
			printWinner(playerOne, null);
		}else{
		
			if (playerOne.getScore() >= playerTwo.getScore())
				printWinner(playerOne, playerTwo);
			else
				printWinner(playerTwo, playerOne);
		}
		
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
		
		if (loser != null){
		
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
		}
		
		//Add players the the leaderboards
		objects.leaders.addScore(playerOne.getName(), LeaderBoard.FULLGAME, playerOne.getScore());
		
		if (loser != null){
			objects.leaders.addScore(playerTwo.getName(), LeaderBoard.FULLGAME, playerTwo.getScore());
			
		System.out.println("commiserations " + loser.getName() + " and of course well done " + winner.getName() + ".");
		}
		
	}
	
	public void load() {

		System.out.println("Hey :) You have reached non code! Well done you\n");
		System.out.println("So what does this mean for you?");
		System.out.println("That the option you selected is not complete, you should give up and turn around!");
		System.out.println("Goodbye: useless method");
	}

	private void save() {

		System.out.println("Hey :) You have reached non code! Well done you\n");
		System.out.println("So what does this mean for you?");
		System.out.println("That the option you selected is not complete, you should give up and turn around!");
		System.out.println("Goodbye: useless method");
	}
	
	public void playCustomGame(){
		
//		System.out.println("Hey :) You have reached non code! Well done you\n");
//		System.out.println("So what does this mean for you?");
//		System.out.println("That the option you selected is not complete, you should give up and turn around!");
//		System.out.println("Goodbye: useless method");
//		
//		return;
		
		io.printLines(2);
		
		System.out.println("So a custom game is it?");
		
		boolean unvalid;
		
		do {
			
			unvalid = false;
			
			System.out.println("\nPlease enter a string of type awesome :");
			System.out.println("I may have lied sorry. Please enter a string of rounds you would like to play");
			System.out.println("W for word, N for number, C for conundrum. For example: WWNNWC");
			
			gameorder = io.getString().toUpperCase().toCharArray();
			
			for ( int i = 0; i < gameorder.length; i++){
				
				char c = gameorder[i];
				
				if ( !(c == WORD || c == NUMBER || c == CONUNDRUM) ){
					
					System.out.println("Error!!!!!! try again!!!\n");
					unvalid = true;
					break;
				}
			}
					
		}while(unvalid);
		
		//tell player if length is silly maybe?
		
		play(0);
		
	}

}
