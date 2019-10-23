package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;

import java.util.Set;

public class AbstractMapperTest {

    protected Account getAccount() {
        final Account account = Account.builder()
            .id(1L)
            .name("Anatoly")
            .email("anatoly@hexlet.io")
            .password("12345678")
            .build();
        account.setCorrections(getCorrections(account));
        return account;
    }

    protected Set<Correction> getCorrections(Account account) {
        return Set.of(
            Correction.builder()
                .id(1L)
                .comment("Some comment1")
                .beforeHighlight("before highlight1")
                .highlightText("some mistake1")
                .afterHighlight("after highlight1")
                .pageURL("https://hexlet.io/test1")
                .account(account)
                .build(),
            Correction.builder()
                .id(3L)
                .comment("Some comment3")
                .beforeHighlight("before highlight3")
                .highlightText("some mistake3")
                .afterHighlight("after highlight3")
                .pageURL("https://hexlet.io/test3")
                .account(account)
                .build(),
            Correction.builder()
                .id(6L)
                .comment("Some comment6")
                .beforeHighlight("before highlight6")
                .highlightText("some mistake6")
                .afterHighlight("after highlight6")
                .pageURL("https://hexlet.io/test6")
                .account(account)
                .build()
        );
    }
}
