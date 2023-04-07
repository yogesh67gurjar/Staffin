package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("today_attendance_list")
    @Expose
    private TodayAttendanceList todayAttendanceList;



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TodayAttendanceList getTodayAttendanceList() {
        return todayAttendanceList;
    }

    public void setTodayAttendanceList(TodayAttendanceList todayAttendanceList) {
        this.todayAttendanceList = todayAttendanceList;
    }

}
