package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class myScoreBDDAdapter  extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "score_database.db";
    private  static final String TABLE_SCORES = "table_scores";
    public static final String COL_ID = "_id";
    public static final String COL_PSEUDO = "pseudo";
    public static final String COL_TEMPS = "temps";
    public static final String COL_NIVEAU = "niveau";

    private static final String CREATE_DB = "create table " + TABLE_SCORES +" ("
            + COL_ID + " integer primary key autoincrement, "
            + COL_PSEUDO + " text not null, "
            + COL_TEMPS + " text not null, "
            + COL_NIVEAU + " text not null);";
    private SQLiteDatabase mDB;
    private MyOpenHelper myOpenHelper;
    public myScoreBDDAdapter(Context context) {
        super(context,"score_database.db",null,1);
        myOpenHelper = new MyOpenHelper(context, DB_NAME, null, DB_VERSION);
    }
    public void open() {
        mDB = myOpenHelper.getWritableDatabase();
    }
    public void close(){
        mDB.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Score getScore(long id) throws SQLException{
        Score s1 = null;
        Cursor s = mDB.query(TABLE_SCORES,
                new String [] {COL_ID,COL_PSEUDO,COL_TEMPS,COL_NIVEAU},
                COL_ID + " = "+ id, null, null, null, null);
        if (s.getCount() > 0 ) {
            s.moveToFirst();
            s1 = new Score(s.getLong(0), s.getString(1), s.getString(2), s.getString(3));
        }
        s.close();
        return s1;
    }
    public List<Score> getAllScores() {
        List<Score> scores = new ArrayList<Score>();
        Cursor s = mDB.query(TABLE_SCORES,
                new String [] {COL_ID,COL_PSEUDO,COL_TEMPS,COL_NIVEAU},
                null, null, null, null, null);
        s.moveToFirst();
        while (!s.isAfterLast()) {
            scores.add(new Score(s.getLong(0), s.getString(1),s.getString(2),s.getString(3)));
            s.moveToNext();
        }
        s.close();
        return scores;
    }
    public long insertScore( String pseudo, String temps , String niveau) {
        ContentValues values = new ContentValues();
        values.put(COL_PSEUDO, pseudo);
        values.put(COL_TEMPS, temps);
        values.put(COL_NIVEAU, niveau);
        return mDB.insert(TABLE_SCORES, null, values);
    }
    private  class MyOpenHelper extends SQLiteOpenHelper {
        public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
            super(context, name, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_DB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table " + TABLE_SCORES + ";");
            onCreate(sqLiteDatabase);
        }
    }
}
