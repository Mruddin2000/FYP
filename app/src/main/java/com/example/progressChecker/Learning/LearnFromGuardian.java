package com.example.progressChecker.Learning;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.progressChecker.DBHelper;
import com.example.progressChecker.R;
import com.example.progressChecker.navigation.leanerSideMenuTestOwnWay;
import com.example.progressChecker.teachOwnWay.Image;
import com.example.progressChecker.teachOwnWay.ImageListAdapter;

import java.util.ArrayList;

public class LearnFromGuardian extends AppCompatActivity {

    GridView gridView;
    ArrayList<Image> list;
    ImageListAdapter adapter = null;
    Button back, test;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        back = findViewById(R.id.BackBtn);
        test = findViewById(R.id.customTest);

        gridView = findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new ImageListAdapter(this, R.layout.activity_learn_from_guardian, list);
        gridView.setAdapter(adapter);

        DBHelper sqLiteHelper = new DBHelper(this, "TeachOwnWayDB.sqlite", null, 1);
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM TeachOwnWay");
        // get all data from sqlite
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Image(name, price, image, id));
        }
        adapter.notifyDataSetChanged();

    }

    public void customTest(View view) {

            Intent i = new Intent(getApplicationContext(), leanerSideMenuTestOwnWay.class);
            startActivity(i);
    }

    public void Backbtn(View view) {
        super.onBackPressed();

    }
}