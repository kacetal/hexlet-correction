package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.account.AccountGetDto;
import io.hexlet.hexletcorrection.dto.account.AccountPostDto;
import io.hexlet.hexletcorrection.dto.account.AccountPutDto;
import io.hexlet.hexletcorrection.dto.account.AccountViewDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mappings({
        @Mapping(target = "numberCorrectionsInProgress", expression = "java(account.getCorrectionsInProgress().size())"),
        @Mapping(target = "numberCorrectionsResolved", expression = "java(account.getCorrectionsResolved().size())")
    })
    AccountGetDto toAccountGetDto(Account account);

    @Mappings({
        @Mapping(target = "correctionsInProgress", qualifiedByName = "correctionToCorrectionGetDto"),
        @Mapping(target = "correctionsResolved", qualifiedByName = "correctionToCorrectionGetDto")
    })
    AccountViewDto toAccountViewDto(Account account);

    @Mapping(target = "password", ignore = true)
    AccountPutDto toAccountPutDto(Account account);

    Account putDtoToAccount(AccountPutDto accountPutDto);

    Account postDtoToAccount(AccountPostDto accountPostDto);

    @Named("correctionToCorrectionGetDto")
    @Mappings({
        @Mapping(target = "correcter", expression = "java(null)"),
        @Mapping(target = "resolver", expression = "java(null)")
    })
    CorrectionGetDto correctionToCorrectionGetDto(Correction correction);
}
