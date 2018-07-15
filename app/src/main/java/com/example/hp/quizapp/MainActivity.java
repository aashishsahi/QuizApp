package com.example.hp.quizapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {
    int number1, number2, result, others, correct = 0, total = 0;
    public static int option;
    final Random r = new Random();
    static CountDownTimer start;
    int f = 0;

    @Override
    protected void onPause() {
        super.onPause();
        start.cancel();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(MainActivity.this, "App was paused... Please play again...", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, AppStart.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button restart = findViewById(R.id.restart_btn);
        final TextView timer = findViewById(R.id.timer_text_view);
        final TextView score = findViewById(R.id.message_test_view);
        final TextView AnotherScore = findViewById(R.id.score_text_view);


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                start.cancel();
                Log.d("Check", "Entered Restart");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });

        start = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long l) {



                timer.setText(String.valueOf((int) l / 1000) + "s");
                f = 1;
            }

            @Override
            public void onFinish() {

                if(f==1)
                {
                    ChangeActivity();
                    f = 0;
                }
            }
        }.start();


        option = r.nextInt(4);
        NextQuestion(option);


    }

   public void ChangeActivity()
   {


           Intent intent = new Intent(MainActivity.this, ScoreScreen.class);
           intent.putExtra("Correct", correct);
           intent.putExtra("Total", total);
           startActivity(intent);
           finish();

   }

    public void Checking()
    {
        final TextView score = findViewById(R.id.message_test_view);
        final TextView AnotherScore = findViewById(R.id.score_text_view);
        Button buttons[] = new Button[4];
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);

        for(int i = 0;i<4;i++)
        {
            if(i!=option)
            {
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        option = r.nextInt(4);

                        NextQuestion(option);
                        total++;
                        score.setText("Your Score is " + String.valueOf(correct) + "/" + String.valueOf(total));
                        AnotherScore.setText(String.valueOf(correct) + "/" + String.valueOf(total));
                    }
                });
            }
            if(i==option)
            {
                buttons[option].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        option = r.nextInt(4);
                        NextQuestion(option);
                        correct++;
                        total++;
                        score.setText("Your Score is " + String.valueOf(correct) + "/" + String.valueOf(total));
                        AnotherScore.setText(String.valueOf(correct) + "/" + String.valueOf(total));
                    }
                });
            }
        }
    }
    public void NextQuestion(int option)
    {
        TextView question = findViewById(R.id.question_text_view);
        TextView score = findViewById(R.id.message_test_view);
        Button buttons[] = new Button[4];
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        number1 = r.nextInt(100);
        number2 = r.nextInt(50);
        question.setText(String.valueOf(number1) + " + " + String.valueOf(number2));
        result = number1 + number2;
        buttons[option].setText(String.valueOf(result));
        for (int i = 0; i < 4; i++) {
            if (i != option) {
                others = r.nextInt(151);
                buttons[i].setText(String.valueOf(others));
            }
        }
        Checking();
    }

}
