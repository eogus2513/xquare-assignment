package com.xquare.assignment.domain.auth.user.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class AuthExistsException extends CustomException {

    public static final CustomException EXCEPTION =
            new AuthExistsException();

    private AuthExistsException() {
        super(ErrorCode.AUTH_EXISTS);
    }
}
