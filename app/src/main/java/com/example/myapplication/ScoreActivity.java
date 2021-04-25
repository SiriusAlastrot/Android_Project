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
    private ListView mListView;
    private ScoreArrayAdapter mAdapter;
    private ArrayList<Score> scores = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ScoreBDD Scorebdd = new ScoreBDD(this);
        Scorebdd.open();

        // on reprends l'intention
        Intent intent = getIntent();
        String message = intent.getStringExtra(EndGameActivity.EXTRA_MESSAGE);
        String name = intent.getStringExtra(ActivityListLevel.EXTRA_NAME);
        String temps = intent.getStringExtra(ActivityGame.EXTRA_TEMPS);
        super.onCreate(savedInstanceState);
        Scorebdd.insertScore(new Score(message,temps,name));
        scores.add(new Score(message,temps,name));
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list);
        registerForContextMenu(mListView);
        mAdapter = new ScoreArrayAdapter(this, scores);
        mListView.setAdapter(mAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.add_settings:
                mAdapter.add(new Score("Alastrot", "18", "2" ));
                return true;
            case R.id.quit_settings:
                System.exit(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.action_delete:
                mAdapter.remove(mAdapter.getItem(menuInfo.position));
                return true;
        }
        return super.onContextItemSelected(item);
    }
}