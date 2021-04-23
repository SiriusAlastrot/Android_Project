package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityListLevel extends AppCompatActivity {
    private ListView mListView;
    private LevelArrayAdapter mAdapter;
    public static ArrayList<Level> listLevel = new ArrayList<Level>();
    public static int currentLevel= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listLevel.add(new Level("Niveau1", 1));
        listLevel.add(new Level("Niveau2", 1));
        setContentView(R.layout.activity_level);
        mListView = (ListView) findViewById(R.id.listLevel);
        registerForContextMenu(mListView);
        mAdapter = new LevelArrayAdapter(this, listLevel);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityListLevel.this, ActivityGame.class);
                currentLevel= position;
                startActivity(intent);
            }
        });
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
                mAdapter.add(new Level("newLevel", 1));
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