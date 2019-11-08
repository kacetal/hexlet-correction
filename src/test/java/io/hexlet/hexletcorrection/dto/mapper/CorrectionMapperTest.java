package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.account.AccountGetDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionGetDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionPutDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionViewDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CorrectionMapperTest extends AbstractMapperTest {

    @Autowired
    private CorrectionMapper correctionMapper;

    @Test
    public void correctionToCorrectionGetDtoTest() {
        final CorrectionGetDto expectedCorrectionGetDto = getCorrectionGetDto();
        final CorrectionGetDto actualCorrectionGetDto = correctionMapper.toCorrectionGetDto(getCorrection());

        assertEquals(expectedCorrectionGetDto.getId(), actualCorrectionGetDto.getId());
        assertEquals(expectedCorrectionGetDto.getTextBeforeCorrection(), actualCorrectionGetDto.getTextBeforeCorrection());
        assertEquals(expectedCorrectionGetDto.getTextCorrection(), actualCorrectionGetDto.getTextCorrection());
        assertEquals(expectedCorrectionGetDto.getTextAfterCorrection(), actualCorrectionGetDto.getTextAfterCorrection());
        assertEquals(expectedCorrectionGetDto.getReporterName(), actualCorrectionGetDto.getReporterName());
        assertEquals(expectedCorrectionGetDto.getResolver().getId(), actualCorrectionGetDto.getResolver().getId());
        assertEquals(expectedCorrectionGetDto.getPageURL(), actualCorrectionGetDto.getPageURL());
        assertEquals(expectedCorrectionGetDto.getTextCorrection(), actualCorrectionGetDto.getTextCorrection());
    }

    @Test
    public void correctionToCorrectionGetDtoNullTest() {
        assertNull(correctionMapper.toCorrectionGetDto(null));
    }

    @Test
    public void correctionToCorrectionViewDtoTest() {
        final CorrectionViewDto expectedCorrectionViewDto = getCorrectionViewDto();
        final CorrectionViewDto actualCorrectionViewDto = correctionMapper.toCorrectionViewDto(getCorrection());

        assertEquals(expectedCorrectionViewDto.getId(), actualCorrectionViewDto.getId());
        assertEquals(expectedCorrectionViewDto.getReporterComment(), actualCorrectionViewDto.getReporterComment());
        assertEquals(expectedCorrectionViewDto.getCorrecterComment(), actualCorrectionViewDto.getCorrecterComment());
        assertEquals(expectedCorrectionViewDto.getResolverComment(), actualCorrectionViewDto.getResolverComment());
        assertEquals(expectedCorrectionViewDto.getTextBeforeCorrection(), actualCorrectionViewDto.getTextBeforeCorrection());
        assertEquals(expectedCorrectionViewDto.getTextCorrection(), actualCorrectionViewDto.getTextCorrection());
        assertEquals(expectedCorrectionViewDto.getTextAfterCorrection(), actualCorrectionViewDto.getTextAfterCorrection());
        assertEquals(expectedCorrectionViewDto.getReporterName(), actualCorrectionViewDto.getReporterName());
        assertEquals(expectedCorrectionViewDto.getCorrecter().getId(), actualCorrectionViewDto.getCorrecter().getId());
        assertEquals(expectedCorrectionViewDto.getResolver().getId(), actualCorrectionViewDto.getResolver().getId());
        assertEquals(expectedCorrectionViewDto.getPageURL(), actualCorrectionViewDto.getPageURL());
    }

    @Test
    public void correctionToCorrectionViewDtoNullTest() {
        assertNull(correctionMapper.toCorrectionViewDto(null));
    }

    @Test
    public void correctionToCorrectionPutDtoTest() {
        final CorrectionPutDto expectedCorrectionPutDto = getCorrectionPutDto();
        final CorrectionPutDto actualCorrectionPutDto = correctionMapper.toCorrectionPutDto(getCorrection());

        assertEquals(expectedCorrectionPutDto.getId(), actualCorrectionPutDto.getId());
        assertEquals(expectedCorrectionPutDto.getReporterComment(), actualCorrectionPutDto.getReporterComment());
        assertEquals(expectedCorrectionPutDto.getCorrecterComment(), actualCorrectionPutDto.getCorrecterComment());
        assertEquals(expectedCorrectionPutDto.getResolverComment(), actualCorrectionPutDto.getResolverComment());
        assertEquals(expectedCorrectionPutDto.getPageURL(), actualCorrectionPutDto.getPageURL());
    }

    @Test
    public void correctionToCorrectionPutDtoNullTest() {
        assertNull(correctionMapper.toCorrectionPutDto(null));
    }

    @Test
    public void putDtoToCorrectionTest() {
        final Correction expectedCorrection = getCorrection();
        final Correction actualCorrection = correctionMapper.putDtoToCorrection(getCorrectionPutDto());

        assertEquals(expectedCorrection.getId(), actualCorrection.getId());
        assertEquals(expectedCorrection.getReporterComment(), actualCorrection.getReporterComment());
        assertEquals(expectedCorrection.getCorrecterComment(), actualCorrection.getCorrecterComment());
        assertEquals(expectedCorrection.getResolverComment(), actualCorrection.getResolverComment());
        assertEquals(expectedCorrection.getCorrecter().getId(), actualCorrection.getCorrecter().getId());
        assertEquals(expectedCorrection.getPageURL(), actualCorrection.getPageURL());
        assertNull(actualCorrection.getTextBeforeCorrection());
        assertNull(actualCorrection.getTextCorrection());
        assertNull(actualCorrection.getTextAfterCorrection());
        assertNull(actualCorrection.getReporterName());
        assertNull(actualCorrection.getResolver());
    }

    @Test
    public void putDtoToCorrectionNullTest() {
        assertNull(correctionMapper.putDtoToCorrection(null));
    }

    @Test
    public void postDtoToCorrectionTest() {
        final Correction expectedCorrection = getCorrection();
        final Correction actualCorrection = correctionMapper.postDtoToCorrection(getCorrectionPostDto());

        assertEquals(expectedCorrection.getTextBeforeCorrection(), actualCorrection.getTextBeforeCorrection());
        assertEquals(expectedCorrection.getTextCorrection(), actualCorrection.getTextCorrection());
        assertEquals(expectedCorrection.getTextAfterCorrection(), actualCorrection.getTextAfterCorrection());
        assertEquals(expectedCorrection.getReporterName(), actualCorrection.getReporterName());
        assertEquals(expectedCorrection.getReporterComment(), actualCorrection.getReporterComment());
        assertEquals(expectedCorrection.getPageURL(), actualCorrection.getPageURL());
        assertNull(actualCorrection.getId());
        assertNull(actualCorrection.getCorrecterComment());
        assertNull(actualCorrection.getResolverComment());
        assertNull(actualCorrection.getCorrecter());
        assertNull(actualCorrection.getResolver());
    }

    @Test
    public void postDtoToCorrectionNullTest() {
        assertNull(correctionMapper.postDtoToCorrection(null));
    }

    @Test
    public void accountToAccountGetDtoTest() {
        final AccountGetDto expectedAccountGetDto = getAccountGetDto();
        final AccountGetDto actualAccountGetDto = correctionMapper.accountToAccountGetDto(getAccount());
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
        assertNull(correctionMapper.accountToAccountGetDto(null));
    }
}
