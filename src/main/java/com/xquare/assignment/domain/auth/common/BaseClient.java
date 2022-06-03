package com.xquare.assignment.domain.auth.common;

import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

public interface BaseClient {

    TokenResponse signIn(@RequestBody @Valid SignInRequest request);

    TokenResponse reissue(@RequestHeader("Refresh-Token") String refreshToken);
}
