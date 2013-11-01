package nl.mprog.evilspacemonsterhangman;

import nl.mprog.evilspacemonsterhangman.models.*;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

import java.util.List;

public class MainActivity extends Activity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set sqlite database
        db = new DatabaseHelper(this).getReadableDatabase();
        WordList wl = new WordList(db, 5);

        List<String> wordList = wl.getWordList();
        Log.d("WORDLIST", wordList.get(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}