package com.sarasapp.sarasapp.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sarasapp.sarasapp.Objects.Photo;

import java.util.ArrayList;


public class DatabaseHelper {

    private static String LOG_TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = Photo.TABLE_NAME;
    private static final int DATABASE_VERSION = 1;
    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME,null,DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + Photo.TABLE_NAME + "(imgurl VARCHAR)");
            Log.d("dmydb", "DATABSE CREATED");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL(" DROP TABLE IF EXISTS " + Photo.TABLE_NAME  );
            Log.d("dmydb", "DATABSE dropped");
        }

    }

    public DatabaseHelper(Context c){
        ourContext = c;
    }

    public DatabaseHelper open(){
        ourHelper = new DbHelper (ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long addPhoto(ContentValues cv) {
        open();
        long id = ourDatabase.insert(Photo.TABLE_NAME, null, cv);
        Log.d("dmydb","PET ADDED");
        close();
        return id;
    }

    public ArrayList<Photo> getAllPhotos () {
        open();
        String[] columns = Photo.columns;
        Cursor c = ourDatabase.query(Photo.TABLE_NAME, columns, null, null, null, null, null);
        ArrayList<Photo> arrayList = Photo.getArrayList(c);
        close();
        return arrayList;
    }





}