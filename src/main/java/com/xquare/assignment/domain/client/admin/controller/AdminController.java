package com.xquare.assignment.domain.client.admin.controller;

import com.xquare.assignment.domain.client.admin.service.AdminService;
import com.xquare.assignment.domain.client.global.BaseClient;
import com.xquare.assignment.domain.client.global.dto.request.SignInRequest;
import com.xquare.assignment.domain.client.global.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AdminController implements BaseClient {

    private final AdminService adminService;

    @Override
    @PostMapping("/admin/token")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest request) {
        return adminService.signIn(request);
    }
}
