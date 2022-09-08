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


public class Forgotpass extends AppCompatActivity {

    EditText email, password, repassword;
    Button reset, signup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        setContentView(R.layout.activity_forgot_login);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        repassword = findViewById(R.id.editTextRePassword);
        reset = findViewById(R.id.ResetButton);
        DB = new DBHelper(this);


        reset.setOnClickListener(view -> {

            String mail = email.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            if (mail.equals("") || pass.equals("") || repass.equals("")) {
                Toast.makeText(Forgotpass.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            } else {
                if (pass.equals(repass)) {
                    int updatePass = DB.updatepass(mail, pass);
                    if (updatePass == 1) {
                        email.setText("");
                        repassword.setText("");
                        Toast.makeText(Forgotpass.this, "Password has been changed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Forgotpass.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Forgotpass.this, "Password Mismatched", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    public void onLoginClick(View view) {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}
