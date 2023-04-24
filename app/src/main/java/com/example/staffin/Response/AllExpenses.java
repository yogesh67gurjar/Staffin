package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllExpenses {

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

    public class GetAllExpenseDetail {

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
        private Object purchaseDate;
        @SerializedName("purchase_from")
        @Expose
        private Object purchaseFrom;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("bill")
        @Expose
        private String bill;
        @SerializedName("image_bill")
        @Expose
        private String imageBill;
        @SerializedName("company_id")
        @Expose
        private Integer companyId;
        @SerializedName("employee_id")
        @Expose
        private List<AllPayroll.AllPayslipDetail.EmployeeId> employeeId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("bill_url")
        @Expose
        private String billUrl;

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

        public Object getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(Object purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public Object getPurchaseFrom() {
            return purchaseFrom;
        }

        public void setPurchaseFrom(Object purchaseFrom) {
            this.purchaseFrom = purchaseFrom;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getBill() {
            return bill;
        }

        public void setBill(String bill) {
            this.bill = bill;
        }

        public String getImageBill() {
            return imageBill;
        }

        public void setImageBill(String imageBill) {
            this.imageBill = imageBill;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public List<AllPayroll.AllPayslipDetail.EmployeeId> getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(List<AllPayroll.AllPayslipDetail.EmployeeId> employeeId) {
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
    }
}
