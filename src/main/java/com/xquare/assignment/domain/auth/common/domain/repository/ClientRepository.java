package com.xquare.assignment.domain.auth.common.domain.repository;

import com.xquare.assignment.domain.auth.common.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByAccountId(String accountId);

    Boolean existsByAccountId(String accountId);
}