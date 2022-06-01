package com.xquare.assignment.domain.client.global;

import com.xquare.assignment.domain.client.global.dto.request.SignInRequest;
import com.xquare.assignment.domain.client.global.dto.response.TokenResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface BaseClient {

    TokenResponse signIn(@RequestBody @Valid SignInRequest request);
}
