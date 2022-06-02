package com.xquare.assignment.domain.comment.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class NoPermissionToModifyCommentException extends CustomException {

    public static final CustomException EXCEPTION =
            new NoPermissionToModifyCommentException();

    private NoPermissionToModifyCommentException() {
        super(ErrorCode.NO_PERMISSION_TO_MODIFY_COMMENT);
    }
}
