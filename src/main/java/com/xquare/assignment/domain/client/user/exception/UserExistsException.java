package com.xquare.assignment.domain.client.user.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class UserExistsException extends CustomException {

    public static final CustomException EXCEPTION =
            new UserExistsException();

    private UserExistsException() {
        super(ErrorCode.USER_EXISTS);
    }
}
