package Game;

//All of the wonderful imports . . .
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

//yeah more of them . . .
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//and some more . . .
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* I wanted to learn how to read/write XML files so I saw this as a chance to do so.
 * Sowwie that it got a bit long
 */

/**
 * The leaderBoard
 */
public class LeaderBoard {

	private ArrayList<Score> scoresFull;		// Holds the full game scores
	private ArrayList<Score> scoresNumber;		// Holds the Number round scores
	private ArrayList<Score> scoresWord;		// Holds the Word round scores
	private ArrayList<Score> scoresConundrum;	// Holds the Conundrum round scores
	private ArrayList<Score> scoresCustom;		// Holds the custom game scores

	/*
	 * A bunch of constants to be used for the round type.
	 * Also includes fullgame which is why this is not included in
	 * the round subclasses. It would make little sense for it to
	 * be there
	 */
	public static final String WORDROUND = "WordRound";
    public static final String NUMBERROUND = "NumberRound";
    public static final String CONUNDRUMROUND = "ConundrumRound";
    public static final String FULLGAME = "FullGame";
    public static final String CUSTOMGAME = "CustomGame";
	
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
		    else if (score < s.score) return AFTER;
		    else return BEFORE;
		}
		
		/**
		 * A quick way to print the score
		 */
		@Override
		public String toString(){
			return (name + "\t\t: " + round + "\t\t: " + score);
			
		}
	} // Close score class
	
	/**
	 * The constructor for the LeaderBoard
	 */
	public LeaderBoard() {
		
		scoresFull 		= new ArrayList<Score>();
		scoresCustom	= new ArrayList<Score>();
		scoresNumber 	= new ArrayList<Score>();
		scoresWord 		= new ArrayList<Score>();
		scoresConundrum = new ArrayList<Score>();
		
		loadScores();
	}

	/**
	 * Loads the scores stored in an xml file.
	 */
	private void loadScores(){
		
		try{
			
			File xmlFile = new File("leaderboard.xml");
			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFac.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			
			//Quick sanity formating. Should be fine unless someone has been playing with the file . . .
			doc.getDocumentElement().normalize();
			
			readhelper(doc, WORDROUND, scoresWord);
			readhelper(doc, NUMBERROUND, scoresNumber);
			readhelper(doc, CONUNDRUMROUND, scoresConundrum);
			readhelper(doc, FULLGAME, scoresFull);
			readhelper(doc, CUSTOMGAME, scoresCustom);
			
		} catch (Exception e){
			System.out.println("Error loading leaderboard file: sorry");
		}
	}
	
	/**
	 * Reads the values from the DOM tree created in loadScores.
	 * 
	 * @param doc The document
	 * @param round the roundtype to be loaded
	 * @param scores the ArrayList<Scores> to put the loaded scores into
	 */
	private void readhelper(Document doc, String round, ArrayList<Score> scores){
		
		NodeList nl = doc.getElementsByTagName(round);

		//If there are child nodes, get the nodes else return empty handed
		if (nl.item(0).hasChildNodes()){
			 nl = nl.item(0).getChildNodes();
		} else {
			return;
		}
		
		//Loop for the child nodes getting the data
		for (int i = 0; i < nl.getLength(); i++){
			
			Node n = nl.item(i);
			
			if ( n.getNodeType() == Node.ELEMENT_NODE){
				
				Element e = (Element) n;
				
				Score score = new Score(
						e.getElementsByTagName("name").item(0).getTextContent(),
						e.getElementsByTagName("round").item(0).getTextContent(),
						Integer.parseInt(e.getElementsByTagName("points").item(0).getTextContent())
						);
				
				scores.add(score);
			}
		}
		
		Collections.sort(scores);
	}

	/**
	 * Saves the scores stored in the leaderboard
	 */
	public void saveScores() {

		try {
			
			//create tools
			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFac.newDocumentBuilder();
			
			//create doc
			Document doc = docBuilder.newDocument();
			
			//root
			Element rootElem = doc.createElement("leaderboard");
			doc.appendChild(rootElem);
			

			//Write all the scores
			writehelper(doc, rootElem, scoresFull, FULLGAME);
			writehelper(doc, rootElem, scoresWord, WORDROUND);
			writehelper(doc, rootElem, scoresNumber, NUMBERROUND);
			writehelper(doc, rootElem, scoresConundrum, CONUNDRUMROUND);
			writehelper(doc, rootElem, scoresCustom, CUSTOMGAME);
			
			
			//write file
			TransformerFactory tranFact = TransformerFactory.newInstance();
			Transformer transformer = tranFact.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("leaderboard.xml"));
			transformer.transform(source, result);
			
			
		} catch (Exception e){
			System.out.println("Error writing leaderboard file. Sorry about that");
		}

	}
	
	/**
	 * This method writes the contents of an arraylist<Score> to the DOM
	 * 
	 * @param doc The document
	 * @param rootElem the root element of the doc
	 * @param scores the arrayList of scores
	 * @param subrootName the name to give the added subroot
	 */
	private void writehelper(Document doc, Element rootElem, ArrayList<Score> scores, String subrootName){
		
		Element subroot = doc.createElement(subrootName);
		rootElem.appendChild(subroot);
		
		if (scores.isEmpty())
			return;
		
		for(Score s: scores){
			
			Element score = doc.createElement("score");
			subroot.appendChild(score);
			
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(s.name));
			score.appendChild(name);
			
			Element round = doc.createElement("round");
			round.appendChild(doc.createTextNode(s.round));
			score.appendChild(round);
			
			Element points = doc.createElement("points");
			points.appendChild(doc.createTextNode(String.valueOf(s.score)));
			score.appendChild(points);
		}
	}

	/**
	 * Prints out all of the scores to the terminal
	 */
	private void printScores(ArrayList<Score> list) {

		UserIO io = new UserIO();
		io.printLines(1);
		
		if (list.isEmpty()){
			System.out.println("hmm no one has made any high scores, I would say why don't you try but i doubt you could . . .");
			return;
		}
		
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
	 * Prints out all of the scores for the custom games
	 */
	public void printScoresCustom() {
		
		printScores(scoresCustom);
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

		ArrayList<Score> scores = new ArrayList<>();
		
		scores.addAll(scoresConundrum);
		scores.addAll(scoresFull);
		scores.addAll(scoresNumber);
		scores.addAll(scoresWord);
		
		Collections.sort(scores);
		
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
		
		else if (round.equals(CUSTOMGAME))
			addScoreHelper(scoresCustom, score);
		
		else 
			throw new IllegalArgumentException("Not a valid round type");
		
	}	
	
	/**
	 * Adds the score to the leaderboard
	 * Created to reduce LOc in addScore above
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