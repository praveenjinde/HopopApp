package com.hopop.hopop.destination.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.AbsListView;
import android.widget.TextView;

import com.hopop.hopop.ply.activity.PlyActivity;
import com.hopop.hopop.ply.R;

public class DestinationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    AutoCompleteTextView search;

    String[] values = new String[] {"ITPL","Bagmane Tech Park(CVR)","EcoSpace Tech Park","Whitefield","Hebbal",
            "Manyata Tech Park","Jayadeva 4th Block","Peenya Industrial Layout","RajaRajeshwari Nagar","MG Road",
            "Shivaji Nagar","Electronic City","Jigini","Sarajapur","Kormangala Police station","Jakkasandra","HSR Layout"};
    //  Intent intent = getIntent();
//   String src = intent.getExtras().getString("src");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.DropHeader);
        setContentView(R.layout.activity_destination);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setSelector(android.R.color.holo_red_light);
        search= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        search.setThreshold(1);//will start working from first character
        search.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

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
                Intent intent_6 = new Intent(DestinationActivity.this, PlyActivity.class);
                //intent_6.putExtra("src", src);
                intent_6.putExtra("dest", destSelect);
                startActivity(intent_6);

            }


        });
        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                Intent intent_6 = new Intent(DestinationActivity.this, PlyActivity.class);
                //intent_6.putExtra("src", src);
                intent_6.putExtra("dest", destSelect);
                startActivity(intent_6);

            }


        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {

        } else if (id == R.id.booking) {

        } else if (id == R.id.wallet) {

        } else if (id == R.id.route) {

        } else if (id == R.id.notification) {

        } else if (id == R.id.feedback) {

        } else if (id == R.id.about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
