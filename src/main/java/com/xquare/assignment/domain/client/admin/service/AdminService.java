package com.xquare.assignment.domain.client.admin.service;

import com.xquare.assignment.domain.client.admin.exception.AdminNotFoundException;
import com.xquare.assignment.domain.client.global.exception.PasswordMisMatchException;
import com.xquare.assignment.domain.client.global.dto.request.SignInRequest;
import com.xquare.assignment.domain.client.global.dto.response.TokenResponse;
import com.xquare.assignment.domain.client.global.domain.Role;
import com.xquare.assignment.domain.client.global.domain.Client;
import com.xquare.assignment.domain.client.global.domain.repository.ClientRepository;
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
