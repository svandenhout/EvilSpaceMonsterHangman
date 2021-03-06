package nl.mprog.evilspacemonsterhangman.models;

/**
 * Created by steven on 6/19/13.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class helps open, create, and upgrade the database file.
 * loads of stuff stolen from the internet
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "databasehelper";
    private static final String DB_NAME = "hangmanwords.db";
    private String dbPath;
    private final Context fContext;
    private SQLiteDatabase myDataBase;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        fContext = context;
        dbPath = context.getFilesDir().getPath();
        dbPath = dbPath.substring(0, dbPath.lastIndexOf("/")) + "/databases/";
    }
    
    public void createDatabase() throws IOException {
    	boolean dbExist = checkDataBase();
    	SQLiteDatabase db_Read = null;
    	
    	if(dbExist){
    		Log.d(TAG, "database exists");
    		// do nothing - database already exist
    	}else{
    		// By calling this method and empty database will be created into the default system path
            // of your application so we are gonna be able to overwrite that database with our database.
    		db_Read = this.getReadableDatabase(); 
    		db_Read.close();
	
	     	try {
	     		Log.d(TAG, "copying database");
	 			copyDataBase();
	 		} catch (IOException e) {
	 			e.printStackTrace();
	     		throw new Error("Error copying database");
	
	     	}
    	}
    }
    
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
 
    	SQLiteDatabase checkDB = null;
 
    	try {
    		String myPath = dbPath + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	}catch(SQLiteException e) {
    		//database does't exist yet.
    		e.printStackTrace();
    	}
 
    	if(checkDB != null) {
    		checkDB.close();
    	}
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException {
 
    	//Open your local db as the input stream
    	InputStream myInput = fContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = dbPath + DB_NAME;
    	Log.d(TAG, outFileName);
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer)) > 0) {
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    }
 
    public SQLiteDatabase getDatabase() throws SQLException {
    	//Open the database
    	Log.d(TAG, "getting database");
        String myPath = dbPath + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        Log.d(TAG, "got database");
    	return myDataBase;
    }
 
  @Override
	public synchronized void close() {
	    if(myDataBase != null)
		    myDataBase.close();
	    
	    super.close();
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
}