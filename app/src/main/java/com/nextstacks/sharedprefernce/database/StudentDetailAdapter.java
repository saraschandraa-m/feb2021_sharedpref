package com.nextstacks.sharedprefernce.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextstacks.sharedprefernce.R;

import java.util.ArrayList;

public class StudentDetailAdapter extends RecyclerView.Adapter<StudentDetailAdapter.StudentDetailHolder> {

    private Context context;
    private ArrayList<StudentDetails> studentList;

    public StudentDetailAdapter(Context context, ArrayList<StudentDetails> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentDetailHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View v1 = LayoutInflater.from(context).inflate(R.layout.cell_student_detail, container, false);
        StudentDetailHolder holder = new StudentDetailHolder(v1);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentDetailHolder holder, int position) {
        StudentDetails item = studentList.get(position);

        holder.mTvName.setText(item.name);
        holder.mTvRollNo.setText(item.rollNo);
        holder.mTvEmail.setText(item.emailID);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentDetailHolder extends RecyclerView.ViewHolder {

        private TextView mTvName;
        private TextView mTvRollNo;
        private TextView mTvEmail;

        public StudentDetailHolder(@NonNull View itemView) {
            super(itemView);

            mTvName = itemView.findViewById(R.id.tv_student_name);
            mTvRollNo = itemView.findViewById(R.id.tv_student_roll_no);
            mTvEmail = itemView.findViewById(R.id.tv_student_email);
        }
    }
}
