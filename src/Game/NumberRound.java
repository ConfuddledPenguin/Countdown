package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * The number round of the countdown game
 *
 */
public class NumberRound extends Round{

	private int ans1;
	private int ans2;
	private String working1;
	private String working2;
	private int target;
	private ArrayList<Integer> numbers;

	private UserIO i;

	public enum returnValues {
		TRUE, FALSE, ERROR
	}

	/**
	 * The constructor for a number round
	 * 
	 * @param dict The dictionary file
	 * @param pOne Player one
	 * @param pTwo Player two if they exist
	 */
	public NumberRound(Dictionary dict, Player pOne, Player pTwo, LeaderBoard board ) {

		super(dict, pOne, pTwo, board, LeaderBoard.NUMBERROUND);
		i = new UserIO();
	}

	/**
	 * A constructor for the number round
	 * @param o A gameObjects object.
	 */
	public NumberRound(GameObjects o) {

		this(o.dict, o.pOne, o.pTwo, o.leaders);
	}

	/**
	 * Plays a round of countdown;
	 */
	public void play() {

		printWelcome();

		//Generate numbers
		generateNumbers();
		System.out.print("The numbers are ");

		for(Integer n: numbers){
			System.out.print(n + " ");
		}
		System.out.println();

		//Generate target number
		Random r = new Random();
		target = r.nextInt(1000);
		r = null;
		System.out.println("The target is : " + target);
		System.out.println();


		//Get the answer for player one
		getAnswer(pOne);

		//Get the answer for player two if they exist
		if (pTwo != null){

			getAnswer(pTwo);

			if ( ans1 == ans2)
				System.out.println("You both have the same number, but lets see if the working is right");
			else if ( Math.abs(target - ans1) < Math.abs(target - ans2))
				System.out.println(pOne.getName() + " is closer, but lets see if the working is right\n");
			else
				System.out.println(pTwo.getName() + " is closer, but lets see if the working is right\n");
		}


		//Get the working for player one
		getWorking(pOne);
		returnValues val;
		while ( (val = checkWorking( ans1, working1)) == returnValues.ERROR){
			getWorking(pOne);
		}

		if ( val == returnValues.TRUE )
			calulatePoints(pOne);
		else
			System.out.println("Sorry " + pOne.getName() + " you working is wrong");

		//Get the working for player two if they exist
		if (pTwo != null){

			getWorking(pTwo);
			while ( (val = checkWorking( ans2, working2)) == returnValues.ERROR){
				getWorking(pTwo);
			}
			if ( val == returnValues.TRUE)
				calulatePoints(pTwo);
			else
				System.out.println("Sorry " + pTwo.getName() + " you working is wrong");
		}

	}

	/**
	 * Find the best solution
	 */
	private void bestAnswer(){

	}

	/**
	 * Generate the number for the game
	 */
	private void generateNumbers(){

		int[] high = {25, 50, 75, 100};
		int[] low = new int[20];
		numbers = new ArrayList<>(); 
		int noHigh;
		Random r = new Random();

		//fill the low array
		for (int i = 0; i < 10; i++){
			low[i] = i;
			low[19-i] = i;
		}

		do {

			System.out.println("Please enter the number of high number you would like: ");
			noHigh = i.getNumber();
		}while(noHigh > 4 || noHigh < 0);

		for(int i = 0; i < noHigh; i++){

			numbers.add(high[r.nextInt(4)]);
		}

		for(int i = 0; i < 6 - noHigh; i++){

			numbers.add(low[r.nextInt(20)]);
		}

		r = null;
		Collections.shuffle(numbers);	
	}

	/**
	 * Calculate a players points
	 * @param p The player the score is being calculated
	 */
	private void calulatePoints(Player p) {

		int temp;

		if(p.getNumber() == 1)
			temp = Math.abs(target - ans1);
		else
			temp = Math.abs(target - ans2);

		if (temp == 0){

			System.out.println(p.getName() + " Got it perfectly. Is awarded 10 points");
			board.addScore(p.getName(), roundType, 10);
			p.updateScore(10);
		} else if ( temp < 5 ){ 

			System.out.println(p.getName() + " got within 5 and is awarded 7 points");
			board.addScore(p.getName(), roundType, 7);
			p.updateScore(7);
		} else if ( temp < 10){ 

			System.out.println(p.getName() + " got within 10 and is awarded 5 points");
			board.addScore(p.getName(), roundType, 5);
			p.updateScore(5);
		}else {

			System.out.println("Nice try " + p.getName() + " but no points");
			board.addScore(p.getName(), roundType, 0);
		}
	}

	/**
	 * Check a players working
	 * @param ans The answer
	 * @param working The working
	 * @return true if the working is correct, false otherwise
	 */
	private returnValues checkWorking(int ans, String working){

		@SuppressWarnings("unchecked")
		ArrayList<Integer> numberClone = (ArrayList<Integer>) numbers.clone();
		ArrayList<String> operators = new ArrayList<String>();
		operators.add("+"); operators.add("-"); operators.add("*"); operators.add("/");

		System.out.println("Players working: " + working);

		int tempAns = 0;

		StringTokenizer st;

		try {

			st = new StringTokenizer(working);

			while(st.hasMoreTokens()) {

				String token = st.nextToken();

				if(operators.contains(token)) { //current token is an operator

					Integer token2 = Integer.valueOf(st.nextToken()); //get next number
					
					if(numberClone.contains(token2)) {
						
						tempAns = calculate(tempAns, token, token2);
						numberClone.remove(token2);
						
					}

				} else if(numberClone.contains(Integer.valueOf(token))) {
					
					tempAns = Integer.valueOf(token);
					
				} else {
					
					System.out.println("Your working contains an invalid number");
					break;
					
				}
				
				if(tempAns == -1)
					break;
				
				numberClone.remove(token);

			}

		} catch(Exception e) {

			System.err.println("\nDid you remember to enter spaces between the tokens that you entered?");
			return returnValues.ERROR;

		}

		if(tempAns == ans)
			return returnValues.TRUE;
		
		else
			return returnValues.FALSE;
		
	}
	
	/**
	 * Check operator and carry out relevant calculation
	 * @param answer
	 * @param operator
	 * @param token
	 * @return
	 */
	private int calculate(int answer, String operator, int token) {

		if(operator.equals("+"))
			return answer + token;
		else if(operator.equals("*"))
			return answer * token;
		else if(operator.equals("-"))
			return answer - token;
		else if(operator.equals("/"))
			return answer / token;
		else {
			System.out.println("something has gone tragically wrong");
			return -1;
		}
	}

	/**
	 * Get an answer from the player
	 * @param p The player the answer is from
	 */
	private void getAnswer(Player p) {

		System.out.println(p.getName() + " enter your answer: ");

		if (p.getNumber() == 1)
			ans1 = i.getNumber();
		else
			ans2 = i.getNumber();

		System.out.println();
	}

	/**
	 * Get the working from a player
	 * @param p The layer the working is from
	 */
	private void getWorking(Player p){

		System.out.println(p.getName() + " using + - * / please enter your working: ");

		if (p.getNumber() == 1)
			working1 = i.getString();
		else
			working2 = i.getString();	

		System.out.println();
	}

}
