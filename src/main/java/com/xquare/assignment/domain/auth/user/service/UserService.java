package com.xquare.assignment.domain.auth.user.service;

import com.xquare.assignment.domain.auth.common.domain.Client;
import com.xquare.assignment.domain.auth.common.domain.Role;
import com.xquare.assignment.domain.auth.common.domain.repository.ClientRepository;
import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.request.SignUpRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;
import com.xquare.assignment.domain.auth.user.exception.ClientExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthService userAuthService;
    private final UserReissueService userReissueService;

    @Transactional
    public void signUp(SignUpRequest request) {
        if (clientRepository.existsByAccountId(request.getAccountId())) {
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
        return userAuthService.userAuth(request);
    }

    public TokenResponse reIssue(String refreshToken) {
        return userReissueService.userReissue(refreshToken);
    }
}
