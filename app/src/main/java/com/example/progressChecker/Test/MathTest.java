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
import com.example.progressChecker.Results.ShowMathResults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MathTest extends AppCompatActivity {

    DBHelper db;
    private TextView countLabel, questionLabel, titleLabel;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>> MathQuizArray = new ArrayList<>();
    //set Questions
        String titile1 = "Select the correct ansswer. 6 + X = 15";
        String correntAns1 = "9";
        String title2 ="Workout the following fractions. 1/2 + 1/3 = ?";
        String crrctAns2 = "5/6";
        String title3 ="What is 0.75% as a percentage?";
        String correctAns3 = "75%";
        String title4 ="Find the mean for 5, 7, 8, 9, 16";
        String correctAns4 = "9";
        String title5 ="Which number is a factor of 12?";
        String correctAns5 = "3";

        //assign quiz data
    String[][] MathQuizData = {
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {titile1, correntAns1, "8", "10", "6"},
            {title2, crrctAns2, "4/5", "2/3", "6/10"},
            {title3, correctAns3, "35%", "70%", "50%"},
            {title4, correctAns4, "10", "7", "13"},
            {title5, correctAns5, "12", "7", "5"},

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_test);

        db = new DBHelper(this);

//assign xml variables
        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        titleLabel  = findViewById(R.id.subtitle);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);


        // Create quizArray from quizData.
        for (int i = 0; i < MathQuizData.length; i++) {

            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(MathQuizData[i][0]); // question
            tmpArray.add(MathQuizData[i][1]); // Right Answer
            tmpArray.add(MathQuizData[i][2]); // Choice1
            tmpArray.add(MathQuizData[i][3]); // Choice2
            tmpArray.add(MathQuizData[i][4]); // Choice3

            // Add tmpArray to quizArray.
            MathQuizArray.add(tmpArray);
        }

        try {
            showNextQuiz();
    }catch(NullPointerException exception){

        }

    }

    public void showNextQuiz() {
        try {
            titleLabel.setText("Math Test");
            // Update quizCountLabel.
            countLabel.setText("Q" + quizCount);

            // Generate random number between 0 and 14 (quizArray's size - 1)
            Random random = new Random();
            int randomNum = random.nextInt(MathQuizArray.size());

            // Pick one quiz set.
            ArrayList<String> quiz = MathQuizArray.get(randomNum);

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
            MathQuizArray.remove(randomNum);
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
                    Intent intent = new Intent(getApplicationContext(), ShowMathResults.class);
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