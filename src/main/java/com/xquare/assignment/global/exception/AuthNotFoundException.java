package com.xquare.assignment.global.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class AuthNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new AuthNotFoundException();

    private AuthNotFoundException() {
        super(ErrorCode.AUTH_NOT_FOUND);
    }
}
