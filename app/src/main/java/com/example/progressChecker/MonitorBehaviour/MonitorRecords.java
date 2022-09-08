package com.example.progressChecker.MonitorBehaviour;

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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MonitorRecords extends Fragment {
    Button back;
    DBHelper db;
    BarChart barChart;
    ArrayList<BarEntry> category;

    ArrayList<BarModel> myList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_monitor_records, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        db = new DBHelper(getContext());

        barChart = view.findViewById(R.id.bar);
        back = view.findViewById(R.id.backBtn);

        category = new ArrayList<>();
        barData();

        for (int i = 0; i < myList.size(); i++) {
            float finalScore = Integer.parseInt(myList.get(i).getFinalScore());
            String category = myList.get(i).getCategory();

            float id = Integer.parseInt(myList.get(i).getId());
            this.category.add(new BarEntry(id, finalScore, "" + category));
        }

        BarDataSet barDataSet = new BarDataSet(category, "Maths, Science, Computing");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(26f);

        BarData barData = new BarData(barDataSet);

        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.animate();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), sideMenu.class);
                startActivity(i);
            }
        });
    }


    public void barData(){
        Cursor cursor = db.Bar();

        if (cursor.getCount()==0){
            Toast.makeText(getContext(),"No Records", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
            myList.add(new BarModel(""+cursor.getString(0),""+cursor.getString(1),""+cursor.getString(2)));

            }
        }
    }
}

