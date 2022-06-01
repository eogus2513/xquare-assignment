package com.xquare.assignment.domain.client.user.controller;

import com.xquare.assignment.domain.client.global.BaseClient;
import com.xquare.assignment.domain.client.global.dto.request.SignInRequest;
import com.xquare.assignment.domain.client.global.dto.request.SignUpRequest;
import com.xquare.assignment.domain.client.global.dto.response.TokenResponse;
import com.xquare.assignment.domain.client.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController implements BaseClient {

    private final UserService userService;

    @Override
    @PostMapping("/token")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest request) {
        return userService.signIn(request);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUpRequest request) {
        userService.signUp(request);
    }


}
