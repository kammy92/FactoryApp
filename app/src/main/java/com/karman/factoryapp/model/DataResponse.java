package com.karman.factoryapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Karman Singh
 */

public class DataResponse {

    @SerializedName("success_type")
    private String successType;

    @SerializedName("success_code")
    private String successCode;

    @SerializedName("status_code")
    private String statusCode;

    @SerializedName("message")
    private String message;

    @SerializedName("more_info")
    private String moreInfo;


    public String getSuccessType() {
        return successType;
    }

    public void setSuccessType(String successType) {
        this.successType = successType;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return "DataResponse [mStatus="+ "]";
    }
}
