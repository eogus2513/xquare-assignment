package com.xquare.assignment.domain.client.user.controller;

import com.xquare.assignment.domain.client.common.BaseClient;
import com.xquare.assignment.domain.client.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.client.common.dto.request.SignUpRequest;
import com.xquare.assignment.domain.client.common.dto.response.TokenResponse;
import com.xquare.assignment.domain.client.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @Override
    @PatchMapping("/token")
    public TokenResponse reissue(String refreshToken) {
        return userService.reissue(refreshToken);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUpRequest request) {
        userService.signUp(request);
    }


}
