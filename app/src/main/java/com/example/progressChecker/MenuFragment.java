package com.example.progressChecker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.progressChecker.navigation.leanerSideMenuTestOwnWay;
import com.example.progressChecker.navigation.sideMenuLeanerMode;
import com.example.progressChecker.navigation.sideMenuMonitorResults;
import com.example.progressChecker.navigation.sideMenuViewTestResults;
import com.example.progressChecker.teachOwnWay.TeachOwnWay;


public class MenuFragment extends Fragment {

    Button testResults, monitorBehaviour, teachOwnWay, testOwnWay, learnerMode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_menu, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        testResults = view.findViewById(R.id.TestResult);
        monitorBehaviour = view.findViewById(R.id.MonitorBehaviour);
        teachOwnWay = view.findViewById(R.id.TeachOwnWay);
        testOwnWay = view.findViewById(R.id.Test);
        learnerMode = view.findViewById(R.id.LeanerBtn);


        testResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), sideMenuViewTestResults.class);
                startActivity(intent);

            }
        });
        monitorBehaviour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), sideMenuMonitorResults.class);
                startActivity(i);

            }
        });
        teachOwnWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), TeachOwnWay.class);
                startActivity(i);

            }
        });
        testOwnWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), leanerSideMenuTestOwnWay.class);
                startActivity(i);

            }
        });
        learnerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), sideMenuLeanerMode.class);
                startActivity(i);

            }
        });
    }
}