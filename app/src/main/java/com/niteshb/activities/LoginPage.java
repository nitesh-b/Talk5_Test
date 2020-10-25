package com.niteshb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.niteshb.apis.ApiClient;
import com.niteshb.apis.RetrofitClient;
import com.niteshb.models.LoginRequestModel;
import com.niteshb.models.LoginResponseModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private EditText mAccessCode, mPassword;
    private Button mLoginButton;
    private TextView mResetPage, mSignup;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAccessCode = findViewById(R.id.access_code);
        mPassword = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(this);
        mResetPage = findViewById(R.id.forgot_your_details);
        mResetPage.setOnClickListener(this);
        mSignup = findViewById(R.id.signup);
        mSignup.setOnClickListener(this);

        mProgressBar = findViewById(R.id.login_progress_bar);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                mProgressBar.setVisibility(View.VISIBLE);
                login();
                break;
            case R.id.signup:
                startActivity(new Intent(this, SignupPage.class));
                break;
            case R.id.forgot_your_details:
                Toast.makeText(this, "URL to forgot password", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void login(){
        /*User Validation First*/
        String accessCode = mAccessCode.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if(accessCode.isEmpty()){
            mAccessCode.setError("Email Required!");
            mAccessCode.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(accessCode).matches()){
            mAccessCode.setError("Enter Valid Email!");
            mAccessCode.requestFocus();
            return;
        }

        if(password.isEmpty()){
            mPassword.setError("Password Required!");
            mPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            mPassword.setError("Password should be atleast 6 character!");
            mPassword.requestFocus();
            return;
        }
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setAccessCode(accessCode);
        loginRequestModel.setPassword(password);
        loginRequestModel.setGrant_type("password");

        Call<LoginResponseModel> loginResponseModelCall = ApiClient.getUserService().userLogin(loginRequestModel);
        loginResponseModelCall.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                mProgressBar.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    LoginResponseModel loginResponseModel = response.body();
                    startJobPage(loginResponseModel);
                }else{
                    Toast.makeText(LoginPage.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(LoginPage.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startJobPage(LoginResponseModel receivedResponse) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoginPage.this, JobPage.class).putExtra("result", receivedResponse));
            }
        },500);
    }
}