package com.example.progressChecker.LoginAndRegistration;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.progressChecker.DBHelper;
import com.example.progressChecker.R;
import com.example.progressChecker.navigation.sideMenu;


public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin, signup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        setContentView(R.layout.activity_login);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        btnlogin = findViewById(R.id.LoginButton);
        DB = new DBHelper(this);


        btnlogin.setOnClickListener(view -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (user.equals("") || pass.equals(""))
                Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if (checkuserpass == true) {
                    Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), sideMenu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onLoginClick(View view) {
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }

    public void forgotpass(View view) {
        Intent intent = new Intent(getApplicationContext(), Forgotpass.class);
        startActivity(intent);
    }
}
