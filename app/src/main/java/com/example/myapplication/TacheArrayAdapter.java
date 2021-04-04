package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TacheArrayAdapter  extends ArrayAdapter<Tache> {
    private final Context context;
    public TacheArrayAdapter(Context context, ArrayList<Tache> values) {
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
        TextView textView = (TextView)cellView.findViewById(R.id.label);
        ImageView imageView = (ImageView)cellView.findViewById(R.id.image);
        Tache task = getItem(position);
        textView.setText(task.title);
        //Modification des taches en fonction des priorité
        switch(task.priority) {
            case 0:
                break;
            case 1:
                break;
            //...
        }
        return cellView;
    } // fin de la méthode getView
}
