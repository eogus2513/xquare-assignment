package com.xquare.assignment.domain.auth.common.service;

import com.xquare.assignment.domain.auth.admin.service.AdminAuthService;
import com.xquare.assignment.domain.auth.common.domain.Client;
import com.xquare.assignment.domain.auth.common.domain.Role;
import com.xquare.assignment.domain.auth.common.domain.repository.ClientRepository;
import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;
import com.xquare.assignment.domain.auth.common.exception.PasswordMisMatchException;
import com.xquare.assignment.domain.auth.user.service.UserAuthService;
import com.xquare.assignment.global.exception.ClientNotFoundException;
import com.xquare.assignment.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService implements AdminAuthService, UserAuthService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponse adminAuth(SignInRequest request) {
        return generateToken(request, Role.ADMIN);
    }

    @Override
    public TokenResponse userAuth(SignInRequest request) {
        return generateToken(request, Role.USER);
    }

    private TokenResponse generateToken(SignInRequest request, Role role) {
        Client client = clientRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> ClientNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), client.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        }

        TokenResponse tokenResponse = jwtTokenProvider.generateTokens(request.getAccountId(), role);

        return TokenResponse.builder()
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
    }
}
