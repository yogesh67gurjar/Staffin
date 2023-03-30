package com.example.staffin.Interface;

import com.example.staffin.Response.AddEmployeeResponse;
import com.example.staffin.Response.AddPasswordForEmployee;
import com.example.staffin.Response.CompanyDetailsResponse;
import com.example.staffin.Response.DepartmentResponse;
import com.example.staffin.Response.DesignationResponse;
import com.example.staffin.Response.HolidayResponse;
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

    @FormUrlEncoded
    @POST("employee-add-password/{employeeID}")
    Call<AddPasswordForEmployee> postSinglePasswordEmployee(@Field("password") String password
            , @Path("employeeID") String employeeID);

    @GET("get-department")
    Call<DepartmentResponse> getDepartment();

    @GET("get-designation")
    Call<DesignationResponse> getDesignation();

    @Multipart
    @POST("add-company-details{id}")
    Call<CompanyDetailsResponse> postSingleCompanyDetailsEmployee(

            @Part("department") RequestBody department,
            @Part("designation") RequestBody designation,
            @Part("annual_leave") RequestBody annual_leave,
            @Part("medical_leave") RequestBody medical_leave,
            @Part("status") RequestBody status,
            @Part("joining_date") RequestBody joining_date,
            @Part("exit_date") RequestBody exit_date,
            @Part("basic") RequestBody basic,
            @Part("hourly") RequestBody hourly,
            @Path("id") int id);


    @FormUrlEncoded
    @POST("add-holiday")
    Call<HolidayResponse> postHoliday(@Field("date") String date,
                                      @Field("occassion") String occassion);

}


