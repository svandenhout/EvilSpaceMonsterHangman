package nl.mprog.evilspacemonsterhangman.test;

import java.util.ArrayList;
import java.util.List;

import android.test.InstrumentationTestCase;
import nl.mprog.evilspacemonsterhangman.models.*;

/**
 * Created by steven on 6/26/13.
 * 
 * tests the equivclass, a simple class that counts the amount of 
 * equal strings given
 * built for use with an ArrayList
 */
public class EqualStringCountTest extends InstrumentationTestCase {
	private List<EqualStringCount> equivClassList;
	
    public EqualStringCountTest(String name) {
        super();
        setName(name);
    }

    protected void setUp() {
        getInstrumentation();
        equivClassList = new ArrayList<EqualStringCount>();
    }

    protected void runTest() {
    	equivClassList.add(new EqualStringCount("__E_"));
    	equivClassList.add(new EqualStringCount("____"));
    	
    	// everytime i use checkWord the class increments the count()
    	equivClassList.get(0).checkWord("__E_");
    	equivClassList.get(0).checkWord("__E_");
    	
    	// it should count three words
    	assertTrue(equivClassList.get(0).count() == 3);
    	
    	// should return false, because constructor word was "____"
    	assertTrue(! equivClassList.get(1).checkWord("__E_"));
    	
    	// should still be one since the last entry was false
    	assertTrue(equivClassList.get(1).count() == 1);
    }

    public void testInstrumentation() {

    }
}
