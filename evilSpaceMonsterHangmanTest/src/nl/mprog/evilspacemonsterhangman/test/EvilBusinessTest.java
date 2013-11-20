package nl.mprog.evilspacemonsterhangman.test;

import java.util.ArrayList;
import java.util.List;

import android.test.InstrumentationTestCase;
import nl.mprog.evilspacemonsterhangman.models.*;

/**
 * Created by steven on 6/26/13.
 */
public class EvilBusinessTest extends InstrumentationTestCase {
	private EvilHangman evilHangman;
	List<String> wordList;
    public EvilBusinessTest(String name) {
        super();
        setName(name);
    }

    protected void setUp() {
        getInstrumentation();
        wordList = new ArrayList<String>();
        wordList.add(0, "jaaho");
        wordList.add(1, "baase");
        wordList.add(2, "mande");
        wordList.add(3, "pteii");
        wordList.add(4, "pompe");
        wordList.add(4, "saass");
        evilHangman = new EvilHangman(5, 8);
        evilHangman.setWordList(wordList);
    }

    protected void runTest() {
    	int key = (int) 'a';
    	evilHangman.setWordList(wordList);
    	evilHangman.evilBusiness(key);
    	assertEquals("_aa__", evilHangman.getCurrentWordState());
    }

    public void testInstrumentation() {

    }
}