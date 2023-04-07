package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayAttendanceList {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("clock_in")
    @Expose
    private String clockIn;
    @SerializedName("clock_out")
    @Expose
    private String clockOut;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
