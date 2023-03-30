package com.example.staffin.Interface;

import com.example.staffin.Response.AddEmployeeResponse;
import com.example.staffin.Response.AddPasswordForEmployee;
import com.example.staffin.Response.BankDetailsResponse;
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

    @FormUrlEncoded
    @POST("add-company-details/{id}")
    Call<CompanyDetailsResponse> postSingleCompanyDetailsEmployee(

            @Field("department") String department,
            @Field("designation") String designation,
            @Field("annual_leave") String annual_leave,
            @Field("medical_leave") String medical_leave,
            @Field("status") String status,
            @Field("joining_date") String joining_date,
            @Field("exit_date") String exit_date,
            @Field("basic") String basic,
            @Field("hourly") String hourly,
            @Path("id") int id);


    @FormUrlEncoded
    @POST("add-holiday")
    Call<HolidayResponse> postHoliday(@Field("date") String date,
                                      @Field("occassion") String occassion);

    @FormUrlEncoded
    @POST("add-bank-details/{id}")
    Call<BankDetailsResponse> postSingleBankDetails(@Path("id") int id,
                                                    @Field("employee_id") String employee_id,
                                                    @Field("account_name") String account_name,
                                                    @Field("account_number") String account_number,
                                                    @Field("bank") String bank,
                                                    @Field("branch") String branch);

}


