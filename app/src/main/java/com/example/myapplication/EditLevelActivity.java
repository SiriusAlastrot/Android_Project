package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditLevelActivity extends AppCompatActivity {
    Button quit;
    Button regenerate;
    EditText levelNameTextField;
    Level editedLevel;
    int indiceLevelEdited;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_level);
        quit= (Button) findViewById(R.id.quit);
        regenerate= (Button) findViewById(R.id.regenerateLab);
        levelNameTextField= (EditText)findViewById(R.id.textFieldLevelName);
        indiceLevelEdited= ActivityListLevel.currentLevel;
        editedLevel= ActivityListLevel.listLevel.get(indiceLevelEdited);
        levelNameTextField.setText(editedLevel.levelName);

    }

    public void quitEditMode(View view)
    {
        ActivityListLevel.listLevel.get(indiceLevelEdited).levelName= levelNameTextField.getText().toString();
        System.exit(0);
    }
    public void regenerateButton(View view)
    {
        ActivityListLevel.listLevel.get(indiceLevelEdited).mazeLevel.regenerate();
    }

}
