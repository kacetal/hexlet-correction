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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.INVALID_EMAIL;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.MAX_ACCOUNT_NAME;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_EMPTY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Attributes(title = "AccountPostDtoSchema")
public class AccountPostDto implements Comparable<AccountPostDto> {

    @NotBlank(message = "Username " + "{javax.validation.constraints.NotBlank.message}")
    @Size(message = "Username " + "{javax.validation.constraints.Size.message}", min = 2, max = MAX_ACCOUNT_NAME)
    @Nullable
    @Attributes(required = true)
    private String username;

    @Max(message = "Last name " + "{javax.validation.constraints.Max.message}", value = MAX_ACCOUNT_NAME)
    @Nullable
    @Attributes(required = true, pattern = "^(.*)$", maxLength = MAX_ACCOUNT_NAME)
    private String lastName;

    @Max(message = "First name must be less than or equal to {value} characters", value = MAX_ACCOUNT_NAME)
    @Nullable
    @Attributes(required = true, pattern = "^(.*)$", maxLength = MAX_ACCOUNT_NAME)
    private String firstName;

    @NotBlank(message = "{0} " + NOT_EMPTY)
    @Email(message = INVALID_EMAIL)
    @Nullable
    @Attributes(required = true, pattern = "^(.*)$")
    private String email;

    @NotBlank(message = "{0} " + NOT_EMPTY)
    @Size(message = "{account.field.password.error.message}", min = 6, max = 20)
    @Nullable
    @Attributes(required = true, pattern = "^(.*)$")
    private String password;

    @NotBlank(message = "{0} " + NOT_EMPTY)
    @Size(message = "Password size must be between {min} and {max}", min = 6, max = 20)
    @Nullable
    @Attributes(required = true, pattern = "^(.*)$")
    private String passwordConfirm;

    @Override
    public int compareTo(AccountPostDto accountPostDto) {
        return this.email.compareTo(accountPostDto.getUsername());
    }
}
