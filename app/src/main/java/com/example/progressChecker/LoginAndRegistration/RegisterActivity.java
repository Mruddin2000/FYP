package com.example.progressChecker.LoginAndRegistration;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.progressChecker.DBHelper;
import com.example.progressChecker.R;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterActivity extends AppCompatActivity {

    Button signin;
    EditText username, EditTextPassword, EditTextRePassword, EditTextEmail;
    TextInputLayout password, repassword;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();
        username   = findViewById(R.id.editTextName);
        EditTextPassword = findViewById(R.id.editTextPassword);
        EditTextRePassword = findViewById(R.id.editTextRePassword);
        EditTextEmail = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.textInputPassword);
        repassword = findViewById(R.id.textInputRePassword);

        signin = findViewById(R.id.RegisterButton);
        DB = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = EditTextPassword.getText().toString();
                String repass = EditTextRePassword.getText().toString();
                String email = EditTextEmail.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("") || email.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        Boolean checkemail = DB.checkemail(email);
                        if(checkuser==false || checkemail==false){
                            Boolean insert = DB.insertData(user, pass, email);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }



}
