package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.dto.AccountGetDto;
import io.hexlet.hexletcorrection.dto.CorrectionGetDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
        assertEquals(expectedAccountGetDto.getName(), actualAccountGetDto.getName());
        assertEquals(expectedAccountGetDto.getEmail(), actualAccountGetDto.getEmail());
        assertEquals(expectedAccountGetDto.getCorrections().size(), actualAccountGetDto.getCorrections().size());
    }

    @Test
    public void accountToAccountGetDtoNullTest() {
        assertNull(accountMapper.toAccountGetDto(null));
    }

    @Test
    public void accountToAccountGetDtoWithoutCorrectionsTest() {
        final AccountGetDto expectedAccountGetDto = getAccountGetDtoWithoutCorrections();
        final AccountGetDto actualAccountGetDto = accountMapper.toAccountGetDtoWithoutCorrections(getAccount());
        assertEquals(expectedAccountGetDto.getId(), actualAccountGetDto.getId());
        assertEquals(expectedAccountGetDto.getName(), actualAccountGetDto.getName());
        assertEquals(expectedAccountGetDto.getEmail(), actualAccountGetDto.getEmail());
        assertNull(actualAccountGetDto.getCorrections());
    }

    @Test
    public void accountToAccountGetDtoWithoutCorrectionsNullTest() {
        assertNull(accountMapper.toAccountGetDtoWithoutCorrections(null));
    }

    @Test
    public void accountPutDtoToAccountTest() {
        final Account expectedAccount = getAccount();
        final Account actualAccount = accountMapper.putDtoToAccount(getAccountPutDto());
        assertEquals(expectedAccount.getId(), actualAccount.getId());
        assertEquals(expectedAccount.getName(), actualAccount.getName());
        assertEquals(expectedAccount.getEmail(), actualAccount.getEmail());
        assertEquals(expectedAccount.getPassword(), actualAccount.getPassword());
        assertNotEquals(expectedAccount.getCorrections(), actualAccount.getCorrections());
        assertNull(actualAccount.getCorrections());
    }

    @Test
    public void accountPutDtoToAccountNullTest() {
        assertNull(accountMapper.putDtoToAccount(null));
    }

    @Test
    public void accountPostDtoToAccountTest() {
        final Account expectedAccount = getAccount();
        final Account actualAccount = accountMapper.postDtoToAccount(getAccountPostDto());
        assertEquals(expectedAccount.getName(), actualAccount.getName());
        assertEquals(expectedAccount.getEmail(), actualAccount.getEmail());
        assertEquals(expectedAccount.getPassword(), actualAccount.getPassword());
        assertNotEquals(expectedAccount.getCorrections(), actualAccount.getCorrections());
        assertNotEquals(expectedAccount.getId(), actualAccount.getId());
        assertNull(actualAccount.getCorrections());
        assertNull(actualAccount.getId());
    }

    @Test
    public void accountPostDtoToAccountNullTest() {
        assertNull(accountMapper.postDtoToAccount(null));
    }

    @Test
    public void correctionsToCorrectionsGetDtoTest() {
        final CorrectionGetDto expectedCorrectionGetDto = getCorrectionGetDto();
        final CorrectionGetDto actualCorrectionGetDto = accountMapper.toCorrectionsGetDto(getCorrection());
        assertEquals(expectedCorrectionGetDto.getId(), actualCorrectionGetDto.getId());
        assertEquals(expectedCorrectionGetDto.getComment(), actualCorrectionGetDto.getComment());
        assertEquals(expectedCorrectionGetDto.getBeforeHighlight(), actualCorrectionGetDto.getBeforeHighlight());
        assertEquals(expectedCorrectionGetDto.getHighlightText(), actualCorrectionGetDto.getHighlightText());
        assertEquals(expectedCorrectionGetDto.getAfterHighlight(), actualCorrectionGetDto.getAfterHighlight());
        assertEquals(expectedCorrectionGetDto.getPageURL(), actualCorrectionGetDto.getPageURL());
        assertEquals(expectedCorrectionGetDto.getAccount(), actualCorrectionGetDto.getAccount());
        assertNull(actualCorrectionGetDto.getAccount());
    }

    @Test
    public void correctionsToCorrectionsGetDtoNullTest() {
        assertNull(accountMapper.toCorrectionsGetDto(null));
    }
}
