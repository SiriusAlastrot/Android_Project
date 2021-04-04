package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private TacheArrayAdapter mAdapter;
    private ArrayList<Tache> taches = new ArrayList<Tache>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taches.add(new Tache("Tache1", 0));
        taches.add(new Tache("Tache2", 1));
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list);
        registerForContextMenu(mListView);
        mAdapter = new TacheArrayAdapter(this, taches);
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
                mAdapter.add(new Tache("newTache", 1));
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