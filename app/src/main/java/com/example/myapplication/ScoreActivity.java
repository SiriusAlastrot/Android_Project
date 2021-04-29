package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {
    public static  int id = 0;
    private ListView mListView;
    private ScoreArrayAdapter mAdapter;
    private myScoreBDDAdapter bdAdapter;
    public static ArrayList<Score> listscores = new ArrayList<Score>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ScoreBDD Scorebdd = new ScoreBDD(this);
        //Scorebdd.open();
        bdAdapter = new myScoreBDDAdapter(this);
        bdAdapter.open();
        // on reprends l'intention
        Intent intentbefore = getIntent();
        String pseudo = intentbefore.getStringExtra(EndGameActivity.EXTRA_PSEUDO);
        System.out.println(pseudo);
        String temps = intentbefore.getStringExtra(EndGameActivity.EXTRA_TEMPS2);
        System.out.println(temps);
        String niveau = intentbefore.getStringExtra(EndGameActivity.EXTRA_NIVEAU3);
        System.out.println(niveau);
        super.onCreate(savedInstanceState);
        bdAdapter.insertScore(pseudo,temps,"15");
        //bdAdapter.insertScore("CORENTIN","CORENTIN","CORENTIN");
        //listscores.add(new Score(23,"jojo","300","3"));
        //listscores.add(new Score(2,"jajaja","301","3"));
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list);

        mAdapter = new ScoreArrayAdapter(this, bdAdapter.getAllScores());
        mListView.setAdapter(mAdapter);

    }
   @Override
    public void onDestroy(){
        bdAdapter.close();
        super.onDestroy();
   }
    public void back(View view){
        Intent intent = new Intent(this,MenuPrincipalActivity.class);
        startActivity(intent);
    }
}