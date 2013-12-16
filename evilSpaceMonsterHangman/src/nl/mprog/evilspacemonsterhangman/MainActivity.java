package nl.mprog.evilspacemonsterhangman;

import nl.mprog.evilspacemonsterhangman.models.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

public class MainActivity extends Activity {
    private SQLiteDatabase db;
    private Hangman hangman;
    private SpaceMonster spaceMonster;
    List<String> wordList;
    List<Button> eyes;
    private int userInputState;

    private TextView computerMonologueView;
    private TextView currentWordStateView;
    private TextView usedLettersView;

    private SharedPreferences preferences;
    private boolean changedPrefs;
    private String userName;
    private int wordLength;
    private int wrongGuesses;
    private boolean evilMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupSpaceMonster();
       	newHangman();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Context context = this;

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.hiScores:
                // Intent startHiScore = new Intent(context, HiScoreActivity.class);
                // startActivity(startHiScore);
                return true;
            case R.id.settings:
                Intent startPreferences = new Intent(context, PreferencesActivity.class);
                startActivityForResult(startPreferences, 0);
                return true;
            case R.id.reset:
                setHangman();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void setupSpaceMonster() {
    	spaceMonster = new SpaceMonster();
        
        char[] chosenLetters = spaceMonster.randomChosenLetters();
        
        // build an eyes list
        eyes = new ArrayList<Button>();
        eyes.add((Button) findViewById(R.id.eye_1));
        eyes.add((Button) findViewById(R.id.eye_2));
        eyes.add((Button) findViewById(R.id.eye_3));
        eyes.add((Button) findViewById(R.id.eye_4));
        eyes.add((Button) findViewById(R.id.eye_5));
        eyes.add((Button) findViewById(R.id.eye_6));

        // fill all of the eyes with the characters that
        // were randomised
        // TODO: should fill the eyes
        int i = 0;
        for(char c: chosenLetters) {
        	String str = String.valueOf(c);
        	eyes.get(i).setText(str);
        	i++;
        	
			Log.d("chosenLetters", str);
        }
    }
    
    private void newHangman() {
        preferences = getSharedPreferences("hangman_preferences", 0);
        userName = preferences.getString("user_name_preference", "Player 1");
        wordLength = preferences.getInt("word_length_preference", 4);
        wrongGuesses = preferences.getInt("incorrect_guesses_preference", 10);
        evilMode = preferences.getBoolean("game_mode_preference", false);
        
        if(evilMode) {
        	hangman = new EvilHangman(wordLength, wrongGuesses);
        }else {
        	hangman = new Hangman(wordLength, wrongGuesses);
        }
        
        doXmlLoad();
    }
    
    

    private void setHangman() {
        hangman.resetValues();
        if(!evilMode) {
        	// set the textviews
	        currentWordStateView = 
        		(TextView) findViewById(R.id.currentWordState);
	        usedLettersView = 
        		(TextView) findViewById(R.id.usedLetters);
	        computerMonologueView = 
        		(TextView) findViewById(R.id.computerDialogue);
	        
	        currentWordStateView.setText(hangman.getCurrentWordState());
	        usedLettersView.setText(hangman.getUsedLetters());
	        computerMonologueView.setText(R.string.cmonologue_start);
        }else {
        	// evilmode
        }
    }
    
    /*
     * doXmlLoad shows progressdialog while the wordList is loaded in the background
     * after the list is loaded = onPostExecute() it will do setupGame()
     */
    private void doXmlLoad() {
        final Context context = this;

        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            ProgressDialog pd;

            @Override
            protected void onPreExecute() {
                pd = new ProgressDialog(context);
                pd.setTitle("Processing...");
                pd.setMessage("Please wait.");
                pd.setCancelable(false);
                pd.setIndeterminate(true);
                pd.show();
            }

            @Override
            protected Void doInBackground(Void... arg0) {
            	
            	// set sqlite database        
            	try {
        	        DatabaseHelper dbHelper = new DatabaseHelper(context);
        	        dbHelper.createDatabase();
        	        db = dbHelper.getDatabase();
        	        WordList wl = new WordList(db, wordLength);
        	        wordList = wl.getWordList();
                }catch(SQLiteException e) {
                	e.printStackTrace();
                }catch(IOException e) {
                	e.printStackTrace();
                }
                
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                pd.dismiss();
                setHangman();
            }


        };
        asyncTask.execute((Void[])null);
    }
    
    // THE EYES
    void eyePress(View v) {
    	switch(v.getId()) {
    		case R.id.eye_1:
    			Log.d("main", "eye 1 click");
    			break;
    		case R.id.eye_2: 
    			Log.d("main", "eye 2 click");
        		break;
    		case R.id.eye_3: 
    			Log.d("main", "eye 3 click");
        		break;
    		case R.id.eye_4: 
    			Log.d("main", "eye 4 click");
        		break;
    		case R.id.eye_5: 
    			Log.d("main", "eye 5 click");
        		break;
    		case R.id.eye_6: 
    			Log.d("main", "eye 6 click");
        		break;
    	}
    }
}