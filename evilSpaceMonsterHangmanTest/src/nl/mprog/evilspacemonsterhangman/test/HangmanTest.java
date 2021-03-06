package nl.mprog.evilspacemonsterhangman.test;

import android.test.InstrumentationTestCase;
import nl.mprog.evilspacemonsterhangman.models.*;

/**
 * Created by steven on 6/26/13.
 */
public class HangmanTest extends InstrumentationTestCase {
	private Hangman hangman;
	
    public HangmanTest(String name) {
        super();
        setName(name);
    }

    protected void setUp() {
        getInstrumentation();
        hangman = new Hangman(5, 8);
        hangman.setCurrentWord("hallo");
    }

    protected void runTest() {
    	int letter = (int) 'h';
    	
    	if(hangman.doUserInput(letter) == hangman.CORRECT_GUESS) {
    		String currentWordState = hangman.getCurrentWordState();
    		if(!currentWordState.equals("h____")) {
        		fail("object not working as expected " + currentWordState);
        	}
    	}else {
    		fail("unexpected userinput error");
    	}
    	
    	// test to make sure the user input acts correctly
    	letter = (int) '1';
    	int gameState = hangman.doUserInput(letter);
    	if(gameState != hangman.INVALID_INPUT) {
    		fail(letter + " should return invalid input, returned " + gameState);
    	}
    }

    public void testInstrumentation() {

    }
}
