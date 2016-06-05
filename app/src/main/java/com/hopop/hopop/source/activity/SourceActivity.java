package com.hopop.hopop.source.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.AbsListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hopop.hopop.communicators.CommunicatorClass;
import com.hopop.hopop.database.FromRoute;
import com.hopop.hopop.ply.R;
import com.hopop.hopop.destination.activity.DestinationActivity;
import com.hopop.hopop.login.activity.LoginActivity;
import com.hopop.hopop.response.Registerresponse;
import com.hopop.hopop.source.RecyclerAdapter;
import com.hopop.hopop.source.data.SourceList;
import com.orm.query.Condition;
import com.orm.query.Select;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    EditText editText;
    Toolbar toolbar;
    private ArrayList<SourceList> data;
    //public static String srcSelect =null;
    private static final int TIME_DELAY = 3000;
    private static long back_pressed;
    AutoCompleteTextView search;

    @Bind(R.id.source_list)
    RecyclerView source_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.PickHeader);
        setContentView(R.layout.activity_source);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        search= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        search.setThreshold(1);//will start working from first character
        Call<SourceList> sourceList1 = CommunicatorClass.getRegisterClass().groupListSrc();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        source_list.setLayoutManager(layoutManager);
        source_list.setItemAnimator(new DefaultItemAnimator());
        sourceList1.enqueue(new Callback<SourceList>() {
            @Override
            public void onResponse(Call<SourceList> call, Response<SourceList> response) {
                Toast.makeText(SourceActivity.this, "In source Activity Login SuccessFully", Toast.LENGTH_SHORT).show();
                SourceList sl = response.body();
                for(FromRoute fromRoute: sl.getFromRoutes()){
                    if(FromRoute.isNew(fromRoute.getStopLocation())) {
                        fromRoute.save();
                    }
                }
                List<FromRoute> list1 = Select.from(FromRoute.class).list();
                for(FromRoute frmRout:list1){
                    Log.e(getClass().getSimpleName(),"the db item is "+frmRout);
                }

                displayTheList(list1);
                ListView listView = (ListView) findViewById(R.id.listView);

            }

            @Override
            public void onFailure(Call<SourceList> call, Throwable t) {
                Toast.makeText(SourceActivity.this, "Invalid Mobile Number/Password", Toast.LENGTH_SHORT).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        //  drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void displayTheList(final List<FromRoute> fromRoutes){
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(fromRoutes,getApplicationContext());
        source_list.setAdapter(recyclerAdapter);
        ((RecyclerAdapter)recyclerAdapter).setOnItemClickListener(new RecyclerAdapter.ItemClickListenr() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(getClass().getSimpleName(),"the item clicked is "+fromRoutes.get(position).getStopLocation());

                //write your on click of an item what has to be done .....
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();

        Intent intent = new Intent(SourceActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // clears all previous activities task
        finish(); // destroy current activity
        startActivity(intent); // starts new activity

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@SuppressWarnings("StatementWithEmptyBody")
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