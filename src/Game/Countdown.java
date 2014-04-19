package Game;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	private String gameOrderString;
	private UserIO io;
	private boolean customGame;
	
	private static char WORD = 'W';
	private static char NUMBER = 'N';
	private static char CONUNDRUM = 'C';
	
	private long timestamp;

	/**
	 * The constructor for the countdown class. Takes a GameObjects object
	 * @param go The GameObjects
	 */
	public Countdown(GameObjects go) {

		this.objects = go;
		playerOne = go.pOne;
		playerTwo = go.pTwo;
		
		io = new UserIO();
		
		timestamp = System.nanoTime();
		
	}

	/**
	 * Play a full game of countdown
	 */
	public void play() {

		//Weird way of assigning array is due to values needing to be added when declaring the array;
		char[] defualt = {'W','W','N','W','W','W','W','N','N','W','W','W','W','N','C'};
		gameOrderString = "WWNWWWWNNWWWWNC";
		gameorder = defualt;
		defualt = null;
		
		customGame = false;
		
		play(0);
	}
	
	/**
	 * Plays the game from a starting pos
	 * 
	 * @param start The round number to start from
	 */
	private void play(int start){
		
		//Say hi :)
		printWelcome();
		
		//Loop for the number of rounds i.e 15
		int roundNo = start;
		while ( roundNo < gameorder.length){
			
			//Tempted to change the below to a list of some sort and use contains . . . .
			
			// if word round
			if ( gameorder[roundNo] == WORD ){
				
				//Let the player know what is going on
				if (roundNo != 0)
					System.out.println("Next up we have a word round");
				else if (roundNo == gameorder.length)
					System.out.println("Sadly we are nearing the end\nwith just a word round left to play");
				else
					System.out.println("The first round, as always, is a word round");
				
				currentRound = new WordRound(objects);
				currentRound.play();
			}
			
			// If its a numbers round
			if ( gameorder[roundNo] == NUMBER ){
				
				//Let the player know what is going on
				if (roundNo != 0)
					System.out.println("Next up we have a number round");
				else if (roundNo == gameorder.length)
					System.out.println("Sadly we are nearing the end\nwith just a number round left to play");
				else
					System.out.println("The first round, is a number round");
				
				currentRound = new  NumberRound(objects);
				currentRound.play();
			}
			
			//If its a conundrum round
			if (gameorder[roundNo] == CONUNDRUM){
				
				//Let the player know what is going on
				if (roundNo != 0)
					System.out.println("Next up we have a conundrum round");
				else if (roundNo == gameorder.length)
					System.out.println("Sadly we are nearing the end.\nWhich of course means we are going to play\n" +
				" a coundrumround");
				else
					System.out.println("The first round, is a conundrum round");
				
				currentRound = new ConundrumRound(objects);
				currentRound.play();
			}
			
			/*
			 * Autosave the game :)
			 * 
			 * Incremented roundNo is passed as it should save the roundNo of the next round not the one
			 * that has just been played.
			 */
			save(++roundNo);
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
		
		//Add players to the leaderboards
		
		String gametypeString;
		
		if (!customGame)
			gametypeString = LeaderBoard.FULLGAME;
		else
			gametypeString = LeaderBoard.CUSTOMGAME;
				
		
		objects.leaders.addScore(playerOne.getName(), gametypeString, playerOne.getScore());
		
		if (loser != null){
			objects.leaders.addScore(playerTwo.getName(), gametypeString, playerTwo.getScore());
			System.out.println("commiserations " + loser.getName() + " and of course well done " + winner.getName() + ".");
		}
		
	}
	
	/**
	 * Loads the game
	 */
	public void load() {

		System.out.println("Hey :) You have reached non code! Well done you\n");
		System.out.println("So what does this mean for you?");
		System.out.println("That the option you selected is not complete, you should give up and turn around!");
		System.out.println("Goodbye: useless method");
		
		
		try{
			
			File xmlFile = new File("leaderboard.xml");
			DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFac.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			
			//Quick sanity formating. Should be fine unless someone has been playing with the file . . .
			doc.getDocumentElement().normalize();
			
			//LOAD IT HERE
			
			
		} catch (Exception e){
			System.err.println("The savefile has become corrupted. I hope you had backups");
		}
	}

	/**
	 * Saves the game
	 */
	private void save(int roundNoValue) {

		Element save = null;
		
		try {
			
			//create tools
			String filepath = "saves.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			
			Node root = doc.getFirstChild();
			
			NodeList saves = root.getChildNodes();
			
			//Check if save of this game already exists
			boolean found = false;
			for (int i = 0; i < saves.getLength(); i++){
				
				Node temp = saves.item(i);
				
				if ( temp instanceof Element){
					save = (Element) temp;
					
					if (save.getAttribute("id").equals("" + timestamp)){
						found = true;
						break;
					}
				}
			}//close save finder
			
			if (found){	//If save exits, update existing save
				
				NodeList saveDataNodes = save.getChildNodes();
				
				//Update player info
				
				/*
				 * If player two exists then the gamedata will be in position 4 of the list
				 * otherwise it will be in position 5. So while updating the player
				 * we shall calc the position while updating player
				 * 
				 * The weird positioning is due to the DOM model seeing text between the nodes
				 */
				int pos = 3;
				
				playerUpdater(saveDataNodes, playerOne);
				
				if (playerTwo != null){
					playerUpdater(saveDataNodes, playerTwo);
					pos = 5;
				}
				
				
				//Next update the game data
				Node gameData = saveDataNodes.item(pos);

				NodeList gameDataNodes = gameData.getChildNodes();
				
				//Loop through them until value to be updated is found
				for (int j = 0; j < gameDataNodes.getLength(); j++){
					
					Node current = gameDataNodes.item(j);
					
					if (current.getNodeName().equals("roundNo"))
						current.setTextContent("" + roundNoValue);
				}
				
				
			}else{ // If save doesn't exist create a new one
				
				//create save
				save = doc.createElement("save");
				save.setAttribute("id", "" + timestamp);
				
	
				//save the players
				playerSaver(doc, save, playerOne);
				if (playerTwo != null)
					playerSaver(doc, save, playerTwo);
				
				
				//save the game details
				Element game = doc.createElement("game");
				
					Element roundString = doc.createElement("roundString");
					roundString.appendChild(doc.createTextNode(gameOrderString));
					game.appendChild(roundString);
				
					Element roundNo = doc.createElement("roundNo");
					roundNo.appendChild(doc.createTextNode("" + roundNoValue));
					game.appendChild(roundNo);
				
					Element customeGameElement = doc.createElement("customGame");
					if (customGame)
						customeGameElement.appendChild(doc.createTextNode("TRUE"));
					else
						customeGameElement.appendChild(doc.createTextNode("FALSE"));
					game.appendChild(customeGameElement);
				
				save.appendChild(game);	
				
				root.appendChild(save);
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			if (source == null)
				System.out.println("FUCK!");
			else System.out.println("All good");
			
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
			
		} catch (Exception e){
			System.out.println("The save file has become corrupted! Delete it! I hope you back things up");
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the players score in DOM for writing to disk
	 * 
	 * @param saveDataNodes The nodes of save
	 * @param p The player
	 */
	private void playerUpdater(NodeList saveDataNodes, Player p){
		
		//Grab the nodes in player
		NodeList playerDataNodes = saveDataNodes.item(p.getNumber() -1).getChildNodes();
		
		//Loop through them until value to be updated is found
		for (int j = 0; j < playerDataNodes.getLength(); j++){
			
			Node current = playerDataNodes.item(j);
			
			if (current.getNodeName().equals("score"))
				current.setTextContent("" + p.getScore());
		}
	}
	
	/**
	 * A small helper method for the save method. This saves the 
	 * Player to the DOM, for writing to disk.
	 * 
	 * @param doc The Document
	 * @param save The save Element
	 * @param p the Player to be saved
	 */
	private void playerSaver(Document doc, Element save, Player p){
		
		Element player = doc.createElement("player");
		
			Element number = doc.createElement("number");
			number.appendChild(doc.createTextNode("" + p.getNumber()));
			player.appendChild(number);
			
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(p.getName()));
			player.appendChild(name);
			
			Element score = doc.createElement("score");
			score.appendChild(doc.createTextNode("" + p.getScore()));
			player.appendChild(score);
		
		save.appendChild(player);
	}
	
	/**
	 * Allows the playing of a custom game of countdown
	 * In this game type the user specifies which rounds
	 * they would like to play
	 */
	public void playCustomGame(){
		
		io.printLines(2);
		customGame = true;
		
		System.out.println("So a custom game, is it?");
		
		boolean unvalid;
		
		do {
			
			unvalid = false;
			
			System.out.println("\nPlease enter a string of type awesome :");
			System.out.println("I may have lied sorry. Please enter a string of rounds you would like to play");
			System.out.println("W for word, N for number, C for conundrum. For example: WWNNWC");
			
			gameOrderString = io.getString().toUpperCase();
			gameorder = gameOrderString.toCharArray();
			
			for ( int i = 0; i < gameorder.length; i++){
				
				char c = gameorder[i];
				
				if ( !(c == WORD || c == NUMBER || c == CONUNDRUM) ){
					
					System.out.println("Error!!!!!! try again!!!\n");
					unvalid = true;
					break;
				}
			}
					
		}while(unvalid);
		
		play(0);
		
	}

}