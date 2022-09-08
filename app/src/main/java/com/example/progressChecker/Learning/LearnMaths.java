package com.example.progressChecker.Learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progressChecker.Test.MathTest;
import com.example.progressChecker.R;

public class LearnMaths extends AppCompatActivity {

    TextView title;
    TextView content;

    Button NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_learn_view);
        title = findViewById(R.id.content_title);
        content = findViewById(R.id.content);
        NextButton = findViewById(R.id.NextButton);
        ImageView image = findViewById(R.id.imageviews);
        image.setImageResource(R.drawable.mathpicone);

        String string = getString(R.string.Mathvalue1title);
        String contents = getString(R.string.Mathvalue1content);


        Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.mathpicone);
        image.setImageBitmap(bImage);
        title.setText(string);
        content.setText(contents);
        NextButton.setText("Next Page 1");

        NextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String BtnText = NextButton.getText().toString();

                if (BtnText.equals("Next Page 1")) {
                    image.setImageResource(R.drawable.mathpictwo);
                    String string = getString(R.string.Mathvalue2title);
                    String contents = getString(R.string.Mathvalue2content);

                    title.setText(string);
                    content.setText(contents);

                    NextButton.setText("Next Page 2");

                }
                if (BtnText.equals("Next Page 2")) {

                    image.setImageResource(R.drawable.mathpicthree);

                    String string2 = getString(R.string.Mathvalue3title);
                    String contents2 = getString(R.string.Mathvalue3contnet);

                    title.setText(string2);
                    content.setText(contents2);
                    NextButton.setText("Next Page 3");

                }

                if (BtnText.equals("Next Page 3")) {

                    image.setImageResource(R.drawable.mathpicfour);

                    String string2 = getString(R.string.MathValue4title);
                    String contents2 = getString(R.string.MathValue4content);

                    title.setText(string2);
                    content.setText(contents2);
                    NextButton.setText("Next Page 4");

                }
                if (BtnText.equals("Next Page 4")) {

                    image.setImageResource(R.drawable.mathpicfive);

                    String string2 = getString(R.string.MathValue4title);
                    String contents2 = getString(R.string.MathValue4content);

                    title.setText(string2);
                    content.setText(contents2);
                    NextButton.setText("Next Page 5");

                }

                if (BtnText.equals("Next Page 5")){
                    Intent i = new Intent(getApplicationContext(), MathTest.class);
                    startActivity(i);
                }

            }


            {


            }
        });
    }

    public void Backbtn(View view) {
        super.onBackPressed();
    }
}




