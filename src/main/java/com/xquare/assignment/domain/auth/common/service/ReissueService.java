package com.xquare.assignment.domain.auth.common.service;

import com.xquare.assignment.domain.auth.admin.service.AdminReissueService;
import com.xquare.assignment.domain.auth.common.domain.RefreshToken;
import com.xquare.assignment.domain.auth.common.domain.Role;
import com.xquare.assignment.domain.auth.common.domain.repository.RefreshTokenRepository;
import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;
import com.xquare.assignment.domain.auth.common.exception.InvalidRefreshTokenException;
import com.xquare.assignment.domain.auth.common.exception.RefreshTokenNotFoundException;
import com.xquare.assignment.domain.auth.user.service.UserReissueService;
import com.xquare.assignment.global.exception.InvalidJWTException;
import com.xquare.assignment.global.security.jwt.JwtProperty;
import com.xquare.assignment.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReissueService implements AdminReissueService, UserReissueService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperty jwtProperty;

    @Override
    public TokenResponse adminReissue(String refreshToken) {
        return reIssue(refreshToken, Role.ADMIN);
    }

    @Override
    public TokenResponse userReissue(String refreshToken) {
        return reIssue(refreshToken, Role.USER);
    }

    private TokenResponse reIssue(String refreshToken, Role role) {
        String parseToken = jwtTokenProvider.parseToken(refreshToken);
        if (parseToken == null) {
            throw InvalidRefreshTokenException.EXCEPTION;
        }

        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(parseToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        TokenResponse tokens = jwtTokenProvider.generateTokens(redisRefreshToken.getAccountId(), role);

        redisRefreshToken.updateToken(tokens.getRefreshToken(), jwtProperty.getExp().getRefresh());

        return TokenResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .build();
    }
}
