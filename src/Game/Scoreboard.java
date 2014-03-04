package Game;

import java.util.LinkedList;

public class Scoreboard {

	private LinkedList<String> scores;

	public Scoreboard() {

		scores = new LinkedList<String>();

	}

	public void loadScores() {

		scores.add("Thomas");

	} 

	public void saveScores() {



	}

	public void viewScores() {

		for(int i = 0; i < scores.size(); i++) {

			System.out.println(scores.get(0));

		}

	}

	public void addScores() {



	}	

}
