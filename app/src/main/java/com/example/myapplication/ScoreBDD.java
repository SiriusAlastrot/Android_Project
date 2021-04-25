package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ScoreBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "scores.db";

    private static final String TABLE_SCORES = "table_scores";
    private static final String COL_PSEUDO = "PSEUDO";
    private static final int NUM_COL_PSEUDO = 0;
    private static final String COL_TEMPS = "TEMPS";
    private static final int NUM_COL_TEMPS = 1;
    private static final String COL_NIVEAU = "Niveau";
    private static final int NUM_COL_NIVEAU = 2;

    private SQLiteDatabase bdd;

    private MaBaseSQL maBaseSQL;

    public ScoreBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQL = new MaBaseSQL(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQL.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertScore(Score score){

        ContentValues values = new ContentValues();

        values.put(COL_PSEUDO, score.getPseudo());
        values.put(COL_TEMPS, score.gettemps());
        values.put(COL_NIVEAU, score.getniveau());

        return bdd.insert(TABLE_SCORES, null, values);
    }
}
