package com.example.progressChecker.Test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.progressChecker.DBHelper;
import com.example.progressChecker.R;
import com.example.progressChecker.Results.ShowMixedResults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class takeATest extends AppCompatActivity {

    DBHelper db;
    private TextView countLabel, questionLabel, titleLabel;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 10;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    //set Questions
    String titile1 = "Which is the not non-renwable energy?";
    String title2 ="How can electricity be genrated?";
    String crrctAns2 = "Gas, Coal, oil, wind or solar";
    String wrongAns2 =  "Gas,Water, oil, wind or coal";
    String wrongAns3 =  "Rocks, wind, solar or water";
    String wrongAns4 =  "Sands, water, gas or wind";

    String title3 ="Which of these is NOT a liquid?";
    String title4 ="What can be found in the Solar System along with dwarf planets and moons?";
    String title5 ="How far is the moon from earth?";
    String titilemath1 = "Select the correct ansswer. 6 + X = 15";
    String correntAnsmath1 = "9";
    String titlemath2 ="Workout the following fractions. 1/2 + 1/3 = ?";
    String crrctAnsmath2 = "5/6";
    String titlemath3 ="What is 0.75% as a percentage?";
    String correctAnsmath3 = "75%";
    String titlemath4 ="Find the mean for 5, 7, 8, 9, 16";
    String correctAnsmath4 = "9";
    String titlemath5 ="Which number is a factor of 12?";
    String correctAnsmath5 = "3";
    String titileComputing1 = "Select the Correct definition of Algorithm";
    String correntAnsComputing1 = "Set of rules that are followed to complete a task";
    String titleComputing2 ="Whats the difference between WWW and Internet ?";
    String crrctAnsComputing2 = "Internet connects computers to the www";
    String titleComputing3 ="Calculate binary value of 1001";
    String correctAnsComputing3 = "9";
    String titleComputing4 ="What is RAM Used for?";
    String correctAnsComputing4 = "To Save Temporary Data";
    String titleComputing5 ="What is correct definition of ROM?";
    String correctAnsComputing5 = "Read Only Memory";


    //assign quiz data
    String[][] quizData = {
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {titile1, "Coal", "Gas", "Oil", "Wind"},
            {title2, crrctAns2, wrongAns2, wrongAns3, wrongAns4},
            {title3, "Water", "Honey", "Steam", "Fire"},
            {title4, "Aliens", "Air", "Asteroids", "clouds"},
            {title5, "240,000 miles (385,000km).", "260,000 miles (418,429km).", "280,000 miles (450,616km).", "300,000 miles (550,616km)."},
            {titilemath1, correntAnsmath1, "8", "10", "6"},
            {titlemath2, crrctAnsmath2, "4/5", "2/3", "6/10"},
            {titlemath3, correctAnsmath3, "35%", "70%", "50%"},
            {titlemath4, correctAnsmath4, "10", "7", "13"},
            {titlemath5, correctAnsmath5, "12", "7", "5"},
            {titileComputing1, correntAnsComputing1, "Set of ideas that are followed to complete a task", "It's type of code", "It's a Programming language"},
            {titleComputing2, crrctAnsComputing2, "They're both same", "internet is the wifi and www is the webpages", "www is google, internet is wifi"},
            {titleComputing3, correctAnsComputing3, "10", "7", "16"},
            {titleComputing4, correctAnsComputing4, "Doesn't do anything" ,"To save data permanently", "To track the computer"},
            {titleComputing5, correctAnsComputing5, "Random Access Memory", "Read ordered Measurement", "Read Outstanding Messages"},

    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_atest);

        db = new DBHelper(this);

//assign xml variables
        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        titleLabel = findViewById(R.id.subtitle);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);


        // Create quizArray from quizData.
        for (int i = 0; i < quizData.length; i++) {

            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); // question
            tmpArray.add(quizData[i][1]); // Right Answer
            tmpArray.add(quizData[i][2]); // Choice1
            tmpArray.add(quizData[i][3]); // Choice2
            tmpArray.add(quizData[i][4]); // Choice3

            // Add tmpArray to quizArray.
            quizArray.add(tmpArray);
        }

        try {
            showNextQuiz();
        }catch(NullPointerException exception){

        }

    }

    public void showNextQuiz() {
        try {
            titleLabel.setText("Mixed Test");
            // Update quizCountLabel.
            countLabel.setText("Q" + quizCount);

            // Generate random number between 0 and 14 (quizArray's size - 1)
            Random random = new Random();
            int randomNum = random.nextInt(quizArray.size());

            // Pick one quiz set.
            ArrayList<String> quiz = quizArray.get(randomNum);

            // Set question and right answer.
            // Array format: {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            questionLabel.setText(quiz.get(0));
            rightAnswer = quiz.get(1);

            // Remove "Country" from quiz and Shuffle choices.
            quiz.remove(0);
            Collections.shuffle(quiz);

            // Set choices.
            answerBtn1.setText(quiz.get(0));
            answerBtn2.setText(quiz.get(1));
            answerBtn3.setText(quiz.get(2));
            answerBtn4.setText(quiz.get(3));

            // Remove this quiz from quizArray.
            quizArray.remove(randomNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            // Correct
            alertTitle = "Correct!";
            rightAnswerCount++;

        } else {
            alertTitle = "Wrong...";
        }

        // Create AlertDialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    // Show Result.
                    Intent intent = new Intent(getApplicationContext(), ShowMixedResults.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);

                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

}