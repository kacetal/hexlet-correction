package io.hexlet.hexletcorrection.service.mapping;

import io.hexlet.hexletcorrection.web.vm.TypoReport;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TypoMapperTest {

    private final TypoMapper mapper = new TypoMapperImpl();

    public static Stream<TypoReport> getTestTypoReport() {
        return Stream.of(new TypoReport()
            .setPageUrl("PageUrl")
            .setReporterName("ReporterName")
            .setReporterRemark("ReporterRemark")
            .setTextBeforeTypo("TextBeforeTypo")
            .setTextTypo("TextTypo")
            .setTextAfterTypo("TextAfterTypo"));
    }

    @ParameterizedTest
    @MethodSource("getTestTypoReport")
    public void isNotRecursionCallForJackson(final TypoReport typoReport) {
        final var typo = mapper.typoReportToTypo(typoReport);
        assertThat(typo).isNotNull()
            .hasFieldOrPropertyWithValue("pageUrl", typoReport.getPageUrl())
            .hasFieldOrPropertyWithValue("reporterName", typoReport.getReporterName())
            .hasFieldOrPropertyWithValue("reporterRemark", typoReport.getReporterRemark())
            .hasFieldOrPropertyWithValue("textBeforeTypo", typoReport.getTextBeforeTypo())
            .hasFieldOrPropertyWithValue("textTypo", typoReport.getTextTypo())
            .hasFieldOrPropertyWithValue("textAfterTypo", typoReport.getTextAfterTypo());
    }
}
