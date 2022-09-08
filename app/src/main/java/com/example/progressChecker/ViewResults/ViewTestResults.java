package com.example.progressChecker.ViewResults;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.progressChecker.DBHelper;
import com.example.progressChecker.R;
import com.example.progressChecker.navigation.sideMenu;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ViewTestResults extends Fragment {

    Button back;

    DBHelper db;
    PieChart pieChart;
    ArrayList<PieEntry> category;
    ArrayList<PieModel> myList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_view_test, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(view.getContext());

        pieChart = view.findViewById(R.id.pie);
        back = view.findViewById(R.id.backBtn);
        category =new ArrayList<>();
        pieData();

            for (int i = 0; i < myList.size(); i++) {
                String category = myList.get(i).getCategory();
                float finalScore = Integer.parseInt(myList.get(i).getFinalScore());
                this.category.add(new PieEntry(finalScore, "" + category));
            }

        PieDataSet pieDataSet = new PieDataSet(category,"Score");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(20f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Category");
        pieChart.animate();
        pieChart.invalidate();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), sideMenu.class);
                startActivity(i);
            }
        });


    }

    public void pieData() {
        Cursor cursor = db.Pie();

        if (cursor.getCount() == 0) {
            Toast.makeText(getView().getContext(), "No Records", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                myList.add(new PieModel("" + cursor.getString(0), "" + cursor.getString(1)));

            }

        }


    }

}

