package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaveAcceptRejectResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("employee_result")
    @Expose
    private EmployeeResult employeeResult;

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

    public EmployeeResult getEmployeeResult() {
        return employeeResult;
    }

    public void setEmployeeResult(EmployeeResult employeeResult) {
        this.employeeResult = employeeResult;
    }
}
