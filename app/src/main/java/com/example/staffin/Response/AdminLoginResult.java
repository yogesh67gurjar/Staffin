package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminLoginResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("company_id")
    @Expose
    private Integer companyId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("reset_code")
    @Expose
    private Object resetCode;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("email_verified")
    @Expose
    private String emailVerified;
    @SerializedName("email_token")
    @Expose
    private String emailToken;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("manager")
    @Expose
    private Integer manager;
    @SerializedName("number_of_logins")
    @Expose
    private Integer numberOfLogins;
    @SerializedName("last_activity")
    @Expose
    private Object lastActivity;
    @SerializedName("accepted_gdpr")
    @Expose
    private Object acceptedGdpr;
    @SerializedName("isAnonymized")
    @Expose
    private Integer isAnonymized;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getResetCode() {
        return resetCode;
    }

    public void setResetCode(Object resetCode) {
        this.resetCode = resetCode;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public Integer getNumberOfLogins() {
        return numberOfLogins;
    }

    public void setNumberOfLogins(Integer numberOfLogins) {
        this.numberOfLogins = numberOfLogins;
    }

    public Object getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Object lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Object getAcceptedGdpr() {
        return acceptedGdpr;
    }

    public void setAcceptedGdpr(Object acceptedGdpr) {
        this.acceptedGdpr = acceptedGdpr;
    }

    public Integer getIsAnonymized() {
        return isAnonymized;
    }

    public void setIsAnonymized(Integer isAnonymized) {
        this.isAnonymized = isAnonymized;
    }

}
