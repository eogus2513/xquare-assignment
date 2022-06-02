package com.xquare.assignment.global.security.auth;

import com.xquare.assignment.domain.client.common.domain.repository.ClientRepository;
import com.xquare.assignment.global.exception.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        return clientRepository.findByAccountId(accountId)
                .map(AuthDetails::new)
                .orElseThrow(() -> ClientNotFoundException.EXCEPTION);
    }

}
