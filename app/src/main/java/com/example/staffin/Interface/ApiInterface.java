package com.example.staffin.Interface;

import android.icu.text.SymbolTable;

import com.example.staffin.Response.AddEmployeeResponse;
import com.example.staffin.Response.AddEventResponse;
import com.example.staffin.Response.AddPasswordForEmployee;
import com.example.staffin.Response.AllEvents;
import com.example.staffin.Response.AllEventsByYear;
import com.example.staffin.Response.AllExpenses;
import com.example.staffin.Response.AllPayroll;
import com.example.staffin.Response.AttendanceResponse;
import com.example.staffin.Response.BankDetailsResponse;
import com.example.staffin.Response.BankDetailsResponseById;
import com.example.staffin.Response.CompanyDetailsResponse;
import com.example.staffin.Response.CompanyResponseById;
import com.example.staffin.Response.CreatedHolidayResp;
import com.example.staffin.Response.DailyAttendance;
import com.example.staffin.Response.DepartmentResponse;
import com.example.staffin.Response.DesignationResponse;
import com.example.staffin.Response.EventResponse;
import com.example.staffin.Response.EventsByYearResponse;
import com.example.staffin.Response.GetMonthlyAttendance;
import com.example.staffin.Response.HolidayResponse;
import com.example.staffin.Response.LeaveAcceptRejectResponse;
import com.example.staffin.Response.LeaveResponse;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Response.NationalHolidayResp;
import com.example.staffin.Response.OverTimeResponse;
import com.example.staffin.Response.PaySlipResponse;
import com.example.staffin.Response.PayrollResponse;
import com.example.staffin.Response.SignupResponse;
import com.example.staffin.Response.SingleEmployeeResponse;
import com.example.staffin.Response.TotalEmployeeResponse;

import java.util.ArrayList;
import java.util.List;

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
    @POST("add-manager")
    Call<SignupResponse> postSignupResponse(@Field("name") String name,
                                            @Field("email") String email,
                                            @Field("mobile") String mobile,
                                            @Field("password") String password);


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
    @POST("employee-update/{id}")
    Call<AddEmployeeResponse> postUpdateEmployeeWithoutImage(
            @Path("id") int id,
            @Field("name") String name,
            @Field("father_name") String father_name,
            @Field("date_of_birth") String date_of_birth,
            @Field("mobile") String mobile,
            @Field("gender") String gender,
            @Field("email") String email,
            @Field("local_address") String local_address,
            @Field("parmanent_address") String parmanent_address
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

    @FormUrlEncoded
    @POST("update-bank-details/{id}")
    Call<BankDetailsResponse> updateBankDetailsById(@Path("id") int id,
                                                    @Field("account_name") String account_name,
                                                    @Field("account_number") String account_number,
                                                    @Field("bank") String bank,
                                                    @Field("branch") String branch);


    @GET("get-event-details")
    Call<EventResponse> getAllEvents();

    //get all holidays
    @GET("get-holiday")
    Call<CreatedHolidayResp> getCreatedHolidays();

    @FormUrlEncoded
    @POST("get-national-holiday")
    Call<NationalHolidayResp> getNationalHolidayMonthly(@Field("country") String country,
                                                        @Field("month") int month,
                                                        @Field("year") int year);

    @FormUrlEncoded
    @POST("get-national-holiday")
    Call<NationalHolidayResp> getNationalHoliday(@Field("country") String country,
                                                 @Field("year") int year);


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
                                                 @Field("department") String department,
                                                 @Field("designation") String designation,
                                                 @Field("annual_leave") String annual_leave,
                                                 @Field("medical_leave") String medical_leave,
                                                 @Field("status") String status,
                                                 @Field("joining_date") String joining_date,
                                                 @Field("exit_date") String exit_date,
                                                 @Field("basic") String basic,
                                                 @Field("hourly") String hourly);

    @GET("test")
    Call<DailyAttendance> getAllEmployeeDailyAttendance();

    @POST("approve-leave/{id}?status=")
    Call<LeaveAcceptRejectResponse> acceptRejectLeave(@Path("id") int id,
                                                      @Query("status") String status);

    @GET("get-event-details/{year}")
    Call<AllEventsByYear> getAllEvents(@Path("year") int year);

    @GET("get-event-details/{year}")
    Call<EventsByYearResponse> getEventsByYear(@Path("year") int year);


    @Multipart
    @POST("add-event")
    Call<LoginResponse> addEventFunc(
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part image1,
            @Part MultipartBody.Part image2,
            @Part MultipartBody.Part image3,
            @Part("title_name") RequestBody title_name,
            @Part("location") RequestBody location,
            @Part("description") RequestBody description,
            @Part("date") RequestBody date,
            @Part("add_member") RequestBody add_member
    );

    @FormUrlEncoded
    @POST("update-attendance")
    Call<LoginResponse> updateAttendanceById(@Field("employeeID") int employeeID,
                                             @Field("date") String date,
                                             @Field("status") String status,
                                             @Field("leaveType") String leaveType,
                                             @Field("overtime") String overtime);


    @GET("get-payslip/{id}")
    Call<PaySlipResponse> getPaySlip(@Path("id") int id);

    @FormUrlEncoded
    @POST("add-payroll")
    Call<PayrollResponse> postPayRoll(@Field("employee_id[]") List<Integer> employee_id,
                                      @Field("year") String year,
                                      @Field("month") String month);

    @GET("get-all-payroll")
    Call<AllPayroll> getAllPayroll();


    @GET("get-all-expense")
    Call<AllExpenses> getAllExpenses();

    @FormUrlEncoded
    @POST("update-payroll/{id}")
    Call<LoginResponse> postUpdatePayroll(@Path("id") int id,
                                          @Field("status") String status,
                                          @Field("daily_rate") String daily_rate,
                                          @Field("total_working_day") String total_working_day,
                                          @Field("overtime_pay") String overtime_pay,
                                          @Field("overtime_hours") String overtime_hours,
                                          @Field("basic") String basic,
                                          @Field("expense") String expense,
                                          @Field("bonus") String bonus,
                                          @Field("deduction") String deduction,
                                          @Field("net_salary") String net_salary);

}


