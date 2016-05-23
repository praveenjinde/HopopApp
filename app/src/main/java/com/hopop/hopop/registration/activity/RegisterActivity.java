package com.hopop.hopop.registration.activity;

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
import android.widget.Toast;
import android.content.Intent;

import com.hopop.hopop.activities.R;
import com.hopop.hopop.activities.SearchActivity;
import com.hopop.hopop.communicators.CommunicatorClass;
import com.hopop.hopop.communicators.builder.RegisterUser;
import com.hopop.hopop.response.Registerresponse;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Bind(R.id.editText_mn) EditText mobile;
    @Bind(R.id.editText_Psw) EditText pass;
    @Bind(R.id.editText_fn) EditText fName;
    @Bind(R.id.editText_ln) EditText lName;
    @Bind(R.id.editText_email) EditText email;

        @OnClick(R.id.button_Done)
        public void signUpUser(View view){
                if(checkFieldValidation()){
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
            }

    @OnClick(R.id.button_Lin)
    public void linkedInUser(View view){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/uas/login"));
                startActivity(browserIntent);

            }

    @OnClick(R.id.button_fb)
    public void facebookUser(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/login/"));
        startActivity(browserIntent);
    }

            @OnTouch(R.id.Button_eye)
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

    //checking field are empty
    private boolean checkFieldValidation() {

        boolean valid=true;
        String fNameValidation = fName.getText().toString();
        String lNameValidation = lName.getText().toString();
        String emailValidation = email.getText().toString();
        String mobileValidation = mobile.getText().toString();
        String passwordValidation = pass.getText().toString();

        if(fName.length()==0)
        {
            fName.requestFocus();
            fName.setError("Field Cann't be Empty");
        }
        else if(!fNameValidation.matches("[a-zA-Z ]+"))
        {
            fName.requestFocus();
            fName.setError("Enter Only Alphabetical Character");
        }

        else if(lName.length()==0)
        {
            lName.requestFocus();
            lName.setError("Field Cann't be Empty");
        }
        else if(!lNameValidation.matches("[a-zA-Z ]+"))
        {
            lName.requestFocus();
            lName.setError("Enter Only Alphabetical Character");
        }

        else if(email.length()==0)
        {
            email.requestFocus();
            email.setError("Field Cann't be Empty");
        }
        else if(!emailValidation.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
        {
            email.requestFocus();
            email.setError("Enter Valid Email Id");
        }

        else if(mobile.length()==0)
        {
            mobile.requestFocus();
            mobile.setError("Field Cann't be Empty");
        }
        else if(!mobileValidation.matches("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$"))
        {
            mobile.requestFocus();
            mobile.setError("Enter Valid Mobile Number");
        }

        else if(pass.length()==0)
        {
            pass.requestFocus();
            pass.setError("Field Cann't be Empty");
        }
        else
        {
            Toast.makeText(RegisterActivity.this, "Registration SuccessFully", Toast.LENGTH_LONG).show();
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