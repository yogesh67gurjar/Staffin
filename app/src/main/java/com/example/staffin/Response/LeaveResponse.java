package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaveResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("employee_result")
    @Expose
    private List<EmployeeLeaveResult> employeeLeaveResult;

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

    public List<EmployeeLeaveResult> getEmployeeLeaveResult() {
        return employeeLeaveResult;
    }

    public void setEmployeeLeaveResult(List<EmployeeLeaveResult> employeeLeaveResult) {
        this.employeeLeaveResult = employeeLeaveResult;
    }
}
