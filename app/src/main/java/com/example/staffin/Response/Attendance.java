package com.example.staffin.Response;

public class Attendance {
    private String name;
    private String phone;
    private String dpImg;
    private String dob;
    private String empId;
    private String email;

    public Attendance(String name, String phone, String dpImg, String dob, String empId, String email) {
        this.name = name;
        this.phone = phone;
        this.dpImg = dpImg;
        this.dob = dob;
        this.empId = empId;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDpImg() {
        return dpImg;
    }

    public void setDpImg(String dpImg) {
        this.dpImg = dpImg;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
