package io.hexlet.hexletcorrection.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.reinert.jjschema.Attributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.INVALID_EMAIL;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.MAX_ACCOUNT_NAME;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_EMPTY;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Attributes(title = "AccountGetDtoSchema")
public class AccountGetDto implements Comparable<AccountGetDto> {

    @NotNull(message = "Id " + NOT_NULL)
    @Attributes(required = true)
    private Long id;

    @NotBlank(message = "Username " + NOT_EMPTY)
    @Size(message = "Username not be more than " + MAX_ACCOUNT_NAME + " characters", max = MAX_ACCOUNT_NAME)
    @Attributes(required = true, pattern = "^(.*)$", maxLength = MAX_ACCOUNT_NAME)
    private String username;

    @Size(message = "Last name not be more than " + MAX_ACCOUNT_NAME + " characters", max = MAX_ACCOUNT_NAME)
    @Attributes(required = true, pattern = "^(.*)$", maxLength = MAX_ACCOUNT_NAME)
    private String lastName;

    @Size(message = "First name not be more than " + MAX_ACCOUNT_NAME + " characters", max = MAX_ACCOUNT_NAME)
    @Attributes(required = true, pattern = "^(.*)$", maxLength = MAX_ACCOUNT_NAME)
    private String firstName;

    @NotBlank(message = "Email " + NOT_EMPTY)
    @Email(message = INVALID_EMAIL)
    @Attributes(required = true, pattern = "^(.*)$")
    private String email;

    @NotNull(message = "Number corrections in progress " + NOT_NULL)
    @Attributes(required = true)
    private Integer numberCorrectionsInProgress;

    @NotNull(message = "Number corrections resolved " + NOT_NULL)
    @Attributes(required = true)
    private Integer numberCorrectionsResolved;

    @Override
    public int compareTo(AccountGetDto accountGetDto) {
        return this.id.compareTo(accountGetDto.getId());
    }
}
