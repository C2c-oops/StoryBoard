package com.c2.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.c2.studentmanagementsystem.helper.DbRegisterHelper;

public class AddActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editText1 = findViewById(R.id.etRegNo);
        editText2 = findViewById(R.id.etName);
        editText3 = findViewById(R.id.etSection);
        editText4 = findViewById(R.id.etEmail);
        editText5 = findViewById(R.id.etPhone);
        editText6 = findViewById(R.id.etFees);

        button = findViewById(R.id.btAdd);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbRegisterHelper md=new DbRegisterHelper(getApplicationContext());
                md.addStudents(Integer.valueOf(editText1.getText().toString()),
                        editText2.getText().toString(),
                        editText3.getText().toString(),
                        editText4.getText().toString(),
                        Integer.valueOf(editText5.getText().toString()),
                        Integer.valueOf(editText6.getText().toString()));
                Toast.makeText(AddActivity.this, "Added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}
