package com.xquare.assignment.domain.client.common;

import com.xquare.assignment.domain.client.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.client.common.dto.response.TokenResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

public interface BaseClient {

    TokenResponse signIn(@RequestBody @Valid SignInRequest request);

    TokenResponse reissue(@RequestHeader("Refresh-Token") String refreshToken);
}
