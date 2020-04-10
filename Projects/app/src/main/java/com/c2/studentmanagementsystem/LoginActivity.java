package com.c2.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.c2.studentmanagementsystem.helper.DbRegisterHelper;
import com.c2.studentmanagementsystem.session.Session;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    EditText loginID, loginPass;
    Button button1, button2;
    DbRegisterHelper db;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DbRegisterHelper(this);
        session = new Session(this);

        loginID = findViewById(R.id.loginId);
        loginPass = findViewById(R.id.loginPassword);
        button1 = findViewById(R.id.loginButton);
        button2 = findViewById(R.id.loginSignUpButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        Integer id = Integer.valueOf(loginID.getText().toString());
        String password = loginPass.getText().toString();

        if(db.getTeacher(id, password)){
            session.setLoggedIn(true);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else{
            Toast.makeText(this, "Wrong Username/password", Toast.LENGTH_SHORT).show();
        }
    }
}
