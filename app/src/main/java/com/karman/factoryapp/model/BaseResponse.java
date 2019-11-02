package com.karman.factoryapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Karman Singh
 */

public class BaseResponse {


    public static final int HTTP_OK = 200;
    public static final int HTTP_OK_201 = 201;
    public static final int HTTP_PUT_OK = 204;
    public static final int HTTP_OK_API_VERSIONING = 320;

    public static final int HTTP_INTERNAL_ERROR = 500;
    public static final int M_COMM_EXCEPTION = 420;
    public static final int AWS_SIGNUP_EXCEPTION = 400;
    public static final int SESSION_EXPIRED_EXCEPTION = 404;

    public static interface ErrorCodes {
        int SESSION_EXPIRES_ADDRESSES = 15002;
        int SESSION_EXPIRES_UPDATE_ORDER = 12047;
        int SESSION_EXPIRES_GET_AUTH = 404;
        int SESSION_EXPIRES_MCOMM = 12009;
    }

//    private boolean error;

//    private boolean successful;

    @SerializedName("data")
    private DataResponse dataResponse;

    @SerializedName("error")
    private ErrorResponse errorResponse;

/*
    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(final boolean successful) {
        this.successful = successful;
    }

    public boolean isError() {
        return error;
    }

    public void setError(final boolean error) {
        this.error = error;
    }

*/
    public DataResponse getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    @Override
    public String toString() {
        return "BaseResponse [" +
                ", mDataResponse=" + dataResponse +
                ", mErrorResponse=" + errorResponse + "]";
    }
}
