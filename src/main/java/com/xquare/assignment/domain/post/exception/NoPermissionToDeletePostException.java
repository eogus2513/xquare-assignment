package com.xquare.assignment.domain.post.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class NoPermissionToDeletePostException extends CustomException {

    public static final CustomException EXCEPTION =
            new NoPermissionToDeletePostException();

    private NoPermissionToDeletePostException() {
        super(ErrorCode.NO_PERMISSION_TO_DELETE_POST);
    }
}
