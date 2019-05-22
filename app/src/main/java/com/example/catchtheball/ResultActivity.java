package com.example.catchtheball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    TextView scoreLabel = null;
    TextView highScoreLabel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");


        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if(score > highScore) {
            highScoreLabel.setText("High Score: "+score);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();

        } else {
            highScoreLabel.setText("High Score: "+highScore);
        }
    }

    private void initView() {
        scoreLabel = (TextView)findViewById(R.id.scoreLabel);
        highScoreLabel = (TextView)findViewById(R.id.highScoreLabel);
    }

    public void tryAgain(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
