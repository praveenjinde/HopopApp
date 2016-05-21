package com.hopop.hopopv10.activities;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AbsListView;
import android.widget.TextView;

public class SearchActivity1 extends AppCompatActivity {

    ListView listView;

    String[] values = new String[] {"ITPL","Bagmane Tech Park(CVR)","EcoSpace Tech Park","Whitefield","Hebbal",
            "Manyata Tech Park","Jayadeva 4th Block","Peenya Industrial Layout","RajaRajeshwari Nagar","MG Road",
            "Shivaji Nagar","Electronic City","Jigini","Sarajapur","Kormangala Police station","Jakkasandra","HSR Layout"};
    //  Intent intent = getIntent();
//   String src = intent.getExtras().getString("src");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.DropHeader);
        setContentView(R.layout.activity_search1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String src = intent.getExtras().getString("src");

        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setSelector(android.R.color.holo_red_light);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String dest = (String) listView.getItemAtPosition(position);
                //   String destSelect = listView.getSelectedItem().toString();
                String destSelect = ((TextView)view).getText().toString();

                //Toast.makeText(getApplicationContext(),
                //"Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG).show();
                Intent intent_6 = new Intent(SearchActivity1.this, PlyActivity.class);
                intent_6.putExtra("src", src);
                intent_6.putExtra("dest", destSelect);
                startActivity(intent_6);

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
