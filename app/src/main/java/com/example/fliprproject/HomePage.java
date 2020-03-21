package com.example.fliprproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements CallBackListener{

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
        setContentView(R.layout.activity_home_page);

        Button new_board = findViewById(R.id.add_board);
        Button trial = findViewById(R.id.test_btn);

        new_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openBoardPrompt();
            }
        });

        trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, DisplayBoard.class);
                intent.putExtra("Board Name", "TRIAL BOARD");
                startActivity(intent);
            }
        });
    }

    public void openBoardPrompt()
    {
        PopupWindow popup_dialog = new PopupWindow();
        popup_dialog.setTitle("NEW BOARD");
        popup_dialog.show(getSupportFragmentManager(),"example");
    }
}
