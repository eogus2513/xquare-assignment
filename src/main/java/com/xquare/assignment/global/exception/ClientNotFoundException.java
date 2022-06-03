package com.xquare.assignment.global.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class ClientNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new ClientNotFoundException();

    private ClientNotFoundException() {
        super(ErrorCode.CLIENT_NOT_FOUND);
    }
}
