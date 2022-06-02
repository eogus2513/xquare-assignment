package com.xquare.assignment.domain.post.exception;


import com.xquare.assignment.global.error.CustomException;
import com.xquare.assignment.global.error.ErrorCode;

public class PostNotFoundException extends CustomException {

    public static final CustomException EXCEPTION =
            new PostNotFoundException();

    private PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
