package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyAttendance {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("students_list")
    @Expose
    private String studentsList;
    @SerializedName("today_attendance_list")
    @Expose
    private List<TodayAttendance> todayAttendanceList;

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

    public String getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(String studentsList) {
        this.studentsList = studentsList;
    }

    public List<TodayAttendance> getTodayAttendanceList() {
        return todayAttendanceList;
    }

    public void setTodayAttendanceList(List<TodayAttendance> todayAttendanceList) {
        this.todayAttendanceList = todayAttendanceList;
    }

}
