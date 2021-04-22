package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class LevelArrayAdapter extends ArrayAdapter<Level> {
    private final Context context;
    public LevelArrayAdapter(Context context, ArrayList<Level> values) {
        super(context, R.layout.cell_list_level, values);
        this.context = context;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View cellView = convertView;
        if (cellView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.cell_list_level, parent, false);
        }
        TextView textLevel = (TextView)cellView.findViewById(R.id.titleLevel);
        TextView textSizeLevel = (TextView)cellView.findViewById(R.id.sizegrid);
        Level task = getItem(position);
        textLevel.setText(task.levelName);
        textSizeLevel.setText(task.size);
        //Modification des taches en fonction des priorité
        /*
        switch(task.priority) {
            case 0:
                break;
            case 1:
                break;
            //...
        }*/
        return cellView;
    } // fin de la méthode getView
}
