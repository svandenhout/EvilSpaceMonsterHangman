package nl.mprog.evilspacemonsterhangman.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
// import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven on 6/20/13.
 * 
 * Constructs a wordList from an SQLiteDatabase file, the arguments are the database and
 * the wordlength. the getWordList() method returns the wordlist.
 */
public class WordList {
    // private final static String TAG = "WordList";
    private final static String DB_TABLE = "wordlist";
    private final static String[] DB_COLLUMN = {"words"};
    
    private List<String> wordList = new ArrayList<String>();
    private SQLiteDatabase db;
    
    public WordList(SQLiteDatabase db, int wordLength) {
        this.db = db;
        this.buildListByWordLength(wordLength);
    }

    public List<String> getWordList() {
        return wordList;
    }

    private void buildListByWordLength(int wordLength) {
        String _line;
        Cursor _result;
        
        // how can this be....
        String _selection = "LENGTH(words) = " + wordLength;
        
        _result = db.query(
    		DB_TABLE,
    		DB_COLLUMN,
    		_selection,
    		null,
    		null,
    		null,
    		null,
    		null
		);
        
        _result.moveToFirst();
        
        while(_result.moveToNext()) {
        	_line = _result.getString(0);
        	wordList.add(_line);
        }
    }
}