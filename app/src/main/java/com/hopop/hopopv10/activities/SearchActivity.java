package com.hopop.hopopv10.activities;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AbsListView;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    public static String srcSelect =null;


    String[] values = new String[]{"KBS (Majestic)", "Banashankari", "Shivajinagar", "K. R. Market",
            "Shantinagar Bus Stand", "K.R.Pura", "Electronic City", "Marathahalli", "Vijaya Nagar",
            "ITPL", "Bapuji Nagar", "Magadi Road", "Rajaji Nagar", "Yashwanthpur", "Mysore Circle",
            "Jayanagar", "Koramangala", "Hebbala", "Chamaraja Nagar"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.PickHeader);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // editText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setSelector(android.R.color.holo_red_light);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String src = (String) listView.getItemAtPosition(position);

                //   String srcSelect = listView.getSelectedItem().toString();
                // String srcSelect = ((TextView)view).getText().toString();
                srcSelect = ((TextView)view).getText().toString();

                //Toast.makeText(getApplicationContext(),
                //"Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG).show();

                Intent intent_5 = new Intent(SearchActivity.this, SearchActivity1.class);
                intent_5.putExtra("src", srcSelect);
                startActivity(intent_5);


            }


        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen for landscape and portrait and set portrait mode always
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
