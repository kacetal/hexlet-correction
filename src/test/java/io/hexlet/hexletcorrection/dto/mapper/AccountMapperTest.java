package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.AccountGetDto;
import io.hexlet.hexletcorrection.dto.AccountPostDto;
import io.hexlet.hexletcorrection.dto.AccountPutDto;
import io.hexlet.hexletcorrection.dto.CorrectionGetDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest extends AbstractMapperTest{

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void accountToAccountGetDtoTest() {
        final AccountGetDto expectedAccountGetDto = getAccountGetDto();
        final AccountGetDto actualAccountGetDto = accountMapper.toAccountGetDto(getAccount());
        assertEquals(expectedAccountGetDto.getName(), actualAccountGetDto.getName());
        assertEquals(expectedAccountGetDto.getEmail(), actualAccountGetDto.getEmail());
        assertEquals(expectedAccountGetDto.getCorrections().size(), actualAccountGetDto.getCorrections().size());
    }

    @Test
    public void accountToAccountGetDtoNullTest() {
        assertNull(accountMapper.toAccountGetDto(null));
    }

//    @Test
//    public void accountToAccountPutDtoTest() {
//        final AccountPutDto expectedAccountPutDto = getAccountPutDto();
//        final AccountPutDto actualAccountPutDto = accountMapper.toAccountPutDto(getAccount());
//
//        assertEquals(expectedAccountPutDto.getId(), actualAccountPutDto.getId());
//        assertEquals(expectedAccountPutDto.getName(), actualAccountPutDto.getName());
//        assertEquals(expectedAccountPutDto.getEmail(), actualAccountPutDto.getEmail());
//        assertNotEquals(expectedAccountPutDto.getPassword(), actualAccountPutDto.getPassword());
//        assertNotNull(expectedAccountPutDto.getPassword());
//        assertNull(actualAccountPutDto.getPassword());
//    }

    @Test
    public void accountToAccountPutDtoNullTest() {
        assertNull(accountMapper.toAccountGetDto(null));
    }

    @Test
    public void correctionsToCorrectionsDtoNullTest() {
        assertNull(accountMapper.toCorrectionsGetDto(null));
    }






    private Set<CorrectionGetDto> getCorrectionGetDto() {
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

    private AccountGetDto getAccountGetDto() {
        return AccountGetDto.builder()
            .name("Anatoly")
            .email("anatoly@hexlet.io")
            .corrections(getCorrectionGetDto())
            .build();
    }

    private AccountPostDto getAccountPostDto() {
        return AccountPostDto.builder()
            .name("Anatoly")
            .email("anatoly@hexlet.io")
            .password("12345678")
            .passwordConfirm("12345678")
            .build();
    }

    private AccountPutDto getAccountPutDto() {
        return AccountPutDto.builder()
            .id(1L)
            .name("Anatoly")
            .email("anatoly@hexlet.io")
            .password("123456789")
            .build();
    }
}
