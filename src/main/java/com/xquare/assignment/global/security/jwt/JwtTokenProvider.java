package com.xquare.assignment.global.security.jwt;

import com.xquare.assignment.domain.client.global.dto.response.TokenResponse;
import com.xquare.assignment.domain.client.global.domain.RefreshToken;
import com.xquare.assignment.domain.client.global.domain.Role;
import com.xquare.assignment.domain.client.global.domain.repository.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static final String ACCESS_KEY = "access";
    private static final String REFRESH_KEY = "refresh";
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    private final JwtProperty jwtProperty;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse generateTokens(String accountId, Role role) {
        String accessToken = generateToken(accountId, role, ACCESS_KEY, jwtProperty.getExp().getAccess());
        String refreshToken = generateToken(accountId, role, REFRESH_KEY, jwtProperty.getExp().getRefresh());

        refreshTokenRepository.save(RefreshToken.builder()
                .accountId(accountId)
                .token(refreshToken)
                .ttl(jwtProperty.getExp().getRefresh())
                .build());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private String generateToken(String accountId, Role role, String type, Long exp) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperty.getSecret())
                .setSubject(accountId)
                .setHeaderParam("typ", type)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }
}
