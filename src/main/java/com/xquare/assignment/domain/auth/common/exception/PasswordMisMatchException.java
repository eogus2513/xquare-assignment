package com.xquare.assignment.domain.auth.common.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class PasswordMisMatchException extends CustomException {

    public static final CustomException EXCEPTION =
            new PasswordMisMatchException();

    private PasswordMisMatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
