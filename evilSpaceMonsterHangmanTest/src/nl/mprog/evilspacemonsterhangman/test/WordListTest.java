package nl.mprog.evilspacemonsterhangman.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.test.InstrumentationTestCase;

import java.util.List;

import nl.mprog.evilspacemonsterhangman.models.DatabaseHelper;
import nl.mprog.evilspacemonsterhangman.models.WordList;

/**
 * Created by steven on 6/25/13.	
 */
public class WordListTest extends InstrumentationTestCase {
    private WordList wl;
    private List<String> stringList;

    public WordListTest(String name) {
        super();
        setName(name);
    }

    /* setup the test? */
    protected void setUp() throws Exception {
        final Context context = getInstrumentation().getTargetContext();
        try {
	        DatabaseHelper dbHelper = new DatabaseHelper(context);
	        dbHelper.createDatabase();
	        SQLiteDatabase db = dbHelper.getDatabase();
	        wl = new WordList(db, 5);
        }catch(SQLiteException e) {
        	e.printStackTrace();
        }
    }

    /* tests if all of the words in the array have the specified wordLength */
    protected void runTest() throws Throwable {
        stringList = wl.getWordList();
        assertNotNull(stringList);
        
        for(String word : stringList) {
            if(word.length() != 5) {
                fail("LENGTH DIFFERS -- the word is:" + word);
            }
        }

        // Wait for the activity to finish all of its processing.
        getInstrumentation().waitForIdleSync();
    }
    
    public void testInstrumentation() {

    }
}
