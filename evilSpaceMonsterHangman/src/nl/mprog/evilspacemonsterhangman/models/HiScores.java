/*
 * The HiScores singleton contains all of the logics to POST & GET HiScores from
 * the HiScores server.
 */
package nl.mprog.evilspacemonsterhangman.models;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

public class HiScores {

	// retrieves a JSONArray with all the hiScores
	public static JSONArray getHiScores(URL url) {
		try{
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			InputStream in = new BufferedInputStream(conn.getInputStream());
			
			// JSONArray object
			JSONArray hiScores = new JSONArray(convertStreamToString(in));
			return hiScores;
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}catch(JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Posts the HiScore to the server
	public static void postHiScore(URL url, String user, String score) {
    	try {
	    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    	conn.setDoOutput(true);
	    	conn.setChunkedStreamingMode(0);
	
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	params.add(new BasicNameValuePair("user", user));
	    	params.add(new BasicNameValuePair("score", score));
	    	
	    	// normal os should be good for this little data
	    	OutputStream os = conn.getOutputStream();
	    	
	    	// this should write it to the specified server
	    	os.write(getQuery(params).getBytes());
	    	os.flush();
	    	os.close();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    // for making a string of url parameters
    // source: http://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post
    private static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
    
    private static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}