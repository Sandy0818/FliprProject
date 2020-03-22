package com.example.fliprproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DisplayCard extends AppCompatActivity {

    EditText label, sel_date, checklist;
    Button save_changes, add_checklist;
    List<String> todo_list = new ArrayList<>();       //read lest of topics of given subject from db
    ArrayList<Boolean> status = new ArrayList<>();

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_card);

        label = findViewById(R.id.ed_label);
        sel_date = findViewById(R.id.ed_date);
        checklist = findViewById(R.id.ed_checklist);
        final LinearLayout disp_checklist = findViewById(R.id.disp_checklist);
        save_changes = findViewById(R.id.save_card);
        add_checklist = findViewById(R.id.add_checklist);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                updateLabel(dayOfMonth, monthOfYear, year);
            }

        };

        sel_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(DisplayCard.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //read from db

        try
        {
            save_changes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //write to db
                    String card_label = label.getText().toString();
                    String card_date = sel_date.getText().toString();

                    int cb_count = disp_checklist.getChildCount();
                    Log.d("card", String.valueOf(cb_count));

                    for(int i = 0; i < cb_count; i++)
                    {
                        CheckBox cb = (CheckBox) disp_checklist.getChildAt(i);
                        todo_list.add(i, cb.getText().toString());

                        if(cb.isChecked())
                            status.add(i, true);
                        else
                            status.add(i, false);
                    }

                    Log.d("card", status.toString());
                    Log.d("card", todo_list.toString());
                    Log.d("card", card_label);
                    Log.d("card", card_date);
                }
            });
        }
        catch (NullPointerException e)
        {
            Log.d("card", "error");
        }


        add_checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String topic = checklist.getText().toString();
                Toast.makeText(getApplicationContext(), topic, Toast.LENGTH_SHORT).show();

                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(topic);
                cb.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                cb.setTextColor(Color.DKGRAY);
                //cb.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                cb.setTextSize(15);

                checklist.setText("");
                checklist.setHint("Enter checklist");

                disp_checklist.addView(cb);
            }
        });
    }

    private void updateLabel(int date, int month, int yr) {

        String slc_date = date + "-" + month + "-" + yr;
        sel_date.setText(slc_date);
    }
}
