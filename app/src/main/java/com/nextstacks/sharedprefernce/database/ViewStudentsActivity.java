package com.nextstacks.sharedprefernce.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nextstacks.sharedprefernce.R;

import java.util.ArrayList;

public class ViewStudentsActivity extends AppCompatActivity implements StudentDetailAdapter.StudentDetailClickListener {

    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        recyclerView = findViewById(R.id.rc_students);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));

        dbHelper = new DatabaseHelper(this);

        loadDataToRecycler();
    }

    public void onAddNewEntryClicked(View view) {
        startActivityForResult(new Intent(ViewStudentsActivity.this, AddStudentDetailActivity.class), 134);
    }

    private void loadDataToRecycler() {
        ArrayList<StudentDetails> studentDetails = dbHelper.getDataFromDatabase(dbHelper.getReadableDatabase());
        StudentDetailAdapter adapter = new StudentDetailAdapter(ViewStudentsActivity.this, studentDetails);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 134) {
            if (resultCode == Activity.RESULT_OK) {
                loadDataToRecycler();
            }
        }
    }


    @Override
    public void onEditClicked(StudentDetails studen) {
        Intent editIntent = new Intent(ViewStudentsActivity.this, AddStudentDetailActivity.class);
        editIntent.putExtra("STUDENT", studen);
        editIntent.putExtra("ISEDIT", true);
        startActivityForResult(editIntent, 134);
    }

    @Override
    public void onDeleteClicked(StudentDetails detail) {
        dbHelper.deleteDataFromDatabase(dbHelper.getWritableDatabase(), detail);
        loadDataToRecycler();
    }
}