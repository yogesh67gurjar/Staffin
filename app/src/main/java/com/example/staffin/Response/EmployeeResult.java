package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("employeeID")
    @Expose
    private String employeeID;
    @SerializedName("company_id")
    @Expose
    private Integer companyId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("department_id")
    @Expose
    private List<DepartmentId> departmentId;
    @SerializedName("designation")
    @Expose
    private List<Designation> designation;
    @SerializedName("joining_date")
    @Expose
    private String joiningDate;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("local_address")
    @Expose
    private String localAddress;
    @SerializedName("permanent_address")
    @Expose
    private String permanentAddress;
    @SerializedName("annual_leave")
    @Expose
    private Integer annualLeave;
    @SerializedName("medical_leave")
    @Expose
    private String medicalLeave;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("remember_token")
    @Expose
    private String rememberToken;
    @SerializedName("exit_date")
    @Expose
    private String exitDate;
    @SerializedName("first_over_time_start")
    @Expose
    private String firstOverTimeStart;
    @SerializedName("first_over_time_end")
    @Expose
    private String firstOverTimeEnd;
    @SerializedName("first_over_time_amount")
    @Expose
    private String firstOverTimeAmount;
    @SerializedName("second_over_time_start")
    @Expose
    private String secondOverTimeStart;
    @SerializedName("second_over_time_end")
    @Expose
    private String secondOverTimeEnd;
    @SerializedName("second_over_time_amount")
    @Expose
    private String secondOverTimeAmount;
    @SerializedName("reset_code")
    @Expose
    private String resetCode;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("profile_image_url")
    @Expose
    private String profileImageUrl;
    @SerializedName("work_duration")
    @Expose
    private String workDuration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<DepartmentId> getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(List<DepartmentId> departmentId) {
        this.departmentId = departmentId;
    }

    public List<Designation> getDesignation() {
        return designation;
    }

    public void setDesignation(List<Designation> designation) {
        this.designation = designation;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
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

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public String getFirstOverTimeStart() {
        return firstOverTimeStart;
    }

    public void setFirstOverTimeStart(String firstOverTimeStart) {
        this.firstOverTimeStart = firstOverTimeStart;
    }

    public String getFirstOverTimeEnd() {
        return firstOverTimeEnd;
    }

    public void setFirstOverTimeEnd(String firstOverTimeEnd) {
        this.firstOverTimeEnd = firstOverTimeEnd;
    }

    public String getFirstOverTimeAmount() {
        return firstOverTimeAmount;
    }

    public void setFirstOverTimeAmount(String firstOverTimeAmount) {
        this.firstOverTimeAmount = firstOverTimeAmount;
    }

    public String getSecondOverTimeStart() {
        return secondOverTimeStart;
    }

    public void setSecondOverTimeStart(String secondOverTimeStart) {
        this.secondOverTimeStart = secondOverTimeStart;
    }

    public String getSecondOverTimeEnd() {
        return secondOverTimeEnd;
    }

    public void setSecondOverTimeEnd(String secondOverTimeEnd) {
        this.secondOverTimeEnd = secondOverTimeEnd;
    }

    public String getSecondOverTimeAmount() {
        return secondOverTimeAmount;
    }

    public void setSecondOverTimeAmount(String secondOverTimeAmount) {
        this.secondOverTimeAmount = secondOverTimeAmount;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(String workDuration) {
        this.workDuration = workDuration;
    }


    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("leaveType")
    @Expose
    private String leaveType;
    @SerializedName("halfDayType")
    @Expose
    private String halfDayType;
    @SerializedName("last_updated_by")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("days")
    @Expose
    private Integer days;
    @SerializedName("applied_on")
    @Expose
    private String appliedOn;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("application_status")
    @Expose
    private String applicationStatus;

 
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getHalfDayType() {
        return halfDayType;
    }

    public void setHalfDayType(String halfDayType) {
        this.halfDayType = halfDayType;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
