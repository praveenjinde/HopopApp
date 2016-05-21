package com.hopop.hopopv10.activities;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hopop.hopopv10.adapter.ServiceAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlyActivity extends AppCompatActivity {

    TextView srcTxt,destTxt,row1,row2,row3;

    ListView mServiceListview;
    ServiceAdapter mAdapter;
    List<Service> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.PlyHeader);
        setContentView(R.layout.activity_ply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        srcTxt = (TextView) findViewById(R.id.textView_pickpoint);
        destTxt = (TextView) findViewById(R.id.textView_droppoint);

        String SRC = getIntent().getStringExtra("src");
        String DST = getIntent().getStringExtra("dest");

        srcTxt.setText(SRC);
        destTxt.setText(DST);

        srcTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentsrcTxt = new Intent(PlyActivity.this, SearchActivity.class);
                // intent_5.putExtra("src", srcSelect);
                startActivity(intentsrcTxt);

            }
        });
        destTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdestTxt = new Intent(PlyActivity.this, SearchActivity.class);
                // intent_5.putExtra("src", srcSelect);
                startActivity(intentdestTxt);

            }
        });


        mServiceListview = (ListView) findViewById(R.id.lv_service_availiability);

        mData.clear();
        mData.add(new Service("12 Seats Available", "Filling Fast", "In Next: <b>15min</b>", R.color.status_red, R.drawable.redbus));
        mData.add(new Service("25 Seats Available", "Slowly Filling", "In Next:  <b>40min</b>", R.color.status_orange, R.drawable.orangebus));
        mData.add(new Service("42 Seats Available", "Booking Started", "In Next:  <b>55min</b>", R.color.status_green, R.drawable.greenbus));

        mAdapter = new ServiceAdapter(this, mData);
        mServiceListview.setAdapter(mAdapter);

        mServiceListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentRow3 = new Intent(PlyActivity.this, PaymentActivty.class);
                startActivity(intentRow3);
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
