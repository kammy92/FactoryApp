package com.karman.factoryapp.network.model.customer;

import com.google.gson.annotations.SerializedName;
import com.karman.factoryapp.model.Customer;
import com.karman.factoryapp.network.model.base.DataResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karman Singh
 */

public class CustomerDataResponse extends DataResponse {

    @SerializedName("customers")
    private List<Customer> customerList = new ArrayList<>();
    @SerializedName("total")
    private int total;
    @SerializedName("offset")
    private int offset;
    @SerializedName("limit")
    private int limit;
    @SerializedName("status")
    private int status;
    @SerializedName("search")
    private String search;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "CustomerDataResponse{" +
                "customerList=" + customerList +
                ", total=" + total +
                ", offset=" + offset +
                ", limit=" + limit +
                ", status=" + status +
                ", search='" + search + '\'' +
                '}';
    }
}
