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

public class ShowMathResults extends AppCompatActivity {

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


        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        SharedPreferences sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
        int totalScore = sharedPreferences.getInt("TOTAL_MATH_SCORE", 0);
        totalScore += score;

      if (score <= 1) {
            resultLabel.setText("Unfortunately, you've only scored " + score + " / 5, Please re-visit the Learning Area to Learn more and Try again!");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.bronze_reward, getApplicationContext().getTheme()));
            resultLabel.setBackgroundColor(Color.parseColor("#CD7F32"));

        }
        if (score >=2 && score <=4){
            resultLabel.setText("Great!!, you've got " + score + " / 5, Keep learning and Practicing to 5/5. Try again!");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.silver_reward, getApplicationContext().getTheme()));
            resultLabel.setBackgroundColor(Color.parseColor("#C0C0C0"));

        }
        if (score == 5){

            resultLabel.setText("Congratulation!!, you've got the highest score " + score + " / 5, reach to the total of 20 to gain access to Advanced Level");
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.gold_reward, getApplicationContext().getTheme()));
            totalScoreLabel.setBackground(getResources().getDrawable(R.drawable.redborder));
            resultLabel.setBackgroundColor(Color.parseColor("#FFD700"));

        }
        titleLabel.setText("Math Test Results");
        totalScoreLabel.setText("Total Score : " + totalScore);

        // Update total score.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TOTAL_MATH_SCORE", totalScore);
        editor.apply();

        String cat = "Math";
        String finalscore = String.valueOf(totalScore);

        boolean insert = db.addScore(cat,finalscore);

        if (insert == true){
            Toast.makeText(ShowMathResults.this,"Inserted Successfully", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(ShowMathResults.this,"Failed", Toast.LENGTH_LONG).show();

        }

    }

    public void returnTop(View view) {
        startActivity(new Intent(getApplicationContext(), sideMenuLeanerMode.class));
    }
}