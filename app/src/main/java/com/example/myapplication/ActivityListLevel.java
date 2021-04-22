package com.example.myapplication;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityListLevel extends AppCompatActivity {
    private ListView mListView;
    private LevelArrayAdapter mAdapter;
    private ArrayList<Level> listLevel = new ArrayList<Level>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listLevel.add(new Level("Niveau1", 1));
        listLevel.add(new Level("Niveau2", 2));
        setContentView(R.layout.activity_level);
        mListView = (ListView) findViewById(R.id.list);
        registerForContextMenu(mListView);
        mAdapter = new LevelArrayAdapter(this, listLevel);
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
                mAdapter.add(new Level("newLevel", 3));
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