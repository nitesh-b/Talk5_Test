package com.niteshb.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.niteshb.adapters.JobsAdapter;
import com.niteshb.apis.ApiClient;
import com.niteshb.models.JobModel;
import com.niteshb.models.LoginResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobPage extends AppCompatActivity {

    private static final String TAG = "JobPageActivity";
    private TextView usernameTextView;
    private RecyclerView jobRecyclerView;
    private JobsAdapter jobsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_page);
        LoginResponseModel model = (LoginResponseModel) getIntent().getSerializableExtra("result");
        Log.d(TAG, "onCreate: Serializable" + model.getCompany_name());
        usernameTextView = findViewById(R.id.display_username);
        usernameTextView.setText(model.getFull_name());
        jobRecyclerView = findViewById(R.id.job_recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jobsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        getJobs(model);
    }

    private void getJobs(LoginResponseModel model) {
        Log.d(TAG, "JobPage: getJobs: access token => " + model.getAccess_token());

        /*Make an API Call*/
        Call<ArrayList<JobModel>> jobModelCall = ApiClient.getUserService().getJobs("Bearer " + model.getAccess_token());
        jobModelCall.enqueue(new Callback<ArrayList<JobModel>>() {
            @Override
            public void onResponse(Call<ArrayList<JobModel>> call, Response<ArrayList<JobModel>> response) {
                Log.d(TAG, "Job Page: onResponse:" + (response));

                if (response.isSuccessful()) {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
                    jobRecyclerView.setLayoutManager(layoutManager);
                    jobsAdapter = new JobsAdapter(response.body(), getBaseContext());
                    jobRecyclerView.setAdapter(jobsAdapter);
                } else {
                    Toast.makeText(JobPage.this, "Failed to get jobs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<JobModel>> call, Throwable t) {
                Toast.makeText(JobPage.this, "Process failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}