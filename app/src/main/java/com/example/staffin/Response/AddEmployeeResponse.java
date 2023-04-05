package com.example.staffin.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddEmployeeResponse {

    private int status;
    private String message;

    @SerializedName("employeeID")
    private List<AddEmployeeDetails> employeeID;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AddEmployeeDetails> getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(List<AddEmployeeDetails> employeeID) {
        this.employeeID = employeeID;
    }
}
