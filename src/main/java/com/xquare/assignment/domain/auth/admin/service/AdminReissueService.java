package com.xquare.assignment.domain.auth.admin.service;

import com.xquare.assignment.domain.auth.common.dto.response.TokenResponse;

public interface AdminReissueService {

    TokenResponse adminReissue(String refreshToken);
}
