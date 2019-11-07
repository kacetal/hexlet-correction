package io.hexlet.hexletcorrection.service;

import io.hexlet.hexletcorrection.domain.Account;

import java.util.Optional;

public interface AccountService extends BaseService<Account, Long> {

    Optional<Account> findByEmail(String email);

    Optional<Account> findByUsername(String username);
}
