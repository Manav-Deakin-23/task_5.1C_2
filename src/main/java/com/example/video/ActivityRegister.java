package com.example.video;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister extends AppCompatActivity {
    EditText etUser, etPwd, etRepwd;
    Button btnRegister, btnGoToLogin;
    DBHelper dbHelper;
    String url;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUser = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPassword);
        etRepwd = findViewById(R.id.etRePassword);
        btnRegister = findViewById(R.id.btnRegister);
        dbHelper = new DBHelper(this);
        btnGoToLogin = findViewById(R.id.btnLogin);
        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user, pwd, rePwd;
                user = etUser.getText().toString();
                pwd = etPwd.getText().toString();
                rePwd = etRepwd.getText().toString();
                if (user.equals("") || pwd.equals("") || rePwd.equals("")){
                    Toast.makeText(ActivityRegister.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    if (pwd.equals(rePwd)){
                        if (dbHelper.checkUsername(user)){
                            Toast.makeText(ActivityRegister.this, "User Already Exists", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean registerSuccess = dbHelper.insertUserData(user, pwd);
                       if (registerSuccess)
                           Toast.makeText(ActivityRegister.this, "Registration Successfully", Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(ActivityRegister.this, "Registration Failed", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(ActivityRegister.this, "Password does not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
