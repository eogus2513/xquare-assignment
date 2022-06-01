package com.xquare.assignment.global.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class SignatureJWTException extends CustomException {

    public static final CustomException EXCEPTION =
            new SignatureJWTException();

    private SignatureJWTException() {
        super(ErrorCode.SIGNATURE_JWT);
    }
}
