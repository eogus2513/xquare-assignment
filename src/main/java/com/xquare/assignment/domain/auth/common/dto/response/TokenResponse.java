package com.xquare.assignment.domain.auth.common.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {

    private final String accessToken;
    private final String refreshToken;
}
