package com.xquare.assignment.domain.auth.admin.controller;

import com.xquare.assignment.domain.auth.admin.service.AdminAuthService;
import com.xquare.assignment.domain.auth.admin.service.AdminReissueService;
import com.xquare.assignment.domain.auth.common.BaseClient;
import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController implements BaseClient {

    private final AdminAuthService adminAuthService;
    private final AdminReissueService adminReissueService;

    @Override
    @PostMapping("/token")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest request) {
        return adminAuthService.adminAuth(request);
    }

    @Override
    @PatchMapping("/token")
    public TokenResponse reissue(@RequestHeader("Refresh-Token") String refreshToken) {
        return adminReissueService.adminReissue(refreshToken);
    }
}
