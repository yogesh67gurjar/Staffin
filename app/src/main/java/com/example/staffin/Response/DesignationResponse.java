package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DesignationResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("designation_details")
    @Expose
    private List<DesignationDetail> designationDetails;

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

    public List<DesignationDetail> getDesignationDetails() {
        return designationDetails;
    }

    public void setDesignationDetails(List<DesignationDetail> designationDetails) {
        this.designationDetails = designationDetails;
    }

}
