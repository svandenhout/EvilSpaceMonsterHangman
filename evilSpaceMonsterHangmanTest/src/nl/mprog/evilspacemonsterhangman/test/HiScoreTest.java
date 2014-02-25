package nl.mprog.evilspacemonsterhangman.test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
