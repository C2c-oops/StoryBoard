package com.c2.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.c2.studentmanagementsystem.helper.DbRegisterHelper;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        listView = findViewById(R.id.lvView);

        DbRegisterHelper md = new DbRegisterHelper(getApplicationContext());
        ArrayList<String> al = md.showStudents();
        ArrayAdapter<String> ad = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,al);
        listView.setAdapter(ad);
    }
}
