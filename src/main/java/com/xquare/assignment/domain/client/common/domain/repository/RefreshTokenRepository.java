package com.xquare.assignment.domain.client.common.domain.repository;

import com.xquare.assignment.domain.client.common.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String refreshToken);
}
