package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.AccountGetDto;
import io.hexlet.hexletcorrection.dto.AccountPostDto;
import io.hexlet.hexletcorrection.dto.AccountPutDto;
import io.hexlet.hexletcorrection.dto.CorrectionGetDto;

import java.util.Set;

public class AbstractMapperTest {

    private final static String PASSWORD = "12345678";

    protected Account getAccount() {
        final Account account = Account.builder()
            .id(1L)
            .name("Anatoly")
            .email("anatoly@hexlet.io")
            .password(PASSWORD)
            .build();
        account.setCorrections(getCorrections(account));
        return account;
    }

    protected AccountGetDto getAccountGetDto() {
        return AccountGetDto.builder()
            .id(1L)
            .name("Anatoly")
            .email("anatoly@hexlet.io")
            .corrections(getSetCorrectionGetDto())
            .build();
    }

    protected AccountGetDto getAccountGetDtoWithoutCorrections() {
        return AccountGetDto.builder()
            .id(1L)
            .name("Anatoly")
            .email("anatoly@hexlet.io")
            .corrections(null)
            .build();
    }

    protected AccountPutDto getAccountPutDto() {
        return AccountPutDto.builder()
            .id(1L)
            .name("Anatoly")
            .email("anatoly@hexlet.io")
            .password(PASSWORD)
            .build();
    }

    protected AccountPostDto getAccountPostDto() {
        return AccountPostDto.builder()
            .name("Anatoly")
            .email("anatoly@hexlet.io")
            .password(PASSWORD)
            .passwordConfirm(PASSWORD)
            .build();
    }

    protected Correction getCorrection() {
        return Correction.builder()
            .id(1L)
            .comment("Some comment1")
            .beforeHighlight("before highlight1")
            .highlightText("some mistake1")
            .afterHighlight("after highlight1")
            .pageURL("https://hexlet.io/test1")
            .account(getAccount())
            .build();
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

    protected CorrectionGetDto getCorrectionGetDto() {
        return CorrectionGetDto.builder()
            .id(1L)
            .comment("Some comment1")
            .beforeHighlight("before highlight1")
            .highlightText("some mistake1")
            .afterHighlight("after highlight1")
            .pageURL("https://hexlet.io/test1")
            .account(null)
            .build();
    }

    protected Set<CorrectionGetDto> getSetCorrectionGetDto() {
        return Set.of(
            CorrectionGetDto.builder()
                .id(1L)
                .comment("Some comment1")
                .beforeHighlight("before highlight1")
                .highlightText("some mistake1")
                .afterHighlight("after highlight1")
                .pageURL("https://hexlet.io/test1")
                .build(),
            CorrectionGetDto.builder()
                .id(3L)
                .comment("Some comment3")
                .beforeHighlight("before highlight3")
                .highlightText("some mistake3")
                .afterHighlight("after highlight3")
                .pageURL("https://hexlet.io/test3")
                .build(),
            CorrectionGetDto.builder()
                .id(6L)
                .comment("Some comment6")
                .beforeHighlight("before highlight6")
                .highlightText("some mistake6")
                .afterHighlight("after highlight6")
                .pageURL("https://hexlet.io/test6")
                .build()
        );
    }

}
