package com.karman.factoryapp.model;

import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("customer_id")
    private int customerID;
    @SerializedName("customer_name")
    private String customerName;
    @SerializedName("customer_mobile")
    private String customerMobile;
    @SerializedName("customer_email")
    private String customerEmail;
    @SerializedName("customer_address")
    private String customerAddress;
    @SerializedName("customer_status")
    private int customerStatus;

    public Customer(int customerID, String customerName,
                    String customerMobile, String customerEmail,
                    String customerAddress, int customerStatus) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(int customerStatus) {
        this.customerStatus = customerStatus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", customerName=" + customerName +
                ", customerMobile=" + customerMobile +
                ", customerEmail=" + customerEmail +
                ", customerAddress=" + customerAddress +
                ", customerStatus=" + customerStatus +
                '}';
    }
}