package com.xquare.assignment.global.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class ExpiredJWTException extends CustomException {

    public static final CustomException EXCEPTION =
            new ExpiredJWTException();

    private ExpiredJWTException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
