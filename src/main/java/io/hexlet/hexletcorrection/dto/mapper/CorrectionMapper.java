package io.hexlet.hexletcorrection.dto.mapper;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.AccountGetDto;
import io.hexlet.hexletcorrection.dto.CorrectionGetDto;
import io.hexlet.hexletcorrection.dto.CorrectionPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CorrectionMapper {

    @Mapping(target = "account", qualifiedByName = "accountToAccountDto")
    CorrectionGetDto toCorrectionDto(Correction correction);

    @Named("accountToAccountDto")
    @Mapping(target = "corrections", expression = "java(null)")
    AccountGetDto toAccountDto(Account account);

    @Mapping(target = "id", ignore = true)
    Correction toCorrection(CorrectionGetDto correctionGetDto);

    @Mapping(target = "corrections", ignore = true)
    Account toAccount(AccountGetDto accountGetDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "account.name", ignore = true),
            @Mapping(target = "account.email", ignore = true),
            @Mapping(target = "account.corrections", ignore = true),
    })
    Correction postDtoToCorrection(CorrectionPostDto correctionPostDto);
}
