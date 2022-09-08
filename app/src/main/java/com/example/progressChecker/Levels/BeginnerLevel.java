package com.example.progressChecker.Levels;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.progressChecker.Learning.LearnComputing;
import com.example.progressChecker.Learning.LearnMaths;
import com.example.progressChecker.Learning.LearnScience;
import com.example.progressChecker.R;
import com.example.progressChecker.navigation.sideMenuLeanerMode;

public class BeginnerLevel extends Fragment {
    Button LearnMathsBtn, LearnScienceBtn, LearnComputingBtn, back;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_beginner_level, container, false);
    }
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

        LearnMathsBtn = view.findViewById(R.id.math);

        LearnScienceBtn = view.findViewById(R.id.science);
        LearnComputingBtn = view.findViewById(R.id.computing);
        back = view.findViewById(R.id.backBtn);

            LearnMathsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LearnMaths.class);
                startActivity(intent);
            }
        });  LearnScienceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LearnScience.class);
                startActivity(intent);
            }
        });  LearnComputingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LearnComputing.class);
                startActivity(intent);
            }
        });  back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), sideMenuLeanerMode.class);
                startActivity(intent);
            }
        });
    }
    }
