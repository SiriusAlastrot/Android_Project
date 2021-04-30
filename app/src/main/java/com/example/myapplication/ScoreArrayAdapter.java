package com.example.myapplication;

import android.content.Context;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;

public class ScoreArrayAdapter  extends ArrayAdapter<Score> {
    private final Context context;
    boolean C = true;
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
        TextView textView2 = (TextView)cellView.findViewById(R.id.temps);
        LinearLayout layout = (LinearLayout)cellView.findViewById(R.id.layout);
        if(C){
            layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            C = false;
        }
        else{
            layout.setBackgroundColor(Color.parseColor("#D3D3D3"));
            C = true;
        }
        Score score = getItem(position);
        textView.setText(score.pseudo);
        textView2.setText(score.temps);
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
