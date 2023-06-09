package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyDetails {

    @SerializedName("department")
    @Expose
    private List<Department> department;
    @SerializedName("designation")
    @Expose
    private List<Designation> designation;
    @SerializedName("annual_leave")
    @Expose
    private Integer annualLeave;
    @SerializedName("medical_leave")
    @Expose
    private String medicalLeave;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("joining_date")
    @Expose
    private String joiningDate;
    @SerializedName("basic")
    @Expose
    private List<Basic> basic;
    @SerializedName("hourly_rate")
    @Expose
    private List<HourlyRate> hourlyRate;

    private String exit_date;

    public String getExit_date() {
        return exit_date;
    }

    public void setExit_date(String exit_date) {
        this.exit_date = exit_date;
    }

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }

    public List<Designation> getDesignation() {
        return designation;
    }

    public void setDesignation(List<Designation> designation) {
        this.designation = designation;
    }

    public Integer getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(Integer annualLeave) {
        this.annualLeave = annualLeave;
    }

    public String getMedicalLeave() {
        return medicalLeave;
    }

    public void setMedicalLeave(String medicalLeave) {
        this.medicalLeave = medicalLeave;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public List<Basic> getBasic() {
        return basic;
    }

    public void setBasic(List<Basic> basic) {
        this.basic = basic;
    }

    public List<HourlyRate> getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(List<HourlyRate> hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

}
