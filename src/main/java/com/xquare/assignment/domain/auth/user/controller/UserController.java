package com.xquare.assignment.domain.auth.user.controller;

import com.xquare.assignment.domain.auth.common.BaseClient;
import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.request.SignUpRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;
import com.xquare.assignment.domain.auth.user.service.UserAuthService;
import com.xquare.assignment.domain.auth.user.service.UserReissueService;
import com.xquare.assignment.domain.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController implements BaseClient {

    private final UserService userService;
    private final UserReissueService userReissueService;
    private final UserAuthService userAuthService;

    @Override
    @PostMapping("/token")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest request) {
        return userAuthService.userAuth(request);
    }

    @Override
    @PatchMapping("/token")
    public TokenResponse reissue(@RequestHeader("Refresh-Token") String refreshToken) {
        return userReissueService.userReissue(refreshToken);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUpRequest request) {
        userService.signUp(request);
    }

}
