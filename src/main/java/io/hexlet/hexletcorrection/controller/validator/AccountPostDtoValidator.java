package io.hexlet.hexletcorrection.controller.validator;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.dto.account.AccountPostDto;
import io.hexlet.hexletcorrection.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountPostDtoValidator implements Validator {

    private final AccountService accountService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AccountPostDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AccountPostDto accountPostDto = (AccountPostDto) o;

        if (!accountPostDto.getPasswordConfirm().equals(accountPostDto.getPassword())) {
            errors.rejectValue("passwordConfirm", "error.validation.password.confirmation");
        }

        if (!accountPostDto.getEmailConfirm().equals(accountPostDto.getEmail())) {
            errors.rejectValue("emailConfirm", "error.validation.password.confirmation");
        }

        Optional<Account> accountByEmail = accountService.findByEmail(accountPostDto.getEmail());
        if (accountByEmail.isPresent()) {
            errors.rejectValue("email", "email exist");
        }

        Optional<Account> accountByUsername = accountService.findByUsername(accountPostDto.getUsername());
        if (accountByUsername.isPresent()) {
            errors.rejectValue("username", "username exist");
        }
    }
}
