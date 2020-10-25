package com.niteshb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.niteshb.apis.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupPage extends AppCompatActivity {

    private EditText mSignupAccessCode, mSignUpPassword;
    private Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        mSignupAccessCode = findViewById(R.id.signup_access_code);
        mSignUpPassword = findViewById(R.id.signup_password);
        mSignupButton = findViewById(R.id.signup_button);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signupUser();
            }
        });

    }

    private void signupUser() {
        String accessCode = mSignupAccessCode.getText().toString().trim();
        String password = mSignUpPassword.getText().toString().trim();
        /*User Validation First*/
        if(accessCode.isEmpty()){
            mSignupAccessCode.setError("Email Required!");
            mSignupAccessCode.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(accessCode).matches()){
            mSignupAccessCode.setError("Enter Valid Email!");
            mSignupAccessCode.requestFocus();
            return;
        }

        if(password.isEmpty()){
            mSignUpPassword.setError("Password Required!");
            mSignUpPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            mSignUpPassword.setError("Password should be atleast 6 character!");
            mSignUpPassword.requestFocus();
            return;
        }
        /*If Validation passes, proceed with registration using API call*/
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getCreateUserApi()
                .createUser(accessCode, password, "password");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        Toast.makeText(SignupPage.this, response.body().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    String error = response.message();
                    Toast.makeText(SignupPage.this, error, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignupPage.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}