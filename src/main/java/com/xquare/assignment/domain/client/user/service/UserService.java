package com.xquare.assignment.domain.client.user.service;

import com.xquare.assignment.domain.client.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.client.common.dto.response.TokenResponse;
import com.xquare.assignment.domain.client.common.domain.Role;
import com.xquare.assignment.domain.client.common.domain.Client;
import com.xquare.assignment.domain.client.common.domain.repository.ClientRepository;
import com.xquare.assignment.domain.client.user.exception.ClientExistsException;
import com.xquare.assignment.domain.client.common.dto.request.SignUpRequest;
import com.xquare.assignment.domain.client.common.exception.PasswordMisMatchException;
import com.xquare.assignment.global.exception.ClientNotFoundException;
import com.xquare.assignment.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signUp(SignUpRequest request) {
        if (clientRepository.findByAccountId(request.getAccountId()).isPresent()) {
            throw ClientExistsException.EXCEPTION;
        }

        Client user = Client.builder()
                .accountId(request.getAccountId())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .profileImageUrl(request.getProfileImageUrl())
                .role(Role.USER)
                .build();
        clientRepository.save(user);
    }

    @Transactional
    public TokenResponse signIn(SignInRequest request) {
        Client user = clientRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> ClientNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        }

        TokenResponse tokenResponse = jwtTokenProvider.generateTokens(request.getAccountId(), Role.USER);

        return TokenResponse.builder()
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
    }
}
