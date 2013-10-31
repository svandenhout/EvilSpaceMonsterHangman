package nl.mprog.evilspacemonsterhangman.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven on 6/20/13.
 */
public class WordList {
    private final static String TAG = "WordList";
    private final static String DB_TABLE = "wordlist";
    private final static String[] DB_COLLUMN = {"words"};
    
    private List<String> wordList = new ArrayList<String>();
    private SQLiteDatabase db;
    
    public WordList(SQLiteDatabase db) {
        this.db = db;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void buildListByWordLength(int wordLength) {
        String _line;
        Cursor _result;
        
        _result = db.query(DB_TABLE, DB_COLLUMN, null, null, null, null, null, null);
        _result.moveToFirst();
        
        while(true) {
        	_line = _result.getString(0);
        	
            if(_line.length() == wordLength) {
                wordList.add(_line);
            }
        	
        	if(!_result.moveToNext()) {
        		break;
        	}    
        }
    }
}