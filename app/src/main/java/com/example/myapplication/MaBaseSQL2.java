package com.example.myapplication;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MaBaseSQL2 extends SQLiteOpenHelper {

    private static final String TABLE_LEVELS = "table_levels";
    private static final String COL_NAME = "NAME";


    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_LEVELS + " ("
            + COL_NAME;

    public MaBaseSQL2(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE " + TABLE_LEVELS + ";");
        onCreate(db);
    }

}
