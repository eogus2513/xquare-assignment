package com.xquare.assignment.domain.auth.user.service;

import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;

public interface UserAuthService {
    TokenResponse userAuth(SignInRequest request);
}
