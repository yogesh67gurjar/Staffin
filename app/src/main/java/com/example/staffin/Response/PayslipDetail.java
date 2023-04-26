package com.example.staffin.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayslipDetail {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("employee_id")
    @Expose
    private List<AllPayroll.AllPayslipDetail.EmployeeId> employeeId;

    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("basic")
    @Expose
    private String basic;
    @SerializedName("overtime_hours")
    @Expose
    private String overtimeHours;
    @SerializedName("overtime_pay")
    @Expose
    private String overtimePay;
    @SerializedName("allowances")
    @Expose
    private String allowances;
    @SerializedName("total_allowance")
    @Expose
    private String totalAllowance;
    @SerializedName("deductions")
    @Expose
    private String deductions;
    @SerializedName("total_deduction")
    @Expose
    private String totalDeduction;
    @SerializedName("additionals")
    @Expose
    private String additionals;
    @SerializedName("total_additional")
    @Expose
    private String totalAdditional;
    @SerializedName("net_salary")
    @Expose
    private String netSalary;
    @SerializedName("pay_date")
    @Expose
    private String payDate;
    @SerializedName("expense")
    @Expose
    private String expense;
    @SerializedName("company_id")
    @Expose
    private Integer companyId;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("bonus")
    @Expose
    private String bonus;

    @SerializedName("total_working_day")
    @Expose
    private String total_working_day;

    @SerializedName("daily_rate")
    @Expose
    private String daily_rate;

    @SerializedName("deduction")
    @Expose
    private String deduction;

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getTotal_working_day() {
        return total_working_day;
    }

    public void setTotal_working_day(String total_working_day) {
        this.total_working_day = total_working_day;
    }

    public String getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(String daily_rate) {
        this.daily_rate = daily_rate;
    }

    public String getDeduction() {
        return deduction;
    }

    public void setDeduction(String deduction) {
        this.deduction = deduction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AllPayroll.AllPayslipDetail.EmployeeId> getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(List<AllPayroll.AllPayslipDetail.EmployeeId> employeeId) {
        this.employeeId = employeeId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(String overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public String getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(String overtimePay) {
        this.overtimePay = overtimePay;
    }

    public String getAllowances() {
        return allowances;
    }

    public void setAllowances(String allowances) {
        this.allowances = allowances;
    }

    public String getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(String totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public String getDeductions() {
        return deductions;
    }

    public void setDeductions(String deductions) {
        this.deductions = deductions;
    }

    public String getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(String totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public String getAdditionals() {
        return additionals;
    }

    public void setAdditionals(String additionals) {
        this.additionals = additionals;
    }

    public String getTotalAdditional() {
        return totalAdditional;
    }

    public void setTotalAdditional(String totalAdditional) {
        this.totalAdditional = totalAdditional;
    }

    public String getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(String netSalary) {
        this.netSalary = netSalary;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
