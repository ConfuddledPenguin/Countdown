package Game;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoard {

	private ArrayList<Score> scores;			// Holds all of the scores for what ever reason
	private ArrayList<Score> scoresFull;		// Holds the full game score
	private ArrayList<Score> scoresNumber;		// Holds the Number round scores
	private ArrayList<Score> scoresWord;		// Holds the Word round scores
	private ArrayList<Score> scoresConundrum;	// Holds the Conundrum round scores

	public static final String WORDROUND = "WordRound";
    public static final String NUMBERROUND = "NumberRound";
    public static final String CONUNDRUMROUND = "ConundrumRound";
    public static final String FULLGAME = "FullGame";
	
	/**
	 * A small class to store the score details 
	 */
	private class Score implements Comparable<Score>{
		
		String name;
		String round;
		int score;
		
		/**
		 * The constructor for score
		 * 
		 * @param p the player
		 * @param r the round
		 * @param score the score
		 */
		public Score(String p, String r, int score){
			
			this.name = p;
			this.round = r;
			this.score = score;
		}

		/**
		 * Need to compare scores against themselves
		 */
		@Override
		public int compareTo(Score s) {
			
			final int BEFORE = -1;
		    final int EQUAL = 0;
		    final int AFTER = 1;
		    
		    if (score == s.score) return EQUAL;
		    else if (score < s.score) return BEFORE;
		    else return AFTER;
		}
		
		/**
		 * A quick way to print the score
		 */
		@Override
		public String toString(){
			return (name + "\t\t: " + round + "\t\t: " + score);
			
		}
	}
	
	/**
	 * The constructor for the LeaderBoard
	 */
	public LeaderBoard() {
		
		scores			= new ArrayList<Score>();
		scoresFull 		= new ArrayList<Score>();
		scoresNumber 	= new ArrayList<Score>();
		scoresWord 		= new ArrayList<Score>();
		scoresConundrum = new ArrayList<Score>();
		loadScores();
	}


	private void loadScores(){
		
	}

	public void saveScores() {


	}

	/**
	 * Prints out all of the scores
	 */
	private void printScores(ArrayList<Score> list) {

		System.out.println("Name\t\t: Round\t\t: Score\n");
		
		for(Score s: list) {

			System.out.println(s);

		}

	}
	
	/**
	 * Prints out all of the full game scores
	 */
	public void printScoresFull() {

		printScores(scoresFull);
	}
	
	/**
	 * Prints out all of the word scores
	 */
	public void printScoresWord() {

		printScores(scoresWord);
	}
	
	/**
	 * Prints out all of the conundrum scores
	 */
	public void printScoresConundrum() {

		printScores(scoresConundrum);
	}
	
	/**
	 * Prints out all of the number round scores
	 */
	public void printScoresNumber() {

		printScores(scoresNumber);
	}
	
	/**
	 * Prints out all of the scores
	 */
	public void printScores() {

		printScores(scores);
	}
	
	/**
	 * Adds a score to the leaderboard.
	 * 
	 * @param player the player
	 * @param round The round
	 * @param points The score
	 */
	public void addScore(String player, String round, int points) throws IllegalArgumentException {

		//Create a new score object
		Score score = new Score(player, round, points);
		
		
		//Add it to its special LeaderBoard
		if ( round.equals(NUMBERROUND))
			addScoreHelper(scoresNumber, score);
		
		else if (round.equals(WORDROUND))
			addScoreHelper(scoresWord, score);
		
		else if (round.equals(CONUNDRUMROUND))
			addScoreHelper(scoresConundrum, score);
		
		else if (round.equals(FULLGAME))
			addScoreHelper(scoresFull, score);
		
		else 
			throw new IllegalArgumentException("Not a valid round type");
		
		
		//Finally add to the general leaderBoards
		addScoreHelper(scores, score);
	}	
	
	/**
	 * Adds the score to the leaderboard to reduce LOC
	 * 
	 * @param board The board
	 * @param score The score
	 */
	private void addScoreHelper(ArrayList<Score> board, Score score){
		
		board.add(score);
		Collections.sort(board);
		
		//Let user know if they are at the top of the leaderboard.
		if (board.get(0).equals(score)){
			
			System.out.println("Hey, your at the top of the leaderBoard for " + score.round + ". Well done you!");
		}
	}

}
