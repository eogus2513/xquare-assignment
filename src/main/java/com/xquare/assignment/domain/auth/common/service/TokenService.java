package com.xquare.assignment.domain.auth.common.service;

import com.xquare.assignment.domain.auth.admin.service.AdminAuthService;
import com.xquare.assignment.domain.auth.common.domain.Auth;
import com.xquare.assignment.domain.auth.common.domain.Role;
import com.xquare.assignment.domain.auth.common.domain.repository.AuthRepository;
import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;
import com.xquare.assignment.domain.auth.common.exception.PasswordMisMatchException;
import com.xquare.assignment.domain.auth.user.service.UserAuthService;
import com.xquare.assignment.global.exception.AuthNotFoundException;
import com.xquare.assignment.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService implements AdminAuthService, UserAuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponse adminAuth(SignInRequest request) {
        return signIn(request, Role.ADMIN);
    }

    @Override
    public TokenResponse userAuth(SignInRequest request) {
        return signIn(request, Role.USER);
    }

    private TokenResponse signIn(SignInRequest request, Role role) {
        Auth auth = authRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> AuthNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), auth.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        }

        TokenResponse tokenResponse = jwtTokenProvider.generateTokens(request.getAccountId(), role);

        return TokenResponse.builder()
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
    }
}
