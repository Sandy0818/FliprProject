package com.example.fliprproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PopupWindow extends AppCompatDialogFragment{

    String title;
    private CallBackListener callBackListener;

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_popup_window, container, true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("AAT", "Reached onActivityCreated");
        //getActivity() is fully created in onActivityCreated and instanceOf differentiate it between different Activities
        if (getActivity() instanceof CallBackListener)
            callBackListener = (CallBackListener) getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView Tv_title = view.findViewById(R.id.TV_title);
        Tv_title.setText(title);

        Button add_btn = view.findViewById(R.id.add_btn);
        Button can_btn = view.findViewById(R.id.cancel_btn);
        final EditText user_title = view.findViewById(R.id.title_name);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //putData();
                Log.d("board", user_title.getText().toString());

                if (callBackListener != null) {
                    getActivity().getFragmentManager().popBackStack();
                    callBackListener.onCallBack();
                }
            }
        });

        can_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (callBackListener != null) {
                    getActivity().getFragmentManager().popBackStack();
                    callBackListener.onCallBack();
                }
            }
        });

    }
}
