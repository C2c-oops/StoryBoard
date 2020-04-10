package com.c2.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.c2.studentmanagementsystem.helper.DbRegisterHelper;

public class UpdateActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editText1 = findViewById(R.id.etUpdateName);
        editText2 = findViewById(R.id.etUpdateSection);
        editText3 = findViewById(R.id.etUpdatePhone);
        editText4 = findViewById(R.id.etUpdateFees);

        button = findViewById(R.id.btUpdate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbRegisterHelper md=new DbRegisterHelper(getApplicationContext());
                md.updateStudents(editText1.getText().toString(),
                        editText1.getText().toString(),
                        Integer.valueOf(editText3.getText().toString()),
                        Integer.valueOf(editText4.getText().toString()));
                finish();
            }
        });
    }
}
