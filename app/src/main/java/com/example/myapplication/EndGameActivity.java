package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EndGameActivity extends AppCompatActivity {
    public static final String EXTRA_PSEUDO = "EXTRA_PSEUDO";
    public static final String EXTRA_TEMPS2 = "EXTRA_TEMPS2";
    public static final String EXTRA_NIVEAU3 = "EXTRA_NIVEAU3";
    EditText editText;
    String pseudo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        editText = (EditText) findViewById(R.id.editTextTextPersonName2);

    }


    public void GoScore (View view){
        pseudo = editText.getText().toString();
        Intent intentbefore = getIntent();
        String temps = intentbefore.getStringExtra(ActivityGame.EXTRA_TEMPS);
        String niveau = intentbefore.getStringExtra(ActivityGame.EXTRA_NIVEAU2);
        Intent intent = new Intent(this,ScoreActivity.class);
        intent.putExtra(EXTRA_TEMPS2,temps);
        intent.putExtra(EXTRA_PSEUDO, pseudo);
        intent.putExtra(EXTRA_NIVEAU3,niveau);
        startActivity(intent);
    }
}