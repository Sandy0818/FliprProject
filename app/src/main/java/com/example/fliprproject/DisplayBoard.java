package com.example.fliprproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayBoard extends AppCompatActivity implements CallBackListener {

    Bundle extras;
    String board_name;

    @Override
    public void onCallBack() {
        Toast.makeText(this,"onCallback Called",Toast.LENGTH_LONG).show();
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e){}

        //getfromdb();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_board);

        TextView list_title = findViewById(R.id.TV_List);

        Button add_list = findViewById(R.id.add_list);
        Button test_btn = findViewById(R.id.test_btn);

        extras = getIntent().getExtras();
        if(extras == null)
            board_name = null;
        else
            board_name = (String) extras.get("Board Name");

        list_title.setText(board_name);

        add_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListPrompt();
            }
        });

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayBoard.this, DisplayList.class);
                intent.putExtra("List Name", "TRIAL LIST");
                startActivity(intent);
            }
        });
    }

    public void openListPrompt()
    {
        PopupWindow popup_dialog = new PopupWindow();
        popup_dialog.setTitle("NEW LIST");
        popup_dialog.show(getSupportFragmentManager(),"example");
    }
}
