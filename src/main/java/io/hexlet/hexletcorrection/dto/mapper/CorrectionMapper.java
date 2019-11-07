package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.account.AccountGetDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionGetDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionPostDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionPutDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CorrectionMapper {

    @Mappings({
        @Mapping(target = "correcter", qualifiedByName = "accountToAccountGetDto"),
        @Mapping(target = "resolver", qualifiedByName = "accountToAccountGetDto")
    })
    CorrectionGetDto toCorrectionGetDto(Correction correction);

    @Mappings({
        @Mapping(target = "correcter", qualifiedByName = "accountToAccountGetDto"),
        @Mapping(target = "resolver", qualifiedByName = "accountToAccountGetDto")
    })
    CorrectionViewDto toCorrectionViewDto(Correction correction);

    @Mapping(target = "correcter", qualifiedByName = "accountToAccountGetDto")
    CorrectionPutDto toCorrectionPutDto(Correction correction);

    Correction putDtoToCorrection(CorrectionPutDto correctionPutDto);

    Correction postDtoToCorrection(CorrectionPostDto correctionPostDto);

    @Named("accountToAccountGetDto")
    @Mappings({
        @Mapping(target = "numberCorrectionsInProgress", expression = "java(account.getCorrectionsInProgress().size())"),
        @Mapping(target = "numberCorrectionsResolved", expression = "java(account.getCorrectionsResolved().size())")
    })
    AccountGetDto accountToAccountGetDto(Account account);
}
