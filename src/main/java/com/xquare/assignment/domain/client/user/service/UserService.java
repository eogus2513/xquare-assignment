package com.xquare.assignment.domain.client.user.service;

import com.xquare.assignment.domain.client.global.dto.request.SignInRequest;
import com.xquare.assignment.domain.client.global.dto.response.TokenResponse;
import com.xquare.assignment.domain.client.global.domain.Role;
import com.xquare.assignment.domain.client.global.domain.Client;
import com.xquare.assignment.domain.client.global.domain.repository.ClientRepository;
import com.xquare.assignment.domain.client.user.exception.UserExistsException;
import com.xquare.assignment.domain.client.global.dto.request.SignUpRequest;
import com.xquare.assignment.domain.client.global.exception.PasswordMisMatchException;
import com.xquare.assignment.global.exception.UserNotFoundException;
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
            throw UserExistsException.EXCEPTION;
        }

        Client user = Client.builder()
                .accountId(request.getAccountId())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .profileImageUrl(request.getProfileImageUrl())
                .build();
        clientRepository.save(user);
    }

    @Transactional
    public TokenResponse signIn(SignInRequest request) {
        Client user = clientRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

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
