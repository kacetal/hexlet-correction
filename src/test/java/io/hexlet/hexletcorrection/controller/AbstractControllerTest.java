package io.hexlet.hexletcorrection.controller;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.mapper.AbstractMapperTest;
import io.hexlet.hexletcorrection.service.AccountService;
import io.hexlet.hexletcorrection.service.CorrectionService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractControllerTest extends AbstractMapperTest {

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected CorrectionService correctionService;

    protected Account createAccount(String login, String email) {
        Account account = getAccount();
        account.setId(null);
        account.setUsername(login);
        account.setEmail(email);
        return accountService.save(account);
    }

    protected Account createAccount() {
        Account account = getAccount();
        account.setId(null);
        return accountService.save(account);
    }

    protected Correction createCorrection(Account resolver, Account correcter) {
        Correction correction = getCorrection();
        correction.setId(null);
        correction.setResolver(resolver);
        correction.setCorrecter(correcter);
        return correctionService.save(correction);
    }

    protected Correction createCorrection() {
        Correction correction = getCorrection();
        correction.setId(null);
        return correctionService.save(correction);
    }

    protected void deleteAccount(final Long id) {
        accountService.deleteById(id);
    }
}
