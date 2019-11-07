package io.hexlet.hexletcorrection.dto.account;

import com.github.reinert.jjschema.Attributes;
import com.github.reinert.jjschema.Nullable;
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
@Attributes(title = "AccountPutDtoSchema")
public class AccountPutDto implements Comparable<AccountPutDto> {

    @NotNull(message = "Id " + NOT_NULL)
    @Attributes(required = true)
    private Long id;

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

    @Nullable
    @Attributes(required = true, pattern = "^(.*)$")
    private String password;

    @Override
    public int compareTo(AccountPutDto accountPutDto) {
        return this.id.compareTo(accountPutDto.getId());
    }
}
