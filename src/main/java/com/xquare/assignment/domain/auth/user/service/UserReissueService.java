package com.xquare.assignment.domain.auth.user.service;

import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;

public interface UserReissueService {
    TokenResponse userReissue(String refreshToken);
}
