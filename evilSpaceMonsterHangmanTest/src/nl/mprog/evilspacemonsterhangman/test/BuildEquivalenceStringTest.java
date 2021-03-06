package nl.mprog.evilspacemonsterhangman.test;

import java.util.ArrayList;
import java.util.List;

import android.test.InstrumentationTestCase;
import nl.mprog.evilspacemonsterhangman.models.*;

/**
 * Created by steven on 6/26/13.
 */
public class BuildEquivalenceStringTest extends InstrumentationTestCase {
	private EvilHangman evilHangman;
	List<String> wordList;
    public BuildEquivalenceStringTest(String name) {
        super();
        setName(name);
    }

    protected void setUp() {
        getInstrumentation();
        wordList = new ArrayList<String>();
        wordList.add(0, "AAAAA");
        wordList.add(1, "ACCCA");
        wordList.add(2, "KAKAA");
        evilHangman = new EvilHangman(5, 8);
        evilHangman.setWordList(wordList);
    }

    protected void runTest() {
    	int key = (int) 'A';
    	
    	for(String word : wordList) {
    		word = evilHangman.buildEquivalenceString(word, key);
    	}
    	
    	// check if the equivilance strings are actually built as expected
    	assertEquals("AAAAA", wordList.get(0));
    	assertEquals("A___A", wordList.get(1));
    	assertEquals("_A_AA", wordList.get(2));
    	
    }

    public void testInstrumentation() {

    }
}
