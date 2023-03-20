package com.example.staffin.Interface;

import com.example.staffin.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("admin-login")
    Call<LoginResponse> postLoginResponse(@Field("mobile") String mobile,
                                          @Field("password") String password);
}
