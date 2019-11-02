package com.karman.factoryapp.network.model.base;

import com.google.gson.annotations.SerializedName;

/**
 * @author Karman Singh
 */

public class ErrorResponse {

    @SerializedName("error_type")
    private String errorType;

    @SerializedName("error_code")
    private String errorCode;

    @SerializedName("status_code")
    private String statusCode;

    @SerializedName("message")
    private String message;

    @SerializedName("error_message")
    private String errorMessage;

    @SerializedName("stacktrace")
    private String stacktrace;

    @SerializedName("file")
    private String file;

    @SerializedName("more_info")
    private String moreInfo;


    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return "ErrorResponse [mError=" + "]";
    }
}
