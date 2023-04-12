package com.example.staffin.Interface;

import android.icu.text.SymbolTable;

import com.example.staffin.Response.AddEmployeeResponse;
import com.example.staffin.Response.AddPasswordForEmployee;
import com.example.staffin.Response.AttendanceResponse;
import com.example.staffin.Response.BankDetailsResponse;
import com.example.staffin.Response.BankDetailsResponseById;
import com.example.staffin.Response.CompanyDetailsResponse;
import com.example.staffin.Response.CompanyResponseById;
import com.example.staffin.Response.DailyAttendance;
import com.example.staffin.Response.DepartmentResponse;
import com.example.staffin.Response.DesignationResponse;
import com.example.staffin.Response.EventResponse;
import com.example.staffin.Response.GetMonthlyAttendance;
import com.example.staffin.Response.HolidayResponse;
import com.example.staffin.Response.LeaveAcceptRejectResponse;
import com.example.staffin.Response.LeaveResponse;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Response.OverTimeResponse;
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
import retrofit2.http.Query;

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

    @Multipart
    @POST("employee-update/{id}")
    Call<AddEmployeeResponse> postUpdateEmployee(
            @Path("id") int id,
            @Part MultipartBody.Part profile_image,
            @Part("name") RequestBody name,
            @Part("father_name") RequestBody father_name,
            @Part("date_of_birth") RequestBody date_of_birth,
            @Part("mobile") RequestBody mobile,
            @Part("gender") RequestBody gender,
            @Part("email") RequestBody email,
            @Part("local_address") RequestBody local_address,
            @Part("parmanent_address") RequestBody parmanent_address
    );

    @FormUrlEncoded
    @POST("get-attendance")
    Call<GetMonthlyAttendance> getMonthlyAttendanceByEid(@Field("month") int month,
                                                         @Field("year") int year,
                                                         @Field("employeeID") int employeeID);

    @GET("delete-employee/{id}")
    Call<LoginResponse> deleteEmployeeById(@Path("id") int id);

    @GET("get-all-employee-details")
    Call<TotalEmployeeResponse> getTotalEmployee();

    @GET("employee-attendance-list/{id}")
    Call<AttendanceResponse> getAttendanceById(@Path("id") int id);

    @GET("get-employee-details/{id}")
    Call<SingleEmployeeResponse> getSingleEmployee(@Path("id") int id);

    @GET("get-company/{id}")
    Call<CompanyResponseById> getCompanyDetailsById(@Path("id") int id);

    @FormUrlEncoded
    @POST("employee-add-password/{employeeID}")
    Call<AddPasswordForEmployee> postSinglePasswordEmployee(@Field("password") String password
            , @Path("employeeID") String employeeID);

    @GET("get-department")
    Call<DepartmentResponse> getDepartment();

    @GET("get-designation/{id}")
    Call<DesignationResponse> getDesignation(@Path("id") int id);

//    @FormUrlEncoded
//    @POST("add-company-details/{id}")
//    Call<CompanyDetailsResponse> postSingleCompanyDetailsEmployee(
//            @Path("id") int id,
//            @Field("department") String department,
//            @Field("designation") String designation,
//            @Field("annual_leave") String annual_leave,
//            @Field("medical_leave") String medical_leave,
//            @Field("status") String status,
//            @Field("joining_date") String joining_date,
//            @Field("exit_date") String exit_date,
//            @Field("basic") String basic,
//            @Field("hourly") String hourly);

    @FormUrlEncoded
    @POST("add-company-details/{id}")
    Call<CompanyDetailsResponse> postSingleCompanyDetail(@Path("id") int id,
                                                         @Field("department") String department,
                                                         @Field("designation") String designation,
                                                         @Field("annual_leave") String annual_leave,
                                                         @Field("medical_leave") String medical_leave,
                                                         @Field("status") String status,
                                                         @Field("joining_date") String joining_date,
                                                         @Field("exit_date") String exit_date,
                                                         @Field("basic") String basic,
                                                         @Field("hourly") String hourly);

    @FormUrlEncoded
    @POST("add-holiday")
    Call<HolidayResponse> postHoliday(@Field("date") String date,
                                      @Field("occassion") String occassion,
                                      @Field("holiday_description") String holiday_description);

    @FormUrlEncoded
    @POST("add-bank-details")
    Call<BankDetailsResponse> postSingleBankDetails(@Field("employee_id") int employee_id,
                                                    @Field("account_name") String account_name,
                                                    @Field("account_number") String account_number,
                                                    @Field("bank") String bank,
                                                    @Field("branch") String branch);


    @GET("get-event-details")
    Call<EventResponse> getAllEvents();

    //get all holidays
    @GET("get-holiday")
    Call<HolidayResponse> getAllHolidays();

    //Get All Employee Leave
    @GET("get-all-employee-leave-apply")
    Call<LeaveResponse> getAllEmployeeLeave();

    //Post Over Time employee
    @FormUrlEncoded
    @POST("employee-over-time/{id}")
    Call<OverTimeResponse> postOverTime(@Path("id") int id,
                                        @Field("first_over_time_start") String first_over_time_start,
                                        @Field("first_over_time_end") String first_over_time_end,
                                        @Field("first_over_time_amount") String first_over_time_amount,
                                        @Field("second_over_time_start") String second_over_time_start,
                                        @Field("second_over_time_end") String second_over_time_end,
                                        @Field("second_over_time_amount") String second_over_time_amount);


    @GET("get-bank-details/{id}")
    Call<BankDetailsResponseById> getBankDetailsById(@Path("id") int id);

    @GET("get-over-time/{id}")
    Call<OverTimeResponse> getOverTime(@Path("id") int id);

    @FormUrlEncoded
    @POST("update-company-details/{id}")
    Call<LoginResponse> updateCompanyDetailsById(@Path("id") int id,
                                                 @Field("department") int department,
                                                 @Field("designation") int designation,
                                                 @Field("annual_leave") int annual_leave,
                                                 @Field("medical_leave") int medical_leave,
                                                 @Field("status") String status,
                                                 @Field("joining_date") String joining_date,
                                                 @Field("exit_date") String exit_date);

    @GET("test")
    Call<DailyAttendance> getAllEmployeeDailyAttendance();

    @POST("approve-leave/{id}?status=")
    Call<LeaveAcceptRejectResponse> acceptRejectLeave(@Path("id") int id,
                                                      @Query("status") String status);
}


