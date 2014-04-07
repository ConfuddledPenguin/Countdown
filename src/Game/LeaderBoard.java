package Game;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoard {

	private ArrayList<Score> scores;			// Holds all of the scores for what ever reason
	private ArrayList<Score> scoresFull;		// Holds the full game score
	private ArrayList<Score> scoresNumber;		// Holds the Number round scores
	private ArrayList<Score> scoresWord;		// Holds the Word round scores
	private ArrayList<Score> scoresConundrum;	// Holds the Conundrum round scores

	/**
	 * A small class to store the score details 
	 */
	private class Score implements Comparable<Score>{
		
		String name;
		String round;
		int score;
		
		public Score(String p, String r, int score){
			
			this.name = p;
			this.round = r;
			this.score = score;
		}

		@Override
		public int compareTo(Score s) {
			
			final int BEFORE = -1;
		    final int EQUAL = 0;
		    final int AFTER = 1;
		    
		    if (score == s.score) return EQUAL;
		    else if (score < s.score) return BEFORE;
		    else return AFTER;
		}
		
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
	 * @param p the player
	 * @param r The round
	 * @param s The score
	 */
	public void addScore(String player, String round, int points) {

		Score score = new Score(player, round, points);
		scores.add(score);
		
		Collections.sort(scores);
	}	

}
