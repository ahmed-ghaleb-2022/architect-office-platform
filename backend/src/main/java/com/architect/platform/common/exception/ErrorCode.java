package com.architect.platform.common.exception;

public class ErrorCode {

    private ErrorCode(){
    }

    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    public static final String BUSINESS_ERROR = "BUSINESS_ERROR";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
}
