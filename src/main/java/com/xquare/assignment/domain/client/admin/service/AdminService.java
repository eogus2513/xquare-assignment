package com.xquare.assignment.domain.client.admin.service;

import com.xquare.assignment.domain.client.admin.exception.AdminNotFoundException;
import com.xquare.assignment.domain.client.common.exception.PasswordMisMatchException;
import com.xquare.assignment.domain.client.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.client.common.dto.response.TokenResponse;
import com.xquare.assignment.domain.client.common.domain.Role;
import com.xquare.assignment.domain.client.common.domain.Client;
import com.xquare.assignment.domain.client.common.domain.repository.ClientRepository;
import com.xquare.assignment.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse signIn(SignInRequest request) {
        Client admin = clientRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        }

        TokenResponse tokenResponse = jwtTokenProvider.generateTokens(request.getAccountId(), Role.ADMIN);

        return TokenResponse.builder()
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
    }
}
