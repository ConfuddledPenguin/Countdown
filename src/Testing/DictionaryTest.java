package Testing;

import static org.junit.Assert.*;

import java.sql.Array;
import java.util.ArrayList;

import org.junit.Test;

import Game.Dictionary;

public class DictionaryTest {

	@Test
	public void testGetBestWord() {
		
		Dictionary test = new Dictionary();
		
		System.out.println("abc");
		ArrayList<String> temp = test.getBestWords("abc");
		System.out.println(temp.size());
		for (String s: temp){
			System.out.println(s);
		}
		System.out.println(temp.get(0));
//		assertTrue("cab" == temp.get(0));
		System.out.println();
		
		System.out.println("cba");
		temp = test.getBestWords("cba");
		for (String s: temp){
			System.out.println(s);
		}
		System.out.println();
		
		for (int i = 0; i < 10; i++){
			String word = test.getAnagram();
			System.out.println(word);
			temp = test.getBestWords(word);
			for (String s: temp){
				System.out.println(s);
			}
			System.out.println();
		}
		
		System.out.println("iasifelxa");
		temp = test.getBestWords("iasifelxa");
		System.out.println(temp.size());
		for (String s: temp){
			System.out.println(s);
		}
	}

	@Test
	public void testCheckWord() {

		Dictionary test = new Dictionary();
		
		assertEquals("Should be true", true, test.checkWord("fish"));
		assertEquals("Should be true", true, test.checkWord("cheese"));
		assertEquals("Should be true", true, test.checkWord("apple"));
		assertEquals("Should be true", true, test.checkWord("fishes"));
		
		assertEquals("Should be false", false, test.checkWord("aaaaa"));
		assertEquals("Should be false", false, test.checkWord("fhdsheid"));
		
		assertEquals("Should be false", false, test.checkWord("aaaaaaaaaa"));
		assertEquals("Should be false", false, test.checkWord(""));
	}

	@Test
	public void testGetAnagram() {
		
		Dictionary test = new Dictionary();
		
		//Impossible to test
		test.getAnagram();
	}

}
