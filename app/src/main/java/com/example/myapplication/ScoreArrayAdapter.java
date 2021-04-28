package com.example.myapplication;

import android.content.Context;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreArrayAdapter  extends ArrayAdapter<Score> {
    private final Context context;
    public ScoreArrayAdapter(Context context, List<Score> values) {
        super(context, R.layout.cell_layout, values);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View cellView = convertView;
        if (cellView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.cell_layout, parent, false);
        }
        TextView textView = (TextView)cellView.findViewById(R.id.pseudo);
        Score task = getItem(position);
        textView.setText(task.pseudo);
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
