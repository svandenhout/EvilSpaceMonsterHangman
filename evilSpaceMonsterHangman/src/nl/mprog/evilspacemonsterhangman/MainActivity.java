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
                setupHangman();
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
        int i = 0;
        for(char c: chosenLetters) {
        	String str = String.valueOf(c);
        	Button button = eyes.get(i);
        	button.setText(str);
        	
        	// set the listener for all the buttons
        	button.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View v) {
        	        Button button = (Button) v;
        	        int key = (int) button.getText().charAt(0);
        	    }
    	    });
        	
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

    private void setupHangman() {
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
                setupSpaceMonster();
                setupHangman();
            }


        };
        asyncTask.execute((Void[])null);
    }
}