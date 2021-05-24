package com.nextstacks.sharedprefernce.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.nextstacks.sharedprefernce.R;

import java.util.ArrayList;

public class ViewStudentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        RecyclerView recyclerView = findViewById(R.id.rc_students);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        ArrayList<StudentDetails> studentDetails = dbHelper.getDataFromDatabase(dbHelper.getReadableDatabase());

        StudentDetailAdapter adapter = new StudentDetailAdapter(ViewStudentsActivity.this, studentDetails);
        recyclerView.setAdapter(adapter);
    }
}