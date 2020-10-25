package com.niteshb.apis;

import com.niteshb.models.JobModel;
import com.niteshb.models.LoginRequestModel;
import com.niteshb.models.LoginResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {
    @POST("auth")
    Call<LoginResponseModel> userLogin(@Body LoginRequestModel loginRequest);


    @GET("jobs")
    Call<ArrayList<JobModel>> getJobs(@Header("Authorization") String token);
}
