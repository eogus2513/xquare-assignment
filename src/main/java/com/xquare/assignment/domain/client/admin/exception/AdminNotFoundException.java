package com.xquare.assignment.domain.client.admin.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class AdminNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new AdminNotFoundException();

    private AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
