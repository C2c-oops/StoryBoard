package com.c2.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.c2.studentmanagementsystem.helper.DbRegisterHelper;

public class DeleteActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        editText = findViewById(R.id.etDeleteName);
        button = findViewById(R.id.btDelete);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbRegisterHelper md = new DbRegisterHelper(getApplicationContext());
                md.deleteStudents(editText.getText().toString());
                Toast.makeText(DeleteActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
