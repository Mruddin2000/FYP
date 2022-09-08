package com.example.progressChecker.navigation;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.progressChecker.LearnerMode;
import com.example.progressChecker.MenuFragment;
import com.example.progressChecker.MonitorBehaviour.MonitorRecords;
import com.example.progressChecker.R;
import com.example.progressChecker.teachOwnWay.TestOwnWay;
import com.example.progressChecker.ViewResults.ViewTestResults;
import com.google.android.material.navigation.NavigationView;

public class leanerSideMenuTestOwnWay extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
       /*  toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
       toggle.getDrawerArrowDrawable().setBarLength(200.0f);
        toggle.getDrawerArrowDrawable().setBarThickness(10.0f);
        toggle.getDrawerArrowDrawable().setGapSize(25.0f);*/

        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TestOwnWay()).commit();
            navigationView.setCheckedItem(R.id.Menu);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MenuFragment()).commit();
                break;
            case R.id.viewResults:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ViewTestResults()).commit();
                break;
            case R.id.monitorBehaviour:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MonitorRecords()).commit();
                break;

            case R.id.learnerMode:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LearnerMode()).commit();                break;
            case R.id.signOut:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Are you sure to Sign out")
//set positive button
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                finish();
                            }
                        })
//set negative button
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what should happen when negative button is clicked
                                Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}