package com.xquare.assignment.domain.comment.exception;

import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class NoPermissionToDeleteCommentException extends CustomException {

    public static final CustomException EXCEPTION =
            new NoPermissionToDeleteCommentException();

    private NoPermissionToDeleteCommentException() {
        super(ErrorCode.NO_PERMISSION_TO_DELETE_COMMENT);
    }
}
