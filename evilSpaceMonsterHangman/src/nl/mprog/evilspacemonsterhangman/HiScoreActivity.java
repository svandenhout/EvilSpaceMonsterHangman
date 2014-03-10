/*
 * HiScoreAcivity
 * retrieves the hiscores from the hiScoreServer & shows them on screen
 */

package nl.mprog.evilspacemonsterhangman;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.mprog.evilspacemonsterhangman.models.HiScores;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HiScoreActivity extends Activity {
	private Context context = this;
	private URL getURL;
	private JSONArray hiScoreList;
	private ListView hiScoreView;
	
	/*
	 * HiScoreAdapter makes it possible to format the ListView a bit 
	 * more extensively
	 * I borrowed a ton of code from:
	 * https://github.com/thecodepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
	 */
	private class HiScoreAdapter extends ArrayAdapter<JSONObject> {
	    // View lookup cache
	    private class ViewHolder {
	        TextView user;
	        TextView score;
	    }

	    public HiScoreAdapter(Context context, List<JSONObject> hiScores) {
	       super(context, R.layout.activity_hi_score, hiScores);
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	       // Get the data item for this position
	       JSONObject hiScore = getItem(position);    
	       
	       // Check if an existing view is being reused, 
	       // otherwise inflate the view
	       ViewHolder viewHolder; 
	       if (convertView == null) {
	          viewHolder = new ViewHolder();
	          LayoutInflater inflater = LayoutInflater.from(getContext());
	          convertView = inflater.inflate(R.layout.simple_list_item_1, null);
	          viewHolder.user = 
	        		  (TextView) convertView.findViewById(R.id.user_name);
	          viewHolder.score = 
	        		  (TextView) convertView.findViewById(R.id.user_score);
	          convertView.setTag(viewHolder);
	       } else {
	           viewHolder = (ViewHolder) convertView.getTag();
	       }
	       // Populate the data into the template view using the data object
	       try {
		       viewHolder.user.setText(hiScore.get("user").toString());
		       viewHolder.score.setText(hiScore.get("score").toString());
	       }catch(JSONException e) {
	    	   e.printStackTrace();
	       }
	       // Return the completed view to render on screen
	       return convertView;
	   }
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hi_score);
		
		Log.d("hiscores", "oncreate");
		AsyncTask<Void, Void, Void> asyncTask =
				new AsyncTask<Void, Void, Void>() {

			ProgressDialog pd;

            @Override
            protected void onPreExecute() {
            	Log.d("hiscores", "onpreexecute");
                pd = new ProgressDialog(context);
                pd.setTitle("Processing...");
                pd.setMessage("Please wait.");
                pd.setCancelable(false);
                pd.setIndeterminate(true);
                pd.show();
                
                try {
                	getURL = new URL("http://10.0.2.2:3000/hiscores.json");
                }catch(MalformedURLException e) {
                	e.printStackTrace();
                }
            }
			
            
            // I know this is not optimal, the lack of native JSON
			// functionality forces me to do it this way
			@Override
			protected Void doInBackground(Void... params) {
				List<JSONObject> hiScores = new ArrayList<JSONObject>();
				
				hiScoreView = (ListView) findViewById(R.id.hi_score_list);
				hiScoreList = HiScores.getHiScores(getURL);
				try {
					for(int i = 0; i < hiScoreList.length(); i++) {
						hiScores.add((JSONObject) hiScoreList.get(i));
					}
				}catch(JSONException e) {
					e.printStackTrace();
				}
				
				HiScoreAdapter adapter = new HiScoreAdapter(context, hiScores);
				hiScoreView.setAdapter(adapter);
				
				return null;
			}
			
			@Override
			protected void onPostExecute(Void params) {
				hiScoreView.refreshDrawableState();
				pd.dismiss();
			}
		};
		
		asyncTask.execute((Void[]) null);
	}
}
