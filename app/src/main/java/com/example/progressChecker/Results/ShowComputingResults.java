package com.example.progressChecker.Results;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.progressChecker.DBHelper;
import com.example.progressChecker.R;
import com.example.progressChecker.navigation.sideMenuLeanerMode;

public class ShowComputingResults extends AppCompatActivity {

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        db = new DBHelper(this);

        TextView resultLabel = findViewById(R.id.resultLabel);
        TextView titleLabel = findViewById(R.id.subtitle);
        ImageView imageView = findViewById(R.id.rewards);
        TextView totalScoreLabel = findViewById(R.id.totalScoreLabel);

        int Computingscore = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        SharedPreferences sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
        int ComputingtotalScore = sharedPreferences.getInt("TOTAL_COMPUTING_SCORE", 0);
        ComputingtotalScore += Computingscore;

        if (Computingscore <= 1) {
            resultLabel.setText("Unfortunately, you've only scored " + Computingscore + " / 5, Please re-visit the Learning Area to Learn more and Try again!");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.bronze_reward, getApplicationContext().getTheme()));
            resultLabel.setBackgroundColor(Color.parseColor("#CD7F32"));
        }
        if (Computingscore >=2 && Computingscore <=4){
            resultLabel.setText("Great!!, you've got " + Computingscore + " / 5, Keep learning and Practicing to 5/5. Try again!");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.silver_reward, getApplicationContext().getTheme()));
            resultLabel.setBackgroundColor(Color.parseColor("#C0C0C0"));

        }
        if (Computingscore == 5) {

            resultLabel.setText("Congratulation!!, you've got the highest score " + Computingscore + " / 5, reach to the total of 20 to gain access to Advanced Level");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.gold_reward, getApplicationContext().getTheme()));
            totalScoreLabel.setBackground(getResources().getDrawable(R.drawable.redborder));
            resultLabel.setBackgroundColor(Color.parseColor("#FFD700"));
        }
        totalScoreLabel.setText("Total Score : " + ComputingtotalScore);
        titleLabel.setText("Computing Test Results");

        // Update total score.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TOTAL_COMPUTING_SCORE", ComputingtotalScore);
        editor.apply();

        String cat = "Computing";
        String finalscore = String.valueOf(ComputingtotalScore);

        boolean insert = db.addScore(cat, finalscore);

        if (insert == true) {
            Toast.makeText(ShowComputingResults.this, "Inserted Successfully", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(ShowComputingResults.this, "Failed", Toast.LENGTH_LONG).show();

        }

    }

    public void returnTop(View view) {
        startActivity(new Intent(getApplicationContext(), sideMenuLeanerMode.class));
    }
}