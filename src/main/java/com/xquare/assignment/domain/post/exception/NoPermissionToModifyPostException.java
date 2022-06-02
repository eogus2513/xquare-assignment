package com.xquare.assignment.domain.post.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class NoPermissionToModifyPostException extends CustomException {

    public static final CustomException EXCEPTION =
            new NoPermissionToModifyPostException();

    private NoPermissionToModifyPostException() {
        super(ErrorCode.NO_PERMISSION_TO_MODIFY_POST);
    }
}
