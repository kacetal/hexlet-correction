package io.hexlet.hexletcorrection.service.impl;

import com.google.common.base.Strings;
import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.repository.AccountRepository;
import io.hexlet.hexletcorrection.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Account> findByEmail(String email) {
        if (Strings.isNullOrEmpty(email)) {
            return empty();
        }
        return accountRepository.findOneByEmail(email);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        if (Strings.isNullOrEmpty(username)) {
            return empty();
        }
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAllByIds(Iterable<Long> ids) {
        if (ids == null) {
            return emptyList();
        }
        return accountRepository.findAllById(ids);
    }

    @Override
    public Optional<Account> findById(Long id) {
        if (id == null) {
            return empty();
        }
        return accountRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return accountRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        if (id == null) {
            return true;
        }
        return !accountRepository.existsById(id);
    }

    @Override
    public long count() {
        return accountRepository.count();
    }

    @Override
    public Account save(Account entity) {
        if (entity == null) {
            return null;
        }

        if (entity.getId() == null) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            return accountRepository.save(entity);
        }

        Account accountToUpdate = accountRepository.findById(entity.getId()).orElseThrow();
        accountToUpdate.setLastName(entity.getLastName());
        accountToUpdate.setFirstName(entity.getFirstName());
        accountToUpdate.setEmail(entity.getEmail());
        return accountRepository.save(accountToUpdate);
    }

    @Override
    public List<Account> saveAll(Iterable<Account> entities) {
        if (entities == null) {
            return null;
        }
        return accountRepository.saveAll(entities);
    }

    @Override
    public void delete(Account entity) {
        if (entity == null) {
            return;
        }

        try {
            accountRepository.delete(entity);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Delete non existing entity with id=" + entity.getId(), e);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            return;
        }

        try {
            accountRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Delete non existing entity with id=" + id, e);
        }
    }
}
