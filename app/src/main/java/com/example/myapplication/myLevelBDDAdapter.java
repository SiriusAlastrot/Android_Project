package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class myLevelBDDAdapter  extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "level_database.db";
    private  static final String TABLE_LEVELS = "table_levels";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_SIZE = "size";


    private static final String CREATE_DB = "create table " + TABLE_LEVELS +" ("
            + COL_ID + " integer primary key autoincrement, "
            + COL_NAME + " text not null, "
            + COL_SIZE + " text not null) ";

    private SQLiteDatabase mDB;
    private MyOpenHelper myOpenHelper;
    public myLevelBDDAdapter(Context context) {
        super(context,"level_database.db",null,1);
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

    public Level getLevel(int id) throws SQLException{
        Level l1 = null;
        Cursor l = mDB.query(TABLE_LEVELS,
                new String [] {COL_ID,COL_NAME,COL_SIZE},
                COL_ID + " = "+ id, null, null, null, null);
        if (l.getCount() > 0 ) {
            l.moveToFirst();
            l1 = new Level(l.getInt(0), l.getString(1), l.getInt(2));
        }
        l.close();
        return l1;
    }
    public List<Level> getAllLevels() {
        List<Level> levels = new ArrayList<Level>();
        Cursor l = mDB.query(TABLE_LEVELS,
                new String [] {COL_ID,COL_NAME,COL_SIZE},
                null, null, null, null, null);
        l.moveToFirst();
        while (!l.isAfterLast()) {
            levels.add(new Level(l.getInt(0), l.getString(1),l.getInt(2)));
            l.moveToNext();
        }
        l.close();
        return levels;
    }
    public long insertLevel(String levelName, int size) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, levelName);
        values.put(COL_SIZE, size);
        return mDB.insert(TABLE_LEVELS, null, values);
    }
    private  class MyOpenHelper extends SQLiteOpenHelper {
        public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
            super(context, name, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            mDB.execSQL(CREATE_DB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            mDB.execSQL("drop table " + TABLE_LEVELS + ";");
            onCreate(sqLiteDatabase);
        }
    }
}
