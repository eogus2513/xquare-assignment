package com.xquare.assignment.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    PASSWORD_MISMATCH(401, "Password MisMatch"),
    EXPIRED_JWT(401, "Expired Jwt"),
    SIGNATURE_JWT(401, "Signature Jwt"),
    INVALID_JWT(401, "Invalid Jwt"),
    NO_PERMISSION_TO_MODIFY_POST(401, "No Permission To Modify Post"),
    NO_PERMISSION_TO_DELETE_POST(401, "NO Permission To Delete Post"),
    NO_PERMISSION_TO_MODIFY_COMMENT(401, "No Permission To Modify Comment"),
    NO_PERMISSION_TO_DELETE_COMMENT(401, "NO Permission To Delete Comment"),

    CLIENT_NOT_FOUND(404, "Client Not Found"),
    POST_NOT_FOUND(404, "Post Not Found"),
    COMMENT_NOT_FOUND(404, "Comment Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "RefreshToken Not Found"),

    CLIENT_EXISTS(409, "Client Exists"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;

}
