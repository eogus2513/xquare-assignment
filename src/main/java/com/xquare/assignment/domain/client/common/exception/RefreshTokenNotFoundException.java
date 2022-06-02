package com.xquare.assignment.domain.client.common.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class RefreshTokenNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
