package com.niteshb.apis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiCalls {
    @FormUrlEncoded
    @POST("createuser")
    Call<ResponseBody> createUser(
            @Field("accessCode") String accessCode,
            @Field("password") String password,
            @Field("grant_type") String grant_type
    );

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("auth")
    Call<ResponseBody> userLogin(
            @Field("accessCode") String accessCode,
            @Field("password") String password,
            @Field("grant_type") String grant_type


    );
}
