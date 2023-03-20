package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("admin_login_result")
    @Expose
    private AdminLoginResult adminLoginResult;

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

    public AdminLoginResult getAdminLoginResult() {
        return adminLoginResult;
    }

    public void setAdminLoginResult(AdminLoginResult adminLoginResult) {
        this.adminLoginResult = adminLoginResult;
    }



}
