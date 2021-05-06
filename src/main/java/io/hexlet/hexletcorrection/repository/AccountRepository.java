package io.hexlet.hexletcorrection.repository;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.security.model.SecuredAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> getAccountByUsername(final String username);

    Optional<SecuredAccount> getSecuredAccountByUsername(final String username);
}
