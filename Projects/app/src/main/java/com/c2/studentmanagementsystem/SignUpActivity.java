package com.c2.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.c2.studentmanagementsystem.helper.DbRegisterHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    
    EditText regName, regId, regEmail, regPhoneNo, regPassword;
    Button button1;
    DbRegisterHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DbRegisterHelper(this);
        regName = findViewById(R.id.signUpName);
        regId = findViewById(R.id.signUpId);
        regEmail = findViewById(R.id.signUpEmail);
        regPhoneNo = findViewById(R.id.signUpPhoneNo);
        regPassword = findViewById(R.id.signUpPassword);

        button1 = findViewById(R.id.signUpButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        /*if (!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateId()) {
            return;
        }else {*/
            String name = regName.getText().toString();
            Integer regid = Integer.valueOf(regId.getText().toString());
            String email = regEmail.getText().toString();
            Integer phone = Integer.valueOf(regPhoneNo.getText().toString());
            String password = regPassword.getText().toString();

            db.addTeacher(name, regid, email, phone, password);
            displayToast("User Registered");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        /*}*/
    }

    private Boolean validateName() {
        String val = regName.getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateId() {
        String val = regId.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regId.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 6) {
            regId.setError("ID too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regId.setError("White Spaces are not allowed");
            return false;
        } else {
            regId.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            return true;
        }
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
