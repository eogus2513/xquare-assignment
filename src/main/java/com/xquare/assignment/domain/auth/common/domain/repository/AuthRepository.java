package com.xquare.assignment.domain.auth.common.domain.repository;

import com.xquare.assignment.domain.auth.common.domain.Auth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends CrudRepository<Auth, Long> {
    Optional<Auth> findByAccountId(String accountId);

    Boolean existsByAccountId(String accountId);
}
