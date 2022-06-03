package com.xquare.assignment.domain.auth.user.service;

import com.xquare.assignment.domain.auth.common.domain.Auth;
import com.xquare.assignment.domain.auth.common.domain.Role;
import com.xquare.assignment.domain.auth.common.domain.repository.AuthRepository;
import com.xquare.assignment.domain.auth.common.dto.request.SignInRequest;
import com.xquare.assignment.domain.auth.common.dto.request.SignUpRequest;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;
import com.xquare.assignment.domain.auth.user.exception.AuthExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthService userAuthService;
    private final UserReissueService userReissueService;

    @Transactional
    public void signUp(SignUpRequest request) {
        if (authRepository.existsByAccountId(request.getAccountId())) {
            throw AuthExistsException.EXCEPTION;
        }

        Auth auth = Auth.builder()
                .accountId(request.getAccountId())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .profileImageUrl(request.getProfileImageUrl())
                .role(Role.USER)
                .build();
        authRepository.save(auth);
    }

    @Transactional
    public TokenResponse signIn(SignInRequest request) {
        return userAuthService.userAuth(request);
    }

    @Transactional
    public TokenResponse reIssue(String refreshToken) {
        return userReissueService.userReissue(refreshToken);
    }
}
