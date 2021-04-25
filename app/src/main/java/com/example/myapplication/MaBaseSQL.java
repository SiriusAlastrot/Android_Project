package com.example.myapplication;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MaBaseSQL extends SQLiteOpenHelper {

    private static final String TABLE_SCORES = "table_scores";
    private static final String COL_PSEUDO = "PSEUDO";
    private static final String COL_TEMPS = "TEMPS";
    private static final String COL_NIVEAU = "Niveau";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_SCORES + " ("
            + COL_PSEUDO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TEMPS + " TEXT NOT NULL, "
            + COL_NIVEAU + " TEXT NOT NULL);";

    public MaBaseSQL(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE " + TABLE_SCORES + ";");
        onCreate(db);
    }

}