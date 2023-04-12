package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceDatum implements java.io.Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("leaveType")
    @Expose
    private String leaveType;
    @SerializedName("halfDayType")
    @Expose
    private String halfDayType;
    @SerializedName("last_updated_by")
    @Expose
    private Integer lastUpdatedBy;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("application_status")
    @Expose
    private String applicationStatus;
    @SerializedName("applied_on")
    @Expose
    private String appliedOn;
    @SerializedName("clock_in")
    @Expose
    private String clockIn;
    @SerializedName("clock_out")
    @Expose
    private String clockOut;
    @SerializedName("clock_in_location")
    @Expose
    private String clockInLocation;
    @SerializedName("clock_out_location")
    @Expose
    private String clockOutLocation;
    @SerializedName("clock_in_ip_address")
    @Expose
    private String clockInIpAddress;
    @SerializedName("clock_out_ip_address")
    @Expose
    private String clockOutIpAddress;
    @SerializedName("working_from")
    @Expose
    private String workingFrom;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("is_late")
    @Expose
    private Integer isLate;
    @SerializedName("office_start_time")
    @Expose
    private String officeStartTime;
    @SerializedName("office_end_time")
    @Expose
    private String officeEndTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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

    public String getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(String appliedOn) {
        this.appliedOn = appliedOn;
    }

    public String getClockIn() {
        return clockIn;
    }

    public void setClockIn(String clockIn) {
        this.clockIn = clockIn;
    }

    public String getClockOut() {
        return clockOut;
    }

    public void setClockOut(String clockOut) {
        this.clockOut = clockOut;
    }

    public String getClockInLocation() {
        return clockInLocation;
    }

    public void setClockInLocation(String clockInLocation) {
        this.clockInLocation = clockInLocation;
    }

    public String getClockOutLocation() {
        return clockOutLocation;
    }

    public void setClockOutLocation(String clockOutLocation) {
        this.clockOutLocation = clockOutLocation;
    }

    public String getClockInIpAddress() {
        return clockInIpAddress;
    }

    public void setClockInIpAddress(String clockInIpAddress) {
        this.clockInIpAddress = clockInIpAddress;
    }

    public String getClockOutIpAddress() {
        return clockOutIpAddress;
    }

    public void setClockOutIpAddress(String clockOutIpAddress) {
        this.clockOutIpAddress = clockOutIpAddress;
    }

    public String getWorkingFrom() {
        return workingFrom;
    }

    public void setWorkingFrom(String workingFrom) {
        this.workingFrom = workingFrom;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getIsLate() {
        return isLate;
    }

    public void setIsLate(Integer isLate) {
        this.isLate = isLate;
    }

    public String getOfficeStartTime() {
        return officeStartTime;
    }

    public void setOfficeStartTime(String officeStartTime) {
        this.officeStartTime = officeStartTime;
    }

    public String getOfficeEndTime() {
        return officeEndTime;
    }

    public void setOfficeEndTime(String officeEndTime) {
        this.officeEndTime = officeEndTime;
    }

}
