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
		}
		
	}

	private void printWelcome(){
		
		System.out.println("----------------------------------------------------");
		System.out.println("----------------------------------------------------");
		System.out.println("Hello and welcome to countdown");
		System.out.println("---------------------------\n");
		
	}
	
	public void load() {



	}

	private void save() {



	}

}
