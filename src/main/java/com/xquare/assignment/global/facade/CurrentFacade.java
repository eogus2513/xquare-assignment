package com.xquare.assignment.global.facade;

import com.xquare.assignment.domain.auth.common.domain.Client;
import com.xquare.assignment.domain.auth.common.domain.repository.ClientRepository;
import com.xquare.assignment.global.exception.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CurrentFacade {

    private final ClientRepository clientRepository;

    public Client getCurrentClient() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return clientRepository.findByAccountId(accountId)
                .orElseThrow(() -> ClientNotFoundException.EXCEPTION);
    }
}
