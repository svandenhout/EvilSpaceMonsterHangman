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
	List<String> wordList2;
	
	/*
	 * ask question about setWordList(), since it
	 * is not able to change the wordList once inserted
	 */
    public EvilBusinessTest(String name) {
        super();
        setName(name);
    }

    protected void setUp() {
        getInstrumentation();
        wordList = new ArrayList<String>();
        wordList.add(0, "pompe");
        wordList.add(1, "baase");
        wordList.add(2, "mande");
        wordList.add(3, "saass");
        wordList.add(4, "jaaho");
        wordList.add(5, "pteii");
        
        wordList2 = new ArrayList<String>();
        wordList2.add(0, "pompe");
        wordList2.add(1, "baase");
        wordList2.add(2, "mande");
        wordList2.add(3, "saass");
        wordList2.add(4, "jaaho");
        wordList2.add(5, "pteii");
        wordList2.add(6, "bbbbb");

        evilHangman = new EvilHangman(5, 8);
        evilHangman.setWordList(wordList);
    }

    protected void runTest() {
    	int key = (int) 'a';
    	// test for the highest number of "equivilance strings to come out"
//    	evilHangman.setWordList(wordList);
//    	evilHangman.evilBusiness(key);
//    	assertEquals("_aa__", evilHangman.getCurrentWordState());
    	
    	// here there are just as many "_aa__" as there are "_____"
    	// the algorithm should return "_____" because it has the least usable
    	// letters
    	evilHangman.setWordList(wordList2);
    	evilHangman.evilBusiness(key);
    	assertEquals("_____", evilHangman.getCurrentWordState());
    }

    public void testInstrumentation() {

    }
}
