package com.nextstacks.sharedprefernce.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nextstacks.sharedprefernce.R;

public class ChatFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view1, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view1, savedInstanceState);

        TextView mTvTitle = view1.findViewById(R.id.tv_fragment_title);

        Bundle data = getArguments();

        if (data != null) {
            int position = data.getInt("SCREEN");

            String title = "";
            switch (position) {
                case 1:
                    title = "Status Fragment";
                    break;

                case 2:
                    title = "Calls Fragment";
                    break;

                default:
                    title = "Chat Fragment";
                    break;
            }

            mTvTitle.setText(title);
        }

    }
}
