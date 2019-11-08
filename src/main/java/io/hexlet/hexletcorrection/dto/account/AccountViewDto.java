package io.hexlet.hexletcorrection.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.reinert.jjschema.Attributes;
import com.github.reinert.jjschema.Nullable;
import io.hexlet.hexletcorrection.dto.correction.CorrectionGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_EMAIL_ERROR_INVALID;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_FIRST_NAME_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_FIRST_NAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_ID_ERROR_NULL;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_LAST_NAME_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_LAST_NAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_PATTERN_EMAIL;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_PATTERN_STRING;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_USERNAME_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_USERNAME_ERROR_LENGTH_SIZE;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_USERNAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_USERNAME_LENGTH_MIN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Attributes(title = "AccountViewDtoSchema")
public class AccountViewDto implements Comparable<AccountViewDto> {

    @NotNull(message = ACCOUNT_ID_ERROR_NULL)
    @Nullable
    @Attributes(required = true)
    private Long id;

    @NotBlank(message = ACCOUNT_USERNAME_ERROR_BLANK)
    @Size(message = ACCOUNT_USERNAME_ERROR_LENGTH_SIZE,
        min = ACCOUNT_USERNAME_LENGTH_MIN,
        max = ACCOUNT_USERNAME_LENGTH_MAX)
    @Nullable
    @Attributes(required = true,
        pattern = ACCOUNT_PATTERN_STRING,
        minLength = ACCOUNT_USERNAME_LENGTH_MIN,
        maxLength = ACCOUNT_USERNAME_LENGTH_MAX)
    private String username;

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

    @JsonIgnoreProperties({"correcter", "resolver"})
    @Attributes(required = true)
    private Set<CorrectionGetDto> correctionsInProgress;

    @JsonIgnoreProperties({"correcter", "resolver"})
    @Attributes(required = true)
    private Set<CorrectionGetDto> correctionsResolved;

    @Override
    public int compareTo(AccountViewDto accountViewDto) {
        return this.id.compareTo(accountViewDto.getId());
    }
}
