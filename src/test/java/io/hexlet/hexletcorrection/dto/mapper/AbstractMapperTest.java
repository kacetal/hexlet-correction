package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.account.AccountGetDto;
import io.hexlet.hexletcorrection.dto.account.AccountPostDto;
import io.hexlet.hexletcorrection.dto.account.AccountPutDto;
import io.hexlet.hexletcorrection.dto.account.AccountViewDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionGetDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionPostDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionPutDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionViewDto;

import java.util.Set;

import static java.util.Collections.emptySet;

public class AbstractMapperTest {

    protected final static String LOGIN = "MyLogin";
    protected final static String LAST_NAME = "MyLastName";
    protected final static String FIRST_NAME = "MyFirstName";
    protected final static String EMAIL = "email@email.com";
    protected final static String PASSWORD = "12345678";

    protected final static String REPORTER_COMMENT = "REPORTER_COMMENT";
    protected final static String CORRECTER_COMMENT = "CORRECTER_COMMENT";
    protected final static String RESOLVER_COMMENT = "RESOLVER_COMMENT";
    protected final static String BEFORE_HIGHLIGHT_TEXT = "BEFORE_HIGHLIGHT_TEXT";
    protected final static String HIGHLIGHT_TEXT = "HIGHLIGHT_TEXT";
    protected final static String AFTER_HIGHLIGHT_TEXT = "AFTER_HIGHLIGHT_TEXT";
    protected final static String REPORTER = "REPORTER";
    protected final static String PAGE_URL = "PAGE_URL";

    protected Account getAccount() {
        final Correction correctionOne = getCorrectionWithoutAccount();
        correctionOne.setId(1L);
        final Correction correctionTwo = getCorrectionWithoutAccount();
        correctionTwo.setId(2L);
        final Correction correctionThree = getCorrectionWithoutAccount();
        correctionThree.setId(3L);
        return Account.builder()
            .id(1L)
            .username(LOGIN)
            .lastName(LAST_NAME)
            .firstName(FIRST_NAME)
            .email(EMAIL)
            .correctionsInProgress(Set.of(correctionOne))
            .correctionsResolved(Set.of(correctionTwo, correctionThree))
            .password(PASSWORD)
            .build();
    }

    private Account getAccountWithoutCorrection() {
        final Account account = Account.builder()
            .id(1L)
            .username(LOGIN)
            .lastName(LAST_NAME)
            .firstName(FIRST_NAME)
            .email(EMAIL)
            .password(PASSWORD)
            .correctionsInProgress(emptySet())
            .correctionsResolved(emptySet())
            .build();
        return account;
    }

    protected AccountGetDto getAccountGetDto() {
        return AccountGetDto.builder()
            .id(1L)
            .username(LOGIN)
            .lastName(LAST_NAME)
            .firstName(FIRST_NAME)
            .email(EMAIL)
            .numberCorrectionsInProgress(1)
            .numberCorrectionsResolved(2)
            .build();
    }

    protected AccountViewDto getAccountViewDto() {
        final CorrectionGetDto correctionGetDtoOne = getCorrectionGetDto();
        correctionGetDtoOne.setId(1L);
        final CorrectionGetDto correctionGetDtoTwo = getCorrectionGetDto();
        correctionGetDtoTwo.setId(2L);
        final CorrectionGetDto correctionGetDtoThree = getCorrectionGetDto();
        correctionGetDtoThree.setId(3L);
        return AccountViewDto.builder()
            .id(1L)
            .username(LOGIN)
            .lastName(LAST_NAME)
            .firstName(FIRST_NAME)
            .email(EMAIL)
            .correctionsInProgress(Set.of(correctionGetDtoOne))
            .correctionsResolved(Set.of(correctionGetDtoTwo, correctionGetDtoThree))
            .build();
    }

    protected AccountPutDto getAccountPutDto() {
        return AccountPutDto.builder()
            .id(1L)
            .lastName(LAST_NAME)
            .firstName(FIRST_NAME)
            .email(EMAIL)
            .password(PASSWORD)
            .build();
    }

    protected AccountPostDto getAccountPostDto() {
        return AccountPostDto.builder()
            .username(LOGIN)
            .lastName(LAST_NAME)
            .firstName(FIRST_NAME)
            .email(EMAIL)
            .passwordConfirm(PASSWORD)
            .password(PASSWORD)
            .build();
    }

    protected Correction getCorrection() {
        return Correction.builder()
            .id(1L)
            .reporterComment(REPORTER_COMMENT)
            .correcterComment(CORRECTER_COMMENT)
            .resolverComment(RESOLVER_COMMENT)
            .beforeHighlightText(BEFORE_HIGHLIGHT_TEXT)
            .highlightText(HIGHLIGHT_TEXT)
            .afterHighlightText(AFTER_HIGHLIGHT_TEXT)
            .reporter(REPORTER)
            .correcter(getAccountWithoutCorrection())
            .resolver(getAccountWithoutCorrection())
            .pageURL(PAGE_URL)
            .build();
    }

    private Correction getCorrectionWithoutAccount() {
        return Correction.builder()
            .id(1L)
            .reporterComment(REPORTER_COMMENT)
            .correcterComment(CORRECTER_COMMENT)
            .resolverComment(RESOLVER_COMMENT)
            .beforeHighlightText(BEFORE_HIGHLIGHT_TEXT)
            .highlightText(HIGHLIGHT_TEXT)
            .afterHighlightText(AFTER_HIGHLIGHT_TEXT)
            .reporter(REPORTER)
            .pageURL(PAGE_URL)
            .build();
    }

    protected CorrectionGetDto getCorrectionGetDto() {
        return CorrectionGetDto.builder()
            .id(1L)
            .beforeHighlightText(BEFORE_HIGHLIGHT_TEXT)
            .highlightText(HIGHLIGHT_TEXT)
            .afterHighlightText(AFTER_HIGHLIGHT_TEXT)
            .reporter(REPORTER)
            .correcter(getAccountGetDto())
            .resolver(getAccountGetDto())
            .pageURL(PAGE_URL)
            .build();
    }

    protected CorrectionViewDto getCorrectionViewDto() {
        return CorrectionViewDto.builder()
            .id(1L)
            .reporterComment(REPORTER_COMMENT)
            .correcterComment(CORRECTER_COMMENT)
            .resolverComment(RESOLVER_COMMENT)
            .beforeHighlightText(BEFORE_HIGHLIGHT_TEXT)
            .highlightText(HIGHLIGHT_TEXT)
            .afterHighlightText(AFTER_HIGHLIGHT_TEXT)
            .reporter(REPORTER)
            .correcter(getAccountGetDto())
            .resolver(getAccountGetDto())
            .pageURL(PAGE_URL)
            .build();
    }

    protected CorrectionPutDto getCorrectionPutDto() {
        return CorrectionPutDto.builder()
            .id(1L)
            .reporterComment(REPORTER_COMMENT)
            .correcterComment(CORRECTER_COMMENT)
            .resolverComment(RESOLVER_COMMENT)
            .correcter(getAccountGetDto())
            .pageURL(PAGE_URL)
            .build();
    }

    protected CorrectionPostDto getCorrectionPostDto() {
        return CorrectionPostDto.builder()
            .reporterComment(REPORTER_COMMENT)
            .beforeHighlightText(BEFORE_HIGHLIGHT_TEXT)
            .highlightText(HIGHLIGHT_TEXT)
            .afterHighlightText(AFTER_HIGHLIGHT_TEXT)
            .reporter(REPORTER)
            .pageURL(PAGE_URL)
            .build();
    }
}
