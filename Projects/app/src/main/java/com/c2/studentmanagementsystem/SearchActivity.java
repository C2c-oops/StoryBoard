package com.c2.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.c2.studentmanagementsystem.helper.DbRegisterHelper;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = findViewById(R.id.etSearchName);
        listView = findViewById(R.id.lvSearch);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DbRegisterHelper md = new DbRegisterHelper(getApplicationContext());
                ArrayList<String> al = md.searchStudents(editText.getText().toString());
                ArrayAdapter<String> ad = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,al);
                listView.setAdapter(ad);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
