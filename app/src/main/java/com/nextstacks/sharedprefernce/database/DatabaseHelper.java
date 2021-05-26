package com.nextstacks.sharedprefernce.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "student_detail";
    private static final String COL_NAME = "name";
    private static final String COL_ROLLNO = "roll_no";
    private static final String COL_BLOODGROUP = "blood_group";
    private static final String COL_PHONE = "phone";
    private static final String COL_EMAIL = "email";

    //insert table_name(name varchar, roll int) values (5, 2);

    //CREATE TABLE student_detail(roll_no TEXT PRIMARY KEY,name TEXT,blood_group TEXT,phone TEXT,email TEXT);
//    "CREATE TABLE "+TABLE_NAME+"("+COL_ROLLNO+" TEXT PRIMARY KEY,"+COL_NAME+" TEXT,"+COL_BLOODGROUP+" TEXT,"+COL_PHONE+" TEXT,+COL_EMAIL +" TEXT);";
    private static final String CREATE_TABLE = "CREATE TABLE student_detail(roll_no TEXT PRIMARY KEY,name TEXT,blood_group TEXT,phone TEXT,email TEXT)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "studentdetailinfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertDataToDatabase(SQLiteDatabase database, StudentDetails student) {
        ContentValues cv = new ContentValues();
        cv.put(COL_ROLLNO, student.rollNo);
        cv.put(COL_NAME, student.name);
        cv.put(COL_EMAIL, student.emailID);
        cv.put(COL_PHONE, student.phoneNo);
        cv.put(COL_BLOODGROUP, student.bloodGroup);

        database.insert(TABLE_NAME, null, cv);
    }

    public ArrayList<StudentDetails> getDataFromDatabase(SQLiteDatabase database) {
        ArrayList<StudentDetails> studentList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                StudentDetails data = new StudentDetails();
                data.rollNo = cursor.getString(cursor.getColumnIndex(COL_ROLLNO));
                data.phoneNo = cursor.getString(cursor.getColumnIndex(COL_PHONE));
                data.name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                data.bloodGroup = cursor.getString(cursor.getColumnIndex(COL_BLOODGROUP));
                data.emailID = cursor.getString(cursor.getColumnIndex(COL_EMAIL));

                studentList.add(data);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return studentList;
    }

    public void deleteDataFromDatabase(SQLiteDatabase database, StudentDetails student) {
        database.delete(TABLE_NAME, COL_ROLLNO + "=" + student.rollNo, null);
    }
}
