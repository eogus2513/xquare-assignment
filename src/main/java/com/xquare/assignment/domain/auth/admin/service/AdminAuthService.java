package com.xquare.assignment.domain.auth.admin.service;

import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;

public interface AdminAuthService {
    TokenResponse adminAuth(SignInRequest request);
}
