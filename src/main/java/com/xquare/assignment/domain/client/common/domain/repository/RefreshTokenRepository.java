package com.xquare.assignment.domain.client.common.domain.repository;

import com.xquare.assignment.domain.client.common.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
