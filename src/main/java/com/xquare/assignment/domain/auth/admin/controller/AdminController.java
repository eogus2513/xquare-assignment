package com.xquare.assignment.domain.auth.admin.controller;

import com.xquare.assignment.domain.auth.admin.service.AdminService;
import com.xquare.assignment.domain.auth.common.BaseClient;
import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController implements BaseClient {

    private final AdminService adminService;

    @Override
    @PostMapping("/token")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest request) {
        return adminService.signIn(request);
    }

    @Override
    @PatchMapping("/token")
    public TokenResponse reissue(@RequestHeader("Refresh-Token") String refreshToken) {
        return adminService.reIssue(refreshToken);
    }
}
