package com.nextstacks.sharedprefernce.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nextstacks.sharedprefernce.R;

public class AddStudentDetailActivity extends AppCompatActivity {


    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_detail);


        EditText mEtStudentName = findViewById(R.id.et_student_name);
        EditText mEtStudentRollNo = findViewById(R.id.et_student_roll);
        EditText mEtStudentBloodGroup = findViewById(R.id.et_student_blood_group);
        EditText mEtStudentPhone = findViewById(R.id.et_student_phone);
        EditText mEtStudentEmail = findViewById(R.id.et_student_email);

        Button mBtnInsert = findViewById(R.id.btn_insert_student);

        dbHelper = new DatabaseHelper(AddStudentDetailActivity.this);

        mBtnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = mEtStudentName.getText().toString();
                String studentRollNo = mEtStudentRollNo.getText().toString();
                String studentBloodGroup = mEtStudentBloodGroup.getText().toString();
                String studentPhone = mEtStudentPhone.getText().toString();
                String studentEmail = mEtStudentEmail.getText().toString();

                StudentDetails student = new StudentDetails();
//                student.setName(studentName);
                student.name = studentName;
                student.bloodGroup = studentBloodGroup;
                student.rollNo = studentRollNo;
                student.emailID = studentEmail;
                student.phoneNo = studentPhone;

                dbHelper.insertDataToDatabase(dbHelper.getWritableDatabase(), student);


                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}