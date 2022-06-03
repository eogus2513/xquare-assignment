package com.xquare.assignment.domain.auth.user.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class ClientExistsException extends CustomException {

    public static final CustomException EXCEPTION =
            new ClientExistsException();

    private ClientExistsException() {
        super(ErrorCode.CLIENT_EXISTS);
    }
}
