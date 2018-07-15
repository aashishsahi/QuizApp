package com.example.hp.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
        final SQLoperations sqLoperations = new SQLoperations(getApplicationContext());
        Intent intent = getIntent();
        int correct, total;
        correct = intent.getIntExtra("Correct", 0);
        total = intent.getIntExtra("Total", 0);

        TextView final_score = findViewById(R.id.final_score);
        final_score.setText("Score for last game is: " + String.valueOf(correct) + "/" + String.valueOf(total));
        sqLoperations.CreateTable();
        sqLoperations.AddScore(correct, total);
        Button prevscore = findViewById(R.id.prevscore);
        prevscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ScoreScreen.this, DisplayScores.class);
                startActivity(intent1);


            }
        });

    }

}
