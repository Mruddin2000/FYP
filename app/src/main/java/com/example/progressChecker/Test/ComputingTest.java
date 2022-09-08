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
import com.example.progressChecker.Results.ShowComputingResults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ComputingTest extends AppCompatActivity {

    DBHelper db;
    private TextView countLabel, questionLabel, titleLabel;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>> ComputingQuizArray = new ArrayList<>();
    //set Questions
    String titile1 = "Select the Correct definition of Algorithm";
    String correntAns1 = "Set of rules that are followed to complete a task";
    String title2 ="Whats the difference between WWW and Internet ?";
    String crrctAns2 = "Internet connects computers to the www";
    String title3 ="Calculate binary value of 1001";
    String correctAns3 = "9";
    String title4 ="What is RAM Used for?";
    String correctAns4 = "To Save Temporary Data";
    String title5 ="What is correct definition of ROM?";
    String correctAns5 = "Read Only Memory";

    //assign quiz data
    String[][] ComputingQuizData = {
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {titile1, correntAns1, "Set of ideas that are followed to complete a task", "It's type of code", "It's a Programming language"},
            {title2, crrctAns2, "They're both same", "internet is the wifi and www is the webpages", "www is google, internet is wifi"},
            {title3, correctAns3, "10", "7", "16"},
            {title4, correctAns4, "Doesn't do anything" ,"To save data permanently", "To track the computer"},
            {title5, correctAns5, "Random Access Memory", "Read ordered Measurement", "Read Outstanding Messages"},

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_test);

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
        for (int i = 0; i < ComputingQuizData.length; i++) {

            // Prepare array.
            ArrayList<String> ComputingTmpArray = new ArrayList<>();
            ComputingTmpArray.add(ComputingQuizData[i][0]); // question
            ComputingTmpArray.add(ComputingQuizData[i][1]); // Right Answer
            ComputingTmpArray.add(ComputingQuizData[i][2]); // Choice1
            ComputingTmpArray.add(ComputingQuizData[i][3]); // Choice2
            ComputingTmpArray.add(ComputingQuizData[i][4]); // Choice3

            // Add ComputingTmpArray to quizArray.
            ComputingQuizArray.add(ComputingTmpArray);
        }

        try {
            showNextQuiz();
    }catch(NullPointerException exception){

        }

    }

    public void showNextQuiz() {
        try {
            titleLabel.setText("Computing Test");
            // Update quizCountLabel.
            countLabel.setText("Q" + quizCount);

            // Generate random number between 0 and 14 (quizArray's size - 1)
            Random random = new Random();
            int randomNum = random.nextInt(ComputingQuizArray.size());

            // Pick one quiz set.
            ArrayList<String> quiz = ComputingQuizArray.get(randomNum);

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
            ComputingQuizArray.remove(randomNum);
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
                    Intent intent = new Intent(getApplicationContext(), ShowComputingResults.class);
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