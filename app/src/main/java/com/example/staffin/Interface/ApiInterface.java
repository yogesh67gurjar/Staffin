package com.example.staffin.Interface;

import com.example.staffin.Response.AddEmployeeResponse;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Response.SingleEmployeeResponse;
import com.example.staffin.Response.TotalEmployeeResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("admin-login")
    Call<LoginResponse> postLoginResponse(@Field("mobile") String mobile,
                                          @Field("password") String password);

    @Multipart
    @POST("employee-add")
    Call<AddEmployeeResponse> postAddEmployee(@Part MultipartBody.Part profile_image,
                                              @Part("name") RequestBody name,
                                              @Part("father_name") RequestBody father_name,
                                              @Part("date_of_birth") RequestBody date_of_birth,
                                              @Part("mobile") RequestBody mobile,
                                              @Part("gender") RequestBody gender,
                                              @Part("email") RequestBody email,
                                              @Part("local_address") RequestBody local_address,
                                              @Part("parmanent_address") RequestBody parmanent_address
    );

    @GET("get-all-employee-details")
    Call<TotalEmployeeResponse> getTotalEmployee();

    @GET("get-employee-details/{id}")
    Call<SingleEmployeeResponse> getSingleEmployee(@Path("id") int id);


}
