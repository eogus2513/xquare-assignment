package com.xquare.assignment.global.facade;

import com.xquare.assignment.domain.auth.common.domain.Auth;
import com.xquare.assignment.domain.auth.common.domain.repository.AuthRepository;
import com.xquare.assignment.global.exception.AuthNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CurrentFacade {

    private final AuthRepository authRepository;

    public Auth getCurrentAuth() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return authRepository.findByAccountId(accountId)
                .orElseThrow(() -> AuthNotFoundException.EXCEPTION);
    }
}
