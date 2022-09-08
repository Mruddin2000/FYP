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

public class ShowMixedResults extends AppCompatActivity {

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

        int mixedScore = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        SharedPreferences sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
        int mixedTotalScore = sharedPreferences.getInt("TOTAL_MIXED_SCORE", 0);
        mixedTotalScore += mixedScore;

        if (mixedScore >= 0 && mixedScore <=3) {
            resultLabel.setText("Unfortunately, you've only scored " + mixedScore + " / 10, Please re-visit the Learning Area to Learn more and Try again!");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.bronze_reward, getApplicationContext().getTheme()));
            resultLabel.setBackgroundColor(Color.parseColor("#CD7F32"));
        }
        if (mixedScore >=4 && mixedScore <=8){
            resultLabel.setText("Great!!, you've got " + mixedScore + " / 10, Keep learning and Practicing to 10/10. Try again!");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.silver_reward, getApplicationContext().getTheme()));
            resultLabel.setBackgroundColor(Color.parseColor("#C0C0C0"));

        }
        if (mixedScore >= 9 && mixedScore <=10) {

            resultLabel.setText("Congratulation!!, you've got the highest score " + mixedScore + " / 10, reach to the total of 30 to gain access to Advanced Level");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.gold_reward, getApplicationContext().getTheme()));
            totalScoreLabel.setBackground(getResources().getDrawable(R.drawable.redborder));
            resultLabel.setBackgroundColor(Color.parseColor("#FFD700"));

        }
            titleLabel.setText("Test Results");
            totalScoreLabel.setText("Total Score : " + mixedTotalScore);

            // Update total score.
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("TOTAL_MIXED_SCORE", mixedTotalScore);
            editor.apply();

            String cat = "Mixed Test";
            String finalscore = String.valueOf(mixedTotalScore);

            boolean insert = db.addScore(cat, finalscore);

            if (insert == true) {
                Toast.makeText(ShowMixedResults.this, "Inserted Successfully", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(ShowMixedResults.this, "Failed", Toast.LENGTH_LONG).show();

            }

        }
    public void returnTop(View view) {
        startActivity(new Intent(getApplicationContext(), sideMenuLeanerMode.class));
    }
}