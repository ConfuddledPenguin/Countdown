package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {
	
	public class Node {
        private char letter;
        private ArrayList<String> words = new ArrayList<String>();
        private ArrayList<Node> children = new ArrayList<Node>();
    }
	
    private Node root;

    public Tree() {
        root = new Node();
    }
    
    public ArrayList<String> findbestWord(String lettersString){
    	
//    	ArrayList<String> bestwords = new ArrayList<String>();
    	char[] letters = lettersString.toCharArray();
    	Arrays.sort(letters);
    	Node currentNode = root;
    	
    	for (int i = 0; i < letters.length; i++){
    		
    		boolean found = false;
    		
    		if (!currentNode.children.isEmpty()){
    			
	    		// find the node for the letter
	    		for(Node n: currentNode.children){
	    			
	    			if ( n.letter == letters[i]){
	    				found = true;
	    				currentNode = n;
	    			}
	    		} // close node finding
    		} else {
    			System.out.println("No match");
    		}
    		
    		if (!found){
    			System.out.println("No match");
    		}
    	}
    	
    	return currentNode.words;
    }
    
    public void addWord(String wordString){
    	
    	Node currentNode = root;
    	char[] word = wordString.toCharArray();
    	Arrays.sort(word);
    	
    	// node search for each letter
    	for (int i = 0; i < word.length; i++){
    		
    		boolean found = false;
    		
    		if (!currentNode.children.isEmpty()){
    			
	    		// find the node for the letter
	    		for(Node n: currentNode.children){
	    			
	    			if ( n.letter == word[i]){
	    				found = true;
	    				currentNode = n;
	    			}
	    		} // close node finding
    		}
    		
    		// if not found
    		if (!found){
    			
    			Node n = new Node();
    			n.letter = word[i];
    			currentNode.children.add(n);
    			currentNode = n;
    		}
    	} // close node search
    	
    	currentNode.words.add(wordString);
//    	System.out.println("added: " + wordString);
    }
}