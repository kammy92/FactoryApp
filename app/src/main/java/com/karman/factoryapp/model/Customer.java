package com.karman.factoryapp.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

public class Customer extends BaseObservable {
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

    @Bindable
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
        notifyPropertyChanged(com.karman.factoryapp.BR.customerID);
    }

    @Bindable
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
        notifyPropertyChanged(com.karman.factoryapp.BR.customerName);
    }

    @Bindable
    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
        notifyPropertyChanged(com.karman.factoryapp.BR.customerMobile);
    }

    @Bindable
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
        notifyPropertyChanged(com.karman.factoryapp.BR.customerEmail);
    }

    @Bindable
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
        notifyPropertyChanged(com.karman.factoryapp.BR.customerAddress);
    }

    @Bindable
    public int getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(int customerStatus) {
        this.customerStatus = customerStatus;
        notifyPropertyChanged(com.karman.factoryapp.BR.customerStatus);
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