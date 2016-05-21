package com.hopop.hopopv10.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import com.facebook.stetho.Stetho;
import com.hopop.hopopv10.communicators.CommunicatorClass;
import com.hopop.hopopv10.communicators.builder.LoginUser;
import com.hopop.hopopv10.response.Registerresponse;

import android.view.View.OnClickListener;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;


public class LoginActivity extends AppCompatActivity {

    EditText mobile,pass;
    Button Login, SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mobile=(EditText)findViewById(R.id.editText_mn);
        pass=(EditText)findViewById(R.id.editText_Psw);
        Login = (Button) findViewById(R.id.button_Login);
        SignUp = (Button) findViewById(R.id.button_signup);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            CommunicatorClass communicatorClass = new CommunicatorClass();
                LoginUser loginUser = new LoginUser();
                loginUser.setMobile_number(mobile.getText().toString().trim());
                loginUser.setPassword(pass.getText().toString().trim());
                //Log.d(getClass().getSimpleName(),"on submit button pressed");
                Log.d("RANDOM TAG", "on submit button pressed");
                CommunicatorClass.getRegisterClass().groupListLogin(loginUser).enqueue(new Callback<Registerresponse>() {
                    @Override
                    public void onResponse(Call<Registerresponse> call, Response<Registerresponse> response) {
                        Toast.makeText(LoginActivity.this, "Login SuccessFully", Toast.LENGTH_SHORT).show();
                        Intent searchIntent = new Intent(LoginActivity.this, SearchActivity.class);
                        startActivity(searchIntent);

                        Log.e(getClass().getSimpleName(), "successful");

                    }

                    @Override
                    public void onFailure(Call<Registerresponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Invalid Mobile Number/Password", Toast.LENGTH_SHORT).show();
                        Log.e(getClass().getSimpleName(), "failure");

                    }


                });

        }});


        //When Button Sign up clicked
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }


        });
    }

    //checking field are empty
    private boolean CheckFieldValidation(){

        boolean valid=true;
        String Mobile = mobile.getText().toString();
        String Password = pass.getText().toString();

        if (Mobile.length() == 0) {
            mobile.requestFocus();
            mobile.setError("FIELD CANNOT BE EMPTY");
        } else if (!Mobile.matches("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$")) {
            mobile.requestFocus();
            mobile.setError("ENTER Valid Mobile Number");
        } else if (Password.length() == 0) {
            pass.requestFocus();
            pass.setError("FIELD CANNOT BE EMPTY");
        }
        else
        {
            Toast.makeText(LoginActivity.this, "Login SuccessFully", Toast.LENGTH_LONG).show();
            //Intent searchIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            //startActivity(searchIntent);
        }

        return valid;
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