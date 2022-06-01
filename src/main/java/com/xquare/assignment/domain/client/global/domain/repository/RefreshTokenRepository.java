package com.xquare.assignment.domain.client.global.domain.repository;

import com.xquare.assignment.domain.client.global.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
