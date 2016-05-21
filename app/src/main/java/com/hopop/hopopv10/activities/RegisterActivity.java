package com.hopop.hopopv10.activities;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;
import android.view.View.OnClickListener;

import com.hopop.hopopv10.communicators.CommunicatorClass;
import com.hopop.hopopv10.communicators.builder.RegisterUser;
import com.hopop.hopopv10.response.Registerresponse;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    EditText email, pass, fName, lName, mobile;
    Button SignUp, linkedIn, facebook;
    ImageButton eye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //ButterKnife.bind(this);
        email = (EditText) findViewById(R.id.editText_email);
        pass = (EditText) findViewById(R.id.editText_Psw);
        fName = (EditText) findViewById(R.id.editText_fn);
        lName = (EditText) findViewById(R.id.editText_ln);
        mobile = (EditText) findViewById(R.id.editText_mn);
        SignUp = (Button) findViewById(R.id.button_Done);
        eye = (ImageButton) findViewById(R.id.Button_eye);
        linkedIn = (Button) findViewById(R.id.button_Lin);
        facebook = (Button) findViewById(R.id.button_Lin);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    /*@OnClick(R.id.button_signup)
    public void signUpUser(View view){*/
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser registerUser = new RegisterUser();
                registerUser.setFirst_name(fName.getText().toString().trim());
                registerUser.setLast_name(lName.getText().toString().trim());
                registerUser.setMail_id(email.getText().toString().trim());
                registerUser.setMobile_number(mobile.getText().toString().trim());
                registerUser.setPassword(pass.getText().toString().trim());
                Log.d("RANDOM TAG", "on submit button pressed");
                CommunicatorClass.getRegisterClass().groupListReg(registerUser).enqueue(new Callback<Registerresponse>() {
                    @Override
                    public void onResponse(Call<Registerresponse> call, Response<Registerresponse> response) {
                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                        Intent register = new Intent(RegisterActivity.this, SearchActivity.class);
                        startActivity(register);
                        Log.e(getClass().getSimpleName(), "successful");

                    }

                    @Override
                    public void onFailure(Call<Registerresponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Registration Unsuccessful", Toast.LENGTH_LONG).show();
                        Log.e(getClass().getSimpleName(), "failure");

                    }


                });
            }
        });




        linkedIn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View V) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/uas/login"));
                startActivity(browserIntent);

            }


        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/login/"));
                startActivity(browserIntent);
            }
        });

        eye.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pass.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                    case MotionEvent.ACTION_UP:
                        pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                }
                return true;
            }
        });


    }

    //checking field are empty
    private boolean CheckFieldValidation() {

        boolean valid = true;
        if (fName.getText().toString().equals("")) {
            fName.setError("Can't be Empty");
            valid = false;
        } else if (lName.getText().toString().equals("")) {
            lName.setError("Can't be Empty");
            valid = false;
        } else if (email.getText().toString().equals("")) {
            email.setError("Can't be Empty");
            valid = false;
        } else if (mobile.getText().toString().equals("")) {
            mobile.setError("Can't be Empty");
            valid = false;
        } else if (pass.getText().toString().equals("")) {
            pass.setError("Can't be Empty");
            valid = false;
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