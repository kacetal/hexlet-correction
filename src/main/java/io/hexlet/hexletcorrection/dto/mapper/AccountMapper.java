package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.AccountGetDto;
import io.hexlet.hexletcorrection.dto.AccountPostDto;
import io.hexlet.hexletcorrection.dto.AccountPutDto;
import io.hexlet.hexletcorrection.dto.CorrectionGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "corrections", ignore = true)
    AccountGetDto toAccountGetDtoWithoutCorrections(Account account);

    @Mapping(target = "corrections", qualifiedByName = "correctionsToCorrectionsGetDto")
    AccountGetDto toAccountGetDto(Account account);

    @Named("correctionsToCorrectionsDto")
    @Mapping(target = "account", expression = "java(null)")
    CorrectionGetDto toCorrectionsGetDto(Correction correction);

    Account putDtoToAccount(AccountPutDto accountPutDto);

    Account postDtoToAccount(AccountPostDto accountPostDto);
}
