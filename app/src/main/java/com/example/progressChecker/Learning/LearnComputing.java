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

import com.example.progressChecker.Test.ComputingTest;
import com.example.progressChecker.R;

public class LearnComputing extends AppCompatActivity {

    TextView title;
    TextView content;
    TextView maintitle;

    Button NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_learn_view);
        maintitle = findViewById(R.id.title);
        title = findViewById(R.id.content_title);
        content = findViewById(R.id.content);
        NextButton = findViewById(R.id.NextButton);
        ImageView image = findViewById(R.id.imageviews);
        image.setImageResource(R.drawable.computingpic1);

        String string = getString(R.string.ComputingTitle1);
        String contents = getString(R.string.ComputingContent1);


        Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.computingpic1);
        image.setImageBitmap(bImage);
        maintitle.setText("Computing");
        title.setText(string);
        content.setText(contents);
        NextButton.setText("Next Page 1");

        NextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String BtnText = NextButton.getText().toString();

                if (BtnText.equals("Next Page 1")) {
                    image.setImageResource(R.drawable.computingpic2);
                    String string = getString(R.string.ComputingTitle2);
                    String contents = getString(R.string.ComputingContent2);

                    title.setText(string);
                    content.setText(contents);

                    NextButton.setText("Next Page 2");

                }
                if (BtnText.equals("Next Page 2")) {

                    image.setImageResource(R.drawable.computingpic3);
                    String string = getString(R.string.ComputingTitle3);
                    String contents = getString(R.string.ComputingContent3);

                    title.setText(string);
                    content.setText(contents);
                    NextButton.setText("Next Page 3");

                }

                if (BtnText.equals("Next Page 3")) {

                    image.setImageResource(R.drawable.computingpic4);
                    String string = getString(R.string.ComputingTitle4);
                    String contents = getString(R.string.ComputingContent4);

                    title.setText(string);
                    content.setText(contents);
                    NextButton.setText("Next Page 4");

                }
                if (BtnText.equals("Next Page 4")) {

                    image.setImageResource(R.drawable.computingpic4);
                    String string = getString(R.string.ComputingTitle5);
                    String contents = getString(R.string.ComputingContent5);

                    title.setText(string);
                    content.setText(contents);
                    NextButton.setText("Next Page 5");

                }

                if (BtnText.equals("Next Page 5")){
                    Intent i = new Intent(getApplicationContext(), ComputingTest.class);
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




