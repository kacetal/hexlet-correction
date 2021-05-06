package io.hexlet.hexletcorrection.service;

import io.hexlet.hexletcorrection.domain.Typo;
import io.hexlet.hexletcorrection.repository.TypoRepository;
import io.hexlet.hexletcorrection.service.mapping.TypoMapperImpl;
import io.hexlet.hexletcorrection.web.vm.TypoReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TypoServiceTest {

    @Mock
    private TypoRepository repository;

    private TypoService service;

    @ParameterizedTest
    @MethodSource("io.hexlet.hexletcorrection.service.mapping.TypoMapperTest#getTestTypoReport")
    public void addTypo(final TypoReport typoReport) {
        when(repository.save(any(Typo.class))).then(returnsFirstArg());
        assertThat(service.addTypoReport(typoReport)).isNotNull()
            .hasFieldOrPropertyWithValue("pageUrl", typoReport.getPageUrl())
            .hasFieldOrPropertyWithValue("reporterName", typoReport.getReporterName())
            .hasFieldOrPropertyWithValue("reporterRemark", typoReport.getReporterRemark())
            .hasFieldOrPropertyWithValue("textBeforeTypo", typoReport.getTextBeforeTypo())
            .hasFieldOrPropertyWithValue("textTypo", typoReport.getTextTypo())
            .hasFieldOrPropertyWithValue("textAfterTypo", typoReport.getTextAfterTypo());
    }

    @BeforeEach
    void initTypoService() {
        service = new TypoService(repository, new TypoMapperImpl());
    }
}
