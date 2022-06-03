package com.xquare.assignment.domain.auth.admin.service;

import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminAuthService adminAuthService;
    private final AdminReissueService adminReissueService;

    @Transactional
    public TokenResponse signIn(SignInRequest request) {
        return adminAuthService.adminAuth(request);
    }

    public TokenResponse reIssue(String refreshToken) {
        return adminReissueService.adminReissue(refreshToken);
    }
}
