package nl.mprog.evilspacemonsterhangman.test;


import java.util.Set;

import android.test.InstrumentationTestCase;
import android.util.Log;
import nl.mprog.evilspacemonsterhangman.models.*;


/**
 * Created by steven on 6/26/13.
 */
public class SpaceMonsterTest extends InstrumentationTestCase {
	private SpaceMonster spaceMonster;
	
	/*
	 * ask question about setWordList(), since it
	 * is not able to change the wordList once inserted
	 */
    public SpaceMonsterTest(String name) {
        super();
        setName(name);
    }

    protected void setUp() {
        spaceMonster = new SpaceMonster();
    }

    protected void runTest() {
    	Set<Character> set = spaceMonster.getAvailableLetters();
    	// check if the set actually contains the lowercase alphabet
    	assertTrue(set.contains('a'));
    	assertTrue(set.contains('z'));
    	assertFalse(set.contains('0'));
    	
    	// i don't really see how i can test the rest since it's
    	// pseudo random
    	
    	// i'll just log it and look at it i guess
    	char[] array = spaceMonster.randomChosenLetters();
    	for(char c : array) {
			String str = String.valueOf(c);
			Log.d("SpaceMonsterTest", str);
		}
    }

    public void testInstrumentation() {

    }
}
