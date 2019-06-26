package com.raghavapp.trainyourbrain;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button resetButton;
    TextView startTextView;
    TextView startTextView2;
    TextView timerTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView textView;
    GridLayout gridLayout;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView resultTextView;
    int a,b, correctAnswer;
    int correctLocation;
    String[] answers;
    int totalCorrect, totalQues;
    CountDownTimer countDownTimer;
    Button playAgainButton;
    TextView finalTextView;

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        startTextView.setVisibility(View.INVISIBLE);
        startTextView2.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText("0/0");
        totalCorrect = 0;
        totalQues = 0;
        scoreTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        finalTextView.setVisibility(View.INVISIBLE);


        countDownTimer = new CountDownTimer(30000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Long.toString(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resetButton.setVisibility(View.INVISIBLE);
                timerTextView.setVisibility(View.INVISIBLE);
                scoreTextView.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
                resultTextView.setVisibility(View.INVISIBLE);
                finalTextView.setText("Final Score: " + totalCorrect + " out of " + totalQues);
                finalTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void onClick(View view) {
        totalQues++;
        Button buttonClicked = (Button) view;
        if (buttonClicked.getText().toString().equals(answers[correctLocation])) {
            resultTextView.setText("Correct!");
            totalCorrect++;
        } else {
            resultTextView.setText("Incorrect :(");
        }
        resultTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText(totalCorrect + "/" + totalQues);
        setQuestion();
    }

    public void setQuestion() {
        Random rand = new Random();
        a = rand.nextInt(99) + 1;
        b = rand.nextInt(99) + 1;

        sumTextView.setText(a + " + " + b);
        correctAnswer = a + b;
        correctLocation = rand.nextInt(4);
        answers[correctLocation] = Integer.toString(correctAnswer);
        for (int i=0; i<4; i++) {
            int data = rand.nextInt(198) + 1;
            while (data == correctAnswer) {
                data = rand.nextInt(198) + 1;
            }
            if (i != correctLocation) {
                answers[i] = Integer.toString(data);
            }
        }
        button0.setText(answers[0]);
        button1.setText(answers[1]);
        button2.setText(answers[2]);
        button3.setText(answers[3]);
    }

    public void onResetClicked(View view) {
        countDownTimer.cancel();
        goButton.setVisibility(View.VISIBLE);
        startTextView.setVisibility(View.VISIBLE);
        startTextView2.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        sumTextView.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        startTextView = findViewById(R.id.startTextView);
        startTextView2 = findViewById(R.id.startTextView2);
        resetButton = findViewById(R.id.resetButton);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        textView = findViewById(R.id.textView);
        gridLayout = findViewById(R.id.gridLayout);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        finalTextView = findViewById(R.id.finalTextView);
        resultTextView = findViewById(R.id.resultTextView);
        answers = new String[4];
        setQuestion();
    }
}
