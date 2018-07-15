package com.example.hp.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scores);
        ListView listView = findViewById(R.id.list);
        ArrayList<String> Score;
        ArrayAdapter<String> arrayAdapter;
        final SQLoperations sqLoperations = new SQLoperations(getApplicationContext());

        Log.d("Check", "Enter");
        Score = sqLoperations.DisplayPrevScores();

        if(!Score.isEmpty())
        {
            Log.d("Check", "Not Empty");
            arrayAdapter = new ArrayAdapter<String>(DisplayScores.this, android.R.layout.simple_list_item_1, Score);
            listView.setAdapter(arrayAdapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No previous scores", Toast.LENGTH_LONG).show();
        }

        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLoperations.Clear();
                DialogBox();
                sqLoperations.DisplayPrevScores();




            }
        });

    }

    void DialogBox()
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Scores have been cleared");
        dlgAlert.setTitle("Alert");
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                Intent intent = new Intent(DisplayScores.this, DisplayScores.class);
                startActivity(intent);
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

    }
}
