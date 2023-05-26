package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AllExpenses   {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("get_all_expense_details")
    @Expose
    private List<GetAllExpenseDetail> getAllExpenseDetails;

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

    public List<GetAllExpenseDetail> getGetAllExpenseDetails() {
        return getAllExpenseDetails;
    }

    public void setGetAllExpenseDetails(List<GetAllExpenseDetail> getAllExpenseDetails) {
        this.getAllExpenseDetails = getAllExpenseDetails;
    }


    public class GetAllExpenseDetail  {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("item_name")
        @Expose
        private String itemName;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("purchase_date")
        @Expose
        private String purchaseDate;
        @SerializedName("purchase_from")
        @Expose
        private String purchaseFrom;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("bill")
        @Expose
        private String bill;
        @SerializedName("image_bill")
        @Expose
        private List<String> imageBill;
        @SerializedName("company_id")
        @Expose
        private Integer companyId;
        @SerializedName("employee_id")
        @Expose
        private List<EmployeeId> employeeId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("bill_url")
        @Expose
        private String billUrl;

        private String image1;

        private String image2;
        private String image3;
        private String image4;
        private String image5;
        private String image6;
        private String image7;
        private String image8;
        private String image9;
        private String image10;

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public String getImage2() {
            return image2;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }

        public String getImage3() {
            return image3;
        }

        public void setImage3(String image3) {
            this.image3 = image3;
        }

        public String getImage4() {
            return image4;
        }

        public void setImage4(String image4) {
            this.image4 = image4;
        }

        public String getImage5() {
            return image5;
        }

        public void setImage5(String image5) {
            this.image5 = image5;
        }

        public String getImage6() {
            return image6;
        }

        public void setImage6(String image6) {
            this.image6 = image6;
        }

        public String getImage7() {
            return image7;
        }

        public void setImage7(String image7) {
            this.image7 = image7;
        }

        public String getImage8() {
            return image8;
        }

        public void setImage8(String image8) {
            this.image8 = image8;
        }

        public String getImage9() {
            return image9;
        }

        public void setImage9(String image9) {
            this.image9 = image9;
        }

        public String getImage10() {
            return image10;
        }

        public void setImage10(String image10) {
            this.image10 = image10;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(String purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public String getPurchaseFrom() {
            return purchaseFrom;
        }

        public void setPurchaseFrom(String purchaseFrom) {
            this.purchaseFrom = purchaseFrom;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getBill() {
            return bill;
        }

        public void setBill(String bill) {
            this.bill = bill;
        }

        public List<String> getImageBill() {
            return imageBill;
        }

        public void setImageBill(List<String> imageBill) {
            this.imageBill = imageBill;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public List<EmployeeId> getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(List<EmployeeId> employeeId) {
            this.employeeId = employeeId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBillUrl() {
            return billUrl;
        }

        public void setBillUrl(String billUrl) {
            this.billUrl = billUrl;
        }


        public class EmployeeId {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("employeeID")
            @Expose
            private String employeeID;
            @SerializedName("company_id")
            @Expose
            private Integer companyId;
            @SerializedName("full_name")
            @Expose
            private String fullName;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("new_password")
            @Expose
            private String newPassword;
            @SerializedName("gender")
            @Expose
            private String gender;
            @SerializedName("father_name")
            @Expose
            private String fatherName;
            @SerializedName("mobile_number")
            @Expose
            private String mobileNumber;
            @SerializedName("date_of_birth")
            @Expose
            private String dateOfBirth;
            @SerializedName("department_id")
            @Expose
            private String departmentId;
            @SerializedName("designation")
            @Expose
            private Integer designation;
            @SerializedName("joining_date")
            @Expose
            private String joiningDate;
            @SerializedName("profile_image")
            @Expose
            private String profileImage;
            @SerializedName("local_address")
            @Expose
            private String localAddress;
            @SerializedName("permanent_address")
            @Expose
            private String permanentAddress;
            @SerializedName("medical_leave")
            @Expose
            private String medicalLeave;
            @SerializedName("annual_leave")
            @Expose
            private String annualLeave;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("last_login")
            @Expose
            private String lastLogin;
            @SerializedName("remember_token")
            @Expose
            private String rememberToken;
            @SerializedName("exit_date")
            @Expose
            private String exitDate;
            @SerializedName("first_over_time_start")
            @Expose
            private String firstOverTimeStart;
            @SerializedName("first_over_time_end")
            @Expose
            private String firstOverTimeEnd;
            @SerializedName("first_over_time_amount")
            @Expose
            private String firstOverTimeAmount;
            @SerializedName("second_over_time_start")
            @Expose
            private String secondOverTimeStart;
            @SerializedName("second_over_time_end")
            @Expose
            private String secondOverTimeEnd;
            @SerializedName("second_over_time_amount")
            @Expose
            private String secondOverTimeAmount;
            @SerializedName("reset_code")
            @Expose
            private String resetCode;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("profile_image_url")
            @Expose
            private String profileImageUrl;
            @SerializedName("work_duration")
            @Expose
            private String workDuration;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getEmployeeID() {
                return employeeID;
            }

            public void setEmployeeID(String employeeID) {
                this.employeeID = employeeID;
            }

            public Integer getCompanyId() {
                return companyId;
            }

            public void setCompanyId(Integer companyId) {
                this.companyId = companyId;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getNewPassword() {
                return newPassword;
            }

            public void setNewPassword(String newPassword) {
                this.newPassword = newPassword;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getFatherName() {
                return fatherName;
            }

            public void setFatherName(String fatherName) {
                this.fatherName = fatherName;
            }

            public String getMobileNumber() {
                return mobileNumber;
            }

            public void setMobileNumber(String mobileNumber) {
                this.mobileNumber = mobileNumber;
            }

            public String getDateOfBirth() {
                return dateOfBirth;
            }

            public void setDateOfBirth(String dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
            }

            public String getDepartmentId() {
                return departmentId;
            }

            public void setDepartmentId(String departmentId) {
                this.departmentId = departmentId;
            }

            public Integer getDesignation() {
                return designation;
            }

            public void setDesignation(Integer designation) {
                this.designation = designation;
            }

            public String getJoiningDate() {
                return joiningDate;
            }

            public void setJoiningDate(String joiningDate) {
                this.joiningDate = joiningDate;
            }

            public String getProfileImage() {
                return profileImage;
            }

            public void setProfileImage(String profileImage) {
                this.profileImage = profileImage;
            }

            public String getLocalAddress() {
                return localAddress;
            }

            public void setLocalAddress(String localAddress) {
                this.localAddress = localAddress;
            }

            public String getPermanentAddress() {
                return permanentAddress;
            }

            public void setPermanentAddress(String permanentAddress) {
                this.permanentAddress = permanentAddress;
            }

            public String getMedicalLeave() {
                return medicalLeave;
            }

            public void setMedicalLeave(String medicalLeave) {
                this.medicalLeave = medicalLeave;
            }

            public String getAnnualLeave() {
                return annualLeave;
            }

            public void setAnnualLeave(String annualLeave) {
                this.annualLeave = annualLeave;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getLastLogin() {
                return lastLogin;
            }

            public void setLastLogin(String lastLogin) {
                this.lastLogin = lastLogin;
            }

            public String getRememberToken() {
                return rememberToken;
            }

            public void setRememberToken(String rememberToken) {
                this.rememberToken = rememberToken;
            }

            public String getExitDate() {
                return exitDate;
            }

            public void setExitDate(String exitDate) {
                this.exitDate = exitDate;
            }

            public String getFirstOverTimeStart() {
                return firstOverTimeStart;
            }

            public void setFirstOverTimeStart(String firstOverTimeStart) {
                this.firstOverTimeStart = firstOverTimeStart;
            }

            public String getFirstOverTimeEnd() {
                return firstOverTimeEnd;
            }

            public void setFirstOverTimeEnd(String firstOverTimeEnd) {
                this.firstOverTimeEnd = firstOverTimeEnd;
            }

            public String getFirstOverTimeAmount() {
                return firstOverTimeAmount;
            }

            public void setFirstOverTimeAmount(String firstOverTimeAmount) {
                this.firstOverTimeAmount = firstOverTimeAmount;
            }

            public String getSecondOverTimeStart() {
                return secondOverTimeStart;
            }

            public void setSecondOverTimeStart(String secondOverTimeStart) {
                this.secondOverTimeStart = secondOverTimeStart;
            }

            public String getSecondOverTimeEnd() {
                return secondOverTimeEnd;
            }

            public void setSecondOverTimeEnd(String secondOverTimeEnd) {
                this.secondOverTimeEnd = secondOverTimeEnd;
            }

            public String getSecondOverTimeAmount() {
                return secondOverTimeAmount;
            }

            public void setSecondOverTimeAmount(String secondOverTimeAmount) {
                this.secondOverTimeAmount = secondOverTimeAmount;
            }

            public String getResetCode() {
                return resetCode;
            }

            public void setResetCode(String resetCode) {
                this.resetCode = resetCode;
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

            public String getProfileImageUrl() {
                return profileImageUrl;
            }

            public void setProfileImageUrl(String profileImageUrl) {
                this.profileImageUrl = profileImageUrl;
            }

            public String getWorkDuration() {
                return workDuration;
            }

            public void setWorkDuration(String workDuration) {
                this.workDuration = workDuration;
            }

        }

    }


}