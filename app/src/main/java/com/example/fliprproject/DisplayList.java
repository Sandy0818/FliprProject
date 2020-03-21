package com.example.fliprproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class DisplayList extends AppCompatActivity implements CallBackListener{

    Bundle extras;
    String list_name;

    ListView lv_languages;


    CardListAdapter list_adapter;
    String[] languages = new String[] { "SQL",
            "JAVA",
            "JAVA SCRIPT",
            "C#",
            "PYTHON",
            "C++",
            "PHP",
            "IOS",
            "ANDROID"
    };

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
        setContentView(R.layout.activity_display_list);

        extras = getIntent().getExtras();
        if(extras == null)
            list_name = null;
        else
            list_name = (String) extras.get("List Name");

        //Button add_list = findViewById(R.id.add_card);
        //Button test_btn = findViewById(R.id.test_btn);

        //TextView list_title = findViewById(R.id.TV_List);
        //list_title.setText("My List");

        init();
        lv_languages.setAdapter(list_adapter);

        /*add_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openListPrompt();
            }
        });*/
    }

    /*public void openListPrompt()
    {
        PopupWindow popup_dialog = new PopupWindow();
        popup_dialog.setTitle("NEW LIST");
        popup_dialog.show(getSupportFragmentManager(),"example");
    }*/

    private void init() {

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("List Cards");
        list_adapter = new CardListAdapter(this,languages, languages);
        lv_languages = (ListView) findViewById(R.id.lv_languages);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
        /*if (id == R.id.) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
