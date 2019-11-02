package com.karman.factoryapp.network.model.customer;

import com.google.gson.annotations.SerializedName;
import com.karman.factoryapp.network.model.base.BaseResponse;

/**
 * @author Karman Singh
 */

public class CustomerResponse extends BaseResponse {

    @SerializedName("data")
    private CustomerDataResponse customerDataResponse;

    public CustomerDataResponse getCustomerDataResponse() {
        return customerDataResponse;
    }

    public void setCustomerDataResponse(CustomerDataResponse customerDataResponse) {
        this.customerDataResponse = customerDataResponse;
    }

    @Override
    public String toString() {
        return "BaseResponse [" +
                ", customerDataResponse=" + customerDataResponse + "]";
    }
}
