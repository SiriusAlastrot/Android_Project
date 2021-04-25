package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class LevelBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "levels.db";

    private static final String TABLE_LEVELS = "table_levels";
    private static final String COL_NAME = "Name";
    private static final int NUM_COL_PSEUDO = 0;


    private SQLiteDatabase bdd;

    private MaBaseSQL2 maBaseSQL2;

    public LevelBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQL2 = new MaBaseSQL2(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQL2.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertLevel(Level level){

        ContentValues values = new ContentValues();

        values.put(COL_NAME, level.levelName);

        return bdd.insert(TABLE_LEVELS, null, values);
    }
}