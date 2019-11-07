package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.dto.account.AccountGetDto;
import io.hexlet.hexletcorrection.dto.account.AccountPutDto;
import io.hexlet.hexletcorrection.dto.account.AccountViewDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionGetDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest extends AbstractMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void accountToAccountGetDtoTest() {
        final AccountGetDto expectedAccountGetDto = getAccountGetDto();
        final AccountGetDto actualAccountGetDto = accountMapper.toAccountGetDto(getAccount());
        assertEquals(expectedAccountGetDto.getId(), actualAccountGetDto.getId());
        assertEquals(expectedAccountGetDto.getUsername(), actualAccountGetDto.getUsername());
        assertEquals(expectedAccountGetDto.getLastName(), actualAccountGetDto.getLastName());
        assertEquals(expectedAccountGetDto.getFirstName(), actualAccountGetDto.getFirstName());
        assertEquals(expectedAccountGetDto.getEmail(), actualAccountGetDto.getEmail());
        assertEquals(expectedAccountGetDto.getNumberCorrectionsInProgress(), actualAccountGetDto.getNumberCorrectionsInProgress());
        assertEquals(expectedAccountGetDto.getNumberCorrectionsResolved(), actualAccountGetDto.getNumberCorrectionsResolved());
    }

    @Test
    public void accountToAccountGetDtoNullTest() {
        assertNull(accountMapper.toAccountGetDto(null));
    }

    @Test
    public void accountToAccountViewDtoTest() {
        final AccountViewDto expectedAccountViewDto = getAccountViewDto();
        final AccountViewDto actualAccountViewDto = accountMapper.toAccountViewDto(getAccount());
        assertEquals(expectedAccountViewDto.getId(), actualAccountViewDto.getId());
        assertEquals(expectedAccountViewDto.getUsername(), actualAccountViewDto.getUsername());
        assertEquals(expectedAccountViewDto.getLastName(), actualAccountViewDto.getLastName());
        assertEquals(expectedAccountViewDto.getFirstName(), actualAccountViewDto.getFirstName());
        assertEquals(expectedAccountViewDto.getEmail(), actualAccountViewDto.getEmail());
        assertEquals(expectedAccountViewDto.getCorrectionsInProgress().size(), actualAccountViewDto.getCorrectionsInProgress().size());
        assertEquals(expectedAccountViewDto.getCorrectionsResolved().size(), actualAccountViewDto.getCorrectionsResolved().size());
    }

    @Test
    public void accountToAccountViewDtoNullTest() {
        assertNull(accountMapper.toAccountViewDto(null));
    }

    @Test
    public void accountToAccountPutDtoTest() {
        final AccountPutDto expectedAccountPutDto = getAccountPutDto();
        final AccountPutDto actualAccountPutDto = accountMapper.toAccountPutDto(getAccount());
        assertEquals(expectedAccountPutDto.getId(), actualAccountPutDto.getId());
        assertEquals(expectedAccountPutDto.getLastName(), actualAccountPutDto.getLastName());
        assertEquals(expectedAccountPutDto.getFirstName(), actualAccountPutDto.getFirstName());
        assertEquals(expectedAccountPutDto.getEmail(), actualAccountPutDto.getEmail());
        assertNull(actualAccountPutDto.getPassword());
    }

    @Test
    public void accountToAccountPutDtoNullTest() {
        assertNull(accountMapper.toAccountPutDto(null));
    }

    @Test
    public void putDtoToAccountTest() {
        final Account expectedAccount = getAccount();
        final Account actualAccount = accountMapper.putDtoToAccount(getAccountPutDto());
        assertEquals(expectedAccount.getId(), actualAccount.getId());
        assertEquals(expectedAccount.getUsername(), actualAccount.getUsername());
        assertEquals(expectedAccount.getLastName(), actualAccount.getLastName());
        assertEquals(expectedAccount.getFirstName(), actualAccount.getFirstName());
        assertEquals(expectedAccount.getEmail(), actualAccount.getEmail());
        assertEquals(expectedAccount.getPassword(), actualAccount.getPassword());
        assertNotNull(actualAccount.getPassword());
    }

    @Test
    public void putDtoToAccountNullTest() {
        assertNull(accountMapper.putDtoToAccount(null));
    }

    @Test
    public void postDtoToAccountTest() {
        final Account expectedAccount = getAccount();
        final Account actualAccount = accountMapper.postDtoToAccount(getAccountPostDto());
        assertEquals(expectedAccount.getUsername(), actualAccount.getUsername());
        assertEquals(expectedAccount.getLastName(), actualAccount.getLastName());
        assertEquals(expectedAccount.getFirstName(), actualAccount.getFirstName());
        assertEquals(expectedAccount.getEmail(), actualAccount.getEmail());
        assertEquals(expectedAccount.getPassword(), actualAccount.getPassword());
        assertNull(actualAccount.getId());
    }

    @Test
    public void postDtoToAccountNullTest() {
        assertNull(accountMapper.postDtoToAccount(null));
    }

    @Test
    public void correctionsToCorrectionsGetDtoTest() {
        final CorrectionGetDto expectedCorrectionGetDto = getCorrectionGetDto();
        final CorrectionGetDto actualCorrectionGetDto = accountMapper.correctionToCorrectionGetDto(getCorrection());
        assertEquals(expectedCorrectionGetDto.getId(), actualCorrectionGetDto.getId());
        assertEquals(expectedCorrectionGetDto.getBeforeHighlightText(), actualCorrectionGetDto.getBeforeHighlightText());
        assertEquals(expectedCorrectionGetDto.getHighlightText(), actualCorrectionGetDto.getHighlightText());
        assertEquals(expectedCorrectionGetDto.getAfterHighlightText(), actualCorrectionGetDto.getAfterHighlightText());
        assertEquals(expectedCorrectionGetDto.getReporter(), actualCorrectionGetDto.getReporter());
        assertEquals(expectedCorrectionGetDto.getPageURL(), actualCorrectionGetDto.getPageURL());
        assertNull(actualCorrectionGetDto.getCorrecter());
        assertNull(actualCorrectionGetDto.getResolver());
    }

    @Test
    public void correctionsToCorrectionsGetDtoNullTest() {
        assertNull(accountMapper.correctionToCorrectionGetDto(null));
    }
}
