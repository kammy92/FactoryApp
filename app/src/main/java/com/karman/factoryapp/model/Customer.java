package com.karman.factoryapp.model;

import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("customer_id")
    private int customerID;
    @SerializedName("customer_name")
    private int customerName;
    @SerializedName("customer_mobile")
    private int customerMobile;
    @SerializedName("customer_email")
    private int customerEmail;
    @SerializedName("customer_address")
    private int customerAddress;
    @SerializedName("customer_status")
    private int customerStatus;

    public Customer(int customerID, int customerName, int customerMobile,
                    int customerEmail, int customerAddress, int customerStatus) {
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

    public int getCustomerName() {
        return customerName;
    }

    public void setCustomerName(int customerName) {
        this.customerName = customerName;
    }

    public int getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(int customerMobile) {
        this.customerMobile = customerMobile;
    }

    public int getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(int customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(int customerAddress) {
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