package com.karman.factoryapp.network.model.base;

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

    @SerializedName("error")
    private ErrorResponse errorResponse;

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    @Override
    public String toString() {
        return "BaseResponse [" +
                ", mErrorResponse=" + errorResponse + "]";
    }
}
