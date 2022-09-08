package com.example.progressChecker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.progressChecker.Learning.LearnFromGuardian;
import com.example.progressChecker.R;
import com.example.progressChecker.Test.takeATest;
import com.example.progressChecker.navigation.leanerSideMenuAdvanced;
import com.example.progressChecker.navigation.leanerSideMenuBeginning;

public class LearnerMode extends Fragment {
    Button beginnerBtn, advancedBtn, learnFromGuardianBtn, takeATestBtn, back;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_learner_mode, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        beginnerBtn = view.findViewById(R.id.BeginnerBtn);

        advancedBtn = view.findViewById(R.id.AdvancedBtn);
        takeATestBtn = view.findViewById(R.id.takeTestBtn);
        learnFromGuardianBtn = view.findViewById(R.id.LeanerBtn);
        back = view.findViewById(R.id.BackBtn);

        beginnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), leanerSideMenuBeginning.class);
                startActivity(intent);
            }
        });  advancedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), leanerSideMenuAdvanced.class);
                startActivity(intent);

            }
        });  learnFromGuardianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), LearnFromGuardian.class);
                startActivity(i);
            }
        });  takeATestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), takeATest.class);
                startActivity(intent);
            }
        });
    }

}