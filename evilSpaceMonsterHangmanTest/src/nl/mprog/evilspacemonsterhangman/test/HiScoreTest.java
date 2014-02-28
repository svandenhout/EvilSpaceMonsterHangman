package nl.mprog.evilspacemonsterhangman.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.InstrumentationTestCase;
import nl.mprog.evilspacemonsterhangman.models.*;

/**
 * Created by steven on 6/26/13.
 */
public class HiScoreTest extends InstrumentationTestCase {
	JSONArray hiScoreList;

	
	/*
	 * HiScore test, i just comment out the send & get to differentiate
	 */
    public HiScoreTest(String name) {
        super();
        setName(name);
    }

    protected void setUp() {
    	hiScoreList = HiScores.getHiScores();
    }

    protected void runTest() {
    	try {
    		// 
    		HiScores.postHiScore("test steven", "5");
	    	assertEquals("Hello World", HiScores.getHelloWorld());
	    	JSONObject oneObject = hiScoreList.getJSONObject(0);
	    	assertEquals("wow such test", oneObject.getString("user"));
    	}catch(JSONException e) {
    		e.printStackTrace();
    	}
    }

    public void testInstrumentation() {

    }
}
