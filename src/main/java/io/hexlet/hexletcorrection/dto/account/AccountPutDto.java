package io.hexlet.hexletcorrection.dto.account;

import com.github.reinert.jjschema.Attributes;
import com.github.reinert.jjschema.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_EMAIL_ERROR_INVALID;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_FIRST_NAME_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_FIRST_NAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_ID_ERROR_NULL;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_LAST_NAME_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_LAST_NAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_PASSWORD_ERROR_LENGTH_MIN;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_PASSWORD_LENGTH_MIN;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_PATTERN_EMAIL;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_PATTERN_PASSWORD;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_PATTERN_STRING;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Attributes(title = "AccountPutDtoSchema")
public class AccountPutDto implements Comparable<AccountPutDto> {

    @NotNull(message = ACCOUNT_ID_ERROR_NULL)
    @Nullable
    @Attributes(required = true)
    private Long id;

    @Max(message = ACCOUNT_LAST_NAME_ERROR_LENGTH_MAX, value = ACCOUNT_LAST_NAME_LENGTH_MAX)
    @Attributes(pattern = ACCOUNT_PATTERN_STRING, maxLength = ACCOUNT_LAST_NAME_LENGTH_MAX)
    private String lastName;

    @Max(message = ACCOUNT_FIRST_NAME_ERROR_LENGTH_MAX, value = ACCOUNT_FIRST_NAME_LENGTH_MAX)
    @Attributes(pattern = ACCOUNT_PATTERN_STRING, maxLength = ACCOUNT_FIRST_NAME_LENGTH_MAX)
    private String firstName;

    @Email(message = ACCOUNT_EMAIL_ERROR_INVALID)
    @Nullable
    @Attributes(required = true, pattern = ACCOUNT_PATTERN_EMAIL)
    private String email;

    @Min(message = ACCOUNT_PASSWORD_ERROR_LENGTH_MIN, value = ACCOUNT_PASSWORD_LENGTH_MIN)
    @Nullable
    @Attributes(required = true, pattern = ACCOUNT_PATTERN_PASSWORD)
    private String password;

    @Override
    public int compareTo(AccountPutDto accountPutDto) {
        return this.id.compareTo(accountPutDto.getId());
    }
}
