package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {
    public static  int id = 0;
    private ListView mListView;
    private ScoreArrayAdapter mAdapter;
    private myScoreBDDAdapter bdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ScoreBDD Scorebdd = new ScoreBDD(this);
        Scorebdd.open();
        bdAdapter = new myScoreBDDAdapter(this);
        bdAdapter.open();

        // on reprends l'intention
        Intent intent = getIntent();
        String message = intent.getStringExtra(EndGameActivity.EXTRA_MESSAGE);
        String name = intent.getStringExtra(ActivityListLevel.EXTRA_NAME);
        String temps = intent.getStringExtra(ActivityGame.EXTRA_TEMPS);
        super.onCreate(savedInstanceState);
        Scorebdd.insertScore(new Score(id++,message,temps,name));
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

}