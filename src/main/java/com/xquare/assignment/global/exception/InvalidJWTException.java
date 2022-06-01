package com.xquare.assignment.global.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class InvalidJWTException extends CustomException {

    public static final CustomException EXCEPTION =
            new InvalidJWTException();

    private InvalidJWTException() {
        super(ErrorCode.INVALID_JWT);
    }
}
