package io.hexlet.hexletcorrection.service.mapping;

import io.hexlet.hexletcorrection.domain.Typo;
import io.hexlet.hexletcorrection.web.vm.TypoReport;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Typo} and it report {@link TypoReport}.
 */
@Mapper(componentModel = "spring")
public interface TypoMapper {

    Typo typoReportToTypo(TypoReport typoReport);
}
